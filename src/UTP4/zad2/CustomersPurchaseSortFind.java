/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP4.zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind {
    List<Purchase> purchases;

    public CustomersPurchaseSortFind() {
        purchases = new ArrayList<>();
    }

    public void readFile(String fileName) {
        try {
            Files.lines(Paths.get(fileName))
                    .map(Purchase::new)
                    .forEach(purchases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String sortBy) {
        Comparator<Purchase> comparator;
        if (sortBy.equalsIgnoreCase("Nazwiska")) {
            comparator = Comparator.comparing(Purchase::getFullName)
                    .thenComparing(Purchase::getId);
        } else {
            comparator = Comparator.comparing(Purchase::getCost, Comparator.reverseOrder())
                    .thenComparing(Purchase::getId);
        }

        List<Purchase> sortedPurchases = purchases.stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        System.out.println(sortBy);
        for (Purchase purchase : sortedPurchases) {
            System.out.println(sortBy.equalsIgnoreCase("Koszty") ? purchase.toString() + " (koszt: " + purchase.getCost() + ")" : purchase.toString());
        }
        System.out.println();
    }

    public void showPurchaseFor(String customerId) {
        System.out.println("Klient " + customerId);
        purchases.stream()
                .filter(p -> p.getId().equals(customerId))
                .forEach(System.out::println);
        System.out.println();
    }
}
