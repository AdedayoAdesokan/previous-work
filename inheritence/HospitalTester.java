
public class HospitalTester {
	public static void main(String[] args) {
		Doctor doc1 = new Doctor("Mike", 10001, 160000, "1234 Bernal Way");
		Nurse nurse1 = new Nurse("Joe", 20001, 80000, "1017 Brick Lane");
		nurse1.assignDoctor(doc1);
		
		Patient pat1 = new Patient("Paul", 30001, "2048 Hobbit Street");
		pat1.assignDoctor(doc1);
		doc1.addPatient(pat1);
		
		Patient pat2 = new Patient("Tim", 30002, "1211 Ginger Circle");
		pat2.assignDoctor(doc1);
		doc1.addPatient(pat2);
		
		System.out.println("Doctor: " + doc1.getName());
		System.out.println("ID Number: " + doc1.getID());
		System.out.println("Salary: " + "$" + doc1.getSalray());
		System.out.println("Address: " + doc1.getAddress());
		System.out.println("Patients:");
		System.out.println(doc1.getPatients());
		System.out.println();
		
		System.out.println("Nurse: " + nurse1.getName());
		System.out.println("ID Number: " + nurse1.getID());
		System.out.println("Salary: " + "$" + nurse1.getSalray());
		System.out.println("Address: " + nurse1.getAddress());
		System.out.println("Assigned Doctors:");
		System.out.println(nurse1.getDoctors());
		System.out.println();
		
		System.out.println("Patient: " + pat1.getName());
		System.out.println("ID Number: " + pat1.getID());
		System.out.println("Address: " + pat1.getAddress());
		System.out.println("Assigned Doctor:" + pat1.getDoctor());
		System.out.println();	
		
		System.out.println("Patient: " + pat2.getName());
		System.out.println("ID Number: " + pat2.getID());
		System.out.println("Address: " + pat2.getAddress());
		System.out.println("Assigned Doctor:" + pat2.getDoctor());
		System.out.println();	
		}
}
