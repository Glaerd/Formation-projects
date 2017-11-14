#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

void printLine(char* line) {

	int j;
	for( j = 0 ; line[j] != '\0'; j++) 
	{
		printf("%c",line[j]);
	}
	printf("\n");
}


