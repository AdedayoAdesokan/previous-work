public class Patient implements HospitalPersonnel {
	private String name;
	private int id;
	private String address;
	private Doctor doctor;
	
	/*
	 * Creates a patient with a given name, id number, and street address
	 * @param name the patient's name
	 * @param id the patient's id
	 * @param address the patient's address
	 */
	public Patient(String name, int id, String address) {
		this.name = name;
		this.id = id;
		this.address = address;
	}
	
	/*
	 * Update the value of doctor
	 * @param doctor the doctor you want to assign this patient to
	 */
	public void assignDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	/*
	 * Gets the doctor's name
	 * @return the name of the doctor
	 */
	public String getDoctor() {
		return doctor.getName();
	}
	
	/*
	 * Gets the patient's name
	 * @return the name of the patient
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Gets the patient's id number
	 * @return the id of the patient
	 */
	public int getID() {
		return id;
	}
	
	/*
	 * Gets the patient's street address
	 * @return the address of the patient
	 */
	public String getAddress() {
		return address;
	}
}