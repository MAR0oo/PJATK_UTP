/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP2.zad2;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

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
    List<String> result = dest.stream()
            .filter(s -> s.startsWith("WAW "))
            .map(s -> {
                String[] flightInfo = s.split(" ");
                String destination = flightInfo[1];
                double pricePLN = Double.parseDouble(flightInfo[2]) * ratePLNvsEUR;
                return "to " + destination + " - price in PLN:\t" + (int) pricePLN;
            })
            .collect(Collectors.toList());

    for (String r : result) System.out.println(r);
  }
}
