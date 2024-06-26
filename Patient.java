package com.railworld.Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {

	private Connection connection;
	
	private Scanner scanner;
	
	
	public Patient(Connection connection, Scanner scanner){
		
		this.connection = connection;
		this .scanner = scanner;
		
		
	}
	public void addPatient() {
		System.out.println("Enter Patient name");
		String name = scanner.next();
		System.out.println("enter patient age");
		int  age = scanner.nextInt();
		System.out.println("Enter patient Gender ");
		String gender= scanner.next();
		
		
		try {
		String query= "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1,name);
		preparedStatement.setInt(2, age);
		preparedStatement.setString(3, gender);
		
		
		int affectedRows= preparedStatement.executeUpdate();
		if(affectedRows>0) {
			System.out.println("patient added Successfelly");
		}else {
			System.out.println("Failed to add patient");
		}
		
		
		
		
		
		
		
		
	}catch (SQLException e){
		e.printStackTrace();
}
	}
	public void viewPatient() {
		
		String query= "SELECT * FROM patients";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			ResultSet resultSet= preparedStatement.executeQuery();
			
			System.out.print("Patient :");
			System.out.println("+------------+-------------------------+---------- +----------------+");
			System.out.println("| patient ID | Name                    | Age       | Gender         |");
			System.out.println("+------------+-------------------------+-----------+----------------+ ");
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age=resultSet.getInt("age");
				String gender=resultSet.getString("gender");
				System.out.printf("|%-12s|%-20s|%-10s|%-12s\n", id, name, age, gender);
				System.out.println("+------------+-------------------------+----------+----------------+");
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	public boolean getPatientbyid(int id) {
		String query= "Select * from patients WHERE  id= ?";
		try {
			PreparedStatement preparedStatement= connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()){
				return true;
			}else {
				return false;
			}
				
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
