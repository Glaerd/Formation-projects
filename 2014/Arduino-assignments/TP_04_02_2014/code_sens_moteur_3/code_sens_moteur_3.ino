

int S1 = 10; // 1-4
int S2 = 11; // 2-3
int pushBut = A1;
int cmpt = 0;

void setup()
{
  Serial.begin(9600);
  pinMode( S1, OUTPUT );
  pinMode( S2, OUTPUT );
  pinMode( pushBut, INPUT );

}


void loop()
{


int push = digitalRead(pushBut);
Serial.println(cmpt);

if( push == HIGH && cmpt < 255)
  {
    delay(25);
    cmpt += 5;
  }
if( push == LOW && cmpt > -255)
  {
    delay(25);
    cmpt -= 5;
  }



 if( cmpt > 10 )
  {
    analogWrite( S2, 0 );
    delay(25);
    analogWrite( S1, cmpt );
  }
 else if(cmpt < -10 )
  {
    analogWrite( S1, 0 );
    delay(25);
    analogWrite( S2, (-1) * cmpt );
  }
 else
  {
    analogWrite( S1, 0 );
    analogWrite( S2, 0 );
  }
}
