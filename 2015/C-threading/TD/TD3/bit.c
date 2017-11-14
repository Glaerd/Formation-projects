#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "gestionFichiers.c"
#include "gestionFichiers.h"
#define R 0
#define W 1
int main()
{
	int fd;
	int pipeFd[2];
	char * c;
	int i=0;
	if(pipe(pipeFD)==-1)
	{
		fprintf(stderr,"pb pipe\n");
		return 0;
	}
	if(fork()!=0)
	{
		close(pipeFd[R]);
		if(fd = open("source.txt", O_RDOnly)==-1)
		{
			fprintf(stderr,"Erreur source\n");
			return 0;
		}
//
//
//
//
	close(pipeFd[W]);
	close(fd);
	wait(NULL);
	}
	else
	{
		close(pipeFd[W]);
		if(fd = open("Dest.txt",O_WOnly|O_CREAT|O_TRUNC,0700)==-1)
		{
			fprintf(stderr,"Erreur Dest\n");
			return 0;
		}
//
//
//
//
	close(pipeFd[R]);
	close(fd);
	return 0;
	}
}
