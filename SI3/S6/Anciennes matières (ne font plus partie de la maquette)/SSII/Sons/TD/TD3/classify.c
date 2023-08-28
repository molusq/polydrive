#include <iostream>
#include <dirent.h>
#include <errno.h>
#include <fstream>
#include <cstdlib>
#include <cstring>
#include <climits>

using namespace std;
void usage(){
  cout<<"classify dir sizeVect nbClasses [--verbose] [--gnuplot] [--showBary] [--showClasses] [--writeBary]"<<endl;
}
int filter(const struct dirent *d){
  if(strcmp(d->d_name,".") && strcmp(d->d_name,".."))
    return 1;
  return 0;
}
double distance(double ** mat,int _i,double ** bar,int _j,int n){
  double d=0;
  for(int i=0;i<n;i++){
    d+=(mat[_i][i]-bar[_j][i])*(mat[_i][i]-bar[_j][i]);
  }
  return d;
}
void display(int * classes,int  n){
  cout<<"[";
  for(int i=0;i<n;i++){
    cout<<classes[i];
    if(i!=(n-1))
      cout<<";";
  }
  cout<<"]"<<endl;
}
int main(int argc, char ** argv){
  if(argc<4){
    usage();
    exit(1);
  }
  bool verbose = false;
  bool gplt=false,showBar=false,showClass=false,writeBary=false;
  if(argc>4){
    for(int i=4;i<argc;i++){
      if(!strcmp(argv[i],"--verbose"))
	verbose=true;
      else
	if(!strcmp(argv[i],"--gnuplot"))
	  gplt=true;
	else
	  if(!strcmp(argv[i],"--showBary"))
	    showBar=true;
	  else
	    if(!strcmp(argv[i],"--showClasses"))
	    showClass=true;
	    else
	      if(!strcmp(argv[i],"--writeBary"))
		writeBary=true;
	    else{
	      cerr<<"Unrecognized option: "<<argv[i]<<" !"<<endl;
	      usage();
	      exit(1);
	    }
    }
  }
  double ** mat;
  double sum; // for normalization of votes
  double integer;
  string dir_name=argv[1];
  struct dirent** namelist;
  int nClasses = strtol(argv[3], (char **)NULL, 10);
  if(nClasses==LONG_MIN || nClasses==LONG_MAX){
    perror("strtol");
    exit(errno);
  }
  int m = strtol(argv[2], (char **)NULL, 10);
  if(m==LONG_MIN || m==LONG_MAX){
    perror("strtol");
    exit(errno);
  }
  ifstream fin;
  int n = scandir(dir_name.c_str(),&namelist,filter,NULL);
  if(n==-1){
    perror("scandir");
    exit(errno);
  }
  if(!n){
    cerr<<"Dir "<<dir_name<<" is empty !"<<endl;
    exit(1);
  }
  mat = new double * [n];
  for(int i=0;i<n;i++){
    mat[i]=new double[m];
  }
  if(verbose)
    cout<<"A "<<n<<"x"<<m<<" matrix has been allocated."<<endl;
  char buff[256];
  if(n==-1)
    {
      perror("Reading dir");
      exit(errno);
    }
  if(verbose)
    cout<<"Directory "<<dir_name<<" has "<<n<<" entries."<<endl;
  for(int i=0;i<n;i++){
    if(verbose)
      cout<<"Reading "<<namelist[i]->d_name<<"..."<<endl;
    string file_name=dir_name+"/"+namelist[i]->d_name;  // Diane: ajout du / entre repertoire et fichier
    fin.open(file_name.c_str());
    if(verbose)
      printf("fichier : %s\n",file_name.c_str());
    if(!fin)
      {
	perror("opening file");
	exit(errno);
      }
    sum = 0;
    for(int j=0;j<m;j++){
      fin.getline(buff,256);
      //      cout<<"buff="<<buff<<endl;
      if(fin.eof()){
	cerr<<"File "<<namelist[i]->d_name<<" is less than "<<m<<" lines long !"<<endl;
	exit(1);
      }
      integer=strtod(buff,(char **)NULL);
      //      cout<<integer<<endl;
      if(integer==LONG_MIN || integer==LONG_MAX){
	perror("strtod in file");
	exit(errno);
      }
      mat[i][j]=integer;
      sum += integer;
    }
    // normalization
    /* if(sum != 0) { */
    /*   for(int j=0;j<m;j++){ */
    /* 	mat[i][j]/=sum; */
    /*   } */
    /* } */
    fin.close();
  }
  if(verbose)
    cout<<"All the files have been read. Starting classification..."<<endl;
  //initializing barycentres
  /* if(m<nClasses){ */
  /*   cerr<<"Error: m must be > nClasses !"<<endl; */
  /*   exit(1); */
  /* } */
  int * classes = new int[n];
  double ** barycentres = new double *[nClasses];
  for(int i=0;i<nClasses;i++){
    if(verbose)
      cout<<"Initializing barycenter "<<i<<" with vector "<<namelist[i]->d_name<<endl;
    barycentres[i]=new double[m];
    for(int j=0;j<m;j++){
      barycentres[i][j]=mat[i][j];
    }
  }
  

  double d,min;
  int c;
  bool change=true;
  int nIt=0;
  while(change){
    if(verbose)
      cout<<"Assigning classes to vectors..."<<endl;
    change=false;
    //assigning classes
    for(int i=0;i<n;i++){
      min = distance(mat,i,barycentres,0,m);
      c=0;
      for(int j=0;j<nClasses;j++){
	d = distance(mat,i,barycentres,j,m);
	if(d<min){
	  min=d;
	  c=j;
	}
      }
      //      cout<<"the class is "<<c<<endl;
      if(classes[i]!=c){
	classes[i]=c;
	change = true;
      }
    }
    if(verbose)
      display(classes,n);
    if(verbose)
      cout<<"Recomputing barycenters..."<<endl;
    //recomputing barycentres
    for(int i=0;i<nClasses;i++){
      for(int j=0;j<m;j++){
	barycentres[i][j]=0;
	int count=0;
	for(int k=0;k<n;k++){
	  if(classes[k]==i)
	    {
	      barycentres[i][j]+=mat[k][j];
	      count++;
	    }
	}
	if(count) barycentres[i][j]/=(double) count;
      }
    }
    nIt++;
  }
  if(verbose)
    cout<<"k-means has converged after "<<nIt<<" iterations."<<endl;
  if(showClass){
  if(!gplt){
    for(int i=0;i<nClasses;i++){
      cout<<"**********Class "<<i<<"**********"<<endl;
      for(int j=0;j<n;j++){
	if(classes[j]==i)
	  cout<<namelist[j]->d_name<< " " <<i <<endl;
      }
    }
  }
  else
    {
      cout<<"plot ";
      int ct=0;
      for(int i=0;i<nClasses;i++){
	for(int j=0;j<n;j++){
	  if(classes[j]==i)
	    {
	      if(ct) cout<<", ";
	      cout<<"\""<<dir_name<<"/"<<namelist[j]->d_name<<"\" lt "<<i+1<<" with linespoints";
	      ct++;
	    }
	}
      }
      cout<<endl;
    }
  }
  if(showBar){
    for(int i=0;i<nClasses;i++){
      cout<<"Barycenter of class "<<i<<endl;
      for(int j=0;j<m;j++)
	cout<<barycentres[i][j]<<endl;
    }
  }
  if(writeBary){
    for(int i=0;i<nClasses;i++){
      char name_file[24];
      sprintf(name_file,"bar%d.txt",i);
      cout<<"Writting barycenter of class "<<i<<" in file "<<name_file<<endl;
      ofstream fout(name_file);
      for(int j=0;j<m;j++)
	fout<<barycentres[i][j]<<endl;
      fout.close();
    }
  }
  return 0;
}
