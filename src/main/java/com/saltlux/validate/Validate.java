package com.saltlux.validate;

import java.util.Scanner;

public class Validate {

	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			String sex = checkStringNonSpace("enter : ", "err");
			System.out.println(sex);
			// check continue program
			System.out.println("Press any key to continue, n to exit !");
			String confirmString = sc.nextLine();
			if (confirmString.equals("n") || confirmString.equals("N")) {
				System.out.println("Bye !");
				return;
			}
		}
	}

	public static int checkNumber(String message, String err) {
		// create number
		int number = 0;
		// create checkNumber
		boolean checkNumber = false;
		// input number
		while (checkNumber == false) {
			System.out.println(message);
			try {
				// get number
				number = Integer.parseInt(sc.nextLine());
				// check if number is avaible
				if (number < 1) {
					System.out.println(err);
				} else {
					checkNumber = true;
				}
			} catch (Exception e) {
				System.out.println(err);
			}
		}
		return number;
	}

	public static int checkOption(int min, int max) {
		// create option
		int option = 0;
		// create checkOption
		boolean checkOption = false;
		// input option
		while (checkOption == false) {
			System.out.println("Enter option :");
			try {
				// get option
				option = Integer.parseInt(sc.nextLine());
				// check if option is avaible
				if (option < min || option > max) {
					System.out.println("Must be integer bigger than " + (min - 1) + " and less than " + (max + 1)
							+ " ! Input again !");
				} else {
					checkOption = true;
				}
			} catch (Exception e) {
				System.out.println("Must be integer bigger than " + (min - 1) + " and less than " + (max + 1)
						+ " ! Input again !");
			}
		}
		return option;
	}

	public static String checkNonNumber(String message, String err) {
		// create input
		String input = "";
		// create checkString
		boolean checkNonNumber = false;
		// create regex
		String regex = "^([A-Za-z]+ )+[A-Za-z]+$|^[A-Za-z]+$";
		// input
		while (checkNonNumber == false) {
			System.out.println(message);
			try {
				// get input
				input = sc.nextLine();
				// check if input is avaible
				if (!input.matches(regex)) {
					System.out.println(err);
				} else {
					checkNonNumber = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return input;
	}

	public static String checkString(String message, String err) {
		// create input
		String input = "";
		// create checkString
		boolean checkString = false;
		// create regex
		String regex = "^([A-Za-z0-9]+ )+[A-Za-z0-9]+$|^[A-Za-z0-9]+$";
		// input
		while (checkString == false) {
			System.out.println(message);
			try {
				// get input
				input = sc.nextLine();
				// check if input is avaible
				if (!input.matches(regex)) {
					System.out.println(err);
				} else {
					checkString = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return input;
	}

	public static String checkStringNonSpace(String message, String err) {
		// create input
		String input = "";
		// create checkString
		boolean checkString = false;
		// create regex
		String regex = "^([A-z0-9])*[^\\s]\\1*$";
		// input
		while (checkString == false) {
			System.out.println(message);
			try {
				// get input
				input = sc.nextLine();
				// check if input is avaible
				if (!input.matches(regex)) {
					System.out.println(err);
				} else {
					checkString = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return input;
	}
	
	public static String checkStringNumberNonSpace(String message, String err) {
		// create input
		String input = "";
		// create checkString
		boolean checkString = false;
		// create regex
		String regex = "^([0-9])*[^\\s]\\1*$";
		// input
		while (checkString == false) {
			System.out.println(message);
			try {
				// get input
				input = sc.nextLine();
				// check if input is avaible
				if (!input.matches(regex)) {
					System.out.println(err);
				} else {
					checkString = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return input;
	}

	public static double checkDouble(String message, String err) {
		// create number
		double number = 0;
		// create checkDouble
		boolean checkDouble = false;
		// input number
		while (checkDouble == false) {
			System.out.println(message);
			try {
				// get number
				number = Double.parseDouble(sc.nextLine());
				// check if number is avaible
				if (number <= 0) {
					System.out.println(err);
				} else {
					checkDouble = true;
				}
			} catch (Exception e) {
				System.out.println(err);
			}
		}
		return number;
	}

	public static String checkEmail(String message, String err) {
		// create input
		String input = "";
		// create checkEmail
		boolean checkEmail = false;
		// create regex
		String regex = "^([A-Za-z0-9]+ )+[A-Za-z0-9]+$|^[A-Za-z0-9]+$";
		// input
		while (checkEmail == false) {
			System.out.println(message);
			try {
				// get input
				input = sc.nextLine();
				// check if input is avaible
				if (!input.matches(regex)) {
					System.out.println(err);
				} else {
					checkEmail = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return input;
	}

	public static String checkSex(String message, String err) {
		// create input
		String input = "";
		// create checkString
		boolean checkString = false;
		
		// input
		while (checkString == false) {
			System.out.println(message);
			try {
				// get input
				input = sc.nextLine();
				// check if input is avaible
				if (!input.equalsIgnoreCase("male") && !input.equalsIgnoreCase("female")) {
					System.out.println(err);
				} else {
					checkString = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return input.toLowerCase();
	}
}