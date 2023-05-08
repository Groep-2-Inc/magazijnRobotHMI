// Leest de waarde van de encoder voor de x-as uit en print dit op de seriele monitor.
#include <Arduino.h>
#include <Wire.h>

// globalSpeed - Bepaald de snelheid van de robot.
// directionPinZ -  Definieert de pinmode van de richting van de z-as.
// pwmPinZ - Definieert de pinmode van de motor voor de z-as.
// brakePinZ - Definieert de pinmode van de rem van de z-as.
// x - Bepaald de waarde van de data van de master arduino.
int globalSpeed = 255;
const int directionPinZ = 13;
const int pwmPinZ = 11;
const int brakePinZ = 8;

int x = 0;

// Leest de waarde van de data van de master arduino.
void receiveEvent(int bytes){
  x = Wire.read();
}

// Zet de pinmode van de bovenstaande variabelen en zorgt ervoor dat de data van de master arduino ontvangen wordt.
void setup() {
  pinMode(directionPinZ, OUTPUT);
  pinMode(pwmPinZ, OUTPUT);
  pinMode(brakePinZ, OUTPUT);
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);
}

// Zorgt ervoor dat de z-as naar voren kan bewegen.
void moveForward(){
  digitalWrite(directionPinZ, LOW);
  digitalWrite(brakePinZ, LOW);
  analogWrite(pwmPinZ, globalSpeed);
}

// Zorgt ervoor dat de z-as naar achter kan bewegen.
void moveBackward(){
  digitalWrite(directionPinZ, HIGH);
  digitalWrite(brakePinZ, LOW);
  analogWrite(pwmPinZ, globalSpeed);
}

// Stopt de z-as van bewegen.
void stopMovement(){
  digitalWrite(brakePinZ, HIGH);
  analogWrite(pwmPinZ, 0);
}

void toMasterArduino(int value){
  Wire.beginTransmission(9);
	Wire.write(value);
	Wire.endTransmission();
}

void loop() {
  // Serial.println(x);
  // Als de waarde 0 is
  if (x == 0){
    // Stop met bewegen
    stopMovement();
  // Als de waarde 1 is
  }else if (x == 1){
    // Beweeg naar voren
    moveForward();
  // Als de waarde 2 is
  }else if (x == 2){
    // Beweeg naar achter
    moveBackward();
  }
}
