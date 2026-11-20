/**
 * Steals and immediately uses a selected opponent item through the five-parameter effect
 * method. Another adrenaline item cannot be stolen.
 *
 * @author Leon
 */
public class Adrenaline extends OpponentItem {
    // DEVELOPED BY: Leon
    /**
     * Initializes this item with its fixed type identifier.
     */
    public Adrenaline(){
        super(Items.ADRENALINE);
    }


    // DEVELOPED BY: Leon
    /**
     * Placeholder overload without a target slot. Does not steal or use an item; use the
     * five-parameter overload for stealing.
     * @param player the player using or receiving the effect
     * @param opponent the opposing player
     * @param gun the shotgun involved in the operation
     * @return 1 without performing an action
     */
    public int Effect(Player player,Player opponent, ShotGun gun) {
        return 1;
    }

    // DEVELOPED BY: Leon
    /**
     * Validates the selected opponent slot, immediately applies that item to the user,
     * and removes it from the opponent only on success. Rejects another adrenaline item.
     * @param player the player using or receiving the effect
     * @param opponent the opposing player
     * @param gun the shotgun involved in the operation
     * @param opponentIndex the zero-based opponent inventory slot, from 0 through 7
     * @param stg the stage configuration
     * @return 1 on success; -1 if the effect cannot be applied
     */
    public int Effect(Player player, Player opponent, ShotGun gun, int opponentIndex, Stage stg) {
        if (opponentIndex >= 0 && opponentIndex <= 7) {
            Items stolen = opponent.GetAllItem()[opponentIndex];

            if(stolen == null ) {
                System.out.println("The empty one cannot be chosen");
                return -1;
            }
            else if(stolen.GetType() == Items.ADRENALINE){
                System.out.println("Cannot steal Adrenaline!");
                return -1;
            }
            else{
                if(stolen.Effect(player, opponent, gun, stg) == 1) {
                    opponent.RemoveItem(opponentIndex);
                    System.out.println("Opponent's item is used");
                    return 1;
                }
                else{
                    System.out.println("item cannot be reused..");
                    return -1;
                }
            }

        }
        else{
            System.out.println("It's out of range");
            return -1;
        }
    }
}
