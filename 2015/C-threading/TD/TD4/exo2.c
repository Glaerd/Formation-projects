#define _GNU_SOURCE
#include <unistd.h>
#include <fcntl.h>
#include "gestionFichiers.c"
#include <semaphore.h>
#include <pthread.h>
#include <stdio.h>

void* th1(void *arg);
void* th2(void *arg);
void* th3(void *arg);

sem_t sem1,sem2,sem3;

int main(){
	pthread_t thread1,thread2,thread3;

	sem_init(&sem1,0,1);
	sem_init(&sem2,0,0);
	sem_init(&sem3,0,0);
	
	printf("Création des threads. \n");

	pthread_create(&thread1,NULL,th1,NULL);
	pthread_create(&thread2,NULL,th2,NULL);
	pthread_create(&thread3,NULL,th3,NULL);
	
	pthread_join(thread1,NULL);
	pthread_join(thread2,NULL);
	pthread_join(thread3,NULL);

	
	exit(0);

}

void* th1(void *arg){
	int j,i=1;
	for(j = 0; j < 10; j++){
		sem_wait(&sem1);
		printf("Affichage %d du thread %d\n", j+1, i); // Thread Pi avec i = 1, 2, 3
		sem_post(&sem2);
	}
}

void* th2(void *arg){
	int j,i=2;
	for(j = 0; j < 10; j++){
		sem_wait(&sem2);
		printf("Affichage %d du thread %d\n", j+1, i); // Thread Pi avec i = 1, 2, 3
		sem_post(&sem3);
	}
}

void* th3(void *arg){
	int j,i=3;
	for(j = 0; j < 10; j++){
		sem_wait(&sem3);
		printf("Affichage %d du thread %d\n", j+1, i); // Thread Pi avec i = 1, 2, 3
		sem_post(&sem1);
	}
}
