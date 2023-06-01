// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>

// joyX - Definieert de pinmode van de joystick voor de data voor de x-as.
// joyY - Definieert de pinmode van de joystick voor de data voor de y-as.
// swPin - Definieert de pinmode van de switch knop van de joystick.
// xDirection - Slaat de waarde van de x-as op.
// yDirection - Slaat de waarde van de y-as op.
// swState - Slaat de status van de switch op.
// zAs - Slaat op of de robot de z-as beweegt.
const int joyX = A3;
const int joyY = A2;
const int swPin = 6;
int xDirection = 0;
int yDirection = 0;
bool swState;

bool zAs = false;

// Zet de pinmode voor joystick.
void joystickSetup(){
	pinMode(joyY, INPUT);
	pinMode(joyX, INPUT);
	pinMode(swPin, INPUT_PULLUP);
}

// Bekijkt of de joystick is ingedrukt en geeft deze lezing een debounce mee.
unsigned long lastPressed = 0;
bool checkJoystickButton(){
	// Leest de waarde van de switch uit.
	bool ingedrukt = digitalRead(swPin); 
	if(!ingedrukt){
		if(millis() - lastPressed > 300){
			lastPressed = millis();
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
// Naar voren 0.0.1, naar achter 0.0.2
// Dit kan gecombineerd worden voor diagonale 1.1.0, 1.2.0, 2.1, 2.2.0
String readJoystick() {
	// Leest de assen en knop uit
	xDirection = analogRead(joyX);
	yDirection = analogRead(joyY);
	swState = checkJoystickButton();

	// Zet de standaard direction
	String direction = "0.0.0";

	// Zet standaard waarde voor de return var
	String horizontal = "0";
	String vertical = "0";
	String depth = "0";

	// Als de knop is ingedrukt
	if(swState == 1){
		// Als zAs true is; dus de knop is een keer ingedrukt
		if(zAs){
			// Zet zAs op false
			zAs = false;
		}else{
			// Anders zet hem op 
			zAs = true;
		}
	}

	if(!zAs){
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
	}else{
		// Als waarde onder de 450 komt
		if (yDirection < 450){
			// Zet vertical op 1
			depth = "1";
		}else if (yDirection > 650){
			// Anders als groter dan 650
			// Zet veritcal op 2
			depth = "2";
		}
	}

	// Voegt de horizontal en vertical samen zodat het een float kan worden
	String data =  horizontal + "." + vertical + "." + depth;

	// Serial.println(data);

	return data;
}