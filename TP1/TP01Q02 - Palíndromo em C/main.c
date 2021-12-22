#include <stdio.h>
#include <string.h>
#include <stdbool.h>

//Função que finaliza o programa
bool returnEnd(char fim []){
    bool result;

    result = (strlen(fim) == 4 && fim[0] == 'F' && fim[1] == 'I' && fim[2] == 'M');

    return result;
}

//Função que verifica se é um palíndromo ou não
int verifyPalidrome(char input[]){
    int j = strlen(input) - 2;
    int i = 0;
    int result = 1;

    while (i < j){
        if(input[i] != input[j])
            result = 0;
        i++;
        j--;
    }   

    return result;
}

int main(){
    char input[200];
    int output = 1;
    do{
        fflush(stdin);
        fgets(input, 200, stdin);
        output = verifyPalidrome(input);
        if(output == 1)
            printf("SIM\n");
        else
            printf("NAO\n");
    }while(returnEnd(input) == false);
    
    return 0;
}