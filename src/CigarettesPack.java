/**
 * Restores one health point when the player is below maximum health.
 *
 * @author Leon
 */
public class CigarettesPack extends HealthItem {

    // DEVELOPED BY: Leon
    /**
     * Initializes this item with its fixed type identifier.
     */
    public  CigarettesPack(){
        super(Items.CIGARETTES_PACK);
    }

    // DEVELOPED BY: Leon
    /**
     * Restores one health point if below maximum health; otherwise reports failure.
     * @param player the player using or receiving the effect
     * @return 1 on success; -1 if the effect cannot be applied
     */
    public int Effect(Player player){
        int Hp=player.GetHP();
        int maxHp=player.GetMaxHp();
        if(Hp < maxHp) {
            player.AddHP(1);
            System.out.println("Restore one health point");
            return 1;
        }
        else{
            System.out.println("The health has been fully restored and cannot be used any longer");
            return -1;
        }
    }



}
