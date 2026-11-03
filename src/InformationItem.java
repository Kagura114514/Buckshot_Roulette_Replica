public abstract class InformationItem extends Items {
    public InformationItem(int type) {
        super(type);
    }

    public abstract int Effect(Player player, Player opponent, ShotGun gun);
}
