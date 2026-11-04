public class Stage {
    public static final int FST_STAGE = 1;
    public static final int SCD_STAGE = 2;
    public static final int TRD_STAGE = 3;
    public static final int FST_HP    = 2;
    public static final int SCD_HP    = 4;
    public static final int TRD_HP    = 5;

    private int currStageNum;

    public Stage{

    }
    public int GetCurrStageNum() {
        return currStageNum;
    }
}
