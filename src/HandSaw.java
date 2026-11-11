public class HandSaw extends ShotgunEffectItem {
    public HandSaw(){
        super(Items.HANDSAW);
    }
    // DEVELOPED BY: RIMI
    /** Double the ShotGun Damage
     *  param@  gun - The gun object
     *  return  1   - Success
     *         -1   - The current shell has used HandSaw already  */
    public int Effect(ShotGun gun){
        if(gun.DoubleTheDMG() == 1){
            return 1;
        }
        else {
            return -1;
        }
    }
}
