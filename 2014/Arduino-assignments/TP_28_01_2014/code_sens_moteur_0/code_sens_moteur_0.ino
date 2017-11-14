int S1 = 10; // Fils 1-4
int S2 = 11; // Fils 2-3
int en = A0; // Entrée relevant une tension soit à +5V, soit à 0V

void setup()
{
  Serial.begin(9600);
  pinMode( S1, OUTPUT );
  pinMode( S2, OUTPUT );
  analogWrite (S1, 0) ;
  analogWrite (S2, 0) ;
  pinMode (en, INPUT) ;
}
void loop()
{
int current = analogRead (en) ; // crée un entier "current" variant entre 0 et 1023 selon la tension relevée sur l'entrée A0
Serial.write(en) ; // Affiche dans le moniteur série la valeur de "current"
   if (current < 0) { // Condition : "current" ne pouvant pas être négatif cette condition, 
   //cette condition ne pourra jamais se produire... Ceci était notre premier code.
     analogWrite (S2, 0 ) ;
     Serial.println ( ‘’S2 = 0 ‘’) ;
     int newCurrent = -1*current ;
     Serial.print ( ‘’S1 =’’) ;
     Serial.println ( newCurrent) ;
     analogWrite (S1, (-1*current) ) ;
     analogWrite ( S2,0) ;
}
else
if (current > 0) {
     analogWrite (S1,0) ;
     Serial.println ( ‘’S1 = 0’’) ;
     Serial.print ( ‘’S2 = ‘’) ;
     Serial.println( current) ;
     analogWrite ( S2, current) ; // analogWrite envoie une valeur entre 0 et 255 alors que current peut aller jusqu'à 1023. 
     //Nous étions vraiment des novices
     analogWrite (S1, 0) ;
}
}

