public abstract class ShotgunEffectItem extends Items {
    public ShotgunEffectItem(int type) {
        super(type);
    }

    public int Effect(Player player,Player opponentPlayer, ShotGun gun) {
        return Effect( gun);
    }

    public  abstract int Effect(ShotGun gun);
}
