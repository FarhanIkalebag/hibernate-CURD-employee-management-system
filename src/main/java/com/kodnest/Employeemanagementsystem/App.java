package com.kodnest.Employeemanagementsystem;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
	
	private static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	private static Scanner sc = new Scanner(System.in);
	
    public static void main( String[] args )
    {
    	System.out.println("<---  WELCOME TO EMPLOYEE MANAGEMENT SYSTEM  --->");
    	
    		// Choice mechanism 
    	while(true) {
    		System.out.println("Press 1 -> To Add Employee Data to Database");
    		System.out.println("Press 2 -> To Get Employee Data from Database");
    		System.out.println("Press 3 -> To Update Employee Data from Database");
    		System.out.println("Press 4 -> To Delete Employee Data from Database");
    		System.out.println("Press 5 -> To Exit from EMPLOYEE MANAGEMENT SYSTEM");
    		System.out.println("ENTER YOUR CHOICE");
    		int choice = sc.nextInt();
    		
    		switch(choice) {
    		case 1: addEmp(); break;
    		case 2: getEmp(); break;
    		case 3: updateEmp(); break;
    		case 4: deleteEmp(); break;
    		case 5: 
    			System.out.println("Exiting program");
    			factory.close();
    			return;
    		default: 
    			System.out.println("Invalid choice. Please try again...");
    		
    		}
    	}
        
    }
    
    
    
    
    // 4 method to perform CRUD operation
    
	     private static void addEmp() {
	    	 Session session = factory.openSession();
	    	 Transaction tran = session.beginTransaction();
	    	 System.out.println("Enter the Employee's Data in given manner - Name, Salary, Email");
	    	 Employee emp = new Employee(sc.next(), sc.nextInt(), sc.next());
	    	 session.persist(emp);
	    	 tran.commit();
	    	 session.close();
	    	
	    }
		 private static void getEmp() {
			 Session session = factory.openSession();
	    	 Transaction tran = session.beginTransaction();
	    	 System.out.println("Enter the id to get Data of Employee");
	    	 int id = sc.nextInt();
	    	 Employee emp = session.get(Employee.class, id);
	    	 if(emp!=null) {
	    		 System.out.println(emp);
	    		 tran.commit();
	    		 System.out.println("Employee detailed viewed successfully.");
	    	 } else {
	    		 System.out.println("There does not exist any employee with ID " + id + " to Retrive");
	    	 }
	    	 session.close();
		    	
		}
		 private static void updateEmp() {
			 Session session = factory.openSession();
	    	 Transaction tran = session.beginTransaction();
	    	 System.out.println("Enter the id --> to update Employee Data");
	    	 int id = sc.nextInt();
	    	 Employee emp = session.get(Employee.class, id);
	    	 if(emp!=null) {
	    		 System.out.println("Enter new name, salary and email.");
	    		 emp.setName(sc.next());
	    		 emp.setSalary(sc.nextInt());
	    		 emp.setEmail(sc.next());
	    		 session.persist(emp);
	    		 System.out.println("Employee updated successfully");
	    		 tran.commit();
	    	 } else {
	    		 System.out.println("There does not exist any employee with ID " + id + " to Update");
	    	 }
	    	 session.close();
		}
		 private static void deleteEmp() {
			 Session session = factory.openSession();
	    	 Transaction tran = session.beginTransaction();
	    	 System.out.println("Enter the id --> to delete Employee Data");
	    	 int id = sc.nextInt();
	    	 Employee emp = session.get(Employee.class, id);
	    	 if(emp!=null) {
	    		 session.remove(emp);
	    		 tran.commit();
	    		 System.out.println("Employee deleted successfully.");
	    	 } else {
	    		 System.out.println("There does not exist any employee with ID " + id + " to Delete");
	    	 }
	    	 session.close();
		 	
		}
		 
		 
    
    
}
