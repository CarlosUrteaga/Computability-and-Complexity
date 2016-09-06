#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int regla(char *str);


int main(int argc, char* argv[]){
	char str[999999]="MI";
	printf("%s\n", str);
	
	//printf("%s\n", aux);
	regla(str);
	printf("%s\n", str);
	return(0);

}

int regla(char *str){
	int n;
	int i;
	n =10;
	time_t t;
	/* Intializes random number generator */
	int j;
	srand((unsigned) time(&t));
	i=0;
	while (i< n){
		j = rand() % 4;
		printf("regla:\t\t%i\n", j+1);
		printf("longitud:\t%i\n", strlen(str));
		printf("turno\t%i\n", i);
		//printf("%s\t\n", str);

		if (j==0){
			//printf("Regla 1\n");
			//printf("%i\n", strlen(str));
			if (str[strlen(str)-1]=='I'){
				strcat(str, "U");
				i++;
			}
		}else{
			if (j==1){
				//printf("Regla 2\n");
				//printf("%i\n", strlen(str));
				char tmp[999999]="";
				strncpy(tmp, &str[1], strlen(str));
				strcat(str, tmp);
				i++;
			}else{
				if (j==2){
					bool boolFlag=false;
					//printf("Regla 3\n");
					//printf("%i\n", strlen(str));
					char aux[999999]="M"; //set the start of the new array
					// we know that str[0] is M
					int idx =1; 
					// aux of Is
					int cntI; 
					char auxI[4]=""; 
					//while length of str
					while (idx < strlen(str)){
						//ask for Is
						if (str[idx]=='I'){ 
							cntI++;				
							strcat(auxI, "I");
							if (cntI==3){
								strcat(aux, "U");
							 	strncpy(auxI,"",1);
							 	cntI=0;
							 	if (!boolFlag)
							 	{
							 		boolFlag=true;
							 	}
							}
						}else{
							//if (str[idx]=='U')	{
							 strcat(aux, auxI);
							 strcat(aux, "U");
							 strncpy(auxI,"",1);
							 cntI=0;
							 	
							 //}
						}
						//printf("%i\n", idx);
						idx++;
					}
					strcat(aux, auxI);
					strncpy(str,"",1);
					strncpy(str, aux, strlen(aux));
					str[strlen(aux)] = '\0';
					if (boolFlag)
					{
						i++;
					}
				}else{
					//printf("Regla 4\n");
					//printf("%i\n", strlen(str));
					bool boolFlag=false;
					int idx =1; // we know that str[0] is M
					int cntU;
					char auxU[3]="";
					char aux[999999]="M";
					while (idx < strlen(str)){
						if (str[idx]=='U')
						{
							cntU++;
							strcat(auxU, "U");
							if (cntU==2){
							 	strncpy(auxU,"",1);
							 	strcat(aux, auxU);
							 	cntU=0;
							 	if (!boolFlag)
							 	{
							 		boolFlag=true;
							 	}
							}
						}else{
							//if (str[idx]=='U')	{
							 strcat(aux, auxU);
							 strcat(aux, "I");
							 strncpy(auxU,"",1);
							 cntU=0;
							 //}
						}
						//printf("%i\n", idx);
						idx++;
					}
					strcat(aux, auxU);
					strncpy(str,"",1);
					strncpy(str, aux, strlen(aux));
					str[strlen(aux)] = '\0';
					if (boolFlag)
					{
						i++;
					}


				}
			}
		}
		
	}
}
