#include <motorController.h>
#include <currentPositionController.h>
#include <comms.h>

bool canMoveInBox = true;

//een functie die een boolean teruggeeft in de situatie op de x-as dat de robot de z-as mag bewegen 
//gebruikt de huidige x-as positie en de array met de x-waarde van het midden van elk vakje (met functie uit motorController) (Joëlle)
bool checkManualMove(){
    if((readX() > (returnXCor(0) - 300) && (readX() < returnXCor(0) + 150)) || (readX() > (returnXCor(1) - 300) && (readX() < returnXCor(1) + 150)) || (readX() > (returnXCor(2) - 300) && readX() < (returnXCor(2) + 150)) || (readX() > (returnXCor(3) - 300) && readX() < (returnXCor(3) +150)) || (readX() > (returnXCor(4) - 300) && readX() > (returnXCor(4)+150))){
        return true;
    }else{
        return false;
    }
}
void checkManualMoveZas(){
    if(getFromSlave() == 31){
        canMoveInBox = true;
    }else if(getFromSlave() == 32){
        canMoveInBox = false;
    }
}

bool getCanMoveInBox(){
    checkManualMoveZas();
    return canMoveInBox;
}