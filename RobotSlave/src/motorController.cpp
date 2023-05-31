#include <Arduino.h>
#include <slaveCurPositionController.h>
#include <IRSensor.h>
#include <comms.h>

//ints voor de pins en de global speed
const int directionPinY = 12;
const int pwmPinY = 3;
const int brakePinY = 9;
int globalSpeed = 255;

const int directionPinZ = 13;
const int pwmPinZ = 11;
const int brakePinZ = 8;

//array met de y coordinaten aan de hand van de value die de encoder teruggeeft (door Jason Joshua)
const int yCor[5]{150, 1200, 2250, 3300, 4350};

//int om bij te houden wat de huidige y is, en 2 bools. wordt gebruikt bij het oppakken van de producten (door Jason Joshua)
int curY = 0;
bool hasProduct = false;
bool productPicked = false;

//motor setup om te zorgen dat alle pins juist gedefinieerd worden.
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

//pak een product op (door Jason Joshua)
void pickUpProduct(){
  if(!productPicked){
    //als cury nog niet gedefinieerd is stel deze gelijk aan de huidige y (door Jason Joshua)
    if (curY == 0){
    	curY = readY();
    }

    //als je het product nog niet hebt, naar voren en dan een stukje omhoog. (door Jason Joshua)
    if(!hasProduct){
		if(measureZas() < 8.76){
			stopMovement();
			moveForward();
		}else if (readY() < curY + 300){
			moveUp();
		}else{
			stopMovement();
			hasProduct = true;
		}
    } else {
		if(measureZas() > 4.22){
			moveBackward();
		}else if (readY() > curY){
			moveDown();
		}else{
			stopMovement();
			hasProduct = false;
			productPicked = true;
		}
    }
  } else {
    stopMovement();
  }
}

//return de productpicked bool (door Jason Joshua)
bool getProductPicked(){
  return productPicked;
}

//als je nog niet bewogen hebt, beweeg naar een bepaalde coordinaat (door Jason Joshua)
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

//reset de hasmoved bool (door Jason Joshua) (alvast voor het reseten van het bewegen om te zorgen dat de robot weer kan bewegen)
void resetHasMoved(){
	hasMoved = false;
}

//return de hasmovde functie (door Jason Joshua)
bool getHasMoved(){
	return hasMoved;
}

void mcReset(){
	hasMoved = false;
	hasProduct = false;
	productPicked = false;
}