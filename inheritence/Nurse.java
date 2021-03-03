import java.util.ArrayList;

public class Nurse extends Employee {
	private ArrayList<Doctor> doctors;
	
	/*
	 * Creates a nurse with a given name, id number, salary, and street address
	 * @param name the nurse's name
	 * @param id the nurse's id
	 * @param salary the nurse's salary
	 * @param address the nurse's address
	 */
	public Nurse(String name, int id, double salary, String address) {
		super(name, id, salary, address);
		doctors = new ArrayList<>();
	}
	
	/*
	 * Adds a doctor to the list of doctors
	 * @param doctor the doctor you want to add
	 */
	public void assignDoctor(Doctor doctor) {
		doctors.add(doctor);
	}
	/*
	 * Gets a list of each doctor's name in the ArrayList
	 * @return a list of doctors' names
	 */
	public String getDoctors() {
		String list = "";
		for (Doctor doctor: doctors) {
			if (doctor == doctors.get(0)) {
				list = doctor.getName();
			}
			else {
				list = list + "\n" + doctor.getName();
			}
		}
		return list;
	}
}
