import java.util.List;

public class Main {
    public static void main(String[] args) {
        BrewingManager manager = new BrewingManager();

        order(manager, Potions.AWKWARD_POTION, 2);
        order(manager, Potions.POTION_OF_NIGHT_VISION_LONG, 3);
        order(manager, Potions.POTION_OF_STRENGTH2, 1);
        order(manager, Potions.POTION_OF_WEAKNESS, 2);

        // wrong requests
        order(manager, Potions.POTION_OF_INVISIBILITY, 4);
        order(manager, Potions.WATER_BOTTLE, 1);
    }

    private static void order(BrewingManager manager, Potions potion, int amount) {
        System.out.println("Order: " + amount + " x " + potion);

        List<ItemStack> result = manager.brewPotion(potion, amount);

        if (result.isEmpty()) {
            System.out.println("Nothing was brewed.\n");
            return;
        }

        for (ItemStack stack : result) {
            System.out.println("Got: " + stack.amount() + " x " + stack.item().getType());
        }
        System.out.println();
    }
}