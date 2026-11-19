import java.util.Scanner;
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Displayer dp = new Displayer();
        int firstPlayerWinNum = 0;
        int secondPlayerWinNum = 0;
        Player winner;
        int option, guessCoinSide, coinSide, target, result = 0,position,index,opponentIndex;
        String playerName1, playerName2, firstPlayerName, choice;
        boolean round3 = false;

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
                GameSystem gs= new GameSystem();
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
                while(true) {
                    System.out.print("Enter your guess: ");
                    guessCoinSide = input.nextInt();
                    if(guessCoinSide == 1 ||  guessCoinSide == 0) {
                        break;
                    }
                    else{
                        System.out.println("Warning, You Should Input A Valid Value!");
                    }
                    System.out.println(" ");
                }
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
                System.out.print("");//换行
                System.out.println("The first stage");
                gs.SetStage(1);
                gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());
                gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());
                do{

                    gs.SetShotGun(gs.GetStage());//设置枪的在第几阶段，有多少子弹
                    dp.DisplayBullets(gs.GetGun());
                    dp.DisplayBulletNum(gs.GetBulletsInDisplay());
                    dp.Enter("Press Enter to continue...");
                    do {
                        dp.DisplayerGrid(gs.GetCurrPlayer(),gs.getAnotherPlayer(gs.GetCurrPlayer()));//显示grid
                        System.out.printf("Player:%s\n",gs.GetCurrPlayer().GetName());
                        System.out.println("Choose your target:");
                        System.out.println("1. your");
                        System.out.println("2. opponent");
                        target = input.nextInt();
                        if (target == 1) {
                            gs.Shooting(false);
                        }
                        else if (target == 2) {
                            gs.Shooting(true);
                        }
                        else {
                            System.out.println("Select a valid target..");//暂定
                        }
                    } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0 && gs.isRemainBullet());
                } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0);
                winner=gs.SetRoundWinner(gs.GetAllPlayer()[0], gs.GetAllPlayer()[1]); // Set Round Winner StageWins +1
                System.out.printf("Round winner is %s",winner.GetName());


                System.out.println(" ");//换行
                System.out.println("The second stage");
                gs.SetStage(2);
                gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());
                gs.GetAllPlayer()[0].SetMaxHp(gs.GetStage());
                gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());
                gs.GetAllPlayer()[1].SetMaxHp(gs.GetStage());
                do{
                    gs.SetShotGun(gs.GetStage());//设置枪的在第几阶段，有多少子弹
                    dp.DisplayBullets(gs.GetGun());
                    dp.DisplayBulletNum(gs.GetBulletsInDisplay());// 展示几个实弹几个空弹
                    dp.Enter("Press Enter to continue...");
                    boolean p1Received = false;
                    boolean p2Received = false;
                    do {
                        Player curr = gs.GetCurrPlayer();
                        if(curr == gs.GetAllPlayer()[0] && p1Received == false) {
                            for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//发两次道具
                                dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                Items item = gs.RandomCreatItem();
                                dp.DisplayRandomItem(item, gs.GetCurrPlayer());//展示发的道具（随机创建道具）
                                position = dp.EnterPos("position");
                                gs.AddItem(position, item);// 把道具加到玩家里
                            }
                            p1Received = true;
                        }
                        else if(curr == gs.GetAllPlayer()[1] && p2Received == false){
                            for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//发两次道具
                                dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                Items item = gs.RandomCreatItem();
                                dp.DisplayRandomItem(item, gs.GetCurrPlayer());//展示发的道具（随机创建道具）
                                position = dp.EnterPos("position");
                                gs.AddItem(position, item);// 把道具加到玩家里
                            }
                            p2Received = true;
                        }
                        // player using items
                        if(gs.GetCurrPlayer().CountItems() > 0) {
                            do {
                                dp.DisplayerGridWithItem(gs.GetCurrPlayer(), gs.getAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.getAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());//显示grid
                                System.out.printf("Player:%s\n", gs.GetCurrPlayer().GetName());
                                System.out.println("Use an item?(Y/N)");
                                choice = input.nextLine().toUpperCase();
                                while (true) {
                                    if (choice.length() <= 0 || choice == null || choice.length() >= 2 || !((choice.equals("Y") || choice.equals("N")))) {
                                        System.out.println("Enter only Y or N !");
                                        choice = input.nextLine().toUpperCase();
                                    } else {
                                        break;
                                    }
                                }
                                if (choice.equals("Y")) {
                                    while (true) {
                                        index = dp.EnterPos("index");
                                        if (gs.GetCurrPlayer().GetAllItem()[index] == null) {
                                            System.out.println("Enter a index with a item !");
                                        } else {
                                            break;
                                        }
                                    }
                                    if (gs.GetCurrPlayer().GetAllItem()[index] instanceof BurnerPhone) {
                                        gs.UseItem(index);
                                    } else if (gs.GetCurrPlayer().GetAllItem()[index] instanceof Adrenaline) {
                                        opponentIndex = dp.EnterPos("opponent index");
                                        gs.UseItem(index, opponentIndex, gs.GetStage());
                                    } else {
                                        gs.UseItem(index);
                                    }
                                }
                            } while (choice.equals("Y") && gs.isRemainBullet() && gs.GetAllPlayer()[0].GetHP() > 0 && gs.GetAllPlayer()[1].GetHP() > 0 && gs.GetCurrPlayer().CountItems() > 0);
                        }
                        if (!gs.isRemainBullet() || gs.GetAllPlayer()[0].GetHP() <= 0 || gs.GetAllPlayer()[1].GetHP() <= 0) {
                            break;
                        }
                        System.out.printf("Player:%s\n",gs.GetCurrPlayer().GetName());
                        System.out.println("Choose your target:");
                        System.out.println("1. your");
                        System.out.println("2. opponent");
                        target = input.nextInt();
                        if (target == 1) {
                            gs.Shooting(false);
                        }
                        else if (target == 2) {
                            gs.Shooting(true);
                        }
                        else {
                            System.out.println("Wrong");//暂定
                        }
                    } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0 && gs.isRemainBullet());
                } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0);


                winner=gs.SetRoundWinner(gs.GetAllPlayer()[0], gs.GetAllPlayer()[1]); // Set Round Winner StageWins +1
                System.out.printf("Round winner is %s",winner.GetName());
                if(gs.GetGameWinner() == 10){
                    System.out.println("Game Over! Game Winner is " + gs.GetAllPlayer()[0].GetName());
                }
                else if(gs.GetGameWinner() == 11){
                    System.out.println("Game Over! Game Winner is " + gs.GetAllPlayer()[1].GetName());
                }
                else{ // When there is no one won 2 games in a row
                    round3 = true;
                }

                while(round3){
                    System.out.println(" ");//换行
                    System.out.println("The Third stage");
                    gs.SetStage(3);
                    gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());
                    gs.GetAllPlayer()[0].SetMaxHp(gs.GetStage());
                    gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());
                    gs.GetAllPlayer()[1].SetMaxHp(gs.GetStage());
                    do{
                        gs.SetShotGun(gs.GetStage());//设置枪的在第几阶段，有多少子弹
                        dp.DisplayBullets(gs.GetGun());
                        dp.DisplayBulletNum(gs.GetBulletsInDisplay());// 展示几个实弹几个空弹
                        dp.Enter("Press Enter to continue...");
                        boolean p1Received = false;
                        boolean p2Received = false;
                        do {
                            Player curr = gs.GetCurrPlayer();
                            if(curr == gs.GetAllPlayer()[0] && p1Received == false) {
                                for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//发两次道具
                                    dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                    Items item = gs.RandomCreatItem();
                                    dp.DisplayRandomItem(item, gs.GetCurrPlayer());//展示发的道具（随机创建道具）
                                    position = dp.EnterPos("position");
                                    gs.AddItem(position, item);// 把道具加到玩家里
                                }
                                p1Received = true;
                            }
                            else if(curr == gs.GetAllPlayer()[1] && p2Received == false){
                                for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//发两次道具
                                    dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                    Items item = gs.RandomCreatItem();
                                    dp.DisplayRandomItem(item, gs.GetCurrPlayer());//展示发的道具（随机创建道具）
                                    position = dp.EnterPos("position");
                                    gs.AddItem(position, item);// 把道具加到玩家里
                                }
                                p2Received = true;
                            }
                            // player using items
                            if(gs.GetCurrPlayer().CountItems() > 0) {
                                do {
                                    dp.DisplayerGridWithItem(gs.GetCurrPlayer(), gs.getAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.getAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());//显示grid
                                    System.out.printf("Player:%s\n", gs.GetCurrPlayer().GetName());
                                    System.out.println("Use an item?(Y/N)");
                                    choice = input.nextLine().toUpperCase();
                                    while (true) {
                                        if (choice.length() <= 0 || choice == null || choice.length() >= 2 || !((choice.equals("Y") || choice.equals("N")))) {
                                            System.out.println("Enter only Y or N !");
                                            choice = input.nextLine().toUpperCase();
                                        } else {
                                            break;
                                        }
                                    }
                                    if (choice.equals("Y")) {
                                        while (true) {
                                            index = dp.EnterPos("index");
                                            if (gs.GetCurrPlayer().GetAllItem()[index] == null) {
                                                System.out.println("Enter a index with a item !");
                                            } else {
                                                break;
                                            }
                                        }
                                        if (gs.GetCurrPlayer().GetAllItem()[index] instanceof BurnerPhone) {
                                            gs.UseItem(index);
                                        } else if (gs.GetCurrPlayer().GetAllItem()[index] instanceof Adrenaline) {
                                            opponentIndex = dp.EnterPos("opponent index");
                                            gs.UseItem(index, opponentIndex, gs.GetStage());
                                        } else {
                                            gs.UseItem(index);
                                        }
                                    }
                                } while (choice.equals("Y") && gs.isRemainBullet() && gs.GetAllPlayer()[0].GetHP() > 0 && gs.GetAllPlayer()[1].GetHP() > 0 && gs.GetCurrPlayer().CountItems() > 0);
                            }
                            if (!gs.isRemainBullet() || gs.GetAllPlayer()[0].GetHP() <= 0 || gs.GetAllPlayer()[1].GetHP() <= 0) {
                                break;
                            }
                            System.out.printf("Player:%s\n",gs.GetCurrPlayer().GetName());
                            System.out.println("Choose your target:");
                            System.out.println("1. your");
                            System.out.println("2. opponent");
                            target = input.nextInt();
                            if (target == 1) {
                                gs.Shooting(false);
                            }
                            else if (target == 2) {
                                gs.Shooting(true);
                            }
                            else {
                                System.out.println("Wrong");//暂定
                            }
                        } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0 && gs.isRemainBullet());
                    } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0);
                    winner = gs.SetRoundWinner(gs.GetAllPlayer()[0], gs.GetAllPlayer()[1]); // Set Round Winner StageWins +1
                    System.out.printf("Round winner is %s",winner.GetName());
                    round3 = false;
                    if(gs.GetGameWinner() == 10){
                        System.out.println("Game Over! Game Winner is " + gs.GetAllPlayer()[0].GetName());
                    }
                    else if(gs.GetGameWinner() == 11){
                        System.out.println("Game Over! Game Winner is " + gs.GetAllPlayer()[1].GetName());
                    }
                }
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