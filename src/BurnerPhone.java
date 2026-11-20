public class BurnerPhone extends InformationItem {
    public BurnerPhone(){
        super(Items.BURNER_PHONE );
    }
    //DEVELOPED BY: RIMI
    /** Get a random Shell Info
     *  @param        gun - Shotgun object
     *  @param        stg - Current stage info
     *  @return         1 - Will just print the Line  */
    public int Effect(ShotGun gun, Stage stg){
        System.out.println(gun.GetRandomShellInfo());
        return 1;
    }
}
