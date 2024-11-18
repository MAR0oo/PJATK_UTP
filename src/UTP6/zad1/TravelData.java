package UTP6.zad1;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TravelData {
    private List<Travel> list;

    public TravelData(File dataDir) {
        list = new ArrayList<>();
        readFromDirectory(dataDir);
    }

    private void readFromDirectory(File dataDir) {
        File[] files = dataDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    readFromFile(file);
                }
            }
        }
    }

    private void readFromFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split("\t");
                if (arr.length == 7) {
                    list.add(new Travel(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public List<String> getOffersDescriptionsList(String loc, String dateFormat) {
        List<String> result = new ArrayList<>();
        Locale locale = Locale.forLanguageTag(loc.replace("_", "-"));
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        SimpleDateFormat format = new SimpleDateFormat(dateFormat, locale);

        for (Travel travel : list) {
            String offerDescription = Dictionary.translateCountryName(travel.getLoc(), locale, travel.getCountry()) + " " +
                    format.format(travel.getDateFrom()) + " " +
                    format.format(travel.getDateTo()) + " " +
                    Dictionary.translateWord(travel.getLoc(), locale, travel.getPlace()) + " " +
                    numberFormat.format(travel.getPrice()) + " " +
                    travel.getCurrency();
            result.add(offerDescription);
        }
        return result;
    }

    public List<Travel> getList() {
        return list;
    }
}
