package fujitsu2020;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
//import java.io.IOException;
//import java.io.ObjectInputStream;

public class Employee {
		int id;
		String lastName, firstName;
		
		///Users/jiaweisun/Downloads/employees.dat
	public static void main(String[] args)throws Exception { 
		List<Employee> employeeList = new ArrayList<Employee>();
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("File path is: ");
//		String userInput = scanner.nextLine();
		File file = new File(args[0]);  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
//		System.out.println(args[0]);
		String st; 
		while ((st = br.readLine()) != null) {
			if(st.charAt(0) != '#') {
				Employee emp = process(st);
				employeeList.add(emp);
			}
		} 
//		sort(employeeList, lowestIndex, HighestIndex-1);
        Collections.sort(employeeList, new SortbyID()); 

		System.out.println("Processing by employee number...");
		for(Employee e : employeeList) {
			System.out.println(e.id+","+e.firstName+" "+e.lastName);
		}
		
		Collections.sort(employeeList, new SortbyLastName());
		
		System.out.println("\nProcessing by last (family) name...");
		for(Employee e : employeeList) {
			System.out.println(e.id+","+e.firstName+" "+e.lastName);
		}
	}

	// takes in a line and returns an Employee object
	public static Employee process(String line) {
			Employee emp = new Employee();
			String[] processedList = line.split(",");
			String ID = processedList[0];
			emp.id = Integer.parseInt(ID);
			processedList[1] = processedList[1].trim();
			String[] processedName = processedList[1].split(" ");
			if(processedName.length != 2){
				processedName = trimm(processedName);
			}
			emp.lastName = processedName[1];
			emp.firstName = processedName[0];
			return emp;
	}
	public static String[] trimm(String[] processedName) {
		String[] FLName = new String[2];
		int counter = 0;
		for(int i = 0; i<processedName.length; i++) {
			
			if((!processedName[i].equals(" ")) || processedName[i].matches("^[a-zA-Z]*$")) {
				FLName[counter] = processedName[i];
				counter++;
			}
			if(counter == 2) {
				break;
			}
//			System.out.println(Arrays.toString(FLName));
		}
			
//			System.out.println(Arrays.toString(FLName));
			return FLName;
		}
}

class SortbyID implements Comparator<Employee> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    public int compare(Employee a, Employee b) 
    { 
        return a.id - b.id; 
    } 
} 
  
class SortbyLastName implements Comparator<Employee> 
{ 
    // Used for sorting in ascending order of 
    // roll name 
    public int compare(Employee a, Employee b) 
    { 
        return a.lastName.compareTo(b.lastName); 
    } 
} 


