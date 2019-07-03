import java.util.*;

//mail 
class mail{
	String subject,message;
	player receiver;
	contract contract;
	boolean read = false;
	public mail(contract contract){
		this.contract = contract;
	}
	public mail(String message,String subject, player receiver){
		this.message = message;
		this.subject = subject;
		this.receiver = receiver;
	}
}
//framework
class willmail{
	ArrayList<mail> WillMail = new ArrayList();
	//
	int INDEX;
	public willmail(int INDEX){
		this.INDEX = INDEX;
	}
	public ArrayList sendmail(int INDEXofRecipicant ,mail newMail, ArrayList mailingList){
		((willmail)mailingList.get(INDEXofRecipicant)).addmail(newMail);
		return mailingList;
	}
	public void addmail(mail incomingMail){
		WillMail.add(incomingMail);
	}
	public ArrayList sendmail(int INDEXofRecipicant ,int INDEXofContract, ArrayList contracts, ArrayList mailingList){
		//contract has all the information that mail has already, you dont need it twice
		mail NewMail = new mail((contract)contracts.get(INDEXofContract));
		((willmail)mailingList.get(INDEXofRecipicant)).addmail(NewMail);
		return mailingList;
	}
	public void printmail(willmail WillMail){
		int indexOfMail;
		int currentIndexOfMail;
		ArrayList<mail> YourMail = WillMail.WillMail;
		if(YourMail.size() > 0){
			do{
				System.out.println("\033[H\033[2J");
				System.out.println("All Mail: ");
				for(currentIndexOfMail = 0; currentIndexOfMail < YourMail.size(); currentIndexOfMail++){
						System.out.println((currentIndexOfMail + 1) + ": " + YourMail.get(currentIndexOfMail).subject);
					}
					System.out.println("0: Done");
				do{
					indexOfMail = new safeScanner().safenextInt("Mail to read: ");
				}while(indexOfMail < 0 || indexOfMail > YourMail.size() + 1);
				System.out.println(YourMail.get(currentIndexOfMail - 1).message);
				YourMail.get(currentIndexOfMail).read = true;
			}while(currentIndexOfMail != YourMail.size());
		}else{
			System.out.println("No Mail Found");
		}
	}

	public int amountOfMail(willmail WillMail){
		ArrayList<mail> YourMail = WillMail.WillMail;
		for(int i = 0; i < YourMail.size() && YourMail.get(i).read; i++){
				return i;	
		}
		return 0;
	}
}
