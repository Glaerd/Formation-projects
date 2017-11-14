%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%Partie 1
%Trac� du signal et de sa TFD pour 500 �chantillons
Fe = 3000;
f01 = 100;
f02 = 105;

%On prend 500 �chantillons
N = 500;
t = [0:(N-1)]*1/Fe;

x = cos(2*pi*f01*t)+(1/2)*cos(2*pi*f02*t);

X = (1/N)*fft(x,N);
f = [0:N-1]*Fe/N;

figure(1);
plot(t,x);
figure(2);
plot(f-Fe/2,fftshift(abs(X)),'r');
hold on, stem(f-Fe/2,fftshift(abs(X))),grid, hold off;

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% %Partie 2
% %1-On augmente la r�solution fr�quentielle avec du "zero padding"
% % et on trace le nouveau spectre
 N2 = 600;
 
 X2 = 1/N*fft(x,N2);
 f2 = [0:N2-1]*Fe/N2;
 
 figure(3)
 plot(f2-Fe/2,fftshift(abs(X2)),'r');
 hold on, stem(f2-Fe/2,fftshift(abs(X2))),grid, hold off;
% 
% %2-On augmente encore la r�solution fr�quentielle
% % et on trace le nouveau spectre
 N3 = 3000;
% 
 X3 = 1/N*fft(x,N3);
 f3 = [0:N3-1]*Fe/N3;
% 
 figure(4)
 plot(f3-Fe/2,fftshift(abs(X3)),'r');
 hold on, stem(f3-Fe/2,fftshift(abs(X3))),grid, hold off;
% 
% 
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% %Partie 3
% %On augmente l'information : on prend le bon nombre d'�chantillons x(n)
% % On a donc augment� la "dur�e d'observation" (donc la dur�e de la fen�tre
% % de pond�ration)
% % et on trace le nouveau spectre
 N4 = N2;
 t2 = [0:(N4-1)]*1/Fe;
 x2 = cos(2*pi*f01*t2)+(1/2)*cos(2*pi*f02*t2);
 
 X4 = 1/N4*fft(x2,N4);
 f4 = [0:N4-1]*Fe/N4;
 
 figure(5)
 plot(f4-Fe/2,fftshift(abs(X4)),'r');
 hold on, stem(f4-Fe/2,fftshift(abs(X4))),grid, hold off;





