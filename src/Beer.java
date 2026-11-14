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
        int result = gun.EjectShell();
        String shell = "";
        if(result == ShotGun.BLANK) {
            shell = "blank";
        }
        else if(result == ShotGun.LIVE){
            shell = "live";
        }
        else{
            System.out.println("No shell can be ejected.");
            System.out.println("Beer is used..\n");
            return -1;
        }

        System.out.println("An " + shell + " shell is ejected...");
        System.out.println("Beer is used..\n");
        return 1;
    }
}
