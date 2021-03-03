public class PizzaTester {
	public static void main(String[] args) {
		ChicagoStyle chicago = new ChicagoStyle();
		chicago.makeFromScratch();

		System.out.println();

		CaliforniaStyle cali = new CaliforniaStyle();
		cali.makeFromScratch();

		System.out.println();

		NewYorkStyle york = new NewYorkStyle();
		york.makeFromScratch();
	}
}
