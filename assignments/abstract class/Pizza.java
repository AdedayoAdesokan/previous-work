public abstract class Pizza {

	/*
	 * Uses primitive operations to make a pizza from scratch
	 */
	public void makeFromScratch() {
		getName();
		makeCrust();
		addToppings();
		preHeatOven();
		cook();
	}

	/*
	 * Prints the name of the pizza
	 */
	public abstract void getName();

	/*
	 * Prints the type of crust for the pizza
	 */
	public abstract void makeCrust();

	/*
	 * Prints the toppings being added to the pizza
	 */
	public abstract void addToppings();

	/*
	 * Prints the degree to bake the pizza at
	 */
	public abstract void preHeatOven();

	/*
	 * Prints the time needed for the pizza to cook
	 */
	public abstract void cook();

}
