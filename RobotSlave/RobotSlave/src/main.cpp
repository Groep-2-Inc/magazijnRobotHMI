#include <Arduino.h>
#include <Wire.h>

int globalSpeed = 255;
const int directionPinZ = 13;
const int pwmPinZ = 11;
const int brakePinZ = 8;

int x = 0;

void receiveEvent(int bytes){
  x = Wire.read();
}

void setup() {
  pinMode(directionPinZ, OUTPUT);
  pinMode(pwmPinZ, OUTPUT);
  pinMode(brakePinZ, OUTPUT);
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
  Serial.begin(9600);
}



void moveForward(){
  digitalWrite(directionPinZ, LOW);
  digitalWrite(brakePinZ, LOW);
  analogWrite(pwmPinZ, globalSpeed);
}

void moveBackward(){
  digitalWrite(directionPinZ, HIGH);
  digitalWrite(brakePinZ, LOW);
  analogWrite(pwmPinZ, globalSpeed);
}

void stopMovement(){
  digitalWrite(brakePinZ, HIGH);
  analogWrite(pwmPinZ, 0);
}

void loop() {
  // Serial.println(x);
  if (x == 0){
    stopMovement();
  }else if (x == 1){
    moveForward();
  }else if (x == 2){
    moveBackward();
  }
}