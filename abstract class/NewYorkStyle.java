public class NewYorkStyle extends Pizza {

	/*
	 * Creates a New York Style pizza
	 */
	public NewYorkStyle() {
	}

	/*
	 * Prints the name of the pizza
	 */
	public void getName() {
		System.out.println("To make New York Style pizza:");
	}

	/*
	 * Prints the type of crust for the pizza
	 */
	public void makeCrust() {
		System.out.println("  Make stuffed crust pizza dough.");
	}

	/*
	 * Prints the toppings required by the pizza
	 */
	public void addToppings() {
		System.out.println("  Add salami, peperoni, bacon, chicken, and anchovies.");
	}

	/*
	 * Prints the degree to bake the pizza
	 */
	public void preHeatOven() {
		System.out.println("  Preheat oven to 315 degrees.");
	}

	/*
	 * Prints the time needed for the pizza to cook
	 */
	public void cook() {
		System.out.println("  Bake for 60 minutes.");
	}
}