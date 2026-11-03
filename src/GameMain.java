import java.util.Scanner;
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameSystem gs= new GameSystem();
        Displayer dp = new Displayer();

        int option, guessCoinSide, coinSide;
        String player1, player2;
        // Display Main Menu
        dp.DisplayMainMenu();
        option = input.nextInt();
        input.nextLine();

        if(option < 1 || option > 3){
            System.out.println("Warning");
        }
        else{
            if(option==1){
                System.out.print("========== PLAYER 1 ==========\n" +
                                 "Enter Player 1's name: ");
                player1 = input.nextLine();

                System.out.println();
                System.out.print("========== PLAYER 2 ==========\n" +
                                 "Enter Player 2's name: ");
                player2 = input.nextLine();

                System.out.println();
                System.out.println("========== PLAYERS READY ==========");
                System.out.println(player1 + " VS " + player2);
                // Display CoinToss UI
                dp.CoinToss(player1, player2);
                System.out.print("Enter your guess: ");
                guessCoinSide = input.nextInt();
                input.nextLine();
                System.out.println("Press ENTER to flip the coin...");
                input.nextLine();
                coinSide = gs.GuessCoinSide();
                if(coinSide == 1){
                    dp.CoinOne();  // Display One
                }
                else if(coinSide == 0){
                    dp.CoinZero(); // Display Zero
                }
                if(coinSide == guessCoinSide){
                    System.out.println("Correct guess! You get to go first.");
                }
                else{
                    System.out.println("Wrong guess! Your opponent goes first.");
                }

            }

        }

    }
}
