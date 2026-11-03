public class CigarettesPack extends HealthItem {
    public  CigarettesPack(){
        super(Items.CIGARETTES_PACK);
    }
    public int Effect(Player player, Player opponent, ShotGun gun){
        int Hp=player.GetHP();
        int maxHp=player.GetMaxHp();
        if(Hp < maxHp) {
            player.AddHP(1);
            return 1;
        }
        else{
            return -1;
        }
    }
}
