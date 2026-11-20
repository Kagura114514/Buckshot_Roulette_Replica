import java.util.Scanner;
/**
 * Coordinates players, stages, the shotgun and item effects. Calls item effects
 * polymorphically and removes successfully used items from their owners.
 *
 * @author RIMI & LEON
 */
public class GameSystem {
    private final Player[] all_Player = new Player[2];
    private Player currPlayer;
    private ShotGun gun;
    private Stage stg;
    private Scanner input;

    // DEVELOPED BY: RIMI
    /** Creates the first-stage configuration, its shotgun and an input scanner.
     *  Players are registered separately.                                                          */
    public GameSystem(){
        stg = new Stage(1);
        gun = new ShotGun(this.stg);
        input = new Scanner(System.in);
    }

    // DEVELOPED BY: RIMI
    /** Finds the other player by comparing object references.
     *  The argument should be one of the two registered players.
     *  @param  p - one of the registered players
     *  @return player 2 when p is player 1; otherwise player 1                             */
    public Player GetAnotherPlayer(Player p) {
        if (p == GetAllPlayer()[0]) {
            return GetAllPlayer()[1];
        } else {
            return GetAllPlayer()[0];
        }
    }

    // DEVELOPED BY: RIMI
    /** Returns the actual array of registered players.
     *  @return the mutable two-player array            */
    public Player[] GetAllPlayer(){
        return all_Player;
    }

    // DEVELOPED BY: RIMI
    /** Creates player 1 if that slot is empty; otherwise creates or replaces player 2.
     *  @param name the player name                                                     */
    public void SetAllPlayerID(String name){
        if(GetAllPlayer()[0] == null) {
            GetAllPlayer()[0] = new Player(name);
        }
        else{
            GetAllPlayer()[1] = new Player(name);
        }
    }


    // DEVELOPED BY: RIMI
    /** Returns the player currently acting.
     *  @return the current player              */
    public Player GetCurrPlayer(){
        return currPlayer;
    }

    // DEVELOPED BY: RIMI
    /** Assigns the current player.
     *  @param player - the player */
    public void SetCurrPlayer(Player player){
        currPlayer = player;
    }

    // DEVELOPED BY: RIMI
    /** Returns the current stage configuration.
     * @return the current stage                    */
    public Stage GetStage(){
        return stg;
    }

    // DEVELOPED BY: RIMI
    /** Replaces the stage configuration.
     *  Player health and inventory are not reset by this method.
     *  @param num the requested stage number                                           */
    public void SetStage(int num){
        stg = new Stage(num);
    }

    // DEVELOPED BY: RIMI
    /** Returns the current shotgun.
     *  @return the current shotgun  */
    public ShotGun GetGun(){
        return gun;
    }

    // DEVELOPED BY: RIMI
    /** Creates a new shotgun using the supplied stage, replacing the previous load.
     *  @param stg - the stage configuration                                              */
    public void SetShotGun(Stage stg){
        gun = new ShotGun(stg);
    }

    // DEVELOPED BY: RIMI
    /** Checks whether either player has won at least two stages.
     *  @return 10 for player 1, 11 for player 2, or 0 if neither has won  */
    public int GetGameWinner() {
        if(all_Player[0].GetStageWins() >= 2){
            return 10;
        }
        else if(all_Player[1].GetStageWins() >= 2){
            return 11;
        }
        else{
            return 0;
        }
    }

    // DEVELOPED BY: RIMI
    /** Records one stage win and returns its winner. Call exactly once after a stage ends,
     *  with exactly one player dead; otherwise the result is not a valid winner check.
     *  @param p1 the first player supplied to this method
     *  @param p2 the second player supplied to this method
     *  @return p2 if p1 has no health; otherwise p1
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

    // DEVELOPED BY: RIMI
    /** Changes the current player to the other player without checking handcuffs.  */
    public void SwitchPlayer(){
        if(GetCurrPlayer() == GetAllPlayer()[0]){
            SetCurrPlayer(GetAllPlayer()[1]);
        }
        else{
            SetCurrPlayer(GetAllPlayer()[0]);
        }
    }

    // DEVELOPED BY: Leon
    /**
     * Get a fair coin using the parity of a random integer from 1 through 100.
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

    // DEVELOPED BY: Leon
    /**
     * Builds a separate array of remaining shells grouped by type. This reveals counts
     * without revealing the actual firing order.
     * @return a new array containing blanks followed by live shells, excluding EMPTY
     *     positions
     */
    public int[] GetBulletsInDisplay(){
        int blankNum=0;
        int liveNum=0;
        for(int i=0;i<gun.GetBulletsInGun().length;i++){//count number of blanknum
            if(gun.GetBulletsInGun()[i]==0){
                blankNum++;
            }
            else if (gun.GetBulletsInGun()[i] == ShotGun.LIVE) {//count number of livenum
                liveNum++;
            }
        }
        int[] list = new int[blankNum + liveNum];
        for (int i = 0; i < blankNum; i++) {//add
            list[i] = ShotGun.BLANK;
        }
        for (int i = blankNum; i < blankNum + liveNum; i++) {//add
            list[i] = ShotGun.LIVE;
        }

        return list;
    }

    // DEVELOPED BY: RIMI
    /** Resolves a shot at the opponent or the current player. A valid shot consumes one shell.
     *  Live shells deal calculated damage; shooting oneself with a blank preserves the turn.
     *  Other shots apply the handcuff-aware turn change.
     *  @param target true to shoot the opponent, false to shoot oneself
     *  @return a description of the shot, or the string ERROR if no valid shell is available   */
    public String Shooting(boolean target){
        String movement = null;
        if(target) {  // if target is opponent
            if (gun.GetCurrShellType() == ShotGun.BLANK) { // if shell is blank
                gun.SetBulletsInGunEMPTY();  // set current shell EMPTY
                movement = GetCurrPlayer().GetName() + " shot " + GetAnotherPlayer(GetCurrPlayer()).GetName() + ". The bullet was Blank!";
                if(gun.GetIsDoubleDMG() == true){       //if a handsaw is enabled
                    gun.SetIsDoubleDMG(false);
                    movement += "The gun barrel has reinstalled/...";
                }
                switchPlayerCuff();
                return movement;
            }
            else if (gun.GetCurrShellType() == ShotGun.LIVE) { // if shell is live
                int dmg = gun.DamageCalc();
                GetAnotherPlayer(currPlayer).SetHP(GetAnotherPlayer(currPlayer).GetHP() - dmg);
                movement = GetCurrPlayer().GetName() + " shot " + GetAnotherPlayer(GetCurrPlayer()).GetName() + ". The bullet was Live!\n";
                gun.SetBulletsInGunEMPTY(); // set current shell EMPTY
                switchPlayerCuff();
                return movement;
            }
        }
        else{        // if target is player oneself
            if (gun.GetCurrShellType() == ShotGun.LIVE) {  // if shell is live
                int dmg = gun.DamageCalc();
                currPlayer.SetHP(currPlayer.GetHP() - dmg);
                movement = GetCurrPlayer().GetName() + " shot themself. Unfortunately the bullet was Live!\n";

                gun.SetBulletsInGunEMPTY();     // set current shell EMPTY
                switchPlayerCuff();
                return movement;
            }
            else if(gun.GetCurrShellType() == ShotGun.BLANK){  // if shell is blank
                gun.SetBulletsInGunEMPTY();     // set current shell EMPTY
                movement = GetCurrPlayer().GetName() + " shot themself. Luckily the bullet was Blank!";
                if(gun.GetIsDoubleDMG() == true){       //if a handsaw is enabled
                    gun.SetIsDoubleDMG(false);
                    movement += "The gun barrel has reinstalled/...";
                }
                return movement;
            }
        }
        return "ERROR";
    }

    // DEVELOPED BY: RIMI
    /** Checks the opponent handcuff flag. Clears an active cuff and keeps the current
     *  player; otherwise changes players.                                              */
    private void switchPlayerCuff() {
        // if opponent isCuffed  == true
        if (GetAnotherPlayer(GetCurrPlayer()).GetIsCuffed() == true) {
            GetAnotherPlayer(GetCurrPlayer()).ConsumeCuff();
            System.out.println(GetAnotherPlayer(GetCurrPlayer()).GetName() + " is cuffed...unable to move...");
        }
        // if opponent isCuffed  == false
        else if (GetAnotherPlayer(GetCurrPlayer()).GetIsCuffed() == false) {
            SetCurrPlayer(GetAnotherPlayer(GetCurrPlayer()));
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

    // DEVELOPED BY: Leon
    /**
     * Creates one of the nine concrete item types with equal probability.
     * @return a newly constructed item
     */
    public Items RandomCreatItem(){
        int type = (int)(Math.random() * 9) + 1;
        Items item;
        if(type==1){//determine item
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

    // DEVELOPED BY: Leon
    /**
     * Places an item in the current player's inventory, replacing any existing item in that slot.
     * @param position a valid zero-based inventory index from 0 through 7
     * @param item the item to store; this method does not validate it
     */
    public void AddItem(int position,Items item){
        GetCurrPlayer().GetAllItem()[position]=item;
    }

    // For other items to use

    // DEVELOPED BY: Leon&&Rimi
    /** Applies the selected item using the current player, opponent, shotgun and stage. Removes it only when its effect returns 1.
     *  @param index a valid zero-based index containing a non-null item                                                                    */
    public void UseItem(int index) {
        // Dynamic dispatch chooses the actual item subclass effect.
        // Only a success result consumes the inventory item.
        if(GetCurrPlayer().GetAllItem()[index].Effect(GetCurrPlayer(), GetAnotherPlayer(GetCurrPlayer()), GetGun(), GetStage())==1) {
            GetCurrPlayer().GetAllItem()[index] = null;
        }
    }

    // For adrenaline to use
    // DEVELOPED BY: Leon&&Rimi
    /** Applies an item that needs an opponent inventory index, such as adrenaline. Removes the current player's selected item only when its effect returns 1.
     *  @param index a valid zero-based index containing a non-null item
     *  @param opponentIndex the opponent slot passed to the effect
     *  @param stage the stage passed to the effect                                                                                                              */
    public void UseItem(int index, int opponentIndex,Stage stage) {
        // Dynamic dispatch chooses the actual item subclass effect.
        // Only a success result consumes the inventory item.
        if(GetCurrPlayer().GetAllItem()[index].Effect(GetCurrPlayer(), GetAnotherPlayer(GetCurrPlayer()), GetGun(), opponentIndex,stage)==1) {
            GetCurrPlayer().GetAllItem()[index] = null;
        }
    }

}
