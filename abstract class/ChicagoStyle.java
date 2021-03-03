public class ChicagoStyle extends Pizza {

	/*
	 * Creates a Chicago Style pizza
	 */
	public ChicagoStyle() {
	}

	/*
	 * Prints the name of the pizza
	 */
	public void getName() {
		System.out.println("To make Chicago Style pizza:");
	}

	/*
	 * Prints the type of crust for the pizza
	 */
	public void makeCrust() {
		System.out.println("  Make thick crust pizza dough.");
	}

	/*
	 * Prints the toppings required by the pizza
	 */
	public void addToppings() {
		System.out.println("  Add onions, mushroom, bell peppers and peperoni.");
	}

	/*
	 * Prints the degree to bake the pizza
	 */
	public void preHeatOven() {
		System.out.println("  Preheat oven to 400 degrees.");
	}

	/*
	 * Prints the time needed for the pizza to cook
	 */
	public void cook() {
		System.out.println("  Bake for 40 minutes.");
	}
}