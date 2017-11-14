#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "gestionFichiers.h"

#define TAILLEBUF 1000

char* readFirstLine(int fileDesc)
{
	int i = 0;
	int j,last;
	char* line;
	line = malloc(TAILLEBUF*sizeof(char));
	while((last=read(fileDesc,&line[i],1)) > 0 && line[i]!='\n' && i<=TAILLEBUF) 
	{
		i++;
	}
	if(last <= 0) printf("Erreur de read\n");
	if(line[i]=='\n') printf("Fin de ligne\n");
	if(i>TAILLEBUF) printf("Fin de recherche\n");

	return line;
}
