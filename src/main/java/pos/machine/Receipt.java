package pos.machine;

import java.util.List;

public class Receipt {
    private List<Item> itemDetails;
    private int totalPrice;

    public Receipt() {

    }

    public Receipt(List<Item> itemDetails, int totalPrice) {
        this.itemDetails = itemDetails;
        this.totalPrice = totalPrice;
    }

    public List<Item> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<Item> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
