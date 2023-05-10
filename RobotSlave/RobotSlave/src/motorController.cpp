#include <Arduino.h>
#include <slaveCurPositionController.h>

const int directionPinY = 12;
const int pwmPinY = 3;
const int brakePinY = 9;
int globalSpeed = 255;

const int directionPinZ = 13;
const int pwmPinZ = 11;
const int brakePinZ = 8;

const int yCor[5]{150, 1200, 2250, 3300, 4350};

void motorSetup(){
    pinMode(directionPinZ, OUTPUT);
    pinMode(pwmPinZ, OUTPUT);
    pinMode(brakePinZ, OUTPUT);
    pinMode(directionPinY, OUTPUT);
    pinMode(pwmPinY, OUTPUT);
    pinMode(brakePinY, OUTPUT);
}

// Stopt met bewegen.
void stopMovement(){
    analogWrite(pwmPinY, 255);
    digitalWrite(brakePinY, HIGH);
    digitalWrite(brakePinZ, HIGH);
    analogWrite(pwmPinZ, 255);
}

// Zorgt ervoor dat de robot omhoog beweegt wanneer deze functie wordt aangeroepen.
void moveUp(){
  if(readY() < 5000){
    digitalWrite(directionPinY, LOW);
    digitalWrite(brakePinY, LOW);
    analogWrite(pwmPinY, globalSpeed);
  } else {
    stopMovement();
  }
}

// Zorgt ervoor dat de robot omlaag beweegt wanneer deze functie wordt aangeroepen.
void moveDown(){
    digitalWrite(directionPinY, HIGH);
    digitalWrite(brakePinY, LOW);
    analogWrite(pwmPinY, globalSpeed);
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

bool hasMoved = false;
void moveY (int coordinate){
    coordinate = coordinate - 1;
    if (yCor[coordinate] > readY() && !hasMoved){
        moveUp();
    }else if (yCor[coordinate] < readY() && !hasMoved){
        moveDown();
    } else {
      hasMoved = true;
    }
}

void resetHasMoved(){
  hasMoved = false;
}

bool getHasMoved(){
  return hasMoved;
}


