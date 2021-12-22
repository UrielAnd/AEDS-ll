import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

class CCelula {
	public Object item;
    public Object menorItem;
	public CCelula prox;    	
    public CCelula(Object valorItem, Object menor, CCelula proxCelula)
    {
        item = valorItem;
        prox = proxCelula;
        menorItem = menor;
    }    			
    public CCelula(Object valorItem)
    {
        item = valorItem;
        menorItem = valorItem;
        prox = null;
    }    			        	
    public CCelula()
    {
    	item = null;
        prox = null;
        menorItem = 100000;
    }    			        	
}

class CPilha {
	private CCelula topo = null;
    private int menorNum = 50000;

    public CPilha() {
		// nenhum código
	}


	// MODIFIQUE SE FOR NECESSÁRIO
	public void empilha(int valorItem) {

        if(valorItem < menorNum){ menorNum = valorItem;}

		topo = new CCelula(valorItem, menorNum, topo);
	
    }
	
	// MODIFIQUE SE FOR NECESSÁRIO
	public Object desempilha() {
	    Object item = null;
		if (topo != null) {
            menorNum = (int)topo.menorItem;
			item = topo.item;
			topo = topo.prox;
		}


		return item;
	}

	// MODIFIQUE SE FOR NECESSÁRIO
	public Object peek() {
		return (topo != null) ? topo.item : null;
	}

    public boolean vazia() {
		return topo == null;
	}

    public Object peekMin()
    {
        return menorNum;
    }

}

public class Main {

    public static boolean finishProgram(String s){
        
        
        if((s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M') ){ // CHECAR SE FIM FOI ESCRITO
            
            return true;
        }
        
        else{ 
            
        return false; 
            
        }
        
    }


    public static int parser(String string){ // FAZ O PARSE DA STRING
        String output = "";
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) >= '0' && string.charAt(i) <= '9')
            { 
                output += string.charAt(i);
            } 
            else 
            { 
                i = string.length();
            }
        }
        return Integer.parseInt(output); 
    }


    public static void main(String[] args) {

        CPilha pilha = new CPilha();

        while(true)
        { // COMANDOS

            String input = MyIO.readLine();

            if( finishProgram(input) == true)
            {
                break;
            }

            
        if(input.charAt(0) == 'I')
        {
            input = input.substring(2);

            int number = parser(input);

            pilha.empilha(number);

        }

        if(input.charAt(0) == 'D')
        {
            
            System.out.println(pilha.desempilha());

        }

        if(input.charAt(0) == 'T')
        {
            
            System.out.println(pilha.peek());

        }

        if(input.charAt(0) == 'M')
        {
           System.out.println(pilha.peekMin());
        }


        }


        while(true) // DESEMPILHA SE RESTANTE
        {
            if(pilha.vazia() == false)
            {
                int desesmpilhado = (int)pilha.desempilha();
                
                System.out.println(desesmpilhado);
            }

            else{ break;}
        }



    

    }


}