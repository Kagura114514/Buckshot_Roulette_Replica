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
        switch(result){
            case ShotGun.BLANK:
                shell = "blank";
                break;
            case ShotGun.LIVE:
                shell = "live";
                break;
        }

        if(result == ShotGun.BLANK || result == ShotGun.LIVE){
            System.out.println("An " + shell + " shell is ejected...");
            System.out.println("Beer is used..");
            return 1;
        }
        else{
            System.out.println("Something went wrong..");
            System.out.println("Beer is used..");
            return -1;
        }
    }
}
