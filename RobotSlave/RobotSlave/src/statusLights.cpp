//Door Sarah

// Zorgt ervoor dat functies uit andere .cpp bestanden gebruikt kunnen worden in dit bestand.
#include <Arduino.h>

// autoLED - definieert pin van lampje die automatische modus aangeeft
// manualLED - definieert pin van lampje die handmatige modus aangeeft
// emergencyLED - definieert pin van lampje die noodstop aangeeft
const int autoLED = 5;
const int manualLED = 6;
const int emergencyLED = 7;

// Zet de pinmode voor de ledjes
void statusLightsSetup(){
	pinMode(autoLED, OUTPUT);
	pinMode(manualLED, OUTPUT);
	pinMode(emergencyLED, OUTPUT);
}

//Zet het lampje aan die de automatische modus weergeeft
void autoLEDOn(){
    digitalWrite(autoLED, HIGH);
    digitalWrite(manualLED, LOW);
    digitalWrite(emergencyLED, LOW);
}

//Zet het lampje aan die de handmatige modus weergeeft
void manualLEDOn(){
    digitalWrite(autoLED, LOW);
    digitalWrite(manualLED, HIGH);
    digitalWrite(emergencyLED, LOW);
}

//Zet het lampje aan die de noodstop weergeeft
void emergencyLEDOn(){
    digitalWrite(autoLED, LOW);
    digitalWrite(manualLED, LOW);
    digitalWrite(emergencyLED, HIGH);
}
