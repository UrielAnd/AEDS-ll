public class Main {
    
    //Função que finaliza o programa
    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }


    public static String verifyPalidrome(String input) {
        int i = 0;
        int j = input.length() - 1;
        String result = "SIM";

        while (i < j){
            if(input.charAt(i) != input.charAt(j))
                result = "NAO";
            i++;
            j--;
        }   
        return result;
    }

    public static void main(String[] args) {
        String[] in = new String[1000];
        String[] out = new String[1000];
        int inputNumber = 0;

        do{
            in [inputNumber] = MyIO.readLine();
            out [inputNumber] = verifyPalidrome(in[inputNumber]);
            MyIO.println(out[inputNumber]);
            
        } while (returnEnd(in[inputNumber++]) == false);
    }
}