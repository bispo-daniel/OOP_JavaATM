public class Account {
	private int AccountNumber;
	private String Name;
	private Float Balance;

	//Constructor
	public Account(int AccountNumber, String Name, Float Balance){
		this.AccountNumber = AccountNumber;
		this.Name = Name;
		this.Balance = Balance;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
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
		return	"Conta: " + AccountNumber + " Titular: " + Name + " Saldo:  R$ " + Balance + "\n";
	}
}