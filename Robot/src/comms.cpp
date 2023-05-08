// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>
// Arduino communicatie
#include <Wire.h>

int x = 0;

// Leest de waarde van de data van de tweede arduino.
void receiveEvent(int bytes){
  x = Wire.read();
}

// Start serial zodat deze in elk ander bestand gebruikt kan worden.
// Start de communicatie tussen de arduino's.
void commsSetup(){
	Serial.begin(9600);
	Wire.begin(9);
  	Wire.onReceive(receiveEvent);
}

// Zorgt ervoor dat data vanuit de master arduino naar de slave arduino gestuurd wordt
void toSlaveArduino(int value){
  	Wire.beginTransmission(9);
	Wire.write(value);
	Wire.endTransmission();
}

// Print een status- en waardetekst naar de Serial
// status -> een code die kan worden geïnterpreteerd door de HMI-applicatie
void toJava(int status){
  	// Print de status naar serial
	Serial.println(status);
}

// Haalt status codes uit de Serial.
int fromJava() {
	int incomingData = 0;
	if(Serial.available() > 0) {
		incomingData = Serial.readString().toInt();

		switch (incomingData){
			case 50:
				// Zet een LED aan als debug
				digitalWrite(5, HIGH);

				toJava(200);
				break;
			case 500:
				// Zet een LED aan als nood
				digitalWrite(7, HIGH);
				toJava(500);
			default:
				break;
		}
	}

	return incomingData;
}