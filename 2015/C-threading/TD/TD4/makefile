# TDm4 Makefile

CC = gcc -lpthread
OBJ = main.o
CC_OPTIONS = -g -Wall

all : exe

exe : $(OBJ)
	$(CC) $(CC_OPTIONS) $(OBJ) -o exe

main.o : main.c gestionFichiers.c gestionFichiers.h
	$(CC) $(CC_OPTIONS) -c main.c

clean :
	rm *.o
