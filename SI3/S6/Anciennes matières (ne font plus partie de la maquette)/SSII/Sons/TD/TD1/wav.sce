[s,fe,b]=wavread('FusRoDah.wav');

t=[0:length(s)-1]/fe
plot2d(t,s)
xgrid
xtitle('wav','t(s)','amplitude')
playsnd(s,fe)
