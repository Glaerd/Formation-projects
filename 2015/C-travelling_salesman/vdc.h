/*! \struct node
    \brief structure pour les noeuds du Graphe de Résolution de Problème (GRP)
*/
typedef struct node {
//! estimation de la quantité g
  double estim_g;
//! estimation de la fonction d'évaluation
  double estim_f;
//! chemin sur la carte routière : liste de sommets (villes)
  int *listsom;
//! longueur de ce chemin
  int len;
//! nombre total de villes
  int n;
//! suite de la liste ou pointeur NULL
  struct node * next; 
} node;

/*! \var pnode
    \brief pointeur sur un node
*/
typedef node * pnode; 

pnode AllocNode(int n);
pnode ExtractFirstOpen(pnode* Open);
void PrintSolution(pnode p, double *cost);
pnode DevelopNode(pnode p, double *cost);
pnode Catenate(pnode p, pnode q);
double ComputeH(pnode p, double *arc, int code, int *rest);
void AStar(int n, double *cost, int code);
double tailleGRP(int n);
