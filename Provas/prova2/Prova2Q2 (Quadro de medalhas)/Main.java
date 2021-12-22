import java.util.Scanner;

class Country {
  //Atributos
   private String name;
   private int gold, silver, bronze, total;

   Country(String name, int gold, int silver, int bronze) {
      this.name = name;
      this.gold = gold;
      this.silver = silver;
      this.bronze = bronze;
      this.total = gold + silver + bronze;
   }
   public String getName() {
      return name;
   }
   public int getBronze() {
      return bronze;
   }
   public int getSilver() {
        return silver;
   }
   public int getGold() {
      return gold;
   }
   public int getTotal() {
      return total;
   }
   public void setGold(int gold) {
      this.gold = gold;
   }
   public void setSilver(int silver) {
      this.silver = silver;
   }
   public void setBronze(int bronze) {
      this.bronze = bronze;
   }
   public void setTotal(int total) {
      this.total = total;
   }
}

public class Main {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Country[] countries = new Country[500];
      int inputCounter = 0;

      inputCounter = Integer.parseInt(scanner.nextLine());
      
      for (int i = 0; i < inputCounter; i++) {
        String in[] = new String[4];
        in = scanner.nextLine().split(" ");
        countries[i] = new Country(in[0], Integer.parseInt(in[1]), Integer.parseInt(in[2]), Integer.parseInt(in[3]));
      }
      
      for (int i = 0; i < inputCounter; i++) {
         for (int j = 0; j < inputCounter; j++) {
            if (countries[i].getTotal() > countries[j].getTotal()) {
               Country aux = countries[i];
               countries[i] = countries[j];
               countries[j] = aux;
            }

            if (countries[i].getGold() > countries[j].getGold()) {
               Country aux = countries[i];
               countries[i] = countries[j];
               countries[j] = aux;
            } 

            else if (countries[i].getGold() == countries[j].getGold()) {
               if (countries[i].getSilver() > countries[j].getSilver()) {
                  Country aux = countries[i];
                  countries[i] = countries[j];
                  countries[j] = aux;
               } 

               else if (countries[i].getSilver() == countries[j].getSilver()) {
                  if (countries[i].getBronze() > countries[j].getBronze()) {
                     Country aux = countries[i];
                     countries[i] = countries[j];
                     countries[j] = aux;
                    }
               }
            }
         }
      }

      for (int i = 0; i < inputCounter; i++) {
         System.out.println(countries[i].getName() + " " + countries[i].getGold() + " " + countries[i].getSilver() + " " + countries[i].getBronze());
      }
      scanner.close();
   }
}