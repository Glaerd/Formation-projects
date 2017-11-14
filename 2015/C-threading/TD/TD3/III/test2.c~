#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <signal.h>

int num = 0;

void traitement() {
	printf("num++\n");
	num++;
}

int main() {

	int ret;
	ret = fork();
	if(ret>0) {
		if(signal(SIGINT, traitement) == SIG_ERR) {
			printf("Erreur dans le gestionnaire\n");
			exit(1);
		}
	
		while(num < 5) {
			printf("a\n");
			pause();
			printf("b\n");
		}
		printf("%d signaux sont arrivés\n", num);
		return 0;
	}
	else if(ret==0) {
		int i;
		for(i = 0; i < 5; i++) {
			sleep(1);
			kill(getppid(), SIGINT);
		}
		return 0;
	}
	else exit(1);
}
