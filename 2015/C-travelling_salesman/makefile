all: vdc

vdc: vdc.o
	gcc vdc.o -o vdc -Wall
	
vdc.o: vdc.c vdc.h
	gcc -c vdc.c -Wall
	
clean:
	rm *.o
