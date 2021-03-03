import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EmployeeTester {
	public static void main(String[] args) {
		
		//Create a few Employee objects
		Doctor doc1 = new Doctor("Mike", 10001, 160000, "1234 Bernal Way");
		Doctor doc2 = new Doctor("Kim", 10002, 160000, "1211 Ginger Circle");
		Nurse nurse1 = new Nurse("Joe", 20001, 80000, "1017 Brick Lane");
		Nurse nurse2 = new Nurse("Amy", 20002, 80000, "2048 Hobbit Street");
		
		//Add each employee to list1
		ArrayList<Employee> list1 = new ArrayList<>();
		list1.add(doc1);
		list1.add(nurse1);
		list1.add(doc2);
		list1.add(nurse2);
		
		//Clone each employee to a new list
		ArrayList<Employee> list2 = new ArrayList<>();
		for (Employee employee: list1) {
			list2.add(employee.clone());
		}
		
		//Print each list before we sort it (they should be the same) 
		System.out.println("Before - " + list1.toString());
		System.out.println("Before - " + list2.toString());
		System.out.println();

		//Sort each employee in list2 based on their names
		Collections.sort(list2, new Comparator<Employee>() {
			public int compare(Employee o1, Employee o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		//Print each list after sorting list2 (list2 should be sorted and list1 shouldn't)
		System.out.println("After - " + list1.toString());
		System.out.println("After - " + list2.toString());
		System.out.println();
		
		//Modify an employee in list2 to see if it's really a deep copy (the employee in list1 shouldn't be modified)
		list2.get(0).setSalary(85000);
		System.out.println(list1.get(3).toString());  //Print that employee in list1
		System.out.println(list2.get(0).toString()); //Print that employee in list2 
	}
}