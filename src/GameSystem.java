public class GameSystem {
    Player[] allPlayer;
    Player currPlayer;
    Stage currstage;
    ShotGun gun;
    Player firstPlayer;
    Player secondPlayer;

    public GameSystem(int num){
        Stage stage = new Stage(num);
        firstPlayer =new Player(stage.GetStartHP());
        fsecondPlayer =new Player(stage.GetStartHP());

    }

    public Player[] GetAllPlayer(){
        return allPlayer;
    }

    public Player GetCurrPlayer(){
        return currPlayer;
    }

    public void SetCurrPlayer(Player player){
        currPlayer=player;
    }

    public Stage GetStage(){
        return currstage;
    }

    public ShotGun GetGun(){
        return gun;
    }

    public void SetShotGun(){

    }

    public boolean isShoot(){
        return true;
    }

    public boolean HasRoundWinner(){
        return true;
    }

    public int GetGameWinner(){
        return 1;
    }

    public boolean SkipRound(){
        return true;
    }

    public void SwitchPlayer(){
        if()
    }
    public int GuessCoinSide(){
        int num=(int)(Math.random()*100)+1;
        if(num%2==1){
            return 1;
        }
        else{
            return 0;
        }
    }
}
