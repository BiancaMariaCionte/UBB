#include <iostream>
#include "Test.cpp"
int main() {
    //std::cout << "Hello, World!" << std::endl;
    CharacterTest::runTests();
    WizardTest::runTests();
    KnightTest::runTests();
    return 0;
}
