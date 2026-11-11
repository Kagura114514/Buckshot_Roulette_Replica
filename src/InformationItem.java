/**
 * Provides a common base for items that reveal information about the shotgun.
 * The shared three-parameter effect method delegates to the shotgun-only
 * method implemented by a concrete subclass.
 *
 * @author Leon
 */
public abstract class InformationItem extends Items {
    /**
     * Initializes the item with its type identifier.
     *
     * @param type the item type identifier passed to Items
     */
    public InformationItem(int type) {
        super(type);
    }

    public int Effect(Player player, Player opponent, ShotGun gun){
        return Effect(gun);
    }

    public int Effect(Player player, Player opponent, ShotGun gun, Stage stg){
        return Effect(gun, stg);
    }

    public int Effect(ShotGun gun, Stage stg){
        return Effect(gun);
    }

    public int Effect(ShotGun gun){
        return 1;
    }
}
