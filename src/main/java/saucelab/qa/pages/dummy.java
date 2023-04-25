package saucelab.qa.pages;

public class dummy {

	public static void main(String[] args) {
		String str = "Item total: $17.98";

		// Get the substring after the dollar sign
		String priceStr = str.substring(str.indexOf("$") + 1);

		// Convert the price string to a double value
		double price = Double.parseDouble(priceStr);

		System.out.println(price); // Output: 17.98
	}

}
