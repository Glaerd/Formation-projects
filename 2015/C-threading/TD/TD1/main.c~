#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include "gestionFichiers/gestionFichiers.h"
#include "gestionFichiers/test2a.h"
#include "gestionFichiers/test2b.h"
#include "gestionFichiers/test2c.h"

int main(int argc, char *argv[])
{ 
	int fileDesc;
	fileDesc = open(getInput(argc, argv),O_RDONLY);
	printf("Open FD : %d\n", fileDesc);
	if(strcmp(argv[2],"1") == 0) 
	{
		printLine(readFirstLine(fileDesc));
	}
	else 
	{
		readLine(fileDesc);
	}
	return 0;
}
