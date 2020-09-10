
public class Customer 
{
	//1.멤버변수
	private int custid;
	private String name;
	private String address;
	private String phone;
	
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getCustid() {
		return custid;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	
	
}
