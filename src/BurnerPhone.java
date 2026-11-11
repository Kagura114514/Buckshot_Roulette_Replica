public class BurnerPhone extends InformationItem {
    public BurnerPhone(){
        super(Items.BURNER_PHONE );
    }

    public int Effect(ShotGun gun, Stage stg){
        System.out.println(gun.GetRandomShellInfo(stg.GetCurrStageNum()));
        return 1;
    }
}
