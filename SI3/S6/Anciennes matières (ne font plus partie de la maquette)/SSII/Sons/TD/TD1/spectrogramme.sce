clf(0),scf(0)
file='noot5.wav';
[y,fe,B]=wavread(file); // charger fichier wave
subplot(2,1,1)
plot([0:length(y)-1]/fe,y) // dessin du chronogramme
xgrid
xtitle(["Chronogramme du signal ",file])
xlabel('temps (seconde)')
ylabel('amplitude')
//playsnd(y,fe) // joue le son
D=1024; //taille de la fen^etre
hamming=window('hm',D);// fen^etre de Hamming

subplot(2,1,2)
// calcul du spectre
spectre=[];
N=fix(length(y)/D)-1; //nbre de fen^etres
for k= 1:N
debut=1+(k-1)*D;
spe=1+abs(fft(y(debut:debut+D-1).*hamming));
spectre=[spectre;20*log10(spe(1:D/2)/D)];
end
fr=[0:D/2-1]*fe/D;
t=(0:N-1)*D/fe;
xset("colormap", hotcolormap(128)); //graycolormap(128)); 
colorbar(min(spectre),0)
grayplot(t',fr',spectre)
xtitle(["Spectrogramme, fen^etre de taille: ",string(D)])
xlabel('temps (s)')
ylabel('fre ́quence (Hz)')
