import java.util.Scanner;

public class Bets {
    public static void putbasebets(People player){
        System.out.println("要下注才能开始游戏，请下您的底注(0.2的倍数)：");
        double bets;
        bets = Poker21.sc.nextDouble();

        player.yourbets = bets;
        player.money -= bets;

    }
}
