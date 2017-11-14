#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#include "id.h"

int currentProcessID(char* text, int i) {

	printf("Processus %d, i = %d - %s\n", getpid(), i, text);
}

int parentProcessID(char* text, int i) {

	printf("Processus avec père %d, i = %d - %s\n", getppid(), i, text);

}

int currentAndParentProcessID(char* text, int i) {

	printf("Processus %d avec père %d, i = %d - %s\n", getpid(), getppid(), i, text);

}
