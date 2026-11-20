/**
 * Provides a common base for items that reveal information about the shotgun.
 * The shared three-parameter effect method delegates to the shotgun-only
 * method, which subclasses may override; the base implementation returns 1.
 *
 * @author
 */
public abstract class InformationItem extends Items {
    // DEVELOPED BY: RIMI
    /** Initializes the item with its type identifier.
     *  @param type - the item type identifier passed to Items    */
    public InformationItem(int type) {
        super(type);
    }
    // DEVELOPED BY: RIMI
    /** Delegates to the shotgun-only effect. Player parameters are unused.
     *  @param player   - the item user, unused here
     *  @param opponent - the opponent, unused here
     *  @param gun      - the shotgun to inspect
     *  @return         - the delegated effect result                                     */
    public int Effect(Player player, Player opponent, ShotGun gun){
        return Effect(gun);
    }
    // DEVELOPED BY: RIMI
    /** Delegates to the shotgun-and-stage effect. Player parameters are unused.
     *  @param player   - the item user, unused here
     *  @param opponent - the opponent, unused here
     *  @param gun      - the shotgun to inspect
     *  @return         - the delegated effect result                                     */
    public int Effect(Player player, Player opponent, ShotGun gun, Stage stg){
        return Effect(gun, stg);
    }
    // DEVELOPED BY: RIMI
    /** Delegates to the shotgun-only effect unless a subclass overrides this overload.
     *  @param gun - the opponent, unused here
     *  @param stg      - the shotgun to inspect
     *  @return         - the delegated effect result                                     */
    public int Effect(ShotGun gun, Stage stg){
        return Effect(gun);
    }
    // DEVELOPED BY: RIMI
    /** Default implementation that reports success without changing or revealing anything.
     *  Subclasses may override it.
     *  @param gun the shotgun, unused by this implementation
     *  @return 1 unconditionally                                     */
    public int Effect(ShotGun gun){
        return 1;
    }
}
