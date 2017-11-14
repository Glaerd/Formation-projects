#define DBL_MAX_VAL 1.79769e+308

#include "vdc.h"
#include <stdio.h>
#include <stdlib.h>

/* ====================================================================== */
/*! \fn pnode AllocNode(int n)
    \param n : nombre de villes
    \return pointeur sur le noeud alloué
    \brief alloue un nouveau noeud
*/
pnode AllocNode(int n) 
{
	pnode res = (pnode)malloc(sizeof(node));
	res->n = n;
	return res;
}

/* ====================================================================== */
/*! \fn pnode ExtractFirstOpen(pnode & Open)
    \param Open : liste des noeuds "ouverts"
    \return pointeur sur le noeud choisi
    \brief sélectionne un noeud qui minimise la quantité estim_f, le retire de la liste Open et retourne un pointeur dessus
*/
pnode ExtractFirstOpen(pnode* Open) 
{
    pnode val;
    if(*Open == NULL)	val = NULL;
    else if((*Open)->next == NULL) 
    {
    	val = *Open;
 	*Open = NULL;
    }
    else 
    {
	val = *Open;
	pnode* temp = Open;
	pnode* temp2 = NULL;
	pnode* val2 = NULL;
	double min_estim_f = (*temp)->estim_f;
	while((*temp) != NULL) 
	{
	    temp2 = temp;
	    temp = &((*temp)->next);
	    if((*temp) == NULL) break;
	    if((*temp)->estim_f < min_estim_f) 
	    {
		min_estim_f = (*temp)->estim_f;
		val2 = temp2;
		val = *temp;
	    }
	}
	if (val2 != NULL) 
	{
	    (*val2)->next = val->next;
	}
	else 
	{
	    *Open = (*Open)->next;
	}
    }
    if (val != NULL) val->next = NULL;
    return val;
}

/* ====================================================================== */
/*! \fn void PrintSolution(pnode p, double *arc)
    \param p : pointeur sur le noeud sélectionné à la profondeur n-1
    \param arc : table des distances entre villes
    \brief affiche la solution à l'écran (le circuit et son coût)
*/
void PrintSolution(pnode p, double *arc)
{
    printf("\nSolution finale :\n\n");
}

/* ====================================================================== */
/*! \fn int NotInListSom(int s, pnode p)
    \param p : un noeud
    \brief teste si s est absent de la liste de sommets du noeud p
*/

/* ====================================================================== */
/*! \fn pnode DevelopNode(pnode p, double *arc)
    \param p : un noeud
    \param arc : table des distances entre villes
    \return la liste des nouveaux noeuds créés
    \brief construit la liste des noeuds successeurs sur noeud p dans le GRP
*/
pnode DevelopNode(pnode p, double *arc)
{   
    
    return ListeNoeuds;
}

/* ====================================================================== */
/*! \fn pnode Catenate(pnode p, pnode q)
    \param p, q : listes de noeuds
    \return pointeur sur la liste résultat
    \brief concatène les listes p et q (physiquement), et retourne le pointeur sur la nouvelle liste
*/
pnode Catenate(pnode p, pnode q)
{
    if(p == NULL) return q;
    else 
    {
    	pnode tmp = p;
    	while(tmp->next != NULL) tmp = tmp->next;
    	tmp->next = q;
    	return p;
    }
}

/* ====================================================================== */
/*! \fn double ComputeH(pnode p, double *arc, int code, int *rest)
    \param p : un noeud
    \param arc : table des distances entre villes
    \param code : le code de l'heuristique (choix parmi différentes possibilités)
    \param rest : tableau contenant les index des p->n - p->len villes à visiter
    \return : valeur de l'heuristique pour ce noeud
    \brief calcule l'heuristique pour le noeud p
*/
double ComputeH(pnode p, double *arc, int code, int *rest)
{
    
    return h;
}

/* ====================================================================== */
/*! \fn void AStar(int n, double *arc, int code)
    \param n : nombre de villes
    \param arc : table des distances entre villes
    \param code : le code de l'heuristique (choix parmi différentes possibilités)
    \brief algorithme A* pour le voyageur de commerce
*/
void AStar(int n, double *arc, int code)
{
   //declaration des listes OUVERT et FERME
    int f;
    pnode d = AllocNode(n);
    d->estim_g = 0.0;
    d->listsom = (int*)malloc(n*sizeof(int));
    for(f=0; f<n; f++)
    {
	d->listsom[f] = -1;
    }
    d->estim_f = d->estim_g + ComputeH(d, arc, code);
    d->next = NULL;
    d->parent = NULL;
    pnode p = NULL;
    pnode OUVERT = d;
    pnode FERME = NULL;
    pnode del = NULL;
    pnode temp_del = del;
    pnode keep = NULL;
    pnode temp_keep = keep;
    while(OUVERT != NULL)
    {
        p = ExtractFirstOpen(&OUVERT);
	FERME = Catenate(FERME, p);
	if(p->len == n) break;
	pnode llist = DevelopNode(p, arc, code);
	if(OUVERT == NULL) OUVERT = Catenate(OUVERT,llist);
	else 
	{
	    pnode temp_llist = llist;
	    keep = NULL;
	    temp_keep = keep;
	    while(temp_llist != NULL) 
	    {
	        pnode j = FERME;
	        pnode i = OUVERT;
	        int l = 0;
	        int k = 0;
	        while(j != NULL && i != NULL) 
		{
		    while(j != NULL) 
		    {
		        if(j->len != temp_llist->len) j = j->next;
		        else 
			{
			    for(f=0; f<n; f++) 
			    {
			        if(j->listsom[f] == temp_llist->listsom[f]) l = 1;
			        else 
				{
				    l = 0;
				    break;
				}
			    }
			    if(l == 1) j = NULL;
			    else j = j->next;
			}
		    }
		    if (l == 1)
		    {
			if (del == NULL) 
			{
			    del = temp_llist;
			    temp_del = del;
			}
			else 
			{
			    temp_del->next = temp_llist;
			    temp_del = temp_del->next;
			}
			break;
		    }
		    while(i != NULL) 
		    {
			if(i->len != temp_llist->len) i = i->next; 
			else 
			{
			    for(f=0; f<n; f++) 
			    {
				if(i->listsom[f] == temp_llist->listsom[f]) k = 1;
				else 
				{
				    k = 0;
				    break;
				}
			    }
			    if(k == 1) i = NULL;
			    else i = i->next;
			}
		    }
		    if (k == 1)
		    {
			if (del == NULL) 
			{
			    del = temp_llist;
			    temp_del = del;
			}
			else 
			{
			    temp_del->next = temp_llist;
			    temp_del = temp_del->next;
			}
			break;
		    }  
		    if(l == 0 && k == 0) 
		    {
		        if (keep == NULL) 
			{
			    keep = temp_llist;
			    temp_keep = keep;
			}
			else 
			{
			    temp_keep->next = temp_llist;
			    temp_keep = temp_keep->next;
			}
			break;
		    }   
		}
		temp_llist = temp_llist->next;
	    }
	    if(temp_keep != NULL) temp_keep->next = NULL;
	    if(temp_del != NULL) temp_del->next = NULL;
	    OUVERT = Catenate(OUVERT,keep);
	}
    }
    pnode tmp = d;
    int l = 0, k = 0;
    printf("\n");
    while( tmp != NULL)
    {
        printf("Noeud n° %d :\n",l);
        printf("\t\t - estim_g = %.2f et estim_f = %.2f\n",tmp->estim_g,tmp->estim_f);
        printf("\t\t - listsom : [ ");
        for(k=0; k < n; k++)
	{
    	    printf("%d ", tmp->listsom[k]);
    	}
    	printf("]\n");
    	printf("\t\t - len = %d\n",tmp->len);
    	l++;
    	tmp = tmp->next;
    }
    printf("\n\n");
    Matrix_print(cost, n);
    printf("\nTaille du graphe de recherche pour %d villes : %llu\n", n, tailleGRP(n));
    PrintSolution(p,cost,code);
    PrintEval(OUVERT, FERME, tailleGRP(n));
    FreeNode(&OUVERT);
    FreeNode(&FERME);
    FreeNode(&del);
}

/* ====================================================================== */
/*! \fn double tailleGRP(int n)
    \param n : nombre de villes
    \return : taille du GRP pour n villes
    \brief estime la taille du GRP pour n villes
*/
double tailleGRP(int n)
{
    int i=0;
    int tailleTotale=0;       
    int temp;               
    for(i=0;i<=n;i++){
	temp = ( fact(n) / fact(n-i) )*( fact(n) / fact(n-i) ) / fact(i);		
	tailleTotale = tailleTotale+temp;
    }
    return tailleTotale;
}

/* ====================================================================== */
int main(int argc, char **argv)
/* ====================================================================== */
{
	
	if (argc != 2) 
    {
	printf("Attendu -> ./heur1 N\n");
	printf("avec N = nb de postes = nb d'agents\n");
	return 0;
    }
    else 
    {
	//int N = 3;
	int N = strtol(argv[1], NULL, 10);	
	int i;
	
	pnode d = AllocNode(N);
	d->estim_g = 0.0;
	d->n = N;
	d->estim_f = d->estim_g + ComputeH(d, C, 1);
	
	d->next = DevelopNode(d, C);
	
	return 0;
    }

} // main()
