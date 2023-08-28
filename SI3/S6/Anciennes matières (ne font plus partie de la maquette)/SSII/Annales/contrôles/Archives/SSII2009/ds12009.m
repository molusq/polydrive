fe=8000; %Hz;
f0=440;
a0= .5;
t=[0:1/fe:5/f0];
s= a0*cos(2*pi*f0*t);
stem(t,s)
title('Chronogramme')
xlabel('temps (s)')
ylabel(['s(1)', num2str((s(0)))
figure(2)
N = 1024
t=[0:1/fe:5];
s= a0*cos(2*pi*f0*t);
fk= [0:4*N-1]*fe/(4*N);
stem(fk,abs(fft(s(1:N),4*N)))
grid
xlabel('fréquence (Hz)')
ylabel('spectre d''amplitude')
title(['largeur de la fenêtre:', num2str(N),' points'])


