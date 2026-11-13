/**
 * Represents a cigarette pack that restores one health point.
 *
 * @author Leon
 */
public class CigarettesPack extends HealthItem {

    /**
     * Creates a cigarette pack with the cigarette item type identifier.
     */
    public  CigarettesPack(){
        super(Items.CIGARETTES_PACK);
    }

    /**
     * Restores one health point if current health is below maximum health.
     *
     * @param player the player receiving the health effect
     * @return return 1 if health is restored; -1 if current health is at or above the maximum
     *         return   if player is null
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
            return -1;
        }
    }



}
