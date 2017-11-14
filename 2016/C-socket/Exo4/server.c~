#include <netinet/in.h>
#include <sys/types.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <string.h>


void whenAccepted(int s, int s_serv, struct sockaddr_in client, FILE *logs, int sw, pid_t pid){
	int TAILLEATTENDUE = 1000;
	char msg[1000];
	int n = 0;
	n = read(s_serv,&msg[0],TAILLEATTENDUE);
	if(n==-1) perror("read");
  	
	printf("n° port de l'emet=%d\n", ntohs(client.sin_port));
	printf("msg recu=%s\n", msg);

	FILE *fp;
	fp=fopen(msg,"r");
	if(fp == NULL) {
		perror("Couldn't open file");
	}

	char data[1000] = "";
	char line[300];
	while (fgets(line, 300, fp) != NULL)  {
		strcat(data, line);
	}
	printf("---------------\n%s---------------\n", data);
	
	char log[100] = "";
	char port[8] = "";
	sprintf(port, "%d", ntohs(client.sin_port));
	char date[50];
	
	time_t t = time(NULL);
	struct tm tm = *localtime(&t);

	sprintf(date, "%d/%d/%d", tm.tm_mday, tm.tm_mon + 1, tm.tm_year + 1900);

	strcat(log,(char*)inet_ntoa(client.sin_addr));
	strcat(log, " - ");
	strcat(log, date);
	strcat(log, " - ");
	strcat(log, msg);
	strcat(log, "\n");
	printf("Log printed is : %s", log);
	int put = fputs(log, logs);
	if(put == -1) perror("fputs error");
	
	char all_logs1[1000] = "";
	if(sw == 2){
		fclose(logs);
		
		FILE *logs3;
		logs3 = fopen("log_file","r+");
		if(logs3 == NULL) perror("Couldn't open file");
		char* log_line1 = NULL;
		size_t len1 = 0;
    	ssize_t read1;
		while ((read1 = getline(&log_line1, &len1, logs3)) != -1) {
			strcat(all_logs1, log_line1);
		}
		strcat(data,"Logs are : \n");
		strcat(data,all_logs1);
		fclose(logs3);
		logs = fopen("log_file","a+");
	}
	else if(sw == 1){
		fclose(logs);
		logs = fopen("log_file","a+");
	}
	
	int w = write(s_serv, data, 1000);
	if(w == -1) printf("write error");
	
	fclose(fp);
}

int main(int argc, char const *argv[]) {

	if(argc != 3) {
		printf("too many/few arguments, argc=3\n");
		return -1;
	}
	
	int s1 = socket(AF_INET, SOCK_STREAM, 0);
	struct sockaddr_in server1;
	server1.sin_family=AF_INET;
	int port1 = atoi(argv[1]);
	server1.sin_port=htons(port1);
  
	server1.sin_addr.s_addr=INADDR_ANY;
	memset(server1.sin_zero, 0, 8);
	
	int s2 = socket(AF_INET, SOCK_STREAM, 0);
	struct sockaddr_in server2;
	server2.sin_family=AF_INET;
	int port2 = atoi(argv[2]);
	server2.sin_port=htons(port2);
  
	server2.sin_addr.s_addr=INADDR_ANY;
	memset(server2.sin_zero, 0, 8);

	int res1 = bind(s1,(struct sockaddr*)&server1 , sizeof(server1));
	if(res1==-1) perror("bind socket");
	
	int res2 = bind(s2,(struct sockaddr*)&server2 , sizeof(server2));
	if(res2==-1) perror("bind socket");

  	FILE *logs;
  	logs = fopen("log_file","w");
  	fclose(logs);
  	logs = fopen("log_file","a+");
  	if(logs == NULL) {
		perror("Couldn't open file");
	}
  	char a;
  	while(1){
  		printf("Press any key to continue... (\'q\' to exit) \n");
  		scanf("%c", &a);
  		if(a == 'q') break;
  		printf("Server On...\n");
  		struct sockaddr_in client;
		socklen_t taille = sizeof(client);
		pid_t child_pid = fork();
        if(child_pid == 0)
        {
        	printf("PID : %d - PPID : %d\n", getpid(),getppid());
        	int l1 = listen(s1, 5);
			if(l1==-1) perror("listen");
			int s_serv1 = accept(s1, (struct sockaddr*)&client, &taille);
			if(s_serv1==-1) perror("accept error");
			whenAccepted(s1, s_serv1, client, logs,1,getpid());
        }
        else{
        	printf("PID : %d - PPID : %d\n", getpid(),getppid());
		    int l2 = listen(s2, 5);
			if(l2==-1) perror("listen");
			int s_serv2 = accept(s2, (struct sockaddr*)&client, &taille);
			if(s_serv2==-1) perror("accept error");
			whenAccepted(s2, s_serv2, client, logs,2, child_pid);
        }
	}
	
	fclose(logs);
	
	printf("\n\n\nAll logs listed :\n");
	
	FILE *logs2;
	char all_logs[1000] = "";
	logs2 = fopen("log_file","r+");
	if(logs2 == NULL) perror("Couldn't open file");
	char* log_line = NULL;
	size_t len = 0;
    ssize_t read;
	while ((read = getline(&log_line, &len, logs2)) != -1) {
		strcat(all_logs, log_line);
	}
	printf("%s\n", all_logs);
	
	fclose(logs2);
	return 0;
}
