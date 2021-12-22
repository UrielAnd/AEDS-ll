import java.io.*;
import java.util.Scanner;

class Serie{

    String nome, formato, duracao, 
    pais, idioma, emissora, transmissao,
    temporadas, episodios;

    Serie(){
        nome = "";
        formato = "";
        duracao = "";
        pais = "";
        idioma = "";
        emissora = "";
        transmissao = "";
        temporadas = "";
        episodios = "";
    }

    Serie Clone(){
        Serie resp = new Serie();
        resp.nome = this.nome;

        return resp;
    }
    
    public String removeTags(String line){
        String newline = "";
        int i = 0;
        while(i < line.length()){
    
        if(line.charAt(i) == '<'){
        i++;
        while(line.charAt(i) != '>') i++;
        } else {
        newline += line.charAt(i);
        }
        i++;
    
    
    
        }
        newline = newline.replaceAll("&nbsp;", "");
        newline = newline.replaceAll("&#160;", "");
        newline = newline.trim(); 
    
    
        newline = newline.replaceAll("(lista de episódios)" , "");
        newline = newline.replaceAll("(Lista de episódios)" , "");
        newline = newline.replaceAll("(até o momento)" , "");
    
        return newline;
    }

    public void ler (String nomeArquivo) throws Exception{
        InputStreamReader isr = new InputStreamReader(new FileInputStream(nomeArquivo));

        BufferedReader br = new BufferedReader(isr);

        while(!br.readLine().contains("infobox_v2"));
        br.readLine();
        this.nome = removeTags(br.readLine());

        while(!br.readLine().contains("Formato"));
        this.formato = removeTags(br.readLine());

        while(!br.readLine().contains("Duração"));
        this.duracao = removeTags(br.readLine());

        while(!br.readLine().contains("País de origem"));
        this.pais = removeTags(br.readLine());

        while(!br.readLine().contains("Idioma original"));
        this.idioma = removeTags(br.readLine());

        while(!br.readLine().contains("Emissora de televisão original"));
        this.emissora = removeTags(br.readLine());

        while(!br.readLine().contains("Transmissão original"));
        this.transmissao = removeTags(br.readLine());

        br.readLine();
        br.readLine();
        br.readLine();
        this.temporadas = removeTags(br.readLine());

        br.readLine();
        br.readLine();
        br.readLine();
        this.episodios = removeTags(br.readLine());

        System.out.println(this.nome + " " + this.formato + " " + this.duracao + " " + this.pais + " " + this.idioma + " " + this.emissora + " " + this.transmissao + " " + this.temporadas + " " + this.episodios);

        br.close();
    }

}
public class Main{

    public static boolean returnEnd(String fim){
        boolean result;

        result = (fim.length() == 3 && fim.charAt(0) == 'F' && fim.charAt(1) == 'I' && fim.charAt(2) == 'M');

        return result;
    }

    public static void main(String [] args){
        Scanner keyboard = new Scanner(System.in);

        Serie serie = new Serie();
        String[] in = new String[1000];
        int inputNumber = 0;

        do{
            String basis = "/tmp/series/";
            in [inputNumber] = keyboard.nextLine();
            basis += in[inputNumber];
            try{
                serie.ler(basis);
            } catch (Exception e){ }

        }while(returnEnd(in[inputNumber++]) == false);

        keyboard.close();
    }

}