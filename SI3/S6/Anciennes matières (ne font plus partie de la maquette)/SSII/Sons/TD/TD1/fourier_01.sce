clf(0),scf(0)
[s,fe,b]=wavread('mesange-tete-noire.wav')
t=[0:length(s)-1]/fe
subplot(2,1,1)
plot2d3(t,s)
xgrid
xtitle('Fourier','t(s)','amplitude')

S=fft(s)
tf=[0:length(s)-1]*fe/length(s)
subplot(2,1,2)
plot2d3(tf,abs(S))
xgrid
xtitle('Fourier','f(Hz)','tr. Fourier')

playsnd(s,fe)
