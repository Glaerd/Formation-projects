test: main.o gestionFichiers.o test2a.o test2b.o test2c.o
	gcc main.o gestionFichiers.o test2a.o test2b.o test2c.o -o test

main.o: main.c gestionFichiers/gestionFichiers.h gestionFichiers/test2a.h gestionFichiers/test2b.h gestionFichiers/test2c.h
	gcc -c main.c

gestionFichiers.o: gestionFichiers/gestionFichiers.c gestionFichiers/gestionFichiers.h
	gcc -c gestionFichiers/gestionFichiers.c

test2a.o: gestionFichiers/test2a.c gestionFichiers/test2a.h
	gcc -c gestionFichiers/test2a.c

test2b.o: gestionFichiers/test2b.c gestionFichiers/test2b.h
	gcc -c gestionFichiers/test2b.c

test2c.o: gestionFichiers/test2c.c gestionFichiers/test2c.h
	gcc -c gestionFichiers/test2c.c
