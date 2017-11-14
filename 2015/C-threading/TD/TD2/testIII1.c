#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main() {
	
	int i, n = 1;
	pid_t pid;
	for(i = 0; i < n; i++) {
		pid = fork();
		if(pid == -1) {
			break;
		}
		if(pid == 0) {
			printf("Fils ");
			execl("./testIB1", NULL);
			break;	
		}else{
			printf("Père ");
			execl("./testIB1", NULL);
			while(wait(NULL) >= 0);
		}
	}
	return 0;
}
