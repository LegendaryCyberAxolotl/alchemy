public class Main {
    public static void main(String[] args) {
        BrewingManager manager = new BrewingManager();

        manager.brewPotion(Potions.AWKWARD_POTION, 2);
        manager.brewPotion(Potions.POTION_OF_NIGHT_VISION_LONG, 3);
        manager.brewPotion(Potions.POTION_OF_STRENGTH2, 1);
        manager.brewPotion(Potions.POTION_OF_WEAKNESS, 2);

        // wrong requests
        manager.brewPotion(Potions.POTION_OF_INVISIBILITY, 4);
        manager.brewPotion(Potions.WATER_BOTTLE, 1);
    }
}