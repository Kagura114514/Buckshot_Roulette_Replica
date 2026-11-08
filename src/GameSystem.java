public class GameSystem {
    Player[] allPlayer;
    Player currPlayer;
    ShotGun gun;
    Player firstPlayer;
    Player secondPlayer;
    Stage stage;

    public GameSystem( ){
        allPlayer=new Player[2];
    }

    public void SetStage(int num){
        Stage stage = new Stage(num);
    }

    public Stage GetStage(){
        return stage;
    }

    public void SetFirstPlayer(int num,String name){
        GetAllPlayer()[0]=new Player(num,name);
    }

    public void SetSecondPlayer(int num,String name){
        GetAllPlayer()[1]=new Player(num,name);
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
        if(GetCurrPlayer().getType()==0){
            SetCurrPlayer(GetAllPlayer()[1]);
        }
        else{
            SetCurrPlayer(GetAllPlayer()[0]);
        }
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
