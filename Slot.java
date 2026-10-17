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

    public int getAmount() {
        return this.amount;
    }

    public boolean isEmpty() {
        if (this.item != null && this.amount > 0) {
            return false;
        } else {
            return true;
        }
    }

    public ItemStack takeItems(int amount) {
        if (this.item == null) {
            System.out.println("The slot is empty.");
            return null;
        }

        if (amount > this.amount) {
            System.out.println("The slot contains less items than you want to take.");
            return null;
        }

        if (amount == 0) {
            this.item = null;
            this.amount = 0;
            return new ItemStack(this.item, this.amount);
        }

        this.amount -= amount;

        if (this.amount == 0) {
            this.item = null;
            this.amount = 0;
        }

        return new ItemStack(this.item, amount);
    }

    public void placeItem(Item item, int amount) {
        int placed = this.amount + amount;

        if (!this.allowedItems.contains(item.getType()) || !this.allowedItems.contains(AnyItem.ANY)) {
            System.out.println("Unable to place item in this slot");
        }

        if (this.item != null && this.item != item) {
            System.out.println("The slot is full");
        }

        if (placed > this.item.getMaxAmount()) {
            System.out.println("You try to place more items than allowed in its stack");
        }

        this.item = item;
        this.amount += amount;
    }
}
