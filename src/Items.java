/**
 * Defines the common type identifiers and effect methods for game items.
 *
 * @author Leon
 */
public abstract class Items {
    /**
     * Type identifier for item
     */
    public static final int EXPIRED_MEDECINE = 1;
    public static final int CIGARETTES_PACK = 2;
    public static final int MAGNIFYING_GLASS = 3;
    public static final int BURNER_PHONE = 4;
    public static final int HANDSAW = 5;
    public static final int INVERTER = 6;
    public static final int BEER = 7;
    public static final int ADRENALINE = 8;
    public static final int HANDCUFF = 9;

    private int type;

    /**
     * Creates an item with the given type identifier.
     *
     * @param type the identifier of this item
     */
    public Items(int type) {
        this.type = type;
    }

    /**
     * Returns this item's type identifier.
     *
     * @return the item type
     */
    public int Gettype() {
        return type;
    }

    /**
     * Applies the effect implemented by a concrete item subclass.
     *
     * @param player the player using the item
     * @param opponent the opposing player
     * @param gun the shotgun currently used in the game
     * @return the result code defined by the subclass implementation
     */
    public abstract int Effect(Player player,Player opponent, ShotGun gun);

    /**
     * Calls the three-parameter effect method by default.
     * This implementation ignores the opponent inventory index. Subclasses such as
     * Adrenaline can override this method to use the index.
     *
     * @param player the player using the item
     * @param opponent the opposing player
     * @param gun the shotgun currently used in the game
     * @param opponentIndex the opponent inventory slot selected for stealing;
     *        ignored by this default implementation
     * @return the result returned by the three-parameter effect method
     */
    public int Effect(Player player, Player opponent, ShotGun gun, int opponentIndex) {
        return Effect(player, opponent, gun);
    }

    public int Effect(Player player, Player opponent, ShotGun gun,  Stage stg) {
        return Effect(player, opponent, gun);
    }


}