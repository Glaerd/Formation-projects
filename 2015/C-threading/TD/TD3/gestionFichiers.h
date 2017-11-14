#define TAILLEBUF 8191

char * litLigne(int fd);

int ecritLigne(int fd, char *c);

int ecritFichier(FILE *fd, char* ligne);
