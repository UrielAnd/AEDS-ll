import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

//Classe lista feita pelo professor Rodrigo Richard
class Lista {
	private String[] array;
	private int n;
    private int compareCounter;

	/**
	 * Construtor da classe.
	 */
	public Lista() {
		this(6);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param tamanho Tamanho da lista.
	 */
	public Lista(int tamanho) {
		array = new String[tamanho];
		n = 0;
	}

	/**
	 * Insere um elemento na primeira posicao da lista e desloca os demais elementos
	 * para o fim da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirInicio(String item) {
		if (n < array.length) {
			// Desloca elementos para o fim do array
			for (int i = n; i > 0; i--)
				array[i] = array[i - 1];

			array[0] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Elemento a ser inserido.
	 */
	public boolean inserirFim(String item) {
		// validar insercao
		if (n < array.length) {
			array[n] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Insere um elemento em uma posicao especifica e move os demais elementos para
	 * o fim da lista.
	 * 
	 * @param item: elemento a ser inserido.
	 * @param pos:  Posicao de insercao.
	 */
	public boolean inserir(String item, int pos) {

		// validar insercao
		if (n < array.length && pos >= 0 && pos <= n) {
			// Desloca elementos para o fim do array
			for (int i = n; i > pos; i--)
				array[i] = array[i - 1];

			array[pos] = item;
			n++;
			return true;
		}
		return false;
	}

	/**
	 * Remove um elemento da primeira posicao da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @return Elemento a ser removido.
	 */
	public String removerInicio() {
		if (n > 0) {
			String item = array[0];
			n--;

			for (int i = 0; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * 
	 * @return Elemento a ser removido.
	 */
	public String removerFim() {
		if (n > 0)
			return array[--n];
		return null;
	}

	/**
	 * Remove um elemento de uma posicao especifica da lista e movimenta os demais
	 * elementos para o inicio da mesma.
	 * 
	 * @param pos: Posicao de remocao.
	 * @return Elemento a ser removido.
	 */
	public String remover(int pos) {
		if (n > 0 && pos >= 0 && pos < n) {
			String item = array[pos];
			n--;

			for (int i = pos; i < n; i++)
				array[i] = array[i + 1];

			return item;
		}
		return null;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
		System.out.print("[ ");
		for (int i = 0; i < n; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("]");
	}

    public boolean pesquisar(String item) {

        int fim = n -1, meio = (fim) / 2;

        int inicio = 0, stop = 0, caractere = 0;

        while (inicio <= fim) {

            stop++;

			if( array[meio].equals(item) ){
				compareCounter++; 
				return true;
			}

			if((int)(item.charAt(caractere)) == (int)(array[meio].charAt(caractere)) ) 
				caractere++;

			else
			{
				if( item.compareTo(array[meio]) > 0) 
					inicio = meio + 1; meio = (fim + inicio) / 2; compareCounter++;   
				if( item.compareTo(array[meio]) < 0 ) 
					 fim = meio - 1;  meio = (fim + inicio) / 2; compareCounter++;    
			}
			if(stop == 100)
				break; 
		}

		return false;
	}

	//Método para criar um txt
    public void matricula(long time){ 

        try {
            File file = new File("matrícula_sequencial.txt");
            if (file.createNewFile()) {
            } else { }
        } catch (IOException e) {
            e.printStackTrace();
          }

		try {
		FileWriter input = new FileWriter("sequencial.txt");
		input.write("728764" + "\t" + time + "\t" + compareCounter);
		input.close();
		} catch (IOException e) {
		e.printStackTrace();
		}

    }
}

public class Main{

	//Função que retorna o ponto de parada da leitura.
    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

	public static void main(String args[]){

		Scanner teclado = new Scanner(System.in);
		Lista listaDeNomes = new Lista(50);
		//Inicialização do timer de execução
		long cronometro = System.nanoTime();

		String in = "";
		String input = "";
	
		while(true){
			//Transforma o nome dos arquivos hmtl em nomes de séries
			in = teclado.nextLine();
			in = in.replaceAll("_", " ");
			in = in.replaceAll(".html", ""); 
	
			if(returnEnd(in) == true)
				break; 
			
			listaDeNomes.inserirFim(in);
	
		}		
	
		while(true){
		input = teclado.nextLine();
		if(returnEnd(input) == true)
			break; 
		
		if( listaDeNomes.pesquisar(input) == true) 
			System.out.println("SIM");
		else 
			System.out.println("NÃO");
	
		} 

		long endTime = System.nanoTime(); //Fim do timer
		long duration = (endTime - cronometro); //Cálculo do tempo
		listaDeNomes.matricula(duration);

		teclado.close();
    }

}