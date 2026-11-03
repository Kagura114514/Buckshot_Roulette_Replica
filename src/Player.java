public class Player {
    private int hp;
    private Items[] items;
    private int stageWins;
    private boolean isPlayerMoveable;
    private int maxHp;

    public Player(int hp){
        this.hp=hp;
    }

    public Items[] GetAllItem(){
        return this.items;
    }

    public int GetHP(){
        return hp;
    }

    public int SetHP(int hp){
        this.hp=hp;
        return 1;
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

    public void ChangeHP(ShotGun shotGun){

    }

    public boolean AddItem(Items item,int index){
        items[index]=item;
        return true;
    }

    public Items[] RemoveItem (int index){
        items[index]=null;
        return items;
    }

    public int GetItem(Items item,int index){
        items[index]=item;
        return 1;
    }

    public boolean UseItem(int index){
        if(index)
            items[index].Effect();
        items[index]=null;
        return true;
    }

    public boolean StealItem(Player opponent, int opponentIndex,int index){
        Items temp = opponent.GetAllItem()[opponentIndex];
        opponent.GetAllItem()[opponentIndex]=null;

        items[index]=temp;
        return true;
    }

    public boolean  ItemStolen(){  //不懂
        return false;
    }

    public void Cuffed(){
        isPlayerMoveable=false;
    }

}

