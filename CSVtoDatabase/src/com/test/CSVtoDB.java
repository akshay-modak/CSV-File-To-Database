package com.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
public class CSVtoDB {

	private static final String insertQuery="INSERT INTO STUDENT (ROLLNO,NAME,CITY,MARKS) VALUES(?,?,?,?) ";
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
		
		List<Student>studentDetails=new ArrayList<Student>()	;
		File f=new File("src/com/config/StudentDetails.csv");
		String line="";
		String[]student=new String[4];
		try(BufferedReader br =new BufferedReader(new FileReader(f)))
		{
			while((line=br.readLine())!=null)
			{
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
		studentDetails.forEach(stu->{
			
			System.out.println("Student RollNo :"+stu.getStudRollNo());
			System.out.println("Student Name:"+stu.getStudName());
			System.out.println("Student City:"+stu.getStudCity());
			System.out.println("Student Marks :"+stu.getStudMarks());
			System.out.println();
			System.out.println("========================================================");
			System.out.println();
			
			
		});
		int studentCount=0;
		for(Student stu:studentDetails)
		{
			if(loadStudentDetailsIntoTable(stu))
			{
				studentCount++;
			}
		}
		if(studentCount>0)
		System.out.println("Successfully "+studentCount+" Student Details Saved Successfully");
		else
			System.out.println("Data not Successfully saved into database ");
		
		System.out.println("=============Printing Student Having Greater than 90 Marks=========");
		
		 String str=studentDetails
		.stream()
		.filter(marks->marks.getStudMarks()>90)
		.collect(Collectors.toList()).toString();
		
		System.out.println("Highest Student Details = : "+str);
		
	}

}
