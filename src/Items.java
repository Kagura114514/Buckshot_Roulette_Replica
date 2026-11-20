/**
 * Defines the common item type identifiers and polymorphic effect entry points. Concrete
 * subclasses implement game effects; the controller removes an item after success.
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

    // DEVELOPED BY: Leon
    /**
     * Stores the supplied item type.
     * @param type the item type identifier defined by Items
     */
    public Items(int type) {
        this.type = type;
    }

    // DEVELOPED BY: Leon
    /**
     * Returns the identifier assigned when the item was constructed.
     * @return the item type identifier from 1 through 9
     */
    public int GetType() {
        return type;
    }

    // DEVELOPED BY:
    /**
     * Forwards to the three-parameter effect, ignoring the stage by default.
     * InformationItem overrides this entry point to forward stage information.
     * @param player the player using or receiving the effect
     * @param opponent the opposing player
     * @param gun the shotgun involved in the operation
     * @param stg the stage configuration
     * @return the result supplied by the concrete effect implementation
     */
    public int Effect(Player player, Player opponent, ShotGun gun, Stage stg) {
        return Effect(player, opponent, gun);
    }
    // DEVELOPED BY: Leon
    /**
     * Forwards to the stage-aware effect, ignoring the opponent slot by default.
     * Adrenaline overrides this entry point to steal the selected item.
     * @param player the player using or receiving the effect
     * @param opponent the opposing player
     * @param gun the shotgun involved in the operation
     * @param opponentIndex the zero-based opponent inventory slot, from 0 through 7
     * @param stg the stage configuration
     * @return the result supplied by the concrete effect implementation
     */
    public int Effect(Player player, Player opponent, ShotGun gun, int opponentIndex, Stage stg) {
        return Effect(player, opponent, gun, stg);
    }
    // DEVELOPED BY: Leon
    /**
     * Defines the common game-effect contract implemented by item subclasses.
     * @param player the player using or receiving the effect
     * @param opponent the opposing player
     * @param gun the shotgun involved in the operation
     * @return the result supplied by the concrete effect implementation
     */
    public abstract int Effect(Player player,Player opponent, ShotGun gun);


}