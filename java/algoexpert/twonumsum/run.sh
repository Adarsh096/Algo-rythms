#!/bin/bash 
echo "Compiling the source codes ..."
/usr/bin/javac -sourcepath ./src/ -d ./classes/ ./src/*.java
echo "Running the compiled java classes : "
/usr/bin/java -cp classes/ algoexpert.twonumsum.src.TwoNumSum