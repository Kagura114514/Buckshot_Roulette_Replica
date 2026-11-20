/**
 * Groups items that change player health. Inherits the item type from Items and
 * forwards the shared effect call to the player-only method.
 *
 * @author Leon
 */
public  abstract class HealthItem extends Items {
    // DEVELOPED BY: Leon
    /**
     * Passes the item type to the Items constructor.
     * @param type the item type identifier defined by Items
     */
    public HealthItem(int type) {
        super(type);
    }

    // DEVELOPED BY: Leon
    /**
     * Forwards to the specialized effect overload, allowing subclass overrides to apply
     * the actual game effect.
     * @param player the player using or receiving the effect
     * @param opponentPlayer the opposing player (unused by this adapter)
     * @param gun the shotgun involved in the operation
     * @return the result returned by the specialized effect
     */
    public int Effect(Player player,Player opponentPlayer, ShotGun gun) {
        return Effect(player);
    }

    // DEVELOPED BY: Leon
    /**
     * Defines the specialized effect that concrete item subclasses must implement.
     * @param player the player using or receiving the effect
     * @return the result returned by the specialized effect
     */
    public abstract int Effect(Player player);
}
