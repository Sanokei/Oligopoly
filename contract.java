class contract{
	String terms = "";
	boolean terms_met = false;
	boolean terms_broken = false;
	player sender, reciever;
	boolean reciever_confirmation = false;
	double paying_amount_if_met = 0;
	double paying_amount_if_broken = 0;
	int num_of_days = 0;
	public contract(player sender, player reciever, double paying_amount_if_met, double paying_amount_if_broken, int num_of_days){
		this.sender = sender;
		this.reciever = reciever;
		this.paying_amount_if_broken = paying_amount_if_broken;
		this.paying_amount_if_met = paying_amount_if_met;
		this.num_of_days = num_of_days;
	}
	public void contract_met(boolean terms_met){
		if(reciever_confirmation){
			this.terms_met = terms_met;
				if(terms_met){
					reciever.money += paying_amount_if_met;
					sender.money -= paying_amount_if_met;
				}
		}else{
			System.out.println("The other player has not responded");
		}
	}
	public void contract_broken(boolean terms_broken){
		if(reciever_confirmation){
			this.terms_broken = terms_broken;
				if(terms_broken){
					reciever.money -= paying_amount_if_met;
					sender.money += paying_amount_if_met;
				}
		}else{
			System.out.println("The other player has not responded");
		}	
	}

}