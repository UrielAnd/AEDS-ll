import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int motoristas = sc.nextInt();
        int limite = sc.nextInt();
        do {
            int entrada, saida, menorSaida = 25, menorEntrada = 25, maiorSaida = -1, maiorEntrada = -1;
            for (int i = 0; i < motoristas; i++) {
                entrada = sc.nextInt();
                saida = sc.nextInt();
                if (i == 0) {
                    maiorSaida = saida;
                    menorSaida = saida;
                    menorEntrada = entrada;
                    maiorEntrada = entrada;
                }else if (i == 1) {
                    if (saida < menorSaida) {
                        menorSaida = saida;
                    }if (entrada < menorEntrada) {
                        menorEntrada = entrada;
                    }  if (entrada > maiorEntrada) {
                        maiorEntrada = entrada;
                    }
                }

                if (saida > maiorSaida) {
                    System.out.println("Nao");
                    break;
                } 

                if (saida > menorSaida && saida < maiorSaida) {
                    System.out.println("Sim");
                    break;
                }
            }
            motoristas = sc.nextInt();
            limite = sc.nextInt();
        } while (motoristas != 0 && limite != 0);
        sc.close();
    }
}