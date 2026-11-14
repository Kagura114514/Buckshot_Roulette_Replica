/**
 * Represents handcuffs that prevent an opponent from acting.
 *
 * @author Leon
 */
public class Handcuff extends OpponentItem {
    public  Handcuff(){
        super(Items.HANDCUFF);
    }


    public int Effect(Player player,Player opponent, ShotGun gun) {
        return Effect(opponent);
    }

    /**
     * Delegates the effect to the opponent-only method.
     *
     * @param opponent the player to handcuff
     * @return return 1 if the opponent is handcuffed; -1 if already unable to act
     * @       return   if opponent is null
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
