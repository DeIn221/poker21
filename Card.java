import java.util.ArrayList;

public class Card {
    static int howmany;
    static int whattype;
    
    private static void generateCard(){
        howmany = Random.randomhowmany();
        whattype = Random.randomtype();
    }
    


    public static void giveCard(ArrayList<Poker> alreadygivepokers, People player){
        
        //抽一张
        generateCard();

        int flag;
        //如果已经发过牌，则查一下有没有发过这张
        while(alreadygivepokers.size() != 0){
            flag = 0;
            for (int i = 0; i < alreadygivepokers.size(); i++) {
                if (howmany == alreadygivepokers.get(i).howmany &&
                    whattype == alreadygivepokers.get(i).type){
                    // 如果发过，flag置为1,跳出循环
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                //全部查一遍，如果flag为1，则重新抽一张，再查一遍
                generateCard();
            }else{
                //全部查一遍，如果flag为0，则完成一次发牌，将发出的牌加入已发出的数组，出循环
                player.getYourCards().add(new Poker(howmany, whattype));
                alreadygivepokers.add(new Poker(howmany, whattype));
                break;
            }
            
        }
        //如果是因为已发牌数组为空出的循环，那么就直接发牌
        if (alreadygivepokers.size() == 0){
            player.getYourCards().add(new Poker(howmany, whattype));
            alreadygivepokers.add(new Poker(howmany, whattype));
        }
        
    }


}
