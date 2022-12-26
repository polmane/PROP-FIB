JC =javac --release 11
.SUFFIXES:.java .class
.java.class:
	$(JC) $*.java


default:
	$(JC) -cp ./:../lib/* Presentacio/Vistes/Main.java -d ../EXE
	$(JC) -cp ../EXE:../lib/* Persistencia/Test/*.java -d ../EXE

all: default

clean:
	rm -R ../EXE/Domini
	rm -R ../EXE/Persistencia
	rm -R ../EXE/Presentacio
	rm -R ../EXE/*.jar

Main: default
	java -cp ../EXE:../lib/* Presentacio/Vistes/Main

TestGestorBD: default
	java -cp ../EXE:../lib/* org.junit.runner.JUnitCore Persistencia.Test.TestGestorBD

TestGestorDocument: default
	java -cp ../EXE:../lib/* org.junit.runner.JUnitCore Persistencia.Test.TestGestorDocument

TestGestorExpressions: default
	java -cp ../EXE:../lib/* org.junit.runner.JUnitCore Persistencia.Test.TestGestorExpressions
