package com.railworld.Hospital;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
	private Connection connection;
	
	
	
	
	public Doctor(Connection connection){
		
		this.connection = connection;
		
		
		
	}
	

	public void viewDoctor() {
	
		String query= "SELECT * FROM doctors";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet= preparedStatement.executeQuery();
			
			System.out.print("Doctor :");
			System.out.println("+------------+-------------------------------+-----------------------------+");
			System.out.println("| Doctor  ID |       Name                    |   Specialization            |");
			System.out.println("+------------+-------------------------------+-----------------------------+");
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String specialization=resultSet.getString("specialization");
				System.out.printf("|%-12s|%-30s|%-29s\n" ,id, name, specialization);
				System.out.println("+------------+-------------------------------+-----------------------------+");
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	public boolean getDoctorbyid(int id) {
		String query= "Select * from doctors WHERE  id= ?";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()){
				return true;
			} else {
				return false;
			}
				
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
		
		
	}
	
	
}
