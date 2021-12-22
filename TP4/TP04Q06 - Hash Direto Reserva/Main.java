import java.util.*;
import java.io.*;
import java.time.*;

class Hash {
    int tabela[];
    int tamanhoNormal, tamanhoReserva, tamanhoTotal, indexReserva;
    static int contador = 0;
    final int NULO = -1;
    Set< String> itensInseridos = new HashSet<String>();
 
    public Hash() {
       this(21, 9);
    }
 
    public Hash(int tamanhoNormal, int tamanhoReserva) {
       this.tamanhoNormal = tamanhoNormal;
       this.tamanhoReserva = tamanhoReserva;
       this.tamanhoTotal = tamanhoNormal + tamanhoReserva;
       this.tabela = new int[this.tamanhoTotal];
       for (int i = 0; i < tamanhoNormal; i++) {
          tabela[i] = NULO;
       }
       indexReserva = 0;
    }
 
    public int h(int elemento) {
       return elemento % tamanhoNormal;
    }
 
    public boolean inserir(int elemento) {
       boolean resp = false;
       if (elemento != NULO) {
          int pos = h(elemento);
          if (tabela[pos] == NULO) {
             tabela[pos] = elemento;
             contador++;
             resp = true;
          } else if (indexReserva < tamanhoReserva) {
             tabela[tamanhoNormal + indexReserva] = elemento;
             indexReserva++;
             contador++;
             resp = true;
          }
       }
       return resp;
    }
 
    public boolean pesquisar(int elemento) {
       boolean resp = false;
       int pos = h(elemento);
       if (tabela[pos] == elemento) {
          resp = true;
       } else if (tabela[pos] != NULO) {
          for (int i = 0; i < indexReserva; i++) {
             if (tabela[tamanhoNormal + i] == elemento) {
                resp = true;
                i = indexReserva;
                contador++;
             }
          }
       }
       if (resp == false) {
       }
       return resp;
    }

    
    static void log(long time){ 

        try {
            File myObj = new File("matriıculaHashReserva.txt");
            if (myObj.createNewFile()) {
            } else {
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            FileWriter myWriter = new FileWriter("matriıculaHashReserva.txt");
            myWriter.write("728764" + "\t" + time + "\t" + contador);
            myWriter.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
    
    }
 
 }

class Serie{
    //declaração dos atributos
    private String name;
    private String format;
    private String duration;
    private String country;
    private String language;
    private String broadcaster;
    private String streaming;
    private int seasons;
    private int episodes;
    //construtor primário
    public Serie(){
        name = "";
        format = "";
        duration = "";
        country = "";
        language = "";
        broadcaster = "";
        streaming = "";
        seasons = 0;
        episodes = 0;
    }
    //construtor secundário
    public Serie(String name, String format, String duration, String country, String language, String broadcaster, String streaming, int seasons, 
    int episodes){
        this.name = name;
        this.format = format;
        this.duration = duration;
        this.country = country;
        this.language = language;
        this.broadcaster = broadcaster;
        this.streaming = streaming;
        this.seasons = seasons;
        this.episodes = episodes;
    }
    //método para setar o atributo name
    public void setName(String name){
        this.name = name;
    }
    //método para setar o atributo formato
    public void setFormat(String format){
        this.format = format;
    }
    //método para setar o atributo duration
    public void setDuration(String duration){
        this.duration = duration;
    }
    //método para setar o atributo country
    public void setCountry(String country){
        this.country = country;
    }
    //método para setar o atributo language
    public void setLanguage(String language){
        this.language = language;
    }
    //método para setar o atributo broadcaster
    public void setBroadcaster(String broadcaster){
        this.broadcaster = broadcaster;
    }
    //método para setar o atributo streaming
    public void setStreaming(String streaming){
        this.streaming = streaming;
    }
    //método para setar o atributo seasons
    public void setSeasons(int seasons){
        this.seasons = seasons;
    }
    //método para setar o atributo episodes
    public void setEpisodes(int episodes){
        this.episodes = episodes;
    }
    //método para retornar o atributo name
    public String getName(){ 
        return this.name; 
    }
    //método para retornar o atributo format
    public String getFormat(){ 
        return this.format; 
    }
    //método para retornar o atributo duration
    public String getDuration(){ 
        return this.duration; 
    }
    //método para retornar o atributo country
    public String getCountry(){ 
        return this.country; 
    }
    //método para retornar o atributo language
    public String getLanguage(){ 
        return this.language; 
    }
    //método para retornar o atributo broadcaster
    public String getBroadcaster(){ 
        return this.broadcaster; 
    }
    //método para retornar o atributo streaming
    public String getStreaming(){ 
        return this.streaming; 
    }
    //método para retornar o atributo seasons
    public int getSeasons(){ 
        return this.seasons; 
    }
    //método para retornar o atributo episodes
    public int getEpisodes(){ 
        return this.episodes; 
    }
    //método para clonar a classe
    public Serie clone(){
        Serie resp = new Serie();
        resp.name = this.name;
        resp.format = this.format;
        resp.duration = this.duration;
        resp.country = this.country;
        resp.language = this.language;
        resp.broadcaster = this.broadcaster;
        resp.streaming = this.streaming;
        resp.seasons = this.seasons;
        resp.episodes = this.episodes;
        return resp;
    }
    //método para printar a classe
    public void printClass(){
        System.out.println(this.name + " " + this.format + " " + this.duration + " " + this.country + " " + this.language + " " + this.broadcaster + " " +
        this.streaming + " " + this.seasons + " " + this.episodes);
    }
    //método para tratar a linha, deixar apenas números e converter o retorno de String para Integer
    public int justInt(String line){
        String resp = "";
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) >= '0' && line.charAt(i) <= '9'){ //caso o caracter seja um número ele é concatenado a variável resp
                resp += line.charAt(i);
            } else { //caso seja outro caracter, o i recebe o valor da condição de parada e o método de repetição é encerrado
                i = line.length();
            }
        }
        return Integer.parseInt(resp); //conversão da string resp para número inteiro a ser retornado
    }
    //método para a remoção das tags da linha lida do arquivo para retornar apenas o que é desejado
    public String removeTags(String line){
        String resp = "";
        int i = 0;
        while(i < line.length()){ //enquanto i for menor que o tamanho da String linha
            if(line.charAt(i) == '<'){ // é testado para verificar se o contador i ainda está dentro das tags
                i++;
                while(line.charAt(i) != '>') i++; //ao encontrar o sinal de fechamento das tags o laço de repetição é encerrado
            } else if(line.charAt(i) == '&'){ //mesmo tratamento de cima mas para outras exceções presentes em alguns outros arquivos
                i++;
                while(line.charAt(i) != ';') i++;
            } else { //o que estiver fora das tags é concatenado a String resp a ser retornada
                resp += line.charAt(i);
            }
            i++;
        }
        //System.out.println(resp);
        return resp;
    }
    //método para tratar o nome do arquivo e retornar o nome da série
    public String searchName(String fileName){
        String resp = "";
        for(int i = 0; i < fileName.length(); i++){
            if(fileName.charAt(i)  == '_'){ //caso o caracter na posição i seja igual ao '_' a variável resp recebe um espaço em branco
                resp += ' ';
            } else { //caso não tenha espaço em branco o caracter é concatenado à string resp
                resp += fileName.charAt(i);
            }
        }
        return resp.substring(0, resp.length()-5); //retorno da substring resp retirando os 5 últimos caracteres relacionados à extensão do arquivo
    }
    //método para leitura do arquivo .html e tratamento das linhas
    public void readClass(String fileName){
        String line;
        String resp = "";
        String file = "/tmp/series/" + fileName;
        try {
            FileReader fileReader = new FileReader(file); //declaração da variável fileReader que será recebida pelo bufferedReader

            BufferedReader br = new BufferedReader(fileReader); //declaração do bufferedReader para leitura do arquivo
            
            //set nome da série
            this.name = searchName(fileName);
            
            //set Formato da série
            while(!br.readLine().contains("Formato"));
            this.format = removeTags(br.readLine());

            //set duração da série
            while(!br.readLine().contains("Duração"));
            this.duration = removeTags(br.readLine());

            //set país da série
            while(!br.readLine().contains("País de origem"));
            this.country = removeTags(br.readLine());
            this.country = this.country.trim();

            //set idioma da série
            while(!br.readLine().contains("Idioma original"));
            this.language = removeTags(br.readLine());

            //set emissora da série
            while(!br.readLine().contains("Emissora de televisão"));
            this.broadcaster = removeTags(br.readLine());

            //set transmissão original da série
            while(!br.readLine().contains("Transmissão original"));
            this.streaming = removeTags(br.readLine());

            //set temporadas da série
            while(!br.readLine().contains("N.º de temporadas"));
            this.seasons = justInt(removeTags(br.readLine()));

            //set episódios da série
            while(!br.readLine().contains("N.º de episódios"));
            this.episodes = justInt(removeTags(br.readLine()));
            
            
            //fechamento do bufferedReader
            br.close();         
        //Tratamento de exceções
        } catch(FileNotFoundException e) {
                          
        } catch(IOException e) {
            
        }
    }
}

public class Main {
	//Função que retorna o ponto de parada da leitura.
    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

    public static void main(String[] argas){
        String line;
        Scanner entrada = new Scanner(System.in);
        Hash hash = new Hash();
        long startTime = System.nanoTime(); 
        String input;
        int sum = 0;
  
        do {
           line = entrada.nextLine();
           if (!line.equals("FIM")) {
              try {
                 Serie serie = new Serie();
                 serie.readClass(line);
                 input = serie.getName();
                 for (int i = 0; i < input.length(); i++) {
                    sum += (int) input.charAt(i);
                 }
                 sum = hash.h(sum);
                 if (hash.inserir(sum) == true) {
                    hash.itensInseridos.add(input);
                 }
                 sum = 0;
              } catch (Exception e) {
                 System.out.println(e);
              }
  
           }
        } while (!line.equals("FIM"));
  
        do {
           line = entrada.nextLine();
           if (!line.equals("FIM")) {
              if (hash.itensInseridos.contains(line)) {
                 System.out.println(" SIM");
              } else {
                 try {
                    for (int i = 0; i < line.length(); i++) {
                       sum += (int) line.charAt(i);
                    }
                    sum = hash.h(sum);
                    hash.pesquisar(sum);
                    sum = 0;
                    System.out.println(" NAO");
                 } catch (Exception e) {
                 }
              }
           }
  
        } while (!line.equals("FIM"));
  
        
		long endTime = System.nanoTime(); //Fim do timer
		long duration = (endTime - startTime);  //Cálculo do tempo
        hash.log(duration);
        entrada.close();

    }
}
