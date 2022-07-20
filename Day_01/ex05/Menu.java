package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
	private TransactionsService service;
	private static Mode mode;
	private static int command;

	public enum Mode {
		PRO,
		DEV,
	}

	public Menu(Mode mode) {
		setMode(mode);
		this.service = new TransactionsService();
	}

	public Mode getMode() {
		return Menu.mode;
	}
	public void setMode(Mode mode) {
		Menu.mode = mode;
	}
	
	public void setCommand(int command) {
		Menu.command = command;
	}
	public int getCommand() {
		return Menu.command;
	}

	public void printProductionPrompt() {
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		System.out.println("5. Finish execution");
	}

	public void printDevPrompt() {
		System.out.println("1. Add a user");
		System.out.println("2. View user balances");
		System.out.println("3. Perform a transfer");
		System.out.println("4. View all transactions for a specific user");
		System.out.println("5. DEV - remove a transfer by ID");
		System.out.println("6. DEV - check transfer validity");
		System.out.println("7. Finish execution");
	}
	
	public void mainLoop() {
		Scanner input = new Scanner(System.in);
		if (Menu.mode == Menu.Mode.DEV) {
			while (true) {
				printDevPrompt();
				if (!input.hasNextInt()) {
					System.err.println("Invalid command!");
				}
				this.setCommand(input.nextInt());
				if (this.getCommand() < 1 || this.getCommand() > 7) {
					System.err.println("Invalid command!");
				}
				executeCommandDev(input);
			}
		} else if (Menu.mode == Menu.Mode.PRO) {
			while (true) {
				printProductionPrompt();
				if (!input.hasNextInt()) {
					System.err.println("Invalid command!");
				}
				this.setCommand(input.nextInt());
				if (this.getCommand() < 1 || this.getCommand() > 5) {
					System.err.println("Invalid command!");
				}
				executeCommandPro(input);
			}
		}
		input.close();
	}

	public void executeCommandDev(Scanner input) {
		if (this.getCommand() == 1) {
			this.addUser(input);
		} else if (this.getCommand() == 2) {
			this.getUserBalance(input);
		} else if (this.getCommand() == 3) {
			performTransfer(input);
		} else if (this.getCommand() == 4) {
			getUserTransaction(input);
		} else if (this.getCommand() == 5) {
			removeTransferById(input);
		} else if (this.getCommand() == 6) {
			checkTransferValidity(input);
		} else if (this.getCommand() == 7) {
			System.exit(0);
		}
	}

	public void executeCommandPro(Scanner input) {
		if (this.getCommand() == 1) {
			this.addUser(input);
		} else if (this.getCommand() == 2) {
			this.getUserBalance(input);
		} else if (this.getCommand() == 3) {
			performTransfer(input);
		} else if (this.getCommand() == 4) {
			getUserTransaction(input);
		} else if (this.getCommand() == 5) {
			System.exit(0);
		}
	}

	public void addUser(Scanner input) {
		while (true) {
			String name;
			Integer balance;
			System.out.println("Enter a user name and a balance");
			if (!input.hasNext()) {
				System.err.println("Invalid command!");
				continue;
			}
			name = input.next();
			if (!input.hasNextInt()) {
				System.err.println("Invalid command!");
				continue;
			}
			balance = input.nextInt();
			User user = new User(name);
			user.setBalance(balance);
			service.addUser(user);
			String response = "User with id = " + user.getId() + " is added";
			System.out.println(response);
			System.out.println("---------------------------------------------------------");
			break;
		}
	}

	public void getUserBalance(Scanner input) {
		while (true) {
			System.out.println("Enter a user ID");
			Integer id;
			if (!input.hasNextInt()) {
				System.err.println("Invalid command!");
				continue;
			}
			id = input.nextInt();
			try {
				String name = service.getListOfUsers().getById(id).getName();
				Integer balance = service.getUserBalance(id);
				String response = name + " - " + balance;
				System.out.println(response);
			} catch (UserNotFoundException e) {
				System.err.println(e.getMessage());
			}
			System.out.println("---------------------------------------------------------");
			break;
		}
	}

	public void performTransfer(Scanner input){
		while (true) {
			System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
			int senderId;
			int recipientId;
			int amount;
			if (!input.hasNextInt()) {
				System.err.println("Invalid command!");
				continue;
			}
			senderId = input.nextInt();
			if (!input.hasNextInt()) {
				System.err.println("Invalid command!");
				continue;
			}
			recipientId = input.nextInt();
			if (!input.hasNextInt()) {
				System.err.println("Invalid command!");
				continue;
			}
			amount = input.nextInt();
			service.performTransaction(senderId, recipientId, amount);
			System.out.println("The transfer is completed");
			System.out.println("---------------------------------------------------------");
			break;
		}
	}

	public void getUserTransaction(Scanner input) {
		while (true) {
			System.out.println("Enter a user ID");
			int id;
			if (!input.hasNextInt()) {
				System.err.println("Invalid command!");
				continue;
			}
			id = input.nextInt();
			try {
				Transaction[] arr = service.getListOfUsers().getById(id).getList().toArray();
				for (int i = 0; i < arr.length; i++) {
					System.out.printf("To %s(id = %d) %d with id = %s\n", arr[i].getRecipient().getName(), arr[i].getRecipient().getId(), arr[i].getAmount(), arr[i].getId());
				}
				System.out.println("---------------------------------------------------------");
				break;
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void removeTransferById(Scanner input) {
		while (true) {
			System.out.println("Enter a user ID and a transfer ID");
			int userId;
			String transferId;
			if (!input.hasNextInt()) {
				System.err.println("Invalid command!");
				continue;
			}
			userId = input.nextInt();
			if (!input.hasNext()) {
				System.err.println("Invalid command!");
				continue;
			}
			transferId = input.next();
			try {
				Transaction[] arr = service.getListOfUsers().getById(userId).getList().toArray();
				int i;
				for (i = 0; i < arr.length; i++) {
					if (arr[i].getId().toString().equals(transferId)) {
						break;
					}
				}
				if (i == arr.length) {
					System.err.println("Invalid transfer id!");
					continue;
				}
				int amount = arr[i].getAmount();
				User recipient = arr[i].getRecipient(); 
				service.deleteTransactionForUser(userId, UUID.fromString(transferId));
				System.out.printf("Transfer To %s(id = %d) %d removed\n", recipient.getName(), recipient.getId(), abs(amount));
				System.out.println("---------------------------------------------------------");
				break;
			} catch (UserNotFoundException e) {
				System.out.println(e.getMessage());
			}
 		}
	}

	public void checkTransferValidity(Scanner input) {
		System.out.println("Check results:");
		Transaction[] notValid = service.checkValidity();
		for (int i = 0; i < notValid.length; i++) {
			System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
				notValid[i].getRecipient().getName(), notValid[i].getRecipient().getId(), notValid[i].getId(), notValid[i].getSender().getName(), notValid[i].getSender().getId(), abs(notValid[i].getAmount()));
		}
		System.out.println("---------------------------------------------------------");
	}

	public static int abs(int a) {
		return a < 0 ? -a : a;
	}
}
