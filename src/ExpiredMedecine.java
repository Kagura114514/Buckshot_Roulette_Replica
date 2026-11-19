/**
 * Randomly attempts to restore up to two health points or removes one health point. The
 * healing branch fails when health is already full.
 *
 * @author Leon
 */
public class ExpiredMedecine extends HealthItem {
    // DEVELOPED BY: Leon
    /**
     * Initializes this item with its fixed type identifier.
     */
    public  ExpiredMedecine(){
        super(Items.EXPIRED_MEDECINE);
    }

    // DEVELOPED BY: Leon
    /**
     * Randomly chooses between healing up to two health points and losing one point.
     * Healing fails at full health; loss is applied directly without clamping.
     * @param player the player using or receiving the effect
     * @return 1 on success; -1 if the effect cannot be applied
     */
    public int Effect(Player player){
        int Hp=player.GetHP();
        int maxHp=player.GetMaxHp();
        int chance = (int)(Math.random() * 2);
        if (chance == 0) {
            if (Hp < maxHp) {
                if (maxHp - Hp == 1) {
                    player.AddHP(1);
                    System.out.println("Restored 1 HP");
                } else {
                    player.AddHP(2);
                    System.out.println("Restored 2 HP");
                }
            } else {
                System.out.println("The health has been fully restored and cannot be used any longer");
                return -1;
            }
        }
        else{
            player.SetHP(player.GetHP() - 1);
            System.out.println("lose 1 HP");
        }
        System.out.println("Expired Medecine is used...");
        return 1;
    }
}
