clf(0),scf(0)
[s,fe,b] = wavread('NOOTNOOT.wav');
Te = 1/fe
t = [0:length(s)-1]*Te
tf=[0:length(s)-1]*fe/length(s)

subplot(611)
plot2d3(tf,abs(fft(s)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(s,fe)

se = s(1:2:length(s)-1)
tf = [0:2:length(s)-1]*fe/length(s)
subplot(612)
plot2d3(tf,abs(fft(se)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(se,fe/2)

se = s(1:4:length(s)-1)
tf = [0:4:length(s)-1]*fe/length(s)
subplot(613)
plot2d3(tf,abs(fft(se)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(se,fe/4)

se = s(1:8:length(s)-1)
tf = [0:8:length(s)-1]*fe/length(s)
subplot(614)
plot2d3(tf,abs(fft(se)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(se,fe/8)

se = s(1:16:length(s)-1)
tf = [0:16:length(s)-1]*fe/length(s)
subplot(615)
plot2d3(tf,abs(fft(se)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(se,fe/16)

se = s(1:32:length(s)-1)
tf = [0:32:length(s)-1]*fe/length(s)
subplot(616)
plot2d3(tf,abs(fft(se)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(se,fe/32)

se = s(1:64:length(s)-1)
tf = [0:64:length(s)-1]*fe/length(s)
subplot(617)
plot2d3(tf,abs(fft(se)))
xgrid
xtitle('FusRoDah,','f(Hz)','fft')
playsnd(se,fe/64)
