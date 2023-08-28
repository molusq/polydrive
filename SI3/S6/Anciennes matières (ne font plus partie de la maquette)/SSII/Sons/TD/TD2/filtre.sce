clf(0),scf(0)

[s,fe,b] = wavread('NOOTNOOT.wav');
Te = 1/fe
t = [0:length(s)-1]*Te
tf = [0:length(s)-1]*fe/length(s)

function filtre(x, a)
    se = s(1:x:length(s)-1)
    f=fe/x
    tf = [0:x:length(s)-1]*f/length(s)
    N=length(se); // on suppose N divisible par 4
    m=[ones(1:N/4-2),0.9,0.5,0.1,zeros(1:N/2-2),0.1,0.5,0.9,ones(1:N/4-2)];
    s2 = ifft( fft(se).*m);
    subplot(8,1,a)
    plot2d3(tf,abs(fft(s2)))
    xgrid
    xtitle('File,','f(Hz)','fft')
    playsnd(s2,f)
endfunction

subplot(811)
plot2d3(tf,abs(fft(s)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(s,fe)

filtre(2, 2);
filtre(4, 3);
filtre(8, 4);
