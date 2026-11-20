/**
 * Stores the starting health, shell-count limits and item allowance for a stage. GameSystem
 * selects the stage and other classes read its settings.
 *
 * @author Leon
 */
public class Stage {
    /** Constant identifier for stage. */
    public static final int FST_STAGE = 1;
    public static final int SCD_STAGE = 2;
    public static final int TRD_STAGE = 3;
    public static final int FST_HP    = 2;
    public static final int SCD_HP    = 4;
    public static final int TRD_HP    = 5;

    private int stageNum;
    private int startHP;
    private int minShellNum;
    private int maxShellNum;
    private int perRoundNum;

    public Stage(){
    }

    // DEVELOPED BY: Leon
    /**
     * Initializes the stage settings. An unsupported stage number falls back to stage 1.
     * @param stageNum the stage number, normally 1, 2 or 3
     */
    public Stage(int stageNum){
        if (stageNum < 1 || stageNum > 3) {//预防报错
            System.out.println("Invalid stage. Using stage 1.");
            stageNum = FST_STAGE;
        }
        if(stageNum == FST_STAGE){
            startHP = FST_HP;
            minShellNum = 2;
            maxShellNum = 5;
            perRoundNum = 0;
        }
        else if(stageNum == SCD_STAGE){
            startHP = SCD_HP;
            minShellNum = 2;
            maxShellNum = 6;
            perRoundNum = 2;
        }
        else{
            startHP = TRD_HP;
            minShellNum = 2;
            maxShellNum = 8;
            perRoundNum = 4;
        }
        this.stageNum = stageNum;

    }

    // DEVELOPED BY: Leon
    /**
     * Returns the corresponding stage setting.
     * @return the stored stage number
     */
    public int GetStageNum(){
        return this.stageNum;
    }


    // DEVELOPED BY: Leon
    /**
     * Returns the corresponding stage setting.
     * @return the starting health per player
     */
    public int GetStartHP(){
        return startHP;
    }



    // DEVELOPED BY:
    /**
     * Returns the corresponding stage setting.
     * @return the minimum shells per load
     */
    public int GetPerRoundItemNum(){
        return perRoundNum;
    }

}
