#include <Windows.h>
#include <stdio.h> 

// décrire le prototype de la procédure : proto_convert est le type défini par typedef,
// c’est un pointeur sur fonction int (int).
typedef void(* proto) (char*); 

int main() {
	proto printLib;
	// Attention : le chemin du fichier est en Unicode si votre projet est configuré
	//             comme tel, la chaine doit alors être L" "  
	// Attention : pas de \ dans le chemin, sinon c’est un caractère d’échappement
	void * hinstDLL = LoadLibrary("C:/Users/Florian/source/repos/Bibliotheque/Debug/Bibliotheque.dll"); 
	if (!hinstDLL) // Erreur lors du chargement de la bibliothèque ? 
		printf("Impossible de charger la bibliothèque."); 
	else {         // On récupère l'adresse de la fonction   
		printLib = (proto)GetProcAddress(hinstDLL, "PrintStop");
		char* st = "Bibliotheque avec chargement dynamique";
		printLib(st);
	}
	FreeLibrary(hinstDLL); 
}