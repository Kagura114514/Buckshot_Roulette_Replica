import java.util.Scanner;
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameSystem gs= new GameSystem();

        int option;
        String player1;
        String player2;
        int guessCoinSide;
        int coinSide;

        System.out.println("+--------------------------------+");
        System.out.println("|        BUCKSHOT ROULETTE       |");
        System.out.println("+--------------------------------+");
        System.out.println("|         — MAIN MENU —          |");
        System.out.println("|1.START GAME                    |");
        System.out.println("|2.HOW TO PLAY                   |");
        System.out.println("|3.EXIT GAME                     |");
        System.out.println("|......                          |");
        System.out.println("+--------------------------------+");
        System.out.print("Choice one option:  ");
        option= input.nextInt();
        input.nextLine();
        if(option<1 || option>3){
            System.out.println("Warning");
        }
        else{
            if(option==1){
                System.out.println("========== PLAYER 1 ==========");
                System.out.print("Enter Player 1's name: ");
                player1= input.nextLine();

                System.out.println();
                System.out.println("========== PLAYER 2 ==========");
                System.out.print("Enter Player 2's name: ");
                player2 = input.nextLine();

                System.out.println();
                System.out.println("========== PLAYERS READY ==========");
                System.out.println(player1 + " VS " + player2);

                System.out.println("+------------------------------------------+");
                System.out.println("|                COIN TOSS                 |");
                System.out.println("+------------------------------------------+");
                System.out.println("|     The coin decides who goes first.     |");
                System.out.printf("|     %7s, make your guess!            |%n",player1);
                System.out.println("|            [1] HEADS                     |");
                System.out.println("|            [0] TAILS                     |");
                System.out.println("+------------------------------------------+");
                System.out.print("Enter your guess: ");
                guessCoinSide=input.nextInt();
                input.nextLine();
                System.out.println("Press ENTER to flip the coin...");
                input.nextLine();
                coinSide=gs.GuessCoinSide();
                if(coinSide==1){
                    System.out.println("     ........... ");
                    System.out.println("  ...           ...");
                    System.out.println(" .                 .");
                    System.out.println(".         ##        .");
                    System.out.println(".        ###        .");
                    System.out.println(".         ##        .");
                    System.out.println(".         ##        .");
                    System.out.println(".       ######      .");
                    System.out.println(" .                 .");
                    System.out.println("  ...           ... ");
                    System.out.println("     ...........");
                }
                else if(coinSide==0){
                    System.out.println("     ........... ");
                    System.out.println("  ...           ...");
                    System.out.println(" .                 .");
                    System.out.println(".       ######      .");
                    System.out.println(".      ##    ##     .");
                    System.out.println(".      ##    ##     .");
                    System.out.println(".      ##    ##     .");
                    System.out.println(".       ######      .");
                    System.out.println(" .                 .");
                    System.out.println("  ...           ... ");
                    System.out.println("     ...........");
                }
                if(coinSide==guessCoinSide){
                    System.out.println("Correct guess! You get to go first.");
                }
                else{
                    System.out.println("Wrong guess! Your opponent goes first.");
                }

            }

        }

    }
}
