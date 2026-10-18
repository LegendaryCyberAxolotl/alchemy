import java.util.List;
import java.util.ArrayList;

public class Potion extends Item{
    private Effect effect;
    private int time; // time is represented by ticks. 1 second = 20 ticks

    public static final Potion AWKWARD_POTION = new Potion(Potions.AWKWARD_POTION, 1, Effect.NONE, 0);

    public static final Potion POTION_OF_NIGHT_VISION = new Potion(Potions.POTION_OF_NIGHT_VISION, 1, Effect.NIGHT_VISION, 3600);
    public static final Potion POTION_OF_NIGHT_VISION_LONG = new Potion(Potions.POTION_OF_NIGHT_VISION_LONG, 1, Effect.NIGHT_VISION, 9600);

    public static final Potion POTION_OF_INVISIBILITY = new Potion(Potions.POTION_OF_INVISIBILITY, 1, Effect.INVISIBILITY, 3600);
    public static final Potion POTION_OF_INVISIBILITY_LONG = new Potion(Potions.POTION_OF_INVISIBILITY_LONG, 1, Effect.INVISIBILITY, 9600);

    public static final Potion POTION_OF_STRENGTH1 = new Potion(Potions.POTION_OF_STRENGTH1, 1, Effect.STRENGTH_1, 3600);
    public static final Potion POTION_OF_STRENGTH1_LONG = new Potion(Potions.POTION_OF_STRENGTH1_LONG, 1, Effect.STRENGTH_1, 9600);

    public static final Potion POTION_OF_STRENGTH2 = new Potion(Potions.POTION_OF_STRENGTH2, 1, Effect.STRENGTH_2, 1800);

    public static final Potion POTION_OF_WEAKNESS = new Potion(Potions.POTION_OF_WEAKNESS, 1, Effect.WEAKNESS, 1800);
    public static final Potion POTION_OF_WEAKNESS_LONG = new Potion(Potions.POTION_OF_WEAKNESS_LONG, 1, Effect.WEAKNESS, 4800);

    private static List<Potion> potions = List.of(
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

    public Potion(Potions potion, int stackSize, Effect effect, int time) {
        
        super(potion, stackSize);
        this.effect = effect;
        this.time = time;
    }

    public ItemType getPotionType() {
        return this.type;
    }

    public List<Potion> getPotions() {
        return this.potions;
    }

    public static Potion getPotion(Potions request) {
        Potion potion;
        for (int i = 0; i < potions.size(); i++) {
            potion = potions.get(i);
            if (potion.getPotionType() == request) {
                return potion;
            }
        }

        return null;
    }
}
