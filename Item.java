import java.util.List;
import java.util.ArrayList;

public class Item {
    protected ItemType type;
    protected int maxAmount;


    public Item(ItemType type, int maxAmount) {
        this.type = type;
        this.maxAmount = maxAmount;
    }

    public int getMaxAmount() {
        return this.maxAmount;
    }

    public ItemType getType() {
        return this.type;
    }

    public static Item getIngredient(Ingredients request) {
        return new Item(request, 64);
    }
}
