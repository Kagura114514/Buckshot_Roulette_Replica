public class Stage {
    public static final int FST_STAGE = 1;
    public static final int SCD_STAGE = 2;
    public static final int TRD_STAGE = 3;
    public static final int FST_HP    = 2;
    public static final int SCD_HP    = 4;
    public static final int TRD_HP    = 5;

    private int currStageNum;
    int stageNum;
    int startHP;
    int minShellNum;
    int maxShellNum;
    int perRoundNum;

    public Stage(){
    }

    public Stage(int stageNum){
        if(stageNum<1 || stageNum>3){
            if(stageNum== FST_STAGE){
                startHP=FST_HP;
                minShellNum=2;
                maxShellNum=5;
                perRoundNum=0;
            }
            else if(stageNum==SCD_STAGE){
                startHP=SCD_HP;
                minShellNum=2;
                maxShellNum=6;
                perRoundNum=2;
            }
            else{
                startHP=TRD_HP;
                minShellNum=2;
                maxShellNum=8;
                perRoundNum=4;
            }
        }
    }

    public int GetStageNum(){
        return stageNum;
    }

    public int GetCurrStageNum() {
        return currStageNum;
    }

    public int GetStartHP(){
        return startHP;
    }

    public int GetItemNum(){
        return perRoundNum;
    }

    public int GetMinShellNum(){
        return minShellNum;
    }

    public int GetMaxShellNum(){
        return maxShellNum;
    }

    public int GetPerRoundItemNum(){
        return perRoundNum;
    }
}
