import java.util.Scanner;
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameSystem gs= new GameSystem();
        Displayer dp = new Displayer();

        int option, guessCoinSide, coinSide, target;
        String player1, player2, firstPlayerName;

        // Display Main Menu
        dp.DisplayMainMenu();
        option = input.nextInt();
        input.nextLine();

        if(option < 1 || option > 3){
            System.out.println("Warning");
        }
        else{
            if(option==1) {
                System.out.print("========== PLAYER 1 ==========\n" +
                        "Enter Player 1's name: ");
                player1 = input.nextLine();
                gs.SetFirstPlayer(0, player1);

                System.out.println();
                System.out.print("========== PLAYER 2 ==========\n" +
                        "Enter Player 2's name: ");
                player2 = input.nextLine();
                gs.SetSecondPlayer(1, player2);

                System.out.println();
                System.out.println("========== PLAYERS READY ==========");
                System.out.println(player1 + " VS " + player2);

                // Display CoinToss UI

                int i = (int) (Math.random() * 2) + 1;
                if (i == 1) {//这一串本来是Displayer,为了方便把它放到main
                    firstPlayerName = gs.GetAllPlayer()[0].GetName();
                    gs.SetCurrPlayer(gs.GetAllPlayer()[0]);//设为先手
                } else {
                    firstPlayerName = gs.GetAllPlayer()[1].GetName();
                    gs.SetCurrPlayer(gs.GetAllPlayer()[1]);//设为先手
                }
                dp.CoinToss(firstPlayerName);//选择谁来猜硬币
                System.out.print("Enter your guess: ");
                guessCoinSide = input.nextInt();
                input.nextLine();
                System.out.println("Press ENTER to flip the coin...");
                input.nextLine();
                coinSide = gs.GuessCoinSide();
                if (coinSide == 1) {
                    dp.CoinOne();  // Display One
                } else if (coinSide == 0) {
                    dp.CoinZero(); // Display Zero
                }
                if (coinSide == guessCoinSide) {
                    System.out.println("Correct guess! You get to go first.");
                } else {
                    System.out.println("Wrong guess! Your opponent goes first.");
                    gs.SwitchPlayer();//猜错后，换人
                }

                //从这开始是游戏的第一阶段
                gs.SetStage(1);


                gs.SetShotGun(gs.GetStage());//设置枪的在第几阶段，有多少子弹
                dp.DisplayerGridWithShell(gs.GetBulletsInDisplay());//在grid里显示几颗实弹，几颗空单
                System.out.println("Press Enter to continue...");
                input.nextLine();
                dp.DisplayerGrid();//显示grid
                System.out.println("Choose your target:");
                System.out.println("1. your");
                System.out.println("2. opponent");
                target = input.nextInt();
            }
        }

    }
}
