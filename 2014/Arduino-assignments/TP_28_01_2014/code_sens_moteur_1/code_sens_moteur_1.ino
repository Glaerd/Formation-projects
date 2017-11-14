int S1 = 10; // Fils 1-4
int S2 = 11; // Fils 2-3
int en = A0; // Entrée relevant une tension soit à +5V, soit à 0V

void setup()
{
  Serial.begin(9600);
  pinMode( S1, OUTPUT );
  pinMode( S2, OUTPUT );

  pinMode( en, INPUT );
}


  
void loop()
{

int current = analogRead(en); // crée un entier "current" variant entre 0 et 1023 selon la tension relevée sur l'entrée A0
Serial.println(current); // Affiche dans le moniteur série la valeur de "current"
 if( current > 1000 ) { // Condition : Si "current" supérieur à 1000, soit si on envoie +5V sur A0, exécuter :
      analogWrite( S2, 0 ); // Envoie +5V sur les fils 2-3 pendant 0 "sous-périodes" de la période totale du PWM : Ton/T = 0
      analogWrite( S1, 255 ); // Envoie +5V sur les fils 1-4 pendant 255 "sous-périodes" de la période totale du PWM : Ton/T ~ 1
      
      }
  else // Sinon
      analogWrite( S1, 0 ); // Envoie +5V sur les fils 2-3 pendant 0 "sous-périodes" de la période totale du PWM : Ton/T = 0
      analogWrite( S2, 255 ); // Envoie +5V sur les fils 2-3 pendant 255 "sous-périodes" de la période totale du PWM : Ton/T ~ 1
    
}
