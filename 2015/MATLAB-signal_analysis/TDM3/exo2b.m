%--------------------------------------------------------------
% exo2b
% IDENTIFICATION D'UN SYSTEME
%--------------------------------------------------------------

N=1000;
% signal d'entrée : bruit blanc uniforme, centrée
x=rand(1,N)-0.5;
% signal filtré
a=0.8;
y=filter(1,[1 -a],x);


%------------------------------------------------------
% Identification du système par intercorrélation
%------------------------------------------------------
% calcul de la RI en filtrant une impulsion de Dirac
h=filter(1,[1 -a],[1 zeros(1,N-1)]);

% calcul de l'intercorrélation Ryx en filtrant un bruit blanc
L = 30;
[Ryx,tau_yx] = xcorr(y,x,L,'unbiased');  %inter-corrélation de y avec x

% comparaison des deux méthodes 
figure(6);
tau=[0:L-1];
plot(tau,Ryx(L+1:2*L)/max(Ryx),tau,h(tau+1));title('Comparaison de h(vert) et Ryx(bleu)'), grid


% %--------------------------------------------------------------------
% % ON CONSIDERE UN BRUIT D'OBSERVATION : un bruit vient se superposer à la
% % sortie du filtre
% %--------------------------------------------------------------------
% % bruit blanc Gaussien additif
% b=randn(1,N);   
% % signal filtré avec bruit d'observation et inter-corrélation
% yb = y + b;
% L = 30;
% [Rybx,tau_yx] = xcorr(yb,x,L,'unbiased');  %inter-corrélation de y avec x
% 
% % RI avec bruit d'observation
% hb = h + b;
% 
% %--------------------------------------------------------------------
% % COMPARAISON DES RESULTATS
% %--------------------------------------------------------------------
% figure(7);
% tau=[0:L-1];
% plot(tau,Rybx(L+1:2*L)/max(Rybx),tau,h(tau+1),tau,hb(tau+1)), grid,
% title('Identification de la RI avec bruit d''observation')
% xlabel('h(vert) , Rybx(bleu) , hb(rouge)')
