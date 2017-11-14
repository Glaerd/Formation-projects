#include <stdio.h>


int main(){
const int n=9;
int tab[]={0,2,3,4,6,8,9,12,13};
int v=15;

int i=0, j=n-1, c=0 ;
while(i!=n && j !=-1){
if(tab[i]+tab[j]==v){
printf("%d %d\n",i,j);
i++;
j--;
c++;
}
else if(tab[i]+tab[j]<v)
i++;
else
j--;
}
printf("%d\n",c);
return 0;
}
