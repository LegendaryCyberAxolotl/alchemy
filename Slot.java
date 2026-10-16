import java.util.List;
import java.util.ArrayList;

public class Slot {
    private Item item;
    private int amount;
    private List<ItemType> allowedItems;
    private SlotType type;

    public Slot(List<ItemType> allowedItems, SlotType type) {
        this.item = null;
        this.amount = 0;
        this.allowedItems = allowedItems;
        this.type = type;
    }

    public Item getItem() {
        return this.item;
    }

    public void takeItem() {
        this.item = null;
    }

    public void placeItem(Item item, int amount) {
        int placed = this.amount + amount;
        if (!this.allowedItems.contains(item.getType()) || !this.allowedItems.contains(ItemType.ANY)) {
            System.out.println("Unable to place item in this slot");
        }

        if (this.item != null && this.item != item) {
            System.out.println("The slot is full");
        }

        if (placed > this.item.getMaxAmount()) {
            System.out.println("This slot already contains the maximum amount of this item");
        }

        this.item = item;
        this.amount = amount;
    }
}
