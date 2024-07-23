package com.mohan.model.DAO;

import java.sql.*;
import com.mohan.model.Mohan;

public class MohanDAO {

	public Mohan getMohan(String username) {
		Mohan m1 = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_app", "root", "admin@root");
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Admin WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				m1 = new Mohan();
				m1.setAdmin_id(rs.getInt("admin_id"));
				m1.setUsername(rs.getString("username"));
				m1.setPassword(rs.getString("password"));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return m1;
	}
}
