// Leest de waarde van de encoder voor de x-as uit en print dit op de seriele monitor.
#include <Arduino.h>
#include <Wire.h>
#include <motorController.h>
#include <slaveCurPositionController.h>

// globalSpeed - Bepaald de snelheid van de robot.
// directionPinZ -  Definieert de pinmode van de richting van de z-as.
// pwmPinZ - Definieert de pinmode van de motor voor de z-as.
// brakePinZ - Definieert de pinmode van de rem van de z-as.
// x - Bepaald de waarde van de data van de master arduino.
// int globalSpeed = 255;


int x = 0;

// Leest de waarde van de data van de master arduino.
void receiveEvent(int bytes){
  x = Wire.read();
}

// Zet de pinmode van de bovenstaande variabelen en zorgt ervoor dat de data van de master arduino ontvangen wordt.
void setup() {
  motorSetup();
  encoderSetup();
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);
}

void loop() {
  Serial.println(readY());
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
  }else if (x == 3){
    // Beweeg naar achter
    moveUp();
  }else if (x == 4){
    // Beweeg naar achter
    moveDown();
  }
}