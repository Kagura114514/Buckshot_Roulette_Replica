public class Player {
    private int hp;
    private Items[] items;
    private int stageWins;
    private boolean isPlayerMoveable;

    public Player(int hp){
        this.hp=hp;
    }

    public int GetHP(){
        return hp;
    }

    public int SetHP(int hp){
        this.hp=hp;
        return 1;
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

    public Items[] RemoveItem (int index{
        items[index]=null;
        return items;
    }

    public int GetItem(Items item,int index{
        items[index]=item;
        return 1;
    }

    public boolean UseItem(int index){
        items[index]=null;
        return true;
    }

    public boolean StealItem(Player opponent, int index){
        return true;
    }
}

