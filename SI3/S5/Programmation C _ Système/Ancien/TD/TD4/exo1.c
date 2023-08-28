#include <stdio.h>
#include <stdarg.h>
#include <string.h>



/*void cat_string(char *str1, ...){
	va_list ap;
 	va_start(ap, str);

 	for (char *current =  str; current; current = va_arg(ap, char *))
    	Printf(current);
 	va_end(ap);
}*/

void printNumClean(int i, int base){
	if(i >= base){
		printNumClean(i/base, base);
	}
	if(i%base<10){
		putchar(i%base+'0');
	}
	else putchar(i%base-10+'A');
}

void Printf(char *format){
	for(int i = 0; i <= (int)strlen(format); i++){
			putchar(format[i]);
	}
}


void CatStrings(char str1[], ...){
	va_list ap;
	va_start(ap, str1);
	char *str = va_arg(ap, char*);
	for(int i = 0; i <= (int)strlen(str); i++){
			putchar(str1[i]);
		}
	while(str[0] > 0){
		Printf(str);
		str = va_arg(ap, char*);
	}
	putchar('\n');
	va_end(ap);
}

void cat_string_0(char *str1, ...){
	va_list ap;
	va_start(ap, str1);
	while(str1 != NULL){
		Printf(str1);
		str1 = va_arg(ap, char*);
	}
	putchar('\n');
}

void new_print(char *format, ...){
	va_list ap;
	va_start(ap, format);
	char *current = format;
	while(*current != '\0'){
		if(*current != '%'){
			putchar(*current++);
			
		} else {
			switch(*++current){
				case '%':
					putchar('%');
					break;
				
				case 'c':
					putchar((int)va_arg(ap, char*));
					current++;
					break;
				
				case 's':
					Printf(va_arg(ap, char*));
					current++;
					break;
				
				case 'x':
					printNumClean(va_arg(ap, int), 16);
					current++;
					break;

				case 'd':
					printNumClean(va_arg(ap, int), 10);
					current++;
					break;
			}
		}
	}
	
	putchar('\n');
}



int main(){
	//CatStrings("Hel", "lo", " World");
	//cat_string_0("Hello", " YounyBaby", NULL);
	new_print("Hello %c %s %d %x", '1', "Bla", 123, 29);
}