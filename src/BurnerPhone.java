public class BurnerPhone extends InformationItem {
    public BurnerPhone(){
        super(Items.BURNER_PHONE );
    }
    /** Get a random Shell Info
     *  param@        gun - Shotgun object
     *                stg - Current stage info
     *  return         1  - Will just print the Line  */
    public int Effect(ShotGun gun, Stage stg){
        System.out.println(gun.GetRandomShellInfo());
        return 1;
    }
}
