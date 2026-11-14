/**
 * Marks the opponent as cuffed so that the game controller can skip that opponent once when
 * changing turns.
 *
 * @author Leon
 */
public class Handcuff extends OpponentItem {
    // DEVELOPED BY: Leon
    /**
     * Initializes this item with its fixed type identifier.
     */
    public  Handcuff(){
        super(Items.HANDCUFF);
    }

    // DEVELOPED BY: Leon
    /**
     * Attempts to cuff the opponent; fails if already cuffed.
     * @param player the player using or receiving the effect
     * @param opponent the opposing player
     * @param gun the shotgun involved in the operation
     * @return 1 on success; -1 if the effect cannot be applied
     */
    public int Effect(Player player,Player opponent, ShotGun gun) {
        return Effect(opponent);
    }

    // DEVELOPED BY: Leon
    /**
     * Attempts to cuff the opponent; fails if already cuffed.
     * @param opponent the opposing player
     * @return 1 on success; -1 if the effect cannot be applied
     */
    public int Effect(Player opponent){
        if(opponent.GetIsCuffed() == false){
            opponent.Cuffed();
            System.out.println("You're opponent is now cuffed...");
            System.out.println("Handcuff is used..");
            return 1;
        }
        else{
            System.out.println("You can't cut a barrel twice right?.. ");
            System.out.println("Handcuff is used..");
            return -1;
        }
    }
}
