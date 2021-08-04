package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PosMachine {

    public String printReceipt(List<String> barcodes) {
        List<Item> itemsPurchased = convertToItems(barcodes);
        Receipt receipt = computeReceipt(itemsPurchased);
        String finalReceipt = generateReceipt(receipt);

        return finalReceipt;
    }

    public List<ItemInfo> getAllItemsInfo(){
        List<ItemInfo> itemsInfo = ItemDataLoader.loadAllItemInfos();

        return itemsInfo;
    }

    public List<Item> convertToItems(List<String> barcodesList){
        List<ItemInfo> itemsInfo = getAllItemsInfo();
        List<Item> itemsPurchased = new ArrayList<>();

        for(ItemInfo item : itemsInfo) {
            for (String barcode : barcodesList.stream().distinct().collect(Collectors.toList())) {
                if (item.getBarcode().equals(barcode)) {
                    Item it = new Item(item.getName(),item.getPrice(), Collections.frequency(barcodesList, barcode));
                    itemsPurchased.add(it);
                }
            }
        }
        return itemsPurchased;
    }

    public List<Item> computeSubtotal(List<Item> itemsPurchased){
        for(Item item : itemsPurchased) {
            item.setSubTotal((item.getQuantity() * item.getUnitPrice()));
        }
        return itemsPurchased;
    }

    public Receipt computeTotalPrice(List<Item> itemsWithSubtotal){
        int totalPrice =0;
        for(Item item : itemsWithSubtotal) {
            totalPrice = totalPrice + item.getSubTotal();
        }
        Receipt receipt = new Receipt(itemsWithSubtotal, totalPrice);

        return receipt;
    }

    public Receipt computeReceipt(List<Item> itemsPurchased){
        List<Item> itemsWithSubtotal = computeSubtotal(itemsPurchased);
        Receipt receipt = computeTotalPrice(itemsWithSubtotal);

        return receipt;
    }

    public String combineItemDetails(Receipt receipt){
        String allItemDetails = "";
        for( Item item: receipt.getItemDetails()) {
           allItemDetails =  allItemDetails + "Name: " + item.getName() + ", Quantity: "
                   + item.getQuantity() + ", Unit price: " + item.getUnitPrice()
                   + " (yuan), Subtotal: " + item.getSubTotal() + " (yuan)\n";
        }

        return "***<store earning no money>Receipt***\n" + allItemDetails;
    }

    public String addTotalPrice(String allItemsDetails, Double totalPrice){
        return allItemsDetails +
                "----------------------\n"
                + "Total: " + totalPrice.intValue() + " (yuan)\n"
                + "**********************";
    }

    public String generateReceipt(Receipt receipt){
        String allItemsDetails = combineItemDetails(receipt);
        double totalPrice = receipt.getTotalPrice();

        String generatedReceipt = addTotalPrice(allItemsDetails,totalPrice);

        return generatedReceipt;
    }
}
