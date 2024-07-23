package com.mohan.model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mohan.model.DAO.DatabaseConnection;
import com.mohan.model.Customer;
import com.mohan.model.Mohan;


public class CustomerDAO {
	public int registerCustomer(Customer cus) throws ClassNotFoundException {
		
		String sql = "INSERT INTO customersregister (fullName, address, mobileNo, email, accountType, initialBalance, dob, idProof, aadharNo, gender, accountNumber, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
       int result = 0;
      
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cus.getFullName());
            ps.setString(2, cus.getAddress());
            ps.setString(3, cus.getMobileNo());
            ps.setString(4, cus.getEmail());
            ps.setString(5, cus.getAccountType());
            ps.setBigDecimal(6, cus.getInitialBalance());
            ps.setDate(7,new Date(cus.getDob().getTime()));
            ps.setString(8, cus.getIdProof());
            ps.setString(9, cus.getAadharNo());
            ps.setString(10, cus.getGender());
            ps.setString(11, cus.getAccountNumber());
            ps.setString(12, cus.getPassword());
            
            System.out.println(ps);
            result = ps.executeUpdate();
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
        
			Connection con=null;
			try {
				con = DatabaseConnection.databaseConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
            String query = "SELECT * FROM customersregister";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Customer c = new Customer();
                c.setFullName(rs.getString("fullName"));
                c.setAddress(rs.getString("address"));
                c.setMobileNo(rs.getString("mobileNo"));
                c.setEmail(rs.getString("email"));
                c.setAccountType(rs.getString("accountType"));
                c.setInitialBalance(rs.getBigDecimal("initialBalance"));
                c.setDob(rs.getDate("dob"));
                c.setIdProof(rs.getString("idProof"));
                c.setAadharNo(rs.getString("aadharNo"));
                c.setGender(rs.getString("gender"));
                c.setAccountNumber(rs.getString("accountNumber"));
                c.setPassword(rs.getString("password"));
                customers.add(c);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
	
	
	
	  public Customer getCustomerByAccountNumber(String accountNumber) throws ClassNotFoundException {
	        Customer customer = null;
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");		
	            
				String query = "SELECT * FROM customersregister WHERE accountNumber = ?";
	            ps = con.prepareStatement(query);
	            ps.setString(1, accountNumber);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                customer = new Customer();
	                customer.setFullName(rs.getString("fullName"));
	                customer.setAddress(rs.getString("address"));
	                customer.setMobileNo(rs.getString("mobileNo"));
	                customer.setEmail(rs.getString("email"));
	                customer.setAccountType(rs.getString("accountType"));
	                customer.setInitialBalance(rs.getBigDecimal("initialBalance"));
	                customer.setDob(rs.getDate("dob"));
	                customer.setIdProof(rs.getString("idProof"));
	                customer.setAadharNo(rs.getString("aadharNo"));
	                customer.setGender(rs.getString("gender"));
	                customer.setAccountNumber(rs.getString("accountNumber"));
	                customer.setPassword(rs.getString("password"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (ps != null) ps.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return customer;
	    }
	  
	  public boolean updateUser(Customer customer) {
	        boolean rowUpdated = false;
	        Connection con = null;
	        PreparedStatement ps = null;

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
	            ps = con.prepareStatement("UPDATE customersregister SET fullName = ?, address = ?, mobileNo = ?, email = ?, dob = ?, idProof = ?, aadharNo = ?, gender = ? WHERE accountNumber = ?");
	            
	            ps.setString(1, customer.getFullName());
	            ps.setString(2, customer.getAddress());
	            ps.setString(3, customer.getMobileNo());
	            ps.setString(4, customer.getEmail());
	            ps.setDate(5, new java.sql.Date(customer.getDob().getTime())); // Convert util.Date to sql.Date
	            ps.setString(6, customer.getIdProof());
	            ps.setString(7, customer.getAadharNo());
	            ps.setString(8, customer.getGender());
	            ps.setString(9, customer.getAccountNumber());

	            rowUpdated = ps.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) {
	                    ps.close();
	                }
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return rowUpdated;
	    }


	public boolean deleteAccount(String acc) throws SQLException {
		
		boolean rowDeleted=false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
            ps = con.prepareStatement("DELETE from customersregister WHERE accountNumber = ?");
            ps.setString(1, acc);
            rowDeleted = ps.executeUpdate()>0;
            
		
	}catch(Exception e)
        {
        	e.printStackTrace();
        }
	return rowDeleted;
	}
	
	public List<Customer> searchCustomersByAccount(String name) {
        List<Customer> customers = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
            ps = con.prepareStatement("SELECT * FROM customersregister WHERE accountNumber = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                Customer customer = new Customer();
                customer.setFullName(rs.getString("fullName"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobileNo"));
                customer.setEmail(rs.getString("email"));
                customer.setAccountType(rs.getString("accountType"));
                customer.setInitialBalance(rs.getBigDecimal("initialBalance"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("idProof"));
                customer.setAadharNo(rs.getString("aadharNo"));
                customer.setGender(rs.getString("gender"));
                customer.setAccountNumber(rs.getString("accountNumber"));
                customer.setPassword(rs.getString("password"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customers;
    }
	
	public Customer getByAccountNumber(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
            ps = con.prepareStatement("SELECT * FROM customersregister WHERE accountNumber = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setFullName(rs.getString("fullName"));
                customer.setAddress(rs.getString("address"));
                customer.setMobileNo(rs.getString("mobileNo"));
                customer.setEmail(rs.getString("email"));
                customer.setAccountType(rs.getString("accountType"));
                customer.setInitialBalance(rs.getBigDecimal("initialBalance"));
                customer.setDob(rs.getDate("dob"));
                customer.setIdProof(rs.getString("idProof"));
                customer.setAadharNo(rs.getString("aadharNo"));
                customer.setGender(rs.getString("gender"));
                customer.setAccountNumber(rs.getString("accountNumber"));
                customer.setPassword(rs.getString("password"));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return customer;
    }

	

    public boolean updatePassword(Customer customer) {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
            ps = con.prepareStatement("UPDATE customersregister SET password = ? WHERE accountNumber = ?");
            
            ps.setString(1, customer.getPassword());
            ps.setString(2, customer.getAccountNumber());

            rowUpdated = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rowUpdated;
    }

    
    public void updateCustomer(Customer customer) {
        String updateQuery = "UPDATE customersregister SET initialBalance = ? WHERE accountNumber = ?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {

            ps.setBigDecimal(1, customer.getInitialBalance());
            ps.setString(2, customer.getAccountNumber());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer balance updated successfully.");
            } else {
                System.out.println("No customer found with the given account number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


