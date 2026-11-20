/**
 * Provides a common base for items that affect the shotgun.
 * The shared three-parameter effect method delegates to the shotgun-only
 * method implemented by a concrete subclass.
 *
 * @author Leon
 */
public abstract class ShotgunEffectItem extends Items {
    // DEVELOPED BY: RIMI
    /** Initializes the item with its type identifier.
     *  @param type the item type identifier passed to Items */
    public ShotgunEffectItem(int type) {
        super(type);
    }

    /** Routes the shared item call to the shotgun-specific effect.
     *  @param player         - the item user, unused here
     *  @param opponentPlayer - the opponent, unused here
     *  @param gun            - the shotgun affected by the item
     *  @return               - the delegated effect result                             */
    public int Effect(Player player,Player opponentPlayer, ShotGun gun) {
        return Effect(gun);
    }

    // DEVELOPED BY: RIMI
    /** Applies the shotgun effect defined by a concrete subclass.
     * @param gun - the shotgun used by the effect
     * @return    - the result code defined by the subclass implementation */
    public abstract int Effect(ShotGun gun);
}
