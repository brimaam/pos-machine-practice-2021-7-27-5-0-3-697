package pos.machine;

import java.util.ArrayList;
import java.util.List;

public class PosMachine {
    public static void main(String[] args) {

    }
    public String printReceipt(List<String> barcodes) {
        return null;
    }

    public List<ItemInfo> getAllItemsInfo(){
        List<ItemInfo> itemsInfo = ItemDataLoader.loadAllItemInfos();

        return itemsInfo;
    }

    public List<Item> convertToItems(List<String> barcodesList){
        List<ItemInfo> itemsInfo = getAllItemsInfo();
        List<Item> itemPurchased = new ArrayList<Item>();

        for(ItemInfo item : itemsInfo) {
            for (String barcode : barcodesList) {
                if (item.getBarcode().equals(barcode)) {
                    Item it = new Item(item.getName(),item.getPrice());
                    itemPurchased.add(it);
                }
            }
        }
        return itemPurchased;
    }

    public List<Item> computeSubtotal(List<Item> itemsPurchased){
        for(Item item : itemsPurchased) {
            item.setSubTotal((item.getQuantity() * item.getUnitPrice()));
        }
        return itemsPurchased;
    }

    public Receipt computeTotalPrice(List<Item> itemsWithSubtotal){
        Receipt receipt = new Receipt();
        for(Item item : itemsWithSubtotal) {
            receipt.setTotalPrice(item.getSubTotal());
            receipt.setItemDetails(itemsWithSubtotal);
        }
        return receipt;
    }

    public Receipt computeReceipt(List<Item> itemsPurchased){
        List<Item> itemsWithSubtotal = computeSubtotal(itemsPurchased);
        Receipt receipt = computeTotalPrice(itemsWithSubtotal);

        return receipt;
    }
}
