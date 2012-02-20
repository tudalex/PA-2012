#
#	Please, send _any_ bug reports or questions to: adrian.sc@rosedu.org
#
# Standard variables to automatize the build process
JCOMP=javac
JFLAGS=-cp bin/
DEBUG=-g

LIB_SOURCES=$(wildcard src/*.java)
LIB_OBJECTS=$(addprefix bin/,$(notdir $(LIB_SOURCES:.java=.class)))

# You can edit the following lines if you want, or you can specify a source on call
#LAB=1
#TASK=1
ifdef LAB
ifdef TASK
SRC=Lab$(LAB)p$(TASK)
endif
endif

# These two targets should always execute
.PHONY: all clean

# This rule compiles the file named $(SRC) (passed as an argument) to an executable
# of the same name and places it in /bin 
ifdef LAB
ifdef TASK
all: $(LIB_OBJECTS) src-lab$(LAB)/$(SRC).java
	#This will compile the class for the current laboratory
	$(JCOMP) $(JFLAGS) $(DEBUG) -d bin/ src-lab$(LAB)/$(SRC).java
else
all: $(LIB_OBJECTS)
	@echo " "
	@echo "To compile a source file, specify a LAB and a TASK number"
	@echo "For example, typing:  "
	@echo "    make LAB=1 TASK=1 "
	@echo "will compile a file called Lab1p1.java to a class called \"Lab1p1.class\"."
	@echo " "
endif
else
all: $(LIB_OBJECTS)
	@echo " "
	@echo "To compile a source file, specify a LAB and a TASK number"
	@echo "For example, typing:  "
	@echo "    make LAB=1 TASK=1 "
	@echo "will compile a file called Lab1p1.java to an executable called \"Lab1p1.class\"."
	@echo " "
endif

# This rule compiles all the files in src/ to corresponding objects in bin/
bin/%.class: src/%.java
	#This will compile the classes for the library sources
	$(JCOMP) $(JFLAGS) $(DEBUG) -d bin/ src/$*.java

# This rule forces doxygen to recompile documentation files
docs:
	rm -rf doc/html
	rm -rf doc/latex
	doxygen doc/PA_codebase_manual

# This rule will clean all files in the current project
clean:
	rm -rf bin/*.class

