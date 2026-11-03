public abstract class ShotgunEffectItem extends Items {
    public ShotgunEffectItem(int type) {
        super(type);
    }

    public abstract int Effect(Player player, Player opponent, ShotGun gun);
}
