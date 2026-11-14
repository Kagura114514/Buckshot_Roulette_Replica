public class ShotGun {
    /** Items involved in this class are:
     ** Magnifying Glass : 可查看当前枪膛内是否为实弹或空包弹
     ** Beer             : 弹出当前枪膛内的子弹并跳过当前回合
     ** Handsaw          : 若此发子弹为实弹则造成2点伤害
     ** BurnerPhone      : 可预知剩余子弹中的第几发子弹为实弹或空包弹 (例：第四发为空包弹)
     ** Inverter         : 将当前枪膛内的子弹切换为实弹或空包弹（例：当前枪膛内为空包弹，使用道具后转为实弹
     */
    public static final int BLANK     = 0; // 空包弹
    public static final int LIVE      = 1; // 实弹
    public static final int EMPTY     = 2; // 无子弹状态（已射出）

    private int[]  bulletsInGun;
    private boolean isDoubleDMG;
    // Constructor
    public ShotGun(Stage stg){
        // Instantiate the object depends on the stage level
        if(stg.GetStageNum() == Stage.FST_STAGE){
            this.bulletsInGun = new int[randomShellNum(1)];
            initialize(this.bulletsInGun, 1);
        }
        else if(stg.GetStageNum() == Stage.SCD_STAGE){
            this.bulletsInGun = new int[randomShellNum(2)];
            initialize(this.bulletsInGun, 1);
        }
        else if(stg.GetStageNum() == Stage.TRD_STAGE){
            this.bulletsInGun = new int[randomShellNum(3)];
            initialize(this.bulletsInGun, 1);
        }

        isDoubleDMG   = false;
    }

    // DEVELOPED BY: RIMI
    /** Return the appropriate number of shells depends on the current stage players in
     *  @param  stg - stage level players current in
     *  @return     - appropriate number of shells depends on current stage                  */
    private int randomShellNum(int stg){
        int num = -1; // dummy value assigned
        if(stg == 1){
            num = (int)(Math.random() * 4) + 2; // Range 2-5
        }
        else if(stg == 2){
            num = (int)(Math.random() * 5) + 2; // Range 2-6
        }
        else if(stg == 3){
            num = (int)(Math.random() * 7) + 2; // Range 2-8
        }
        return num;
    }

    // DEVELOPED BY: RIMI
    /** Return either LIVE or BLANK (in integer) in a 1:1 probability for the other method to used
     *  @return     - LIVE or BLANK (int)                                                             */
    private int randomShellType(){
        int prob = (int)(Math.random() * 2);
        if(prob == 1){
            return LIVE;
        }
        else{
            return BLANK;
        }
    }

    // DEVELOPED BY: RIMI
    /** To help constructor to assign the shells type in the bulletsInGun array
     *  (There is a minimum number of a type of shell depend on the stage level,
     *   and the method will handle of it)
     *  @param  list    - The array list need to be assigned
     *  @param  limit   - The limit number to make sure the minimum number of shells */
    private void initialize(int[] list, int limit){
        int countLive;
        int countBlank;
        do {
            countLive = 0;
            countBlank = 0;
            for (int i = 0; i < list.length; i++) {
                list[i] = randomShellType();
                if(list[i] == LIVE){
                    countLive++;
                }
                else{
                    countBlank++;
                }
            }
        }while(countBlank < limit || countLive < limit);
    }
    // DEVELOPED BY: RIMI
    /** Return the number in words
     *  @param  num - The number needs to change into words
     *  @return     - The number in words                   */
    private String numInWords(int num){
        String str;
        switch (num){
            case 1:
                str = "first";
                break;
            case 2:
                str = "second";
                break;
            case 3:
                str = "third";
                break;
            case 4:
                str = "fourth";
                break;
            case 5:
                str = "fifth";
                break;
            case 6:
                str = "sixth";
                break;
            case 7:
                str = "seventh";
                break;
            case 8:
                str = "eighth";
                break;
            default:
                str = "something went wrong";
                break;
        }
        return str;
    }
    // DEVELOPED BY: RIMI
    /** Return the current shell position with either blank or live bullet
     *  @return     - the current shell position    */
    private int getCurrShellPos(){
        for(int i = 0; i < GetBulletsInGun().length; i++){
            if(GetBulletsInGun()[i] != EMPTY){
                return i;
            }
        }
        return -1;
    }

    public int[] GetBulletsInGun(){
        return this.bulletsInGun;
    }

    public void SetBulletsInGunEMPTY(){
        int position = getCurrShellPos();

        if (position >= 0) {
            GetBulletsInGun()[position] = EMPTY;
        }

    }

    // Magnifying Glass will need this
    public int GetCurrShellType(){
        for(int i = 0; i < GetBulletsInGun().length; i++){
            if(GetBulletsInGun()[i] != EMPTY){
                return this.GetBulletsInGun()[i];
            }
        }
        return -1;
    }

    public void SetCurrShellType(int shellType){
        for(int i = 0; i < GetBulletsInGun().length; i++){
            if(GetBulletsInGun()[i] != EMPTY){
                GetBulletsInGun()[i] = shellType;
                break;
            }
        }
    }

    public boolean GetIsDoubleDMG(){
        return this.isDoubleDMG;
    }

    public void SetIsDoubleDMG(boolean boo){
        this.isDoubleDMG = boo;
    }

    // BurnerPhone will need this
    /** Get a random shell info, (it won't give empty shell info)
     *  param@  - current stage level
     *  return  - Random line about the shell position info   */
    public String GetRandomShellInfo(){
        int current = getCurrShellPos();
        int remainingOpt = GetBulletsInGun().length - 1 - current;
        String line = "Something went wrong";
        if(current == -1 || current > GetBulletsInGun().length - 2){
            return "How Unfortunate...";
        }
        else{
            int index = (int)(Math.random() * remainingOpt) + current + 1;

            if(GetBulletsInGun()[index] == BLANK){
                line = "blank.";
            }
            else if(GetBulletsInGun()[index] == LIVE){
                line = "live.";
            }

            String order = numInWords(index + 1);

            return order + " shell ...\n   ...   " + line;
        }
    }
    // Inverter will need this
    // DEVELOPED BY: RIMI
    /** Revert the type of the current shell
     *  @return     true  - success
 *                  false - failed                */
    public boolean SwitchShellType(){
        if(GetCurrShellType() == LIVE){
            SetCurrShellType(BLANK);
            return true;
        }
        else if(GetCurrShellType() == BLANK){
            SetCurrShellType(LIVE);
            return true;
        }
        return false;
    }
    // Handsaw will need this
    // DEVELOPED BY: RIMI
    /** Set isDoubleDMG true
     *  return  1 - Set IsDoubleDMG = true successfully
     *         -1 - The IsDoubleDMG is already true     */
    public int DoubleTheDMG(){
        if(GetIsDoubleDMG() == false) {
            SetIsDoubleDMG(true);
            return 1;
        }
        else{
            return -1;
        }
    }
    // Will need this when shooting
    // DEVELOPED BY: RIMI
    /** Calculate the damage to the shotgun of the current round,
     *  the damage will be doubled if user uses a handsaw
     *  @return     - appropriate damage of the current round                */
    public int DamageCalc(){
        if(GetIsDoubleDMG()){
            SetIsDoubleDMG(false);
            return 2;
        }
        else {
            return 1;
        }
    }
    // 注意！ 射击后/使用beer时需要被调用
    // DEVELOPED BY: RIMI
    /** Eject the current shell after a shot
     *  @return  true  - ejected a shell successfully
     *           false - failed, maybe the gun has no bullets in it already */
    public int EjectShell(){
        int pos = getCurrShellPos();
        int type =  GetCurrShellType();
        if(pos < 0 || pos >= GetBulletsInGun().length){
            return -1;
        }
        else {
            GetBulletsInGun()[getCurrShellPos()] = EMPTY;
            return type;
        }
    }
}