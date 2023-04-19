public class Account {
	private int AccountNumber;
	private String Password;
	private String Name;
	private Float Balance;

	//Constructor
	public Account(int AccountNumber, String Password, String Name, Float Balance){
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

	public Float getBalance() {
		return Balance;
	}

	public void setBalance(Float balance) {
		Balance = balance;
	}

	public String print(){
		return	"Account number: " + AccountNumber + " Name: " + Name + " Balance: $ " + Balance + "\n";
	}
}