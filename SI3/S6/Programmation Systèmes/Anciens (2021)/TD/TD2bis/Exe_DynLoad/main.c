#include <Windows.h>
#include <stdio.h> 

// d�crire le prototype de la proc�dure : proto_convert est le type d�fini par typedef,
// c�est un pointeur sur fonction int (int).
typedef void(* proto) (char*); 

int main() {
	proto printLib;
	// Attention : le chemin du fichier est en Unicode si votre projet est configur�
	//             comme tel, la chaine doit alors �tre L" "  
	// Attention : pas de \ dans le chemin, sinon c�est un caract�re d��chappement
	void * hinstDLL = LoadLibrary("C:/Users/Florian/source/repos/Bibliotheque/Debug/Bibliotheque.dll"); 
	if (!hinstDLL) // Erreur lors du chargement de la biblioth�que ? 
		printf("Impossible de charger la biblioth�que."); 
	else {         // On r�cup�re l'adresse de la fonction   
		printLib = (proto)GetProcAddress(hinstDLL, "PrintStop");
		char* st = "Bibliotheque avec chargement dynamique";
		printLib(st);
	}
	FreeLibrary(hinstDLL); 
}