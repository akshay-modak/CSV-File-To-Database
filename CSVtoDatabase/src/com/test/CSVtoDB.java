package com.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author akshay.modak
 *
 */
//Main Class
public class CSVtoDB {

	//Insert Query
	private static final String insertQuery="INSERT INTO STUDENT (ROLLNO,NAME,CITY,MARKS) VALUES(?,?,?,?) ";
	//this method stores student object details into database table
	public static boolean loadStudentDetailsIntoTable(Student s)
	{
		boolean check=false;
		int k=0;
		Connection con=null;
		PreparedStatement ps=null;
		try
		{
				con=DBConn.getConn();
				ps=con.prepareStatement(insertQuery);
				ps.setString(1, s.getStudRollNo());
				ps.setString(2, s.getStudName());
				ps.setString(3, s.getStudCity());
				ps.setFloat(4, s.getStudMarks());
				k=ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(k>0)
			check=true;
			
		return check;
	}
	public static void main(String[] args) {
		//ArrayList of Student Class Object
		List<Student>studentDetails=new ArrayList<Student>()	;
		File f=new File("src/com/config/StudentDetails.csv");
		String line="";
		String[]student=new String[4];
		//Try with resources block
		try(BufferedReader br =new BufferedReader(new FileReader(f)))
		{
			while((line=br.readLine())!=null)
			{
				//This condition skip the first header line of CSV file
				if(!line.contains("marks"))
				{
				Student s=new Student();
				student=line.split(",");
				s.setStudRollNo(student[0]);
				s.setStudName(student[1]);
				s.setStudCity(student[2]);
				s.setStudMarks(Float.parseFloat(student[3]));
				studentDetails.add(s);
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("==========CSV DATA PRINTING INTO CONSOLE===============");
		//Student object iterating using forEach method
		studentDetails.forEach(stu->{
			
			//Printing each student field into console
			System.out.println("Student RollNo :"+stu.getStudRollNo());
			System.out.println("Student Name:"+stu.getStudName());
			System.out.println("Student City:"+stu.getStudCity());
			System.out.println("Student Marks :"+stu.getStudMarks());
			System.out.println();
			System.out.println("========================================================");
			System.out.println();
			
			
		});
		int studentCount=0;
		//Each student object passing to the loadStudentDetailsIntoTable(Student s) method which stored 
		// into database one by one object
		for(Student stu:studentDetails)
		{
			if(loadStudentDetailsIntoTable(stu))
			{
				//count how many students record saved in the database
				studentCount++;
			}
		}
		if(studentCount>0)
		System.out.println("Successfully "+studentCount+" Student Details Saved Successfully");
		else
			System.out.println("Data not Successfully saved into database ");
		
		System.out.println("=============Printing Student Having Greater than 90 Marks=========");
		
		//Stream API filter which find the student details who is having more than 90 Marks
		 String str=studentDetails
		.stream()
		.filter(marks->marks.getStudMarks()>90)
		.collect(Collectors.toList()).toString();
		
		System.out.println("Highest Student Details = : "+str);
		
	}

}
