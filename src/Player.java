/**
 * Stores a player's health, inventory and ability to act.
 * The current constructor initializes only health; the inventory remains null,
 * maximum health remains zero and the movement flag remains false.
 *
 * @author Leon
 */
public class Player {
    private int type;
    private int hp;
    private Items[] items;
    private int stageWins;
    private boolean isPlayerMoveable;
    private int maxHp;
    private String name;

    public Player(int type,String name){
        this.type=type;
        this.name=name;

    }

    public String GetName(){
        return name;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /** Set the HP at the starting
     *
     *  @param hp  - current stage HP
     */
    public void SetHP(int hp){
        this.hp=hp;
    }

    /** Get the HP at every stage
     *
     * @return  - the HP at every stage
     */
    public int GetHP(){
        return hp;
    }

    /**
     * Sets maximum health without changing current health.
     *
     * @param maxHp the maximum health value to store
     * @return 1 after storing the value
     */
    public int SetMaxHp(int maxHp){
        this.maxHp=maxHp;
        return 1;
    }

    /**
     * Returns the stored maximum health.
     *
     * @return the maximum health value
     */
    public int GetMaxHp(){
        return maxHp;
    }

    /**
     * Adds the given amount to current health without enforcing maximum health.
     *
     * @param num the amount to add
     * @return 1 after updating health
     */
    public int AddHP(int num){
        hp+=num;
        return 1;
    }

    /**
     * Subtracts the given amount from current health without enforcing a lower limit.
     *
     * @param num the amount to subtract
     * @return 1 after updating health
     */
    public int MinusHP(int num){
        hp-=num;
        return 1;
    }

    public void ChangeHP(Stage stage){

    }

    /**
     * Returns the stored movement flag.
     *
     * @return true if the flag allows the player to act; false otherwise
     */
    public boolean GetPlayerMoveable(){
        return  isPlayerMoveable;
    }

    /**
     * Returns the actual inventory array, not a copy.
     *
     * @return Items[], or null if it has not been initialized
     */
    public Items[] GetAllItem(){
        return this.items;
    }

    /**
     * Stores an item in the selected slot, replacing any existing item.
     * The inventory must already be initialized.
     *
     * @param item the item to store; may be null
     * @param index the zero-based inventory slot
     * @return return true after the assignment completes
     *         return    if the inventory is null
     *         return if the index is outside the array
     */
    public boolean AddItem(Items item,int index){
        items[index]=item;
        return true;
    }

    /**
     * Clears the selected inventory slot.
     *
     * @param index the zero-based slot to clear
     * @return return   if the inventory is null
     * @       return   if the index is outside the array
     */
    public void RemoveItem (int index){
        items[index]=null;
    }

    /**
     * Calls an item's effect and clears its slot if the effect call returns normally.
     * The current implementation accepts only indices 1 through 9, ignores the
     * effect result code and does not check health or the movement flag.
     *
     * @param index the inventory slot; the current condition accepts 1 through 9
     * @param player the player using the item
     * @param opponent the opposing player
     * @param gun the shotgun currently used in the game
     * @param opponentIndex the opponent inventory slot passed to the item effect
     * @return true after the effect call and removal complete; false if index is
     *         outside the accepted range
     *
     *         return false if the accepted slot is accessed while the
     *         inventory or selected item is null
     *
     *         return false if an accepted index exceeds the array bounds
     */
    public boolean UseItem(int index,Player player,Player opponent, ShotGun gun,int opponentIndex){
        if(0<index && index<10) {
            items[index].Effect(player,opponent, gun,opponentIndex);
            items[index]=null;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Sets whether the player is allowed to act.
     *
     * @param isPlayerMoveable the new movement flag
     * @return the flag value assigned, not a success indicator
     */
    public boolean SetPlayerMoveable(boolean isPlayerMoveable){
        return  this.isPlayerMoveable=isPlayerMoveable;
    }

    /**
     * Sets the movement flag to false.
     * Turn skipping and releasing the handcuffs must be handled elsewhere.
     */
    public void Cuffed(){
        isPlayerMoveable=false;
    }


}

