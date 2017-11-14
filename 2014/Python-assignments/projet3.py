

from mpl_toolkits.basemap import Basemap
import matplotlib.pyplot as plt
import numpy as numpy
import csv
from pprint import pprint
from numpy import *
import pickle
import matplotlib.mlab as mlab


dpFilePath = 'depenses_publiques.csv'
dpFilePath2 = 'depenses_publiques_%.csv'
centroids = 'phys_geo.csv'

dictAllDep = dict()
dictAllDep2 = dict()
dictCen = dict()
temp = []
temp2 = []
temp3 = []
alldp = []
alldp2 = []
listTaille = list()
listColor = []
listColor2 = []
listx = list()
listy = list()
listtemp = []

with open(dpFilePath, 'r') as dpFile:
	dp = csv.reader(dpFile, delimiter=';')
	dp.next()
	for row in dp:
            if row[0] not in dictAllDep:
		dictAllDep[row[0]] = {}
            for i in range(2,21):
                if row[i]=='#VALEUR!':
                    row[i] = '0,0'
	    dictAllDep[row[0]][row[1]] = [row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13], row[14], row[15], row[16], row[17], row[18], row[19], row[20]]

for pays,num in dictAllDep.iteritems():
    for n, infos in num.iteritems():
        for i in range(0,19) :
            temp.append(infos[i].replace(',','.'))

temp = filter(lambda a: a != '0.0', temp)
for i in temp :
    alldp.append(float(i))

    

f = plt.figure()
x = numpy.array(alldp)
(n, bins, patches) = plt.hist(x, bins = [0,100000,200000,300000,400000,500000,600000,700000,800000,900000,1000000,1100000,1200000,1300000,1400000,1500000])
plt.axis([0, 1500000, 0, 350])
plt.title('Depenses publiques des differents pays europeens sur 19 ans')
plt.xlabel("Depenses publiques (en millions d'euros)")
plt.ylabel('Nombre de pays entrant dans ces depenses')
plt.show()

with open(dpFilePath2, 'U') as dpFile:
	dp = csv.reader(dpFile, delimiter=';')
	dp.next()
	for row in dp:
            if row[0] not in dictAllDep2:
		dictAllDep2[row[0]] = {}
            for i in range(2,21):
                if row[i]==':':
                    row[i] = '0,0'
            dictAllDep2[row[0]][row[1]] = [row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], row[13], row[14], row[15], row[16], row[17], row[18], row[19], row[20], row[1]]

for pays,num in dictAllDep2.iteritems():
    for n, infos in num.iteritems():
        for i in range(0,19) :
            temp2.append(infos[i].replace(',','.'))
temp2 = filter(lambda a: a != '0.0', temp2)
for i in temp2 :
    alldp2.append(float(i))
    

f = plt.figure()
x2 = numpy.array(alldp2)
(n, bins, patches) = plt.hist(x2, 25)
plt.axis([0, 100, 0, 100])
plt.title('Pourcentage des depenses publiques des differents pays europeens sur 19 ans')
plt.xlabel("Pourcentage des depenses publiques")
plt.ylabel('Nombre de pays entrant dans ces pourcentages')
'''a = numpy.zeros((2,512*512), dtype=numpy.float32)
plt.plot(bins, numpy.std(a), 10)'''
plt.show()




map = Basemap(projection='merc', lat_0=50, lon_0=10, resolution='l', area_thresh=100.0, llcrnrlon=-20, llcrnrlat=30, urcrnrlon=40, urcrnrlat=70)
 

map.drawcoastlines()
map.drawcountries()
'''map.fillcontinents(color='coral', lake_color='blue')'''
map.drawmapboundary()
map.drawmeridians(numpy.arange(0, 360, 30))
map.drawparallels(numpy.arange(-90, 90, 30))
map.bluemarble()


with open(centroids, 'r') as dpFile:
	dp = csv.reader(dpFile, delimiter=',')
	dp.next()
	for row in dp:
            if row[0] not in dictCen:
		dictCen[row[0]] = {}
	    dictCen[row[0]][row[1]] = [row[3], row[4], row[0]]

for e2 in dictCen:
    for e3 in dictCen[e2]:
	xpt,ypt = map(float(dictCen[e2][e3][1]),float(dictCen[e2][e3][0]))
        for e4 in dictAllDep2:
            for e5 in dictAllDep2[e4]:
                if dictCen[e2][e3][2] == dictAllDep2[e4][e5][19]:
                    listx.append(xpt)
                    listy.append(ypt)
                    inc = 0
                    compt = 0
                    for i in range(2,18):
                        inc += float(dictAllDep2[e4][e5][i].replace(',','.'))
                        if not float(dictAllDep2[e4][e5][i].replace(',','.'))==0.0:
                            compt += 1
                    moy=float(inc)/float(compt)
                    aff=30*(moy-30)
                    listTaille.append(aff)
for e6 in dictAllDep:
    for e7 in dictAllDep[e6]:
        inc2 = 0
        compt2 = 0
        for i in range(2,18):
            if not float(dictAllDep[e6][e7][i].replace(',','.'))==0.0:
                inc2 += float(dictAllDep[e6][e7][i].replace(',','.'))
                compt2 += 1
        moy2=float(inc2)/float(compt2)
        listColor2.append(255*moy2/1048694.92)

for color in listColor2 :

    if color < 50:
        listColor.append('#FFC0C0')
    if color < 100:
        listColor.append('#FF6060')
    if color < 150:
        listColor.append('#FF3030')
    if color < 200:
        listColor.append('#E00000')
    if color < 250:
        listColor.append('#A00000')
    if color >= 250:
        listColor.append('#000000')
sc = map.scatter(listx, listy, listTaille, c=listColor, marker='s', alpha=0.9)
plt.title('Depenses publiques - Taille en fonction du pourcentage du PIB - Couleur en fonction des millions depenses')
plt.show()