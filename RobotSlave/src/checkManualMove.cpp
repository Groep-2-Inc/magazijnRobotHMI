#include <motorController.h>
#include <slaveCurPositionController.h>
#include <IRSensor.h>
#include <comms.h>

bool manualMoveZas = false;

//een functie die een boolean teruggeeft in de situatie op de y-as dat de robot de z-as mag bewegen 
//gebruikt de huidige y-as positie en de array met de y-waarde van het midden van elk vakje (met functie uit motorController) (Joëlle)
bool checkManualMoveYas(){
    if((readY() > (returnYCor(0) - 80) && (readY() < returnYCor(0) + 200)) || (readY() > (returnYCor(1) - 80) && (readY() < returnYCor(1) + 200)) || (readY() > (returnYCor(2) - 80) && readY() < (returnYCor(2) + 200)) || (readY() > (returnYCor(3) - 80) && readY() < (returnYCor(3) + 200)) || (readY() > (returnYCor(4) - 80) && readY() > (returnYCor(4)+ 200))){
        return true;
    }else{
        return false;
    }
}

unsigned long previousMillis = 0;

void checkManualMoveZas(){
    if(millis() - previousMillis > 10){
        Serial.println("11111111111111111111");
        if(measureZas() < 4.20){
            Serial.println("22222222222222222222");
            sendData(31);
            manualMoveZas  = true;
        }else{
            Serial.println("33333333333333333");
            sendData(32);
            manualMoveZas = false;
        }
        previousMillis = millis();

    }

}

bool checkManualMoveBox(){
    if(((readY() > (returnYCor(0) - 80)) && readY() < (returnYCor(0) + 300)) || ((readY() > (returnYCor(1) - 80)) && readY() < (returnYCor(1) + 300)) || ((readY() > (returnYCor(2) - 80)) && readY() < (returnYCor(2) + 300)) || ((readY() > (returnYCor(3) - 80)) && readY() < (returnYCor(3) + 300)) || ((readY() > (returnYCor(4) - 80)) && readY() < (returnYCor(4) + 300))){
        return true;
        
    }else{
        return false;
    }
}
bool getManualMoveZas(){
    return manualMoveZas;
}