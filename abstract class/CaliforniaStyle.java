public class CaliforniaStyle extends Pizza {

	/*
	 * Creates a California Style pizza
	 */
	public CaliforniaStyle() {
	}

	/*
	 * Prints the name of the pizza
	 */
	public void getName() {
		System.out.println("To make California Style pizza:");
	}

	/*
	 * Prints the type of crust for the pizza
	 */
	public void makeCrust() {
		System.out.println("  Make thin pizza dough.");
	}

	/*
	 * Prints the toppings required by the pizza
	 */
	public void addToppings() {
		System.out.println("  Add sausage, peperoni, and pineapple.");
	}

	/*
	 * Prints the degree to bake the pizza
	 */
	public void preHeatOven() {
		System.out.println("  Preheat oven to 375 degrees.");
	}

	/*
	 * Prints the time needed for the pizza to cook
	 */
	public void cook() {
		System.out.println("  Bake for 30 minutes.");
	}
}
