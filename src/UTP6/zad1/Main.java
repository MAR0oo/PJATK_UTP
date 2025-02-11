/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP6.zad1;


import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args)  {
    File dataDir = new File("data");
    TravelData travelData = new TravelData(dataDir);
    String dateFormat = "yyyy-MM-dd";
    for (String locale : Arrays.asList("pl_PL", "en_GB")) {
      List<String> odlist = travelData.getOffersDescriptionsList(locale, dateFormat);
      for (String od : odlist) System.out.println(od);
    }
    // --- część bazodanowa
    String url = "jdbc:mysql://localhost:3306/jbcutp?user=root";
    Database db = new Database(url, travelData);
    db.create();
    db.showGui();
  }
}
