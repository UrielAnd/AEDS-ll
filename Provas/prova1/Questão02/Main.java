import java.io.*;
import java.io.FileReader;
import java.io.File;  
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static boolean returnEnd(String fim){
		boolean result;

		result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

		return result;
	}

    public static void main (String [] args){
        Scanner keyboard = new Scanner(System.in);
        String[] in = new String[1000]; //Array de inputs
        int inputNumber = 0;


		while(true){
            int counter1 = 0; //Conta os "("
            int counter2 = 0; //Conta os ")"
            in [inputNumber] = keyboard.nextLine();

            if(returnEnd(in[inputNumber]) == true){
                inputNumber++;
                break;
            }

            for(int i = 0; i < in[inputNumber].length(); i++){
                if(in[inputNumber].charAt(i) == '(')
                    counter1++;

                else if(in[inputNumber].charAt(i) == ')')
                    counter2++;

                if (counter2 > counter1){ //Quebra a verificação caso um ")" apareça antes de "("
                    break;
                }
            }

            if(counter1 == counter2)
                System.out.println("CORRETO");
            else
                System.out.println("INCORRETO");
            
            inputNumber ++;
        }
        keyboard.close();
    }

}
