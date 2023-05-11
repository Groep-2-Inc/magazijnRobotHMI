// Leest de waarde van de encoder voor de x-as uit en print dit op de seriele monitor.
#include <Arduino.h>
#include <Wire.h>
#include <motorController.h>
#include <slaveCurPositionController.h>
#include <statusLights.h>
// #include <manualOrAutoButtons.h>

// globalSpeed - Bepaald de snelheid van de robot.
// directionPinZ -  Definieert de pinmode van de richting van de z-as.
// pwmPinZ - Definieert de pinmode van de motor voor de z-as.
// brakePinZ - Definieert de pinmode van de rem van de z-as.
// x - Bepaald de waarde van de data van de master arduino.
// int globalSpeed = 255;

bool canMove = false;
int x = 0;

// Leest de waarde van de data van de master arduino.
void receiveEvent(int bytes){
  x = Wire.read();
}

// Zet de pinmode van de bovenstaande variabelen en zorgt ervoor dat de data van de master arduino ontvangen wordt.
void setup() {
  motorSetup();
  encoderSetup();
  statusLightsSetup();
  // manualOrAutoButtonsSetup();
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

//Ontvangt status van de Main Arduino en laat op basis hiervan lampjes branden (Sarah)
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
  } else if (x == 5){
    canMove = !canMove;
  } else if (x == 6 || x == 7 || x == 8 || x == 9 || x == 10){
    if(getHasMoved() == false){
      moveY(x-5);
    }
  } else if (x == 11){
    resetHasMoved();
  }

  if(getHasMoved() == true){
  Wire.beginTransmission(9);
  Wire.write(101);
  Wire.endTransmission();
  } else {
    Wire.beginTransmission(9);
    Wire.write(100);
    Wire.endTransmission();
  }

  statusLightsOn();

  // if(isAutoMode()){
  //   Wire.beginTransmission(9);
  //   Wire.write(24);
  //   Wire.endTransmission();
  // } else if(!isAutoMode()){
  //   Wire.beginTransmission(9);
  //   Wire.write(25);
  //   Wire.endTransmission();
  // }
}