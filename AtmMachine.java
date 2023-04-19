import javax.swing.*;
import java.util.ArrayList;

public class AtmMachine {
	static ArrayList<Account> accountsList = new ArrayList<Account>();

	//Function to create a new account
	private static void create() {
		String accountNumberString = JOptionPane.showInputDialog("Type the account number");
		int accountNumber = Integer.parseInt(accountNumberString);

 		String name = JOptionPane.showInputDialog("Type your name");

		String accountBalanceString = JOptionPane.showInputDialog("Type the initial balance");
		Float accountBalance = Float.parseFloat(accountBalanceString);

		Account acc = new Account(accountNumber, name, accountBalance);
		accountsList.add(acc);
		
		JOptionPane.showMessageDialog(null, "Account successfully created!");
 		ATM();
 	}

	static String listAllAccounts = "";

	//Function to display all accounts 
 	private static void read() {
		listAllAccounts = "";

 		for (Account acc : accountsList) {
			listAllAccounts += acc.print();
		}
		
		JOptionPane.showMessageDialog(null, listAllAccounts);
 		ATM();
 	}

	//Function to update an account 
 	private static void update() {
		String aux01 = JOptionPane.showInputDialog("Which account do you want to update?");
		int accountNumber = Integer.parseInt(aux01);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String aux02 = JOptionPane
						.showInputDialog("\nWhat do you want to update? \n1) Account number \n2) Name \n3) Balance");
				int op = Integer.parseInt(aux02);

				switch (op) {
					case 1:
						acc.setAccountNumber(Integer.parseInt(JOptionPane.showInputDialog("Type the new account number")));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
						break;
					case 2:
						acc.setName(JOptionPane.showInputDialog("Type the new name"));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
						break;
					case 3:
						acc.setBalance(Float.parseFloat(JOptionPane.showInputDialog("Type the new balance")));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
						break;
					default:
						JOptionPane.showMessageDialog(null, "Type a valid option...");
						update();
				}
			}
		}

 		ATM();
 	}
 	
	//Function to delete an account
 	private static void delete() {
		String auxiliar = JOptionPane.showInputDialog("Type the account number that you want to delete");
		int accountNumber = Integer.parseInt(auxiliar);
		
		for(Account account : accountsList){
			if(account.getAccountNumber() == accountNumber){
				accountsList.remove(account);

				JOptionPane.showMessageDialog(null, "Account successfully removed!");

				//Break used to exit for loop and avoid an error
				break;
			}
		}
		
		ATM();
	}

	//Function to get the highest balance among all the accounts
 	private static float max() {
 		float max = accountsList.get(0).getBalance();

 		for (Account acc : accountsList) {
			if (acc.getBalance() > max) {
				max = acc.getBalance();
			}
 		}

 		return max;
 	}

	//function to get the lowest balance among all the accounts
 	private static float min() {
 		float min = accountsList.get(0).getBalance();

 		for (Account acc : accountsList) {
			if (acc.getBalance() < min) {
				min = acc.getBalance();
			}
 		}

 		return min;
 	}

	//Function to get the average balance of all accounts 
 	private static float average() {
 		float add = 0;
		int divider = 0;

 		for (Account acc : accountsList) {
			divider++;
			add += acc.getBalance();
 		}

 		float average = add / divider;
 		return average;
 	}

	//Function to display the 3 previous functions
	 private static void statistics() {

		float max = max();
		float min = min();
		float average = average();

		JOptionPane.showMessageDialog(null,
				"Highest balance: $ " + max + "\nLowest balance: $ " + min + "\nBalance average: $ " + average);

		ATM();
	}

	//Function to take a bank statement
 	private static void statement() {
		String accountNumberString = JOptionPane.showInputDialog("\nFrom which account do you want a statement?");
		int accountNumber = Integer.parseInt(accountNumberString);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				JOptionPane.showMessageDialog(null, "\nAvailable balance: $ " + acc.getBalance());
			}
		}

 		ATM();
 	}

	//Function to make a deposit
 	private static void deposit() {
		String accountNumberString = JOptionPane.showInputDialog("\nIn which account do you want to deposit?");
		int accountNumber = Integer.parseInt(accountNumberString);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String aux02 = JOptionPane.showInputDialog("\nType the deposit value");
				Float depositValue = Float.parseFloat(aux02);		

				float novoSaldo = acc.getBalance() + depositValue;
				acc.setBalance(novoSaldo);

				JOptionPane.showMessageDialog(null, "Deposit successfully made!");
			}
		}

 		ATM();
 	}

	//Function to withdraw money from one account
 	private static void withdraw() {
 		String accountNumberString = JOptionPane.showInputDialog("From which account do you want to withdraw?");
		int accountNumber = Integer.parseInt(accountNumberString);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String withdrawValueString = JOptionPane.showInputDialog("Type the withdraw value");
				Float withdrawValue = Float.parseFloat(withdrawValueString);

				if (withdrawValue > acc.getBalance()) {
					JOptionPane.showMessageDialog(null,	"Error!\nWithdraw value greater than balance.");
				} else {
					float newBalance = acc.getBalance() - withdrawValue;
					acc.setBalance(newBalance);
					JOptionPane.showMessageDialog(null, "Withdraw successfully made!");
				}
			}
		}

 		ATM();
 	}

	//Function to transfer money from one account to another
 	private static void transfer() {
		String originAccountNumberString = JOptionPane.showInputDialog("From which account do you want to transfer?");
		int originAccountNumber = Integer.parseInt(originAccountNumberString);

		String transferValueString = JOptionPane.showInputDialog("Type the transfer value");
		Float transferValue = Float.parseFloat(transferValueString);		   

		String destinationAccountNumberString = JOptionPane.showInputDialog("To which account do you want to transfer?");
		int destinationAccountNumber = Integer.parseInt(destinationAccountNumberString);

		for (Account acc : accountsList) {
				if (acc.getAccountNumber() == originAccountNumber) {

					if (transferValue > acc.getBalance()) {
							JOptionPane.showMessageDialog(null,
							"Error!\nTransfer value greater than balance.");
						ATM();
					} else {
						float senderNewBalance = acc.getBalance() - transferValue;
						acc.setBalance(senderNewBalance);
						JOptionPane.showMessageDialog(null, "Money successfully transferred!");
					}

				}
		}

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == destinationAccountNumber) {
				float destinationAccountNewBalance = acc.getBalance() + transferValue;
				acc.setBalance(destinationAccountNewBalance);
			}
		}

 		
 		ATM();
 	}

	//Function to display the others functions and get the user's input and manage it
	public static void ATM() {
		String optionString = JOptionPane.showInputDialog(
				"Welcome!\n\nWhat do you want to do?\n 1) Create account \n 2) List accounts \n 3) Update account \n 4) Delete account \n 5) Statistics \n 6) Statement \n 7) Deposit \n 8) Withdraw \n 9) Transfer \n 0) Exit");

		int option = Integer.parseInt(optionString);

		switch (option) {
			case 1:
				create();
				break;
			case 2:
				read();
				break;
			case 3:
				update();
				break;
			case 4:
				delete();
				break;
			case 5:
				statistics();
				break;
			case 6:
				statement();
				break;
			case 7:
				deposit();
				break;
			case 8:
				withdraw();
				break;
			case 9:
				transfer();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Type a valid option...");
				ATM();
		}
	}
	

	//Main function that calls the ATM function to display and call the options
 	public static void main(String[] args) {
		try{
			ATM();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "You probably typed a letter where a number is expected.\n Try again...");
			main(args);
		}
	}
 }