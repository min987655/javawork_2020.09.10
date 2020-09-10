
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;



public class CustomerList 
{
	//1.멤버변수
	private Connection con; // 멤버변수
	private Statement stmt;
	private ResultSet rs;
	
	//1.멤버변수
//	private int custid;
//	private String name;
//	private String address;
//	private String phone;
	
	//2.배열
	private int custid2[]; // 쩜! -> 레퍼런스(연결)
	private String name2[];
	private String address2[];
	private String phone2[];
	
	//3.객체
//	class Customer2{
//		private int custid;
//		private String name;
//		private String address;
//		private String phone;
//	}	
	Customer cs;
	
	//4.객체 배열
	Customer cs2[];
	
	//생성자
	public CustomerList()
	{
		//1.변수 초기화
//		custid 	= 0;
//		name 	= "";
//		address = "";
//		phone	= "";
		
		//2.배열 초기화
		custid2  = new int[5];
		name2	 = new String[5];
		address2 = new String[5];
		phone2	 = new String[5];
		
		//3.객체 초기화
		cs = new Customer();
		
		//4.객체 배열 초기화
		cs2 = new Customer[5];//객체 아님! 배열일 뿐(공간)
		//4-1.객체 배열을 만들었으면, 
		//반드시 객체배열안에 들어갈 "객체"원소를 만들어야 된다.
		for(int i=0; i<5; i++)
			cs2[i]  = new Customer();//5개의 객체 생성
	}
	
	

	public void getConnection() 
	{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid =  "c##madang"; //c##추가
		String pwd = "c##madang"; //c##추가
	   
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공");
		}
		catch (ClassNotFoundException e) 
		{
		   e.printStackTrace();
		}
		
		try 
		{
		   System.out.println("데이터베이스 연결 준비 .....");
		   con = DriverManager.getConnection(url,userid,pwd);
		   System.out.println("데이터베이스 연결 성공");
		}
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}
	}

	public void getCustomer_DB() //하는 일이 뭐냐?
	{ 
		String query = "SELECT custid, name, address, phone  FROM customer";
		try 
		{
			stmt = con.createStatement(); //2
			rs = stmt.executeQuery(query); //3
			System.out.println("BOOK ID \tBOOK NAME \t\tPUBLISHER \t\t\tPRICE");
			
			int index=0;
			while (rs.next ()) 
			{
				//1.변수에 담기
//				custid 	= rs.getInt(1);
//				name 	= rs.getString(2);
//				address	= rs.getString(3);
//				phone	= rs.getString(4);
				
//				print1();
				
				//2.배열에 담기
//				custid2[index] 	= rs.getInt(1);
//				name2[index] 	= rs.getString(2);
//				address2[index]	= rs.getString(3);
//				phone2[index]	= rs.getString(4);
//				
//				index++;
				
				//3.객체에 담기 //변수 직접 접근 금지!
//				cs.custid 	= rs.getInt(1);
//				cs.name 	= rs.getString(2);
//				cs.address	= rs.getString(3);
//				cs.phone	= rs.getString(4);
				
//				cs.setCustid(rs.getInt(1));
//				cs.setName(rs.getString(2));
//				cs.setAddress(rs.getString(3));
//				cs.setPhone(rs.getString(4));
//				
//				print3();
				
				//4.객체 배열에 담기,  멤버변수에 직접 접근 금지!
//				cs2[0].custid 	= rs.getInt(1);
//				cs2[0].name 	= rs.getString(2);
//				cs2[0].address	= rs.getString(3);
//				cs2[0].phone	= rs.getString(4);
				
				cs2[index].setCustid(rs.getInt(1));
				cs2[index].setName(rs.getString(2));
				cs2[index].setAddress(rs.getString(3));
				cs2[index].setPhone(rs.getString(4));
				
				index++;
			}
			con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
//	private void print1()
//	{
//		System.out.print(custid + "\t");
//		System.out.print(name+ "\t");
//		System.out.print(address+ "\t");
//		System.out.println(phone+ "\t");
//	}
	
//	void print2()
//	{
//		for(int index=0; index<5; ++index)
//		{
//			System.out.print(custid2[index] + "\t");
//			System.out.print(name2[index]+ "\t");
//			System.out.print(address2[index]+ "\t");
//			System.out.println(phone2[index]+ "\t");
//		}
//	}
	
	void print3()
	{
		//변수 직접 접근 금지!
//		System.out.print(cs.custid + "\t");
//		System.out.print(cs.name+ "\t");
//		System.out.print(cs.address+ "\t");
//		System.out.println(cs.phone+ "\t");
		
		//그래서 멤버 메소드를 통해서 접근
		System.out.print(cs.getCustid() + "\t");
		System.out.print(cs.getName()+ "\t");
		System.out.print(cs.getAddress()+ "\t");
		System.out.println(cs.getPhone()+ "\t");
	}
	
	void print4()
	{
		//변수 직접 접근 금지!
//		for(int i=0; i<5;i++)
//		{
//			System.out.print(cs2[i].custid + "\t");
//			System.out.print(cs2[i].name+ "\t");
//			System.out.print(cs2[i].address+ "\t");
//			System.out.println(cs2[i].phone+ "\t");
//		}
		
		//그래서 멤버 메소드를 통해서 접근
		//변수 직접 접근 금지!
		for(int i=0; i<5;i++)
		{
			System.out.print(cs2[i].getCustid() + "\t");
			System.out.print(cs2[i].getName()+ "\t");
			System.out.print(cs2[i].getAddress()+ "\t");
			System.out.println(cs2[i].getPhone()+ "\t");
		}
		
	}

}




	
	

