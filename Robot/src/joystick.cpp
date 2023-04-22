#include <Arduino.h>

const int joyX = A5;
const int joyY = A4;
const int swPin = 5;
int xDirection = 0;
int yDirection = 0;
int swState = 0;

// Zet de pinmode voor joystick
void joystickSetup(){
	pinMode(joyY, INPUT);
	pinMode(joyX, INPUT);
	pinMode(swPin, INPUT_PULLUP);
}

// Controleert of de joyStick button is ingedrukt
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

// Leest de joystick uit
// joystick is 90 graden gedraaid, de assen op de joystick komen dus niet overeen met de assen in de software!
// Deze functie returnt waardes als een float, 
// 		Dit omdat we op basis van het cijfer voor de komma de links en naar rechts kunnen bepalen
//		Dit omdat we op basis van het cijfer achter de komma de naar boven en onder kunnen bepalen.
// Naar links zou dus zijn 1.0, rechts 2.0
// Boven 0.1, onder 0.2
// Dit kan gecombineerd worden voor diagonale 1.1, 1.2, 2.1, 2.2
float readJoystick() {
	// Leest de assen en knop uit
	xDirection = analogRead(joyX);
	yDirection = analogRead(joyY);
	swState = checkButton();

	// Zet standaard waarde voor de return var
	String horizontal = "0";
	String vertical = "0";

	// Als de waarde boven de 600 komt
	if (xDirection > 600){
		// Zet horizontal op 1
		horizontal = "1";
	}else if (xDirection < 400){
		// Anders als kleinder is dan 400 
		// Zet horizontal op 2
		horizontal = "2";
	}

	// Als waarde onder de 450 komt
	if (yDirection < 450){
		// Zet vertical op 1
		vertical = "1";
	}else if (yDirection > 650){
		// Anders als groter dan 650
		// Zet veritcal op 2
		vertical = "2";
	}

	// Voegt de horizontal en vertical samen zodat het een float kan worden
	String direction = horizontal + "." + vertical;
	// Zet de string om naar een float
	float returnValue = direction.toFloat();

	return returnValue;
}