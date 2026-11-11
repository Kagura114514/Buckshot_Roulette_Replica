public class Beer extends ShotgunEffectItem {
    public Beer(){
        super(Items.BEER);
    }
    // DEVELOPED BY: RIMI
    /** Eject a shell
     *  param@  gun - The gun object
     *  return  1   - Success
     *         -1   - Failed, no shell to eject already(basically won't happen I guess)  */
    public int Effect(ShotGun gun){
        if(gun.EjectShell()){
            return 1;
        }
        else{
            return -1;
        }
    }
}
