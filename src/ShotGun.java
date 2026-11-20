public class ShotGun {
    /** Items involved in this class are:
     ** Magnifying Glass
     ** Beer
     ** Handsaw
     ** BurnerPhone
     ** Inverter
     */
    public static final int BLANK     = 0;
    public static final int LIVE      = 1;
    public static final int EMPTY     = 2; // when no bullet

    private int[]  bulletsInGun;
    private boolean isDoubleDMG;
    // Constructor
    /** Loads a new shotgun for stage 1, 2 or 3, with at least one live and one blank shell.
     *  @param stg a non-null, initialized stage whose number is 1, 2 or 3                     */
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
        }while(countBlank < limit || countLive < limit);  // repeat until the randomized ratio of the shells are valid
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
                str = "something went wrong"; // won't reach here if in normal case
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
    // DEVELOPED BY: RIMI
    /** Returns the actual firing-order array. Consumed positions contain EMPTY.
     *  @return     - the mutable shell array, not a copy    */
    public int[] GetBulletsInGun(){
        return this.bulletsInGun;
    }

    // DEVELOPED BY: RIMI
    /** Sets the first remaining shell EMPTY      */
    public void SetBulletsInGunEMPTY(){
        int position = getCurrShellPos();

        if (position >= 0) {
            GetBulletsInGun()[position] = EMPTY;
        }

    }

    // DEVELOPED BY: RIMI
    /** Looks up the next remaining shell without consuming it.
     *  @return BLANK or LIVE, or -1 if the gun is empty           */
    public int GetCurrShellType(){
        for(int i = 0; i < GetBulletsInGun().length; i++){ // track the type of the current shell
            if(GetBulletsInGun()[i] != EMPTY){
                return this.GetBulletsInGun()[i];
            }
        }
        return -1;
    }
    // DEVELOPED BY: RIMI
    /** Replaces the first remaining shell type; does nothing if the gun is empty.
     *  @param shellType - the value to store, normally BLANK or LIVE; not validated here           */
    public void SetCurrShellType(int shellType){
        for(int i = 0; i < GetBulletsInGun().length; i++){ // to reach to the current position of the shell
            if(GetBulletsInGun()[i] != EMPTY){
                GetBulletsInGun()[i] = shellType;
                break; // instant break after a value is assigned
            }
        }
    }

    // DEVELOPED BY: RIMI
    /** Checks whether the handsaw damage boost is active.
     *  @return true - when double damage is enabled           */
    public boolean GetIsDoubleDMG(){
        return this.isDoubleDMG;
    }

    // DEVELOPED BY: RIMI
    /** Enable double damage
     *  @param boo - true to enable the boost, false to clear it           */
    public void SetIsDoubleDMG(boolean boo){
        this.isDoubleDMG = boo;
    }

    /** Get a random shell info, (it won't give empty shell info)
     *  param@  - current stage level
     *  return  - Random line about the shell position info   */
    public String GetRandomShellInfo(){
        int current = getCurrShellPos();
        int remainingOpt = GetBulletsInGun().length - 1 - current;
        String line = "Something went wrong";
        if(current == -1 || current > GetBulletsInGun().length - 2){ // if no shell or shell is only 1 left
            return "How Unfortunate...";
        }
        else{
            int index = (int)(Math.random() * remainingOpt) + current + 1; // index == between 2~current

            if(GetBulletsInGun()[index] == BLANK){
                line = "blank.";
            }
            else if(GetBulletsInGun()[index] == LIVE){
                line = "live.";
            }

            String order = numInWords(index - current + 1);

            return order + " shell ...\n   ...   " + line;
        }
    }

    // DEVELOPED BY: RIMI
    /** Revert the type of the current shell
     *  @return     true  - success
     *              false - failed                */
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

    // DEVELOPED BY: RIMI
    /** Eject the current shell after a shot
     *  @return  true  - ejected a shell successfully
     *           false - failed, maybe the gun has no bullets in it already */
    public int EjectShell(){
        int pos = getCurrShellPos();
        int type =  GetCurrShellType();
        if(pos < 0 || pos >= GetBulletsInGun().length){ // would not reach here in usual case
            return -1;
        }
        else {
            GetBulletsInGun()[getCurrShellPos()] = EMPTY;
            return type;
        }
    }

    // DEVELOPED BY: RIMI
    /** Builds an eight-slot display array with the same remaining live and blank counts in a randomized display order.
     *  Unused slots contain EMPTY. Does not reveal or change the actual firing order.
     *  @return a new eight-element display array                                                                         */
    public int[] GetBulletsInDisplay(){
        int[] list = new int[8];
        int liveCount = 0;
        int blankCount = 0;

        for(int i = 0; i < GetBulletsInGun().length; i++){ // record the type of the current shells
            if(GetBulletsInGun()[i] == LIVE){
                liveCount++;
            }
            else if(GetBulletsInGun()[i] == BLANK){
                blankCount++;
            }
        }

        for(int i = 0; i < 8; i++){  // assign the new array all EMPTY
            list[i] = ShotGun.EMPTY;
        }

        int totalShell = liveCount + blankCount;
        for(int i = 0; i < totalShell; i++){    // assign either LIVE or BLANK to the new list with amount according
            int v = (int)(Math.random() * 2);   // to the BulletsInGun list with random order
            if(v == 0 && liveCount > 0){
                list[i] = ShotGun.LIVE;
                liveCount--;
            }
            else if(blankCount > 0){
                list[i] = ShotGun.BLANK;
                blankCount--;
            }
            else{
                list[i] = ShotGun.LIVE;
                liveCount--;
            }
        }

        return list;
    }
}