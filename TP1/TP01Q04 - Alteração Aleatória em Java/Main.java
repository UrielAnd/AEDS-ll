import java.util.Random;

public class Main{

    //Função que finaliza o programa
    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

    //Função que substitui as letras
    public static String randomizeString(String input, Random gerador) {
        String result;
        char [] characters = new char [input.length()];

        for(int i = 0; i < input.length(); i++){
            char aux = (char)(input.charAt(i));
            characters[i] = aux;
        }

        char random1 =  ( char ) ( 'a' + (Math. abs ( gerador . nextInt ( ) ) % 26 ) );
        char random2 =  ( char ) ( 'a' + (Math. abs ( gerador . nextInt ( ) ) % 26 ) );
        
        for(int i = 0; i < input.length(); i++){
            
            if(characters[i] == random1)
                characters[i] = random2;
        }
        result = new String(characters);

        return result;
    }

    public static void main(String[] args) {
        
        String[] in = new String[1000];
        String[] out = new String[1000];
        int inputNumber = 0;
        
        Random gerador = new Random(); //Cria o random que é passado pela função.
        gerador.setSeed (4) ;

        do{
            in [inputNumber] = MyIO.readLine();
            out [inputNumber] = randomizeString(in[inputNumber], gerador);
            MyIO.println(out[inputNumber]);
            
        } 
        while (returnEnd(in[inputNumber++]) == false);
    }
}