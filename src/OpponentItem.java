/**
 * Groups items that affect an opponent. This abstract class inherits the effect contract from
 * Items; concrete subclasses supply the behavior.
 *
 * @author Leon
 */
public abstract class OpponentItem extends Items {
    // DEVELOPED BY: Leon
    /**
     * Passes the item type to the Items constructor.
     * @param type the item type identifier defined by Items
     */
    public OpponentItem(int type) {
        super(type);
    }



}
