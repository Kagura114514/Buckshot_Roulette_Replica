public class ShotGun {
    boolean[] bulletsInGun;
    boolean nextShell;
    boolean currShellType;

    public ShotGun(){
        this.bulletsInGun = new [RandomShell()];
        for(int i = 0; i < this.bulletsInGun.length; i++){
            bulletsInGun[i] = RandomBoo();

        }
    }

    private int RandomShell(){
        int num = (int)(Math.random() * 8) + 1;
        return num;
    }

    private boolean RandomBoo(){
        int prob = (int)(Math.random() * 2);
        if(prob == 1){
            return true;
        }
        else{
            return false;
        }
    }
}
