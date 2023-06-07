public class Random {
    public static int randomhowmany(){
        //返回0-12分别代表A,2,3...J,Q,K
        int randomseed = (int) (Math.random()*100%13);
        //System.out.println(randomseed);
        return randomseed;
    }

    public static int randomtype(){
        //返回0-3分别代表♠，♣，♦，♥
        int randomseed = (int) (Math.random()*100%4);
        return randomseed;
    }
}
