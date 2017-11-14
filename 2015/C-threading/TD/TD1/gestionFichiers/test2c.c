#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "test2c.h"

#define TAILLEBUF 1000

void readLine(int fileDesc)
{
	int i = 0, j = 0;
	int last;
	char** text;
	char* line;
	printf("1\n");
	text = malloc(TAILLEBUF*sizeof(char*));
	line = malloc(TAILLEBUF*TAILLEBUF*sizeof(char));
	printf("2 - Addr line : %d\n", &line[i]);
	line[0] = '<';
	while(line[i] != '\0')
	{
		while((last=read(fileDesc,&line[i],1)) >= 0 && line[i]!='\n' && i<=TAILLEBUF) 
		{
			i++;
			printf("a", i);
		}
		printf("\n");
		if(line[i] != '\0') last=read(fileDesc,&line[i],1);
	}
	if(last <= 0) printf("last = %d\n",last);
}

