JC =javac --release 11
.SUFFIXES:.java .class
.java.class:
	$(JC) $./*.java


default:
	@javac -d . Classes/*.java
	@javac -d . Controladors/*.java
	@javac -d . Drivers/*.java

	@jar cfe ../EXE/DriverCtrlDomini.jar Drivers.DriverCtrlDomini Drivers/DriverCtrlDomini.class Controladors/*.class Classes/*.class
	@jar cfe ../EXE/DriverCtrlDirectori.jar Drivers.DriverCtrlDirectori Drivers/DriverCtrlDirectori.class Controladors/*.class Classes/*.class
	@jar cfe ../EXE/DriverCtrlExpressio.jar Drivers.DriverCtrlExpressio Drivers/DriverCtrlExpressio.class Controladors/*.class Classes/*.class

	@javac -sourcepath . -classpath "lib/*" Test/*.java -d .
	@jar cfe ../EXE/Tests/TestCtrlDirectori.jar Test.TestCtrlDirectori Classes/*.class
	@jar cfe ../EXE/Tests/TestCtrlExpressio.jar Test.TestCtrlExpressio Classes/*.class
	@jar cfe ../EXE/Tests/TestDirectori.jar Test.TestDirectori Classes/*.class
	@jar cfe ../EXE/Tests/TestDocument.jar Test.TestDocument Classes/*.class
	@jar cfe ../EXE/Tests/TestExpressio.jar Test.TestExpressio Classes/*.class

all: default

clean:
	rm Classes/*.class
	rm Controladors/*.class
	rm Drivers/*.class
	rm Test/*.class
	rm ../EXE/*.jar
	rm ../EXE/Test*.jar

DriverCtrlDomini: default
	@java Drivers.DriverCtrlDomini

DriverCtrlExpressio: default
	@java Drivers.DriverCtrlExpressio

DriverCtrlDirectori: default
	@java Drivers.DriverCtrlDirectori

TestCtrlDirectori: default
	@java -jar lib/junit-4.12.jar -cp . --select-class Test.TestCtrlDirectori

TestCtrlExpressio: default
	@java -jar lib/junit-4.12.jar -cp . --select-class Test.TestCtrlExpressio

TestDirectori: default
	@java -jar lib/junit-4.12.jar -cp . --select-class Test.TestDirectori

TestDocument: default
	@java -jar lib/junit-4.12.jar -cp . --select-class Test.TestDocument

TestExpressio: default
	@java -jar lib/junit-4.12.jar -cp . --select-class Test.TestExpressio