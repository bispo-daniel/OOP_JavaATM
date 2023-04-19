import javax.swing.*;
import java.util.ArrayList;

public class AtmMachine {
	static Account accountLogged;
	static ArrayList<Account> accountsList = new ArrayList<Account>();

	private static void login() {
		String accountNumberString = JOptionPane.showInputDialog("Type the account number");
		int accountNumber = Integer.parseInt(accountNumberString);
		
		String password = JOptionPane.showInputDialog("Type the account password");

		int countAccounts = 0;

		for (Account acc : accountsList) {
			countAccounts++;

			if (acc.getAccountNumber() == accountNumber && acc.getPassword().equals(password)) {
				
				accountLogged = acc;
				break;
				
			}
		}

		if (countAccounts == accountsList.size()) {
			JOptionPane.showMessageDialog(null, "Wrong account number or password.\n Try again...");
		}
	}

	//Function to create a new account
	private static void create() {
		String accountNumberString = JOptionPane.showInputDialog("Type the account number");
		int accountNumber = Integer.parseInt(accountNumberString);
		
		String password = JOptionPane.showInputDialog("Type the account password");

 		String name = JOptionPane.showInputDialog("Type your name");

		String accountBalanceString = JOptionPane.showInputDialog("Type the initial balance");
		Float accountBalance = Float.parseFloat(accountBalanceString);

		Account acc = new Account(accountNumber, password, name, accountBalance);
		accountsList.add(acc);
		
		JOptionPane.showMessageDialog(null, "Account successfully created!");
 	}

	static String listAllAccounts = "";

	//Function to display all accounts 
 	private static void read() {
		listAllAccounts = "";

 		for (Account acc : accountsList) {
			listAllAccounts += acc.print();
		}
		
		JOptionPane.showMessageDialog(null, listAllAccounts);
 		
 	}

	//Function to update an account 
 	private static void update(Account accountLogged) {
		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountLogged.getAccountNumber()) {
				String aux02 = JOptionPane
						.showInputDialog("\nWhat do you want to update? \n1) Account number \n2) Password \n3) Name \n4) Balance");
				int op = Integer.parseInt(aux02);

				switch (op) {
					case 1:
						acc.setAccountNumber(Integer.parseInt(JOptionPane.showInputDialog("Type the new account number")));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
						break;
					case 2:
						acc.setPassword(JOptionPane.showInputDialog("Type the new account password"));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
						break;
					case 3:
						acc.setName(JOptionPane.showInputDialog("Type the new name"));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
						break;
					case 4:
						acc.setBalance(Float.parseFloat(JOptionPane.showInputDialog("Type the new balance")));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
						break;
					default:
						JOptionPane.showMessageDialog(null, "Type a valid option...");
						update(accountLogged);
				}
			}
		}

		
 	}

	//Function to delete an account
 	private static void delete(Account accountLogged) {
		int accountNumber = accountLogged.getAccountNumber();
		String accountPassword = JOptionPane.showInputDialog(null, "Type your account password to delete it");
		
		for(Account account : accountsList){
			if(account.getAccountNumber() == accountNumber && account.getPassword().equals(accountPassword)){
				accountLogged = null;
				accountsList.remove(account);

				JOptionPane.showMessageDialog(null, "Account successfully removed!");
				
				//Break used to exit for loop and avoid an error
				break;
			}
		}
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

		
	}

	//Function to take a bank statement
 	private static void statement(Account accountLogged) {
		int accountNumber = accountLogged.getAccountNumber();

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				JOptionPane.showMessageDialog(null, "\nAvailable balance: $ " + acc.getBalance());
			}
		}

		
 	}

	//Function to make a deposit
 	private static void deposit(Account accountLogged) {
		int accountNumber = accountLogged.getAccountNumber();

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String aux02 = JOptionPane.showInputDialog("\nType the deposit value");
				Float depositValue = Float.parseFloat(aux02);		

				float novoSaldo = acc.getBalance() + depositValue;
				acc.setBalance(novoSaldo);

				JOptionPane.showMessageDialog(null, "Deposit successfully made!");
			}
		}

		
 	}

	//Function to withdraw money from one account
 	private static void withdraw(Account accountLogged) {
 		int accountNumber = accountLogged.getAccountNumber();

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

		
 	}

	//Function to transfer money from one account to another
 	private static void transfer(Account accountLogged) {
		int originAccountNumber = accountLogged.getAccountNumber();

		String transferValueString = JOptionPane.showInputDialog("Type the transfer value");
		Float transferValue = Float.parseFloat(transferValueString);		   

		String destinationAccountNumberString = JOptionPane.showInputDialog("To which account do you want to transfer?");
		int destinationAccountNumber = Integer.parseInt(destinationAccountNumberString);

		for (Account acc : accountsList) {
				if (acc.getAccountNumber() == originAccountNumber) {

					if (transferValue > acc.getBalance()) {
							JOptionPane.showMessageDialog(null,
							"Error!\nTransfer value greater than balance.");
						userMenu();
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

		
 	}

	//Function to display the others functions and get the user's input and manage it
	public static void userMenu() {
		String dialog = "Welcome %s! \nYour account number: %d \nYour balance: %.2f \n\nWhat do you want to do? \n 1) Statement \n 2) Deposit \n 3) Withdraw \n 4) Transfer \n 5) Update account \n 6) Delete account \n 0) Exit account";
		String optionString = JOptionPane.showInputDialog(
				String.format(dialog, accountLogged.getName(), accountLogged.getAccountNumber(), accountLogged.getBalance()));

		int option = Integer.parseInt(optionString);

		switch (option) {
			case 0:
				if (confirmation() == true) {
					accountLogged = null;
					main(null);
				} else {
					main(null);
				}
				break;
			case 1:
				statement(accountLogged);
				main(null);
				break;
			case 2:
				deposit(accountLogged);
				main(null);
				break;
			case 3:
				withdraw(accountLogged);
				main(null);
				break;
			case 4:
				transfer(accountLogged);
				main(null);
				break;
			case 5:
				update(accountLogged);
				main(null);
				break;
			case 6:
				if (confirmation() == true) {
					delete(accountLogged);
					accountLogged = null;
					main(null);
				} else {
					main(null);
				}
				break;
			default:
				JOptionPane.showMessageDialog(null, "Type a valid option...");
				userMenu();
		}
	}

	static void initialMenu(){
		String dialog = "Welcome!\n\n What do you to do? \n 1) Login \n 2) Create account \n 3) List accounts \n 4) Statistics \n 0) Exit system";
		String optionString = JOptionPane.showInputDialog(null, dialog);
		int option = Integer.parseInt(optionString);

		switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				login();
				main(null);
				break;
			case 2:
				create();
				main(null);
				break;
			case 3:
				read();
				main(null);
				break;
			case 4:
				statistics();
				main(null);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Type a valid number...");
				initialMenu();
		}

	}

	static boolean confirmation() {
		boolean bool = false;
		String confirmationString = JOptionPane.showInputDialog(null, "Are you sure?\n\n 0) No \n 1) Yes");
		int confirmation = Integer.parseInt(confirmationString);

		switch (confirmation) {
			case 0:
				bool = false;
				break;
			case 1:
				bool = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Type a valid option...");
				confirmation();
				break;
		}

		return bool;
	}

	//Main function that calls the userMenu function to display and call the options
 	public static void main(String[] args) {
		System.out.println(accountLogged);
		try{

			if (accountLogged != null) {
				userMenu();
			} else {
				initialMenu();
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "You probably typed a letter where a number is expected.\n Try again...");
			main(args);
		}
	}
 }