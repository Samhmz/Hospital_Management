package com.railworld.Hospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;

        try {
            // JDBC URL for MySQL
            String url = "jdbc:mysql://localhost:3306/hospital";

            // Database credentials
            String username = "root";
            String password = "hamzamysql@123";

            // Establish connection
            connection = DriverManager.getConnection(url, username, password);

            // Connection successful
            System.out.println("Connected to the database!");

            // Do something with the connection...
        } catch (SQLException e) {
            // Connection failed
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        } 
                Scanner scanner = new Scanner(System.in);
                Patient patient=new Patient(connection, scanner);
				Doctor doctor=new Doctor(connection);
				while(true) {
					System.out.println(" HOSPITAL MANAGEMENT SYSTEM");
					System.out.println("1. Add Patient");
					System.out.println("2. View Patient");
					System.out.println("3. View Doctor");
					System.out.println("4. Book Appointment");
					System.out.println("5. Exit");
					System.out.println("6. Enter your choice");
					int choice= scanner.nextInt();
					
					switch(choice) {
					case 1:
						//Add patient
						patient.addPatient();
						System.out.println();
						break;
					case 2:
						//view patient
						patient.viewPatient();
						System.out.println();
						break;
					case 3:
						//viw doctor
						doctor.viewDoctor();
						System.out.println();
						break;
					case 4:
						//book appointment
						bookAppointment(patient, doctor, connection, scanner);
						System.out.println();
						break;
					case 5: 
						System.out.println("THANKYOU FOR USING !!!");
						
					       return;
					 default:
						 System.out.println("Enter valid choice");
					}
				
        		}
    }
                
        		
        		
        		
            
        	
        	
        public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        	
        	System.out.println("Enter Patient ID");
        	int patientid=scanner.nextInt();
        	System.out.println(" Enter Doctor Id ");
        	int doctorid=scanner.nextInt();
        	System.out.println("Enter Appointment date (YYYY-MM-DD): ");
        	String appointmentDate=scanner.next();
        	if(patient.getPatientbyid(patientid) && doctor.getDoctorbyid(doctorid)) {
        		if(checkDoctorAvailability(doctorid,appointmentDate, connection, scanner )) {
        			String appointmentQuery= "INSERT INTO appointments(patient_id, doctor_id,appointment_date) VALUES(?,?,?)";
        			try {
        				PreparedStatement preparedStatement=connection.prepareStatement(appointmentQuery);
        				preparedStatement.setInt(1, patientid);
        				preparedStatement.setInt(2, doctorid);
        				preparedStatement.setString(3,appointmentDate);
        				int rowsAffected= preparedStatement.executeUpdate();
        				if(rowsAffected>0) {
        					System.out.println("Appointment Booked");
        				}else {
        					
        					System.out.println("Failed to Booked Appointment");
        				}
        				
        			}catch(SQLException e) {
        				e.printStackTrace();
        			}
        		}else {
        			System.out.println("Sorry Doctor is not Available on this date ");
        		}
        		
        	}else {
        		System.out.println("Doctor or patient doesnot exist");
        	}
        	
        } 
        public static boolean checkDoctorAvailability(int doctorid, String appointmentDate ,Connection connection, Scanner scanner){
        	String query= "SELECT COUNT(*) FROM appointments WHERE doctor_id=? AND appointment_date=?";
        	try {
        		PreparedStatement preparedStatement=connection.prepareStatement(query);
        		preparedStatement.setInt(1, doctorid);
        		preparedStatement.setString(2, appointmentDate);
        		ResultSet resultSet=preparedStatement.executeQuery();
        		if(resultSet.next()) {
        			int count= resultSet.getInt(1);
        			if(count==0) {
        				return true;
        				
        			}else {
        				return false;
        			}
        			
        		}
        		
        		
        	}catch(SQLException e) {
        		e.printStackTrace();
        	}
        	return false;
        	
        }
        	
        }
