#include <netinet/in.h>
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <string.h>

int main(int argc, char const *argv[]) {

  if(argc != 2) {
    printf("too many/few arguments, argc=2\n");
    return -1;
  }

int pid = getpid();

  int s = socket(AF_INET, SOCK_DGRAM, 0);
  struct sockaddr_in server;
  server.sin_family=AF_INET;
  int port = atoi(argv[1]);
  server.sin_port=htons(port);
  server.sin_addr.s_addr=INADDR_ANY;
  memset(server.sin_zero, 0, 8);


  int res = bind(s,(struct sockaddr*)&server , sizeof(server));
  if(res==-1) perror("bind socket");

  char msg[50], msg2[50], msg3[50];
  sprintf(msg3, "%d", pid);

  struct sockaddr_in client;

  int taille = sizeof(client);
  int recv_msg = recvfrom(s, msg, sizeof(msg), 0, (struct sockaddr*)&client, &taille);
  if(recv_msg==-1) perror("recvfrom");
  int recv_msg2 = recvfrom(s, msg2, sizeof(msg2), 0, (struct sockaddr*)&client, &taille);
  if(recv_msg2==-1) perror("recvfrom2");

  printf("Message du client : %s\nPID client : %s\n",msg,msg2);

  sendto(s, msg, sizeof(msg), 0, (struct sockaddr*)&client, taille);
  sendto(s, msg3, sizeof(msg3), 0, (struct sockaddr*)&client, taille);


  return 0;
}
