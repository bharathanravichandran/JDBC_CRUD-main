package Employee;

public class Testdao {
	  public static void main(String[] args) {
	        EmployeeDao dao = new EmployeeDao();

	        // Store new employee data
	        Employee e1 = new Employee();
	        e1.setId(1);
	        e1.setName("Bharathan");
	        e1.setAddress("karur");
	        e1.setSalary(50000);
	        e1.setDept("Owner");
	        dao.storeData(e1);

	        // Retrieve all employee data
	        dao.retrieveData();

	        // Retrieve Specific data
	        dao.retrieveData(1);

	        // Update employee data
	        e1.setName("Bharathan R");
	        dao.updateData(1, e1);

	        // Retrieve updated employee data
	        dao.retrieveData(1);

	        // Delete employee data
	        dao.deleteData(1);

	        // Try to retrieve deleted employee data
	        dao.retrieveData(1);
	    }

}
