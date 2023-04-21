#include <Arduino.h>

const int joyX = A5;
const int joyY = A4;
const int swPin = 5;
int xDirection = 0;
int yDirection = 0;
int swState = 0;

unsigned long lastPress = 0;
bool checkButton(){
  bool pressed = digitalRead(swPin);
  if (pressed){
    if (millis() - lastPress > 250){
      lastPress = millis();
      return true;
    }
  }
  return false;
}


float readJoystick() {
  xDirection = analogRead(joyX);
  yDirection = analogRead(joyY);
  swState = checkButton();
  String horizontal = "0";
  String vertical = "0";

  if (xDirection > 600){
    horizontal = "1";
  }else if (xDirection < 400){
    horizontal = "2";
  }

  if (yDirection < 450){
    vertical = "1";
  }else if (yDirection > 650){
    vertical = "2";
  }

  String direction = horizontal + "." + vertical;
  float returnValue = direction.toFloat();
  // Serial.println(direction);
  return returnValue;
}