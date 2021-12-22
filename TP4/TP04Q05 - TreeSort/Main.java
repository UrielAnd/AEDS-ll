import java.io.*;
import java.io.FileReader;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

class ArvoreBinaria {
	private No raiz; // Raiz da arvore.
    public static int compareCounter;
    private int number;

	/**
	 * Construtor da classe.
	 */
	public ArvoreBinaria() {
		raiz = null;
        number = 0;
	}

	/**
	 * Metodo publico iterativo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String x) {
        System.out.print("raiz ");
		return pesquisar(x, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar elemento.
	 * @param x Elemento que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String x, No i) {

        if(i != null){
        // System.out.println(x + " " + i.elemento.getName());
        }

      boolean resp;
		if (i == null) {
         resp = false;
         System.out.print("NAO");

      } else if (x.compareTo(i.elemento.getName()) == 0 ) {
          compareCounter++;
        //   System.out.println(x + " " + i.elemento.getName() + " ACHOU");
         resp = true;
         System.out.print("SIM");

      } else if (x.compareTo(i.elemento.getName()) < 0) {
        compareCounter++;
        System.out.print("esq ");
         resp = pesquisar(x, i.esq);

      } else {
        compareCounter++;
        System.out.print("dir ");
         resp = pesquisar(x, i.dir);
      }
      return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i No em analise.
	 */
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserir(Serie x) throws Exception {
        number++;
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o elemento existir.
	 */
	private No inserir(Serie x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

        } else if (x.getName().compareTo(i.elemento.getName()) < 0) {
         i.esq = inserir(x, i.esq);

        } else if (x.getName().compareTo(i.elemento.getName()) > 0) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

		return i;
	}

	/**
	 * Metodo publico para inserir elemento.
	 * @param x Elemento a ser inserido.
	 * @throws Exception Se o elemento existir.
	 */
	public void inserirPai(Serie x) throws Exception {
      if(raiz == null){
         raiz = new No(x);
        } else if (x.getName().compareTo(raiz.elemento.getName()) < 0) {
		   inserirPai(x, raiz.esq, raiz);
        } else if (x.getName().compareTo(raiz.elemento.getName()) > 0) {
		   inserirPai(x, raiz.dir, raiz);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}

	/**
	 * Metodo privado recursivo para inserirPai elemento.
	 * @param x Elemento a ser inserido.
	 * @param i No em analise.
	 * @param pai No superior ao em analise.
	 * @throws Exception Se o elemento existir.
	 */
	private void inserirPai(Serie x, No i, No pai) throws Exception {
		if (i == null) {
         if(x.getName().compareTo(pai.elemento.getName()) < 0){
            pai.esq = new No(x);
         } else {
            pai.dir = new No(x);
         }
      } else if (x.getName().compareTo(i.elemento.getName()) < 0) {
         inserirPai(x, i.esq, i);
      } else if (x.getName().compareTo(i.elemento.getName()) > 0) {
         inserirPai(x, i.dir, i);
      } else {
         throw new Exception("Erro ao inserirPai!");
      }
	}


	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover(Serie x) throws Exception {
		raiz = remover(x, raiz);
	}

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private No remover(Serie x, No i) throws Exception {

		if (i == null) {


      } else if (x.getName().compareTo(i.elemento.getName()) < 0) {
        compareCounter++;
         i.esq = remover(x, i.esq);

      } else if (x.getName().compareTo(i.elemento.getName()) > 0) {
        compareCounter++;
         i.dir = remover(x, i.dir);

      // Sem no a direita.
      } else if (i.dir == null) {
        compareCounter++;
         i = i.esq;

      // Sem no a esquerda.
      } else if (i.esq == null) {
        compareCounter++;
         i = i.dir;

      // No a esquerda e no a direita.
      } else {
         i.esq = maiorEsq(i, i.esq);
		}

		return i;
	}

	/**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
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

	/**
	 * Metodo que retorna o maior elemento da árvore
	 * @return int maior elemento da árvore
	 */
   public Serie getMaior(){
      Serie resp = new Serie();

      if(raiz != null){
         No i;
         for(i = raiz; i.dir != null; i = i.dir);
         resp = i.elemento;
      }

      return resp;
   }


	/**
	 * Metodo que retorna o menor elemento da árvore
	 * @return int menor elemento da árvore
	 */
   public Serie getMenor(){
    Serie resp = new Serie();

      if(raiz != null){
         No i;
         for(i = raiz; i.esq != null; i = i.esq);
         resp = i.elemento;
      }

      return resp;
   }


	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
   public int getAltura(){
      return getAltura(raiz, 0);
   }


	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
   public int getAltura(No i, int altura){
      if(i == null){
         altura--;
      } else {
         int alturaEsq = getAltura(i.esq, altura + 1);
         int alturaDir = getAltura(i.dir, altura + 1);
         altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
      }
      return altura;
   }


	/**
	 * Metodo publico iterativo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @throws Exception Se nao encontrar elemento.
	 */
	public void remover2(String x) throws Exception {
      if (raiz == null) {
         throw new Exception("Erro ao remover2!");
      } else if(x.compareTo(raiz.elemento.getName()) < 0){
         remover2(x, raiz.esq, raiz);
      } else if (x.compareTo(raiz.elemento.getName()) > 0){
         remover2(x, raiz.dir, raiz);
      } else if (raiz.dir == null) {
         raiz = raiz.esq;
      } else if (raiz.esq == null) {
         raiz = raiz.dir;
      } else {
         raiz.esq = maiorEsq(raiz, raiz.esq);
      }
   }

	/**
	 * Metodo privado recursivo para remover elemento.
	 * @param x Elemento a ser removido.
	 * @param i No em analise.
	 * @param pai do No em analise.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private void remover2(String x, No i, No pai) throws Exception {
		if (i == null) {
         throw new Exception("Erro ao remover2!");
      } else if (x.compareTo(i.elemento.getName()) < 0) {
         remover2(x, i.esq, i);
      } else if (x.compareTo(i.elemento.getName()) > 0) {
         remover2(x, i.dir, i);
      } else if (i.dir == null) {
         pai = i.esq;
      } else if (i.esq == null) {
         pai = i.dir;
      } else {
         i.esq = maiorEsq(i, i.esq);
		}
	}

   public Serie getRaiz() throws Exception {
      return raiz.elemento;
   }

   public static boolean igual (ArvoreBinaria a1, ArvoreBinaria a2){
      return igual(a1.raiz, a2.raiz);
   }

   private static boolean igual (No i1, No i2){
      boolean resp;
      if(i1 != null && i2 != null){
         resp = (i1.elemento == i2.elemento) && igual(i1.esq, i2.esq) && igual(i1.dir, i2.dir);
      } else if(i1 == null && i2 == null){
         resp = true;
      } else {
         resp = false; 
      }
      return resp;
   }

   public Serie[] sort() {
    Serie[] a = new Serie[number];
    number = 0;
    sort(raiz, a);
    return a;
}

// Metodo recursivo para ordenar o array
private void sort(No i, Serie[] a) {
    if (i != null) {
        sort(i.esq, a);
        compareCounter++;
        a[number++] = i.elemento;
        sort(i.dir, a);
    }
}

   static void log(long time){ // GERADOR DE ARQUIVO DE LOG

    try {
        File myObj = new File("matrícula_treesort.txt");
        if (myObj.createNewFile()) {
        } else {
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        FileWriter myWriter = new FileWriter("matrícula_treesort.txt");
        myWriter.write("728764" + "\t" + time + "\t" + compareCounter);
        myWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

}
}



/**
 * No da arvore binaria
 * @author Max do Val Machado
 */

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

    public String printName(){
        return this.name;
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
            
            //método para mostrar a classe
            //fechamento do bufferedReader
            br.close();         
        //Tratamento de exceções
        } catch(FileNotFoundException e) {
            System.out.println("Unable to open file '" + fileName + "'");                
        } catch(IOException e) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }
}       



public class Main{

    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

	public static void main(String args[]) throws Exception{

    Long startTime = System.nanoTime();
    int n = 0;
    int NumberOperations = 0;
	ArvoreBinaria arvore = new ArvoreBinaria();

    while(true){
        Serie serie = new Serie();
        String input2 = MyIO.readLine();
        if(returnEnd(input2) == true){
            break; 
        }
        serie.readClass(input2);
        arvore.inserir(serie);
    }

    Serie[] a = arvore.sort();
    for(int i = 0; i < a.length; i++){
        a[i].printClass();
    }

    long stopTime = System.nanoTime();
    long duration = stopTime - startTime;
    arvore.log(duration);

    }
}