%----------------------------------------------------------
% exo1a
% ETUDE DE LA STATIONNARITE ET DE L'ERGODISME
%----------------------------------------------------------

N=500;     % nombre de points par réalisations
K=200;      % nombre total de réalisations 

%----------------------------------------------------------
% SINUSOIDE + BRUIT
% création d'un bruit avec K réalisations, chacune sur N valeurs 
b = randn(K,N);
% création d'une K sinusoïdes, chacune sur N points
S = ones(K,1)*sin(2*pi*[1:N]/N);
% signal + bruité
X=3*b + 3*S;

%----------------------------------------------------------
% AFFICHAGE PAR LA FONCTION "plot-rea"
M=4; % Nombre de réalisations affichées
% ce sont les réalisations dont les indices sont contenues 
% dans le vecteur k
k=[1:M+1]; % (ici les M premières), 
%ou tout autre vecteur de taille M
plot_rea(X,k,1)

