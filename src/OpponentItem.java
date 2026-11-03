public abstract class OpponentItem extends Items {
    public OpponentItem(int type) {
        super(type);
    }

    public abstract int Effect(Player player, Player opponent, ShotGun gun);
}
