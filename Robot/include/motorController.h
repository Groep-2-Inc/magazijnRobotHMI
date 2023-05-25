#include <Arduino.h>

void moveUp();
void moveDown();
void moveLeft();
void moveRight();
void stopMovement();
void motorSetup();
void manualControl();
void moveX(int);
bool returnHasMoved();
bool moveToHome();
void resetHasMoved();
void moveY (int);
bool moveXY(int, int);
void resetBoolXY();
int getCorX(int);
int getCorY(int);
bool pickUpProduct();
void resetManualControl();
int returnXCor(int);

