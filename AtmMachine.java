import javax.swing.*;
import java.util.ArrayList;

public class AtmMachine {
	static ArrayList<Account> accountsList = new ArrayList<Account>();
 	
 	private static int iterator;

	//Function to create a new account
	private static void create() {
		accountsList.add(new Account());

 		try {
 			String aux01 = JOptionPane.showInputDialog("Digite o número da conta: " + "[" + (iterator + 1) + "]");
 			int accountNumber = Integer.parseInt(aux01);
 			accountsList.get(iterator).setAccountNumber(accountNumber);
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você digitou não digitou o número corretamente. Tente Novamente");

 			String aux01 = JOptionPane.showInputDialog("Digite o número da conta: " + "[" + (iterator + 1) + "]");
 			int accountNumber = Integer.parseInt(aux01);
 			accountsList.get(iterator).setAccountNumber(accountNumber);
 		}

 		String aux02 = JOptionPane.showInputDialog("Digite o nome do titular: ");
 		accountsList.get(iterator).setName(aux02);

 		try {
 			String aux03 = JOptionPane.showInputDialog("Digite o saldo inicial: ");
 			Float accountBalance = Float.parseFloat(aux03);
 			accountsList.get(iterator).setBalance(accountBalance);
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você não digitou o saldo corretamente. Tente Novamente");

 			String aux03 = JOptionPane.showInputDialog("Digite o saldo inicial: ");
 			Float accountBalance = Float.parseFloat(aux03);
 			accountsList.get(iterator).setBalance(accountBalance);
 		}

 		JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

 		iterator++;
 		ATM();
 	}

	//Function to display all accounts 
 	private static void read() {
 		for (int i = 0; i < accountsList.size(); i++) {
 			JOptionPane.showMessageDialog(null, "Número da Conta: " + accountsList.get(i).getAccountNumber() + "\nTitular: "
 					+ accountsList.get(i).getName() + "\nSaldo:  R$ " + accountsList.get(i).getBalance());
 		}

 		ATM();

 	}

	//Function to update an account 
 	private static void update() {
 		try {
 			String aux01 = JOptionPane.showInputDialog("Qual conta você deseja alterar?");
 			int accountNumber = Integer.parseInt(aux01);

 			for (int i = 0; i < accountsList.size(); i++) {
 				if (accountsList.get(i).getAccountNumber() == accountNumber) {
 					String aux02 = JOptionPane
 							.showInputDialog("\nO que desejas alterar? \n1)Nome \n2)Número da Conta \n3)Saldo");
 					int op = Integer.parseInt(aux02);

 					switch (op) {
 						case 1:
 							accountsList.get(i).setName(JOptionPane.showInputDialog("Digite o novo nome: "));
 							break;
 						case 2:
 							accountsList.get(i).setAccountNumber(
 									Integer.parseInt(JOptionPane.showInputDialog("Digite o novo número: ")));
 							break;
 						case 3:
 							accountsList.get(i)
 									.setBalance(Float.parseFloat(JOptionPane.showInputDialog("Digite o novo saldo: ")));
 							break;
 					}
 					JOptionPane.showMessageDialog(null, "Cliente alterado com Sucesso!");
 				}
 			}
 		} catch (NumberFormatException e) {
 			JOptionPane.showInternalMessageDialog(null, "Digite o número da conta corretamente");
 			update();
 		}

 		ATM();
 	}
 	
	//Function to delete an account
 	private static void delete() {
		String n = JOptionPane.showInputDialog("Insira o número da conta que deseja excluir:");
		int nmr = Integer.parseInt(n);
		
		for(Account account : accountsList){
			if(account.getAccountNumber() == nmr){
				accountsList.remove(account);

				JOptionPane.showMessageDialog(null, "Conta removida com sucesso!");

				//Break used to exit for loop and avoid an error
				break;
			}
		}
		
		iterator--;
		ATM();
		
	}

	//Function to get the highest balance among all the accounts
 	private static float max() {
 		float max = accountsList.get(0).getBalance();

 		for (int i = 0; i < accountsList.size(); i++) {
 			try {
 				if (accountsList.get(i).getBalance() > max) {
 					max = accountsList.get(i).getBalance();
 				}
 			} catch (NullPointerException e) {
 				continue;
 			}

 		}
 		return max;
 	}

	//function to get the lowest balance among all the accounts
 	private static float min() {
 		float min = accountsList.get(0).getBalance();
 		for (int i = 0; i < accountsList.size(); i++) {
 			try {
 				if (accountsList.get(i).getBalance() < min) {
 					min = accountsList.get(i).getBalance();
 				}
 			} catch (NullPointerException e) {
 				continue;
 			}
 		}

 		return min;
 	}

	//Function to get the average balance of all accounts 
 	private static float average() {
 		float add = 0;
 		for (int i = 0; i < accountsList.size(); i++) {
 			try {
 				add += accountsList.get(i).getBalance();
 			} catch (NullPointerException e) {
 				continue;
 			}
 		}

 		float average = add / iterator;
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
 		try {
 			String aux = JOptionPane.showInputDialog("\nDe qual conta deseja tirar o extrato?");
 			int accountNumber = Integer.parseInt(aux);

 			for (int i = 0; i < accountsList.size(); i++) {
 				if (accountsList.get(i).getAccountNumber() == accountNumber) {
 					JOptionPane.showMessageDialog(null, "\nSaldo disponível: R$" + accountsList.get(i).getBalance());
 				}
 			}
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você não digitou o número da conta corretamente. Tente novamente.");
 			extrato();
 		}
 		ATM();

 	}

	//Function to make a deposit
 	private static void depositar() {
 		try {
 			String aux01 = JOptionPane.showInputDialog("\nEm qual conta deseja realizar um depósito?");
 			int accountNumber = Integer.parseInt(aux01);
 			String aux02 = JOptionPane.showInputDialog("\nQual o valor do depósito?");
 			Float depositValue = Float.parseFloat(aux02);
 			for (int i = 0; i < accountsList.size(); i++) {
 				if (accountsList.get(i).getAccountNumber() == accountNumber) {
 					float novoSaldo = accountsList.get(i).getBalance() + depositValue;
 					accountsList.get(i).setBalance(novoSaldo);
 					JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
 				}
 			}
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você não digitou o número da conta corretamente. Tente novamente.");
 			depositar();
 		}

 		ATM();
 	}

	//Function to withdraw money from one account
 	private static void sacar() {
 		String aux01 = JOptionPane.showInputDialog("Em qual conta deseja realizar um saque?");
 		try {
 			int accountNumber = Integer.parseInt(aux01);
 			String aux02 = JOptionPane.showInputDialog("Qual o valor do saque?");
 			Float withdrawValue = Float.parseFloat(aux02);
 			for (int i = 0; i < accountsList.size(); i++) {
 				if (accountsList.get(i).getAccountNumber() == accountNumber) {
 					if (withdrawValue <= accountsList.get(i).getBalance()) {
 						float newBalance = accountsList.get(i).getBalance() - withdrawValue;
 						accountsList.get(i).setBalance(newBalance);
 						JOptionPane.showMessageDialog(null, "Saque realizado com Sucesso!");
 					} else {
 						JOptionPane.showMessageDialog(null,
 								"Valor do saque maior do que o saldo.\nSaque não concluído.");
 					}
 				}
 			}
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você não digitou o número da conta corretamente. Tente novamente.");
 			sacar();
 		}
 		ATM();
 	}

	//Function to transfer money from one account to another
 	private static void tranferir() {
 		try {
 			String aux01 = JOptionPane.showInputDialog("De qual conta deseja realizar uma transferência?");
 			int originAccountNumber = Integer.parseInt(aux01);
 			String aux02 = JOptionPane.showInputDialog("Qual o valor da transferência?");
 			Float tedValue = Float.parseFloat(aux02);
 			String aux03 = JOptionPane.showInputDialog("Para qual conta deseja enviar sua transferência?");
 			int destinationAccountNumber = Integer.parseInt(aux03);

 			for (int i = 0; i < accountsList.size(); i++) {
 				try {
 					if (accountsList.get(i).getAccountNumber() == originAccountNumber) {
 						if (tedValue <= accountsList.get(i).getBalance()) {
 							float novoSaldoOrigem = accountsList.get(i).getBalance() - tedValue;
 							accountsList.get(i).setBalance(novoSaldoOrigem);
 							JOptionPane.showMessageDialog(null, "Transferência realizada com Sucesso!");
 						} else {
 							JOptionPane.showMessageDialog(null,
 									"Valor da tranferência maior do que o saldo.\nTranferência não concluída.");
 							ATM();
 						}
 					}
 				} catch (NullPointerException e) {
 					continue;
 				}
 			}

 			for (int i = 0; i < accountsList.size(); i++) {
 				try {
 					if (accountsList.get(i).getAccountNumber() == destinationAccountNumber) {
 						float novoSaldoDestino = accountsList.get(i).getBalance() + tedValue;
 						accountsList.get(i).setBalance(novoSaldoDestino);
 					}
 				} catch (NullPointerException e) {
 					continue;
 				}
 			}

 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você digitou algo errado. Tente novamente.");
 			tranferir();
 		}
 		ATM();
 	}

	//Function to display the others functions and get the user's input and process it
	 public static void ATM() {
		try {
			String aux = JOptionPane.showInputDialog(
					"O que deseja fazer?\n\n1)Cadastrar Conta \n2)Listar Contas \n3)Atualizar Conta \n4)Deletar Conta \n5)Descritivo \n6)Extrato \n7)Depositar \n8)Sacar \n9)Tranferir \n0)Sair");

			int escolha = Integer.parseInt(aux);

			try {
				if (escolha < 0 || escolha > 9) {
					JOptionPane.showMessageDialog(null, "Por favor, digite um valor entre 1 e 9");
					ATM();
				} else {
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
			} catch (NullPointerException e) {
				ATM();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Você não digitou a opção corretamente.\nDigite um número de 1 a 9 Por favor.");
			ATM();
		}
	}

	//Main function that calls the ATM function to display and call the options
 	public static void main(String[] args) {
 		JOptionPane.showMessageDialog(null, "Olá, Seja Bem-Vindo!");
 		ATM();
 	}
 }