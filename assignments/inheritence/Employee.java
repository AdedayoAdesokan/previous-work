public class Employee implements HospitalPersonnel {
	private String name;
	private int id;
	private double salary;
	private String address;
	
	/*
	 * Creates an employee with a given name, id number, salary, and street address
	 * @param name the employee's name
	 * @param id the employee's id
	 * @param salary the employee's salary
	 * @param address the employee's address
	 */
	public Employee(String name, int id, double salary, String address) {
		this.name = name;
		this.id = id;
		this.salary = salary;
		this.address = address;
	}
	
	/*
	 * Updates the salary of the employee
	 * @param newSalary the updated salary
	 */
	public void setSalary(double newSalary) {
		if (newSalary > 0) {
			this.salary = newSalary;
		}
	}
	
	/*
	 * Gets the employee's salary
	 * @return the salary of the employee
	 */
	public double getSalray() {
		return salary;
	}
	
	/*
	 * Gets the employee's name
	 * @return the name of the employee
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Gets the employee's id number
	 * @return the id of the employee
	 */
	public int getID() {
		return id;
	}
	
	/*
	 * Gets the employee's street address
	 * @return the address of the employee
	 */
	public String getAddress() {
		return address;
	}
}
