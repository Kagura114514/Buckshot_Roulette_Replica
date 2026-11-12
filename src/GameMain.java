import java.util.Scanner;
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameSystem gs= new GameSystem();
        Displayer dp = new Displayer();
        int firstPlayerWinNum = 0;
        int secondPlayerWinNum = 0;

        int option, guessCoinSide, coinSide, target, result = 0,position,index;
        String playerName1, playerName2, firstPlayerName, choice;

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
                playerName1 = input.nextLine();
                gs.SetAllPlayerID(playerName1);

                System.out.println();
                System.out.print("========== PLAYER 2 ==========\n" +
                        "Enter Player 2's name: ");
                playerName2 = input.nextLine();
                gs.SetAllPlayerID(playerName2);

                System.out.println();
                System.out.println("========== PLAYERS READY ==========");
                System.out.println(playerName1 + " VS " + playerName2);

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
                gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());
                gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());
                do{

                    gs.SetShotGun(gs.GetStage());//设置枪的在第几阶段，有多少子弹
                    dp.DisplayBulletNum(gs.GetBulletsInDisplay());
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                    do {
                        dp.DisplayerGrid(gs.GetAllPlayer()[0],gs.GetAllPlayer()[1]);//显示grid
                        System.out.printf("Player:%s",gs.GetCurrPlayer().GetName());
                        System.out.println("Choose your target:");
                        System.out.println("1. your");
                        System.out.println("2. opponent");
                        target = input.nextInt();
                        if (target == 1) {
                            result=gs.Shooting(false);
                        } 
                        else if (target == 2) {
                            result=gs.Shooting(true);
                        } 
                        else {
                            System.out.println("Wrong");//暂定
                        }
                        if (result == 1) {
                            System.out.println(" shot the opponent. Blank!");
                        } 
                        else if (result == 2) {
                            System.out.println(" shot the opponent. Live!");
                        } 
                        else if (result == 3) {
                            System.out.println(" shot yourself. Live!");
                        }
                        else{
                            System.out.println(" shot yourself. Blank!");    
                        }
                    } while(!(gs.GetAllPlayer()[0].GetHP()==0) && !(gs.GetAllPlayer()[1].GetHP()==0) && gs.isRemainBullet());
                } while(!(gs.GetAllPlayer()[0].GetHP()==0) && !(gs.GetAllPlayer()[1].GetHP()==0));
                System.out.printf("winner is %s",gs.GetCurrPlayer().GetName());

                gs.SetStage(2);
                gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());
                gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());
                do{
                    gs.SetShotGun(gs.GetStage());//设置枪的在第几阶段，有多少子弹
                    dp.DisplayBulletNum(gs.GetBulletsInDisplay());
                    System.out.println("Press Enter to continue...");
                    input.nextLine();
                    for(int j=0;j<2;j++){
                        dp.DisplayRandomItem(gs.RandomCreatItem());
                        System.out.println("Enter the position: ");
                        position=input.nextInt();
                        input.nextLine();
                        gs.AddItem(position,gs.RandomCreatItem());
                    }
                    do {
                        dp.DisplayerGrid(gs.GetAllPlayer()[0],gs.GetAllPlayer()[1]);//显示grid
                        System.out.println("Use an item?(Y/N)");
                        choice=input.nextLine();
                        if(choice.equals("Y") ||choice.equals("y")){
                            System.out.println("Enter the index: ");
                            index=input.nextInt();
                            if(0<index || index<)

                        }


                        System.out.printf("Player:%s",gs.GetCurrPlayer().GetName());
                        System.out.println("Choose your target:");
                        System.out.println("1. your");
                        System.out.println("2. opponent");
                        target = input.nextInt();
                        if (target == 1) {
                            result=gs.Shooting(false);
                        }
                        else if (target == 2) {
                            result=gs.Shooting(true);
                        }
                        else {
                            System.out.println("Wrong");//暂定
                        }
                        if (result == 1) {
                            System.out.println(" shot the opponent. Blank!");
                        }
                        else if (result == 2) {
                            System.out.println(" shot the opponent. Live!");
                        }
                        else if (result == 3) {
                            System.out.println(" shot yourself. Live!");
                        }
                        else{
                            System.out.println(" shot yourself. Blank!");
                        }
                    } while(!(gs.GetAllPlayer()[0].GetHP()==0) && !(gs.GetAllPlayer()[1].GetHP()==0) && gs.isRemainBullet());
                } while(!(gs.GetAllPlayer()[0].GetHP()==0) && !(gs.GetAllPlayer()[1].GetHP()==0));
                System.out.printf("winner is %s",gs.GetCurrPlayer().GetName());
            }
            else if(option == 2){

            }
            else if(option == 3){

            }
        }

    }
}
