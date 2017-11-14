%----------------------------------------------------------
% exo1b
% ETUDE D'UN BRUIT BLANC ET TRACE D'HISTOGRAMME
%----------------------------------------------------------

% BRUITS BLANC SUR N POINTS
N=1000;

X1 = randn(1,N);
plot_sighisto(X1,30,2)

X2 = rand(1,N)-0.5;
plot_sighisto(X2,30,3)


% COMPARAISON DES AUTO-CORRELATIONS
[Rxx1,tau1] = xcorr(X1,N/2,'unbiased');
[Rxx2,tau2] = xcorr(X2,N/2,'unbiased');

figure(4),
subplot(2,1,1),plot(tau1,Rxx1),grid
title('Auto-corrélations')
subplot(2,1,2),plot(tau2,Rxx2),grid
