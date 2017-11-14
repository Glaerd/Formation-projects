close all

load sig1

%------------------------------------
%TRACE DU SIGNAL ET DE SON SPECTRE
%------------------------------------

N=length(x) ;	% nombre d'échantillons du signal
Fe=8000; 	    % fréquence d'échantillonnage
f0=200;          %fréquence fondamentale du signal
t=(0 :N-1)/Fe ;	    % échelle temporelle 

Xf=(1/N)*fft(x) ;
f=(0 :N-1)*Fe/N ;      % échelle des fréquences calculées par TFD ;

figure(1) ;
subplot(211); plot(t,x);title('Signal original');
subplot(212); plot(f-Fe/2,fftshift(abs(Xf))) ;title('Spectre du signal original')

%-----------------------------------------------------------
%1- ELIMINATION DE LA COMPOSANTE CONTINUE (~ de la moyenne)
%-----------------------------------------------------------

%1.4
L = 100;
h = ones(1,L);                   %réponse impulsionnelle du filtre
tb = (0:length(h)-1)/Fe;
figure(2);
stem(tb,h);title('Réponse impulsionnelle du filtre qui "enlève la moyenne" ')
grid

%1.5
 A = ;
 B = ;
 figure(3);
 freqz(B,A);title('Réponse en fréquence du filtre qui "enlève la moyenne" ')
 
% %1.6
% x0=filter(...);
% Xf0=(1/N)*fft(x0);
% 
% figure(4);
% subplot(211);plot(t,x0);title('Signal sans composante continue');
% subplot(212);plot(f-Fe/2,fftshift(abs(Xf0))) ;title('spectre du signal sans composante continue')
% 
% 
% %-----------------------------------------------------------
% %2- ACCENTUATION D'UNE ZONE "FORMANTIQUE" AUTOUR DE 1000 Hz
% %-----------------------------------------------------------
% %2.1.
% r=...              %module du pole inférieur à 1 mais très proche de 1    
% teta=...           %argument du pole lié à la fréquence de résonnance 
% 
% p1=...             %pôles de H(z)
% p2=...
% 
% %2.2.
% A=poly([p1 p2])
% B=...
% %2.3.
% figure(5); 
% freqz(B,A);title(['Réponse en fréquence du filtre avec résonnance')
% 
% %2.4. réponse impulsionnelle obtenue par filtrage d'une impulsion
% h_A=filter(1,A,[1 zeros(1,300)]);
% th=[0:300]/Fe;
% figure(6); 
% plot(th,h_A);title('Réponse impulsionnelle du filtre avec résonnance')
% 
% 
% 
% %2.5.
% y=...
% Yf=(1/N)*fft(y);
% 
% figure(7);
% .....
% 
% 
% %----------------------------
% % APPLICATION AU SIGNAL AUDIO
% %----------------------------
% [x0,Fe] = wavread('Audio');
% N = length(x0);
% t = ...
% X0 = ...
% f = ...
% figure(8)
% subplot(211),plot(t,x0), title('Signal audio original')
% subplot(212),plot(f-Fe/2,abs(fftshift(X0)))
% 
% fr = ...
% r = ...
% teta = ... 
% p1 = ... 
% p2 = ... 
% A=poly([p1 p2])
% y = ... 
% Yf = ... 
% 
% figure(9);
% subplot(211);plot(t,y);title('Signal audio filtré');
% subplot(212);plot(f-Fe/2,fftshift(abs(Yf))) ;
% 
% soundsc(x0,Fe), 
% pause(1), 
% soundsc(y,Fe)
