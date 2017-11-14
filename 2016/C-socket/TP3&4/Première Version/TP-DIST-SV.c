/* T. Grandpierre - Application distribue'e pour TP IF4-DIST 2004-2005

But :

fournir un squelette d'application capable de recevoir des messages en
mode non bloquant provenant de sites connus. L'objectif est de fournir
une base pour implementer les horloges logique/vectorielle/scalaire, ou
bien pour implementer l'algorithme d'exclusion mutuelle distribue'

Syntaxe :
         arg 1 : Numero du 1er port
     arg 2 et suivant : nom de chaque machine

--------------------------------
Exemple pour 3 site :

Dans 3 shells lances sur 3 machines executer la meme application:

pc5201a>./dist 5000 pc5201a.esiee.fr pc5201b.esiee.fr pc5201c.esiee.fr
pc5201b>./dist 5000 pc5201a.esiee.fr pc5201b.esiee.fr pc5201c.esiee.fr
pc5201c>./dist 5000 pc5201a.esiee.fr pc5201b.esiee.fr pc5201c.esiee.fr

pc5201a commence par attendre que les autres applications (sur autres
sites) soient lance's

Chaque autre site (pc5201b, pc5201c) attend que le 1er site de la
liste (pc5201a) envoi un message indiquant que tous les sites sont lance's


Chaque Site passe ensuite en attente de connexion non bloquante (connect)
sur son port d'ecoute (respectivement 5000, 5001, 5002).
On fournit ensuite un exemple permettant
1) d'accepter la connexion
2) lire le message envoye' sur cette socket
3) il est alors possible de renvoyer un message a l'envoyeur ou autre si
necessaire

*/

#include <fcntl.h>
#include <netdb.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>
#include <errno.h>
#include <limits.h>
#include <inttypes.h>
#include <math.h>
#include <float.h>


int GetSitePos(int Nbsites, char* argv[], int index);
void WaitSync(int socket);
void SendSync(char* site, int Port);
int inSect, demandeSect;

typedef struct { double order; int machine; } demand;

typedef struct {
	demand dliste[3];
	int accord[3];
} sect_crit;

sect_crit sect;

void entre_SC();
void sort_SC(int sitePos);

void demand_SC(int nbMachines, int machine, char* argv[], int s_ecoute) {

	int j;
	for(j = 0; j < nbMachines; j++) {

		if(j != machine) {
			struct sockaddr_in sock_add;
			int client;
			int size_sock_add;
			int l;
			struct hostent* hp;
			hp = gethostbyname(argv[j + 2]);
			if(hp == NULL) {
				perror("client");
			}

			size_sock_add       = sizeof(struct sockaddr_in);
			sock_add.sin_family = AF_INET;
			sock_add.sin_port   = htons(atoi(argv[1]) + j);
			memcpy(&sock_add.sin_addr.s_addr, hp->h_addr, hp->h_length);

			if((client = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
				perror("Creation socket");
				exit(-1);
			}

			if(connect(client, (struct sockaddr*)&sock_add, size_sock_add) == -1) {
				perror("Probleme connect");
			} else {
				double order = sect.dliste[machine].order;
				l = write(client, &machine, sizeof(int));
				l = write(client, &order, sizeof(double));
				if(l == -1)
					perror("Write Error in demand_SC");
				close(client);
			}
		}
	}
}

void checkAccords(int s_ecoute,int argc,int sitePos, int NSites){

	if(demandeSect == 1) {
			int s_service;
			struct sockaddr_in sock_add_dist;
			socklen_t size_sock;
			size_sock = sizeof(struct sockaddr_in);
			int j;
			for(j = 0; j < argc - 3; j++) {
				while(1){
					int ac;
					double orderAc;
					s_service = accept(s_ecoute, (struct sockaddr*)&sock_add_dist, &size_sock);
					if(s_service > 0) {
						read(s_service, &ac, sizeof(int));
						read(s_service, &orderAc, sizeof(double));
						sect.accord[ac] = ac;
						sect.dliste[ac].order = orderAc;
						fflush(0);
						close(s_service);
						break;
					}
				}
			}
			double temp = DBL_MAX;
			for(j = 0; j < NSites; j++) {
				if(sect.dliste[j].order < temp){
					temp = sect.dliste[j].order;
				}
			}
			if(sect.dliste[sitePos].order == temp) {
				entre_SC(sect);
				demandeSect = 0;
			}
		}

}

void accepteDemand(int s_ecoute, struct sockaddr_in sock_add, struct sockaddr_in sock_add_dist, socklen_t size_sock, char* argv[], int sitePos){

	if(inSect == 0) {
			double ord;
			int mach;
			int s_service = accept(s_ecoute, (struct sockaddr*)&sock_add_dist, &size_sock);
			if(s_service > 0) {
				int l;
				l = read(s_service, &mach, sizeof(int));
				l = read(s_service, &ord, sizeof(double));
				fflush(0);
				close(s_service);

				sect.dliste[mach].order   = ord;
				sect.dliste[mach].machine = mach;


				struct sockaddr_in sock_add;
				int client;
				int size_sock_add;
				struct hostent* hp;
				hp = gethostbyname(argv[mach + 2]);
				if(hp == NULL) {
					perror("client");
				}

				size_sock_add       = sizeof(struct sockaddr_in);
				sock_add.sin_family = AF_INET;
				sock_add.sin_port   = htons(atoi(argv[1]) + mach);
				memcpy(&sock_add.sin_addr.s_addr, hp->h_addr, hp->h_length);

				if((client = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
					perror("Creation socket");
					exit(-1);
				}

				if(connect(client, (struct sockaddr*)&sock_add, size_sock_add) == -1) {
					perror("Probleme connect");
				} else {
					int accord = sitePos;
					double orderAccord = sect.dliste[sitePos].order;
					l = write(client, &accord, sizeof(int));
					l = write(client, &orderAccord, sizeof(double));
					if(l == -1)
						perror("Write Error in demand_SC");
					close(client);
				}
				fflush(0); /* pour montrer que le serveur est actif*/
			}
		}
		else{
			double ord;
			int mach;
				int s_service = accept(s_ecoute, (struct sockaddr*)&sock_add_dist, &size_sock);
				if(s_service > 0) {
					int l;
					l = read(s_service, &mach, sizeof(int));
					l = read(s_service, &ord, sizeof(double));
					fflush(0);
					close(s_service);

					sect.dliste[mach].order   = ord;
					sect.dliste[mach].machine = mach;


					struct sockaddr_in sock_add;
					int client;
					int size_sock_add;
					struct hostent* hp;
					hp = gethostbyname(argv[mach + 2]);
					if(hp == NULL) {
						perror("client");
					}

					size_sock_add       = sizeof(struct sockaddr_in);
					sock_add.sin_family = AF_INET;
					sock_add.sin_port   = htons(atoi(argv[1]) + mach);
					memcpy(&sock_add.sin_addr.s_addr, hp->h_addr, hp->h_length);

					if((client = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
						perror("Creation socket");
						exit(-1);
					}

					if(connect(client, (struct sockaddr*)&sock_add, size_sock_add) == -1) {
						perror("Probleme connect");
					} else {
						int accord = sitePos;
						double orderAccord = 0;
						l = write(client, &accord, sizeof(int));
						l = write(client, &orderAccord, sizeof(double));
						if(l == -1)
							perror("Write Error in demand_SC");
						close(client);
					}
					fflush(0); /* pour montrer que le serveur est actif*/
				}
		}
}

void entre_SC() {
	demandeSect = 0;
	inSect = 1;
}

void sort_SC(int sitePos) {
	inSect = 0;
	sect.dliste[sitePos].order = DBL_MAX;
}

/*Identification de ma position dans la liste */
int GetSitePos(int NbSites, char* argv[], int baseIndex) {
	char MySiteName[20];
	int MySitePos = -1;
	int i;
	gethostname(MySiteName, 20);
	for(i = baseIndex; i < NbSites; i++)
		if(strcmp(MySiteName, argv[i + 2]) == 0) {
			MySitePos = i;
			return MySitePos;
		}
	if(MySitePos == -1) {
		printf("Indice du Site courant non trouve' dans la liste\n");
		exit(-1);
	}
	return (-1);
}

/*Attente bloquante d'un msg de synchro sur la socket donne'e*/
void WaitSync(int s_ecoute) {
	char texte[40];
	int l;
	int s_service;
	struct sockaddr_in sock_add_dist;
	socklen_t size_sock;
	size_sock = sizeof(struct sockaddr_in);
	printf("WaitSync : ");
	fflush(0);
	s_service = accept(s_ecoute, (struct sockaddr*)&sock_add_dist, &size_sock);
	l         = read(s_service, texte, 39);
	texte[l]  = '\0';
	printf("%s\n", texte);
	fflush(0);
	close(s_service);
}

/*Envoie d'un msg de synchro a la machine Site/Port*/
void SendSync(char* Site, int Port) {
	struct hostent* hp;
	int s_emis;
	char chaine[15];
	int longtxt;
	struct sockaddr_in sock_add_emis;
	int size_sock;

	if((s_emis = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		perror("SendSync : Creation socket");
		exit(-1);
	}

	hp = gethostbyname(Site);
	if(hp == NULL) {
		perror("SendSync: Gethostbyname");
		exit(-1);
	}

	size_sock                = sizeof(struct sockaddr_in);
	sock_add_emis.sin_family = AF_INET;
	sock_add_emis.sin_port   = htons(Port);
	memcpy(&sock_add_emis.sin_addr.s_addr, hp->h_addr, hp->h_length);

	if(connect(s_emis, (struct sockaddr*)&sock_add_emis, size_sock) == -1) {
		perror("SendSync : Connect");
		exit(-1);
	}

	sprintf(chaine, "**SYNCHRO**");
	longtxt = strlen(chaine);
	/*Emission d'un message de synchro*/
	int l;
	l = write(s_emis, chaine, longtxt);
	if(l == -1)
		perror("Write Error");
	close(s_emis);
}

/***********************************************************************/
/***********************************************************************/
/***********************************************************************/
/***********************************************************************/

int main(int argc, char* argv[]) {
	struct sockaddr_in sock_add, sock_add_dist;
	socklen_t size_sock;
	size_sock = sizeof(struct sockaddr_in);
	int s_ecoute;
	int i, l;
	double t;
	inSect = 0;

	int PortBase = -1; /*Numero du port de la socket a` creer*/
	int NSites   = -1; /*Nb total de sites*/

	srand(time(NULL));

	if(argc < 3) {
		printf("Erreur: il faut donner au moins 2 sites pour faire fonctionner l'application: "
		       "NumeroPortBase et liste_des_sites\n");
		exit(-1);
	}

	/*----Nombre de sites (adresses de machines)---- */
	NSites = argc - 2;

	int sitePos = GetSitePos(NSites, argv, 0);

	/*CREATION&BINDING DE LA SOCKET DE CE SITE*/
	PortBase = atoi(argv[1]) + sitePos;
	printf("Numero de port de ce site %d\n", PortBase);


	sock_add.sin_family      = AF_INET;
	sock_add.sin_addr.s_addr = htons(INADDR_ANY);
	sock_add.sin_port        = htons(PortBase);

	if((s_ecoute = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		perror("Creation socket");
		exit(-1);
	}

	while(bind(s_ecoute, (struct sockaddr*)&sock_add, sizeof(struct sockaddr_in)) == -1) {
		if(errno != 98) {
			perror("Bind socket");
			exit(-1);
		}
		sitePos = GetSitePos(NSites, argv, sitePos + 1);
		PortBase          = atoi(argv[1]) + sitePos;
		printf("Numero de port de ce site %d\n", PortBase);
		sock_add.sin_port = htons(PortBase);
	}

	sect.dliste[sitePos].machine = sitePos;
	for(i = 0; i < NSites; i++) {
		sect.dliste[i].order = DBL_MAX;
	}

	listen(s_ecoute, 30);
	/*----La socket est maintenant cre'e'e, binde'e et listen----*/

	if(sitePos == 0) {
		/*Le site 0 attend une connexion de chaque site : */
		for(i = 0; i < NSites - 1; i++)
			WaitSync(s_ecoute);
		printf("Toutes les synchros recues \n");
		fflush(0);
		/*et envoie un msg a chaque autre site pour les synchroniser */
		for(i = 0; i < NSites - 1; i++)
			SendSync(argv[3 + i], atoi(argv[1]) + i + 1);
	} else {
		/* Chaque autre site envoie un message au site0
	   (1er  dans la liste) pour dire qu'il est lance'*/
		SendSync(argv[2], atoi(argv[1]));
		/*et attend un message du Site 0 envoye' quand tous seront lance's*/
		printf("Wait Synchro du Site 0\n");
		fflush(0);
		WaitSync(s_ecoute);
		printf("Synchro recue de  Site 0\n");
		fflush(0);
	}

	/* Passage en mode non bloquant du accept pour tous*/
	/*---------------------------------------*/
	fcntl(s_ecoute, F_SETFL, O_NONBLOCK);

	/* Boucle infini*/

	/* On commence par tester l'arriv�e d'un message */
	/* Petite boucle d'attente : c'est ici que l'on peut faire des choses*/
	demandeSect = 0;
	double            ms0, ms; // Milliseconds
  	time_t          s0, s;  // Seconds
  	double 			t0, t1;
  	struct timespec spec;
  	clock_gettime(CLOCK_REALTIME, &spec);
  	ms0 = round(spec.tv_nsec / 1.0e6)/1000;
  	s0 = spec.tv_sec;
  	t0 = s0+ms0;
  	double clock;
	for(l = 0; l < 1000; l++) {
    	
    	clock_gettime(CLOCK_REALTIME, &spec);
    	s  = spec.tv_sec;
    	ms = round(spec.tv_nsec / 1.0e6)/1000;
    	t1 = s+ms;
    	clock = t1 - t0;
		nanosleep((const struct timespec[]){{0, 50000000L}}, NULL);
		t = rand() % 100;
		if(demandeSect == 0 && inSect == 0) printf("%d -> %fs - 0\n", l, clock);
		else if(demandeSect == 1 && inSect == 0) printf("%d -> %fs - 1\n", l, clock);
		else if(demandeSect == 0 && inSect == 1) printf("%d -> %fs - 2\n", l, clock);
		else printf("X");
		
		
		if(demandeSect == 1) demand_SC(argc - 2, sitePos, argv, s_ecoute);
		else{
			if(t < 1) {
				if(inSect == 0) {
					sect.dliste[sitePos].order = clock;
					demand_SC(argc - 2, sitePos, argv, s_ecoute);
					demandeSect = 1;
				} else if(inSect == 1) {
					sort_SC(sitePos);
				}
			}		
		}

		checkAccords(s_ecoute,argc,sitePos,NSites);
		accepteDemand(s_ecoute,sock_add,sock_add_dist,size_sock,argv,sitePos);
	}

	close(s_ecoute);
	return 0;
}
