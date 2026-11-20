public class MagnifyingGlass extends InformationItem {
    public  MagnifyingGlass(){
        super(Items. MAGNIFYING_GLASS );
    }

    // DEVELOPED BY: RIMI
    /** Get the current Shell Info
     *  param@        gun - Shotgun object
     *  return         -1 - If something went wrong
     *          otherwise - return correspond type of the shell*/
    public int Effect(ShotGun gun){
        String text = null;
        int type = gun.GetCurrShellType();
        if(type == 1){
            text = "Live";
        }
        else if(type == 0){
            text = "Blank";
        }
        if(type != -1) {
            System.out.println("Current Shell Type is " + text + "...");
            return 1;
        }
        else{
            System.out.println("Something went wrong...");
            return -1;
        }
    }
}
