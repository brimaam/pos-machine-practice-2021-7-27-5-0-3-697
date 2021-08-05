package pos.machine;

public class Item {
    private String name;
    private int quantity;
    private int unitPrice;
    private int subTotal;

    public Item(String name, int unitPrice, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Item() {

    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }
}
