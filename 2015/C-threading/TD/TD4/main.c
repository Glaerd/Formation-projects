#define _GNU_SOURCE
#include <unistd.h>
#include <fcntl.h>
#include "gestionFichiers.c"
#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>

void* th1(void *arg);
void* th2(void *arg);

char *c;
sem_t vide,plein;

int main(){
	pthread_t thread1, thread2;

	sem_init(&vide,0,1);
	sem_init(&plein,0,0);
	
	printf("Création des threads. \n");

	pthread_create(&thread1,NULL,th1,NULL);
	pthread_create(&thread2,NULL,th2,NULL);
	
	pthread_join(thread1,NULL);
	pthread_join(thread2,NULL);

	
	exit(0);

}

void* th1(void *arg){
	int fd1;
	printf("Ouverture fichier source \n");
	fd1 = open("Source.txt", O_RDONLY);

	while(1){
		//j'attend que ce soit vide		
		sem_wait(&vide);
		
		//je rempli avec une ligne
		printf("Lecture ligne. \n");		
		c = litLigne(fd1);

		//j'indique que c'est plein
		sem_post(&plein);

		//si c'est j'arrive plus a lire de ligne, je sors
		if(c == '\0') break;
	}	
	close(fd1);
	exit(0);
}

void* th2(void *arg){
	int fd2;

	printf("Ouverture fichier destination \n");
	fd2 = open("Destination.txt", O_WRONLY);
	
	while(1){
	//j'attend que ce soit plein
	sem_wait(&plein);
	//si y a une ligne
	if(c !='\0'){
		// je l'ecris
		printf("Ecriture ligne. \n");
		ecritLigne(fd2, c);
		//je libere la ligne
		free(c);
		}
	//sinon je sors
	else break;
		
	//j'indique que c'est vide
	sem_post(&vide);

	}


	close(fd2);
	exit(0);
}




