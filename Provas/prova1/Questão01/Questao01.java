import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

class CCelula {
	public Object item;
	public Object menorValor;
	public CCelula prox;    	

    public CCelula(Object valorItem, CCelula proxCelula, Object menor)
    {
        item = valorItem;
        prox = proxCelula;
		menorValor = menor;
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

class CPilha {
	private CCelula topo = null;
	private int menor = 900000;
	private int valorInt = 0;

	// MODIFIQUE SE FOR NECESSÁRIO
	public void empilha(Object valorItem) {
		valorInt = (int) valorItem;
		if (valorInt < menor)
			menor = valorInt;
		
		topo = new CCelula(valorItem, topo, menor);
	}
	
	// MODIFIQUE SE FOR NECESSÁRIO
	public Object desempilha() {
		Object item = null;
		if (topo != null) {
			if (topo.prox != null)
				menor = (int) topo.prox.menorValor;
			else 
				menor = (int) topo.menorValor;
			item = topo.item;
			topo = topo.prox;
		}
		return item;
	}

	// MODIFIQUE SE FOR NECESSÁRIO
	public Object peek() {
		return (topo != null) ? topo.item : null;
	}

	public Object getMenor(){
		return menor;
	}

	public boolean verificaVazia() {
		return topo == null;
	}

}

public class Questao01 {

	//Método que retorna o true caso o input seja fim
	public static boolean returnEnd(String fim){
		boolean result;

		result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

		return result;
	}

	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		String[] in = new String[1000]; //Array de inputs
        int inputNumber = 0;
		CPilha pilha = new CPilha();
		String auxValor;
		int valor = 0;

		while(true){
            in [inputNumber] = keyboard.nextLine();
            if(returnEnd(in[inputNumber]) == true){
                inputNumber++;
                break;
            }

			if(in[inputNumber].charAt(0)== 'I'){
				auxValor = in[inputNumber].substring(2);
				valor = Integer.parseInt(auxValor);
				pilha.empilha(valor);
			}
			
			else if(in[inputNumber].charAt(0)== 'D'){
				System.out.println(pilha.desempilha());
			}

			else if(in[inputNumber].charAt(0)== 'T'){
				System.out.println(pilha.peek());
			}

			else if(in[inputNumber].charAt(0)== 'M'){
				System.out.println(pilha.getMenor());
			}
			
            
            inputNumber ++;
        }

		while(true){
			if(pilha.verificaVazia() == false){
                int saida = (int)pilha.desempilha();
                
                System.out.println(saida);
            }
			else	
				break;

		}

		

		keyboard.close();
	}

}