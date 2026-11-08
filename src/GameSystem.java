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

    public void SetShotGun(Stage stage){
        gun =new ShotGun(stage);
    }

    public ShotGun GetGun(){
        return gun;
    }//检测在随机的弹夹中，有几个实弹和几个空弹并有序排列
    public int[] GetBulletsInDisplay(){
        int blankNum=0;
        int liveNum=0;
        for(int i=0;i<gun.GetBulletsInGun().length;i++){
            if(gun.GetBulletsInGun()[i]==0){
                blankNum++;
            }
            else{
                liveNum++;
            }
        }
        int[] list = new int[blankNum+liveNum];
        for(int i=0;i<blankNum;i++){
            list[i]=0;
        }
        for(int i=blankNum;i<blankNum+liveNum;i++){
            list[i]=1;
        }

        return list;
    }

    public void SetStage(int num){
        stage = new Stage(num);
    }

    public Stage GetStage(){
        return stage;
    }

    public void SetFirstPlayer(int num,String name){//把第一个玩家放在第一位
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
