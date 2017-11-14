%--------------------------------------------------------------
% exo3a
% DETECTION DE CIBLES (INTERFACES)
%
% par inter-corr�lation
%--------------------------------------------------------------
close all

% Signal �mis
t = [1:100]; 
s = 1*sin(2*pi*0.01*(1+0.1*t).*t);
Rss=xcorr(s); Ls=length(Rss); % calcul de l'autocorr�lation du signal �mis

% Position "pr�d�finie" de cibles diff�rentes
Lg=1000;
g=zeros(1,Lg); g(250)=1; g(300)=1; g(500)=2;g(550)=1; g(700)=0.5;

% Signal re�u sans bruit additif (y) et avec bruit additif (z)
y = conv(s,g);
A = 0.5; %�cart-type du bruit
z = y + A*randn(1,length(y));

% D�tection des cibles par inter-corr�lation
% cas non bruit�
Rys=xcorr(y,s); Lys=length(Rys);
% cas bruit�
Rzs=xcorr(z,s); Lzs=length(Rzs); 

% Observation des signaux
figure(1); 
subplot(211),plot(s); title(' Signal �mis'); xlabel('signal temporel')
subplot(212),plot([-(Ls-1)/2:Ls/2],Rss); grid,xlabel('Autocorr�lation')
figure(2); plot(y); title('Observation non bruit�e')
figure(3);  plot([Lg:Lys]-Lg,Rys(Lg:Lys)); title('Inter-corr�lation (cas non bruit�)')
figure(4); plot(z); title('Observation bruit�e')
figure(5); plot([Lg:Lzs]-Lg,Rzs(Lg:Lzs)); title('Inter-corr�lation (cas bruit�)')
%