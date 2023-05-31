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

// Zet de pinmode van de bovenstaande variabelen en zorgt ervoor dat de data van de master arduino ontvangen wordt.
void setup() {
	statusLightsSetup();
	motorSetup();
	encoderSetup();
	IRSensorSetup();
	manualOrAutoButtonsSetup();
	commsSetup();
}

void statusLightsOn(){
	if(recivedValue == 21){
		emergencyLEDOn();
	} else if(recivedValue == 22){
		manualLEDOn();
	} else if(recivedValue == 23){
		autoLEDOn();
	}
}

void loop() {
	recivedValue = getRecivedValue();

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

	statusLightsOn();

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

	//delay voor het zorgen dat de arduinos meer gelijk lopen.
	delay(10);
}