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

    public List<ItemStack> brewPotion(Potions request, int amount) {
        if (!isValidAmount(amount)) {
            return new ArrayList<>();
        }

        // walk back from the requested potion to the water bottles
        List<Potions> chain = new ArrayList<>();
        Potions step = request;
        while (true) {
            Recipe stepRecipe = Recipe.getRecipe(step);
            if (stepRecipe == null) {
                System.out.println("There is no recipe for this potion.");
                return new ArrayList<>();
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

        return collectPotions();
    }

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

    private List<ItemStack> collectPotions() {
        List<ItemStack> collected = new ArrayList<>();
        for (Slot out : new Slot[] {brewingStand.getOut1(), brewingStand.getOut2(), brewingStand.getOut3()}) {
            if (!out.isEmpty()) {
                collected.add(out.takeItems(out.getAmount()));
            }
        }
        return collected;
    }

    private void clearStand() {
        collectPotions();
        Slot in = brewingStand.getIn();
        if (!in.isEmpty()) {
            in.takeItems(in.getAmount());
        }
    }
}