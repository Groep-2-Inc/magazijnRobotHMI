#include <Arduino.h>
#include <joystick.h>

int globalSpeed = 255;
const int directionPinX = 13;
const int pwmPinX = 11;
const int directionPinY = 12;
const int pwmPinY = 3;
const int brakePinX = 8;
const int brakePinY = 9;

void motorSetup() {
    pinMode(directionPinX, OUTPUT);
    pinMode(pwmPinX, OUTPUT);
    pinMode(directionPinY, OUTPUT);
    pinMode(pwmPinY, OUTPUT);
    pinMode(brakePinX, OUTPUT);
    pinMode(brakePinY, OUTPUT);
}

void moveUp(){
    digitalWrite(directionPinY, LOW);
    digitalWrite(brakePinY, LOW);

    analogWrite(pwmPinY, globalSpeed);
}

void moveDown(){
    digitalWrite(directionPinY, HIGH);
    digitalWrite(brakePinY, LOW);

    analogWrite(pwmPinY, globalSpeed);
}

void moveLeft(){
    digitalWrite(directionPinX, LOW);
    digitalWrite(brakePinX, LOW);

    analogWrite(pwmPinX, globalSpeed);
}

void moveRight(){
    digitalWrite(directionPinX, HIGH);
    digitalWrite(brakePinX, LOW);

    analogWrite(pwmPinX, globalSpeed);
}

void stopMovement(){
    digitalWrite(brakePinX, HIGH);
    digitalWrite(brakePinY, HIGH);
    analogWrite(pwmPinX, 0);
    analogWrite(pwmPinY, 0);
}

void manualControl(){
    float dir = readJoystick();

    if (dir == 1.0){
        moveLeft();
        Serial.println("L");
    }else if (dir == 2.0){
        moveRight();
        Serial.println("R");
    }else if (dir == 0.1){
        moveUp();
        Serial.println("U");
    }else if (dir == 0.2){
        moveDown();
        Serial.println("D");
    }else if (dir == 1.1){
        moveUp();
        moveLeft();
    }else if (dir == 2.1){
        moveUp();
        moveRight();
    }else if (dir == 1.2){
        moveDown();
        moveLeft();
    }else if (dir == 2.2){
        moveDown();
        moveRight();
    }else {
        stopMovement();
    }
}