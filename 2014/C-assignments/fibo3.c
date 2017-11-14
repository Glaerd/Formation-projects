#include <stdio.h>

int fib2(int n){
if(n==0) return 0;
if (n==1) return 1;
return fib2(n-2) + fib2(n-1);
}

int main() {
int n=39;
printf("n=");


printf("Fib(%d)=%d\n",n,fib2(n));

return 0;	
}
