import java.util.*;
class service{
	//name, development cost + overhead, selling price
	private ArrayList<String> service_name = new ArrayList<String>();
	private ArrayList<Double> dev_cost = new ArrayList<Double>();
	private ArrayList<Double> selling_cost = new ArrayList<Double>();
	public ArrayList<ArrayList> service = new ArrayList<ArrayList>();
	public service(){

	}
	public service(String service_name, double dev_cost, double selling_cost){
		this.service_name.add(service_name);
		this.dev_cost.add(dev_cost);
		this.selling_cost.add(selling_cost);
		//name, development cost + overhead, selling price
		service.add(this.service_name);
		service.add(this.dev_cost);
		service.add(this.selling_cost);
	}
	public void addservice(String service_name, double dev_cost, double selling_cost){
		this.service_name.add(service_name);
		this.dev_cost.add(dev_cost);
		this.selling_cost.add(selling_cost);
		//name, development cost + overhead, selling price
		service.add(this.service_name);
		service.add(this.dev_cost);
		service.add(this.selling_cost);
	}
}
class product{
	//name, how many of that items you have, development cost + overhead, How much it costs to make, how much to sell it for
	private ArrayList<String> product_name = new ArrayList();
	private ArrayList<Integer> amount_of_prod = new ArrayList();
	private ArrayList<Double> dev_cost, prod_cost, selling_cost;
	public ArrayList<ArrayList> product = new ArrayList<>();
	public product(){

	}
	public product(String service_name, int amount_of_prod, double dev_cost,double prod_cost,double selling_cost){
		this.product_name.add(service_name);
		this.amount_of_prod.add(amount_of_prod);
		this.dev_cost.add(dev_cost);
		this.prod_cost.add(prod_cost);
		this.selling_cost.add(selling_cost);
		product.add(this.product_name);
		product.add(this.amount_of_prod);
		product.add(this.dev_cost);
		product.add(this.prod_cost);
		product.add(this.selling_cost);
	}
	public void addproduct(String product_name, int amount_of_prod, double dev_cost,double prod_cost,double selling_cost){
		this.product_name.add(product_name);
		this.amount_of_prod.add(amount_of_prod);
		this.dev_cost.add(dev_cost);
		this.prod_cost.add(prod_cost);
		this.selling_cost.add(selling_cost);
		product.add(this.product_name);
		product.add(this.amount_of_prod);
		product.add(this.dev_cost);
		product.add(this.prod_cost);
		product.add(this.selling_cost);
	}

}
class company{
	public String company_name;
	public service service = new service();
	public product product = new product();
	public double comp_money, stock_price;
	public int num_of_shares;
	public boolean Public = false;
	
	public company(){
		company_name = "Untitled";
		comp_money = 100000f;
		num_of_shares = 10000000;
		stock_price = comp_money / num_of_shares;
	}

	public company(String name){
		this.company_name = name;
		comp_money = 100000f;
		num_of_shares = 10000000;
		stock_price = comp_money / num_of_shares;
	}

	public void setproduct(String Name_of_product, int No_of_items, double Dev_cost_overhead, double Product_price, double Selling_price){
		product.addproduct(Name_of_product, No_of_items, Dev_cost_overhead, Product_price, Selling_price);
	}
	public void setservice(String Name_of_service, Double Dev_cost_overhead, Double Selling_price){
		service.addservice(Name_of_service, Dev_cost_overhead, Selling_price);
	}
}