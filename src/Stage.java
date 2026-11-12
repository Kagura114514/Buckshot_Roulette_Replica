/**
 * Stores stage health, shell limits and item distribution settings.
 *
 * @author Leon
 */
public class Stage {
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

    /**
     * Background rules for each round, such as starting health and the countdown value
     *
     * @param stageNum the intended stage number: 1, 2 or 3
     */
    public Stage(int stageNum){
        if (stageNum < 1 || stageNum > 3) {//预防报错
            System.out.println("Invalid stage. Using stage 1.");
            stageNum = 1;
        }
        else if(stageNum == FST_STAGE){
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

    /**
     * Returns the stored stage number.
     * The current constructors do not assign this field.
     *
     * @return the stored stage number, initially zero
     */
    public int GetStageNum(){
        return this.stageNum;
    }


    /**
     * Returns the stored starting health for each player.
     *
     * @return the starting health setting
     */
    public int GetStartHP(){
        return startHP;
    }

    /**
     * Returns the minimum number of shells per load.
     *
     * @return the stored minimum shell count
     */
    public int GetMinShellNum(){
        return minShellNum;
    }

    /**
     * Returns the maximum number of shells per load.
     *
     * @return the stored maximum shell count
     */
    public int GetMaxShellNum(){
        return maxShellNum;
    }

    /**
     * Returns the same item count as GetItemNum.
     * Here a round refers to a load of shells, not a single player turn.
     *
     * @return the number of items intended for each player per load
     */
    public int GetPerRoundItemNum(){
        return perRoundNum;
    }

    public int GetCurrStageNum() {
        return stageNum;
    }
}
