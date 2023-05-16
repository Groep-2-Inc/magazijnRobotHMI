#include <Arduino.h>
#include <slaveCurPositionController.h>
#include <IRSensor.h>

const int directionPinY = 12;
const int pwmPinY = 3;
const int brakePinY = 9;
int globalSpeed = 255;

const int directionPinZ = 13;
const int pwmPinZ = 11;
const int brakePinZ = 8;

const int yCor[5]{150, 1200, 2250, 3300, 4350};

int curY = 0;
bool hasProduct = false;
bool productPicked = false;

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
  if(measureZas() < 8.80){
    digitalWrite(directionPinZ, LOW);
    digitalWrite(brakePinZ, LOW);
    analogWrite(pwmPinZ, globalSpeed);
  } else {
    stopMovement();
  }
 
}

// Zorgt ervoor dat de z-as naar achter kan bewegen.
void moveBackward(){
  if (measureZas() > 4.16){
    digitalWrite(directionPinZ, HIGH);
    digitalWrite(brakePinZ, LOW);
    analogWrite(pwmPinZ, globalSpeed);
  } else {
    stopMovement();
  }
 
}

void pickUpProduct(){
  if(!productPicked){
    if (curY == 0){
    curY = readY();
    }

    if(!hasProduct){
      if(measureZas() < 9){
        moveForward();
      }else if (readY() < curY + 300){
        moveUp();
      }else{
        stopMovement();
        hasProduct = true;
      }
    } else {
      if(measureZas() > 4.17){
        moveBackward();
      }else if (readY() > curY){
        moveDown();
      }else{
        hasProduct = false;
        productPicked = true;
      }
    }
  } else {
    stopMovement();
  }
  
}

bool getProductPicked(){
  return productPicked;
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


