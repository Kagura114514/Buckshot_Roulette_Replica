/**
 * Defines an abstract expired-medicine item with a healing effect.
 * The current implementation always heals when health is below the maximum;
 * it does implement the game rule requiring a 50 percent chance of healing
 * and a 50 percent chance of losing health. This class cannot be instantiated
 *
 * @author Leon
 */
public abstract class ExpiredMedecine extends HealthItem {
    /**
     * Initializes the expired-medicine item type identifier for a subclass.
     */
    public  ExpiredMedecine(){
        super(Items.EXPIRED_MEDECINE);
    }

    /**
     * Restores up to two health points without exceeding maximum health.
     * Restores one point if only one point is missing, and two points otherwise.
     * This method does not remove the inventory item.
     *
     * @param player the player receiving the health effect
     * @return return 1 if health is restored; -1 if current health is at or above the maximum
     *         return   if player is null
     */
    public int Effect(Player player){
        int Hp=player.GetHP();
        int maxHp=player.GetMaxHp();
        if(Hp < maxHp) {
            if(maxHp-Hp==1) {
                player.AddHP(1);
            }
            else{
                player.AddHP(2);
            }
            return 1;
        }
        else{
            return -1;
        }
    }
}
