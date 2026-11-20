
/**
 * Stores a player name, health, inventory, stage wins and handcuff state. Private fields are
 * accessed through methods; the inventory getter exposes the actual array.
 *
 * @author Leon
 */
public class Player {
    private int hp;
    private int maxHp;
    private Items[] items;
    private int stageWins;
    private String name;
    private boolean isCuffed;

    // DEVELOPED BY:Leon
    /**
     * Creates a player with two health points, maximum health two, eight empty slots,
     * zero wins and no handcuffs.
     * @param name the player name
     */
    public Player(String name){
        this.hp = 2;
        this.name = name;
        this.items = new Items[8];
        this.stageWins = 0;
        this.isCuffed = false;
        this.maxHp=2;
    }

    // DEVELOPED BY: Leon
    /**
     * Returns the player name.
     * @return the stored name
     */
    public String GetName(){
        return name;
    }

    // DEVELOPED BY: Leon
    /**
     * Assigns current health directly. This method does not clamp health to zero or
     * maximum health.
     * @param hp the health value to assign
     */
    public void SetHP(int hp){
        this.hp = hp;
    }

    // DEVELOPED BY: Leon
    /**
     * Returns current health.
     * @return the current health, which may be negative
     */
    public int GetHP(){
        return hp;
    }

    // DEVELOPED BY: Leon
    /**
     * Sets maximum health from the supplied stage without changing current health.
     * @param stage the stage configuration
     */
    public void SetMaxHp(Stage stage){
        this.maxHp = stage.GetStartHP();
    }

    // DEVELOPED BY: Leon
    /**
     * Returns the health limit used by stage.
     * @return the maximum health
     */
    public int GetMaxHp(){
        return maxHp;
    }

    // DEVELOPED BY: Leon
    /**
     *Heal the player
     * @param num the number of health points to add or subtract
     * @return 1 after updating health
     */
    public int AddHP(int num){
        hp+=num;
        return 1;
    }

    // DEVELOPED BY: Leon
    /**
     * Returns the actual inventory array, not a copy. Null elements represent unused
     * @return the mutable eight-slot inventory
     */
    public Items[] GetAllItem(){
        return this.items;
    }

    // DEVELOPED BY: Leon
    /**
     * Clears the selected inventory slot.
     * @param index the zero-based inventory slot, from 0 through 7
     */
    public void RemoveItem (int index){
        items[index]=null;
    }

    // DEVELOPED BY:Leon
    /**
     * Counts non-null inventory slots.
     * @return the number of items currently held
     */
    public int CountItems(){
        int count = 0;
        for(int i = 0;i < items.length;i++){
            if(items[i] != null){
                count++;
            }
        }
        return count;
    }

    public int GetStageWins(){
        return this.stageWins;
    }

    // DEVELOPED BY: RIMI
    /**
     * Increments the stage-win count by one; it does not assign a supplied value.
     */
    public void SetStageWins(){
        this.stageWins++;
    }

    // DEVELOPED BY: RIMI
    /** Consume the handcuff
     *  @return     true - A handcuff exists, and it is consumed
     *             false - No handcuff exists                       */
    public void ConsumeCuff(){
        this.isCuffed = false;
    }

    // DEVELOPED BY: RIMI
    /** Sets the handcuff flag. Turn skipping is handled by GameSystem. */
    public void Cuffed(){
        this.isCuffed = true;
    }

    // DEVELOPED BY: RIMI
    /** Checks the handcuff flag.
     *  @return true if the player is currently cuffed; false otherwise */
    public boolean GetIsCuffed(){
        return this.isCuffed;
    }
}

