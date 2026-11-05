public abstract class InformationItem extends Items {
    public InformationItem(int type) {
        super(type);
    }

    public int Effect(Player player,Player opponentPlayer, ShotGun gun) {
        return Effect( gun);
    }

    public  abstract int Effect(ShotGun gun);
}
