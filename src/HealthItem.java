public  abstract class HealthItem extends Items {
    public HealthItem(int type) {
        super(type);
    }


    public int Effect(Player player,Player opponentPlayer, ShotGun gun) {
        return Effect(player);
    }

    public  abstract int Effect(Player player);
}
