/**
 * Provides a common base for items that interact with an opponent.
 * Inherits the abstract three-parameter effect method from Items and leaves
 * its implementation to concrete subclasses.
 *
 * @author Leon
 */
public abstract class OpponentItem extends Items {
    /**
     * Initializes an opponent-related item with its type identifier.
     *
     * @param type the item type identifier passed to Items
     */
    public OpponentItem(int type) {
        super(type);
    }



}
