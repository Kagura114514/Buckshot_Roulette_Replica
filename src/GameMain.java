import java.util.Scanner;
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameSystem gs= new GameSystem();
        Displayer dp = new Displayer();
        int firstPlayerWinNum = 0;
        int secondPlayerWinNum = 0;

        int option, guessCoinSide, coinSide, target, result = 0,position,index,opponentIndex;
        String playerName1, playerName2, firstPlayerName, choice;

        while(true) {
            // Display Main Menu
            while(true) {
                dp.DisplayMainMenu();
                option = input.nextInt();
                input.nextLine();
                if (option < 1 || option > 3) {
                    System.out.println("Warning, You Should Input A Valid Value!");
                }
                else {
                    break;
                }
            }
            if(option == 1) {
                playerName1 = dp.EnterName(1);
                gs.SetAllPlayerID(playerName1);
                System.out.println();
                playerName2 = dp.EnterName(2);
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

                dp.Enter("Press ENTER to flip the coin...");
                coinSide = gs.GuessCoinSide();
                if (coinSide == 1) {
                    dp.CoinOne();  // Display One
                }
                else if (coinSide == 0) {
                    dp.CoinZero(); // Display Zero
                }
                if (coinSide == guessCoinSide) {
                    System.out.println("Correct guess! You get to go first.");
                }
                else {
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
                    dp.Enter("Press Enter to continue...");
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
                            System.out.println("Select a valid target..");//暂定
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
                gs.SetRoundWinner(gs.GetAllPlayer()[0], gs.GetAllPlayer()[1]); // Set Round Winner StageWins +1
                //System.out.printf("winner is %s",gs.GetCurrPlayer().GetName());

                gs.SetStage(2);
                gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());
                gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());
                do{
                    gs.SetShotGun(gs.GetStage());//设置枪的在第几阶段，有多少子弹
                    dp.DisplayBulletNum(gs.GetBulletsInDisplay());// 展示几个实弹几个空弹
                    dp.Enter("Press Enter to continue...");
                    for(int j=0;j<2;j++){//发两次道具
                        Items item = gs.RandomCreatItem();
                        dp.DisplayRandomItem(item);//展示发的道具（随机创建道具）
                        position = dp.EnterPos("position");
                        input.nextLine();
                        gs.AddItem(position-1, item);// 把道具加到玩家里
                        //展示加了道具后的棋盘
                        dp.DisplayerGridWithItem(gs.GetCurrPlayer(),gs.getAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.getAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());
                    }
                    do {
                        do {
                            dp.DisplayerGridWithItem(gs.GetCurrPlayer(),gs.getAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.getAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());//显示grid
                            System.out.println("Use an item?(Y/N)");
                            choice = input.nextLine().toUpperCase();
                            while(true) {
                                if (choice.length() <= 0 || choice == null || choice.length() >= 2 || !((choice.equals("Y") || choice.equals("N")))) {
                                    System.out.println("Enter only Y or N !");
                                    choice = input.nextLine().toUpperCase();
                                }
                                else{
                                    break;
                                }
                            }
                            if (choice.equals("Y")) {
                                index = dp.EnterPos("index");
                                if (gs.GetCurrPlayer().GetAllItem()[index] instanceof BurnerPhone) {
                                    gs.UseItem(index);
                                }
                                else if (gs.GetCurrPlayer().GetAllItem()[index] instanceof Adrenaline) {
                                    opponentIndex = dp.EnterPos("opponent index");
                                    gs.UseItem(index, opponentIndex);
                                }
                                else{
                                    gs.UseItem(index);
                                }
                            }
                        }while(choice.equals("Y"));

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
                System.out.printf("winner is %s\n",gs.GetCurrPlayer().GetName());
            }
            else if(option == 2){

            }
            else if(option == 3){
                System.out.println("Goodbye.....");
                System.out.print(".\n.\n.\n.\n.\n.\n.\nSystem is closing...");
                break;
            }
        }

    }
}
