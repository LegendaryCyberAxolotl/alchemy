public class Potion extends Item{
    private Effect effect;
    private int time; // effects are represented by ticks. 1 second = 20 ticks

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


    public Potion(Potions potion, int stackSize, Effect effect, int time) {
        
        super(potion, stackSize);
        this.effect = effect;
        this.time = time;
    }
}
