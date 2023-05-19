// Hoofdbestand dat met de HMI communiceert
#include <Arduino.h>
// Arduino communicatie
#include <Wire.h>

byte i2c_rcv;

// Start serial zodat deze in elk ander bestand gebruikt kan worden.
// Start de communicatie tussen de arduino's.
int recieved = 0;
bool hasConection = false;
int incomingData = 0;

//voeg een recieve event toe voor de wire  (Door Jason Joshua)
void receiveEvent(int bytes){
  recieved = Wire.read();
}

void commsSetup(){
  Serial.begin(9600);
  Wire.begin(9);
  Wire.onReceive(receiveEvent);
}

// Print een status- en waardetekst naar de Serial
// status -> een code die kan worden geïnterpreteerd door de HMI-applicatie
void toJava(int status){
  // Print de status naar serial
	Serial.println(status);
}

int fromJava() {
	if(Serial.available() > 0) {
		incomingData = Serial.readString().toInt();

		switch (incomingData){
			case 50:
				toJava(200);
        		hasConection = true;
				break;
			case 500:
				// Zet een LED aan als nood
				// digitalWrite(7, HIGH);
				toJava(500);
			default:
				break;
		}
	}

  // Als er geen nieuwe data is return dan
  return incomingData;
}

int getData(){
	fromJava();
	return incomingData;
}


// Zorgt ervoor dat data vanuit de master arduino naar de slave arduino gestuurd wordt
void toSlaveArduino(int value){
  // Serial.println(value);
  Wire.beginTransmission(9);
	Wire.write(value);
	Wire.endTransmission();
}

//return de recieved int van de slave (Door Jason Joshua)
int getFromSlave(){
  return recieved;
}

bool getConection(){
return hasConection;
}
