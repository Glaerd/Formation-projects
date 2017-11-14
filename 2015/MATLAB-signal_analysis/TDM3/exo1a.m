%----------------------------------------------------------
% exo1a
% ETUDE DE LA STATIONNARITE ET DE L'ERGODISME
%----------------------------------------------------------

N=500;     % nombre de points par r�alisations
K=200;      % nombre total de r�alisations 

%----------------------------------------------------------
% SINUSOIDE + BRUIT
% cr�ation d'un bruit avec K r�alisations, chacune sur N valeurs 
b = randn(K,N);
% cr�ation d'une K sinuso�des, chacune sur N points
S = ones(K,1)*sin(2*pi*[1:N]/N);
% signal + bruit�
X=3*b + 3*S;

%----------------------------------------------------------
% AFFICHAGE PAR LA FONCTION "plot-rea"
M=4; % Nombre de r�alisations affich�es
% ce sont les r�alisations dont les indices sont contenues 
% dans le vecteur k
k=[1:M+1]; % (ici les M premi�res), 
%ou tout autre vecteur de taille M
plot_rea(X,k,1)

