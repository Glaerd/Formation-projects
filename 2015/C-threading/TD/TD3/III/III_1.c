#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <signal.h>

int block = 0;

void traitement() {
	printf("Trop tard, le coffre est fermé\n");
	block = 1;
	exit(0);
}

int main() {
	
	char code[] = "1234";
	int ret;
	char entree[5] = {0};
	ret = fork();
	if(ret>0) {
		if(signal(SIGUSR1, traitement) == SIG_ERR) {
			printf("Erreur dans le gestionnaire\n");
			exit(1);
		}
		
		printf("Code : ");
		scanf("%s\n",entree);
		if(strcmp(entree,code) == 0) {
			printf("Bravo : coffre-fort ouvert\n");
			return 0;
		}
		else {
			printf("Code faux : coffre-fort fermé\n");
			return 0;
		
		}
		exit(0);
	}
	else if(ret==0) {
		sleep(3);
		kill(getppid(), SIGUSR1);
		exit(0);
	}
	else exit(1);
}
