// Importation de la librairie du LCD

#include <Wire.h>
#include <Adafruit_MCP23017.h>
#include <Adafruit_RGBLCDShield.h>
Adafruit_RGBLCDShield lcd = Adafruit_RGBLCDShield();
#define WHITE 0x7

int S1 = 10; // Fils 1-4
int S2 = 11; // Fils 2-3
int cmpt = 0; // Valeur permettant d'établir le PWM
int side = 0; // Valeur variant selon sur quel bouton on appuie
int E1 = A2;

void setup()
{
  Serial.begin(9600);
  pinMode( S1, OUTPUT );
  pinMode( S2, OUTPUT );
  pinMode( E1, INPUT );
  lcd.begin(16, 2); // Instaure l'écran LCD à 2 lignes x 16 cases
  lcd.print("Welcome !"); // Ecrit "Welcome !" sur la première ligne par défaut
  lcd.setCursor(0, 1); // Selectionne la deuxième ligne
  lcd.print("Choose a Side"); // Ecrit "Choose a Side" sur la deuxième ligne
  lcd.setBacklight(WHITE); // Définit la couleur d'arrière-plan

}



void loop()
{
  
  int vit = analogRead(E1);

  uint8_t buttons = lcd.readButtons(); // Définit les boutons de la librairie du LCD 

  if (buttons) { // Si un bouton est appuyé
    lcd.clear(); // Efface ce qui est écrit
    lcd.setCursor(0,0); // Selectionne la première ligne
    if (buttons & BUTTON_RIGHT) { // Si un bouton est appuyé et qu'il s'agit de BUTTON_RIGHT
      if (cmpt > 0) {
        lcd.print("To The Right "); // Ecrit "To The Right" sur la première ligne
        lcd.setCursor(0,1); 
        int vit1 = vit;
        delay(1);
        int vit2 = vit;
        delay(1);
        int vit3 = vit;
        delay(1);
        int moy = (vit1 + vit2 + vit3) / 3;
        lcd.print(moy);
      }
      else if (cmpt < 0) {
        lcd.print("To The Left "); 
        lcd.setCursor(0,1); 
        int vit1 = vit;
        delay(1);
        int vit2 = vit;
        delay(1);
        int vit3 = vit;
        delay(1);
        int moy = (vit1 + vit2 + vit3) / 3;
        lcd.print(moy);
      }
      else if (cmpt == 0) lcd.print("Choose a Side ");
      side = 0;
      if(cmpt < 250) cmpt += 10; // 
    }
    else if (buttons & BUTTON_LEFT) { // Si un bouton est appuyé et qu'il s'agit de BUTTON_LEFT
      if (cmpt < 0) {
        lcd.print("To The Left "); // Ecrit "To The Left" sur la première ligne
        lcd.setCursor(0,1); 
        int vit1 = vit;
        delay(1);
        int vit2 = vit;
        delay(1);
        int vit3 = vit;
        delay(1);
        int moy = (vit1 + vit2 + vit3) / 3;
        lcd.print(moy);
      }
      else if (cmpt > 0) {
        lcd.print("To The Right ");
        lcd.setCursor(0,1); 
        int vit1 = vit;
        delay(1);
        int vit2 = vit;
        delay(1);
        int vit3 = vit;
        delay(1);
        int moy = (vit1 + vit2 + vit3) / 3;
        lcd.print(moy);
      }
      else if (cmpt == 0) lcd.print("Choose a Side ");
      side = 0;
      if (cmpt > -250) cmpt -= 10; // 
    }
    else if (buttons & BUTTON_SELECT) { // Si un bouton est appuyé et qu'il s'agit de BUTTON_SELECT
      lcd.print("Choose a side "); // Ecrit "Choose a Side" sur la première ligne
      side = 1; // La valeur side est à 3
    }
  }
  
  
  
  if( side == 1 && cmpt < 0) // Si side est à 1 (Donc si on a appuyé sur BUTTON_SELECT) et si la valeur cmpt est inférieure à 0 
  {
    cmpt += 5; // Incrémente la valeur cmpt de 5
    delay(25); // Attend 25 ms
  }
  else if( side == 1 && cmpt > 0) // Si side est à 1 (Donc si on a appuyé sur BUTTON_SELECT) et si la valeur cmpt est supérieure à 0 
  {
    cmpt -= 5; // Décrémente la valeur cmpt de 5
    delay(25); // Attend 25 ms
  }
  else if( side == 1 && cmpt == 0) side = 0;
  
  
  

 if( cmpt > 0 ) // Si la valeur cmpt est supérieure à 0
  {
    analogWrite( S2, 0 ); // Envoie 5V sur les sorties 2-3 pendant 0 "sous-périodes" de la période totale du PWM
    analogWrite( S1, cmpt ); // Envoie 5V sur les sorties 1-4 pendant 255 "sous-périodes" de la période totale du PWM
  }
 else if(cmpt < 0 ) // Si la valeur cmpt est inférieure à 0
  {
    analogWrite( S1, 0 ); // Envoie 5V sur les sorties 1-4 pendant 0 "sous-périodes" de la période totale du PWM
    analogWrite( S2, (-1) * cmpt ); // Envoie 5V sur les sorties 2-3 pendant 255 "sous-périodes" de la période totale du PWM
  }
 else // Sinon
  {
    analogWrite( S1, 0 ); // Envoie 5V sur les sorties 1-4 pendant 255 "sous-périodes" de la période totale du PWM
    analogWrite( S2, 0 ); // Envoie 5V sur les sorties 2-3 pendant 255 "sous-périodes" de la période totale du PWM
  }
}
