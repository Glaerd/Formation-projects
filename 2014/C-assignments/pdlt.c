int pdlt(int X [], int Y [], int Z [])
{
	int i=0,j=0,k=0;	//Init : i=j=k=0
	while(X[i]!=Y[j] || X[i]!=Z[k] || Y[j]!=Z[k])	//Condition d'arrêt : X[i]=Y[j]=Z[k]
	{	//I(i,j,k)
		if(X[i]<Y[j]) //I(i+1,j,k)
		i++;	//I(i,j,k)
		else if(Y[j]<Z[k]) //I(i,j+1,k)
		j++;	//I(i,j,k)
		else if(Z[k]<X[i]) //I(i,j,k+1)
		k++;	//I(i,j,k)
	}
return X[i];	

}


int main()
{
	int X [8] = {2,10,35,46,46,72,444,500};
	int Y [6] = {5,20,46,72,104,444};
	int Z [9] = {1,2,3,4,5,46,104,444,500};
	int egal = pdlt(X,Y,Z);
	printf("%d\n", egal);
}


