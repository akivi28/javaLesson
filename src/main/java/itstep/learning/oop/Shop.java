package itstep.learning.oop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public void run(){
        printProducts();
        System.out.println("--------------------Manual--------------------");
        printManualProducts();
        System.out.println("-------------------Non-Manual--------------------");
        printNonManualProducts();
        System.out.println("-------------------Works--------------------");
        showWorks();
        System.out.println("---------------------Warranty Products--------------------");
        showWarrantyProducts();

    }

    private void printManualProducts(){
        for(Product product : products){
            if(product instanceof Manual){
                System.out.println(product.getCard());
            }
        }
    }

    private void printNonManualProducts(){
        for(Product product : products){
            if(!(product instanceof Manual)){
                System.out.println(product.getCard());
            }
        }
    }

    private void printProducts() {
        for (Product product : products) {
            if (product instanceof Testable) {
                ((Testable) product).test();
            }
            else{
                System.out.println(product.getCard());
            }
        }
    }

    private void showWorks(){
        for(Product product : products){
            for(Method method: product.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Works.class)) {
                    System.out.print( method.getAnnotation(Works.class).value() + " ");
                    method.setAccessible(true);
                    try {
                        method.invoke(product);
                    }
                    catch (IllegalAccessException | InvocationTargetException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    private void showWarrantyProducts(){
        for(Product product : products){
            Warranty warranty = product.getClass().getAnnotation(Warranty.class);
            if(warranty != null){
                System.out.println("Warranty for " + product.getCard()+ " - " + warranty.value() + " year(s)");
            }
        }
    }
}
