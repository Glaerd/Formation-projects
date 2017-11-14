#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include "gestionFichiers.h"
#include "id.h"

int main() {

	int fileDesc;
	fileDesc = open("affichage-processus.txt",O_RDONLY);
	char* text = readFirstLine(fileDesc);
	int i, n = 1;
	pid_t pid;
	printf("\n");
	for(i = 0; i < n; i++) {
		pid = fork();
		if(pid == -1) {
			break;
		}
		if(pid == 0) {
			currentProcessID(text, i);
			parentProcessID(text, i);
			currentAndParentProcessID(text, i);
			break;	
		}else{
			wait(NULL);
		}
	}
	return 0;
}
