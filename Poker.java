public class Poker {
    int howmany;
    int type;
    int showorback;
    String[] imgcontent = new String[7];
    String[] imgback = new String[7];

    public Poker() {
    }
    public Poker(int howmany, int type) {
        this.howmany = howmany;
        this.type = type;
        drawCard();
        drawBack();
    }
    public int getHowmany() {
        return howmany;
    }
    public void setHowmany(int howmany) {
        this.howmany = howmany;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String[] getImgcontent() {
        return imgcontent;
    }
    public void setImgcontent(String[] imgcontent) {
        this.imgcontent = imgcontent;
    }
    public int getShoworback() {
        return showorback;
    }
    public void setShoworback(int showorback) {
        this.showorback = showorback;
    }
    public String[] getImgback() {
        return imgback;
    }
    public void setImgback(String[] imgback) {
        this.imgback = imgback;
    }


    public void drawBack(){
        imgback[0] = "┌─────┓";
        imgback[1] = "│"+"^^~^^"+"┃";
        imgback[2] = "│"+"^~~~^"+"┃";
        imgback[3] = "│"+"^^~^^"+"┃";
        imgback[4] = "│"+"^~~~^"+"┃";
        imgback[5] = "│"+"^^~^^"+"┃";
        imgback[6] = "└─────┛";
    }
    public void drawCard(){
        String point;
        String color;

        switch(howmany){
            case 0: 
                point = "A"; 
                break;
            case 10: 
                point = "J"; 
                break;
            case 11: 
                point = "Q"; 
                break;
            case 12: 
                point = "K"; 
                break;
            default: 
                point = (howmany+1)+"";
                break; 
        }
        switch (type) {
            case 0:
                color = "♠";
                break;
            case 1:
                color = "♣";
                break;
            case 2:
                color = "♦";
                break;
            default:
                color = "♥";
                break;
        }


        imgcontent[0] = "┌─────┓";
        if(point.equals("10"))
            imgcontent[1] = "│"+point+"   "+"┃";
        else
            imgcontent[1] = "│"+point+"    "+"┃";
        imgcontent[2] = "│"+color+"    "+"┃";
        imgcontent[3] = "│"+"     "+"┃";
        imgcontent[4] = "│"+"     "+"┃";
        imgcontent[5] = "│"+"     "+"┃";
        imgcontent[6] = "└─────┛";

    }
    
    
    
    
}
