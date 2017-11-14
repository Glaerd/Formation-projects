function PRMATLAB

%Etude du pendule pesant



%Cas 1: Oscillations: om=0.5
figure(1)                               
traj([1;0],100,0.5,0.5)

%Cas 2: Expulsion faible: om=1.25
figure(2)                               
traj([1;0],100,1.25,0.5)

%Cas 3: Expulsion forte: om=2
figure(3)                               
traj([1;0],100,2,-5.5)



%---------------------------------------------------
%Equation différentielle du pendule
%---------------------------------------------------
function dxdt=pendule(~,x,om,l0)
dxdt=[x(2);om^2*x(1)+x(1)*((l0/sqrt((x(1)^2)+1))-1)];

%---------------------------------------------------
%Representation des trajectoires
%---------------------------------------------------
function traj(x0,tf,om,l0)
[t,x]=ode45(@pendule,[0 tf],x0,[],om,l0);

%---------------------------------------------------
%Representation de x en fonction du temps
%---------------------------------------------------
subplot(3,1,1)
plot(t,x(:,1))
title(['om=' num2str(om) '   l0=' num2str(l0) '  ;  x_0=' num2str(x0(1)) '  v_0=' num2str(x0(2)) ])
xlabel('Temps : t')
ylabel('Position sur la tige : x(t)')
grid
axis([0 10 -10 30])
%---------------------------------------------------
%Representation de dx/dt en fonction de x
%---------------------------------------------------
subplot(3,1,2)
plot(x(:,1),x(:,2))
axis([-3 3 -2 2])
xlabel('Position sur la tige : x(t)')
ylabel('Vitesse : v(t)')
grid
%---------------------------------------------------
%Representation de Y en fonction de X -> Trajectoire
%---------------------------------------------------
subplot(3,1,3)
X=x(:,1).*cos(om*t);
Y=x(:,1).*sin(om*t);
plot(X,Y)
grid
if om == 0.5 axis([-1.5 1.5 -1.5 1.5]);
else axis([-20 20 -20 20]);
end
xlabel('Position : X(t)')
ylabel('Position : Y(t)')

