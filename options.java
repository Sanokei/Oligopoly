public class options {
    int expire_in_num_days, num_of_shares;
    company company;
    player player;
    double price_paid,price_at_breakeven;
    public options(company company/*input from public_database*/, player player,int Days_Until_Expires, String type,double price_paid,double price_at_breakeven,int num_of_hundred_shares){
        this.company = company;
        this.player = player;
        this.expire_in_num_days = Days_Until_Expires;
        this.num_of_shares = num_of_hundred_shares;
        this.price_paid = price_paid;
        this.price_at_breakeven = price_at_breakeven;
        if(type == "put"){
            //if price goes lower
            if((price_paid * num_of_hundred_shares) == 1){

            }

        }
        if(type == "call"){
            //Higher
        }
    }
    public void check_expire(){

    }
}
