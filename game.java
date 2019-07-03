import java.util.*;
class game{
	public String company_name,player_name,buyorsell,putcall,SoO,stock_name;
	public int num_of_player,choice;
	public player[] players;
	public product product;
	public limitlist limitlists = new limitlist();
	public Scanner input = new Scanner(System.in);
	public safeScanner safeinput = new safeScanner();
	public ArrayList<options> option = new ArrayList<>();
	public static ArrayList<ArrayList> public_database = new ArrayList<ArrayList>();
	public ArrayList<contract> contracts = new ArrayList();
	public ArrayList<willmail> WillMail = new ArrayList();
	public String days[] = {"Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday","Sunday"};

	public void play(){
		Random random = new Random();
		System.out.print("\033[H\033[2J");
		boolean num_player_is_valid = false;
		do{
			num_of_player = safeinput.safenextInt("Number of Players (At least 3): ");
		}while(num_of_player < 3);
		players = new player[num_of_player];
		for(int INDEX = 0; INDEX < num_of_player; INDEX++){
			do{
				System.out.println("Player " + (INDEX + 1));
				System.out.print("Player Name: "); 
				player_name = input.nextLine();
				System.out.print("Company Name: ");
				company_name = input.nextLine();
				//Test if they actually input a name.
				//no you do it! 
				//Day 12: I am going insane. I am talking to comments I made in the past
			}while(company_name_test(company_name) || player_name_test(players, player_name, INDEX));
			players[INDEX] = new player(company_name,player_name);
			WillMail.add(new willmail(INDEX));
            public_database.add(players[INDEX].private_database);
            //out of bounds [FIXED] i think. . . \/
			//public_database.get(INDEX).add(players[INDEX].private_database);
            ((company)(public_database.get(INDEX)/*<-private:Company->*/.get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name))))).num_of_shares = 1000000;
		}
		boolean win = false;
		int day = 1;
		while(!win){
			for (int INDEX = 0; INDEX < num_of_player; INDEX++) {
				System.out.println("\033[H\033[2J");
				boolean EndTurn;
				do {//turn
					//check the limit buy orders [DONE] <3
					//count the number of turns every 5th day is a friday the NYSE is closed for the weekend [DONE] you loon
					
					//Activate stuff
					limitlists.activate_all_limits();


					EndTurn = false;
					if(day % 6 != 0 || day % 7 != 0) {
						do {
							System.out.println("\nIt is " + players[INDEX].player_name + " turn.");
							System.out.println("You Currently Have: $" + players[INDEX].money);
							System.out.println("It is " + days[(day % 7) - 1] == "Wednesday" ?  "" + days[(day % 7) - 1]+" my dude": "It is " + days[(day % 7) - 1]);
							System.out.println("\n");
							/*xd if you get the memes i mean who reads comments anyway? pft i know i dont*/
							//DONE: 0,1,2,3,4,5,,7,8,
							//WIP: 6,9
							//\/ 9
							//NEW: news[HIDDEN],check news, bank, random NPC buys[HIDDEN]
							choice = safeinput.safenextInt("0) End Turn\n1) Buy/Sell Stock of Company\n2) Create New Contract($1000 Filling fee)\n3) Invest in New Product/Service($10,000~)\n4) Invest in New Company($100,000)\n5)Bring company public($20,000)\n6)Check Will News\n7)Check Will-Mail("+WillMail.get(INDEX).amountOfMail(WillMail.get(INDEX))+")\n8)Send Will-Mail\n9)Bank of Will\n");
						} while (choice < 0 || choice > 9);
					}else{
						do {
							System.out.println("Since it is a weekend the stock market is closed. The bank is closed");
							choice = safeinput.safenextInt("0) End Turn 1)STOCK MARKET CLOSED\n2) Create New Contract($1000 Filling fee)\n3) Invest in New Item($10,000~)\n4) Invest in New Company($100,000)\n5)Bring company public($20,000)\n6)Check Will News\n7)Check Will-Mail\n8)Send Will-Mail\n9)Bank of Will\n");
						} while(choice == 1 || ((choice < 0 || choice > 9) && choice != 1));
					}

					switch (choice) {
						case 0:
							EndTurn = true;
							break;
						case 1:
							//make sure you make his not ! after development
							if(atLeastOneCompNotYoursPublic(INDEX)) {
								do {
									System.out.println("Buy or Sell: ");
									buyorsell = (input.next()).toLowerCase();
								} while (!buyorsell.equals("buy") && !buyorsell.equals("sell"));

								System.out.println("Companies Stock's: ");
								printallcompinfoexceptown(INDEX);
								//fix the input handling (Sounds like work tho)
								//simple fix \/ (not as bad as i thought)
								input.nextLine();
								do {
									System.out.println("Stock or Option: ");
									SoO = (input.nextLine()).toLowerCase();
								} while (!SoO.equals("option") && !SoO.equals("stock"));

								//Buy Stock of Company
								if (SoO.equals("stock")) {
									stock_name = "null";
									do {
										System.out.println("Enter Company Name: ");
										stock_name = (input.nextLine()).toLowerCase();
									} while (returncompanyfromname(stock_name) == null || companyFromNameIsPublic(stock_name));
									String buying = "null";

									//hey dummy returncompanyfromname() uses the public_database so you so you dont have to worry about
									//making sure it is updated
                                    if(buyorsell.equals("buy")) {
                                        do {
                                            System.out.println("Limit Buy or Buy: ");
                                            buying = (input.nextLine()).toLowerCase();
                                        } while (!buying.equals("limit buy") && !buying.equals("buy"));
                                        int no_shares = 0;
                                        do {
                                            no_shares = safeinput.safenextInt("How Many Shares: ");
                                        } while (no_shares <= 0 || no_shares > returncompanyfromname(stock_name).num_of_shares);
                                        if (buying.equals("buy")) {
                                            buyshares(returncompanyfromname(stock_name), players[INDEX], no_shares);
                                        } else {//buy or limit buy (limit buy)
                                            double potential_price = 0f;
                                            do {
                                                potential_price = safeinput.safenextDouble("At what potential price: ");
                                            } while (potential_price <= 0.0);
                                            limitlist.makelimit(players[INDEX],returncompanyfromname(stock_name),potential_price,buyorsell,no_shares);
                                        }
                                    }else{//sell
                                        do {
                                            System.out.println("Limit Sell or Sell: ");
                                            buying = (input.nextLine()).toLowerCase();
                                        } while (!buying.equals("limit sell") && !buying.equals("sell"));
                                        int no_shares = 0;
                                        do {
                                            no_shares = safeinput.safenextInt("How Many Shares: ");
                                        } while (no_shares <= 0 || no_shares > returncompanyfromname(stock_name).num_of_shares);
                                        if (buying.equals("sell")) {
                                            sellshares(returncompanyfromname(stock_name), players[INDEX], no_shares);
                                        } else {//buy or limit sell (limit sell)
                                            double potential_price = 0f;
                                            do {
                                                potential_price = safeinput.safenextDouble("At what potential price: ");
                                            } while (potential_price <= 0.0);
                                            limitlist.makelimit(players[INDEX],returncompanyfromname(stock_name),potential_price,buyorsell,no_shares);
                                        }
                                    }
                                }else{
									System.out.println("Options is currently broken and will be fixed in the next update. Also Fuck options god the amount of math required baffles the best of us");
								}
                            }else{
                                System.out.println("\nNo Company that is not yours is public\n");
                            }
							/*
                            else {//stock or options (options)
                                do {
                                    System.out.println("Put or Call: ");
                                    putcall = (input.nextLine()).toLowerCase();
                                } while (!putcall.equals("call") && !putcall.equals("put"));
                                printallcompinfoexceptown(INDEX);
                                stock_name = "null";
                                do {
                                    System.out.println("Enter Company Name: ");
                                    stock_name = (input.nextLine()).toLowerCase();
                                } while (returncompanyfromname(stock_name) == null || companyFromNameIsPublic(stock_name));
                                printcompinfo(returncompanyfromname(stock_name));
                                int days_until_expire = 0, num_of_hun_shares = 0;
                                double price_to_breakeven, price_of_option;
                                String agree = "null";
                                //number of days, the price at which to breakeven; the number of shares, EULA
                                do {//eula
                                    do {//num of days
                                        days_until_expire = safeinput.safenextInt("How many days until the option expires(The longer the period of time the more it costs): ");
                                    } while (days_until_expire < 1);
                                    do {//price to breakeven
                                        options.options_of_comp(days_until_expire, returncompanyfromname(stock_name), players[INDEX], putcall);
                                        price_to_breakeven = safeinput.safenextDouble("Price to BreakEven closer to the current share price will cost more money: ");
                                    } while (price_to_breakeven < 1 || price_to_breakeven > 11);
                                    do {//number of shares

                                        System.out.println("Remember: The price will be 100x since you have to buy/sell at least in 100 share blocks.");
                                        num_of_hun_shares = safeinput.safenextInt("How many hundreds of shares would you like to " + buyorsell + ": ");
                                    } while (players[INDEX].money >= (num_of_hun_shares * ((company) public_database.get(INDEX).get((public_database.get(INDEX).indexOf(returncompanyfromname(stock_name))))).stock_price)//Check if the player has enough money);
                                    System.out.println("By saying \"Yes\" you agree to buying " + (num_of_hun_shares * 100) + " shares of " + (returncompanyfromname(stock_name).company_name) + " for " + ((num_of_hun_shares * 100)));
                                } while (!agree.equals("yes"));
                                //company companyinput from public_database, player player,int Days_Until_Expires, String type,//double price_paid,double price_at_breakeven,int num_of_hundred_shares

                                //option.add(new options(returncompanyfromname(stock_name),players[INDEX],days_until_expire, putcall, ));
                            }
                            */
							break;
						case 2:
							if(players[INDEX].money >= 1000){
								System.out.println("Create a Contract with a player. Ya this is a bit primitive. Shut up. It is the honor system.");
								//player sender, player reciever, double paying_amount_if_met, double paying_amount_if_broken, int num_of_days
								player reciever = choosePlayer(INDEX);
								double paying_amount_if_met = 0f, paying_amount_if_broken = 0f;
								int NOD = 0;
								do{
									paying_amount_if_met = safeinput.safenextDouble("Enter The Amount owed if contract is met: ");
								}while(paying_amount_if_met <= 0);
								do{
									paying_amount_if_broken = safeinput.safenextDouble("Enter The Amount owed if contract is broken: ");
								}while(paying_amount_if_broken <= 0);
								do{
									NOD = safeinput.safenextInt("Enter the number of days for the contract to expire: ");
								}while(NOD <= 0);
								contracts.add(new contract(players[INDEX], reciever, paying_amount_if_met,paying_amount_if_broken,NOD));
								
								System.out.println("The contract has been sent via WillMail. There is a $1000 filing fee.");
								WillMail = WillMail.get(INDEX).sendmail(INDEX, contracts.size() - 1,contracts, WillMail);
								players[INDEX].money -= 1000;
							}else{System.out.println("You cannot afford the filing fee");}
							break;
						case 3:
						//Invest in new product
							do{
								num_player_is_valid = true;
								printAllYourCompanyInfo(num_of_player);
								do {
									System.out.println("Which Company: ");
									company_name = input.nextLine();
								} while (returncompanyfromname(company_name) == null);
								players[INDEX].money -= 20000;
								company comp = ((company) (public_database.get(INDEX).get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name)))));
								
								try {
									choice = safeinput.safenextInt("1) Create a new Product\n2) Create a new service\n");
								} 
								catch (NumberFormatException e) {
									System.out.println("That is not a number. Please input the number of players\n");
									num_player_is_valid = false;
								}
							}while(!num_player_is_valid || choice < 1 || choice > 2);
							if(((company) (public_database.get(INDEX).get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name))))).comp_money > 1101){
								switch(choice){
									case 1: 
										System.out.print("Name your product: "); 
										String product_name = input.nextLine(); 
										int amount_of_prod;
										do{
											amount_of_prod = safeinput.safenextInt("Amount of product: ");
										}while(amount_of_prod <=0); 
										//devcost is random and so is the production cost.
										double dev_cost = (random.nextDouble() * 1000) + 1001;
										double prod_cost = (random.nextDouble() * 100) + 101;
										double selling_cost;
										do{
											selling_cost = safeinput.safenextDouble("Selling Price of Product: ");
										}while(selling_cost <= 0);
										((company) (public_database.get(INDEX).get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name))))).setproduct(product_name, amount_of_prod, dev_cost, prod_cost, selling_cost);
										((company) (public_database.get(INDEX).get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name))))).comp_money -= (dev_cost + (prod_cost * amount_of_prod));
									break;
									
									case 2: 
										System.out.print("Name your service: "); 
										
										String service_name = input.nextLine();
									
										dev_cost = (random.nextDouble() * 10000) + 10001;
				
										do{
											selling_cost = safeinput.safenextDouble("Selling Price of Product: ");
										}while(selling_cost <= 0);
										((company) (public_database.get(INDEX).get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name))))).setservice(service_name, dev_cost, selling_cost);
										((company) (public_database.get(INDEX).get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name))))).comp_money -= dev_cost;
									break;

									default:
										System.out.print("Wtf how did you get to this . . . please send bug report via comments.");
									}
							}else{
								System.out.println("Looks like your too low on funds. ");
							}
						break;
						case 4:
							String comp_name;
							if(players[INDEX].money >= 100000f){
								do{
									System.out.println("Enter Company Name: ");
									comp_name = input.nextLine();
								}while(companyFromNameIsPublic(comp_name));
								public_database.get(INDEX).add(new company(comp_name));
								players[INDEX].money -= 100000;
							}else{
								System.out.println("Go make more money first. Pft the finacial market cant hear broke");
							}
							break;
						case 5:
							if(players[INDEX].money >= 20000) {
								if (!atLeastOnePlayerCompPublic(INDEX)) {
									printAllYourCompanyInfo(INDEX);
									String company_name = "null";
									do {
										System.out.println("Which Company: ");
										company_name = input.nextLine();
									} while (returncompanyfromname(company_name) == null);
									players[INDEX].money -= 20000;
									((company) (public_database.get(INDEX)/*<-private:Company->*/.get(public_database.get(INDEX).indexOf(returncompanyfromname(company_name))))).Public = true;
								} else {
									System.out.println("No Company of your\'s is not public");

								}
							}else{
								System.out.println("You do not have enough money");
								}
						break;
						case 6:
							System.out.print("Work in Progress");
						break;
						case 7:
							WillMail.get(INDEX).printmail(WillMail.get(INDEX));
						break;
						case 8:
							for(int indexofreviever = 0; indexofreviever < players.length; indexofreviever++){
								if(indexofreviever != INDEX)
									System.out.println((indexofreviever + 1) + ": " + players[indexofreviever].player_name);
							}
							int indexofreviever;
							do{
								indexofreviever = safeinput.safenextInt("Which Player?: ");
							}while(indexofreviever <= 0 || indexofreviever > players.length);
							System.out.print("Subject: ");
							String subject = input.nextLine();
							System.out.print("Message: ");
							String message = input.nextLine();
							mail newMail = new mail(message, subject, players[INDEX]);
							WillMail = WillMail.get(INDEX).sendmail(indexofreviever, newMail, WillMail);
							System.out.println(WillMail.size());
						break;
						case 9:
							System.out.print("Work in Progress");
						break;
						default:
							System.out.println("Oops looks like you found a bug!");
					}//switch ends
				} while (!EndTurn);
			}
			day++;
		}while(!win);
	}
	public int IndexOf(player player){
		for(int i = 0; i < num_of_player; i++)
			if(players[i] == player)
				return i;

		System.out.println("Can't Find you in the system. . . Weird, defaulting to 0");
		return 0;
	}
	private player choosePlayer(int INDEX){
		int player_index = 0;
		for(int i = 0; i < players.length; i++){
			if(i != INDEX){
				System.out.println((i + 1) + ": " + players[i].player_name);
			}
		}
		do{
			player_index = safeinput.safenextInt("Which player: ");
		}while(player_index - 1 < 0 || player_index == INDEX);

		return players[player_index - 1];
	}
	private boolean atLeastOnePlayerCompPublic(int INDEX){
		for(int prv_db = 0; prv_db < public_database.get(INDEX).size(); prv_db++)
			if (((company) (public_database.get(INDEX).get(prv_db))).Public) {
				return true;
			}
		return false;
	}
	private boolean atLeastOneCompNotYoursPublic(int INDEX){
		for(int pub_db = 0; pub_db < public_database.size(); pub_db++){
			for(int prv_db = 0; prv_db < public_database.get(pub_db).size(); prv_db++){
				if(((company)(public_database.get(pub_db).get(prv_db))).Public && pub_db != INDEX){
					return true;
				}

			}
		}
		return false;
	}

	private boolean companyFromNameIsPublic(String company_name) {
		for(int pub_db = 0; pub_db < public_database.size(); pub_db++){
			for(int prv_db = 0; prv_db < public_database.get(pub_db).size(); prv_db++){
				if(((company)(public_database.get(pub_db).get(prv_db))).company_name.equals(company_name) && !((company)(public_database.get(pub_db).get(prv_db))).Public ){
					System.out.println("Company is not Public");
					return true;
				}

			}
		}
		return false;
	}
	private void printAllYourCompanyInfo(int num_ply){
			for(int priv = 0; priv < public_database.get(num_ply).size(); priv++){
				company tempcomp = (company)(public_database.get(num_ply)).get(priv);
					try {
						System.out.println("Company Name: " + tempcomp.company_name);
						System.out.println("Company Share Price: " + (tempcomp.Public ? tempcomp.stock_price : "Stock is not Public"));
						System.out.println("Company Shares: " + tempcomp.num_of_shares);
						System.out.println("Company is Public: " + (tempcomp.Public ? "Yes" : "No"));
						System.out.println("\n");
					}catch (Exception e){
						System.out.println("Portfolio Not Found");
					}
		}
	}
	private void printallcompinfoexceptown(int player_index){
		for(int num_ply = 0; num_ply < public_database.size(); num_ply++){
			for(int priv = 0; priv < public_database.get(num_ply).size(); priv++){
				company tempcomp = (company)(public_database.get(num_ply)).get(priv);
				if(player_index != num_ply)
					try {
						System.out.println("Company Name: " + tempcomp.company_name);
						System.out.println("Company Share Price: " + (tempcomp.Public ? tempcomp.stock_price : "Stock is not Public"));
						System.out.println("Company Shares: " + tempcomp.num_of_shares);
						System.out.println("Company is Public: " + (tempcomp.Public ? "Yes" : "No"));
						System.out.println("\n");
					}catch (Exception e){
						System.out.println("Portfolio Not Found");
					}

			}
		}
	}

	private void printcompinfo(company company){
		try {
			System.out.println("Company Name: " + company.company_name);
			System.out.println("Company Share Price: " + (company.Public ? company.stock_price : "Stock is not Public"));
			System.out.println("Company Shares: " + company.num_of_shares);
			System.out.println("Company is Public: " + (company.Public ? "Yes" : "No"));
			System.out.println("\n");
		}catch (Exception e){
			System.out.println("ERROR: Company Not Found, ");
		}
	}
	public static void sellshares(company comp, player plyr, int num_of_shares) {
		company tempcomp = comp;
		tempcomp.num_of_shares += num_of_shares;
		tempcomp.comp_money -= num_of_shares * tempcomp.stock_price;
		plyr.money += num_of_shares * tempcomp.stock_price;
		((company) public_database.get(public_database.indexOf(plyr.private_database)).get((public_database.get(public_database.indexOf(plyr.private_database))).indexOf(comp))).num_of_shares = tempcomp.num_of_shares;
		((company) public_database.get(public_database.indexOf(plyr.private_database)).get((public_database.get(public_database.indexOf(plyr.private_database))).indexOf(comp))).comp_money = tempcomp.comp_money;
	}

	public static void buyshares(company comp, player plyr,int num_of_shares) {
		if (plyr.money >= (num_of_shares * comp.stock_price)){//player has enough money
			if (plyr.inportfolio(comp)) {
				plyr.portfolioset(comp, num_of_shares);
			} else {
				plyr.portfolioadd(comp,num_of_shares);
			}
			//adding and subtracting from public database
			company tempcomp = comp;
			tempcomp.num_of_shares -= num_of_shares;
			tempcomp.comp_money += num_of_shares * tempcomp.stock_price;
			plyr.money -= num_of_shares * tempcomp.stock_price;
			((company) public_database.get(public_database.indexOf(plyr.private_database)).get((public_database.get(public_database.indexOf(plyr.private_database))).indexOf(comp))).num_of_shares = tempcomp.num_of_shares;
			((company) public_database.get(public_database.indexOf(plyr.private_database)).get((public_database.get(public_database.indexOf(plyr.private_database))).indexOf(comp))).comp_money = tempcomp.comp_money;
		}else{
			System.out.println("You don't have enough money to make this transaction");
		}
	}

	private company returncompanyfromname(String company_name) {
		for(int pub_db = 0; pub_db < public_database.size(); pub_db++){
			for(int prv_db = 0; prv_db < public_database.get(pub_db).size(); prv_db++){
				if(((company)(public_database.get(pub_db).get(prv_db))).company_name.equals(company_name)){
					return ((company)(public_database.get(pub_db).get(prv_db)));
				}

			}
		}
		return null;
	}

	public boolean company_name_test(String company_name){
		for(int perplayer = 0; perplayer < public_database.size();perplayer++){
			for(int percomp = 0; percomp < public_database.get(perplayer).size(); percomp++) {
				if (company_name.equals(((company)public_database.get(perplayer).get(percomp)).company_name)){
					System.out.println("That company name is already taken.");
					return true;
				}
			}
		}
		return false;
	}
	public boolean player_name_test(player[] plys, String ply_name, int curr_num_plys){
		for(int INDEX = 0; INDEX < curr_num_plys; INDEX++){
			if(ply_name.equals(plys[INDEX].player_name)){
				System.out.println("That player name is already taken.");
				return true;
			}
		}
		return false;
	}

}
//System.out.println("Company Name: "+company_database.get(INDEX).company_name + "\nCompany Stock Price: " + company_database.get(INDEX).stock_price + "\nCompany Market Cap: " + (company_database.get(INDEX).stock_price * company_database.get(INDEX).num_of_shares) +"\nNumber of public shares: "+(company_database.get(INDEX).Public ? 0 : company_database.get(INDEX).num_of_shares)+"\n");

