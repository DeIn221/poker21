import java.io.IOException;
import java.util.ArrayList;

public class Game {
    static ArrayList<Poker> alreadygivepokers = new ArrayList<Poker>();

    public static void startGame(People player,People banker) throws IOException{
        alreadygivepokers.clear();
        
        //下注

        //一人发一张牌，到达游戏第一阶段
        Card.giveCard(alreadygivepokers, player);
        Card.giveCard(alreadygivepokers, banker);
        Card.giveCard(alreadygivepokers, player);
        Card.giveCard(alreadygivepokers, banker);

        //玩家的两张牌显示出来，庄家只显示第二张
        player.getYourCards().get(0).showorback = 1;
        player.getYourCards().get(1).showorback = 1;

        banker.getYourCards().get(0).showorback = 0;
        banker.getYourCards().get(1).showorback = 1;
        
        Monitor.drwaGame(player,banker);

        //如果你的点数直接就是21点，那么庄家如果可能有21点，那则看牌比较，如果庄家不可能不要牌达到21点，则以3:2比例给玩家奖励
        if(player.yourpoint == 21){
            if(banker.getYourCards().get(1).howmany >= 9
                || banker.getYourCards().get(1).howmany == 0){
                //庄家可能有21点
                banker.getYourCards().get(0).showorback = 1;
                Monitor.drwaGame(player, banker);

                settleResult(player, banker);
            }else{
                System.out.println("直接以你底注的3:2的比例获得奖励");
                settle21(player, banker);
            }
        }
        
    }

    public static int isBankerhave21(People banker){
        //判断庄家有没有可能会有21点，玩家选择是否下保险注
        //庄家翻过来的的卡，为A就有可能21点，需要下保险注
        if(banker.getYourCards().get(1).howmany == 0){
            return 1;
        }else{
            return 0;
        }
    }

    public static int insurerenceOrNot(People player){
        double insurerencebets = player.yourbets/2;
        System.out.println("庄家可能有21点，是否下"+insurerencebets+"￥的保险注（Y/N）?");
        String youranswer;
        youranswer = Poker21.sc.next();
    

        if(youranswer.equals("Y")){
            //选择下保险注
            player.money -= insurerencebets;
            player.insurerence = 1;
            return 1;
        }else{

            player.insurerence = 0;
            return 0;
        }
    }

    public static void is21(People banker,People player) throws IOException {
        //如果第一张底牌是10，J，Q，K则有21点

        if(banker.getYourCards().get(0).howmany >= 9){
            System.out.println("庄家有21点");
            banker.getYourCards().get(0).showorback = 1;
            System.out.println("庄家的牌是：");
            banker.showyourcards();

            if(player.insurerence == 1){
                System.out.println("因为你下了保险注，得到了赔偿");
                settleInsurerence(player,banker);
            }else{
                settleResult(player, banker);
            }
        }else{
            System.out.println("庄家没有21点，你输掉了保险注");
            banker.money += player.yourbets/2;
        }
    }

    private static void settleInsurerence(People player, People banker) throws IOException {
        player.money+=player.yourbets;
        player.yourbets = 0;
        player.insurerence = 0;

        player.getYourCards().clear();
        banker.getYourCards().clear();

        player.allgamecounts++;
        player.winrate = (1.0*player.wingamecounts)/(player.allgamecounts*1.0)*100;

        isRegameOrCheckInfo(player, banker);
    }

    public static void isRegameOrCheckInfo(People player,People banker) throws IOException{
        System.out.println("是否还要再玩(Y/N)?你也可以查看你目前的战绩和信息(W/I)?");
        switch(Poker21.sc.next().toLowerCase()){
            case "y":
                Poker21.start(player, banker);
                break;
            case "n":
                return ;
            case "w":
                checkWinrate(player,banker);
                break;
            case "i":
                checkInfo(player,banker);
                break;
        }
    }
    private static void checkInfo(People player, People banker) throws IOException {
        System.out.println("++++++++++++++++++"+"+++++++++++++++");
        System.out.println(String.format("你的钱：%.2f￥",player.money));
        System.out.println("++++++++++++++++++"+"+++++++++++++++");

        isRegameOrCheckInfo(player, banker);
    }

    private static void checkWinrate(People player, People banker) throws IOException {
        System.out.println("++++++++++++++++++"+"+++++++++++++++");
        System.out.println("你的胜场数："+player.wingamecounts+"场");
        System.out.println("游戏总场数："+player.allgamecounts+"场");
        System.out.println(String.format("你的胜率:%.2f%%",player.winrate));
        System.out.println("++++++++++++++++++"+"+++++++++++++++");

        isRegameOrCheckInfo(player, banker);
    }

    public static void isHityouOrDoubleOrStop(People player,People banker) throws IOException {
        if(player.getYourCards().size()==2){
            System.out.println("选择你的操作：\n【D】双倍下注【H】要牌【S】停牌");
            switch(Poker21.sc.next().toLowerCase()){
                case "d":
                    doubleBets(player, banker);
                    break;
                case "h":
                    hit(player,banker);
                    break;
                case "s":
                    stop(player,banker);
                    break;
    
            }
        }else{
            System.out.println("选择你的操作：\n【H】要牌【S】停牌");
            switch(Poker21.sc.next().toLowerCase()){
                case "h":
                    hit(player,banker);
                    break;
                case "s":
                    stop(player,banker);
                    break;
    
            }
        }
        
    }
    private static void stop(People player, People banker) throws IOException {
        //庄家把第一张牌翻开
        banker.getYourCards().get(0).showorback = 1;
        Monitor.drwaGame(player, banker);
        //计算结果
        while(calResult(player, banker)==1){
            //如果玩家胜，庄家就要要牌
            System.out.println("庄家要牌！");
            Card.giveCard(alreadygivepokers, banker);
            banker.getYourCards().get(banker.getYourCards().size()-1).showorback = 1;

            Monitor.drwaGame(player, banker);

            if(isBurst(banker) == 1){
                System.out.println("庄家爆了！");
                break;
            }
        }
        //结算结果
        settleResult(player, banker);
    }

    private static void hit(People player, People banker) throws IOException {
        Card.giveCard(alreadygivepokers, player);

        player.getYourCards().get(player.getYourCards().size()-1).showorback = 1;

        Monitor.drwaGame(player,banker);

        if(isBurst(player)==1){
            System.out.println("你爆了");
            settleResult(player, banker);
        }else{
            isHityouOrDoubleOrStop(player,banker);
        }
    }

    public static void doubleBets(People player,People banker) throws IOException{
        player.money -= player.yourbets;
        player.yourbets *= 2;

        Card.giveCard(alreadygivepokers, player);

        player.getYourCards().get(player.getYourCards().size()-1).showorback = 1;

        Monitor.drwaGame(player,banker);

        if(isBurst(player)==1){
            System.out.println("你爆了");
            settleResult(player, banker);
        }else{
            //庄家把第一张牌翻开
            banker.getYourCards().get(0).showorback = 1;
            Monitor.drwaGame(player, banker);
            //计算结果
            while(calResult(player, banker)==1){
                //如果玩家胜，庄家就要要牌
                System.out.println("庄家要牌！");
                Card.giveCard(alreadygivepokers, banker);
                banker.getYourCards().get(banker.getYourCards().size()-1).showorback = 1;

                Monitor.drwaGame(player, banker);

                if(isBurst(banker) == 1){
                    System.out.println("庄家爆了！");
                    break;
                }
            }
            //结算结果
            settleResult(player, banker);
        }
    }

    public static void settle21(People player,People banker) throws IOException{
        player.money+=(player.yourbets*3/2)+(player.yourbets);
        //你的注要清零
        player.yourbets = 0;
        //庄家和你手里的牌也要清空
        player.getYourCards().clear();
        banker.getYourCards().clear();

        //清空保险注
        player.insurerence = 0;
        banker.insurerence = 0;
        //计算胜场
        player.wingamecounts++;
        player.allgamecounts++;
        banker.allgamecounts++;
        banker.winrate = (1.0*banker.wingamecounts)/(banker.allgamecounts*1.0)*100;
        player.winrate = (1.0*player.wingamecounts)/(player.allgamecounts*1.0)*100;

        isRegameOrCheckInfo(player, banker);
    }
    public static void settleWinner(People winner,People loser){
        winner.money+=(winner.yourbets*2);
            loser.money-=winner.yourbets;
            //你的注要清零
            winner.yourbets = 0;
            //庄家和你手里的牌也要清空
            winner.getYourCards().clear();
            loser.getYourCards().clear();
            //清空保险注
            winner.insurerence = 0;
            loser.insurerence = 0;
            //计算胜场
            winner.wingamecounts++;
            winner.allgamecounts++;
            loser.allgamecounts++;
            loser.winrate = (1.0*loser.wingamecounts)/(1.0*loser.allgamecounts)*100;
            winner.winrate = (1.0*winner.wingamecounts)/(1.0*winner.allgamecounts)*100;
    }

    public static void settleDraw(People player,People banker){
        player.money+=player.yourbets;
        //底注清零
        player.yourbets = 0;

        //保险清零
        player.insurerence = 0;

        //庄家和你手里的牌也要清空
        player.getYourCards().clear();
        banker.getYourCards().clear();

        player.allgamecounts++;
        player.winrate = (1.0*player.wingamecounts)/(player.allgamecounts*1.0)*100;
    }

    public static void settleResult(People player,People banker) throws IOException{
        Monitor.drwaGame(player, banker);
        if(calResult(player, banker) == 1){
            System.out.println("玩家获胜");
            System.out.println("赢得了"+player.yourbets+"￥");
            settleWinner(player,banker);

        }else if(calResult(player, banker) == 2){
            System.out.println("庄家获胜");
            System.out.println("你输掉了"+player.yourbets+"￥");
            settleWinner(banker, player);
        }else{
            System.out.println("平局，将退回筹码");
            settleDraw(player, banker);
        }

        isRegameOrCheckInfo(player, banker);
    }

    public static int calResult(People player,People banker){
        //1玩家胜，2庄家胜，3平局
        if(player.yourpoint>21)
            //你爆了
            return 2;
        if(banker.yourpoint>21)
            //庄家爆了
            return 1;
        if(player.yourpoint> banker.yourpoint)
            return 1;
        else if(player.yourpoint<banker.yourpoint)
            return 2;
        else 
            return 3;
    }

    public static int isBurst(People player){
        if(player.yourpoint>21){
            return 1;
        }else
            return 0;
    }
}
