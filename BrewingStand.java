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
        Potions.WATER_BOTTLE,
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
        Potions.WATER_BOTTLE,
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
        Potions.WATER_BOTTLE,
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
        this.slotFuel.placeItem(fuel, amount);
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

    public boolean isOutEmpty() {
        boolean out1 = this.slotOut1.isEmpty();
        boolean out2 = this.slotOut2.isEmpty();
        boolean out3 = this.slotOut3.isEmpty();

        if (out1 && out2 && out3) {
            return true;
        }

        return false;
    }

    private ItemType typeOf(Slot slot) {
        if (slot.isEmpty()) {
            return null;
        }
        return slot.getItem().getType();
    }

    public void brew(Potions request, int amount) {
        if (amount < 1 || amount > 3) {
            System.out.println("You can get 1 to 3 potions.");
            return;
        }
        Recipe recipe = Recipe.getRecipe(request);
        if (recipe == null) {
            System.out.println("There is no recipe for this potion.");
            return;
        }

        if (!Recipe.isValidRecipe(recipe, typeOf(this.slotIn), typeOf(this.slotOut1), typeOf(this.slotOut2), typeOf(this.slotOut3))) {
            System.out.println("Invalid ingredients.");
            return;
        }

        Potion result = recipe.getRecipeResult();

        this.consumeFuel();
        if (!this.isFuel()) {
            System.out.println("There is no fuel.");
            return;
        }
        this.burnFuel();
        this.slotIn.takeItems(1);

        try {
            Thread.sleep(brewingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

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