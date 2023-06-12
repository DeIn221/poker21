import java.io.IOException;

public class Monitor {
    public static void flushMonitor() throws IOException{
        System.out.print("\033[H\033[2J");
    }

    public static void drwaMainMenu() throws IOException{
        flushMonitor();
        System.out.println("======Welcome to Poker21======");
        System.out.println("    ======Main Menu======     ");
        System.out.println("         【1】开始游戏          ");
        System.out.println("         【2】退出游戏          ");
        System.out.println("                              ");
        System.out.println("                              ");
        System.out.println("                              ");
        System.out.println("     Copyright to chuanko     ");
        
    }
    public static void drwaGoodbye() throws IOException{
        flushMonitor();
        System.out.println("                              ");
        System.out.println("                              ");
        System.out.println("                              ");
        System.out.println("  == == == =goodbye= == == == ");
        System.out.println("                              ");
        System.out.println("                              ");
        System.out.println("                              ");
        System.out.println("     Copyright to chuanko     ");
    }

    public static void drwaGame(People player, People banker) throws IOException {
        flushMonitor();
        System.out.println("庄家的牌：");
        banker.showyourcards();
        System.out.print("庄家的点数：");
        banker.calpoint();
        banker.showyourpoint();
        
        System.out.println("====================================");
        
        System.out.println("你的牌：");
        player.showyourcards();
        System.out.print("你的点数：");
        player.calpoint();
        player.showyourpoint();
        System.out.print("你下的注：");
        player.showyourbets();
        System.out.print("你的钱：");
        player.showyourmoney();
        
    }
}
