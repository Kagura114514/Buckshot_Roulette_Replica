public class CigarettesPack extends HealthItem {
    public  CigarettesPack(int tpye){
        super(type);
    }
    public int Effect(Player player){
        if(player.GetHP() < player.GetMaxHp()) {
            player.AddHP(1);
            return 1;
        }
        else{
            return -1;
        }
    }
}
