clf(0),scf(0)
t=[0:1:64]/8000
s = 0.75*cos(2*%pi*500*t) + 0.75*cos(2*%pi*250*t) + 0.75*cos(2*%pi*1000*t)
tf=[0:1:64]*8000/64
subplot(2,1,1)
plot2d3(tf,abs(fft(s)))
xgrid
xtitle('cos 250Hz + 500Hz + 1000Hz,fe=8000Hz,N=64','f(Hz)','fft')


t=[0:1:64]/8000
s=0.75*cos(2*%pi*250*t)
tf = [0:1:64]*8000/64
subplot(2,1,2)
plot2d3(tf,abs(fft(s)))
xgrid
xtitle('cos 250Hz, fe=8000Hz,N=64','f(Hz)','fft')
