// Door Sarah

#include <Arduino.h>

const int sensor = A3; // pin infraroodsensor

void IRSensorSetup() {
  pinMode(sensor, INPUT);
}

// onderstaande functie leest de IR sensor uit en rekent hem om, zodat hij de juiste afstand weergeeft
double measureZas() {
  float volts = analogRead(sensor)*0.0048828125;
  double distance = 13*pow(volts, -1);
  
  return distance;
}