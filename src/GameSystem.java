import java.util.Scanner;

public class GameSystem {
    public static final int ERROR = -1;

    private final Player[] allPlayer = new Player[2];
    private Player currPlayer;  // 现在的玩家
    private ShotGun gun;
    private Stage stg;
    private Scanner input;

    public GameSystem(){
        stg = new Stage(1);
        gun = new ShotGun(this.stg);
        input = new Scanner(System.in);
    }

    public Player getAnotherPlayer(Player p) {
        if (p == GetAllPlayer()[0]) {
            return GetAllPlayer()[1];
        } else {
            return GetAllPlayer()[0];
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

    public int GetGameWinner() {
        if(allPlayer[0].GetStageWins() >= 2){
            return 10;
        }
        else if(allPlayer[1].GetStageWins() >= 2){
            return 11;
        }
        else{
            return 0;
        }
    }

    public void SetRoundWinner(Player p1, Player p2){
        if(p1.GetHP() <= 0) {
            p2.SetStageWins();
        }
        else if(p2.GetHP() <= 0) {
            p1.SetStageWins();
        }
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
            else if (gun.GetBulletsInGun()[i] == ShotGun.LIVE) {
                liveNum++;
            }
        }
        int[] list = new int[blankNum + liveNum];
        for (int i = 0; i < blankNum; i++) {
            list[i] = ShotGun.BLANK;
        }
        for (int i = blankNum; i < blankNum + liveNum; i++) {
            list[i] = ShotGun.LIVE;
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
        if(target) { // if target is opponent
            if (gun.GetCurrShellType() == ShotGun.BLANK) {
                 gun.SetBulletsInGunEMPTY();
                 switchPlayerCuff();
                 return 1;
            }
            else if (gun.GetCurrShellType() == ShotGun.LIVE) {
                 int dmg = gun.DamageCalc();
                 getAnotherPlayer(currPlayer).SetHP(getAnotherPlayer(currPlayer).GetHP() - dmg);
                 gun.SetBulletsInGunEMPTY();
                 switchPlayerCuff();
                 return 2;
            }
        }
        else{        // if target is player oneself
             if (gun.GetCurrShellType() == ShotGun.LIVE) {
                 int dmg = gun.DamageCalc();
                 currPlayer.SetHP(currPlayer.GetHP() - dmg);
                 gun.SetBulletsInGunEMPTY();
                 switchPlayerCuff();
                 return 3;
             }
             else if(gun.GetCurrShellType() == ShotGun.BLANK){
                 gun.SetBulletsInGunEMPTY();
                 switchPlayerCuff();
                 return 4;
             }
        }
        return ERROR;
    }

    private void switchPlayerCuff() {
        // 如果opponent cuffed状态 == true
        if (getAnotherPlayer(GetCurrPlayer()).GetIsCuffed() == true) {
            getAnotherPlayer(GetCurrPlayer()).ConsumeCuff();
            System.out.println(getAnotherPlayer(GetCurrPlayer()).GetName() + " is cuffed...unable to move...");
        }
        // 如果opponent cuffed状态 == false
        else if (getAnotherPlayer(GetCurrPlayer()).GetIsCuffed() == false) {
            SetCurrPlayer(getAnotherPlayer(GetCurrPlayer()));
        }
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
    // for testing
    public void DisplayBullets(ShotGun gun){
        for(int i = 0; i < gun.GetBulletsInGun().length; i++){
            System.out.print(gun.GetBulletsInGun()[i] + " ");
        }
        System.out.println();
    }
    /**
     * Randomly creates one of the nine item types.
     * Each type has an approximately equal chance of being selected.
     * This method creates the item but does not add it to a player's inventory.
     *
     * @return a new randomly selected item
     */
    public Items RandomCreatItem(){
        int type = (int)(Math.random() * 9) + 1;
        Items item;
        if(type==1){
            item=new ExpiredMedecine();
            return item;
        }
        else if(type==2){
            item=new CigarettesPack();
            return item;
        }
        else if(type==3){
            item=new MagnifyingGlass();
            return item;
        }
        else if(type==4){
            item=new BurnerPhone();
            return item;
        }
        else if(type==5){
            item=new HandSaw();
            return item;
        }
        else if(type==6){
            item=new Inverter();
            return item;
        }
        else if(type==7){
            item=new Beer();
            return item;
        }
        else if(type==8){
            item=new Adrenaline();
            return item;
        }
        else {
            item=new Handcuff();
            return item;
        }
    }
    /**
     * Places an item in the current player's inventory at the specified position.
     * Replaces any item already stored in that slot.
     *
     * @param position the inventory index, starting from 0; must be within bounds
     * @param item the item to store; null clears the slot
     */
    public void AddItem(int position,Items item){
        GetCurrPlayer().GetAllItem()[position]=item;
    }

    // For other items to use
    public void UseItem(int index) {
        if(GetCurrPlayer().GetAllItem()[index].Effect(GetCurrPlayer(), getAnotherPlayer(GetCurrPlayer()), GetGun(), GetStage())==1) {
            GetCurrPlayer().GetAllItem()[index] = null;
        }
    }
    // For adrenaline to use
    public void UseItem(int index, int opponentIndex,Stage stage) {
        if(GetCurrPlayer().GetAllItem()[index].Effect(GetCurrPlayer(), getAnotherPlayer(GetCurrPlayer()), GetGun(), opponentIndex,stage)==1) {
            GetCurrPlayer().GetAllItem()[index] = null;
        }
    }

}
