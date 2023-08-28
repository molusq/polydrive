#include <iostream>

#include "document.h"

using namespace std;





int main()
{

Paragraph p1 {"Un titre","des trucs à lire ... \n\t\t blablabla "};
Paragraph p2 {"Un autre titre","des trucs autre trucs"};
Paragraph pext1  {"Un titre externe","un paragraph externe"};
Item_Paragraph ip {"", "le contenu d'un item", "#"};


cout << p1 << endl;
cout << "~~~~~~~~~~~~~~~~~~~~~~~" << endl;
cout << ip << endl;

vector<Paragraph*> vptr_p {&p1,&ip};

for(Paragraph* ptr_p: vptr_p){
  cout << *ptr_p << endl;
  cout << "~~~~~~~~~~~~~~~~~~~~~~~" << endl;
}

vector<string> d1_authors {"Julien DeAntoni","Jean-Paul Rigault"};

vector<Paragraph*> d1_paragraphs {&p1};

Document d1("Nouveau Document", d1_authors, d1_paragraphs);

cout<<"****************   d1   *************************"<<endl;
cout<< d1 << endl;

d1.append(p2, true);

cout<<"*************** d1 + p2 *************************"<<endl;
cout<< d1 << endl;

d1.append(pext1, false);

cout<<"**************  d1 + pext  **********************"<<endl;
cout<< d1 << endl;

Paragraph p3 {p1 + p2};

d1.append(p3, true);

cout<<"************* d1 + (p1 + p2)  *******************"<<endl;
cout<< d1 << endl;

pext1.operator+=(Paragraph("modifie", "quelques ajouts"));

cout<<"************* d1 with pext1 concatenated ********"<<endl;
cout<< d1 << endl;


 Document d2 {d1};
cout<<"*************  d2 = d1  *************************"<<endl;
 cout<< d2 << endl;

try {
cout<<"************* d1[0]   ****************************"<<endl;
cout<< d1[0] << endl;

cout<<"************* d1[2]   ****************************"<<endl;
cout<< d1[2] << endl;

cout<<"************* d1[3]   ****************************"<<endl;
cout<< d1[3] << endl;

// cout<<"************* d1[4]   ****************************"<<endl;
// cout<< d1[4] << endl;
}
catch(Document::No_such_paragraph e)
{
  cout << "index out of bound..." <<endl;
}

d1.append(ip,true);
cout<<"************* d1 + ip  *******************"<<endl;
cout<< d1 << endl;

cout<<"************* ip  *******************"<<endl;
cout<< ip << endl;

Document d3 {"leTitre", {"auteur1", "auteur2"}, {&p1,&p2}};
cout<<"************* d3  *******************"<<endl;
cout<<d3<<endl;
return 1;
}
