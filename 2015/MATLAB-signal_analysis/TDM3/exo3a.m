%--------------------------------------------------------------
% exo3a
% DETECTION DE CIBLES (INTERFACES)
%
% par inter-corrélation
%--------------------------------------------------------------
close all

% Signal émis
t = [1:100]; 
s = 1*sin(2*pi*0.01*(1+0.1*t).*t);
Rss=xcorr(s); Ls=length(Rss); % calcul de l'autocorrélation du signal émis

% Position "prédéfinie" de cibles différentes
Lg=1000;
g=zeros(1,Lg); g(250)=1; g(300)=1; g(500)=2;g(550)=1; g(700)=0.5;

% Signal reçu sans bruit additif (y) et avec bruit additif (z)
y = conv(s,g);
A = 0.5; %écart-type du bruit
z = y + A*randn(1,length(y));

% Détection des cibles par inter-corrélation
% cas non bruité
Rys=xcorr(y,s); Lys=length(Rys);
% cas bruité
Rzs=xcorr(z,s); Lzs=length(Rzs); 

% Observation des signaux
figure(1); 
subplot(211),plot(s); title(' Signal émis'); xlabel('signal temporel')
subplot(212),plot([-(Ls-1)/2:Ls/2],Rss); grid,xlabel('Autocorrélation')
figure(2); plot(y); title('Observation non bruitée')
figure(3);  plot([Lg:Lys]-Lg,Rys(Lg:Lys)); title('Inter-corrélation (cas non bruité)')
figure(4); plot(z); title('Observation bruitée')
figure(5); plot([Lg:Lzs]-Lg,Rzs(Lg:Lzs)); title('Inter-corrélation (cas bruité)')
%