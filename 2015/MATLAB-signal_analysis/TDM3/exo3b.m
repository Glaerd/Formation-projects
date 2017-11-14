%--------------------------------------------------------------
% exo3b
% DETECTION DE CIBLES (INTERFACES)
%
% par filtrage adapté
%--------------------------------------------------------------

% création de la réponse impulsionnelle h(n) = s(-n)
    s=s(:); h=flipud(s);
% filtrage
    out = filter(h,1,z); M=length(out);

% Détermination de la position des cibles
    % seuillage à 3*sigma_out (sigma-out : écart-type du bruit en sortie) 
    I=find(abs(out)>3.3*A*sqrt(sum(s.^2)));  
    % extraction des positions
    pos = zeros(1,M); pos(I)=out(I);
    % Attention, comme le filtre est causal, les maxima sont décalés de la
    % longueur de la RI
    pos = pos(length(h):M);

% Observation des résultats
figure(6); 
subplot(411);
plot(z); title('Observation bruitée')
subplot(412);
plot([1:M],out); title('Sortie du filtre adapté')
subplot(413);
plot(pos,'r') ; title('Sortie du filtre adapté (aprés seuillage et décalage)')
subplot(414);
plot(g); title('Comparaison avec la "vraie" position');

