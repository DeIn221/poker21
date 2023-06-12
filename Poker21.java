import java.io.IOException;
import java.util.Scanner;

public class Poker21 {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //程序主入口0.
        People player = new People("YOU",0,0,0,10.00);
        People banker = new People("庄家", 0, 0, 0, 1000.00);
        

        while(true){
            Monitor.drwaMainMenu();
            String option = sc.next();
            switch(option){
                case "1":
                    Monitor.flushMonitor();
                    start(player,banker);
                    break;
                case "2":
                    Monitor.drwaGoodbye();
                    sc.close();
                    return ;
                    
                    
            }
        }
        
        
    }

    public static void start(People player, People banker) throws IOException{
        //开始前要下注
        Bets.putbasebets(player);

        //开始游戏
        Game.startGame(player,banker);

        //判断庄家是否可能会有21点
        if(Game.isBankerhave21(banker) == 1){
            //有可能，则询问玩家是否下保险注
            Game.insurerenceOrNot(player);
            
            Game.is21(banker, player);
            
        }
        Game.isHityouOrDoubleOrStop(player,banker);
    }
}
