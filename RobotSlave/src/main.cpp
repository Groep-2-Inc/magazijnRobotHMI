// Leest de waarde van de encoder voor de x-as uit en print dit op de seriele monitor.
#include <Arduino.h>
#include <Wire.h>
#include <motorController.h>
#include <slaveCurPositionController.h>
#include <statusLights.h>
#include <IRSensor.h>
#include <manualOrAutoButtons.h>

// globalSpeed - Bepaald de snelheid van de robot.
// directionPinZ -  Definieert de pinmode van de richting van de z-as.
// pwmPinZ - Definieert de pinmode van de motor voor de z-as.
// brakePinZ - Definieert de pinmode van de rem van de z-as.
// x - Bepaald de waarde van de data van de master arduino.
// int globalSpeed = 255;

bool canMove = true;
int x = 0;

// Leest de waarde van de data van de master arduino.
void receiveEvent(int bytes){
  x = Wire.read();
}

// Zet de pinmode van de bovenstaande variabelen en zorgt ervoor dat de data van de master arduino ontvangen wordt.
void setup() {
  statusLightsSetup();
  motorSetup();
  encoderSetup();
  IRSensorSetup();
  manualOrAutoButtonsSetup();
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);
}

void statusLightsOn(){
  if(x == 21){
    emergencyLEDOn();
  } else if(x == 22){
    manualLEDOn();
  } else if(x == 23){
    autoLEDOn();
  }
}

void loop() {

  // Serial.println(readY());
  // Als de waarde 0 is
  if (x == 0){
    // Stop met bewegen
    stopMovement();
    // Als de waarde 1 is
  }else if (x == 1){
    // Beweeg naar voren
    if(canMove)
      moveForward();
  // Als de waarde 2 is
  }else if (x == 2){
    // Beweeg naar achter
    if(canMove)
      moveBackward();
  }else if (x == 3){
    // Beweeg naar achter
    if(canMove)
      moveUp();
  }else if (x == 4){
    // Beweeg naar achter
    if(canMove)
      moveDown();
  } else if (x == 5){
    canMove = !canMove;
  } else if (x == 6 || x == 7 || x == 8 || x == 9 || x == 10){
    if(getHasMoved() == false && canMove){
      moveY(x-5);
    }
  } else if (x == 11){
    resetHasMoved();
  } else if (x == 12){
    pickUpProduct();
  }

  statusLightsOn();

  measureZas();

  checkBtns();

  if(getHasMoved() == true){
  Wire.beginTransmission(9);
  Wire.write(101);
  Wire.endTransmission();
  } else {
    Wire.beginTransmission(9);
    Wire.write(100);
    Wire.endTransmission();
  }

  delay(10);
  // Serial.print(readY());
}