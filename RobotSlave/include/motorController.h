#include <Arduino.h>

void motorSetup();
void moveUp();
void moveDown();
void stopMovement();
void moveForward();
void moveBackward();
void moveY(int);
void resetHasMoved();
bool getHasMoved();