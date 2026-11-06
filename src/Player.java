public class Player {
    private int hp;
    private Items[] items;
    private int stageWins;
    private boolean isPlayerMoveable;
    private int maxHp;

    public Player(int hp){
        this.hp=hp;
    }

    /** Set the HP at the starting
     *  @param hp  - current stage HP*/
    public void SetHP(int hp){
        this.hp=hp;
    }

    /** Get the HP at every stage
     *  @return  - the HP at every stage   */
    public int GetHP(){
        return hp;
    }

    public int SetMaxHp(int maxHp){
        this.maxHp=maxHp;
        return 1;
    }

    public int GetMaxHp(){
        return maxHp;
    }


    public int AddHP(int num){
        hp+=num;
        return 1;
    }

    public int MinusHP(int num){
        hp-=num;
        return 1;
    }

    public void ChangeHP(Stage stage){

    }

    public boolean GetPlayerMoveable(){
        return  isPlayerMoveable;
    }

    public Items[] GetAllItem(){
        return this.items;
    }

    public boolean AddItem(Items item,int index){
        items[index]=item;
        return true;
    }

    public void RemoveItem (int index){
        items[index]=null;
    }

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

    public boolean SetPlayerMoveable(boolean isPlayerMoveable){
        return  this.isPlayerMoveable=isPlayerMoveable;
    }

    public void Cuffed(){
        isPlayerMoveable=false;
    }

}

