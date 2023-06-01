#include <Arduino.h>
#include <Wire.h>

// Leest de waarde van de data van de master arduino.
int x = 0;

// Leest de waarde van de data van de master arduino.
void receiveEvent(int bytes){
  x = Wire.read();
}

// Zet juiste instellingen voor de communicatie
void commsSetup(){
    Wire.begin(9);
    Wire.onReceive(receiveEvent);
    Serial.begin(9600);
}

// Versturud data naar de master
void toMasterArduino(int data){
  Wire.beginTransmission(9);
  Wire.write(data);
  Wire.endTransmission();
}

// Getter voor de ontvangen waarde
int getRecivedValue(){
  return x;
}