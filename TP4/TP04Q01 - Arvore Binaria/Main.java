import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

class No {
    public Serie elemento; // Conteudo do no.
    public No esq, dir;  // Filhos da esq e dir.

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     */
    public No(Serie elemento) {
        this(elemento, null, null);
    }

    /**
     * Construtor da classe.
     * @param elemento Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(Serie elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class ArvoreBinaria{
    private No raiz; //Raiz da arvore.
    public static int contador;
    public ArvoreBinaria(){
        raiz = null;
    }

    //Método que chama a função recursiva
    public boolean pesquisar(String x){
        System.out.print("raiz ");
        return pesquisar(x, raiz);
    }

    public boolean pesquisar(String s, No n){
        boolean resp;

        if(n == null){
            resp = false;
            System.out.print("NAO");
        }else if (s.compareTo(n.elemento.getName()) == 0){
            contador ++;
            System.out.print("SIM");
            resp = true;
        }else if(s.compareTo(n.elemento.getName()) < 0){
            contador++;
            System.out.print("esq ");
            resp = pesquisar(s, n.esq);
        }else{
            contador++;
            System.out.print("dir ");
            resp = pesquisar(s, n.dir);    
        }
        return resp;
    }

    //Metodo publico iterativo para exibir elementos.
    public void caminharCentral(){
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");   
    }

    //Metodo privado recursivo para exibir elementos.
	private void caminharCentral(No n) {
		if (n != null) {
			caminharCentral(n.esq); // Elementos da esquerda.
			System.out.print(n.elemento + " "); // Conteudo do no.
			caminharCentral(n.dir); // Elementos da direita.
		}
	}

    //Metodo publico iterativo para exibir elementos.
    public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

    //Metodo privado recursivo para exibir elementos.
    private void caminharPre(No n) {
		if (n != null) {
			System.out.print(n.elemento + " "); // Conteudo do no.
			caminharPre(n.esq); // Elementos da esquerda.
			caminharPre(n.dir); // Elementos da direita.
		}
	}

    //Metodo publico iterativo para exibir elementos.
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}
    
    //Metodo privado recursivo para exibir elementos.
	private void caminharPos(No n) {
		if (n != null) {
			caminharPos(n.esq); // Elementos da esquerda.
			caminharPos(n.dir); // Elementos da direita.
			System.out.print(n.elemento + " "); // Conteudo do no.
		}
	}

    //Metodo publico iterativo para inserir elemento.
	public void inserir(Serie s) throws Exception {
		raiz = inserir(s, raiz);
	}
    
    // Metodo privado recursivo para inserir elemento.
	private No inserir(Serie s, No n) throws Exception {
		if (n == null) {
         n = new No(s);
        }else if(s.getName().compareTo(n.elemento.getName()) < 0) {
         n.esq = inserir(s, n.esq);
        }else if (s.getName().compareTo(n.elemento.getName()) > 0) {
         n.dir = inserir(s, n.dir);
      }else {
         throw new Exception("Erro ao inserir!");
      }
		return n;
	}  

    //Metodo publico para inserir elemento.
	public void inserirPai(Serie s) throws Exception {
        if(raiz == null){
           raiz = new No(s);
          } else if (s.getName().compareTo(raiz.elemento.getName()) < 0) {
             inserirPai(s, raiz.esq, raiz);
          } else if (s.getName().compareTo(raiz.elemento.getName()) > 0) {
             inserirPai(s, raiz.dir, raiz);
        } else {
           throw new Exception("Erro ao inserirPai!");
        }
    }

    //Metodo privado recursivo para inserirPai elemento.
	private void inserirPai(Serie s, No n, No pai) throws Exception {
		if (n == null) {
         if(s.getName().compareTo(pai.elemento.getName()) < 0){
            pai.esq = new No(s);
         } else {
            pai.dir = new No(s);
         }
      } else if (s.getName().compareTo(n.elemento.getName()) < 0) {
         inserirPai(s, n.esq, n);
      } else if (s.getName().compareTo(n.elemento.getName()) > 0) {
         inserirPai(s, n.dir, n);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}
    
    //Metodo publico iterativo para remover elemento.
	public void remover(Serie s) throws Exception {
		raiz = remover(s, raiz);
	}   

    //Metodo privado recursivo para remover elemento.
	private No remover(Serie s, No n) throws Exception {

		if (n == null) {

      } else if (s.getName().compareTo(n.elemento.getName()) < 0) {
        contador++;
         n.esq = remover(s, n.esq);
      } else if (s.getName().compareTo(n.elemento.getName()) > 0) {
        contador++;
         n.dir = remover(s, n.dir);
      // Sem no a direita.
      } else if (n.dir == null) {
        contador++;
         n = n.esq;
      // Sem no a esquerda.
      } else if (n.esq == null) {
        contador++;
         n = n.dir;
      // No a esquerda e no a direita.
      } else {
        n.esq = maiorEsq(n, n.esq);
		}
		return n;
	} 

    //Metodo para trocar o elemento "removido" pelo maior da esquerda.
    private No maiorEsq(No i, No j) {

        // Encontrou o maximo da subarvore esquerda.
          if (j.dir == null) {
              i.elemento = j.elemento; // Substitui i por j.
              j = j.esq; // Substitui j por j.ESQ.
  
        // Existe no a direita.
          } else {
           // Caminha para direita.
              j.dir = maiorEsq(i, j.dir);
          }
          return j;
    }

    //Metodo que retorna o maior elemento da árvore
    public Serie getMaior(){
        Serie resp = new Serie();
  
        if(raiz != null){
           No n;
           for(n = raiz; n.dir != null; n = n.dir);
           resp = n.elemento;
        }
  
        return resp;
    }

    public Serie getMenor(){
        Serie resp = new Serie();
    
          if(raiz != null){
             No n;
             for(n = raiz; n.esq != null; n = n.esq);
             resp = n.elemento;
          }
    
          return resp;
       }
    
    //Metodo que retorna a altura da árvore
    public int getAltura(){
        return getAltura(raiz, 0);
    }
    
    //Metodo que retorna a altura da árvore
    public int getAltura(No n, int altura){
        if(n == null){
            altura--;
        } else {
            int alturaEsq = getAltura(n.esq, altura + 1);
            int alturaDir = getAltura(n.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    }
    
     //Metodo publico iterativo para remover elemento.
    public void remover2(String s) throws Exception {
        if (raiz == null) {
            throw new Exception("Erro ao remover2!");
        } else if(s.compareTo(raiz.elemento.getName()) < 0){
            remover2(s, raiz.esq, raiz);
        } else if (s.compareTo(raiz.elemento.getName()) > 0){
            remover2(s, raiz.dir, raiz);
        } else if (raiz.dir == null) {
            raiz = raiz.esq;
        } else if (raiz.esq == null) {
            raiz = raiz.dir;
        } else {
            raiz.esq = maiorEsq(raiz, raiz.esq);
        }
    }
    
    //Metodo privado recursivo para remover elemento.
    private void remover2(String s, No n, No pai) throws Exception {
        if (n == null) {
            throw new Exception("Erro ao remover2!");
        } else if (s.compareTo(n.elemento.getName()) < 0) {
            remover2(s, n.esq, n);
        } else if (s.compareTo(n.elemento.getName()) > 0) {
            remover2(s, n.dir, n);
        } else if (n.dir == null) {
            pai = n.esq;
        } else if (n.esq == null) {
            pai = n.dir;
        } else {
            n.esq = maiorEsq(n, n.esq);
        }
    }
    
    public Serie getRaiz() throws Exception {
        return raiz.elemento;
    }

    public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
        return igual(a1.raiz, a2.raiz);
    }
    
    private static boolean igual (No n1, No n2){
        boolean resp;
        if(n1 != null && n2 != null){
            resp = (n1.elemento == n2.elemento) && igual(n1.esq, n2.esq) && igual(n1.dir, n2.dir);
        } else if(n1 == null && n2 == null){
            resp = true;
        } else {
            resp = false; 
        }
        return resp;
    }

    static void log(long time){ 

        try {
            File myObj = new File("matrícula_arvoreBinaria.txt");
            if (myObj.createNewFile()) {
            } else {
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
          try {
            FileWriter myWriter = new FileWriter("matrícula_arvoreBinaria.txt");
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

	public static void main(String args[])  throws Exception{

		Scanner teclado = new Scanner(System.in);
		long startTime = System.nanoTime(); 
        int contaodrDeRepeticoes = 0;
        String input;
        int i = 0;
        int breakpoint = 0;
        ArvoreBinaria arvore = new ArvoreBinaria();

        //Leitura do pub.in
		while(true){
            Serie serie = new Serie();
			input = teclado.nextLine();
            if(returnEnd(input) == true)
                break;
            serie.readClass(input);
            arvore.inserir(serie);
		}		

        input = teclado.nextLine();
        breakpoint = Integer.parseInt(input);
	
        for(i = 0; i < breakpoint  ; i++){
            Serie serie = new Serie();
            input = teclado.nextLine();

            if(input.charAt(0) == 'I'){
                input = input.substring(2);
                serie.readClass(input);
                arvore.inserir(serie);
            }

            if(input.charAt(0) == 'R'){
                input = input.substring(2);
                Serie serie2 = new Serie();
                input = input + ".html";
                input = input.replace(" ", "_");
                serie2.readClass(input);
                arvore.remover(serie2);
            }
        }

        while(true){
            input = teclado.nextLine();
            if(returnEnd(input) == true){
                break; 
            }
            arvore.pesquisar(input);
            System.out.print("\n");
        }

		long endTime = System.nanoTime(); //Fim do timer
		long duration = (endTime - startTime);  //Cálculo do tempo

        arvore.log(duration);
		teclado.close();
    }
}
