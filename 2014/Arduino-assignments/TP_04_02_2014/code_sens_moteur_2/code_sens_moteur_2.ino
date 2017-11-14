int S1 = 10; // 1-4
int S2 = 11; // 2-3
int en = A0;

void setup()
{
  Serial.begin(9600);
  pinMode( S1, OUTPUT );
  pinMode( S2, OUTPUT );

  pinMode( en, INPUT );
}


  
void loop()
{

int current = analogRead(en);  //Lis une valeur de tension variant de 0 à 1023 selon la tension
Serial.println(current);  //Envoi de la valeur lue dans le moniteur série
 if( current > 980 ) {   //Après quelques tests, on trouve les valeurs correspondant à +5V et à 0V.
                        // On compare avec un nombre se trouvant entre les deux valeurs
      analogWrite( S2, 0 );   //La sortie S2 n'émet aucune tension quand le cable est branché à +5V
      analogWrite( S1, 255 );  //La sortie S1 émet une tension de +5V quand le cable est branché à +5V
      
      }
  else
      {
      analogWrite( S1, 0 );   //La sortie S1 n'émet aucune tension quand le cable n'est pas branché
      analogWrite( S2, 255 );   //La sortie S2 émet une tension de +5V quand le cable n'est pas branché
      }

  
}
