//rectangle des pièces 
final double W=1400;                      //  largeur du rect
final double H=800;                       //  hauteur du rect
final double tailleDemiPorte=60;          // taille demi porte  
final int tailleDemiPersonne=15;          // taille du rayon de la boule d'une personne 
final int decalage = 50;                  // pour la mise en forme 

// définition des boules
final int nx=4, ny=5;                     //positions des boules dans une matrice 
final int nbBoules=nx*ny;                 // number of balls
double[] r = new double[nbBoules];        // radius of a ball
final double rate=0.6;                    // occupation rate
double[][] x = new double[nbBoules][2];   // position of a ball
double[] masse= new double[nbBoules];     // masse des boules
double[] Ptot = new double[nbBoules];     // total pressure on a ball

// vitesse
final double omega=0.;              // angular velocity
double[][] v = new double[nbBoules][2];   // velocity of a ball

// contact des boules 
double[][] p =new double[nbBoules][nbBoules];       // Lagrange multpliers
double[][][] e = new double[nbBoules][nbBoules][2];    // director vector
double[][] s = new double[nbBoules][nbBoules];      // gap

// murs 
final int nombreMur=35;                       // Nb d'obstacles
double[][][] xo=new double[nombreMur][2][2];  // Geometrie des obstacles
double[] Ro=new double[nombreMur];            // épaisseur des obstacles
double[][] N=new double[nombreMur][2];       // unitary normals to the obstacles
double[] LongueurMur=new double[nombreMur];        // Length of the obstacles

// Zones
final int nbZones=4;
double[][] zone=new double[nbZones][4];    // zones = coordonnées {xmin,ymin,xmax,ymax}
double[][] g_zone=new double[nbZones][2];  // vecteur g de chaque zone

// contacts entre obstacles et boules
double[][][] E = new double[nombreMur][nbBoules][2];// director vector
double[][][] X = new double[nombreMur][nbBoules][2];// projection point
double[][] S = new double[nombreMur][nbBoules];     // gap
double[][] P =new double[nombreMur][nbBoules];      // Lagrange multpliers

boolean[] active = new boolean[nbBoules];

// temps 
double t ; // temps
final double dt=0.05; // delta temps 

// autres valeurs
final double epsilon=1.e-3;           // residu for p and P
final double epsilonX=1.e-7;          // residu for x,v
double rho,Rho;                       // penalization parameter
double epsilonp;                      // relaxation paramter
double minP, maxP;                    // pression maximales et minimales
final double relaxp=0.1;             // relaxation of p
final double CFL=1./32;               // CFL condition

// Some basic functions
double norm(double[] v) {
  return Math.sqrt(v[0]*v[0]+v[1]*v[1]);
}       // norm
double ps(double[] v1, double[] v2) {
  return (v1[0]*v2[0]+v1[1]*v2[1]);
} // scalar product
double wp(double[] v1, double[] v2) {
  return (v1[0]*v2[1]-v1[1]*v2[0]);
} // vectorial product
void rot(double[] X,double theta){
  double x1=Math.cos(theta)*X[0]-Math.sin(theta)*X[1];
  double x2=Math.sin(theta)*X[0]+Math.cos(theta)*X[1];
  X[0]=x1;X[1]=x2;
}
double plus(double a) {
  if (a>0) return a;
  else return 0;
}

double[] eval_g(double[] x){
    for(int k=0;k<nbZones;k++) if ((x[0]>zone[k][0]) && (x[1]>zone[k][1]) 
&& (x[0]<zone[k][2]) && (x[1]<zone[k][3])) return g_zone[k];
    
    return new double[] {0,0};
}

void setup() {
  size(1500, 900);  // must be equal to viewSize
  t=0;
  // Initialisationd des boules à true
  for(int i = 0; i<nbBoules; i++) active[i] = true; 
 
  // penalization parameter
  if (relaxp==0.) rho=CFL/(dt*dt);
  else rho =Math.min(CFL/(dt*dt),1./(2.*relaxp));      // penalization   (dépendance à étuider... le problème est que cela induit une modification du comportement qd on ajoute relaxp)     
  epsilonp=relaxp/(1-rho*relaxp);    
  Rho=CFL/(dt*dt);
  xo=new double[][][]
  {
    {{0, 0}, {W, 0}}, 
    {{0, 0}, {0, H}},
    {{0,H}, {W,H}},
    {{W,0}, {W,H}},
    {{0,H*.413}, {W*.06,H*.413}},
    {{W*.06+tailleDemiPorte,H*.413}, {W*.2-tailleDemiPorte,H*.413}},
    {{W*.198,H*.413}, {W*.25,H*.413}},
    {{W*.25+tailleDemiPorte,H*.413}, {W*.39-tailleDemiPorte,H*.413}},
    {{W*.39,H*.413}, {W*.45,H*.413}},
    {{W*.45+tailleDemiPorte,H*.413}, {W*.597-tailleDemiPorte,H*.413}},
    {{W*.597,H*.413}, {W*.61,H*.413}},
    {{W*.61+tailleDemiPorte,H*.413}, {W*.794-tailleDemiPorte,H*.413}},
    {{W*.794,H*.413}, {W*.812,H*.413}},
    {{W*.812+tailleDemiPorte,H*.413}, {W*.98-tailleDemiPorte,H*.413}},
    {{W*.98,H*.413}, {W,H*.413}},
    {{W*.1,H*.585}, {W*.105,H*.585}},
    {{W*.105+tailleDemiPorte,H*.585}, {W*.195-tailleDemiPorte,H*.585}},
    {{W*.195,H*.585}, {W*.21,H*.585}},
    {{W*.21+tailleDemiPorte,H*.585}, {W*.49-tailleDemiPorte,H*.585}},
    {{W*.49,H*.585}, {W*.51,H*.585}},
    {{W*.51+tailleDemiPorte,H*.585}, {W*.68-tailleDemiPorte,H*.585}},
    {{W*.68,H*.585}, {W*.75,H*.585}},
    {{W*.75+tailleDemiPorte,H*.585}, {W*.89-tailleDemiPorte,H*.585}},
    {{W*.89,H*.585}, {W*.9,H*.585}},
    {{W/5,0},{W/5,H*.412}}, 
    {{(W/5)*2,0},{(W/5)*2,H*.413}},
    {{(W/5)*3,0},{(W/5)*3,H*.4133}},
    {{(W/5)*4,0},{(W/5)*4,H*.413}},
    {{W/10,H*.585},{W/10,H}},
    {{W/5,H*.585},{W/5,H}},
    {{W*.15,H*.585},{W*.15,H}},
    {{W*.5,H*.585},{W*.5,H}},
    {{W*.6,H*.585},{W*.6,H}},
    {{W*.7,H*.585},{W*.7,H}},
    {{W*.9,H*.585},{W*.9,H}},
  };
   Ro=new double[]{2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
  
  // Zones
   zone=new double[][]
  {
      {0, 0,W, H*.413}, //rect {xmin,xmax,ymin,ymax}
      {0,H*.585,W, H},
      {0,H*.413,W/2.,H*.585},
      {W/2.,H*.413,W,H*.585}
  };
  g_zone=new double[][]{{0,10},{0,-10},{-10,0},{10,0}};// vecteur g dans chaque zone
   
  // Initialization of the balls
 int k=0;
  for (int i=0; i<nx; i++)for (int j=0; j<ny; j++) { 
    r[k]= tailleDemiPersonne;
    if (j<ny/2) {
      x[k][0]=(W/nx)*i+r[k];
      x[k][1] = (H*0.412/ny)*j+r[k];
    }
    else {
      x[k][0]=W/10+((W*0.9-W/10)/nx)*i+r[k];
      x[k][1]=0.585*H+332/ny*j+r[k];
    }
    v[k][0]=0;
    v[k][1]=0;
    masse[k]=1;//density*r[k]*r[k]*Math.PI; 
    Ptot[k]=0;
    k++;
  }
  // Initialization of p
  for (int i=0; i<nbBoules; i++) for (int j=0; j<nbBoules; j++) if(active[i] && active[j]) p[i][j]=0;
}

void draw() {
  // Display current state
  background(1);
  // Let's draw the box and the balls
  translate(decalage, decalage);
  scale(1, 1);
  stroke(255);
  for(int io=0;io<nombreMur;io++) {
    strokeWeight((float)(2.*Ro[io]));
    if(Ro[io]==0) strokeWeight(1);
    line((float) xo[io][0][0],(float) xo[io][0][1],(float) xo[io][1][0],(float) xo[io][1][1]);
  }
  strokeWeight(1);
  for (int i=0; i<nbBoules; i++) {
    if(active[i]){
      fill((float)(Math.min(Ptot[i]/400*255, 255)), 0, 0);
      circle((float) x[i][0], (float) x[i][1], (float)(2*r[i]));
    }
  }

  t+=dt;
  // update obstacles
  //for(int io=0;io<nombreMur;io++)for(int j=0;j<2;j++) rot(xo[io][j],omega*dt);
  // vmax computation
  double vmax=0;
  for (int i=0; i<nbBoules; i++) if(active[i]){ vmax=Math.max(vmax, norm(v[i]));}

  // List of neighboring balls, computation of gaps and director vectors
  int[] I=new int[nbBoules*nbBoules];
  int[] J=new int[nbBoules*nbBoules];
  int M=0;
  for (int i=0; i<nbBoules; i++) for (int j=0; j<i; j++) {
    if(active[i] && active[j]){
      for (int k=0; k<2; k++) e[i][j][k]=x[i][k]-x[j][k];
      s[i][j]=norm(e[i][j]);
      for (int k=0; k<2; k++) e[i][j][k]/=s[i][j];   
      s[i][j]-=(r[i]+r[j]);
      if (s[i][j] < 2.*vmax*dt+(r[i]+r[j])/10){
        I[M]=i;
        J[M]=j;
        M++;
      }
    }
  }

  // computation of the normal and length of the obstacle
  for (int io=0; io<nombreMur; io++) {
    N[io][1]=xo[io][1][0]-xo[io][0][0];
    N[io][0]=-(xo[io][1][1]-xo[io][0][1]);
    LongueurMur[io] =norm(N[io]);
    if (LongueurMur[io]==0) {
      N[io][0]=1;N[io][1]=0;  
    }
    else
    for (int k=0; k<2; k++) N[io][k]=N[io][k]/LongueurMur[io];
  }

  // List of neighboring obstacles, computation of gaps, projection points and director vectors
  int[] Io=new int[nombreMur*nbBoules];
  int[] Jo=new int[nombreMur*nbBoules];
  int Mo=0;
  for (int io=0; io<nombreMur; io++) for (int j=0; j<nbBoules; j++)  {
    if (active[j]){
      double[] V=new double[2]; 
      for (int k=0; k<2; k++) V[k]=x[j][k]-xo[io][0][k];
      double l=wp(V, N[io]);
      if (l<0) {                    // the projection point is xo[io][0]
        for (int k=0; k<2; k++) X[io][j][k]=xo[io][0][k];
      } else if (l>LongueurMur[io]) {      // the projection point is xo[io][1]
        for (int k=0; k<2; k++) X[io][j][k]=xo[io][1][k];
      } else {                      // the projection point is on the interval [xo[io][0],xo[io][1]]
        double[] tau=new double[]{N[io][1], -N[io][0]};
        for (int k=0; k<2; k++) X[io][j][k]=xo[io][0][k] + l* tau[k];
      }
      // comptuation of the director vector and of the current gap
      for (int k=0; k<2; k++) E[io][j][k]=X[io][j][k]-x[j][k];
      S[io][j]=norm(E[io][j]);
      for (int k=0; k<2; k++) E[io][j][k]/=S[io][j];   
      S[io][j]-=(Ro[io]+r[j]);
      // We add the constaint to the list to take into account if the ball is close to the obstacle
      if (S[io][j] < 2.*vmax*dt+(Ro[io]+r[j])/10) {
        Io[Mo]=io;
        Jo[Mo]=j;
        Mo++;
      }
    }
  }

  // hard copy of x and v
  double[][] xn=new double[nbBoules][2]; 
  double[][] vn=new double[nbBoules][2];
  for (int i=0; i<nbBoules; i++) for (int k=0; k<2; k++) {
    if(active[i])
      xn[i][k]=x[i][k];
      vn[i][k]=v[i][k];
  }
  
  double residu=2.*epsilon;
  while (residu>epsilon) {  // loop on p and P
    double residuX=2.*epsilonX;
    while (residuX>epsilonX){  // minimization wrt on x,v,s
      residuX=0;
      
      for(int i=0;i<nbBoules;i++)
          if(active[i]){
            double[] g=eval_g(xn[i]);  // détermination de g
            for (int k=0; k<2; k++) v[i][k]=vn[i][k]+dt*g[k]; // application de g
          }
      // contact ball<->ball
      for (int m=0; m<M; m++) {
        int i=I[m], j=J[m];
        for (int k=0; k<2; k++) {
          v[i][k]+=dt*p[i][j]*e[i][j][k]/masse[i];
          v[j][k]-=dt*p[i][j]*e[i][j][k]/masse[j];
        }
        double[] d=new double[]{x[i][0]-x[j][0], x[i][1]-x[j][1]};
        double alpha=dt*rho*(ps(d, e[i][j])-(r[i]+r[j])-s[i][j]);
        for (int k=0; k<2; k++) {
          v[i][k]-=alpha*e[i][j][k]/masse[i];
          v[j][k]+=alpha*e[i][j][k]/masse[j];
        }
      }
      
      // contact ball<->obstacle
      for (int m=0; m<Mo; m++) {
        int io=Io[m], j=Jo[m];
        for (int k=0; k<2; k++)
          v[j][k]-=dt*P[io][j]*E[io][j][k]/masse[j];
        double[] d=new double[]{X[io][j][0]-x[j][0], X[io][j][1]-x[j][1]};
        double alpha=dt*Rho*(ps(d, E[io][j])-(Ro[io]+r[j])-S[io][j]);
        for (int k=0; k<2; k++)
          v[j][k]+=alpha*E[io][j][k]/masse[j];
      }
      for (int i=0; i<nbBoules; i++) { // update position
        if(active[i]){
          double[] xk=new double[]{x[i][0], x[i][1]};
          for (int k=0; k<2; k++) x[i][k]=xn[i][k]+dt*v[i][k];
          double[] d=new double[]{x[i][0]-xk[0], x[i][1]-xk[1]};
          residuX=Math.max(residuX, norm(d)/dt);
        }
      }
      double residuS=0;
      for (int m=0; m<M; m++) {// update s
        int i=I[m], j=J[m];
        double[] d=new double[]{x[i][0]-x[j][0], x[i][1]-x[j][1]};
        double updated_s=plus(-p[i][j]/rho+ps(d, e[i][j])-(r[i]+r[j]));
        residuS=Math.max(residuS, Math.abs(s[i][j]-updated_s)/dt);
        s[i][j]=updated_s;
      }
      for (int m=0; m<Mo; m++) {// update S
        int io=Io[m], j=Jo[m];
        double[] d=new double[]{X[io][j][0]-x[j][0], X[io][j][1]-x[j][1]};
        double updated_s=plus(-P[io][j]/Rho+ps(d, E[io][j])-(Ro[io]+r[j]));
        residuS=Math.max(residuS, Math.abs(S[io][j]-updated_s)/dt);
        S[io][j]=updated_s;
      }
      residuX+=residuS;
    }
    
    // end minimization wrt x,v,s
    // update p
    residu=0;
    for (int m=0; m<M; m++)
    {
      int i=I[m], j=J[m];
      double[] d=new double[]{x[i][0]-x[j][0], x[i][1]-x[j][1]};
      double dp=ps(d, e[i][j])-(r[i]+r[j])-s[i][j];
      double pn=p[i][j];
      p[i][j]-=rho*dp;
      p[i][j]/=(1+epsilonp);  //
      dp=pn-p[i][j];
      residu=Math.max(residu, Math.abs(dp));
    }
    // update P
    residu=0;
    for (int m=0; m<Mo; m++)
    {
      int io=Io[m], j=Jo[m];
      double[] d=new double[]{X[io][j][0]-x[j][0], X[io][j][1]-x[j][1]};
      double dp=ps(d, E[io][j])-(Ro[io]+r[j])-S[io][j];
      P[io][j]-=Rho*dp; 
      residu=Math.max(residu, Math.abs(dp));
    }
  }// end loop on p
  
  
  // Computation of the total pressure on each ball
  for (int i=0; i<nbBoules; i++) {
    if(active[i])
      Ptot[i]=0;
  }
  for (int m=0; m<M; m++) {
    int i=I[m], j=J[m];
    double[] d=new double[]{x[i][0]-x[j][0], x[i][1]-x[j][1]};
    double alpha=rho*(ps(d, e[i][j])-(r[i]+r[j])-s[i][j]);
    Ptot[i]+=p[i][j]-alpha;
    Ptot[j]+=p[i][j]-alpha;
  }
  for (int m=0; m<Mo; m++) {
    int io=Io[m], j=Jo[m];
    double[] d=new double[]{X[io][j][0]-x[j][0], X[io][j][1]-x[j][1]};
    double alpha=Rho*(ps(d, E[io][j])-(Ro[io]+r[j])-S[io][j]);
    Ptot[j]+=P[io][j]-alpha;
  }

  minP=Ptot[0];
  maxP=Ptot[0];
  for (int i=0; i<nbBoules; i++) {
    if(active[i]){
      minP=Math.min(minP, Ptot[i]);
      maxP=Math.max(maxP, Ptot[i]);
    }
  }
}
