package com.esiee.mbdaihm;

import com.esiee.mbdaihm.dataaccess.geojson.NEGeoJsonDecoder;
import com.esiee.mbdaihm.dataaccess.geojson.RawCountry;
import com.esiee.mbdaihm.dataaccess.wdi.WDIIndicatorsDecoder;
import com.esiee.mbdaihm.datamodel.DataManager;
import com.esiee.mbdaihm.datamodel.countries.Country;
import com.esiee.mbdaihm.datamodel.countries.GeoPoint;
import com.esiee.mbdaihm.datamodel.countries.Polygon;
import com.esiee.mbdaihm.datamodel.indicators.Indicator;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Application entry point.
 */
public class Launch
{
    public static final String WDI_FOLDER = "./data/WDI";

    private static final String COUNTRIES_FILE = "./data/ne_50m_admin_0_countries.json";

    private static final Logger LOGGER = LoggerFactory.getLogger(Launch.class);
    
    private static List<Country> countries;
    
    private static JFrame frame;

    private static DrawingPanel panel;
    
    private static MouseWheelEventClass mel;

    private static void populateCountries()
    {
        // Parse the countries data file
        List<RawCountry> rawData = NEGeoJsonDecoder.parseFile(new File(COUNTRIES_FILE));
        LOGGER.info("Parsed {} countries.", rawData.size());

        // Convert into our data model
        countries = NEGeoJsonDecoder.convert(rawData);
        LOGGER.info("Converted {} countries.", countries.size());

        // Store in the DataManager
        DataManager.INSTANCE.setCountries(countries);

        // Print the list of countries for debug
        countries.forEach((Country country) ->
        {
            // Change the log level to info to display all countries with default log config
            LOGGER.debug("Country : {} - code : {} ; {} polygons.",
                         country.getName(),
                         country.getIsoCode(),
                         country.getGeometry().getPolygons().size());
        });
    }

    private static void populatesIndicators()
    {
        // Decode the indicators files
        List<Indicator> indicators = WDIIndicatorsDecoder.decode(WDI_FOLDER);
        LOGGER.info("Parsed {} indicators in WDI series.", indicators.size());

        // Categorise and store in the DataManager
        WDIIndicatorsDecoder.categoriseIndicators(indicators);
    }

    /**
     * Decode and store countries and indicators in the {@link DataManager}.
     */
    public static void initData()
    {
        // Log the working directory to help debug...
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        LOGGER.info("Started application, working dir: {} ", s);

        populateCountries();
        populatesIndicators();
    }

    /**
     * Application entry point.
     *
     * @param args no parameter used
     */
    public static void main(String[] args)
    {
        initData();
        frame = new JFrame("Countries");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1600,1000);
        frame.setVisible(true);
        panel = new DrawingPanel();
        mel = new MouseWheelEventClass(panel);
        frame.add(panel);
        // Instanciate and display the view here
    }
    
    private static class DrawingPanel extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform tr = g2d.getTransform();
            tr.scale(getWidth()/360, -getHeight()/180);
            tr.translate(180, -90);
            tr.scale(mel.scaleFactor, mel.scaleFactor);
            tr.translate(mel.mousePos[0],-mel.mousePos[1]);
            g2d.setTransform(tr);
            Random rand = new Random();
            countries.forEach((Country country) ->
            {
                float rc = rand.nextFloat();
                float gc = rand.nextFloat();
                float bc = rand.nextFloat();
                List<GeneralPath> paths = new ArrayList();
                country.getGeometry().getPolygons().forEach((Polygon polygon)->
                {
                    GeneralPath t = new GeneralPath();
                    t.moveTo(polygon.points[0].lon,polygon.points[0].lat);
                    for(GeoPoint point : polygon.points)
                    {
                        t.lineTo(point.lon, point.lat);
                    }
                    t.closePath();
                    g2d.setPaint(Color.BLACK);
                    g2d.setStroke(new BasicStroke(0.5f * (float)(1/mel.scaleFactor)));
                    g2d.draw(t);
                    paths.add(t);
                });
                g2d.setPaint(new Color(rc,bc,gc));
                paths.forEach((t) -> {
                    g2d.fill(t);
                });
            });
        }
    }
}
