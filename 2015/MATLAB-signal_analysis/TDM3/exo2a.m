%----------------------------------------------------------
% exo2a
% EFFET DU FILTRAGE :
%----------------------------------------------------------
close all
% création de l'entrée sur N points
N=1000;
x=rand(1,N)-0.5;
% filtrage
a=0.8;
y=filter(1,[1 -a],x);

%-----------------------------------
% Comparer les deux signaux
figure(1); 
subplot(211)
plot([1:N],x); title('Signal d''entrée')
subplot(212)
plot([1:N],y); title('Signal de sortie')

%-----------------------------------
% Comparer les histogrammes
plot_sighisto(x,30,2);
plot_sighisto(y,30,3);

%-----------------------------------
% Tracer la TF du signal aléatoire de sortie
figure(4)
plot([1:N]/N,abs(fft(y)));grid, title('TF du signal de sortie'); xlabel('Fréquence réduite')

%------------------------------------------------------
% Comparaison des fonctions de corrélation
L = 30; % calcul des corrélations pour tau compris entre -L*Te et +L*Te (ici Te est normalisée à 1)
[Rxx,tau_x] = xcorr(x,L,'unbiased');  %autocorrélation de x
[Rxy,tau_xy] = xcorr(x,y,L,'unbiased');  %inter-corrélation de x avec y
[Ryx,tau_yx] = xcorr(y,x,L,'unbiased');  %inter-corrélation de y avec x
[Ryy,tau_y]=xcorr(y,L,'unbiased');  %autocorrélation de y

figure(5);
subplot(221)
plot(tau_x,Rxx); title('Rxx'),grid
subplot(222)
plot(tau_xy,Rxy);title('Rxy'),grid
subplot(223)
plot(tau_yx,Ryx);title('Ryx'),grid
subplot(224)
plot(tau_y,Ryy);title('Ryy'),grid
