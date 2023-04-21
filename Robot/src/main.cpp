#include <Arduino.h>
#include <comms.h>
#include <emergencyStop.h>
#include <motorController.h>
#include <joystick.h>

const int stopButtton = 7;


void setup() {
  pinMode(stopButtton, INPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(A4, INPUT);
  pinMode(A5, INPUT);
  pinMode(5, INPUT_PULLUP);

  motorSetup();
  Serial.begin(9600);
}

unsigned long startTime = 0; 
void loop() {
  checkStop(stopButtton);
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



  if (millis() - startTime > 500){
    startTime = millis();
    
  }

  fromJava();
}