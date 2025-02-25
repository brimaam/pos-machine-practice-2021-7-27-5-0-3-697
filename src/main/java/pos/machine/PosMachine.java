package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {

    public String printReceipt(List<String> barcodes) {
        List<Item> itemsPurchased = convertToItems(barcodes);
        Receipt receipt = computeReceipt(itemsPurchased);

        return generateReceipt(receipt);
    }

    public List<ItemInfo> getAllItemsInfo() {

        return ItemDataLoader.loadAllItemInfos();
    }

    public Item convertToItem(ItemInfo item, List<String> barcodes) {
        Item it = new Item();
        for (String barcode : barcodes) {
            if (item.getBarcode().equals(barcode)) {
                it = new Item(item.getName(), item.getPrice(), Collections.frequency(barcodes, barcode));
            }
        }
        return it;
    }

    public List<Item> convertToItems(List<String> barcodesList) {
        List<ItemInfo> itemsInfo = getAllItemsInfo();
        List<Item> itemsPurchased = new ArrayList<>();
        for (ItemInfo item : itemsInfo) {
            itemsPurchased.add(convertToItem(item, barcodesList));
        }

        return itemsPurchased;
    }

    public List<Item> computeSubtotal(List<Item> itemsPurchased) {
        for (Item item : itemsPurchased) {
            item.setSubTotal((item.getQuantity() * item.getUnitPrice()));
        }
        return itemsPurchased;
    }

    public Receipt computeTotalPrice(List<Item> itemsWithSubtotal) {
        int totalPrice = 0;
        for (Item item : itemsWithSubtotal) {
            totalPrice = totalPrice + item.getSubTotal();
        }

        return new Receipt(itemsWithSubtotal, totalPrice);
    }

    public Receipt computeReceipt(List<Item> itemsPurchased) {
        List<Item> itemsWithSubtotal = computeSubtotal(itemsPurchased);

        return computeTotalPrice(itemsWithSubtotal);
    }

    public String combineItemDetails(Receipt receipt) {
        String allItemDetails = "";
        for (Item item : receipt.getItemDetails()) {
            allItemDetails = allItemDetails + "Name: " + item.getName() + ", Quantity: "
                    + item.getQuantity() + ", Unit price: " + item.getUnitPrice()
                    + " (yuan), Subtotal: " + item.getSubTotal() + " (yuan)\n";
        }

        return "***<store earning no money>Receipt***\n" + allItemDetails;
    }

    public String addTotalPrice(String allItemsDetails, Double totalPrice) {
        return allItemsDetails +
                "----------------------\n"
                + "Total: " + totalPrice.intValue() + " (yuan)\n"
                + "**********************";
    }

    public String generateReceipt(Receipt receipt) {
        String allItemsDetails = combineItemDetails(receipt);
        double totalPrice = receipt.getTotalPrice();

        return addTotalPrice(allItemsDetails, totalPrice);
    }
}
