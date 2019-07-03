import java.util.ArrayList;

public class portfolio {
    public ArrayList<company> port = new ArrayList();

    public Object get(int INDEX){
        return  port.get(INDEX);
    }
    public int size(){
        return port.size();
    }
    public void add(company company,int num_of_shares) {
        if(port.indexOf(company) == -1)
            port.add(company);
        else{
            buy(company,num_of_shares);
        }

    }
    public void sell(company company, int no_shares){
        port.get(port.indexOf(company)).num_of_shares -= no_shares;
    }
    public void buy(company company, int no_shares){
        port.get(port.indexOf(company)).num_of_shares += no_shares;
    }
    public void getport(company comp){
        try {
            int INDEX_in_port = port.indexOf(comp);
            System.out.println("Company Name: " + (port.get(INDEX_in_port).company_name));
            System.out.println("How Much You Have: " + (port.get(INDEX_in_port).stock_price * port.get(INDEX_in_port).num_of_shares));
            System.out.println("How Many Shares You Have: " + (port.get(INDEX_in_port).num_of_shares));
        } catch (Exception e) {
            System.out.println("Something Went Wrong! Portfolio Does Not Exist.");
        }
    }
}
