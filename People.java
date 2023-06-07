import java.util.ArrayList;

public class People {
    String name;
    ArrayList<Poker> yourCards;
    int yourpoint = 0;
    double money;
    double yourbets;
    int insurerence;
    int wingamecounts;
    int allgamecounts;
    double winrate;
    public People() {
        this.name = "无名氏";
        this.yourCards = new ArrayList<>();
    }
    public People(String name, int wingamecounts, int allgamecounts, double winrate,double money) {
        this.name = name;
        this.yourCards = new ArrayList<>();
        this.wingamecounts = wingamecounts;
        this.allgamecounts = allgamecounts;
        this.winrate = winrate;
        this.money = money;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Poker> getYourCards() {
        return yourCards;
    }
    public void setYourCards(ArrayList<Poker> yourCards) {
        this.yourCards = yourCards;
    }
    public int getWingamecounts() {
        return wingamecounts;
    }
    public void setWingamecounts(int wingamecounts) {
        this.wingamecounts = wingamecounts;
    }
    public int getAllgamecounts() {
        return allgamecounts;
    }
    public void setAllgamecounts(int allgamecounts) {
        this.allgamecounts = allgamecounts;
    }
    public double getWinrate() {
        return winrate;
    }
    public void setWinrate(double winrate) {
        this.winrate = winrate;
    }
    public int getYourpoint() {
        return yourpoint;
    }
    public void setYourpoint(int yourpoint) {
        this.yourpoint = yourpoint;
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public double getYourbets() {
        return yourbets;
    }
    public void setYourbets(double yourbets) {
        this.yourbets = yourbets;
    }
    public int getInsurerence() {
        return insurerence;
    }
    public void setInsurerence(int insurerence) {
        this.insurerence = insurerence;
    }
    
    public void showyourcards(){
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < yourCards.size(); j++) {
                if(yourCards.get(j).showorback == 1)
                    System.out.print(yourCards.get(j).getImgcontent()[i]);
                else
                    System.out.print(yourCards.get(j).getImgback()[i]);
            }
            System.out.println();
        }
    }

    public void showyourmoney(){
        System.out.println(String.format("%.2f", money)+"￥");
    }
    public void showyourbets(){
        System.out.println(String.format("%.2f", yourbets)+"￥");
    }
    public void showyourpoint(){
        System.out.println(yourpoint);
    }
    public void calpoint(){
        //每次发牌都要点数清零重新算
        yourpoint = 0;
        //判断牌组中是否有A直接在算分的同时判断
        int flag = 0;
        for (int i = 0; i < getYourCards().size(); i++) {
            if(getYourCards().get(i).howmany == 0 
                && getYourCards().get(i).showorback == 1){
                flag++;
                continue;
            }
            //只计算show出来的牌值
            if(getYourCards().get(i).showorback == 1){
                if(getYourCards().get(i).howmany >= 9){
                    //如果是10，J，Q，K则当10
                    yourpoint += 10;
                }else{
                    yourpoint += (getYourCards().get(i).howmany+1);
                }
            }
            
        }

        //有一张A的情况
        if(flag == 1){
            //当11时爆了就当1，没爆就当11
            if(yourpoint+11 > 21)
                yourpoint += 1;
            else
                yourpoint += 11;
        }else if(flag == 2){
            //有两张A的情况，因为两张A都当11铁爆，所以还是只用考虑一张当11爆不爆的问题
            if(yourpoint+12 > 21)
                yourpoint += 2;
            else
                yourpoint += 12;
        }else if(flag == 3){
            if(yourpoint+13 > 21)
                yourpoint += 3;
            else
                yourpoint += 13;
        }else if(flag == 4){
            if(yourpoint+14 > 21)
                yourpoint += 4;
            else
                yourpoint += 14;
        }
        
    }
    
    
    
}
