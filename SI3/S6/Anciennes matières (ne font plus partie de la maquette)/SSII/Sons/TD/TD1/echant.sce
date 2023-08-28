clf(0),scf(0)
[s,fe,b]=wavread('mesange-tete-noire.wav');
//N=length(s)
//se=s(1:N-1)

Te=1/fe
t=[0:length(s)-1]*Te
seq=10*floor(s*100)
//se=s(1:4:length(s)-1)
subplot(2,1,1)
plot2d3(t,s)
subplot(2,1,2)
plot2d3(t,seq)
xgrid
xtitle('wav','t(s)','amplitude')
playsnd(seq,fe)
