import java.util.Scanner;

/**
 * Provides console menus, board drawings, item labels and input helpers. GameMain supplies
 * game data for display; this class also reads names and inventory positions.
 *
 * @author TODO: confirm original code author(s)
 */
public class Displayer {
    Scanner input;

    public Displayer() {
        input = new Scanner(System.in);
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Builds eight display positions from shell codes: solid for live, hollow for blank.
     * Positions beyond the array length are represented by spaces.
     * @param list the array of shell codes
     * @return the shell-symbol string; codes other than 0 and 1 also print a space
     */
    private String DisplayBullets(int[] list){
        String result = "";
        for(int i=0;i<8;i++){
            String symbol =" ";
            if(i<list.length) {
                if (list[i] == 1) {
                    symbol = "■";
                } else if (list[i] == 0) {
                    symbol = "□";
                } else {
                    System.out.print(" ");
                }
            }
            result += " " + symbol + " \\";
        }
        return result;
    }

    private String DisplayBullets2(int[] list){
        return "";
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Counts array entries with the blank-shell code 0.
     * @param list the array of shell codes
     * @return the number of blank shells
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
    // DEVELOPED BY: TODO: confirm original code author(s)
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

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Prompts for a player name until it contains from one through seven characters.
     * @param num the player number shown in the prompt
     * @return the accepted name
     */
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

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Shows a prompt and reads input until an empty line is entered.
     * @param line the text used in the input prompt
     */
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

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Reads a single digit from 1 through 8 and converts it to an inventory array index.
     * Invalid text is rejected before parsing.
     * @param line the text used in the input prompt
     * @return the accepted zero-based index from 0 through 7
     */
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

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Prints the numbers of blank and live shells.
     * @param list the array of shell codes
     */
    public void DisplayBulletNum(int[] list){//过度使用
        System.out.printf("There are %d blank bullet and %d live bullet\n",blankBulletNum(list),liveBulletNum(list));
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
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




    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Prints the main menu choices.
     */
    public void DisplayMainMenu(){
        System.out.print("+--------------------------------+\n" +
                         "|        BUCKSHOT ROULETTE       |\n" +
                         "+--------------------------------+\n" +
                         "|         — MAIN MENU —          |\n" +
                         "|1.START GAME                    |\n" +
                         "|2.HOW TO PLAY                   |\n" +
                         "|3.EXIT GAME                     |\n" +
                         "|......                          |\n" +
                         "+--------------------------------+\n" +
                         "Choice one option:  ");
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
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
    // DEVELOPED BY: TODO: confirm original code author(s)
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

    // DEVELOPED BY: TODO: confirm original code author(s)
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

    public void DisplaySignMachine(){
    }

    // DEVELOPED BY: TODO: confirm original code author(s)
    /**
     * Prints the board with shell symbols and both player health values. Supply a grouped
     * display array to avoid revealing the firing order.
     * @param list the array of shell codes
     * @param p1 the first player supplied to this method
     * @param p2 the second player supplied to this method
     */
    public void DisplayerGridWithShell(int[] list,Player p1,Player p2){
        System.out.print("                                                   -----------------------------------------------------------------------------------------------------------\n"+
                "                                                  /                                       -------------------------------                                     \\\n"+
                "                                                 / ---------------------------------     /                               \\     ------------------------------- \\\n"  +
                "                                                / /               /               /     /                                 \\     \\              \\              \\ \\\n"+
                "                                               / /               /               /     /                                   \\     \\              \\              \\ \\\n"+
                "                                              / /               /               /     /                                     \\     \\              \\              \\ \\\n"+
                "                                             / /               /               /     /                                       \\     \\              \\              \\ \\\n"+
                "                                            / /               /               /     /                                         \\     \\              \\              \\ \\\n"+
                "                                           / /               /               /     ---------------------------------------------     \\              \\              \\ \\\n"+
                "                                          / /               /               /                                                         \\              \\              \\ \\\n"+
                "                                         / ---------------------------------                                                           ------------------------------- \\\n"+
                "                                        / /               /               /                                                             \\              \\              \\ \\\n"+
                "                                       / /               /               /                                                               \\              \\              \\ \\\n"+
                "                                      / /               /               /                                                                 \\              \\              \\ \\\n"+
                "                                     / /               /               /                                                                   \\              \\              \\ \\\n"+
                "                                    / /               /               /                                                                     \\              \\              \\ \\\n"+
                "                                   / /               /               /                                                                       \\              \\              \\ \\\n"+
                "                                  / /               /               /                                                                         \\              \\              \\ \\\n"+
                "                                 / /               /               /                                                                           \\              \\              \\ \\\n"+
                "                                / ---------------------------------                                                                             ------------------------------- \\\n"+
                "                               /                                                                                                               --------------------------------- \\\n"+
                "                              /                                                                                                                 \\"+DisplayBullets(list) +" \\\n"+
                "                             /-------------------------------------------------------------------------------------------------------------------\\"+DisplayBullets(list) +" \\\n"+
                "                            /                                                                                                                     --------------------------------- \\\n"+
                "                           / -----------------------------------                                                                                  ---------------------------------- \\\n"+
                "                          / /                /                /                                                                                    \\                \\               \\ \\\n"+
                "                         / /                /                /                                                                                      \\                \\               \\ \\\n"+
                "                        / /                /                /                                                                                        \\                \\               \\ \\\n"+
                "                       / /                /                /                                                                                          \\                \\               \\ \\\n"+
                "                      / /                /                /                                                                                            \\                \\               \\ \\\n"+
                "                     / /                /                /                                                                                              \\                \\               \\ \\\n"+
                "                    / /                /                /                                                                                                \\                \\               \\ \\\n"+
                "                   / /                /                /                                                                                                  \\                \\               \\ \\\n"+
                "                  / /                /                /                                                                                                    \\                \\               \\ \\\n"+
                "                 / -----------------------------------                                                                                                      ---------------------------------- \\\n"+
                "                / /                /                /                                                                                                        \\                \\               \\ \\\n"+
                "               / /                /                /                 ----------------------------------------------------------------------                   \\                \\               \\ \\\n"+
                "              / /                /                /                 /                                                                      \\                   \\                \\               \\ \\\n"+
                "             / /                /                /                 /                                                                        \\                   \\                \\               \\ \\\n"+
                "            / /                /                /                 /                                                                          \\                   \\                \\               \\ \\\n"+
                "           / /                /                /                 /                                                                            \\                   \\                \\               \\ \\\n"+
                "          / /                /                /                 /                                                                              \\                   \\                \\               \\ \\\n"+
                "         / /                /                /                 /                                                                                \\                   \\                \\               \\ \\\n"+
                "        / /                /                /                 /                                                                                  \\                   \\                \\               \\ \\\n"+
                "       / /                /                /                 /                                                                                    \\                   \\                \\               \\ \\\n"+
                "      / -----------------------------------                 /                                                                                      \\                   ---------------------------------  \\\n" +
                "     /                                                     ------------------------------------------------------------------------------------------                                                      \\\n"+
                "    +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+
                "    |                                                                                                                                                                                                       |\n"+
                "    |                                                                                                                                                                                                       |\n"+
                "    +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+p1.GetHP()+" "+p2.GetHP()+"\n"
        );}

    public void DisplayerGrid(Player p1,Player p2){
        System.out.print("                                                   -----------------------------------------------------------------------------------------------------------\n"+
                "                                                  /                                       -------------------------------                                     \\\n"+
                "                                                 / ---------------------------------     /                               \\     ------------------------------- \\\n"  +
                "                                                / /               /               /     /                                 \\     \\              \\              \\ \\\n"+
                "                                               / /               /               /     /                                   \\     \\              \\              \\ \\\n"+
                "                                              / /               /               /     /                                     \\     \\              \\              \\ \\\n"+
                "                                             / /               /               /     /                                       \\     \\              \\              \\ \\\n"+
                "                                            / /               /               /     /                                         \\     \\              \\              \\ \\\n"+
                "                                           / /               /               /     ---------------------------------------------     \\              \\              \\ \\\n"+
                "                                          / /               /               /                                                         \\              \\              \\ \\\n"+
                "                                         / ---------------------------------                                                           ------------------------------- \\\n"+
                "                                        / /               /               /                                                             \\              \\              \\ \\\n"+
                "                                       / /               /               /                                                               \\              \\              \\ \\\n"+
                "                                      / /               /               /                                                                 \\              \\              \\ \\\n"+
                "                                     / /               /               /                                                                   \\              \\              \\ \\\n"+
                "                                    / /               /               /                                                                     \\              \\              \\ \\\n"+
                "                                   / /               /               /                                                                       \\              \\              \\ \\\n"+
                "                                  / /               /               /                                                                         \\              \\              \\ \\\n"+
                "                                 / /               /               /                                                                           \\              \\              \\ \\\n"+
                "                                / ---------------------------------                                                                             ------------------------------- \\\n"+
                "                               /                                                                                                               --------------------------------- \\\n"+
                "                              /                                                                                                                 \\   \\   \\   \\   \\   \\   \\   \\   \\ \\\n"+
                "                             /-------------------------------------------------------------------------------------------------------------------\\   \\   \\   \\   \\   \\   \\   \\   \\ \\\n"+
                "                            /                                                                                                                     --------------------------------- \\\n"+
                "                           / -----------------------------------                                                                                  ---------------------------------- \\\n"+
                "                          / /                /                /                                                                                    \\                \\               \\ \\\n"+
                "                         / /                /                /                                                                                      \\                \\               \\ \\\n"+
                "                        / /                /                /                                                                                        \\                \\               \\ \\\n"+
                "                       / /                /                /                                                                                          \\                \\               \\ \\\n"+
                "                      / /                /                /                                                                                            \\                \\               \\ \\\n"+
                "                     / /                /                /                                                                                              \\                \\               \\ \\\n"+
                "                    / /                /                /                                                                                                \\                \\               \\ \\\n"+
                "                   / /                /                /                                                                                                  \\                \\               \\ \\\n"+
                "                  / /                /                /                                                                                                    \\                \\               \\ \\\n"+
                "                 / -----------------------------------                                                                                                      ---------------------------------- \\\n"+
                "                / /                /                /                                                                                                        \\                \\               \\ \\\n"+
                "               / /                /                /                 ----------------------------------------------------------------------                   \\                \\               \\ \\\n"+
                "              / /                /                /                 /                                                                      \\                   \\                \\               \\ \\\n"+
                "             / /                /                /                 /                                                                        \\                   \\                \\               \\ \\\n"+
                "            / /                /                /                 /                                                                          \\                   \\                \\               \\ \\\n"+
                "           / /                /                /                 /                                                                            \\                   \\                \\               \\ \\\n"+
                "          / /                /                /                 /                                                                              \\                   \\                \\               \\ \\\n"+
                "         / /                /                /                 /                                                                                \\                   \\                \\               \\ \\\n"+
                "        / /                /                /                 /                                                                                  \\                   \\                \\               \\ \\\n"+
                "       / /                /                /                 /                                                                                    \\                   \\                \\               \\ \\\n"+
                "      / -----------------------------------                 /                                                                                      \\                   ---------------------------------  \\\n" +
                "     /                                                     ------------------------------------------------------------------------------------------                                                      \\\n"+
                "    +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+
                "    |                                                                                                                                                                                                       |\n"+
                "    |                                                                                                                                                                                                       |\n"+
                "    +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+p1.GetHP()+" "+p2.GetHP()+"\n"



        );}

    int[][] shells = new int[8][1];

    private String shellInGraphic(ShotGun gun, int[][] list){
        for(int i = 0; i < 8; i++){
            if(gun.GetBulletsInGun()[i] == ShotGun.EMPTY){
                this.shells[i][0] = ShotGun.EMPTY;
            }
        }
        return"";
    }


    public void DisplayBullets(ShotGun gun){
        System.out.print("                              ================================================================================================================|  " +
                         "                             /            //            //            //            //            //            //            //            //|  " +
                         "                            /            //            //            //            //            //            //            //            // |  " +
                         "                           /            //            //            //            //            //            //            //            //  |  " +
                         "                          /            //            //            //            //            //            //            //            //   |  " +
                         "                         /            //            //            //            //            //            //            //            //    |  " +
                         "                        /            //            //            //            //            //            //            //            //     |  " +
                         "                       /            //            //            //            //            //            //            //            //      |  " +
                         "                      /            //            //            //            //            //            //            //            //      //  " +
                         "                     =================================================================================================================|     //   " +
                         "                     |                                                                                                                |    //    " +
                         "                     |                                                                                                                |   //     " +
                         "                     |                                                                                                                |  //      " +
                         "                     ===================================================================================================================//       " );
    }


    public void DisplayMiniGrid(Items[] p1itemList){
        System.out.printf(      "     +-----------------+-----------------+           +-----------------+-----------------+\n" +
                                "     |1%15s |2%15s |           |5%15s |6%15s |             \n"+
                                "     +-----------------+-----------------+           +-----------------+-----------------+\n" +
                                "     |3%15s |4%15s |           |7%15s |8%15s |             \n"+
                                "     +-----------------+-----------------+           +-----------------+-----------------+\n" ,
                DisplayItemWithMiniGrid(p1itemList[0]),   DisplayItemWithMiniGrid(p1itemList[1]),  DisplayItemWithMiniGrid(p1itemList[4]),   DisplayItemWithMiniGrid(p1itemList[5]),  DisplayItemWithMiniGrid(p1itemList[2]),  DisplayItemWithMiniGrid(p1itemList[3]),  DisplayItemWithMiniGrid(p1itemList[6]),  DisplayItemWithMiniGrid(p1itemList[7]));
    }

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

    public String[] DisplayItem(Items item){
        if(item instanceof ExpiredMedecine){
            return  new String[]{
                    "     _________     ",
                    "    |=========|    ",
                    "    |_________|    ",
                    "    /         \\   ",
                    "   |  +     X  |   ",
                    "   | --------- |   ",
                    "   |  /\\  /\\   |   ",
                    "   | /  \\/  \\  |   ",
                    "   |___________|   ",
            };
        }
        else if(item instanceof CigarettesPack){
            return  new String[]{
                    "      _______      ",
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
                    "      .------.     ",
                    "    /   /      \\  ",
                    "   |   /        |  ",
                    "   |            |  ",
                    "    \\          /  ",
                    "     '.______.'    ",
                    "         ||        ",
                    "         ||        ",
                    "         ||        "
            };
        }
        else if(item instanceof BurnerPhone){
            return new String[]{
                    "     __            ",
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
                    "       ______      " ,
                    "     / ____ \\     " ,
                    "     | |  | |      " ,
                    "     | |__| |      " ,
                    "     \\______ /    " ,
                    "       |   |>      " ,
                    "       |   |>      " ,
                    "       |   |>      " ,
                    "       |___|       "

            };
        }
        else if(item instanceof Inverter){
            return new String[]{
                        "     __________    "  ,
                        "   /_________/|    " ,
                        "   |  ---->  ||    " ,
                        "   |  <----  ||    " ,
                        "   |---------||    " ,
                        "   |   (+)   ||    " ,
                        "   |  □   ■  ||    " ,
                        "   |_________|/    " ,
                        "     ||   ||       "

            };
        }
        else if(item instanceof Beer){
            return new String[]{
                    "     _________     " ,
                    "    /   (_)   \\   " ,
                    "    |=========|    " ,
                    "    |         |    " ,
                    "    |  .---.  |    " ,
                    "    |  |~~~|  |    " ,
                    "    |  '---'  |    " ,
                    "    |         |    " ,
                    "    \\_________/   "

            };
        }
        else if(item instanceof Adrenaline){
            return new String[]{
                    "       _____       " ,
                    "      |_____|      " ,
                    "        | |        " ,
                    "     ___|_|___     " ,
                    "       |   |       " ,
                    "       |---|       " ,
                    "       |---|       " ,
                    "       \\___/      " ,
                    "         |         ",

            };
        }
        else if(item instanceof Handcuff){
            return new String[]{
                    "  .----.           ",
                    " / .--. \\         " ,
                    "||    | |--o       " ,
                    " \\ '--' /   o     " ,
                    "  '----'  .--o--.  " ,
                    "         / .---. \\" ,
                    "        |  |   |  |" ,
                    "         \\ '---' /",
                    "          '-----'  "


            };
        }
        else{
            return new String[]{"",
                                "",
                                "","","","","",""};
        }
    }

    private void AllItemGraph(){
        String[][] allItemGraph =new String[7][14];
        allItemGraph[0]= new String[]{
                "     _________     ",
                "    |=========|    ",
                "    |_________|    ",
                "    /         \\   ",
                "   |  +     X  |   ",
                "   | --------- |   ",
                "   |  /\\  /\\   |   ",
                "   | /  \\/  \\  |   ",
                "   |___________|   ",
        };

        allItemGraph[1]= new String[]{
                "      _______      ",
                "     | || || |     ",
                "     | || || |     ",
                "    _|_||_||_|_    ",
                "   /__________/|   ",
                "   | ///////  ||   ",
                "   | -------  ||   ",
                "   |          ||   ",
                "   |__________|/   "
        };

        allItemGraph[2]= new String[]{
                "      .------.     ",
                "    /   /      \\  ",
                "   |   /        |  ",
                "   |            |  ",
                "    \\          /  ",
                "     '.______.'    ",
                "         ||        ",
                "         ||        ",
                "         ||        "
        };

        allItemGraph[3]= new String[]{
                "     __            ",
                "    |  |______     ",
                "    |  _____  |    ",
                "    | |     | |    ",
                "    | |_____| |    ",
                "    |  o o o  |    ",
                "    |  o o o  |    ",
                "    |  o o o  |    ",
                "    |_________|    "
        };

        allItemGraph[4]= new String[]{
                        "       ______      " ,
                        "     / ____ \\     " ,
                        "     | |  | |      " ,
                        "     | |__| |      " ,
                        "     \\______ /    " ,
                        "       |   |>      " ,
                        "       |   |>      " ,
                        "       |   |>      " ,
                        "       |___|       "

        };

        allItemGraph[5]= new String[]{
                        "     __________    "  ,
                        "   /_________/|    " ,
                        "   |  ---->  ||    " ,
                        "   |  <----  ||    " ,
                        "   |---------||    " ,
                        "   |   (+)   ||    " ,
                        "   |  □   ■  ||    " ,
                        "   |_________|/    " ,
                        "     ||   ||       "

        }

        ;allItemGraph[6]= new String[]{
                        "     _________     " ,
                        "    /   (_)   \\   " ,
                        "    |=========|    " ,
                        "    |  .---.  |    " ,
                        "    |  |~~~|  |    " ,
                        "    |  '---'  |    " ,
                        "    |         |    " ,
                        "    \\_________/   "

        };

        allItemGraph[7]= new String[]{
                        "       _____       " ,
                        "      |_____|      " ,
                        "        | |        " ,
                        "     ___|_|___     " ,
                        "       |   |       " ,
                        "       |---|       " ,
                        "       |---|       " ,
                        "       \\___/      " ,
                        "         |         ",

        };

        allItemGraph[8]= new String[]{
                         "  .----.           ",
                         " / .--. \\         " ,
                         "||    | |--o       " ,
                         " \\ '--' /   o     " ,
                         "  '----'  .--o--.  " ,
                         "         / .---. \\" ,
                         "        |  |   |  |" ,
                         "         \\ '---' /",
                         "          '-----'  "


        };

    }
    /*
    public void DisplayerGridWithItem(Player p1,Player p2,Items[] p1itemList,Items[] p2itemList){
        System.out.printf("                                                   -------------------------------------------------------------------------------------------------------------\n"+
                "                                                  /                                       -------------------------------                                       \\\n"+
                "                                                 / ---------------------------------     /                               \\     --------------------------------- \\\n"  +
                "                                                / /%15s/%15s/     /                                 \\     \\%15s\\%15s\\ \\\n"+
                "                                               / /               /               /     /                                   \\     \\               \\               \\ \\\n"+
                "                                              / /               /               /     /                                     \\     \\               \\               \\ \\\n"+
                "                                             / /               /               /     /                                       \\     \\               \\               \\ \\\n"+
                "                                            / /               /               /     /                                         \\     \\               \\               \\ \\\n"+
                "                                           / /               /               /     ---------------------------------------------     \\               \\               \\ \\\n"+
                "                                          / /               /               /                                                         \\               \\               \\ \\\n"+
                "                                         / ---------------------------------                                                           --------------------------------- \\\n"+
                "                                        / /%15s/%15s/                                                             \\%15s\\%15s\\ \\\n"+
                "                                       / /               /               /                                                               \\               \\               \\ \\\n"+
                "                                      / /               /               /                                                                 \\               \\               \\ \\\n"+
                "                                     / /               /               /                                                                   \\               \\               \\ \\\n"+
                "                                    / /               /               /                                                                     \\               \\               \\ \\\n"+
                "                                   / /               /               /                                                                       \\               \\               \\ \\\n"+
                "                                  / /               /               /                                                                         \\               \\               \\ \\\n"+
                "                                 / /               /               /                                                                           \\               \\               \\ \\\n"+
                "                                / ---------------------------------                                                                             --------------------------------- \\\n"+
                "                               /                                                                                                               ----------------------------------- \\\n"+
                "                              /                                                                                                                 \\   \\   \\   \\   \\   \\   \\   \\   \\ \\\n"+
                "                             /-------------------------------------------------------------------------------------------------------------------\\   \\   \\   \\   \\   \\   \\   \\   \\ \\\n"+
                "                            /                                                                                                                     ----------------------------------- \\\n"+
                "                           / -----------------------------------                                                                                  ------------------------------------ \\\n"+
                "                          / /%16s/%16s/                                                                                    \\%16s\\%16s\\ \\\n"+
                "                         / /                /                /                                                                                      \\                 \\                \\ \\\n"+
                "                        / /                /                /                                                                                        \\                 \\                \\ \\\n"+
                "                       / /                /                /                                                                                          \\                 \\                \\ \\\n"+
                "                      / /                /                /                                                                                            \\                 \\                \\ \\\n"+
                "                     / /                /                /                                                                                              \\                 \\                \\ \\\n"+
                "                    / /                /                /                                                                                                \\                 \\                \\ \\\n"+
                "                   / /                /                /                                                                                                  \\                 \\                \\ \\\n"+
                "                  / /                /                /                                                                                                    \\                 \\                \\ \\\n"+
                "                 / -----------------------------------                                                                                                      ------------------------------------ \\\n"+
                "                / /%16s/%16s/                                                                                                        \\%16s\\%16s\\ \\\n"+
                "               / /                /                /                 ----------------------------------------------------------------------                   \\                 \\                \\ \\\n"+
                "              / /                /                /                 /                                                                      \\                   \\                 \\                \\ \\\n"+
                "             / /                /                /                 /                                                                        \\                   \\                 \\                \\ \\\n"+
                "            / /                /                /                 /                                                                          \\                   \\                 \\                \\ \\\n"+
                "           / /                /                /                 /                                                                            \\                   \\                 \\                \\ \\\n"+
                "          / /                /                /                 /                                                                              \\                   \\                 \\                \\ \\\n"+
                "         / /                /                /                 /                                                                                \\                   \\                 \\                \\ \\\n"+
                "        / /                /                /                 /                                                                                  \\                   \\                 \\                \\ \\\n"+
                "       / /                /                /                 /                                                                                    \\                   \\                 \\                \\ \\\n"+
                "      / -----------------------------------                 /                                                                                      \\                   -----------------------------------  \\\n" +
                "     /                                                     ------------------------------------------------------------------------------------------                                                        \\\n"+
                "    +---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+
                "    |                                                                                                                                                                                                       |\n"+
                "    |                                                                                                                                                                                                       |\n"+
                "    +-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+p1.GetHP()+" "+p2.GetHP()+"\n"

                ,  DisplayItem(p2itemList[0]),   DisplayItem(p2itemList[1]),  DisplayItem(p2itemList[4]),   DisplayItem(p2itemList[5]),  DisplayItem(p2itemList[2]),  DisplayItem(p2itemList[3]),  DisplayItem(p2itemList[6]),  DisplayItem(p2itemList[7]),
                        DisplayItem(p1itemList[0]),   DisplayItem(p1itemList[1]),  DisplayItem(p1itemList[4]),   DisplayItem(p1itemList[5]),  DisplayItem(p1itemList[2]),  DisplayItem(p1itemList[3]),  DisplayItem(p1itemList[6]),  DisplayItem(p1itemList[7]));
    }

     */
    public void DisplayerGridWithItem(Player p1,Player p2,Items[] p1itemList,Items[] p2itemList){
        System.out.printf(
                        "+-------------------------------------------------------------------------------------------------------------------------------------+\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "|                                               |            %16s         |                                               |\n" +
                        "|                                               |                  %d                  |                                               |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     +-------------------------------------+     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "|                                                                                                                                     |\n" +
                        "|-------------------------------------------------------------------------------------------------------------------------------------|\n" +
                        "|                                                                                                                                     |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     +-------------------------------------+     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| |%19s|%19s|     |                                     |     |%19s|%19s| |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "|                                               |                  %d                  |                                               |\n" +
                        "|                                               |            %16s         |                                               |\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "+-------------------------------------------------------------------------------------------------------------------------------------+\n" ,

                        p2.GetName(),p2.GetHP(),
                        DisplayItem(p2itemList[0])[0], DisplayItem(p2itemList[1])[0], DisplayItem(p2itemList[4])[0], DisplayItem(p2itemList[5])[0],
                        DisplayItem(p2itemList[0])[1], DisplayItem(p2itemList[1])[1], DisplayItem(p2itemList[4])[1], DisplayItem(p2itemList[5])[1],
                        DisplayItem(p2itemList[0])[2], DisplayItem(p2itemList[1])[2], DisplayItem(p2itemList[4])[2], DisplayItem(p2itemList[5])[2],
                        DisplayItem(p2itemList[0])[3], DisplayItem(p2itemList[1])[3], DisplayItem(p2itemList[4])[3], DisplayItem(p2itemList[5])[3],
                        DisplayItem(p2itemList[0])[4], DisplayItem(p2itemList[1])[4], DisplayItem(p2itemList[4])[4], DisplayItem(p2itemList[5])[4],
                        DisplayItem(p2itemList[0])[5], DisplayItem(p2itemList[1])[5], DisplayItem(p2itemList[4])[5], DisplayItem(p2itemList[5])[5],
                        DisplayItem(p2itemList[0])[6], DisplayItem(p2itemList[1])[6], DisplayItem(p2itemList[4])[6], DisplayItem(p2itemList[5])[6],
                        DisplayItem(p2itemList[0])[7], DisplayItem(p2itemList[1])[7], DisplayItem(p2itemList[4])[7], DisplayItem(p2itemList[5])[7],

                        DisplayItem(p2itemList[2])[0], DisplayItem(p2itemList[3])[0], DisplayItem(p2itemList[6])[0], DisplayItem(p2itemList[7])[0],
                        DisplayItem(p2itemList[2])[1], DisplayItem(p2itemList[3])[1], DisplayItem(p2itemList[6])[1], DisplayItem(p2itemList[7])[1],
                        DisplayItem(p2itemList[2])[2], DisplayItem(p2itemList[3])[2], DisplayItem(p2itemList[6])[2], DisplayItem(p2itemList[7])[2],
                        DisplayItem(p2itemList[2])[3], DisplayItem(p2itemList[3])[3], DisplayItem(p2itemList[6])[3], DisplayItem(p2itemList[7])[3],
                        DisplayItem(p2itemList[2])[4], DisplayItem(p2itemList[3])[4], DisplayItem(p2itemList[6])[4], DisplayItem(p2itemList[7])[4],
                        DisplayItem(p2itemList[2])[5], DisplayItem(p2itemList[3])[5], DisplayItem(p2itemList[6])[5], DisplayItem(p2itemList[7])[5],
                        DisplayItem(p2itemList[2])[6], DisplayItem(p2itemList[3])[6], DisplayItem(p2itemList[6])[6], DisplayItem(p2itemList[7])[6],
                        DisplayItem(p2itemList[2])[7], DisplayItem(p2itemList[3])[7], DisplayItem(p2itemList[6])[7], DisplayItem(p2itemList[7])[7],

                        DisplayItem(p1itemList[0])[0], DisplayItem(p1itemList[1])[0], DisplayItem(p1itemList[4])[0], DisplayItem(p1itemList[5])[0],
                        DisplayItem(p1itemList[0])[1], DisplayItem(p1itemList[1])[1], DisplayItem(p1itemList[4])[1], DisplayItem(p1itemList[5])[1],
                        DisplayItem(p1itemList[0])[2], DisplayItem(p1itemList[1])[2], DisplayItem(p1itemList[4])[2], DisplayItem(p1itemList[5])[2],
                        DisplayItem(p1itemList[0])[3], DisplayItem(p1itemList[1])[3], DisplayItem(p1itemList[4])[3], DisplayItem(p1itemList[5])[3],
                        DisplayItem(p1itemList[0])[4], DisplayItem(p1itemList[1])[4], DisplayItem(p1itemList[4])[4], DisplayItem(p1itemList[5])[4],
                        DisplayItem(p1itemList[0])[5], DisplayItem(p1itemList[1])[5], DisplayItem(p1itemList[4])[5], DisplayItem(p1itemList[5])[5],
                        DisplayItem(p1itemList[0])[6], DisplayItem(p1itemList[1])[6], DisplayItem(p1itemList[4])[6], DisplayItem(p1itemList[5])[6],
                        DisplayItem(p1itemList[0])[7], DisplayItem(p1itemList[1])[7], DisplayItem(p1itemList[4])[7], DisplayItem(p1itemList[5])[7],

                        DisplayItem(p1itemList[2])[0], DisplayItem(p1itemList[3])[0], DisplayItem(p1itemList[6])[0], DisplayItem(p1itemList[7])[0],
                        DisplayItem(p1itemList[2])[1], DisplayItem(p1itemList[3])[1], DisplayItem(p1itemList[6])[1], DisplayItem(p1itemList[7])[1],
                        DisplayItem(p1itemList[2])[2], DisplayItem(p1itemList[3])[2], DisplayItem(p1itemList[6])[2], DisplayItem(p1itemList[7])[2],
                        DisplayItem(p1itemList[2])[3], DisplayItem(p1itemList[3])[3], DisplayItem(p1itemList[6])[3], DisplayItem(p1itemList[7])[3],
                        DisplayItem(p1itemList[2])[4], DisplayItem(p1itemList[3])[4], DisplayItem(p1itemList[6])[4], DisplayItem(p1itemList[7])[4],
                        DisplayItem(p1itemList[2])[5], DisplayItem(p1itemList[3])[5], DisplayItem(p1itemList[6])[5], DisplayItem(p1itemList[7])[5],
                        DisplayItem(p1itemList[2])[6], DisplayItem(p1itemList[3])[6], DisplayItem(p1itemList[6])[6], DisplayItem(p1itemList[7])[6],
                        DisplayItem(p1itemList[2])[7], DisplayItem(p1itemList[3])[7], DisplayItem(p1itemList[6])[7], DisplayItem(p1itemList[7])[7],
                        p1.GetHP(),p1.GetName()
        );}
    /*

    public void DisplayerGridWithItem(Player p1,Player p2,Items[] p1itemList,Items[] p2itemList){
        System.out.printf(
                        "+-------------------------------------------------------------------------------------------------------------------------------------+\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| |                   |                   |     +-------------------------------------+     |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "|                                                                                                                                     |\n" +
                        "|-------------------------------------------------------------------------------------------------------------------------------------|\n" +
                        "|                                                                                                                                     |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| +-------------------+-------------------+                                                 +-------------------+-------------------+ |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |                                                 |                   |                   | |\n" +
                        "| |                   |                   |     +-------------------------------------+     |                   |                   | |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| |                   |                   |     |                                     |     |                   |                   | |\n" +
                        "| +-------------------+-------------------+     |                                     |     +-------------------+-------------------+ |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "|                                               |                                     |                                               |\n" +
                        "|                                               +-------------------------------------+                                               |\n" +
                        "+-------------------------------------------------------------------------------------------------------------------------------------+\n",
                        DisplayItem(p2itemList[0])[0], DisplayItem(p2itemList[1])[0], DisplayItem(p2itemList[4])[0], DisplayItem(p2itemList[5])[0],
                        DisplayItem(p2itemList[0])[1], DisplayItem(p2itemList[1])[1], DisplayItem(p2itemList[4])[1], DisplayItem(p2itemList[5])[1],
                        DisplayItem(p2itemList[0])[2], DisplayItem(p2itemList[1])[2], DisplayItem(p2itemList[4])[2], DisplayItem(p2itemList[5])[2],
                        DisplayItem(p2itemList[0])[3], DisplayItem(p2itemList[1])[3], DisplayItem(p2itemList[4])[3], DisplayItem(p2itemList[5])[3],
                        DisplayItem(p2itemList[0])[4], DisplayItem(p2itemList[1])[4], DisplayItem(p2itemList[4])[4], DisplayItem(p2itemList[5])[4],
                        DisplayItem(p2itemList[0])[5], DisplayItem(p2itemList[1])[5], DisplayItem(p2itemList[4])[5], DisplayItem(p2itemList[5])[5],
                        DisplayItem(p2itemList[0])[6], DisplayItem(p2itemList[1])[6], DisplayItem(p2itemList[4])[6], DisplayItem(p2itemList[5])[6],
                        DisplayItem(p2itemList[0])[7], DisplayItem(p2itemList[1])[7], DisplayItem(p2itemList[4])[7], DisplayItem(p2itemList[5])[7],

                        DisplayItem(p2itemList[2])[0], DisplayItem(p2itemList[3])[0], DisplayItem(p2itemList[6])[0], DisplayItem(p2itemList[7])[0],
                        DisplayItem(p2itemList[2])[1], DisplayItem(p2itemList[3])[1], DisplayItem(p2itemList[6])[1], DisplayItem(p2itemList[7])[1],
                        DisplayItem(p2itemList[2])[2], DisplayItem(p2itemList[3])[2], DisplayItem(p2itemList[6])[2], DisplayItem(p2itemList[7])[2],
                        DisplayItem(p2itemList[2])[3], DisplayItem(p2itemList[3])[3], DisplayItem(p2itemList[6])[3], DisplayItem(p2itemList[7])[3],
                        DisplayItem(p2itemList[2])[4], DisplayItem(p2itemList[3])[4], DisplayItem(p2itemList[6])[4], DisplayItem(p2itemList[7])[4],
                        DisplayItem(p2itemList[2])[5], DisplayItem(p2itemList[3])[5], DisplayItem(p2itemList[6])[5], DisplayItem(p2itemList[7])[5],
                        DisplayItem(p2itemList[2])[6], DisplayItem(p2itemList[3])[6], DisplayItem(p2itemList[6])[6], DisplayItem(p2itemList[7])[6],
                        DisplayItem(p2itemList[2])[7], DisplayItem(p2itemList[3])[7], DisplayItem(p2itemList[6])[7], DisplayItem(p2itemList[7])[7],

                        DisplayItem(p1itemList[0])[0], DisplayItem(p1itemList[1])[0], DisplayItem(p1itemList[4])[0], DisplayItem(p1itemList[5])[0],
                        DisplayItem(p1itemList[0])[1], DisplayItem(p1itemList[1])[1], DisplayItem(p1itemList[4])[1], DisplayItem(p1itemList[5])[1],
                        DisplayItem(p1itemList[0])[2], DisplayItem(p1itemList[1])[2], DisplayItem(p1itemList[4])[2], DisplayItem(p1itemList[5])[2],
                        DisplayItem(p1itemList[0])[3], DisplayItem(p1itemList[1])[3], DisplayItem(p1itemList[4])[3], DisplayItem(p1itemList[5])[3],
                        DisplayItem(p1itemList[0])[4], DisplayItem(p1itemList[1])[4], DisplayItem(p1itemList[4])[4], DisplayItem(p1itemList[5])[4],
                        DisplayItem(p1itemList[0])[5], DisplayItem(p1itemList[1])[5], DisplayItem(p1itemList[4])[5], DisplayItem(p1itemList[5])[5],
                        DisplayItem(p1itemList[0])[6], DisplayItem(p1itemList[1])[6], DisplayItem(p1itemList[4])[6], DisplayItem(p1itemList[5])[6],
                        DisplayItem(p1itemList[0])[7], DisplayItem(p1itemList[1])[7], DisplayItem(p1itemList[4])[7], DisplayItem(p1itemList[5])[7],

                        DisplayItem(p1itemList[2])[0], DisplayItem(p1itemList[3])[0], DisplayItem(p1itemList[6])[0], DisplayItem(p1itemList[7])[0],
                        DisplayItem(p1itemList[2])[1], DisplayItem(p1itemList[3])[1], DisplayItem(p1itemList[6])[1], DisplayItem(p1itemList[7])[1],
                        DisplayItem(p1itemList[2])[2], DisplayItem(p1itemList[3])[2], DisplayItem(p1itemList[6])[2], DisplayItem(p1itemList[7])[2],
                        DisplayItem(p1itemList[2])[3], DisplayItem(p1itemList[3])[3], DisplayItem(p1itemList[6])[3], DisplayItem(p1itemList[7])[3],
                        DisplayItem(p1itemList[2])[4], DisplayItem(p1itemList[3])[4], DisplayItem(p1itemList[6])[4], DisplayItem(p1itemList[7])[4],
                        DisplayItem(p1itemList[2])[5], DisplayItem(p1itemList[3])[5], DisplayItem(p1itemList[6])[5], DisplayItem(p1itemList[7])[5],
                        DisplayItem(p1itemList[2])[6], DisplayItem(p1itemList[3])[6], DisplayItem(p1itemList[6])[6], DisplayItem(p1itemList[7])[6],
                        DisplayItem(p1itemList[2])[7], DisplayItem(p1itemList[3])[7], DisplayItem(p1itemList[6])[7], DisplayItem(p1itemList[7])[7]

        );}

     */

    }

