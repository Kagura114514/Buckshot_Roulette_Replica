public class GameSystem {
    public static final int ERROR = -1;

    private final Player[] allPlayer = new Player[2];
    private Player currPlayer;  // 现在的玩家
    private ShotGun gun;
    private Stage stg;

    public GameSystem(){
        stg = new Stage(1);
        gun = new ShotGun(this.stg);
    }

    private boolean switchPlayer(Player p2){
        if(p2.consumeCuff() == true){
            return false;
        }
        else{
            return true;
        }
    }

    private Player getAnotherPlayer(Player p){
        if(p == GetAllPlayer()[0]){
            return  GetAllPlayer()[1];
        }
        else {
            return  GetAllPlayer()[0];
        }
    }

    public Player[] GetAllPlayer(){
        return allPlayer;
    }

    public void SetAllPlayerID(String name){
        if(GetAllPlayer()[0] == null) {
            GetAllPlayer()[0] = new Player(name);
        }
        else{
            GetAllPlayer()[1] = new Player(name);
        }
    }

    public Player GetCurrPlayer(){
        return currPlayer;
    }

    public void SetCurrPlayer(Player player){
        currPlayer = player;
    }

    public Stage GetStage(){
        return stg;
    }

    public void SetStage(int num){
        stg = new Stage(num);
    }

    public ShotGun GetGun(){
        return gun;
    }

    public void SetShotGun(Stage stg){
        gun = new ShotGun(stg);
    }

    public boolean isShoot(){
        return true;
    }

    public boolean HasRoundWinner(){
        return true;
    }

    public int GetGameWinner(){
        return 1;
    }

    public boolean SkipRound(){
        return true;
    }

    public void SwitchPlayer(){
        if(GetCurrPlayer() == GetAllPlayer()[0]){
            SetCurrPlayer(GetAllPlayer()[1]);
        }
        else{
            SetCurrPlayer(GetAllPlayer()[0]);
        }
    }

    public int GuessCoinSide(){
        int num=(int)(Math.random()*100)+1;
        if(num%2==1){
            return 1;
        }
        else{
            return 0;
        }
    }

    //检测在随机的弹夹中，有几个实弹和几个空弹并有序排列
    public int[] GetBulletsInDisplay(){
        int blankNum=0;
        int liveNum=0;
        for(int i=0;i<gun.GetBulletsInGun().length;i++){
            if(gun.GetBulletsInGun()[i]==0){
                blankNum++;
            }
            else{
                liveNum++;
            }
        }
        int[] list = new int[blankNum+liveNum];
        for(int i=0;i<blankNum;i++){
            list[i]=1;
        }
        for(int i=blankNum;i<blankNum+liveNum;i++){
            list[i]=0;
        }

        return list;
    }
    // DEVELOPED BY: RIMI
    /** Does the shooting work
     *  @param  target - The target currPlayer going to shoot
     *  @return     -1 - Something went wrong
     *               1 - Player shot the opponent and the shell is blank
     *               2 - Player shot the opponent and the shell is live
     *               3 - Player shot oneself and the shell is live
     *               4 - Player shot oneself and the shell is blank   */
    public int Shooting(boolean target){
        if(target) {  // if target is opponent
            if (gun.GetCurrShellType() == ShotGun.BLANK) {
                currPlayer.SetPlayerMoveable(!switchPlayer(getAnotherPlayer(currPlayer)));
                gun.SetBulletsInGunEMPTY();
                return 1;
            }
            else if (gun.GetCurrShellType() == ShotGun.LIVE) {
                int dmg = gun.DamageCalc();
                getAnotherPlayer(currPlayer).SetHP(getAnotherPlayer(currPlayer).GetHP() - dmg);
                currPlayer.SetPlayerMoveable(!switchPlayer(getAnotherPlayer(currPlayer)));
                gun.SetBulletsInGunEMPTY();
                return 2;
            }
        }
        else{        // if target is player oneself
             if (gun.GetCurrShellType() == ShotGun.LIVE) {
                int dmg = gun.DamageCalc();
                currPlayer.SetHP(currPlayer.GetHP() - dmg);
                currPlayer.SetPlayerMoveable(!switchPlayer(getAnotherPlayer(currPlayer)));
                gun.SetBulletsInGunEMPTY();
                return 3;
             }
             else if(gun.GetCurrShellType() == ShotGun.BLANK){
                return 4;
             }
        }
        return ERROR;
    }

    // DEVELOPED BY: LEON&RIMI
    /** To determine is there still bullet in the gun
     *  @return     true  - there is still bullets remain
     *              false - no bullets already */
    public boolean isRemainBullet(){
        for(int i = 0; i < gun.GetBulletsInGun().length ;i++){
            if(gun.GetBulletsInGun()[i] != ShotGun.EMPTY){
                return true;
            }
        }
        return false;
    }

}
