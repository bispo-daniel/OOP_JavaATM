package OOP_javaATM;

import javax.swing.*;
import java.util.ArrayList;

public class AtmMachine {
	static ArrayList<Account> accountsList = new ArrayList<Account>();

	//Function to create a new account
	private static void create() {
		String aux01 = JOptionPane.showInputDialog("Digite o número da conta: ");
		int accountNumber = Integer.parseInt(aux01);

 		String nome = JOptionPane.showInputDialog("Digite o nome do titular: ");

		String aux03 = JOptionPane.showInputDialog("Digite o saldo inicial: ");
		Float accountBalance = Float.parseFloat(aux03);

		Account acc = new Account(accountNumber, nome, accountBalance);
		accountsList.add(acc);
		
		JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
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
		String aux01 = JOptionPane.showInputDialog("Qual conta você deseja alterar?");
		int accountNumber = Integer.parseInt(aux01);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String aux02 = JOptionPane
						.showInputDialog("\nO que deseja alterar? \n1) Número da Conta \n2) Nome \n3) Saldo");
				int op = Integer.parseInt(aux02);

				switch (op) {
					case 1:
						acc.setAccountNumber(Integer.parseInt(JOptionPane.showInputDialog("Digite o novo número: ")));
						JOptionPane.showMessageDialog(null, "Cliente alterado com Sucesso!");
						break;
					case 2:
						acc.setName(JOptionPane.showInputDialog("Digite o novo nome: "));
						JOptionPane.showMessageDialog(null, "Cliente alterado com Sucesso!");
						break;
					case 3:
						acc.setBalance(Float.parseFloat(JOptionPane.showInputDialog("Digite o novo saldo: ")));
						JOptionPane.showMessageDialog(null, "Cliente alterado com Sucesso!");
						break;
					default:
						JOptionPane.showMessageDialog(null, "Escolha uma opção entre 1 e 3...");
						update();
				}
			}
		}

 		ATM();
 	}
 	
	//Function to delete an account
 	private static void delete() {
		String auxiliar = JOptionPane.showInputDialog("Insira o número da conta que deseja excluir:");
		int accountNumber = Integer.parseInt(auxiliar);
		
		for(Account account : accountsList){
			if(account.getAccountNumber() == accountNumber){
				accountsList.remove(account);

				JOptionPane.showMessageDialog(null, "Conta removida com sucesso!");

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
	 private static void descritivo() {

		float max = max();
		float min = min();
		float average = average();

		JOptionPane.showMessageDialog(null,
				"Maior Saldo:  R$ " + max + "\nMenor Saldo:  R$ " + min + "\nMedia dos Saldos:  R$ " + average);

		ATM();
	}

	//Function to take a bank statement
 	private static void extrato() {
		String aux = JOptionPane.showInputDialog("\nDe qual conta deseja tirar o extrato?");
		int accountNumber = Integer.parseInt(aux);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				JOptionPane.showMessageDialog(null, "\nSaldo disponível: R$ " + acc.getBalance());
			}
		}

 		ATM();
 	}

	//Function to make a deposit
 	private static void depositar() {
		String aux01 = JOptionPane.showInputDialog("\nEm qual conta deseja realizar um depósito?");
		int accountNumber = Integer.parseInt(aux01);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String aux02 = JOptionPane.showInputDialog("\nQual o valor do depósito?");
				Float depositValue = Float.parseFloat(aux02);		

				float novoSaldo = acc.getBalance() + depositValue;
				acc.setBalance(novoSaldo);

				JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
			}
		}

 		ATM();
 	}

	//Function to withdraw money from one account
 	private static void sacar() {
 		String aux01 = JOptionPane.showInputDialog("Em qual conta deseja realizar um saque?");
		int accountNumber = Integer.parseInt(aux01);

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == accountNumber) {
				String aux02 = JOptionPane.showInputDialog("Qual o valor do saque?");
				Float withdrawValue = Float.parseFloat(aux02);

				if (withdrawValue > acc.getBalance()) {
					JOptionPane.showMessageDialog(null,	"Valor do saque maior do que o saldo.\nSaque não concluído.");
				} else {
					float newBalance = acc.getBalance() - withdrawValue;
					acc.setBalance(newBalance);
					JOptionPane.showMessageDialog(null, "Saque realizado com Sucesso!");
				}
			}
		}

 		ATM();
 	}

	//Function to transfer money from one account to another
 	private static void tranferir() {
		String aux01 = JOptionPane.showInputDialog("De qual conta deseja realizar uma transferência?");
		int originAccountNumber = Integer.parseInt(aux01);

		String aux02 = JOptionPane.showInputDialog("Qual o valor da transferência?");
		Float tedValue = Float.parseFloat(aux02);		   

		String aux03 = JOptionPane.showInputDialog("Para qual conta deseja enviar sua transferência?");
		int destinationAccountNumber = Integer.parseInt(aux03);

		for (Account acc : accountsList) {
				if (acc.getAccountNumber() == originAccountNumber) {

					if (tedValue > acc.getBalance()) {
							JOptionPane.showMessageDialog(null,
							"Valor da tranferência maior do que o saldo.\nTranferência não concluída.");
						ATM();
					} else {
						float novoSaldoOrigem = acc.getBalance() - tedValue;
						acc.setBalance(novoSaldoOrigem);
						JOptionPane.showMessageDialog(null, "Transferência realizada com Sucesso!");
					}

				}
		}

		for (Account acc : accountsList) {
			if (acc.getAccountNumber() == destinationAccountNumber) {
				float novoSaldoDestino = acc.getBalance() + tedValue;
				acc.setBalance(novoSaldoDestino);
			}
		}

 		
 		ATM();
 	}

	//Function to display the others functions and get the user's input and manage it
	public static void ATM() {
		String aux = JOptionPane.showInputDialog(
				"Olá, Seja Bem-Vindo!\n\nO que deseja fazer?\n\n1)Cadastrar Conta \n2)Listar Contas \n3)Atualizar Conta \n4)Deletar Conta \n5)Descritivo \n6)Extrato \n7)Depositar \n8)Sacar \n9)Tranferir \n0)Sair");

		int escolha = Integer.parseInt(aux);

		switch (escolha) {
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
				descritivo();
				break;
			case 6:
				extrato();
				break;
			case 7:
				depositar();
				break;
			case 8:
				sacar();
				break;
			case 9:
				tranferir();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Digite um número entre 0 e 9...");
		}
	}
	

	//Main function that calls the ATM function to display and call the options
 	public static void main(String[] args) {
		try{
			ATM();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Você provavelmente digitou uma letra onde um número era esperado. Tente novamente...");
			main(args);
		}
	}
 }