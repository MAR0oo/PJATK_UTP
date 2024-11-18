/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP2.zad1;


import java.util.*;


public class Main {

  static List<String> getPricesInPLN(List<String> destinations, double xrate) {
    return ListCreator.collectFrom(destinations)
                       .when(s -> s.startsWith("WAW"))
            .mapEvery(s -> {
              String[] parts = s.split(" ");
              String destination = parts[1];
              int priceInEUR = Integer.parseInt(parts[2]);
              int priceInPLN = (int) (priceInEUR * xrate);
              return "to " + destination + " - price in PLN:\t" + priceInPLN;
            });
  }

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
    for (String r : result) System.out.println(r);
  }
}
