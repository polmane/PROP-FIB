JC =javac --release 11
.SUFFIXES:.java .class
.java.class:
	$(JC) $*.java


default:
	$(JC) -d ../EXE -cp ../ Controladors/*.java
	$(JC) -d ../EXE -cp ../ Drivers/*.java

	jar cfe ../EXE/DriverCtrlDirectori.jar Drivers.DriverCtrlDirectori ../EXE/FONTS/Drivers/DriverCtrlDirectori.class ../EXE/FONTS/Controladors/*.class ../EXE/FONTS/Classes/*.class
	jar cfe ../EXE/DriverCtrlExpressio.jar Drivers.DriverCtrlExpressio ../EXE/FONTS/Drivers/DriverCtrlExpressio.class ../EXE/FONTS/Controladors/*.class ../EXE/FONTS/Classes/*.class

	$(JC) -cp ../:lib/* Test/*.java -d ../EXE

all: default

clean:
	rm ../EXE/FONTS/Classes/*.class
	rm ../EXE/FONTS/Controladors/*.class
	rm ../EXE/FONTS/Drivers/*.class
	rm ../EXE/FONTS/Test/*.class
	rm ../EXE/*.jar
	rm ../EXE/FONTS/Test/*.jar

DriverCtrlExpressio: default
	java -cp ../EXE FONTS/Drivers/DriverCtrlExpressio

DriverCtrlDirectori: default
	java -cp ../EXE FONTS/Drivers/DriverCtrlDirectori

TestCtrlDirectori: default
	java -cp ../EXE:lib/* org.junit.runner.JUnitCore FONTS.Test.TestCtrlDirectori

TestCtrlExpressio: default
	java -cp ../EXE:lib/* org.junit.runner.JUnitCore FONTS.Test.TestCtrlExpressio

TestDirectori: default
	java -cp ../EXE:lib/* org.junit.runner.JUnitCore FONTS.Test.TestDirectori

TestDocument: default
	java -cp ../EXE:lib/* org.junit.runner.JUnitCore FONTS.Test.TestDocument

TestExpressio: default
	java -cp ../EXE:lib/* org.junit.runner.JUnitCore FONTS.Test.TestExpressio