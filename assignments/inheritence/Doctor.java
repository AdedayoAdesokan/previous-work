import java.util.ArrayList;

public class Doctor extends Employee{
	private ArrayList<Patient> patients;
	
	/*
	 * Creates a doctor with a given name, id number, salary, and street address
	 * @param name the doctor's name
	 * @param id the doctor's id
	 * @param salary the doctor's salary
	 * @param address the doctor's address
	 */
	public Doctor(String name, int id, double salary, String address) {
		super(name, id, salary, address);
		patients = new ArrayList<>();
	}
	
	/*
	 * Adds a patient to the list of patients
	 * @param patient the patient you want to add
	 */
	public void addPatient(Patient patient) {
		patients.add(patient);
	}
	
	/*
	 * Gets a list of each patients name in the ArrayList
	 * @return a list of patients' names
	 */
	public String getPatients() {
		String list = "";
		for (Patient patient: patients) {
			if (patient == patients.get(0)) {
				list = patient.getName();
			}
			else {
				list = list + "\n" + patient.getName();
			}
		}
		return list;
	}
}
