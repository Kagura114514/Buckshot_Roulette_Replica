public class Inverter extends ShotgunEffectItem {
    public  Inverter(int tpye){
        super(Items.INVERTER);
    }
    // DEVELOPED BY: RIMI
    /** Revert the type of the current shell
     *  param@  gun - The gun object
     *  return  1   - Success
     *         -1   - failed, something went wrong           */
    public int Effect(ShotGun gun){
        if(gun.SwitchShellType()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
