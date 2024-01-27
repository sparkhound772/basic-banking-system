## Description

A Java program with a banking system that can create, store and make various operations on the banks customers and their different types of accounts.

## Requirements

Works at least with OpenJDK 17 but possibly other versions as well.

## Compile and run

Clone and compile:

`git clone https://github.com/sparkhound772/basic-banking-system.git`

`cd basic-banking-system`

`javac -d bin -cp bin src/*`

Run:

`java -cp bin Main`

The program passes the test bench provided by the teacher with 69 different tests - my own tests in the provided Main.java file is significantly more rudimentary. It's something to begin with but could be developed for further exploring the program (the tests are in Swedish however).

## Future improvements

- Improve the test bench.
- Implement exceptions and file handling.
- Make a GUI using Swing of JavaFX.

## Notes

The program was a part of a Java course. Detailed specifications were given for the classes and methods and their various functionalities that they should have. 

The exact method declarations were provided beforehand, but not their bodies except for some version of the following lines in Account.java: 

72, 81-83 for number and language formatting.
18-19, 21, 31-32 for creating unique accounts.
123-124 for formatting date and time.


