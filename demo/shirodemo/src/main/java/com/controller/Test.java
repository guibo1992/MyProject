package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Test {
	public static void main(String[] args) {
		String sql="select * from user";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shiro","root","123");
			PreparedStatement  ps=con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println(rs.getInt(1)+rs.getString(2)+rs.getInt(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
