import java.util.List;
import java.util.ArrayList;

public class BrewingStand {
    private Slot slotIn;
    private Slot slotFuel;
    private Slot slotOut1;
    private Slot slotOut2;
    private Slot slotOut3;
    private int brewingTime;
    private int fuelLeft;

    private final List<ItemType> allowedIn = List.of(AnyItem.ANY);
    private final List<ItemType> allowedFuel = List.of(Ingredients.BLAZE_POWDER);
    private final List<ItemType> allowedOut1 = List.of(
        Potions.AWKWARD_POTION,
        Potions.POTION_OF_NIGHT_VISION,
        Potions.POTION_OF_NIGHT_VISION_LONG,
        Potions.POTION_OF_INVISIBILITY,
        Potions.POTION_OF_INVISIBILITY_LONG,
        Potions.POTION_OF_STRENGTH1,
        Potions.POTION_OF_STRENGTH1_LONG,
        Potions.POTION_OF_STRENGTH2,
        Potions.POTION_OF_WEAKNESS,
        Potions.POTION_OF_WEAKNESS_LONG);

    private final List<ItemType> allowedOut2 = List.of(
        Potions.AWKWARD_POTION,
        Potions.POTION_OF_NIGHT_VISION,
        Potions.POTION_OF_NIGHT_VISION_LONG,
        Potions.POTION_OF_INVISIBILITY,
        Potions.POTION_OF_INVISIBILITY_LONG,
        Potions.POTION_OF_STRENGTH1,
        Potions.POTION_OF_STRENGTH1_LONG,
        Potions.POTION_OF_STRENGTH2,
        Potions.POTION_OF_WEAKNESS,
        Potions.POTION_OF_WEAKNESS_LONG);

    private final List<ItemType> allowedOut3 = List.of(
        Potions.AWKWARD_POTION,
        Potions.POTION_OF_NIGHT_VISION,
        Potions.POTION_OF_NIGHT_VISION_LONG,
        Potions.POTION_OF_INVISIBILITY,
        Potions.POTION_OF_INVISIBILITY_LONG,
        Potions.POTION_OF_STRENGTH1,
        Potions.POTION_OF_STRENGTH1_LONG,
        Potions.POTION_OF_STRENGTH2,
        Potions.POTION_OF_WEAKNESS,
        Potions.POTION_OF_WEAKNESS_LONG);

    public BrewingStand() {
        this.slotIn = new Slot(allowedIn, SlotType.INPUT);
        this.slotFuel = new Slot(allowedFuel, SlotType.INPUT);
        this.slotOut1 = new Slot(allowedOut1, SlotType.OUTPUT);
        this.slotOut2 = new Slot(allowedOut2, SlotType.OUTPUT);
        this.slotOut3 = new Slot(allowedOut3, SlotType.OUTPUT);
        this.brewingTime = 3000;
        this.fuelLeft = 0;
    }

    public void consumeFuel() {
        if (!this.isFuel() && slotFuel.isEmpty() == false) {
            slotFuel.takeItems(1);
            this.fuelLeft = 5;
        }
    }

    public void burnFuel() {
        if (this.isFuel()) {
            this.fuelLeft -= 1;
        }
    }

    public void addFuel(Item fuel, int amount) {
        this.slotFuel.placeItem(new Item(Ingredients.BLAZE_POWDER, 64), amount);
        this.consumeFuel();
    }

    public boolean isFuel() {
        if (fuelLeft == 0) {
            return false;
        }
        return true;
    }

    public void putIngredient(Item item, int amount) {
        this.slotIn.placeItem(item, amount);
    }

    public void putBottle1(Item item, int amount) {
        this.slotOut1.placeItem(item, amount);
    }

    public void putBottle2(Item item, int amount) {
        this.slotOut2.placeItem(item, amount);
    }

    public void putBottle3(Item item, int amount) {
        this.slotOut3.placeItem(item, amount);
    }

    public Slot getIn() {
        return this.slotIn;
    }

    public Slot getOut1() {
        return this.slotOut1;
    }

    public Slot getOut2() {
        return this.slotOut2;
    }

    public Slot getOut3() {
        return this.slotOut3;
    }

    public void brew(Potions request) {
        Recipe recipe = Recipe.getRecipe(request);
        if (recipe == null) {
            System.out.println("There is no recipe for this potion.");
            return;
        }

        if (!Recipe.isValidRecipe(recipe, this.getIn().getItem().getType(), this.getOut1().getItem().getType(), this.getOut2().getItem().getType(), this.getOut3().getItem().getType())) {
            System.out.println("Invalid ingredients.");
            return;
        }

        Potion result = recipe.getRecipeResult();

        this.consumeFuel();
        this.burnFuel();
        this.slotIn.takeItems(1);

        if(!this.slotOut1.isEmpty()) {
            this.slotOut1.takeItems(1);
            this.slotOut1.placeItem(result, 1);
        }

        if(!this.slotOut2.isEmpty()) {
            this.slotOut2.takeItems(1);
            this.slotOut2.placeItem(result, 1);
        }

        if(!this.slotOut3.isEmpty()) {
            this.slotOut3.takeItems(1);
            this.slotOut3.placeItem(result, 1);
        }
    }
}
