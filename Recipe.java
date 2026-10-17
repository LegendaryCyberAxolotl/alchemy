import java.util.List;
import java.util.ArrayList;

public class Recipe {
    private ItemType in;
    private ItemType out;
    private Potion result;

    public static final Recipe AWKWARD_POTION = new Recipe(Ingredients.NETHER_WART, Ingredients.WATER_BOTTLE, Potion.AWKWARD_POTION);

    public static final Recipe POTION_OF_NIGHT_VISION = new Recipe(Ingredients.GOLDEN_CARROT, Potions.AWKWARD_POTION, Potion.POTION_OF_NIGHT_VISION);
    public static final Recipe POTION_OF_NIGHT_VISION_LONG = new Recipe(Ingredients.REDSTONE, Potions.POTION_OF_NIGHT_VISION, Potion.POTION_OF_NIGHT_VISION_LONG);

    public static final Recipe POTION_OF_INVISIBILITY = new Recipe(Ingredients.FERMENTED_SPIDER_EYE, Potions.AWKWARD_POTION, Potion.POTION_OF_INVISIBILITY);
    public static final Recipe POTION_OF_INVISIBILITY_LONG = new Recipe(Ingredients.REDSTONE, Potions.POTION_OF_INVISIBILITY, Potion.POTION_OF_INVISIBILITY_LONG);

    public static final Recipe POTION_OF_STRENGTH1 = new Recipe(Ingredients.BLAZE_POWDER, Potions.AWKWARD_POTION, Potion.POTION_OF_STRENGTH1);
    public static final Recipe POTION_OF_STRENGTH1_LONG = new Recipe(Ingredients.REDSTONE, Potions.POTION_OF_STRENGTH1, Potion.POTION_OF_STRENGTH1_LONG);

    public static final Recipe POTION_OF_STRENGTH2 = new Recipe(Ingredients.GLOWSTONE_DUST, Potions.POTION_OF_STRENGTH1, Potion.POTION_OF_STRENGTH2);

    public static final Recipe POTION_OF_WEAKNESS = new Recipe(Ingredients.FERMENTED_SPIDER_EYE, Ingredients.WATER_BOTTLE, Potion.POTION_OF_WEAKNESS);
    public static final Recipe POTION_OF_WEAKNESS_LONG = new Recipe(Ingredients.REDSTONE, Potions.POTION_OF_WEAKNESS, Potion.POTION_OF_WEAKNESS_LONG);

    private static final List<Recipe> recipes = List.of(
        AWKWARD_POTION,
        POTION_OF_NIGHT_VISION,
        POTION_OF_NIGHT_VISION_LONG,
        POTION_OF_INVISIBILITY,
        POTION_OF_INVISIBILITY_LONG,
        POTION_OF_STRENGTH1,
        POTION_OF_STRENGTH1_LONG,
        POTION_OF_STRENGTH2,
        POTION_OF_WEAKNESS,
        POTION_OF_WEAKNESS_LONG
    );

    public Recipe(ItemType in, ItemType out, Potion result) {
        this.in = in;
        this.out = out;
        this.result = result;
    }

    public ItemType getRecipeIn() {
        return this.in;
    }

    public ItemType getRepiceOut() {
        return this.out;
    }

    public Potion getRecipeResult() {
        return this.result;
    }

    public static boolean isValidRecipe(Recipe recipe, ItemType in, ItemType out1, ItemType out2, ItemType out3) {
        if (in != recipe.getRecipeIn() && (out1 != recipe.getRepiceOut() || out2 != recipe.getRepiceOut() || out3 != recipe.getRepiceOut())) {
            return false;
        }

        return true;
    }

    public static Recipe getRecipe(Potions request) {
        Recipe recipe = null;

        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            if (request == recipe.getRecipeResult().getType()) {
                return recipe;
            }
        }

        return null;
    }
}
