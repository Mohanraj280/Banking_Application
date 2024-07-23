package com.mohan.CustomerServiceDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mohan.CustomerService.CustomerService;


public class CustomerServiceDAO {
	public int storeTransaction(CustomerService cusSer) throws ClassNotFoundException {
		
	    String sql = "INSERT INTO transactions (transaction_id, account_number, actions, transaction_date, amount) VALUES ( ?, ?, ?, ?, ?)";
	       int result = 0;
	      
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, cusSer.getTransactionId());
	            ps.setString(2, cusSer.getAccountNumber());
	            ps.setString(3, cusSer.getActions());
	            ps.setDate(4, new Date(cusSer.getTransactionDate().getTime()));
	            ps.setBigDecimal(5, cusSer.getAmount());
	           
	            
	            System.out.println(ps);
	            result = ps.executeUpdate();
	            
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
			
		}
	
	
	public List<CustomerService> getAllTransactions(String accountNumber) {
        List<CustomerService> transactions = new ArrayList<>();
        try {
        	Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");		
            String query = "SELECT * FROM transactions where account_number = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1,accountNumber);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                CustomerService c = new CustomerService();
                c.setTransactionId(rs.getString("transaction_id"));
                c.setAccountNumber(rs.getString("account_number"));
                c.setActions(rs.getString("actions"));
                c.setTransactionDate(rs.getTimestamp("transaction_date"));
                c.setAmount(rs.getBigDecimal("amount"));
                transactions.add(c);
            }
            rs.close();
            pst.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
	
	 public List<CustomerService> getLast10Transactions(String accountNumber) {
	        List<CustomerService> transactions = new ArrayList<>();
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
	            String query = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC LIMIT 10";
	            PreparedStatement pst = con.prepareStatement(query);
	            pst.setString(1, accountNumber);

	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	                CustomerService c = new CustomerService();
	                c.setTransactionId(rs.getString("transaction_id"));
	                c.setAccountNumber(rs.getString("account_number"));
	                c.setActions(rs.getString("actions"));
	                c.setTransactionDate(rs.getTimestamp("transaction_date"));
	                c.setAmount(rs.getBigDecimal("amount"));
	                transactions.add(c);
	            }
	            rs.close();
	            pst.close();
	            con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return transactions;
	    }

}
