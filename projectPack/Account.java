package projectPack;
public class Account {
	private int AccountNumber;
	private String Password;
	private String Name;
	private Double Balance;

	//Constructor
	public Account(int AccountNumber, String Password, String Name, Double Balance){
		this.AccountNumber = AccountNumber;
		this.Password = Password;
		this.Name = Name;
		this.Balance = Balance;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Double getBalance() {
		return Balance;
	}

	public void setBalance(Double balance) {
		Balance = balance;
	}

	public String print(){
		return	"Account number: " + AccountNumber + " Name: " + Name + " Balance: $ " + Balance + "\n";
	}
}