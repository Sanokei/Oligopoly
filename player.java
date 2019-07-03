import java.util.ArrayList;
class player extends game{
    public String player_name;
    public portfolio portfolio = new portfolio();
    public ArrayList<company> private_database = new ArrayList<company>();
    public double money = 100000;
    public player(){
        this.player_name = "Un-named";
        private_database.add(new company("Untitled"));
    }
    public player(String comp_name, String player_name){
        this.player_name = player_name;
        private_database.add(new company(comp_name));
    }

    public void portfolioset(company comp, int number_of_shares) {
        for(int INDEX = 0; INDEX < portfolio.size(); INDEX++){
            ((company)(portfolio.get(INDEX))).num_of_shares += number_of_shares;
        }
    }
	
    public void portfolioadd(company company, int num_of_shares) {
        portfolio.add(company, num_of_shares);
    }

    public boolean inportfolio(company comp) {
        for(int INDEX = 0; INDEX < portfolio.size(); INDEX++){
            if (portfolio.get(INDEX).equals(comp)){
                return true;
            }
        }
        return false;
    }
}