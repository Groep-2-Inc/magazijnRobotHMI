#include <Arduino.h>
#include <comms.h>

// Leest de stopknop uit
// stopButton -> pin waar de noodstop op is aangesloten
unsigned long laatstIngedrukt = 0;
bool checkStopButton(int stopButton){
  bool ingedrukt = digitalRead(stopButton); 
  if(ingedrukt){
    if(millis() - laatstIngedrukt > 250){
      laatstIngedrukt = millis();
      return true;
    }
  }
  return false;
}

// Stopt de robot en stuurt melding naar HMI
void stop(){
    // stopt de robot
        // insert hier stop motor code

    // Stuurt melding naar de HMI
    toJava(500, "NOODSTOP INGEDRUKT");
}

// Controleert of de stopknop is ingedrukt
// stopButton -> pin waar de noodstop op is aangesloten
void checkStop(int stopButton){
    if(checkStopButton(stopButton)){
        stop();
    }
}
