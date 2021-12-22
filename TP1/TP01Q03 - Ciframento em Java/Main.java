public class Main {
    
    //Função que finaliza o programa
    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

    //Função que faz o ciframento de César.
    public static String encryption(String input) {
        String result;
        char [] characters = new char [input.length()];

        for(int i = 0; i < input.length(); i++){
            char aux = (char)(input.charAt(i) + 3);
            characters[i] = aux;
        }

        result = new String(characters);
        return result;
    }

    public static void main(String[] args) {
        String[] in = new String[1000];
        String[] out = new String[1000];
        int inputNumber = 0;

        do{
            in [inputNumber] = MyIO.readLine();
            out [inputNumber] = encryption(in[inputNumber]); //chamada da fução do ciframento.
            MyIO.println(out[inputNumber]);
            
        } while (returnEnd(in[inputNumber++]) == false);
    }

}
