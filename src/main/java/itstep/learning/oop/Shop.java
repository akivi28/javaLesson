package itstep.learning.oop;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<Product> products;

    public Shop() {
        products = new ArrayList<Product>();
        products.add(new Lamp("Philips",60.0));
        products.add(new Pump("Pumper",100));
        products.add(new Accumulator("Duracell", 50));
    }

    public void printProducts() {
        for (Product product : products) {
            System.out.println(product.getCard());
        }
    }
}
