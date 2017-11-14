#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>

#include "gestionFichiers.h"

#define R 0
#define W 1

int main() {

	int fileDesc;
	int fd[2];
	int n_octets;
	char* text;
	char* ligne;
	FILE *fp = fopen("Destination.txt", "w+");
	fileDesc = open("source.txt",R);

	text = NULL;

	pipe(fd);

	if(fork()==0){
		close(fd[W]);	
		do {
			ligne = litLigne(fd[R]);
			if(ligne != NULL) {
				printf("Lecture de %d octets : %s", strlen(ligne), ligne);
				ecritFichier(fp,ligne);
			}
		} while( ligne != NULL );
		fclose(fp);
		close(fd[R]);
	}
	else {
		close(fd[R]);
		do {
			if(text != NULL) free(text);
			text = litLigne(fileDesc);
			if(text != NULL) ecritLigne(fd[W],text);
		} while(text !=NULL);
		close(fd[W]);
	}
	wait(100);
	return 0;

}
