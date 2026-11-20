import java.util.Scanner;
public class GameMain {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Displayer dp = new Displayer();
        Player winner;
        int coinSide, target, position, index, opponentIndex, opt;
        String guessCoinSide, option, playerName1, playerName2, firstPlayerName, choice;
        boolean round3 = false;

        while(true) {
            // Display Main Menu
            while(true) {
                dp.DisplayMainMenu();
                option = input.nextLine();
                if (option.length() != 1 || option == null || (option.charAt(0) != 49 && option.charAt(0) != 50 && option.charAt(0) != 51)) {//Determine whether the input meets the requirements
                    System.out.println("Warning, You Should Input A Valid Value!");
                }
                else {
                    opt = Integer.parseInt(option);
                    break;
                }
            }
            if(opt == 1) { // if player wish to play the game
                GameSystem gs= new GameSystem();//game starts
                playerName1 = dp.EnterName(1);//Create the first player
                gs.SetAllPlayerID(playerName1);
                System.out.println();
                playerName2 = dp.EnterName(2);//Create the second player
                gs.SetAllPlayerID(playerName2);

                System.out.println();
                System.out.println("========== PLAYERS READY ==========");
                System.out.println(playerName1 + " VS " + playerName2);

                // Display CoinToss UI

                int i = (int) (Math.random() * 2) + 1;
                if (i == 1) {//Randomly select one player to guess the coin.
                    firstPlayerName = gs.GetAllPlayer()[0].GetName();
                    gs.SetCurrPlayer(gs.GetAllPlayer()[0]);//set to the first player
                } else {
                    firstPlayerName = gs.GetAllPlayer()[1].GetName();
                    gs.SetCurrPlayer(gs.GetAllPlayer()[1]);//set to the first player
                }
                dp.CoinToss(firstPlayerName);//choose someone to toss the coin
                while(true) {
                    System.out.print("Enter your guess: ");
                    guessCoinSide = input.nextLine();
                    if(guessCoinSide.length() != 1 || (guessCoinSide.charAt(0) != 48 && guessCoinSide.charAt(0) != 49)) {//Determine whether the input meets the requirements
                        System.out.println("Warning, You Should Input A Valid Value!");
                    }
                    else{
                        break;
                    }
                    System.out.println(" ");
                }
                dp.Enter("Press ENTER to flip the coin...");
                coinSide = gs.GuessCoinSide();//output outcome
                if (coinSide == 1) {//determine
                    dp.CoinOne();  // Display One
                }
                else if (coinSide == 0) {
                    dp.CoinZero(); // Display Zero
                }
                if (coinSide == Integer.parseInt(guessCoinSide)) {
                    System.out.println("Correct guess! You get to go first.");
                }
                else {
                    System.out.println("Wrong guess! Your opponent goes first.");
                    gs.SwitchPlayer();//Switch players after an incorrect guess
                }

                String playerMovement = null;
                System.out.print("");
                System.out.println("Welcome to the First Stage\n");
                gs.SetStage(1);// first stage
                gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());//set started HP
                gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());//set started HP
                do{
                    gs.SetShotGun(gs.GetStage()); //Setup for the ShotGun
                    dp.DisplayBullets(gs.GetGun());
                    if(playerMovement != null){//Determine whether the player can move
                        System.out.println(playerMovement + "\n");
                        playerMovement = null;
                    }
                    dp.DisplayBulletNum(gs.GetBulletsInDisplay());//display type of bullets
                    dp.Enter("Press Enter to continue...");
                    do {
                        dp.DisplayerGrid(gs.GetCurrPlayer(),gs.GetAnotherPlayer(gs.GetCurrPlayer()));// display grid
                        if(playerMovement != null){//Determine whether the player can move
                            System.out.println(playerMovement + "\n");
                            playerMovement = null;
                        }
                        System.out.printf("Player:%s\n",gs.GetCurrPlayer().GetName());
                        System.out.println("Choose your target:");
                        System.out.println("1. your");
                        System.out.println("2. opponent");
                        target = dp.EnterOpt();
                        if (target == 1) {//Determine whether the input meets the requirements
                            playerMovement = gs.Shooting(false);
                        }
                        else if (target == 2) {
                            playerMovement = gs.Shooting(true);
                        }
                    } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0 && gs.isRemainBullet());//If all players have health points greater than zero and the ammunition has run out, proceed to the next round.
                } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0);//The first stage of the game ends when one player's life points reach zero.
                if(playerMovement != null){
                    System.out.println(playerMovement + "\n");
                    playerMovement = null;
                }
                winner=gs.SetRoundWinner(gs.GetAllPlayer()[0], gs.GetAllPlayer()[1]); // Set Round Winner StageWins +1
                System.out.printf("Round winner is %s",winner.GetName());


                System.out.println(" ");// \n
                System.out.println("We are now entering into the Second Stage...\n");
                gs.SetStage(2);//start second stage
                gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());//set started Hp
                gs.GetAllPlayer()[0].SetMaxHp(gs.GetStage());//set max Hp
                gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());//set started Hp
                gs.GetAllPlayer()[1].SetMaxHp(gs.GetStage());//set max Hp
                do{
                    gs.SetShotGun(gs.GetStage());//Set the stage for the weapon and the number of bullets it contains.
                    dp.DisplayBullets(gs.GetGun());
                    if(playerMovement != null){//Determine whether the player can move
                        System.out.println(playerMovement + "\n");
                        playerMovement = null;
                    }
                    dp.DisplayBulletNum(gs.GetBulletsInDisplay());//display type of bullets
                    dp.Enter("Press Enter to continue...");
                    boolean p1Received = false;//Determine whether to grant an item to the user in the current state.
                    boolean p2Received = false;
                    do {
                        Player curr = gs.GetCurrPlayer();
                        if(curr == gs.GetAllPlayer()[0] && p1Received == false) {
                            for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//Distribute the items that should be issued for each round
                                if(playerMovement != null){
                                    System.out.println(playerMovement + "\n");
                                    playerMovement = null;
                                }
                                dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                Items item = gs.RandomCreatItem();
                                dp.DisplayRandomItem(item, gs.GetCurrPlayer());//Display generated items (randomly created items)
                                position = dp.EnterPos("position");
                                gs.AddItem(position, item);// Add the item to the player.
                            }
                            p1Received = true;
                        }
                        else if(curr == gs.GetAllPlayer()[1] && p2Received == false){//another player
                            for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//Distribute the items that should be issued for each round
                                if(playerMovement != null){
                                    System.out.println(playerMovement + "\n");
                                    playerMovement = null;
                                }
                                dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                Items item = gs.RandomCreatItem();
                                dp.DisplayRandomItem(item, gs.GetCurrPlayer());//Display generated items (randomly created items)
                                position = dp.EnterPos("position");
                                gs.AddItem(position, item);// Add the item to the player.
                            }
                            p2Received = true;
                        }
                        // player using items
                        if(gs.GetCurrPlayer().CountItems() > 0) { // if current player is having at least 1 item
                            do {
                                dp.DisplayerGridWithItem(gs.GetCurrPlayer(), gs.GetAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.GetAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());//显示grid
                                if(playerMovement != null){
                                    System.out.println(playerMovement + "\n");
                                    playerMovement = null;
                                }
                                System.out.printf("Player:%s\n", gs.GetCurrPlayer().GetName());
                                System.out.println("Use an item?(Y/N)");
                                choice = input.nextLine().toUpperCase();
                                while (true) {
                                    if (choice.length() <= 0 || choice == null || choice.length() >= 2 || !((choice.equals("Y") || choice.equals("N")))) {//Determine whether the entries have been filled in correctly.
                                        System.out.println("Enter only Y or N !");
                                        choice = input.nextLine().toUpperCase();
                                    } else {
                                        break;
                                    }
                                }
                                if (choice.equals("Y")) {
                                    while (true) {
                                        index = dp.EnterPos("index");
                                        if (gs.GetCurrPlayer().GetAllItem()[index] == null) {//Determine whether the entries have been filled in correctly.
                                            System.out.println("Enter a index with a item !");
                                        } else {
                                            break;
                                        }
                                    }
                                    if (gs.GetCurrPlayer().GetAllItem()[index] instanceof BurnerPhone) {//determine type of item
                                        gs.UseItem(index);
                                    } else if (gs.GetCurrPlayer().GetAllItem()[index] instanceof Adrenaline) {
                                        opponentIndex = dp.EnterPos("opponent index");
                                        gs.UseItem(index, opponentIndex, gs.GetStage());
                                    } else {
                                        gs.UseItem(index);
                                    }
                                }
                            } while (choice.equals("Y") && gs.isRemainBullet() && gs.GetAllPlayer()[0].GetHP() > 0 && gs.GetAllPlayer()[1].GetHP() > 0 && gs.GetCurrPlayer().CountItems() > 0);//Determine whether an item can be used
                        }
                        else{ // if current player has no item
                            dp.DisplayerGridWithItem(gs.GetCurrPlayer(), gs.GetAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.GetAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());
                            if(playerMovement != null){
                                System.out.println(playerMovement + "\n");
                                playerMovement = null;
                            }
                        }
                        if (!gs.isRemainBullet() || gs.GetAllPlayer()[0].GetHP() <= 0 || gs.GetAllPlayer()[1].GetHP() <= 0) {
                            break;
                        }
                        System.out.printf("Player:%s\n",gs.GetCurrPlayer().GetName());
                        System.out.println("Choose your target:");
                        System.out.println("1. your");
                        System.out.println("2. opponent");
                        target = dp.EnterOpt();
                        if (target == 1) {//determine target
                            playerMovement = gs.Shooting(false);
                        }
                        else if (target == 2) {
                            playerMovement = gs.Shooting(true);
                        }
                    } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0 && gs.isRemainBullet());//If all players have health points greater than zero and the ammunition has run out, proceed to the next round.
                } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0);//The first stage of the game ends when one player's life points reach zero.
                if(playerMovement != null){
                    System.out.println(playerMovement + "\n");
                    playerMovement = null;
                }
                winner=gs.SetRoundWinner(gs.GetAllPlayer()[0], gs.GetAllPlayer()[1]); // Set Round Winner StageWins +1
                System.out.printf("Round winner is %s",winner.GetName());
                if(gs.GetGameWinner() == 10){//If the same player wins twice, the game ends.
                    System.out.println("Game Over! Game Winner is " + gs.GetAllPlayer()[0].GetName());
                }
                else if(gs.GetGameWinner() == 11){
                    System.out.println("Game Over! Game Winner is " + gs.GetAllPlayer()[1].GetName());
                }
                else{ // When there is no one won 2 games in a row
                    round3 = true;
                }

                while(round3){
                    System.out.println(" ");// \n
                    System.out.println("We are now in the Third Stage !\n");
                    gs.SetStage(3);
                    gs.GetAllPlayer()[0].SetHP(gs.GetStage().GetStartHP());
                    gs.GetAllPlayer()[0].SetMaxHp(gs.GetStage());
                    gs.GetAllPlayer()[1].SetHP(gs.GetStage().GetStartHP());
                    gs.GetAllPlayer()[1].SetMaxHp(gs.GetStage());
                    do{
                        gs.SetShotGun(gs.GetStage());//Set the stage for the weapon and the number of bullets it contains.
                        dp.DisplayBullets(gs.GetGun());
                        if(playerMovement != null){
                            System.out.println(playerMovement + "\n");
                            playerMovement = null;
                        }
                        dp.DisplayBulletNum(gs.GetBulletsInDisplay());//display type of bullets
                        dp.Enter("Press Enter to continue...");
                        boolean p1Received = false;
                        boolean p2Received = false;
                        do {
                            Player curr = gs.GetCurrPlayer();
                            if(curr == gs.GetAllPlayer()[0] && p1Received == false) {
                                for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//Distribute the items that should be issued for each round
                                    if(playerMovement != null){
                                        System.out.println(playerMovement + "\n");
                                        playerMovement = null;
                                    }
                                    dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                    Items item = gs.RandomCreatItem();
                                    dp.DisplayRandomItem(item, gs.GetCurrPlayer());//Display generated items (randomly created items)
                                    position = dp.EnterPos("position");
                                    gs.AddItem(position, item);// 把道具加到玩家里
                                }
                                p1Received = true;
                            }
                            else if(curr == gs.GetAllPlayer()[1] && p2Received == false){
                                for (int z = 0; z < gs.GetStage().GetPerRoundItemNum(); z++) {//Distribute the items that should be issued for each round
                                    if(playerMovement != null){
                                        System.out.println(playerMovement + "\n");
                                        playerMovement = null;
                                    }
                                    dp.DisplayMiniGrid(gs.GetCurrPlayer().GetAllItem());
                                    Items item = gs.RandomCreatItem();
                                    dp.DisplayRandomItem(item, gs.GetCurrPlayer());//Display generated items (randomly created items)
                                    position = dp.EnterPos("position");
                                    gs.AddItem(position, item);// Add the item to the player.
                                }
                                p2Received = true;
                            }
                            // player using items
                            if(gs.GetCurrPlayer().CountItems() > 0) {
                                do {
                                    dp.DisplayerGridWithItem(gs.GetCurrPlayer(), gs.GetAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.GetAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());//显示grid
                                    if(playerMovement != null){
                                        System.out.println(playerMovement + "\n");
                                        playerMovement = null;
                                    }
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
                            else{
                                dp.DisplayerGridWithItem(gs.GetCurrPlayer(), gs.GetAnotherPlayer(gs.GetCurrPlayer()), gs.GetCurrPlayer().GetAllItem(), gs.GetAnotherPlayer(gs.GetCurrPlayer()).GetAllItem());
                                if(playerMovement != null){
                                    System.out.println(playerMovement + "\n");
                                    playerMovement = null;
                                }
                            }
                            if (!gs.isRemainBullet() || gs.GetAllPlayer()[0].GetHP() <= 0 || gs.GetAllPlayer()[1].GetHP() <= 0) {
                                break;
                            }
                            System.out.printf("Player:%s\n",gs.GetCurrPlayer().GetName());
                            System.out.println("Choose your target:");
                            System.out.println("1. your");
                            System.out.println("2. opponent");
                            target = dp.EnterOpt();
                            if (target == 1) {
                                gs.Shooting(false);
                            }
                            else if (target == 2) {
                                gs.Shooting(true);
                            }
                        } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0 && gs.isRemainBullet());
                    } while(gs.GetAllPlayer()[0].GetHP()>0 && gs.GetAllPlayer()[1].GetHP()>0);
                    if(playerMovement != null){
                        System.out.println(playerMovement + "\n");
                        playerMovement = null;
                    }

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
            else if(opt == 2){

            }
            else if(opt == 3){
                System.out.println("Goodbye.....");
                System.out.print(".\n.\n.\n.\n.\n.\n.\nSystem is closing...");
                break;
            }
        }

    }
}