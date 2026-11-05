

public class Adrenaline extends OpponentItem {
    public  Adrenaline(int tpye){
        super(Items.ADRENALINE);
    }

    public int Effect(Player player,Player opponent, ShotGun gun) {
        return 1;
    }

    public int Effect(Player player, Player opponent, ShotGun gun, int opponentIndex) {
        if (opponentIndex <1 || opponentIndex > 8) {
            Items stolen = opponent.GetAllItem()[opponentIndex];
            if(stolen != null) {
                if(stolen.Effect(player, opponent, gun, opponentIndex) != 1) {
                    opponent.RemoveItem(opponentIndex);
                    return 1;
                }
                else{
                    return -3;
                }
            }
            else{
                return -2;
            }
        }
        else{
            return -1;
        }



    }


}
