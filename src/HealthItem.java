/**
 * Provides a common base for items that affect the user's health.
 * The shared three-parameter effect method delegates to the player-only method
 * implemented by a concrete subclass.
 *
 * @author Leon
 */
public  abstract class HealthItem extends Items {
    /**
     * Initializes a health item with its type identifier.
     *
     * @param type the item type identifier passed to Items
     */
    public HealthItem(int type) {
        super(type);
    }

    /**
     * Delegates the effect to the player-only method.
     * The opponent and shotgun parameters are not used by this implementation.
     *
     * @param player the player receiving the health effect
     * @param opponentPlayer the opposing player; ignored by this implementation
     * @param gun the current shotgun; ignored by this implementation
     * @return the result code returned by the player-only effect method
     */
    public int Effect(Player player,Player opponentPlayer, ShotGun gun) {
        return Effect(player);
    }

    /**
     * Applies the health effect defined by a subclass.
     *
     * @param player the player receiving the health effect
     * @return the result code defined by the subclass implementation
     */
    public  abstract int Effect(Player player);
}
