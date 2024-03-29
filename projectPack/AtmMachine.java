package projectPack;
import javax.swing.*;

import java.util.ArrayList;

public class AtmMachine {
	static Account accountLogged;
	static ArrayList<Account> accountsList = new ArrayList<>();

	public static void login() {
		String accountNumberString = JOptionPane.showInputDialog("Type the account number");
		int accountNumber = Integer.parseInt(accountNumberString);
		
		String password = JOptionPane.showInputDialog("Type the account password");

		boolean accountFound = false;

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber && acc.getPassword().equals(password)) {
				accountFound = true;
				
				accountLogged = acc;
				break;
				
			}
		}

		if (!accountFound) {
			JOptionPane.showMessageDialog(null, "Wrong account number or password.\n Try again...");
		}
	}

	//Function to create a new account
	public static void create() {
		String accountNumberString = JOptionPane.showInputDialog("Type the account number");
		int accountNumber = Integer.parseInt(accountNumberString);
		
		String password = JOptionPane.showInputDialog("Type the account password");

 		String name = JOptionPane.showInputDialog("Type your name");

		String accountBalanceString = JOptionPane.showInputDialog("Type the initial balance");
		Double accountBalance = Double.parseDouble(accountBalanceString);

		Account acc = new Account(accountNumber, password, name, accountBalance);
		accountsList.add(acc);
		
		JOptionPane.showMessageDialog(null, "Account successfully created!");
 	}

	static String listAllAccounts = "";

	//Function to display all accounts 
 	public static void read() {
		listAllAccounts = "";

 		for (Account acc : accountsList) {
			listAllAccounts += acc.print();
		}
		
		JOptionPane.showMessageDialog(null, listAllAccounts);
 		
 	}

	//Function to update an account 
 	public static void update(Account accountLogged) {
		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountLogged.getAccountNumber()) {
				String aux02 = JOptionPane
						.showInputDialog("\nWhat do you want to update? \n1) Account number \n2) Password \n3) Name \n4) Balance");
				int op = Integer.parseInt(aux02);

				switch (op) {
					case 1:
						acc.setAccountNumber(Integer.parseInt(JOptionPane.showInputDialog("Type the new account number")));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
					case 2:
						acc.setPassword(JOptionPane.showInputDialog("Type the new account password"));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
					case 3:
						acc.setName(JOptionPane.showInputDialog("Type the new name"));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
					case 4:
						acc.setBalance(Double.parseDouble(JOptionPane.showInputDialog("Type the new balance")));
						JOptionPane.showMessageDialog(null, "Account successfully updated!");
					default:
						JOptionPane.showMessageDialog(null, "Type a valid option...");
						update(accountLogged);
				}
			}
		}

		
 	}

	//Function to delete an account
 	public static void delete(Account accountLogged) {
		int accountNumber = accountLogged.getAccountNumber();
		String accountPassword = JOptionPane.showInputDialog(null, "Type your account password to delete it");
		
		for(Account account : accountsList){
			if(account.getAccountNumber() == accountNumber && account.getPassword().equals(accountPassword)){
				accountsList.remove(account);

				JOptionPane.showMessageDialog(null, "Account successfully removed!");
				
				//Break used to exit for loop and avoid an error
				break;
			}
		}
	}

	//Function to get the highest balance among all the accounts
 	public static double max() {
 		double max = accountsList.get(0).getBalance();

 		for (Account acc : accountsList) {
			if (acc.getBalance() > max) {
				max = acc.getBalance();
			}
 		}

 		return max;
 	}

	//function to get the lowest balance among all the accounts
 	public static double min() {
 		double min = accountsList.get(0).getBalance();

 		for (Account acc : accountsList) {
			if (acc.getBalance() < min) {
				min = acc.getBalance();
			}
 		}

 		return min;
 	}

	//Function to get the average balance of all accounts 
 	public static double average() {
 		double add = 0;
		int divider = 0;

 		for (Account acc : accountsList) {
			divider++;
			add += acc.getBalance();
 		}

		return add / divider;
 	}

	//Function to display the 3 previous functions
	public static void statistics() {

		double max = max();
		double min = min();
		double average = average();

		JOptionPane.showMessageDialog(null,
				"Highest balance: $ " + max + "\nLowest balance: $ " + min + "\nBalance average: $ " + average);

		
	}

	//Function to take a bank statement
 	public static void statement(Account accountLogged) {
		int accountNumber = accountLogged.getAccountNumber();

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				JOptionPane.showMessageDialog(null, "\nAvailable balance: $ " + acc.getBalance());
			}
		}

		
 	}

	//Function to make a deposit
 	public static void deposit(Account accountLogged) {
		int accountNumber = accountLogged.getAccountNumber();

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String aux02 = JOptionPane.showInputDialog("\nType the deposit value");
				double depositValue = Double.parseDouble(aux02);		

				double newBalance = acc.getBalance() + depositValue;
				acc.setBalance(newBalance);

				JOptionPane.showMessageDialog(null, "Deposit successfully made!");
			}
		}

		
 	}

	//Function to withdraw money from one account
 	public static void withdraw(Account accountLogged) {
 		int accountNumber = accountLogged.getAccountNumber();

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String withdrawValueString = JOptionPane.showInputDialog("Type the withdraw value");
				double withdrawValue = Double.parseDouble(withdrawValueString);

				if (withdrawValue > acc.getBalance()) {
					JOptionPane.showMessageDialog(null,	"Error!\nWithdraw value greater than balance.");
				} else {
					double newBalance = acc.getBalance() - withdrawValue;
					acc.setBalance(newBalance);
					JOptionPane.showMessageDialog(null, "Withdraw successfully made!");
				}
			}
		}

		
 	}

	//Function to transfer money from one account to another
 	public static void transfer(Account accountLogged) {
		int originAccountNumber = accountLogged.getAccountNumber();

		String transferValueString = JOptionPane.showInputDialog("Type the transfer value");
		double transferValue = Double.parseDouble(transferValueString);		   

		String destinationAccountNumberString = JOptionPane.showInputDialog("To which account do you want to transfer?");
		int destinationAccountNumber = Integer.parseInt(destinationAccountNumberString);

		for (Account acc : accountsList) {
				if (acc.getAccountNumber() == originAccountNumber) {

					if (transferValue > acc.getBalance()) {
							JOptionPane.showMessageDialog(null,
							"Error!\nTransfer value greater than balance.");
						userMenu();
					} else {
						double senderNewBalance = acc.getBalance() - transferValue;
						acc.setBalance(senderNewBalance);
						JOptionPane.showMessageDialog(null, "Money successfully transferred!");
					}

				}
		}

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == destinationAccountNumber) {
				double destinationAccountNewBalance = acc.getBalance() + transferValue;
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
				if (confirmation()) {
					accountLogged = null;
					main(null);
				} else {
					main(null);
				}
			case 1: 
				statement(accountLogged);
				main(null);
			case 2: 
				deposit(accountLogged);
				main(null);
			case 3: 
				withdraw(accountLogged);
				main(null);
			case 4: 
				transfer(accountLogged);
				main(null);
			case 5: 
				update(accountLogged);
				main(null);
			case 6: 
				if (confirmation()) {
					delete(accountLogged);
					accountLogged = null;
					main(null);
				} else {
					main(null);
				}
			default: 
				JOptionPane.showMessageDialog(null, "Type a valid option...");
				userMenu();
		}
	}

	//Function to display the initial functions
	public static void initialMenu(){
		String dialog = "Welcome!\n\n What do you to do? \n 1) Login \n 2) Create account \n 3) List accounts \n 4) Statistics \n 0) Exit system";
		String optionString = JOptionPane.showInputDialog(null, dialog);
		int option = Integer.parseInt(optionString);

		switch (option) {
			case 0: System.exit(0);
			case 1:
				login();
				main(null);
			case 2:
				create();
				main(null);
			case 3:
				read();
				main(null);
			case 4:
				statistics();
				main(null);
			default:
				JOptionPane.showMessageDialog(null, "Type a valid number...");
				initialMenu();
		}

	}

	//Function that is called when the user wants to exit account ou delete his account
	public static boolean confirmation() {
		boolean bool = false;
		String confirmationString = JOptionPane.showInputDialog(null, "Are you sure?\n\n 1) Yes");
		int confirmation = Integer.parseInt(confirmationString);

		if (confirmation == 1) {
			bool = true;
		}

		return bool;
	}

	//Main function that calls the userMenu function to display and call the options
 	public static void main(String[] args) {
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