#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>

int main(){

	int fd[2], ret;
	char req[] = "Requête", req2[] = "Requête traitée";
	char buf[strlen(req)+1], buf2[strlen(req2)+1];
	char q[1];
	pipe(fd);
	ret = fork();
	if(ret > 0) {
		printf("Père : %d\nWriting from Père\n", getpid());
		write(fd[1],req,strlen(req)+1);
		wait(NULL);
		printf("Reading from Père\n");
		close(fd[1]);
		read(fd[0], buf2, strlen(req2)+1);
		close(fd[0]);
		printf("req2 = %s et buf2 = %s\n",req2,buf2);
		if(strcmp(buf2,req2) == 0) {
			printf("Prg done... End of Program...\n");
			exit(0);
		}
		else {
			printf("Père : not done\n");
			exit(1);
		}
	}
	else if(ret == 0) {
		printf("Fils : %d avec père %d\nReading from Fils\n", getpid(), getppid());
		read(fd[0], buf, strlen(req)+1);
		printf("req = %s et buf = %s\n",req,buf);
		sleep(5);		
		if(strcmp(buf,req) == 0) {
			printf("Writing from Fils\n");
			close(fd[0]);
			write(fd[1], req2, strlen(req2)+1);
			close(fd[1]);
			exit(0);
		}
		else {
			printf("Fils : not done\n");
			exit(1);
		}
	}
	else {
		printf("Fork() : not done\n");
		exit(1);
	}
}
