import java.util.List;
import java.util.ArrayList;

public class BrewingStand {
    private Slot slotIn;
    private Slot slotFuel;
    private Slot slotOut1;
    private Slot slotOut2;
    private Slot slotOut3;
    private int brewingTime;

    private final List<ItemType> allowedIn = List.of(ItemType.ANY);
    private final List<ItemType> allowedFuel = List.of(ItemType.REDSTONE);
    private final List<ItemType> allowedOut1 = List.of(
        ItemType.AWKWARD_POTION,
        ItemType.POTION_OF_NIGHT_VISION,
        ItemType.POTION_OF_INVISIBILITY,
        ItemType.POTION_OF_STRENGTH1,
        ItemType.POTION_OF_STRENGTH2,
        ItemType.POTION_OF_WEAKNESS);

    private final List<ItemType> allowedOut2 = List.of(
        ItemType.AWKWARD_POTION,
        ItemType.POTION_OF_NIGHT_VISION,
        ItemType.POTION_OF_INVISIBILITY,
        ItemType.POTION_OF_STRENGTH1,
        ItemType.POTION_OF_STRENGTH2,
        ItemType.POTION_OF_WEAKNESS);

    private final List<ItemType> allowedOut3 = List.of(
        ItemType.AWKWARD_POTION,
        ItemType.POTION_OF_NIGHT_VISION,
        ItemType.POTION_OF_INVISIBILITY,
        ItemType.POTION_OF_STRENGTH1,
        ItemType.POTION_OF_STRENGTH2,
        ItemType.POTION_OF_WEAKNESS);

    public BrewingStand() {
        this.slotIn = new Slot(allowedIn, SlotType.INPUT);
        this.slotFuel = new Slot(allowedFuel, SlotType.INPUT);
        this.slotOut1 = new Slot(allowedOut1, SlotType.OUTPUT);
        this.slotOut2 = new Slot(allowedOut2, SlotType.OUTPUT);
        this.slotOut3 = new Slot(allowedOut3, SlotType.OUTPUT);
        this.brewingTime = 3000;
    }
}
