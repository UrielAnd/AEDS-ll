import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

class CCelula{
	public Object item;
	public CCelula prox;    	
    public CCelula(Object valorItem, CCelula proxCelula)
    {
        item = valorItem;
        prox = proxCelula;
    }    			
    public CCelula(Object valorItem)
    {
        item = valorItem;
        prox = null;
    }    			        	
    public CCelula()
    {
    	item = null;
        prox = null;
    }    			        	
}

/**
 * 
 * @author Rodrigo Richard Gomes
 * @version 1.00 2018/3/16
 * 
 */
class CFila {
	private CCelula frente; // Celula cabeca.
	private CCelula tras; // Ultima celula.
	private int qtde;

	/**
	 * Fun��o construtora. Cria a c�lula cabe�a e faz as refer�ncias frente e tras
	 * apontarem para ela.
	 */
	public CFila() {
		frente = new CCelula();
		tras = frente;
	}

	/**
	 * Verifica se a fila est� vazia.
	 * 
	 * @return Retorna TRUE se a fila estiver vazia e FALSE caso contr�rio.
	 */
	public boolean vazia() {
		return frente == tras;
	}

	/**
	 * Imprime os elementos da fila
	 */
	public void mostra() {
		System.out.print("[ ");
		for (CCelula c = frente.prox; c != null; c = c.prox)
			System.out.print(c.item + " ");
		System.out.println("] ");
	}

	/**
	 * Insere um novo Item no fim da fila.
	 * 
	 * @param valorItem O parametro "valorItem" � um Object contendo o elemento a
	 *                  ser inserido no final da fila.
	 */
	public void enfileira(Object valorItem) {
		tras.prox = new CCelula(valorItem);
		tras = tras.prox;
		qtde++;
	}

	public void enfileiraDuplicado(Object valorItem)
	{
		//Faz o primeiro enfileiramento
		tras.prox = new CCelula(valorItem);
		tras = tras.prox;
		qtde++;

		String[] valores = new String[50];
		int contador = 0;
		CCelula celula;
		Object novaCelula = null;

		valores[contador++] = (String)valorItem;

		for (celula = frente.prox; celula != null; celula = celula.prox) {
			valores[contador] = (String)celula.item;
			contador++;
		}

		int novoTamanho = qtde + 1;

		for(int i = qtde; i >= 0; i--) {
			if (frente != tras) {
				frente = frente.prox;
				novaCelula = frente.item;
				qtde--;
			}
		}

		for(int i = 0; i < novoTamanho; i++ ){
			String valorDosElementos = valores[i];
			tras.prox = new CCelula(valorDosElementos);
			tras = tras.prox;
			qtde++;
		}
	}

	/**
	 * Retira e retorna o primeiro elemento da fila.
	 * 
	 * @return Retorna um Object contendo o primeiro elemento da fila. Caso a fila
	 *         esteja vazia retorna null.
	 */
	public Object desenfileira() {
		Object item = null;
		if (frente != tras) {
			frente = frente.prox;
			item = frente.item;
			qtde--;
		}
		return item;
	}
		
	// Id�ntico ao m�todo anterior, mas sem usar a vari�vel tempor�ria item
	public Object desenfileirav2() {
		if (frente != tras) {
			frente = frente.prox;
			qtde--;
			return frente.item;
		}
		return null;
	}
	

	/**
	 * Retorna o primeiro Item da fila sem remove-lo.
	 * 
	 * @return Retorna um Object contendo o primeiro Item da fila.
	 */
	public Object peek() {
		if (frente != tras)
			return frente.prox.item;
		else
			return null;
		//return (frente != tras)? frente.prox.item : null;
	}

	/**
	 * Verifica se o Item passado como parametro esta contido na fila.
	 * 
	 * @param valorItem O parametro "valorItem" e um object contendo o Item a ser
	 *                  localizado.
	 * @return O m�todo retorna TRUE caso o item esteja presente na fila.
	 */
	public boolean contem(Object valorItem) {
		CCelula aux = frente.prox;
		while (aux != null) {
			if (aux.item.equals(valorItem))
				return true;
			aux = aux.prox;
		}
		return false;
	}

	/**
	 * Verifica se o Item passado como parametro esta contido na fila. (Obs: usa o
	 * comando FOR)
	 * 
	 * @param (valorItem) Recebe como parametro um object contendo o Item a ser
	 *                    localizado.
	 * @return Retorna TRUE caso o Item esteja presente na fila.
	 */
	public boolean contemFor(Object valorItem) {
		for (CCelula aux = frente.prox; aux != null; aux = aux.prox)
			if (aux.item.equals(valorItem))
				return true;
		return false;
	}

	/**
	 * Metodo que retorna a quantidade de itens da fila.
	 * 
	 * @return
	 */
	public int quantidade() {
		return qtde;
	}

}

public class Main {
    //Método que retorna o true caso o input seja fim
	public static boolean returnEnd(String fim){
		boolean result;

		result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

		return result;
	}
	public static void main(String[] args){
		CFila fila = new CFila();
		Scanner keyboard = new Scanner(System.in);

		while(true){
			String in = keyboard.nextLine();
			if(returnEnd(in) == true)
				break;

			if(in.charAt(0) == 'I'){
				in = in.substring(2);
				fila.enfileira(in);
			}

			if(in.charAt(0) == 'D'){
				in = in.substring(2);
				fila.enfileiraDuplicado(in);
			}
		}

		while (true){
			String output = (String) fila.desenfileira();
			if(output == null){
				break;
			}
			System.out.println(output);
		}

		keyboard.close();
	}
}
