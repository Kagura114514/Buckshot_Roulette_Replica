/**
 * Provides a common base for items that affect the shotgun.
 * The shared three-parameter effect method delegates to the shotgun-only
 * method implemented by a concrete subclass.
 *
 * @author Leon
 */
public abstract class ShotgunEffectItem extends Items {
    /**
     * Initializes the item with its type identifier.
     *
     * @param type the item type identifier passed to Items
     */
    public ShotgunEffectItem(int type) {
        super(type);
    }

    public int Effect(Player player,Player opponentPlayer, ShotGun gun) {
        return Effect( gun);
    }

    /**
     * Applies the shotgun effect defined by a concrete subclass.


     *
     * @param gun the shotgun used by the effect
     * @return the result code defined by the subclass implementation
     */
    public  abstract int Effect(ShotGun gun);
}
