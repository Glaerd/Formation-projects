all: gestionFichiers.o tube.o

tube: tube.o
	gcc tube.o gestionFichiers.o -o tube

tube.o: tube.c gestionFichiers.h
	gcc -c tube.c

gestionFichiers.o: gestionFichiers.c gestionFichiers.h
	gcc -c gestionFichiers.c
