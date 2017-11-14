%--------------------------------------------------------------
% exo3b
% DETECTION DE CIBLES (INTERFACES)
%
% par filtrage adapt�
%--------------------------------------------------------------

% cr�ation de la r�ponse impulsionnelle h(n) = s(-n)
    s=s(:); h=flipud(s);
% filtrage
    out = filter(h,1,z); M=length(out);

% D�termination de la position des cibles
    % seuillage � 3*sigma_out (sigma-out : �cart-type du bruit en sortie) 
    I=find(abs(out)>3.3*A*sqrt(sum(s.^2)));  
    % extraction des positions
    pos = zeros(1,M); pos(I)=out(I);
    % Attention, comme le filtre est causal, les maxima sont d�cal�s de la
    % longueur de la RI
    pos = pos(length(h):M);

% Observation des r�sultats
figure(6); 
subplot(411);
plot(z); title('Observation bruit�e')
subplot(412);
plot([1:M],out); title('Sortie du filtre adapt�')
subplot(413);
plot(pos,'r') ; title('Sortie du filtre adapt� (apr�s seuillage et d�calage)')
subplot(414);
plot(g); title('Comparaison avec la "vraie" position');

