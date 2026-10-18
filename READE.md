# Clean code principles

## Meaningful names that make the Facade's simplified API self-explanatory

`BrewingManager`, `BrewingStand`, `Recipe`, `Slot`

> Names represent each class's function.

## Single Responsibility: the Facade coordinates, it does not reimplement subsystem logic

```java
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
```

> BrewingManager runs other class's methods instead of creating new ones for managin brewing process.

## No leaking of subsystem details through the Facade's public interface

```java
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
```

> Methods do not return private fields' objects. Client only requests ptions and waits for them to be ready.

## Small, focused subsystem classes with clear responsibilities

> Each subsystems, for example `BrewingStand`, `Recipe`, and `Slot`, has its own responsibility. For example `BrewingStand` as a system that holds potions and ingredients and controls the process, `Recipe` is defining recipes for each potion, and `Slot` creates slot logic.

## Proper error handling for subsystem coordination failures

```java
Recipe stepRecipe = Recipe.getRecipe(step);
if (stepRecipe == null) {
    System.out.println("There is no recipe for this potion.");
    System.out.println("Nothing was brewed.\n");
    return;
}
```

```java
private boolean isValidAmount(int amount) {
    if (amount < 1 || amount > 3) {
        System.out.println("You can get 1 to 3 potions.");
        return false;
    }
    return true;
}
```

> The system checks for valid recipes and valid amount of placed ingredients. If there is an error, the programm does not falls but shwos a message and continues to run.