public abstract class HealthItem extends Items {
    public HealthItem(int type) {
        super(type);
    }

    public abstract int Effect(Player player, Player opponent, ShotGun gun);
}
