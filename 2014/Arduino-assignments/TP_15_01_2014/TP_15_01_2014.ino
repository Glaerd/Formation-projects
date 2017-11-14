int en = 3;
int C1 = 5;
int C2 = 6;
int C3 = 9;
int C4 = 10;
const int motorPin = 4;
const int C = 11;


void setup()
{
  Serial.begin(9600);
  pinMode(C1, OUTPUT);
  pinMode(C2, OUTPUT);
  pinMode(C3, OUTPUT);
  pinMode(C4, OUTPUT);
  pinMode(en, INPUT);
}

void loop()
{
  en = analogRead(en);
  Serial.println(en);
  if (Serial.available())
  {
    char ch = Serial.read();
    if (isDigit(ch))
    {
      int speed = map(ch,'0','9',0,255);
      analogWrite(motorPin, speed);
      Serial.println(speed);
    }
    else
    {
      Serial.print("Unexpected character ");
      Serial.print(ch);
    }
  }

  if (en == 255)
  {
    C1 = motorPin;
    C4 = motorPin;
    C2 = 0;
    C3 = 0;
  }
  if (en == 0)
  {
    C1 = 0;
    C4 = 0;
    C2 = motorPin;
    C3 = motorPin;
  }
}


