public class ExpiredMedecine extends HealthItem {
    public  ExpiredMedecine(){
        super(Items.EXPIRED_MEDECINE);
    }

    public int Effect(Player player, Player opponent, ShotGun gun){
        int Hp=player.GetHP();
        int maxHp=player.GetMaxHp();
        if(Hp < maxHp) {
            if(maxHp-Hp==1) {
                player.AddHP(1);
            }
            else{
                player.AddHP(2);
            }
            return 1;
        }
        else{
            return -1;
        }
    }
}
