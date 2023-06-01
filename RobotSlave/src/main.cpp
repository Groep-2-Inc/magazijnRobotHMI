// Leest de waarde van de encoder voor de recivedValue-as uit en print dit op de seriele monitor.
#include <Arduino.h>
#include <motorController.h>
#include <slaveCurPositionController.h>
#include <statusLights.h>
#include <IRSensor.h>
#include <manualOrAutoButtons.h>
#include <comms.h>

// globalSpeed - Bepaald de snelheid van de robot.
// directionPinZ -  Definieert de pinmode van de richting van de z-as.
// pwmPinZ - Definieert de pinmode van de motor voor de z-as.
// brakePinZ - Definieert de pinmode van de rem van de z-as.
// recivedValue - Bepaald de waarde van de data van de master arduino.
// int globalSpeed = 255;

bool canMove = true;
int recivedValue = 0;
bool sendProductPicked = false;
bool emergency = false;

// Zet de pinmode van de bovenstaande variabelen en zorgt ervoor dat de data van de master arduino ontvangen wordt.
void setup() {
	statusLightsSetup();
	motorSetup();
	encoderSetup();
	IRSensorSetup();
	manualOrAutoButtonsSetup();
	commsSetup();
}

void loop() {
	recivedValue = getRecivedValue();

	if(!emergency){
		// Als de waarde 0 is
		if (recivedValue == 0){
			// Stop met bewegen
			stopMovement();
			// Als de waarde 1 is
		}else if (recivedValue == 1){
			// Beweeg naar voren
			if(canMove)
			moveForward();
		// Als de waarde 2 is
		}else if (recivedValue == 2){
			// Beweeg naar achter
			if(canMove)
			moveBackward();
		}else if (recivedValue == 3){
			// Beweeg naar boven (door Jason Joshua)
			if(canMove)
			moveUp();
		}else if (recivedValue == 4){
			// Beweeg naar beneden (door Jason Joshua)
			if(canMove)
			moveDown();
		} else if (recivedValue == 5){
			//verander de canmove bool (door Jason Joshua)
			canMove = !canMove;
		} else if (recivedValue == 6 || recivedValue == 7 || recivedValue == 8 || recivedValue == 9 || recivedValue == 10){
			//als je kan bewegen, ga dan naar een specefieke y coordinaat (door Jason Joshua)
			if(getHasMoved() == false && canMove){
			moveY(recivedValue-5);
			}
		} else if (recivedValue == 11){
			//reset de hasmoved (door Jason Joshua)
			resetHasMoved();
		} else if (recivedValue == 12){
			//pak een product op (door Jason Joshua)
			pickUpProduct();
		}
	}
	

	checkBtns();

	measureZas();

	// als hasmoved true is, return een statuscode naar de hoofdarduino, zo niet return dan een andere statuscode  (door Jason Joshua)
	if(getHasMoved()){
		toMasterArduino(101);
	} else {
		toMasterArduino(100);
	}

	// Als het product is gepicked
	// Door Martijn
	if(getProductPicked()){
		// Verstuur code 13 één keer naar de master
		if(!sendProductPicked){
			toMasterArduino(13);
			sendProductPicked = true;
		}
	}

	// Door Daan
	// Als er geen emergency is
	if(!emergency){
		// Als de robot in auto mode staat
		if(isAutoMode()){
			// Doe de auto mode led aan en stuur code 23 naar de master
			autoLEDOn();
			toMasterArduino(23);
		// Als de robot in manual mode staat
		}else {
			// Doe de manual mode led aan en stuur code 22 naar de master
			manualLEDOn();
			toMasterArduino(22);	
		}
	}
	

	// Als de ontvangen waarde vanaf de slave 50 is
	if(recivedValue == 50){
		// Zet emergency op true, zet de emergency led aan en stopt het bewegen van de robot
		emergency = true;
		emergencyLEDOn();
		stopMovement();
	// Anders als de ontvangen waarde vanaf de slave 51 is
	}
	if(recivedValue == 51){
		// Stopt het bewegen van de robot
		emergency = false;
		stopMovement();
	}

	//delay voor het zorgen dat de arduinos meer gelijk lopen.
	delay(10);
}