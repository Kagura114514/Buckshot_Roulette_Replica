public class Handcuff extends OpponentItem {
    public  Handcuff(int tpye){
        super(Items.HANDCUFF);
    }

    public int Effect(Player player,Player opponent, ShotGun gun) {
        return Effect(opponent);
    }
    public int Effect(Player opponent){
        if(opponent.GetPlayerMoveable()==true){
            opponent.Cuffed();
            return 1;
        }
        else{
            return -1;
        }
    }
}
