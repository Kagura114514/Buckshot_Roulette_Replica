/**
 * Represents an adrenaline item intended to steal and immediately use
 * an opponent's item. This draft has inverted index and effect-result checks
 * and does not prevent stealing another adrenaline item.
 *
 * @author Leon
 */
public class Adrenaline extends OpponentItem {
    /**
     * Creates an adrenaline item using the fixed ADRENALINE identifier.
     */
    public Adrenaline(){
        super(Items.ADRENALINE);
    }

    public int Effect(Player player,Player opponent, ShotGun gun) {
        return 1;
    }

    /**
     * Attempts to use the item at the supplied opponent inventory index.
     * The current condition enters the inventory-access branch only for indices
     *
     * @param player the player passed to the selected item
     * @param opponent the player whose inventory is accessed
     * @param gun the shotgun passed to the selected item
     * @param opponentIndex the array index used to select the opponent's item
     * @return 1 if the index is from 1 through 8; -2 if the accessed slot is empty;
     */
    public int Effect(Player player, Player opponent, ShotGun gun, int opponentIndex, Stage stg) {
        if (opponentIndex >1 && opponentIndex < 8) {
            Items stolen = opponent.GetAllItem()[opponentIndex];
            if(stolen != null) {
                if(stolen.Effect(player, opponent, gun, stg) != 1) {
                    opponent.RemoveItem(opponentIndex);
                    return 1;
                }
                else{
                    return -3;
                }
            }
            else{
                return -2;
            }
        }
        else{
            return -1;
        }



    }


}
