package Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
Connection con;
PreparedStatement pst;
ResultSet rs;
public EmployeeDao() {
try {
	//load the class driver
	Class.forName("com.mysql.cj.jdbc.Driver");
	//create a connection
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo", "root", "root");
} catch (ClassNotFoundException e) {
	e.printStackTrace();
} catch (SQLException e) {
	e.printStackTrace();
}
}

public void storeData(Employee e) {
	//inserting data into the database using prepared statement
	String sql="insert into employee(id, name, address, salary, dept) values(?, ?, ?, ?, ?)";
	try {
		pst=con.prepareStatement(sql);
		pst.setInt(1, e.getId());
		pst.setString(2, e.getName());
		pst.setString(3, e.getAddress());
		pst.setFloat(4, e.getSalary());
		pst.setString(5, e.getDept());
		pst.executeUpdate();
		System.out.println("data inserted");
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
}
public void retrieveData() {
	String str="select * from employee";
	try {
		pst=con.prepareStatement(str);
		//execute the statement
		rs=pst.executeQuery();
		System.out.println("ID \t NAME \t ADDRESS \t SALARY \t ROLE");
		while(rs.next()) {
			System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getString("address")+"\t"+rs.getFloat("salary")+" \t "+rs.getString("dept"));
		}	
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	
}
public void retrieveData(int id) {
	//retrieving specific data
    String str = "select * from employee where id = ?";
    try {
        pst = con.prepareStatement(str);
        pst.setInt(1, id);
        rs = pst.executeQuery();
        if (rs.next()) {
            System.out.println("ID:      " + rs.getInt("id"));
            System.out.println("Name:    " + rs.getString("name"));
            System.out.println("Address: " + rs.getString("address"));
            System.out.println("Salary:  " + rs.getFloat("salary"));
            System.out.println("Dept:    " + rs.getString("dept"));
        } else {
            System.out.println("Employee not found with ID: " + id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

}
public void deleteData(int id) {
	//delete the record
	 String sql = "delete from employee where id = ?";
     try {
         pst = con.prepareStatement(sql);
         pst.setInt(1, id);
         int rowsAffected = pst.executeUpdate();
         if (rowsAffected > 0) {
             System.out.println("Employee with ID " + id + " deleted");
         } else {
             System.out.println("Employee not found with ID: " + id);
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
}
public void updateData(int id,Employee e) {
	 String sql = "update employee set name = ?, address = ?, salary = ?, dept = ? where id = ?";
     try {
         pst = con.prepareStatement(sql);
         pst.setString(1, e.getName());
         pst.setString(2, e.getAddress());
         pst.setFloat(3, e.getSalary());
         pst.setString(4, e.getDept());
         pst.setInt(5, id);
         int rowsAffected = pst.executeUpdate();
         if (rowsAffected > 0) {
             System.out.println("Employee with ID " + id + " updated");
         } else {
             System.out.println("Employee not found with ID: " + id);
         }
     } catch (SQLException e1) {
         e1.printStackTrace();
     }
 	try {
		con.close();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
}
}
