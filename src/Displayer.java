public class Displayer {
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
    public void CoinToss(String p1, String p2){
        String p;
        int i = (int)(Math.random()*2) + 1;
        if(i == 1){
            p = p1;
        }
        else{
            p = p2;
        }
        System.out.print ("+------------------------------------------+"     +
                          "|                COIN TOSS                 |"     +
                          "+------------------------------------------+"     +
                          "|     The coin decides who goes first.     |"     );
        System.out.printf("|     %7s, make your guess!            |\n", p    );
        System.out.print ("|            [1] HEADS                     |"     +
                          "|            [0] TAILS                     |"     +
                          "+------------------------------------------+"     );
    }
    public void CoinOne(){
        System.out.print("     ........... "      +
                         "  ...           ..."    +
                         " .                 ."   +
                         ".         ##        ."  +
                         ".        ###        ."  +
                         ".         ##        ."  +
                         ".         ##        ."  +
                         ".       ######      ."  +
                         " .                 ."   +
                         "  ...           ... "   +
                         "     ..........."       );
    }

    public void CoinZero(){
        System.out.println("     ........... "     +
                           "  ...           ..."   +
                           " .                 ."  +
                           ".       ######      ." +
                           ".      ##    ##     ." +
                           ".      ##    ##     ." +
                           ".      ##    ##     ." +
                           ".       ######      ." +
                           " .                 ."  +
                           "  ...           ... "  +
                           "     ..........."      );
    }

    public void DisplaySignMachine(){
    }
}
