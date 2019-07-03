import java.util.*;
class limitlist{
    public static ArrayList<limits> limitslist = new ArrayList<>();

    public static void makelimit(player player, company company, double price_trade_at, String type, int num_of_shares){
        limitslist.add(new limits(player, company, price_trade_at, type, num_of_shares));
    }
    public static void limitsadd(limits limits){
        limitslist.add(limits);
    }
    public static void activate_all_limits(){
        for(int i = 0; i < limitslist.size(); i++){
            if(limitslist.get(i).company.stock_price == limitslist.get(i).price_trade_at ){
                if(limitslist.get(i).type.equals("buy"))
                    game.buyshares(limitslist.get(i).company,limitslist.get(i).player,limitslist.get(i).num_of_shares);
                else{
                    game.sellshares(limitslist.get(i).company,limitslist.get(i).player,limitslist.get(i).num_of_shares);
                }
            }

        }
    }
}
public class limits {
    public double price_trade_at;
    int num_of_shares;
    player player;
    company company;
    String type;
    public limits(player player, company company, double price_trade_at, String type, int num_of_shares){
        this.player = player;
        this.company = company;
        this.price_trade_at = price_trade_at;
        this.type = type;
        this.num_of_shares = num_of_shares;
    }
}
