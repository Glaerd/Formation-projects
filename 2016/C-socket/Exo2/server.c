#include <netinet/in.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <string.h>


int main(int argc, char const *argv[]) {

  int TAILLEATTENDUE = 1000;

  if(argc != 2) {
    printf("too many/few arguments, argc=2\n");
    return -1;
  }
	
  int s = socket(AF_INET, SOCK_STREAM, 0);
  struct sockaddr_in server;
  server.sin_family=AF_INET;
  int port = atoi(argv[1]);
  server.sin_port=htons(port);
  server.sin_addr.s_addr=INADDR_ANY;
  memset(server.sin_zero, 0, 8);


  int res = bind(s,(struct sockaddr*)&server , sizeof(server));
  if(res==-1) perror("bind socket");
  
  int l = listen(s, 5);
  if(l==-1) perror("listen");

  char msg[1000];
  struct sockaddr_in client;

  int taille = sizeof(client);
  int s_serv = accept(s, (struct sockaddr*)&client, &taille);
  if(s_serv==-1) perror("accept");
  int n = 0, recu = 0;
  n = read(s_serv,&msg[recu],TAILLEATTENDUE - recu);
  if(n==-1) perror("read");
  
  printf("n° port de l'emet=%d\n", ntohs(client.sin_port));
  printf("msg recu=%s\n", msg);


  return 0;
}
