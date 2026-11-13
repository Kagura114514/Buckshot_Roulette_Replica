/**
 * Defines an abstract expired-medicine item with a healing effect.
 * The current implementation always heals when health is below the maximum;
 * it does implement the game rule requiring a 50 percent chance of healing
 * and a 50 percent chance of losing health. This class cannot be instantiated
 *
 * @author Leon
 */
public class ExpiredMedecine extends HealthItem {
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
        int chance = (int)(Math.random() * 2);
        if (chance == 0) {
            if (Hp < maxHp) {
                if (maxHp - Hp == 1) {
                    player.AddHP(1);
                } else {
                    player.AddHP(2);
                }
                System.out.println("Restore two health points or lose one health point");

            } else {
                System.out.println("The health has been fully restored and cannot be used any longer");
                return -1;
            }
        }
        else{
            player.SetHP(player.GetHP() - 1);
        }
        return 1;
    }
}
