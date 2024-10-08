package itstep.learning.oop;

public class Pump extends Product implements Manual {
    int productivity;

    public Pump(String manufacturer, int productivity) {
        this.setProductivity(productivity);
        super.setManufacturer(manufacturer);
    }

    public int getProductivity() {
        return productivity;
    }

    public void setProductivity(int productivity) {
        this.productivity = productivity;
    }

    @Override
    public String getCard() {
        return String.format("Pump: '%s', Power: %d l/h", super.getManufacturer(), this.getProductivity());
    }

    @Works("as pump")
    public void pump(){
        System.out.println("Working on: "+ getCard());
    }
}
