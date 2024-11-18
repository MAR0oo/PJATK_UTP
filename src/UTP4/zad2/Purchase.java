/**
 *
 *  @author Osiak Marek S24671
 *
 */

package UTP4.zad2;


public class Purchase {
    private final String id;
    private final String fullName;
    private final String itemName;
    private final double price;
    private final double quantity;

    public Purchase(String inputLine) {
        String[] fields = inputLine.split(";");
        this.id = fields[0];
        this.fullName = fields[1];
        this.itemName = fields[2];
        this.price = Double.parseDouble(fields[3]);
        this.quantity = Double.parseDouble(fields[4]);
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getCost() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return id + ";" + fullName + ";" + itemName + ";" + price + ";" + quantity;
    }
}
