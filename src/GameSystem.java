import java.util.Scanner;
/**
 * Coordinates players, stages, the shotgun and item effects. Calls item effects
 * polymorphically and removes successfully used items from their owners.
 *
 * @author TODO: confirm original code author(s)
 */
public class GameSystem {
    public static final int ERROR = -1;

    private final Player[] allPlayer = new Player[2];
    private Player currPlayer;  // 现在的玩家
    private ShotGun gun;
    private Stage stg;
    private Scanner input;
    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Creates the first-stage configuration, its shotgun and an input scanner. Players
     * are registered separately.
     */
    public GameSystem(){
        stg = new Stage(1);
        gun = new ShotGun(this.stg);
        input = new Scanner(System.in);
    }
    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Finds the other player by comparing object references. The argument should be one
     * of the two registered players.
     * @param p one of the registered players
     * @return player 2 when p is player 1; otherwise player 1
     */
    public Player getAnotherPlayer(Player p) {
        if (p == GetAllPlayer()[0]) {
            return GetAllPlayer()[1];
        } else {
            return GetAllPlayer()[0];
        }
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Returns the actual array of registered players.
     * @return the mutable two-player array
     */
    public Player[] GetAllPlayer(){
        return allPlayer;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Creates player 1 if that slot is empty; otherwise creates or replaces player 2.
     * @param name the player name
     */
    public void SetAllPlayerID(String name){
        if(GetAllPlayer()[0] == null) {
            GetAllPlayer()[0] = new Player(name);
        }
        else{
            GetAllPlayer()[1] = new Player(name);
        }
    }


    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Returns the player currently acting.
     * @return the current player
     */
    public Player GetCurrPlayer(){
        return currPlayer;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Assigns the current player.
     * @param player the player using or receiving the effect
     */
    public void SetCurrPlayer(Player player){
        currPlayer = player;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Returns the current stage configuration.
     * @return the current stage
     */
    public Stage GetStage(){
        return stg;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Replaces the stage configuration. Player health and inventory are not reset by this
     * method.
     * @param num the requested stage number
     */
    public void SetStage(int num){
        stg = new Stage(num);
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Returns the current shotgun.
     * @return the current shotgun
     */
    public ShotGun GetGun(){
        return gun;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Creates a new shotgun using the supplied stage, replacing the previous load.
     * @param stg the stage configuration
     */
    public void SetShotGun(Stage stg){
        gun = new ShotGun(stg);
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Placeholder for a shooting check; currently always returns true.
     * @return true regardless of game state
     */
    public boolean isShoot(){
        return true;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Checks whether either player has won at least two stages.
     * @return 10 for player 1, 11 for player 2, or 0 if neither has won
     */
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

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Records one stage win and returns its winner. Call exactly once after a stage ends,
     * with exactly one player dead; otherwise the result is not a valid winner check.
     * @param p1 the first player supplied to this method
     * @param p2 the second player supplied to this method
     * @return p2 if p1 has no health; otherwise p1
     */
    public Player SetRoundWinner(Player p1, Player p2){
        if(p1.GetHP() <= 0) {
            p2.SetStageWins();
            return p2;
        }
        else  {
            p1.SetStageWins();
            return p1;
        }

    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Placeholder for skipping a round; currently does not change game state.
     * @return true regardless of game state
     */
    public boolean SkipRound(){
        return true;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Changes the current player to the other player without checking handcuffs.
     */
    public void SwitchPlayer(){
        if(GetCurrPlayer() == GetAllPlayer()[0]){
            SetCurrPlayer(GetAllPlayer()[1]);
        }
        else{
            SetCurrPlayer(GetAllPlayer()[0]);
        }
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Simulates a fair coin using the parity of a random integer from 1 through 100.
     * @return 0 or 1 with equal probability
     */
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

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Builds a separate array of remaining shells grouped by type. This reveals counts
     * without revealing the actual firing order.
     * @return a new array containing blanks followed by live shells, excluding EMPTY
     *     positions
     */
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
    /**
     * Fires at the selected target and consumes one shell. Applies damage for live shells
     * and checks handcuffs when changing turns; a blank shot at oneself keeps the turn.
     * @param target true to shoot the opponent; false to shoot oneself
     * @return 1 for opponent/blank, 2 for opponent/live, 3 for self/live, 4 for
     *     self/blank, or ERROR if no valid shell is found
     */
    public int Shooting(boolean target){
        if(target) {  // if target is opponent
            if (gun.GetCurrShellType() == ShotGun.BLANK) {
                gun.SetBulletsInGunEMPTY();
                System.out.println(" shot the opponent. Blank!");
                if(gun.GetIsDoubleDMG() == true){
                    gun.SetIsDoubleDMG(false);
                    System.out.println("The gun barrel has reinstalled/...");
                }
                switchPlayerCuff();
                return 1;
            }
            else if (gun.GetCurrShellType() == ShotGun.LIVE) {
                int dmg = gun.DamageCalc();
                getAnotherPlayer(currPlayer).SetHP(getAnotherPlayer(currPlayer).GetHP() - dmg);
                System.out.println(" shot the opponent. Live!\n");
                gun.SetBulletsInGunEMPTY();
                switchPlayerCuff();
                return 2;
            }
        }
        else{        // if target is player oneself
            if (gun.GetCurrShellType() == ShotGun.LIVE) {
                int dmg = gun.DamageCalc();
                currPlayer.SetHP(currPlayer.GetHP() - dmg);
                System.out.println(" shot yourself. Live!\n");
                gun.SetBulletsInGunEMPTY();
                switchPlayerCuff();
                return 3;
            }
            else if(gun.GetCurrShellType() == ShotGun.BLANK){
                gun.SetBulletsInGunEMPTY();
                System.out.println(" shot yourself. Blank!");
                if(gun.GetIsDoubleDMG() == true){
                    gun.SetIsDoubleDMG(false);
                    System.out.println("The gun barrel has reinstalled/...");
                }
                return 4;
            }
        }
        return ERROR;
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Checks the opponent handcuff flag. Clears an active cuff and keeps the current
     * player; otherwise changes players.
     */
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
    /**
     * Checks whether any shell position is not EMPTY.
     * @return true if a blank or live shell remains; otherwise false
     */
    public boolean isRemainBullet(){
        for(int i = 0; i < gun.GetBulletsInGun().length ;i++){
            if(gun.GetBulletsInGun()[i] != ShotGun.EMPTY){
                return true;
            }
        }
        return false;
    }
    // for testing

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Prints the raw shell array for debugging, including its actual order.
     * @param gun the shotgun involved in the operation
     */
    public void DisplayBullets(ShotGun gun){
        for(int i = 0; i < gun.GetBulletsInGun().length; i++){
            System.out.print(gun.GetBulletsInGun()[i] + " ");
        }
        System.out.println();
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Creates one of the nine concrete item types with equal probability.
     * @return a newly constructed item
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

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Stores an item in the current player inventory, replacing any existing item at that
     * position. The caller must validate the position.
     * @param position the zero-based inventory slot, from 0 through 7
     * @param item the item to store or describe
     */
    public void AddItem(int position,Items item){
        GetCurrPlayer().GetAllItem()[position]=item;
    }

    // For other items to use

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Invokes the selected item effect and clears its slot only when the effect returns
     * 1. The caller must supply a valid, occupied slot; the indexed overload also
     * forwards the opponent selection.
     * @param index the zero-based inventory slot, from 0 through 7
     */
    public void UseItem(int index) {
        // Dynamic dispatch chooses the actual item subclass effect.
        // Only a success result consumes the inventory item.
        if(GetCurrPlayer().GetAllItem()[index].Effect(GetCurrPlayer(), getAnotherPlayer(GetCurrPlayer()), GetGun(), GetStage())==1) {
            GetCurrPlayer().GetAllItem()[index] = null;
        }
    }
    // For adrenaline to use

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Invokes the selected item effect and clears its slot only when the effect returns
     * 1. The caller must supply a valid, occupied slot; the indexed overload also
     * forwards the opponent selection.
     * @param index the zero-based inventory slot, from 0 through 7
     * @param opponentIndex the zero-based opponent inventory slot, from 0 through 7
     * @param stage the stage configuration
     */
    public void UseItem(int index, int opponentIndex,Stage stage) {
        // Dynamic dispatch chooses the actual item subclass effect.
        // Only a success result consumes the inventory item.
        if(GetCurrPlayer().GetAllItem()[index].Effect(GetCurrPlayer(), getAnotherPlayer(GetCurrPlayer()), GetGun(), opponentIndex,stage)==1) {
            GetCurrPlayer().GetAllItem()[index] = null;
        }
    }

}
