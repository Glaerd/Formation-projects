#include <stdio.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char** argv) {

    if(argc != 4) {
        printf("The miracle never happen.\n");
        return 1;
    }
    
    struct hostent *he;
    he = gethostbyname(argv[1]);
    if (!he)
    	return 1;

    printf("Official name is: %s\n", he->h_name);

    int mysock = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in destaddress;
    destaddress.sin_family = AF_INET;
    destaddress.sin_port = htons(atoi(argv[2]));
    
    memcpy(&destaddress.sin_addr.s_addr, he->h_addr,he->h_length);
    printf("Address used is: %s\n", inet_ntoa(destaddress.sin_addr));
    
    int c = connect(mysock,(struct sockaddr*)&destaddress, sizeof(destaddress));
    if(c == -1) perror("connect error");

    char cmd[1000];
	char rec[1000];
    
    sprintf(cmd, "%s",argv[3]);
	printf("Envoi du message : %s\n", cmd);
	fflush(0);
	
	int w = write(mysock, cmd, 1000);
	if(w == -1) printf("write error");
	int r = read(mysock, rec, 1000);
	if(r == -1) printf("read error");

	printf("%s", rec);
    close(mysock);

    return 0;
}
