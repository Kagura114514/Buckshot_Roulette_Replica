import java.util.Scanner;

/**
 * Provides console menus, board drawings, item labels and input helpers. GameMain supplies
 * game data for display; this class also reads names and inventory positions.
 *
 * @author Leon & Rimi
 */
public class Displayer {
    private Scanner input;

    public Displayer() {
        input = new Scanner(System.in);
    }



    // DEVELOPED BY: Leon
    /**
     * Builds a one-line shell display with eight positions. Prints a space as a side effect for unrecognized values.
     * @param list the shell values; missing positions are shown as empty
     * @return the formatted shell symbols and separators
     */
    private int blankBulletNum(int[] list){
        int num=0;
        for(int i=0;i<list.length;i++){
            if(list[i]==0){
                num++;
            }
        }
        return num;
    }
    // DEVELOPED BY: Leon
    /**
     * Counts array entries with the live-shell code 1.
     * @param list the array of shell codes
     * @return the number of live shells
     */
    private int liveBulletNum(int[] list){
        int num=0;
        for(int i=0;i<list.length;i++){
            if(list[i]==1){
                num++;
            }
        }
        return num;
    }
    // DEVELOPED BY: RIMI
    /** Prompts for option only between 1 or 2, accept other type of value
     *  @return - 1 or 2                                                    */
    public int EnterOpt(){
        String inp;
        while(true){
            inp = input.nextLine();
            if(inp.length() != 1 || inp == null || (inp.charAt(0) != 49 && inp.charAt(0) != 50)){ // only 1 or 2 is accepted
                System.out.println("Select a valid target..");
            }
            else{
                if(inp.charAt(0) == 49){
                    return 1;
                }
                else if(inp.charAt(0) == 50){
                    return 2;
                }
            }
        }
    }

    // DEVELOPED BY: RIMI
    /** Prompts for a player name until it contains from one through seven characters.
     *  @param  num - the player number shown in the prompt
     *  @return     - the accepted name                                                  */
    public String EnterName(int num){
        String name;
        while(true) {
            System.out.print("========== PLAYER " + num + " ==========\n" +
                    "Enter Player " + num + "'s name: ");
            name = input.nextLine();
            if(name.equals("") || name == null || name.length() <= 0) {
                System.out.println("You should input your Player name!");
            }
            else if(name.length() >= 8){
                System.out.println("You're name should not be longer than 8 characters!");
            }
            else{
                break;
            }
        }
        return name;
    }

    // DEVELOPED BY: RIMI
    /** Shows a prompt and reads input until an empty line is entered.
     *  @param line - the text used in the input prompt                  */
    public void Enter(String line){
        System.out.print(line);
        String inp = input.nextLine();
        while(true) {
            if (inp.equals("")) {
                System.out.println();
                break;
            } else if (inp.length() > 0) {
                System.out.println(line);
                inp = input.nextLine();
            }
        }
    }

    // DEVELOPED BY: RIMI
    /** Reads only 1 to 8 and return it with -1
     *  Invalid text is rejected before parsing.
     *  @param line - the text used in the input prompt
     *  @return     - the accepted zero-based index from 0 through 7  */
    public int EnterPos(String line){
        String position;
        int pos;
        System.out.print("Enter the " + line + ": ");
        while(true) {
            while(true) {
                position = input.nextLine();
                if (position.length() != 1 || !(((int)((char)(position.charAt(0)))) >= 48 && ((int)((char)(position.charAt(0)))) <= 57)){
                    System.out.print("Enter the correct " + line + " !: ");
                }
                else {
                    break;
                }
            }
            pos = Integer.parseInt(position);
            if (pos >= 1 && pos <= 8) {
                break;
            }
            else {
                System.out.print("Enter the correct " + line + " !: ");
            }
        }
        return pos-1;
    }

    // DEVELOPED BY: Leon
    /**
     * Prints the numbers of blank and live shells.
     * @param list the array of shell codes
     */
    public void DisplayBulletNum(int[] list){
        System.out.printf("The gun is reloading....\nThere are %d blank bullet and %d live bullet\n",blankBulletNum(list),liveBulletNum(list));
    }

    // DEVELOPED BY: LEON
    /**
     * Prints the receiving player name and the generated item name, then asks where to
     * place it.
     * @param item the item to store or describe
     * @param p one of the registered players
     */
    public void DisplayRandomItem(Items item, Player p){
        System.out.print(p.GetName() + " ,you got a ");
        if(item instanceof ExpiredMedecine){
            System.out.print("ExpiredMedecine");
        }
        else if(item instanceof CigarettesPack){
            System.out.print("CigarettesPack");
        }
        else if(item instanceof MagnifyingGlass){
            System.out.print("MagnifyingGlass");
        }
        else if(item instanceof BurnerPhone){
            System.out.print("BurnerPhone");
        }
        else if(item instanceof HandSaw){
            System.out.print("HandSaw");
        }
        else if(item instanceof Inverter){
            System.out.print("Inverter");
        }
        else if(item instanceof Beer){
            System.out.print("Beer");
        }
        else if(item instanceof Adrenaline){
            System.out.print("Adrenaline");
        }
        else if(item instanceof Handcuff){
            System.out.print("Handcuff");
        }
        System.out.println(", select a grid to place it on");
    }




    // DEVELOPED BY: Leon
    /**
     * Prints the main menu choices.
     */
    public void DisplayMainMenu(){
        System.out.print("+--------------------------------+\n" +
                         "|        BUCKSHOT ROULETTE       |\n" +
                         "+--------------------------------+\n" +
                         "|         — MAIN MENU —          |\n" +
                         "|1.START GAME                    |\n" +
                         "|2.EXIT GAME                     |\n" +
                         "|......                          |\n" +
                         "+--------------------------------+\n" +
                         "Choice one option: ");
    }

    // DEVELOPED BY: Leon
    /**
     * Prints the coin-guessing instructions for the selected player; does not generate
     * the coin result.
     * @param firstPlayerName the name of the player guessing the coin
     */
    public void CoinToss(String firstPlayerName){

        System.out.print ("+------------------------------------------+\n"     +
                          "|                COIN TOSS                 |\n"     +
                          "+------------------------------------------+\n"     +
                          "|     The coin decides who goes first.     |\n"     );
        System.out.printf("|     %7s, make your guess!            |\n", firstPlayerName    );
        System.out.print ("|            [1] HEADS                     |\n"     +
                          "|            [0] TAILS                     |\n"     +
                          "+------------------------------------------+\n"     );
    }
    // DEVELOPED BY: Leon
    /**
     * Prints the ASCII illustration of coin result 1.
     */
    public void CoinOne(){
        System.out.print("     ...........\n"      +
                         "  ...           ...\n"    +
                         " .                 .\n"   +
                         ".         ##        .\n"  +
                         ".        ###        .\n"  +
                         ".         ##        .\n"  +
                         ".         ##        .\n"  +
                         ".       ######      .\n"  +
                         " .                 .\n"   +
                         "  ...           ...\n "   +
                         "     ...........\n"       );
    }

    // DEVELOPED BY: Leon
    /**
     * Prints the ASCII illustration of coin result 0.
     */
    public void CoinZero(){
        System.out.println("     ...........\n"     +
                           "  ...           ...\n"   +
                           " .                 .\n"  +
                           ".       ######      .\n" +
                           ".      ##    ##     .\n" +
                           ".      ##    ##     .\n" +
                           ".      ##    ##     .\n" +
                           ".       ######      .\n" +
                           " .                 .\n"  +
                           "  ...           ...\n "  +
                           "     ...........\n"      );
    }

    private int[] shells;
    // DEVELOPED BY: RIMI
    /** Copies the shotgun display data into eight cached tray slots, padding unused positions with EMPTY.
     *  @param gun - the ShotGun object providing display data                                              */
    private void shellSave(ShotGun gun){
        this.shells = new int[8];
        int[] bullets = gun.GetBulletsInDisplay();
        for(int i = 0; i < 8; i++){
            if(bullets == null || i >= bullets.length)
                this.shells[i] = ShotGun.EMPTY;
            else{
                if(bullets[i] == ShotGun.EMPTY){
                    this.shells[i] = ShotGun.EMPTY;
                }
                else if(bullets[i] == ShotGun.BLANK){
                    this.shells[i] = ShotGun.BLANK;
                }
                else{
                    this.shells[i] = ShotGun.LIVE;
                }
            }
        }
    }
    // DEVELOPED BY: RIMI
    /** Prints the tray border or line ending for one drawing row.
     *  @param i - the drawing row number from 1 through 8; other values produce no output                                              */
    private void displayLine(int i){
        switch (i){
            case 1:
                System.out.print("|\n                            /");
                break;
            case 2:
                System.out.print(" |\n                           /");
                break;
            case 3:
                System.out.print("  |\n                          /");
                break;
            case 4:
                System.out.print("   |\n                         /");
                break;
            case 5:
                System.out.print("    |\n                        /");
                break;
            case 6:
                System.out.print("     |\n                       /");
                break;
            case 7:
                System.out.print("      |\n                      /");
                break;
            case 8:
                System.out.print("      //\n");
                break;
        }
    }
    // DEVELOPED BY: RIMI
    /** Prints eight rows of shell artwork using the supplied tray values.
     *  @param shellType - an array of eight values using BLANK, LIVE or EMPTY */
    private void shellDisplay(int[] shellType){
        for(int i = 0; i < 8; i++){
            String graph = "";
            for(int k = 0; k < 8; k++) {
                if(i == 0){
                    if(shellType[k] == ShotGun.BLANK){
                        graph += "[          ]//";
                    }
                    else if(shellType[k] == ShotGun.LIVE){
                        graph += "[##########]//";
                    }
                    else{
                        graph += "            //";
                    }
                }
                else if(i >= 1 && i <= 6){
                    if(shellType[k] == ShotGun.BLANK){
                        graph += "|          |//";
                    }
                    else if(shellType[k] == ShotGun.LIVE){
                        graph += "|##########|//";
                    }
                    else{
                        graph += "            //";
                    }
                }
                else if(i == 7){
                    if(shellType[k] == ShotGun.BLANK || shellType[k] == ShotGun.LIVE){
                        graph += "[==========]//";
                    }
                    else{
                        graph += "            //";
                    }
                }
            }
            System.out.print(graph);
            displayLine(i + 1);
        }
    }
    // DEVELOPED BY: RIMI
    /** Prints the shell tray using a separate display order, without changing the actual firing order.
     *  @param gun - the shotgun whose remaining shells are displayed                                       */
    public void DisplayBullets(ShotGun gun){
        shellSave(gun);
        System.out.print("                              ================================================================================================================|\n" +
                         "                             /");

        shellDisplay(this.shells);

        System.out.print("                     =================================================================================================================|     //\n"  +
                         "                     |                                                                                                                |    //\n"   +
                         "                     |                                                                                                                |   //\n"    +
                         "                     |                                                                                                                |  //\n"     +
                         "                     ===================================================================================================================//\n"      );
    }
    // DEVELOPED BY: Leon
    /**
     * After printing the home chessboard
     *  it is used to show where the player can place it
     * @param p1itemList inventory array containing at least eight slots
     */
    public void DisplayMiniGrid(Items[] p1itemList){
        System.out.printf(      "     +-----------------+-----------------+           +-----------------+-----------------+\n" +
                                "     |1%15s |2%15s |           |5%15s |6%15s |             \n"+
                                "     +-----------------+-----------------+           +-----------------+-----------------+\n" +
                                "     |3%15s |4%15s |           |7%15s |8%15s |             \n"+
                                "     +-----------------+-----------------+           +-----------------+-----------------+\n" ,
                DisplayItemWithMiniGrid(p1itemList[0]),   DisplayItemWithMiniGrid(p1itemList[1]),  DisplayItemWithMiniGrid(p1itemList[4]),   DisplayItemWithMiniGrid(p1itemList[5]),  DisplayItemWithMiniGrid(p1itemList[2]),  DisplayItemWithMiniGrid(p1itemList[3]),  DisplayItemWithMiniGrid(p1itemList[6]),  DisplayItemWithMiniGrid(p1itemList[7]));
    }
    // DEVELOPED BY: Leon
    /**
     * Gets the item label used in the compact inventory display.
     * @param item item to identify; may be null for an empty slot
     * @return the item label, or an empty string for null or an unknown item
     */
    public String DisplayItemWithMiniGrid(Items item){
        if(item instanceof ExpiredMedecine){
            return "ExpiredMedecine";
        }
        else if(item instanceof CigarettesPack){
            return "CigarettesPack";
        }
        else if(item instanceof MagnifyingGlass){
            return "MagnifyingGlass";
        }
        else if(item instanceof BurnerPhone){
            return "BurnerPhone";
        }
        else if(item instanceof HandSaw){
            return "HandSaw";
        }
        else if(item instanceof Inverter){
            return "Inverter";
        }
        else if(item instanceof Beer){
            return "Beer";
        }
        else if(item instanceof Adrenaline){
            return "Adrenaline";
        }
        else if(item instanceof Handcuff){
            return "Handcuff";
        }
        else{
            return "";
        }
    }
    // DEVELOPED BY: Leon
    /**
     * Returns the selected item's shape without printing it.
     * @param item the item to draw; null or an unrecognized type uses the fallback artwork
     * @return the artwork as an array of row strings
     */
    private String[] DisplayItem(Items item){
        if(item instanceof ExpiredMedecine){
            return  new String[]{
                    "    _________     ",
                    "    |=========|    ",
                    "    |_________|    ",
                    "  /           \\   ",
                    "   |  +     X  |   ",
                    "   | --------- |   ",
                    "   |  /\\  /\\   |   ",
                    "   | /  \\/  \\  |   ",
                    "   |___________|   ",
            };
        }
        else if(item instanceof CigarettesPack){
            return  new String[]{
                    "     _______      ",
                    "     | || || |     ",
                    "     | || || |     ",
                    "    _|_||_||_|_    ",
                    "   /__________/|   ",
                    "   | ///////  ||   ",
                    "   | -------  ||   ",
                    "   |          ||   ",
                    "   |__________|/   "
            };
        }
        else if(item instanceof MagnifyingGlass){
            return new String[]{
                    "     .------.     ",
                    "  /   /      \\   ",
                    "   |   /        |  ",
                    "   |            |  ",
                    "   \\           /  ",
                    "     '.______.'    ",
                    "         ||        ",
                    "         ||        ",
                    "         ||        "
            };
        }
        else if(item instanceof BurnerPhone){
            return new String[]{
                    "    __            ",
                    "    |  |______     ",
                    "    |  _____  |    ",
                    "    | |     | |    ",
                    "    | |_____| |    ",
                    "    |  o o o  |    ",
                    "    |  o o o  |    ",
                    "    |  o o o  |    ",
                    "    |_________|    "
            };
        }
        else if(item instanceof HandSaw){
            return new String[]{
                    "        _____     " ,
                    "       /|___|\\    " ,
                    "        \\ ____/    " ,
                    "        /   |>     " ,
                    "       /   |>      " ,
                    "      /   |>       " ,
                    "     /   |>        " ,
                    "    /   |>         " ,
                    "   /___|>          "

            };
        }
        else if(item instanceof Inverter){
            return new String[]{
                    "   ___________    "  ,
                    "   /_________/|    " ,
                    "   |  ---->  ||    " ,
                    "   |  <----  ||    " ,
                    "   |---------||    " ,
                    "   |   (+)   ||    " ,
                    "   | [O] [X] ||    " ,
                    "   |_________|/    " ,
                    "     ||   ||       "

            };
        }
        else if(item instanceof Beer){
            return new String[]{
                    "    _________     " ,
                    "   /   (_)   \\    " ,
                    "    |=========|    " ,
                    "    |         |    " ,
                    "    | .----.  |    " ,
                    "    | |Beer|  |    " ,
                    "    | '----'  |    " ,
                    "    |         |    " ,
                    "   \\_________/    "

            };
        }
        else if(item instanceof Adrenaline){
            return new String[]{
                    "      _____       " ,
                    "      |_____|      " ,
                    "        | |        " ,
                    "     ___|_|___     " ,
                    "       |   |       " ,
                    "       |---|       " ,
                    "       |---|       " ,
                    "      \\___/       " ,
                    "         |         "

            };
        }
        else if(item instanceof Handcuff){
            return new String[]{
                    "  .----.          ",
                    " / .--. \\         " ,
                    "||   | |--o        " ,
                    " \\ '--' /   o     " ,
                    "   '----' .--o--.  " ,
                    "        / .---. \\ " ,
                    "        | |     | |" ,
                    "        \\ '---' / " ,
                    "          '-----'  "


            };
        }
        else{
            return new String[]{"", "", "","","","","","",""};
        }
    }




    // DEVELOPED BY: Leon
    /**
     * Prints the board with item shape, names and health indicators.
     * Player two appears above player one. This method only displays state.
     * @param p1 player shown in the lower half
     * @param p2 player shown in the upper half
     * @param p1itemList player one's inventory, containing at least eight slots
     * @param p2itemList player two's inventory, containing at least eight slots
     */
    public void DisplayerGridWithItem(Player p1,Player p2,Items[] p1itemList,Items[] p2itemList){
        System.out.printf(
                "+-------------------------------------------------------------------------------------------------------------------------------------+\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "|                                               |          Name:  %-8s            |                                               |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "| |1%18s|2%18s|     |           %11s               |     |5%18s|6%18s| |\n" +
                        "| |%19s|%19s|     |              HP %d/%d                 |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     +-------------------------------------+     |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |3%18s|4%18s|                                  ___            |7%18s|8%18s| |\n" +
                        "| |%19s|%19s|                                 /  /|           |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                /__/ /           |%19s|%19s| |\n" +
                        "| |%19s|%19s|                               /  / /            |%19s|%19s| |\n" +
                        "| |%19s|%19s|                              /##/ /             |%19s|%19s| |\n" +
                        "| |%19s|%19s|                             /##/ /              |%19s|%19s| |\n" +
                        "| |%19s|%19s|                            /##/ /               |%19s|%19s| |\n" +
                        "| |%19s|%19s|                           /__/ /                |%19s|%19s| |\n" +
                        "| |%19s|%19s|                          /__/ /                 |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+                         /___\\\\/                 +-------------------+-------------------+ |\n" +
                        "|                                                                  / __ /                                                             |\n" +
                        "|-----------------------------------------------------------------/ /_//--------------------------------------------------------------|\n" +
                        "|                                                                /    /__                                                             |\n" +
                        "| +-------------------+-------------------+                     /    /   )                  +-------------------+-------------------+ |\n" +
                        "| |1%18s|2%18s|                    /    / (_/                   |5%18s|6%18s| |\n" +
                        "| |%19s|%19s|                   /     \\\\__/                   |%19s|%19s| |\n" +
                        "| |%19s|%19s|                  /       |                      |%19s|%19s| |\n" +
                        "| |%19s|%19s|                 /        |                      |%19s|%19s| |\n" +
                        "| |%19s|%19s|                /         |                      |%19s|%19s| |\n" +
                        "| |%19s|%19s|               /          |                      |%19s|%19s| |\n" +
                        "| |%19s|%19s|              /___________|                      |%19s|%19s| |\n" +
                        "| |%19s|%19s|             \\\\===========/                      |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |3%18s|4%18s|                                                 |7%18s|8%18s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|                                                 |%19s|%19s| |\n" +
                        "| |%19s|%19s|     +-------------------------------------+     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |              HP %d/%d                 |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |          %11s                |     |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "|                                               |          Name:  %-8s            |                                               |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "+-------------------------------------------------------------------------------------------------------------------------------------+\n" ,

                                                                                             p2.GetName(),
                DisplayItem(p2itemList[0])[0], DisplayItem(p2itemList[1])[0],             DisplayerHealth(p2),         DisplayItem(p2itemList[4])[0], DisplayItem(p2itemList[5])[0],
                DisplayItem(p2itemList[0])[1], DisplayItem(p2itemList[1])[1],          p2.GetHP(),p2.GetMaxHp(),       DisplayItem(p2itemList[4])[1], DisplayItem(p2itemList[5])[1],
                DisplayItem(p2itemList[0])[2], DisplayItem(p2itemList[1])[2], DisplayItem(p2itemList[4])[2], DisplayItem(p2itemList[5])[2],
                DisplayItem(p2itemList[0])[3], DisplayItem(p2itemList[1])[3], DisplayItem(p2itemList[4])[3], DisplayItem(p2itemList[5])[3],
                DisplayItem(p2itemList[0])[4], DisplayItem(p2itemList[1])[4], DisplayItem(p2itemList[4])[4], DisplayItem(p2itemList[5])[4],
                DisplayItem(p2itemList[0])[5], DisplayItem(p2itemList[1])[5], DisplayItem(p2itemList[4])[5], DisplayItem(p2itemList[5])[5],
                DisplayItem(p2itemList[0])[6], DisplayItem(p2itemList[1])[6], DisplayItem(p2itemList[4])[6], DisplayItem(p2itemList[5])[6],
                DisplayItem(p2itemList[0])[7], DisplayItem(p2itemList[1])[7], DisplayItem(p2itemList[4])[7], DisplayItem(p2itemList[5])[7],
                DisplayItem(p2itemList[0])[8], DisplayItem(p2itemList[1])[8], DisplayItem(p2itemList[4])[8], DisplayItem(p2itemList[5])[8],

                DisplayItem(p2itemList[2])[0], DisplayItem(p2itemList[3])[0], DisplayItem(p2itemList[6])[0], DisplayItem(p2itemList[7])[0],
                DisplayItem(p2itemList[2])[1], DisplayItem(p2itemList[3])[1], DisplayItem(p2itemList[6])[1], DisplayItem(p2itemList[7])[1],
                DisplayItem(p2itemList[2])[2], DisplayItem(p2itemList[3])[2], DisplayItem(p2itemList[6])[2], DisplayItem(p2itemList[7])[2],
                DisplayItem(p2itemList[2])[3], DisplayItem(p2itemList[3])[3], DisplayItem(p2itemList[6])[3], DisplayItem(p2itemList[7])[3],
                DisplayItem(p2itemList[2])[4], DisplayItem(p2itemList[3])[4], DisplayItem(p2itemList[6])[4], DisplayItem(p2itemList[7])[4],
                DisplayItem(p2itemList[2])[5], DisplayItem(p2itemList[3])[5], DisplayItem(p2itemList[6])[5], DisplayItem(p2itemList[7])[5],
                DisplayItem(p2itemList[2])[6], DisplayItem(p2itemList[3])[6], DisplayItem(p2itemList[6])[6], DisplayItem(p2itemList[7])[6],
                DisplayItem(p2itemList[2])[7], DisplayItem(p2itemList[3])[7], DisplayItem(p2itemList[6])[7], DisplayItem(p2itemList[7])[7],
                DisplayItem(p2itemList[2])[8], DisplayItem(p2itemList[3])[8], DisplayItem(p2itemList[6])[8], DisplayItem(p2itemList[7])[8],

                DisplayItem(p1itemList[0])[0], DisplayItem(p1itemList[1])[0], DisplayItem(p1itemList[4])[0], DisplayItem(p1itemList[5])[0],
                DisplayItem(p1itemList[0])[1], DisplayItem(p1itemList[1])[1], DisplayItem(p1itemList[4])[1], DisplayItem(p1itemList[5])[1],
                DisplayItem(p1itemList[0])[2], DisplayItem(p1itemList[1])[2], DisplayItem(p1itemList[4])[2], DisplayItem(p1itemList[5])[2],
                DisplayItem(p1itemList[0])[3], DisplayItem(p1itemList[1])[3], DisplayItem(p1itemList[4])[3], DisplayItem(p1itemList[5])[3],
                DisplayItem(p1itemList[0])[4], DisplayItem(p1itemList[1])[4], DisplayItem(p1itemList[4])[4], DisplayItem(p1itemList[5])[4],
                DisplayItem(p1itemList[0])[5], DisplayItem(p1itemList[1])[5], DisplayItem(p1itemList[4])[5], DisplayItem(p1itemList[5])[5],
                DisplayItem(p1itemList[0])[6], DisplayItem(p1itemList[1])[6], DisplayItem(p1itemList[4])[6], DisplayItem(p1itemList[5])[6],
                DisplayItem(p1itemList[0])[7], DisplayItem(p1itemList[1])[7], DisplayItem(p1itemList[4])[7], DisplayItem(p1itemList[5])[7],
                DisplayItem(p1itemList[0])[8], DisplayItem(p1itemList[1])[8], DisplayItem(p1itemList[4])[8], DisplayItem(p1itemList[5])[8],

                DisplayItem(p1itemList[2])[0], DisplayItem(p1itemList[3])[0], DisplayItem(p1itemList[6])[0], DisplayItem(p1itemList[7])[0],
                DisplayItem(p1itemList[2])[1], DisplayItem(p1itemList[3])[1], DisplayItem(p1itemList[6])[1], DisplayItem(p1itemList[7])[1],
                DisplayItem(p1itemList[2])[2], DisplayItem(p1itemList[3])[2], DisplayItem(p1itemList[6])[2], DisplayItem(p1itemList[7])[2],
                DisplayItem(p1itemList[2])[3], DisplayItem(p1itemList[3])[3], DisplayItem(p1itemList[6])[3], DisplayItem(p1itemList[7])[3],
                DisplayItem(p1itemList[2])[4], DisplayItem(p1itemList[3])[4], DisplayItem(p1itemList[6])[4], DisplayItem(p1itemList[7])[4],
                DisplayItem(p1itemList[2])[5], DisplayItem(p1itemList[3])[5], DisplayItem(p1itemList[6])[5], DisplayItem(p1itemList[7])[5],
                DisplayItem(p1itemList[2])[6], DisplayItem(p1itemList[3])[6], DisplayItem(p1itemList[6])[6], DisplayItem(p1itemList[7])[6],
                DisplayItem(p1itemList[2])[7], DisplayItem(p1itemList[3])[7],    p1.GetHP(),p1.GetMaxHp(),      DisplayItem(p1itemList[6])[7], DisplayItem(p1itemList[7])[7],
                DisplayItem(p1itemList[2])[8], DisplayItem(p1itemList[3])[8],       DisplayerHealth(p1),        DisplayItem(p1itemList[6])[8], DisplayItem(p1itemList[7])[8],
                                                                                    p1.GetName()
        );}

    // DEVELOPED BY: Leon
    /**
     * Prints the board with names and health indicators but no item shape.
     * @param p1 player shown in the lower half
     * @param p2 player shown in the upper half
     */
    public void DisplayerGrid(Player p1,Player p2){
        System.out.printf(
                "+-------------------------------------------------------------------------------------------------------------------------------------+\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "|                                               |          Name:  %-8s            |                                               |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "| |1                  |2                  |     |         %11s                 |     |5                  |6                  | |\n" +
                        "| |                   |                   |     |              HP %d/%d                 |     |                   |                   | |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| |                   |                   |     +-------------------------------------+     |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |3                  |4                  |                                  ___            |7                  |8                  | |\n" +
                        "| |                   |                   |                                 /  /|           |                   |                   | |\n" +
                        "| |                   |                   |                                /__/ /           |                   |                   | |\n" +
                        "| |                   |                   |                               /  / /            |                   |                   | |\n" +
                        "| |                   |                   |                              /##/ /             |                   |                   | |\n" +
                        "| |                   |                   |                             /##/ /              |                   |                   | |\n" +
                        "| |                   |                   |                            /##/ /               |                   |                   | |\n" +
                        "| |                   |                   |                           /__/ /                |                   |                   | |\n" +
                        "| |                   |                   |                          /__/ /                 |                   |                   | |\n" +
                        "| +-------------------+-------------------+                         /___\\\\/                 +-------------------+-------------------+ |\n" +
                        "|                                                                  / __ /                                                             |\n" +
                        "|-----------------------------------------------------------------/ /_//--------------------------------------------------------------|\n" +
                        "|                                                                /    /__                                                             |\n" +
                        "| +-------------------+-------------------+                     /    /   )                  +-------------------+-------------------+ |\n" +
                        "| |1                  |2                  |                    /    / (_/                   |5                  |6                  | |\n" +
                        "| |                   |                   |                   /     \\\\__/                   |                   |                   | |\n" +
                        "| |                   |                   |                  /       |                      |                   |                   | |\n" +
                        "| |                   |                   |                 /        |                      |                   |                   | |\n" +
                        "| |                   |                   |                /         |                      |                   |                   | |\n" +
                        "| |                   |                   |               /          |                      |                   |                   | |\n" +
                        "| |                   |                   |              /___________|                      |                   |                   | |\n" +
                        "| |                   |                   |             \\\\===========/                      |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |3                  |4                  |                                                 |7                  |8                  | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |     +-------------------------------------+     |                   |                   | |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| |                   |                   |     |              HP %d/%d                 |     |                   |                   | |\n" +
                        "| |                   |                   |     |         %11s                 |     |                   |                   | |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "|                                               |          Name:  %-8s            |                                               |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "+-------------------------------------------------------------------------------------------------------------------------------------+\n",
                p2.GetName(),DisplayerHealth(p2),p2.GetHP(),p2.GetMaxHp(),p1.GetHP(),p1.GetMaxHp(),DisplayerHealth(p1),p1.GetName()

        );}

    // DEVELOPED BY: Leon
    /**
     * Builds a # bar from current and maximum health without clamping either value.
     * @param p1 the player whose health is displayed
     * @return filled # for current HP followed by empty . for missing HP
     */
    private String DisplayerHealth(Player p1){
        String result = "";
        String char1="";
        for(int i=0;i<p1.GetHP();i++){//count health
            char1 ="#";
            result += char1 +" ";
        }
        for(int i=0;i<p1.GetMaxHp()-p1.GetHP();i++){// number of the loss health
            char1 =".";
            result += char1 +" ";
        }

        return  result;

    }


    }

