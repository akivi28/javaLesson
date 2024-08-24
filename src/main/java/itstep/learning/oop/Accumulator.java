package itstep.learning.oop;

import java.util.Locale;

public class Accumulator extends Product {
    double capacity;

    public Accumulator(String manufacturer, double capacity) {
        this.setCapacity(capacity);
        super.setManufacturer(manufacturer);
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getCard() {
        return String.format(Locale.ROOT,"Accumulator: '%s', Capacity: %.1f mAh", super.getManufacturer(), this.getCapacity());
    }
}
