public class ShotGun {
    /** Items involved in this class are:
     ** Magnifying Glass : 可查看当前枪膛内是否为实弹或空包弹
     *  Beer             : 弹出当前枪膛内的子弹并跳过当前回合
     *  Handsaw          : 若此发子弹为实弹则造成2点伤害
     ** BurnerPhone      : 可预知剩余子弹中的第几发子弹为实弹或空包弹 (例：第四发为空包弹)
     ** Inverter         : 将当前枪膛内的子弹切换为实弹或空包弹（例：当前枪膛内为空包弹，使用道具后转为实弹
     */
    public static final int BLANK     = 0; // 空包弹
    public static final int LIVE      = 1; // 实弹
    public static final int EMPTY     = 2; // 无子弹状态（已射出）

    private int[]  bulletsInGun;
    private int       nextShell;
    private int   currShellType;
    private boolean isDoubleDMG;
    // Constructor
    public ShotGun(Stage stg){
        // Instantiate the object depends on the stage level
        if(stg.GetCurrStageNum() == Stage.FST_STAGE){
            this.bulletsInGun = new int[randomShellNum(1)];
            initialize(this.bulletsInGun, 1);
        }
        else if(stg.GetCurrStageNum() == Stage.SCD_STAGE){
            this.bulletsInGun = new int[randomShellNum(2)];
            initialize(this.bulletsInGun, 2);
        }
        else if(stg.GetCurrStageNum() == Stage.TRD_STAGE){
            this.bulletsInGun = new int[randomShellNum(3)];
            initialize(this.bulletsInGun, 2);
        }

        nextShell     = this.bulletsInGun[1];
        currShellType = this.bulletsInGun[0];
        isDoubleDMG   = false;
    }

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

    private int randomShellType(){
        int prob = (int)(Math.random() * 2);
        if(prob == 1){
            return LIVE;
        }
        else{
            return BLANK;
        }
    }

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
        }
        return str;
    }

    public int GetNextShell(){
        return this.nextShell;
    }
    // Magnifying Glass will need this
    public int GetCurrShellType(){
        for(int i = 0; i < bulletsInGun.length; i++){
            if(bulletsInGun[i] != EMPTY){
                SetCurrShellType(bulletsInGun[i]);
                return this.bulletsInGun[i];
            }
        }
        return -1;
    }

    public void SetCurrShellType(int type){
        currShellType = type;
    }

    public boolean isDoubleDMG(){
        return this.isDoubleDMG;
    }

    public void RevertIsDoubleDMG(){
        if(this.isDoubleDMG){
            this.isDoubleDMG = false;
        }
        else{
            this.isDoubleDMG = true;
        }
    }
    // BurnerPhone will need this
    public String GetRandomShellInfo(int stg){
        if(this.bulletsInGun[bulletsInGun.length - 2] == EMPTY){
            return "How Unfortunate...";
        }
        else{
            int index;
            do{
                index = randomShellType(stg);
            }while(this.bulletsInGun[index] == EMPTY);

            String line;
            if(bulletsInGun[index] == BLANK){
                line = "blank.";
            }
            else if(bulletsInGun[index] == LIVE){
                line = "live.";
            }

            String order = numInWords(index);

            return order + " shell ...\n   ...   " + line;
        }
    }
    // Inverter will need this
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


}
