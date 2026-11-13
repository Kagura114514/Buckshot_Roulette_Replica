public class MagnifyingGlass extends InformationItem {
    public  MagnifyingGlass(){
        super(Items. MAGNIFYING_GLASS );
    }

    /** Get the current Shell Info
     *  param@        gun - Shotgun object
     *  return         -1 - If something went wrong
     *          otherwise - return correspond type of the shell*/
    public int Effect(ShotGun gun){
        int type = gun.GetCurrShellType();
        if(type != -1) {
            System.out.println("Current Shell Type is " + type + "...");
            return 1;
        }
        else{
            System.out.println("Something went wrong...");
            return -1;
        }
    }
}
