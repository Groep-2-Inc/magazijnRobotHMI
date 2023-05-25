#include <arduino.h>
#include <Wire.h>

void sendData(int data){
  Wire.beginTransmission(9);
  Wire.write(data);
  Wire.endTransmission();
}