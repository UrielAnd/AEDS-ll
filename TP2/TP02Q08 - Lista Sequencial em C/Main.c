#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_FIELD_SIZE 100

typedef struct {
    char nome[MAX_FIELD_SIZE];
    char formato[MAX_FIELD_SIZE];
    char duracao[MAX_FIELD_SIZE];
    char pais[MAX_FIELD_SIZE];
    char idioma[MAX_FIELD_SIZE];
    char emissora[MAX_FIELD_SIZE];
    char transmissao[MAX_FIELD_SIZE];
    int num_temporadas;
    int num_episodios;
} Serie;

char *remove_line_break(char *line) {
    while (*line != '\r' && *line != '\n') line++;
    *line = '\0';
    return line;
}

char *freadline(char *line, int max_size, FILE *file) {
    return remove_line_break(fgets(line, max_size, file));
}

char *readline(char *line, int max_size) {
    return freadline(line, max_size, stdin);
}

void print_serie(Serie *serie) {
    printf("%s %s %s %s %s %s %s %d %d\n",
        serie->nome,
        serie->formato,
        serie->duracao,
        serie->pais,
        serie->idioma,
        serie->emissora,
        serie->transmissao,
        serie->num_temporadas,
        serie->num_episodios
    );
}

// Retorna o tamanho em bytes de um arquivo.
long tam_arquivo(FILE *file) {
    fseek(file, 0L, SEEK_END);
    long size = ftell(file);
    rewind(file);
    return size;
}

// Retorna todo o conteúdo do arquivo numa string.
char *ler_html(char filename[]) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        fprintf(stderr, "Falha ao abrir arquivo %s\n", filename);
        exit(1);
    }
    long tam = tam_arquivo(file);
    char *html = (char *) malloc(sizeof(char) * (tam + 1));
    fread(html, 1, tam, file);
    fclose(file);
    html[tam] = '\0';
    return html;
}

/**
 * @brief Extrai os textos de uma tag html.
 * 
 * @param html Ponteiro para o caractere que abre a tag '<'.
 * @param texto Ponteiro para onde o texto deve ser colocado.
 * 
 * @return Ponteiro para o texto extraído.
 */
char *extrair_texto(char *html, char *texto) {
    char *start = texto;
    int contagem = 0;
    while (*html != '\0') {
        if (*html == '<') {
            if (
                (*(html + 1) == 'p') ||
                (*(html + 1) == 'b' && *(html + 2) == 'r') ||
                (*(html + 1) == '/' && *(html + 2) == 'h' && *(html + 3) == '1') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'h') ||
                (*(html + 1) == '/' && *(html + 2) == 't' && *(html + 3) == 'd')
            ) break;
            else contagem++;
        }
        else if (*html == '>') contagem--;
        else if (contagem == 0 && *html != '"') {
            if (*html == '&') html = strchr(html, ';');
            else if (*html != '\r' && *html != '\n') *texto++ = *html;
        }
        html++;
    }
    *texto = '\0';
    return *start == ' ' ? start + 1 : start;
}

/**
 * @brief Lê o HTML da série e popula os campos da struct.
 * 
 * @param serie Struct Serie que vai receber os dados.
 * @param html String contendo todo o HTML do arquivo.
 */

void ler_serie(Serie *serie, char *html) {
    char texto[MAX_FIELD_SIZE];

    char *ptr = strstr(html, "<h1");
    extrair_texto(ptr, texto);

    char *parenteses_ptr = strchr(texto, '(');
    if (parenteses_ptr != NULL) *(parenteses_ptr - 1) = '\0';

    strcpy(serie->nome, texto);

    ptr = strstr(ptr, "<table class=\"infobox_v2\"");

    ptr = strstr(ptr, "Formato");
    ptr = strstr(ptr, "<td");
    strcpy(serie->formato, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Duração");
    ptr = strstr(ptr, "<td");
    strcpy(serie->duracao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "País de origem");
    ptr = strstr(ptr, "<td");
    strcpy(serie->pais, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Idioma original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->idioma, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Emissora de televisão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->emissora, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "Transmissão original");
    ptr = strstr(ptr, "<td");
    strcpy(serie->transmissao, extrair_texto(ptr, texto));

    ptr = strstr(ptr, "N.º de temporadas");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_temporadas);

    ptr = strstr(ptr, "N.º de episódios");
    ptr = strstr(ptr, "<td");
    sscanf(extrair_texto(ptr, texto), "%d", &serie->num_episodios);
}

//Atributos e metódos da lista
Serie array[50];
int n;
 
void start(){
    n = 0;
}
void inserirInicio(Serie x) {
    if (n >= 50) 
        exit(1);

    //levar elementos para o fim do array
    for (int i = n; i > 0; i--){
        array[i] = array[i-1];
    }
    array[0] = x;
    n++;
}
void inserirFim(Serie x) {
    if (n >= 50) 
        exit(1);
    array[n] = x;
    n++;
}
void inserir(Serie x, int pos) {
    if (n >= 50 || pos < 0 || pos > n)
        exit(1);

    //levar elementos para o fim do array
    for (int i = n; i > pos; i--){
        array[i] = array[i-1];
    }
    array[pos] = x;
    n++;
}

Serie removerInicio() {
    if (n == 0)
        exit(1);
    Serie resp = array[0];
    n--;
    for (int i = 0; i < n; i++){
        array[i] = array[i+1];
    }
    return resp;
}

Serie removerFim() {
    if (n == 0)
        exit(1); 
 
    return array[--n];
}

Serie remover(int pos) {
    if (n == 0 || pos < 0 || pos >= n)
        exit(1);
    Serie resp = array[pos];
    n--;
    for (int i = pos; i < n; i++){
        array[i] = array[i+1];
    }
    return resp;
}

void mostrar(){
    for (int i = 0; i < n; i++){
        print_serie(&array[i]);
    }
    
}

#define MAX_LINE_SIZE 250
#define PREFIXO "/tmp/series/"
// #define PREFIXO "../entrada e saida/tp02/series/"

//Função que finaliza o programa
int isFim(char line[]) {
    return line[0] == 'F' && line[1] == 'I' && line[2] == 'M';
}

// Função que retorna uma substring
char* substring(char *substring, const char *string, int inicio, int n)
{
    //Copia caractere por caractere até n e envia para a substring
    while (n > 0)
    {
        *substring = *(string + inicio);
 
        substring++;
        string++;
        n--;
    }
 
    //adiciona o null no final
    *substring = '\0';
 
    // retorna substring
    return substring;
}

int main(void){
    int breakpoint = 0; //Variável que finaliza o programa
    size_t tam_prefixo = strlen(PREFIXO);
    char line[MAX_LINE_SIZE];
    char auxComando[MAX_LINE_SIZE]; //Variável que armazena o comando
    char auxPosition[MAX_LINE_SIZE]; //Variável que armazena a posição
    char auxArquivo[MAX_LINE_SIZE]; 
    char caminhoArquivo[MAX_LINE_SIZE]; //Variável que armazena o caminho do arquivo
    int position = 0;

    strcpy(line, PREFIXO);
    readline(line + tam_prefixo, MAX_LINE_SIZE);

    while (!isFim(line + tam_prefixo)) {
        Serie serie;
        char *html = ler_html(line);
        ler_serie(&serie, html);
        free(html);
        inserirFim(serie);
        readline(line + tam_prefixo, MAX_LINE_SIZE);
    }

    scanf("%d", &breakpoint);
    breakpoint++;
    
    for(int i = 0; i <breakpoint; i++){
        Serie serie;
        fgets(line, MAX_LINE_SIZE, stdin);
        substring(auxComando, line, 0, 2);
        
        //Caso o comando dado seja INSERIR NO INICIO
        if(auxComando[0] == 'I' && auxComando[1] == 'I'){
            substring(auxArquivo, line, 3, strlen(line)-1);
            strcpy(caminhoArquivo, PREFIXO);
            strcat(caminhoArquivo, auxArquivo);
            remove_line_break(caminhoArquivo);
            char *html = ler_html(caminhoArquivo);
            ler_serie(&serie, html);
            free(html);
            inserirInicio(serie);
        }

        //Caso o comando dado seja INSERIR EM QUALUQER POISÇÃO
        else if(auxComando[0] == 'I' && auxComando[1] == '*'){
            substring(auxPosition, line, 3, 5);
            position = atoi(auxPosition);
            substring(auxArquivo, line, 6, strlen(line));
            strcpy(caminhoArquivo, PREFIXO);
            strcat(caminhoArquivo, auxArquivo);
            remove_line_break(caminhoArquivo);
            char *html = ler_html(caminhoArquivo);
            ler_serie(&serie, html);
            free(html);
            inserir(serie, position);
        }

        //Caso o comando dado seja INSERIR NO FIM
        else if(auxComando[0] == 'I' && auxComando[1] == 'F'){
            substring(auxArquivo, line, 3, strlen(line));
            strcpy(caminhoArquivo, PREFIXO);
            strcat(caminhoArquivo, auxArquivo);
            remove_line_break(caminhoArquivo);
            char *html = ler_html(caminhoArquivo);
            ler_serie(&serie, html);
            free(html);
            inserirFim(serie);
        }

        //Caso o comando dado seja RETIRAR NO INICIO
        else if(auxComando[0] == 'R' && auxComando[1] == 'I'){
            printf("(R) %s\n", removerInicio().nome);
        }

        //Caso o comando dado seja RETIRAR EM QUALQUER POSIÇÃO
        else if(auxComando[0] == 'R' && auxComando[1] == '*'){
            substring(auxPosition, line, 3, 5);
            position = atoi(auxPosition);
            printf("(R) %s\n", remover(position).nome);
            
        }

        //Caso o comando seja RETIRAR NO FIM
        else if(auxComando[0] == 'R' && auxComando[1] == 'F'){
            printf("(R) %s\n", removerFim().nome);
        }

    }
    mostrar();
    return EXIT_SUCCESS;
}