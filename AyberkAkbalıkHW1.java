package hw1;

import java.sql.*;
import java.util.Scanner;

// CS 202 HW 1 ------ HAMZA AYBERK AKBALIK  S012309

public class AyberkAkbalýkHW1 {
	private static final String URL = "jdbc:mysql://localhost:3306/dbms?useSSL=false&useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "akayberk";

	private static Connection connection = null;

	public static void establishConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//******************************************************************		    
	// NUMBER 1 REGISTRATION
	public static void registration() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner customer_reg = new Scanner(System.in);
			System.out.print("Please enter the name: ");
			String name = customer_reg.nextLine();
			System.out.print("Please enter the surname: ");
			String surname = customer_reg.nextLine();
			System.out.print("Please enter the address (just city): ");
			String address = customer_reg.nextLine();
			System.out.print("Please enter the GSM number: ");
			String phone_number = customer_reg.nextLine();

			String insertQuery = "insert into customer(name, surname, address, phone_number) values(\"" + name
					+ "\" ,\"" + surname + "\"  , \"" + address + "\", \"" + phone_number + "\" )";
			statement.executeUpdate(insertQuery);
			System.out.println("\nProcess completed!");

			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//********************************************
	// NUMBER 2
	public static void ordering() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner ordering = new Scanner(System.in);
			System.out.print("Please enter cid: ");
			int c_id = ordering.nextInt();
			System.out.print("Please enter pid: ");
			int p_id = ordering.nextInt();
			System.out.print("Please enter the bid: ");
			int b_id = ordering.nextInt();
			System.out.print("Please enter quantity: ");
			int quantity = ordering.nextInt();

			String selectQuery = "(select * from storing where p_id=\"" + p_id + "\" and b_id=\"" + b_id + "\")";
			ResultSet rs = statement.executeQuery(selectQuery);

			if (rs.next()) {
				if (rs.getInt("stock_quantity") >= quantity) {
					String updateQuery = ("UPDATE storing SET stock_quantity=stock_quantity-\"" + quantity
							+ "\" WHERE p_id=\"" + p_id + "\" AND b_id=\"" + b_id + "\"");
					statement.executeUpdate(updateQuery);

					String insertQuery = "insert into ordering (p_id, b_id, c_id, quantity, order_date) values(\""
							+ p_id + "\" ,\"" + b_id + "\" ,\"" + c_id + "\",\"" + quantity + "\",\""
							+ java.time.LocalDate.now() + "\")";
					statement.executeUpdate(insertQuery);
					System.out.println("\nProcess completed!");
				} else {
					System.out.println("There are no enough products in stock.");
				}
			} else {
				System.out.println("There is no such kind of product.");
			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

//*****************************************************************		    
	// NUMBER 3
	public static void list_of_customers() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			String selectQuery = "select * from customer";
			ResultSet rs = statement.executeQuery(selectQuery);

			System.out.println("\nCustomers' informations are:\n ");
			while (rs.next()) {
				System.out.println("C.id: " + rs.getString("c_id") + " | Name: " + rs.getString("name")
						+ " | Surname : " + rs.getString("surname") + " | Address : " + rs.getString("address")
						+ " | Phone : " + rs.getString("phone_number"));

			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//************************************************
	// NUMBER 4
	public static void purchases_of_a_customer() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner customer = new Scanner(System.in);
			System.out.print("Please enter the customer id: ");
			String id_of_a_customer = customer.nextLine();

			String selectQuery = "(select * from ordering where c_id=\"" + id_of_a_customer
					+ "\"order by order_date desc) ";

			ResultSet rs = statement.executeQuery(selectQuery);
			System.out.println("\nPurchase information of the customer:\n ");

			if (rs.next()) {
				System.out.println(
						"C.id: " + rs.getString("c_id") + " | P.id: " + rs.getString("p_id") + " | Order_date : "
								+ rs.getString("order_date") + " | Quantity : " + rs.getString("quantity"));

				while (rs.next()) {
					System.out.println(
							"C.id: " + rs.getString("c_id") + " | P.id: " + rs.getString("p_id") + " | Order_date : "
									+ rs.getString("order_date") + " | Quantity : " + rs.getString("quantity"));
				}
			} else {
				System.out.println("This customer has not yet purchased any products.");
			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//********************************
	// NUMBER 5
	public static void last_five_purchases() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner customer = new Scanner(System.in);
			System.out.print("Please enter the customer id: ");
			String id_of_a_customer = customer.nextLine();

			String selectQuery = "(select * from ordering where c_id=\"" + id_of_a_customer
					+ "\" order by order_date desc limit 5) ";

			ResultSet rs = statement.executeQuery(selectQuery);
			if (rs.next()) {
				System.out.println(
						"C.id: " + rs.getString("c_id") + " | P.id: " + rs.getString("p_id") + " | Order_date : "
								+ rs.getString("order_date") + " | Quantity : " + rs.getString("quantity"));
				while (rs.next()) {
					System.out.println(
							"C.id: " + rs.getString("c_id") + " | P.id: " + rs.getString("p_id") + " | Order_date : "
									+ rs.getString("order_date") + " | Quantity : " + rs.getString("quantity"));
				}
			} else {
				System.out.println("This customer has not yet purchased any products.");
			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//******************************************************************
	// NUMBER 6
	public static void list_of_branches() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			String selectQuery = "select * from branch";
			ResultSet rs = statement.executeQuery(selectQuery);
			System.out.println("\nInformations of the all branches in the system: \n");
			while (rs.next()) {
				System.out.println("ID:(" + rs.getString("b_id") + ") Name:(" + rs.getString("name") + ") Address:("
						+ rs.getString("address") + ")");

			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//******************************************************
	// NUMBER 7
	public static void branch_stock() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner branch = new Scanner(System.in);
			System.out.print("Please enter the branch id: ");
			int b_id = branch.nextInt();
			System.out.println("\nThe information of the products in this branch are as follows:\n");

			String selectQuery = "select * from storing natural join product where b_id = \"" + b_id
					+ "\" and stock_quantity > 0";

			ResultSet rs = statement.executeQuery(selectQuery);
			if (rs.next()) {
				System.out.println("p.id: " + rs.getString("p_id") + " | Stock: " + rs.getString("stock_quantity")
						+ " | Name: " + rs.getString("name") + " | Description: " + rs.getString("description")
						+ " | Price: " + rs.getString("price"));
				while (rs.next()) {
					System.out.println("p.id: " + rs.getString("p_id") + " | Stock: " + rs.getString("stock_quantity")
							+ " | Name: " + rs.getString("name") + " | Description: " + rs.getString("description")
							+ " | Price: " + rs.getString("price"));
				}
			} else {
				System.out.println(
						"The storage information of the branch you specified has not been entered yet. Please return step 10.");
			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//*******************************************************
	// NUMBER 8
	public static void new_branch() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner branch = new Scanner(System.in);
			System.out.print("Please enter the name of new branch: ");
			String branch_name = branch.nextLine();
			System.out.print("Please enter the address of the branch (as a city): ");
			String branch_address = branch.nextLine();

			String insertQuery = "insert into branch (name, address) values(\"" + branch_name + "\" ,\""
					+ branch_address + "\")";
			statement.executeUpdate(insertQuery);
			System.out.println("\nProcess completed!");

			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//***************************************
	// NUMBER 9
	public static void new_product() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner product = new Scanner(System.in);
			System.out.print("Please enter the new product's name: ");
			String product_name = product.nextLine();
			System.out.print("Please enter the product's description: ");
			String product_description = product.nextLine();
			System.out.print("Please enter new product's price: ");
			double product_price = product.nextDouble();

			String insertQuery = "insert into product (name, description, price) values(\"" + product_name + "\" ,\""
					+ product_description + "\" ,\"" + product_price + "\")";
			statement.executeUpdate(insertQuery);
			System.out.println("\nProcess completed!");

			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//**************************************************************
	// NUMBER 10
	public static void add_in_store() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner product = new Scanner(System.in);
			System.out.print("Please enter the p id: ");
			int pid = product.nextInt();
			System.out.print("Please enter the b id: ");
			int bid = product.nextInt();
			System.out.print("Please enter the quantity: ");
			int quantity = product.nextInt();

			String selectQuery = "(select * from storing where p_id=\"" + pid + "\" and b_id=\"" + bid + "\")";
			ResultSet rs = statement.executeQuery(selectQuery);
			if (rs.next()) {
				String updateQuery = ("UPDATE storing SET stock_quantity=stock_quantity+\"" + quantity
						+ "\" WHERE p_id=\"" + pid + "\" AND b_id=\"" + bid + "\"");
				statement.executeUpdate(updateQuery);
			} else {
				String insertQuery = "insert into storing (p_id, b_id, stock_quantity) values(\"" + pid + "\" ,\"" + bid
						+ "\" ,\"" + quantity + "\")";
				statement.executeUpdate(insertQuery);
			}
			System.out.println("\nProcess completed!");

			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//*********************************************
	// Number 11
	public static void phone_numbers() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner phone = new Scanner(System.in);
			System.out.print("Please enter the GSM number:");
			String gsm = phone.nextLine();

			String selectQuery = "select * from customer where phone_number = \"" + gsm + "\" ";
			ResultSet rs = statement.executeQuery(selectQuery);
			if (rs.next()) {
				System.out.println("\nThe Customer Informations:\n ");
				System.out.println("C.id: " + rs.getString("c_id") + " | Name: " + rs.getString("name") + " | Surname:"
						+ rs.getString("surname") + " | Address: " + rs.getString("address") + " | Phone Number: "
						+ rs.getString("phone_number"));
			} else {
				System.out.println("The phone number you entered was not found in the system.");
			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

//****************************************************
	// NUMBER 12
	public static void delete_customer() {
		try {
			establishConnection();
			Statement statement = connection.createStatement();

			Scanner customer = new Scanner(System.in);
			System.out.print("Please enter the customer id: ");
			int c_id = customer.nextInt();
			System.out.println("\nDeleting process continues...:\n");

			String delete_ordering = "delete from ordering where c_id = \"" + c_id + "\"";
			statement.executeUpdate(delete_ordering);

			String delete_customer = "select * from customer where c_id=\"" + c_id + "\"";
			ResultSet rs = statement.executeQuery(delete_customer);

			if (rs.next()) {
				if (rs.getInt("c_id") == c_id) {
					String deleteQuery = ("delete from customer where c_id=\"" + c_id + "\"");
					statement.executeUpdate(deleteQuery);
					System.out.println("Process completed!");
				} else {
				}
			} else {
				System.out.println("WARNING! There is no customer with the given customer id.");
			}
			rs.close();
			statement.close();
			closeConnection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("WELCOME TO FoodHorse Supermarket!\n");
		Scanner numbers = new Scanner(System.in);

		while (true) {
			System.out.println("***************************************************************\n");
			System.out.print("(1) Customer registration\r\n" + "(2) Buying a product\r\n" + "(3) List customers\r\n"
					+ "(4) List a customer's purchases\r\n" + "(5) List a customer's most recent purchases\r\n"
					+ "(6) List branches\r\n" + "(7) List a branch’s stock\r\n" + "(8) Add new branch store\r\n"
					+ "(9) Add new product\r\n" + "(10) Add product to branch’s stock\r\n"
					+ "(11) Search customer by their phone number\r\n" + "(12) Remove a user from system\r\n"
					+ "(13) Exit\r\n\n" + "PLEASE CLICK ONE BUTTON: ");
			int select_number = numbers.nextInt();
			if (select_number == 1) {
				registration();
			} else if (select_number == 2) {
				ordering();
			} else if (select_number == 3) {
				list_of_customers();
			} else if (select_number == 4) {
				purchases_of_a_customer();
			} else if (select_number == 5) {
				last_five_purchases();
			} else if (select_number == 6) {
				list_of_branches();
			} else if (select_number == 7) {
				branch_stock();
			} else if (select_number == 8) {
				new_branch();
			} else if (select_number == 9) {
				new_product();
			} else if (select_number == 10) {
				add_in_store();
			} else if (select_number == 11) {
				phone_numbers();
			} else if (select_number == 12) {
				delete_customer();
			} else if (select_number == 13) {
				System.out.println("BYE!");
				break;
			}

		}
	}
}