// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>
// Arduino communicatie
#include <Wire.h>

byte i2c_rcv;

// Start serial zodat deze in elk ander bestand gebruikt kan worden.
// Start de communicatie tussen de arduino's.
void commsSetup(){
	Serial.begin(9600);
	Wire.begin();
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

		if(incomingData == 200){
			digitalWrite(5, HIGH);
		}
	}
	return incomingData;
}

// Zorgt ervoor dat data vanuit de master arduino naar de slave arduino gestuurd wordt
void toSlaveArduino(int value){
  // Serial.println(value);
  Wire.beginTransmission(9);
	Wire.write(value);
	Wire.endTransmission();
}
