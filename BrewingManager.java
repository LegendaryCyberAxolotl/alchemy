import java.util.ArrayList;
import java.util.List;

public class BrewingManager {
    private BrewingStand brewingStand;
    private Recipe recipe;

    public BrewingManager() {
        this.brewingStand = new BrewingStand();
    }

    public void setRecipe(Recipe recipe){
        this.recipe = recipe;
    }

    // Brews a potion that is made directly from water bottles (awkward potion, weakness).
    public void brewAwkward(Potions request, int amount) {
        if (!isValidAmount(amount)) {
            return;
        }

        setRecipe(Recipe.getRecipe(request));
        if (recipe == null || recipe.getRepiceOut() != Potions.WATER_BOTTLE) {
            System.out.println("This potion can't be brewed from water bottles.");
            return;
        }

        Ingredients ingredient = recipe.getRecipeIn();
        ItemType output = recipe.getRepiceOut();

        clearStand();
        ensureFuel();
        brewingStand.putIngredient(Item.getIngredient(ingredient), 1);

        brewingStand.putBottle1(new Item(output, 1), 1);
        if (amount >= 2) {
            brewingStand.putBottle2(new Item(output, 1), 1);
        }

        if (amount >= 3) {
            brewingStand.putBottle3(new Item(output, 1), 1);
        }

        this.brewingStand.brew(request, amount);
    }

    // Brews any potion on request: finds the whole chain of recipes
    // (e.g. water -> awkward -> strength 1 -> strength 2), brews it step by step
    // and prints the finished potions.
    public void brewPotion(Potions request, int amount) {
        System.out.println("Order: " + amount + " x " + request);

        if (!isValidAmount(amount)) {
            System.out.println("Nothing was brewed.\n");
            return;
        }

        // walk back from the requested potion to the water bottles
        List<Potions> chain = new ArrayList<>();
        Potions step = request;
        while (true) {
            Recipe stepRecipe = Recipe.getRecipe(step);
            if (stepRecipe == null) {
                System.out.println("There is no recipe for this potion.");
                System.out.println("Nothing was brewed.\n");
                return;
            }

            chain.add(0, step);

            if (stepRecipe.getRepiceOut() instanceof Potions previous && previous != Potions.WATER_BOTTLE) {
                step = previous;
            } else {
                break;
            }
        }

        brewAwkward(chain.get(0), amount);
        for (int i = 1; i < chain.size(); i++) {
            brewStep(chain.get(i), amount);
        }

        List<ItemStack> result = collectPotions();
        if (result.isEmpty()) {
            System.out.println("Nothing was brewed.\n");
            return;
        }

        for (ItemStack stack : result) {
            System.out.println("Got: " + stack.amount() + " x " + stack.item().getType());
        }
        System.out.println();
    }

    // One brewing cycle on top of the bottles that are already in the stand.
    private void brewStep(Potions request, int amount) {
        setRecipe(Recipe.getRecipe(request));

        ensureFuel();
        brewingStand.putIngredient(Item.getIngredient(recipe.getRecipeIn()), 1);
        brewingStand.brew(request, amount);
    }

    private void ensureFuel() {
        if (!brewingStand.isFuel()) {
            brewingStand.addFuel(Item.getIngredient(Ingredients.BLAZE_POWDER), 1);
        }
    }

    private boolean isValidAmount(int amount) {
        if (amount < 1 || amount > 3) {
            System.out.println("You can get 1 to 3 potions.");
            return false;
        }
        return true;
    }

    // Takes all bottles out of the stand.
    private List<ItemStack> collectPotions() {
        List<ItemStack> collected = new ArrayList<>();
        for (Slot out : new Slot[] {brewingStand.getOut1(), brewingStand.getOut2(), brewingStand.getOut3()}) {
            if (!out.isEmpty()) {
                collected.add(out.takeItems(out.getAmount()));
            }
        }
        return collected;
    }

    // Empties the stand so the next request starts from scratch.
    private void clearStand() {
        collectPotions();
        Slot in = brewingStand.getIn();
        if (!in.isEmpty()) {
            in.takeItems(in.getAmount());
        }
    }
}