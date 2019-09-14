JAVAC=/usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR) $<

CLASSES=PowerBSTApp.class PowerAVLApp.class
CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/*.class
	rm -Rf doc

doc:
	javadoc -d$(DOCDIR) $(SRCDIR)/*.java

run:
	java -cp $(BINDIR) PowerBSTApp

runBST:
	java -cp $(BINDIR) PowerBSTApp

runAVL:
	java -cp $(BINDIR) PowerAVLApp
