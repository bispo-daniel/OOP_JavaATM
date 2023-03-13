import javax.swing.*;

public class AtmMachine {
 	private static Account[] accountsList = new Account[20];
 	
 	private static int iterator;

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
 							if (iterator < accountsList.length) {
 								create();
 							} else {
 								JOptionPane.showMessageDialog(null, "A lista já está cheia...");
 								ATM();
 							}
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
 							exit();
 							break;
 						default:
 							JOptionPane.showMessageDialog(null, "Ocorreu algum erro...");
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

	private static void create() {
 		accountsList[iterator] = new Account();

 		try {
 			String aux01 = JOptionPane.showInputDialog("Digite o número da conta: " + "[" + (iterator + 1) + "]");
 			int accountNumber = Integer.parseInt(aux01);
 			accountsList[iterator].setAccountNumber(accountNumber);
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você digitou não digitou o número corretamente. Tente Novamente");

 			String aux01 = JOptionPane.showInputDialog("Digite o número da conta: " + "[" + (iterator + 1) + "]");
 			int accountNumber = Integer.parseInt(aux01);
 			accountsList[iterator].setAccountNumber(accountNumber);
 		}

 		String aux02 = JOptionPane.showInputDialog("Digite o nome do titular: ");
 		accountsList[iterator].setName(aux02);

 		try {
 			String aux03 = JOptionPane.showInputDialog("Digite o saldo inicial: ");
 			Float accountBalance = Float.parseFloat(aux03);
 			accountsList[iterator].setBalance(accountBalance);
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você não digitou o saldo corretamente. Tente Novamente");

 			String aux03 = JOptionPane.showInputDialog("Digite o saldo inicial: ");
 			Float accountBalance = Float.parseFloat(aux03);
 			accountsList[iterator].setBalance(accountBalance);
 		}

 		JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");

 		iterator++;
 		ATM();
 	}

 	private static void read() {
 		for (int i = 0; i < accountsList.length; i++) {

 			JOptionPane.showMessageDialog(null, "Número da Conta: " + accountsList[i].getAccountNumber() + "\nTitular: "
 					+ accountsList[i].getName() + "\nSaldo:  R$ " + accountsList[i].getBalance());
 			
 			
 		}

 		ATM();

 	}

 	private static void update() {
 		try {
 			String aux01 = JOptionPane.showInputDialog("Qual conta você deseja alterar?");
 			int accountNumber = Integer.parseInt(aux01);

 			for (int i = 0; i < accountsList.length; i++) {
 				if (accountsList[i].getAccountNumber() == accountNumber) {
 					String aux02 = JOptionPane
 							.showInputDialog("\nO que desejas alterar? \n1)Nome \n2)Número da Conta \n3)Saldo");
 					int op = Integer.parseInt(aux02);

 					switch (op) {
 						case 1:
 							accountsList[i].setName(JOptionPane.showInputDialog("Digite o novo nome: "));
 							break;
 						case 2:
 							accountsList[i].setAccountNumber(
 									Integer.parseInt(JOptionPane.showInputDialog("Digite o novo número: ")));
 							break;
 						case 3:
 							accountsList[i]
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
 	
 	private static void delete() {
		String n = JOptionPane.showInputDialog("Insira o número da conta que deseja excluir:");
		int nmr = Integer.parseInt(n);
		
		for(int i = 0; i < accountsList.length; i++) {
			if(nmr == accountsList[i].getAccountNumber()) {
				accountsList[i].setAccountNumber(0);
				accountsList[i].setName(null);
				accountsList[i].setBalance(null);				
			}
		}
		
		ATM();
		
	}

 	private static void descritivo() {

 		float max = max();
 		float min = min();
 		float average = average();

 		JOptionPane.showMessageDialog(null,
 				"Maior Saldo:  R$ " + max + "\nMenor Saldo:  R$ " + min + "\nMedia dos Saldos:  R$ " + average);

 		ATM();
 	}

 	private static float max() {
 		float max = accountsList[0].getBalance();

 		for (int i = 0; i < accountsList.length; i++) {
 			try {
 				if (accountsList[i].getBalance() > max) {
 					max = accountsList[i].getBalance();
 				}
 			} catch (NullPointerException e) {
 				continue;
 			}

 		}
 		return max;
 	}

 	private static float min() {
 		float min = accountsList[0].getBalance();
 		for (int i = 0; i < accountsList.length; i++) {
 			try {
 				if (accountsList[i].getBalance() < min) {
 					min = accountsList[i].getBalance();
 				}
 			} catch (NullPointerException e) {
 				continue;
 			}
 		}

 		return min;
 	}

 	private static float average() {
 		float add = 0;
 		for (int i = 0; i < accountsList.length; i++) {
 			try {
 				add += accountsList[i].getBalance();
 			} catch (NullPointerException e) {
 				continue;
 			}
 		}

 		float average = add / iterator;
 		return average;
 	}

 	private static void extrato() {
 		try {
 			String aux = JOptionPane.showInputDialog("\nDe qual conta deseja tirar o extrato?");
 			int accountNumber = Integer.parseInt(aux);

 			for (int i = 0; i < accountsList.length; i++) {
 				if (accountsList[i].getAccountNumber() == accountNumber) {
 					JOptionPane.showMessageDialog(null, "\nSaldo disponível: R$" + accountsList[i].getBalance());
 				}
 			}
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você não digitou o número da conta corretamente. Tente novamente.");
 			extrato();
 		}
 		ATM();

 	}

 	private static void depositar() {
 		try {
 			String aux01 = JOptionPane.showInputDialog("\nEm qual conta deseja realizar um depósito?");
 			int accountNumber = Integer.parseInt(aux01);
 			String aux02 = JOptionPane.showInputDialog("\nQual o valor do depósito?");
 			Float depositValue = Float.parseFloat(aux02);
 			for (int i = 0; i < accountsList.length; i++) {
 				if (accountsList[i].getAccountNumber() == accountNumber) {
 					float novoSaldo = accountsList[i].getBalance() + depositValue;
 					accountsList[i].setBalance(novoSaldo);
 					JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!");
 				}
 			}
 		} catch (NumberFormatException e) {
 			JOptionPane.showMessageDialog(null, "Você não digitou o número da conta corretamente. Tente novamente.");
 			depositar();
 		}

 		ATM();
 	}

 	private static void sacar() {
 		String aux01 = JOptionPane.showInputDialog("Em qual conta deseja realizar um saque?");
 		try {
 			int accountNumber = Integer.parseInt(aux01);
 			String aux02 = JOptionPane.showInputDialog("Qual o valor do saque?");
 			Float withdrawValue = Float.parseFloat(aux02);
 			for (int i = 0; i < accountsList.length; i++) {
 				if (accountsList[i].getAccountNumber() == accountNumber) {
 					if (withdrawValue <= accountsList[i].getBalance()) {
 						float newBalance = accountsList[i].getBalance() - withdrawValue;
 						accountsList[i].setBalance(newBalance);
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

 	private static void tranferir() {
 		try {
 			String aux01 = JOptionPane.showInputDialog("De qual conta deseja realizar uma transferência?");
 			int originAccountNumber = Integer.parseInt(aux01);
 			String aux02 = JOptionPane.showInputDialog("Qual o valor da transferência?");
 			Float tedValue = Float.parseFloat(aux02);
 			String aux03 = JOptionPane.showInputDialog("Para qual conta deseja enviar sua transferência?");
 			int destinationAccountNumber = Integer.parseInt(aux03);

 			for (int i = 0; i < accountsList.length; i++) {
 				try {
 					if (accountsList[i].getAccountNumber() == originAccountNumber) {
 						if (tedValue <= accountsList[i].getBalance()) {
 							float novoSaldoOrigem = accountsList[i].getBalance() - tedValue;
 							accountsList[i].setBalance(novoSaldoOrigem);
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

 			for (int i = 0; i < accountsList.length; i++) {
 				try {
 					if (accountsList[i].getAccountNumber() == destinationAccountNumber) {
 						float novoSaldoDestino = accountsList[i].getBalance() + tedValue;
 						accountsList[i].setBalance(novoSaldoDestino);
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

 	private static void exit() {
 		JOptionPane.showMessageDialog(null, "Muito obrigado por sua visita!\nVolte Sempre.");
 		System.exit(0);
 	}

 	public static void main(String[] args) {
 		JOptionPane.showMessageDialog(null, "Olá, Seja Bem-Vindo!");
 		ATM();
 	}
 }