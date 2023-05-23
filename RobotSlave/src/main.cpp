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
    autoLEDOff();
    manualLEDOff();
  } else if(x == 22){
    manualLEDOn();
    autoLEDOff();
  } else if(x == 23){
    autoLEDOn();
    manualLEDOff();
  }
}

void sendData(int data){
  Wire.beginTransmission(9);
  Wire.write(data);
  Wire.endTransmission();
}

// // Zorgt ervoor dat data vanuit de slave arduino naar de master arduino gestuurd wordt
// void toMasterArduino(int value){
//   Wire.beginTransmission(9);
// 	Wire.write(value);
// 	Wire.endTransmission();
// }

void loop() {

  switch (x)
  {
    case 0:
      // Stop met bewegen
      stopMovement();
      break;
    case 1:
      // Beweeg naar voren
      if(canMove)
        moveForward();
      break;
    case 2:
      // Beweeg naar achter
      if(canMove)
        moveBackward();
      break;
    case 3:
      // Beweeg naar boven (door Jason Joshua)
      if(canMove)
        moveUp();
      break;
    case 4:
      // Beweeg naar beneden (door Jason Joshua)
      if(canMove)
        moveDown();
      break;
    case 5:
      //verander de canmove bool (door Jason Joshua)
      canMove = !canMove;
      break;
    case 6: case 7: case 8: case 9: case 10:
      //als je kan bewegen, ga dan naar een specefieke y coordinaat (door Jason Joshua)
      if(getHasMoved() == false && canMove){
        moveY(x-5);
      }
      break;
    case 11:
      //reset de hasmoved (door Jason Joshua)
      resetHasMoved();
      break;
    case 12:
      //pak een product op (door Jason Joshua)
      pickUpProduct();
    default:
      break;
  }

  //uitgecommend voor het testen. nog behouden tot alles hierboven 1000% werkt
  // // Serial.println(readY());
  // // Als de waarde 0 is
  // if (x == 0){
    
  //   // Als de waarde 1 is
  // }else if (x == 1){
  //   // Beweeg naar voren
  //   if(canMove)
  //     moveForward();
  // // Als de waarde 2 is
  // }else if (x == 2){
  //   // Beweeg naar achter
  //   if(canMove)
  //     moveBackward();
  // }else if (x == 3){
  //   // Beweeg naar boven (door Jason Joshua)
  //   if(canMove)
  //     moveUp();
  // }else if (x == 4){
  //   // Beweeg naar beneden (door Jason Joshua)
  //   if(canMove)
  //     moveDown();
  // } else if (x == 5){
  //   //verander de canmove bool (door Jason Joshua)
  //   canMove = !canMove;
  // } else if (x == 6 || x == 7 || x == 8 || x == 9 || x == 10){
  //   //als je kan bewegen, ga dan naar een specefieke y coordinaat (door Jason Joshua)
  //   if(getHasMoved() == false && canMove){
  //     moveY(x-5);
  //   }
  // } else if (x == 11){
  //   //reset de hasmoved (door Jason Joshua)
  //   resetHasMoved();
  // } else if (x == 12){
  //   //pak een product op (door Jason Joshua)
  //   pickUpProduct();
  // }

  statusLightsOn();

  checkBtns();

  measureZas();

  // als hasmoved true is, return een statuscode naar de hoofdarduino, zo niet return dan een andere statuscode  (door Jason Joshua)
  if(getHasMoved()){
    sendData(101);
  } else {
    sendData(100);
  }

  if(isAutoMode()){
    // Serial.println(isAutoMode());
    autoLEDOn();
    sendData(22);
  }else{
    // Serial.println(isAutoMode());
    manualLEDOn();
    sendData(23);
  }

  //delay voor het zorgen dat de arduinos meer gelijk lopen.
  delay(10);
  // Serial.print(readY());
}