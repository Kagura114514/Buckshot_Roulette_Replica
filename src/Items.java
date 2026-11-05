public abstract class Items {
    public static final int EXPIRED_MEDECINE = 1;
    public static final int CIGARETTES_PACK = 2;
    public static final int MAGNIFYING_GLASS = 3;
    public static final int BURNER_PHONE = 4;
    public static final int HANDSAW = 5;
    public static final int INVERTER = 6;
    public static final int BEER = 7;
    public static final int ADRENALINE = 8;
    public static final int HANDCUFF = 9;

    private int type;

    public Items(int type) {
        this.type = type;
    }

    public int Gettype() {
        return type;
    }

    public abstract int Effect(Player player,Player opponent, ShotGun gun);

    public int Effect(Player player, Player opponent, ShotGun gun, int opponentIndex) {
        return Effect(player, opponent, gun);
    }
}

