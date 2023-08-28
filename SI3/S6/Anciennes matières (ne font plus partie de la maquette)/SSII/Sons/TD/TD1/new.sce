N=64
Te=1/800
t=[0:0.5:N-1]*Te
s=0.5*cos(2*%pi*200*t)
plot2d(t,s)
xgrid
playsnd(s,1/Te)
