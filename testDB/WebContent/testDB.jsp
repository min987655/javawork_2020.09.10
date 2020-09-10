<%@ page import="db.beans.*,java.sql.*,java.util.*,java.io.*"%>
<%@ page contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<jsp:useBean id="QueryBean" scope="page" class="db.beans.QueryBean" />
<jsp:setProperty name="QueryBean" property="*" />
<%
	//캐쉬 제거?
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","0");

	response.setCharacterEncoding("UTF-8");
	
	QueryBean.getConnection();
	
	ArrayList<String>resArr = new ArrayList<>();
	
	try
	{
		resArr = QueryBean.getUserInfo();
	}
	catch(SQLException e)
	{
		out.print(e.toString());
	}
	finally
	{
		QueryBean.closeConnection();
	}
	
	out.println("{");
	out.println("\"datas\":[");
	
	if(resArr.size() == 0)
	{
		out.print("]");
		out.print("}");
	} else {
		out.print("{ ");
		out.println("\"ID\": \""           + (String)resArr.get(0) + "\", ");
		out.println("\"NAME\": \""         + (String)resArr.get(1) + "\", ");
		out.println("\"PHONE\": \""        + (String)resArr.get(2) + "\", ");
		out.println("\"GRADE\": \""        + (String)resArr.get(3) + "\", ");
		out.println("\"WRITE_TIME\": \""     + (String)resArr.get(4) + "\" ");
		out.print("} ");
		
		for(int i=5; i<resArr.size(); i+=5)
		{
			out.print(",");
			out.print("{ ");
			out.println("    \"ID\": \""           + (String)resArr.get(i) + "\", ");
			out.println("    \"NAME\": \""         + (String)resArr.get(i+1) + "\", ");
			out.println("    \"PHONE\": \""        + (String)resArr.get(i+2) + "\", ");
			out.println("    \"GRADE\": \""        + (String)resArr.get(i+3) + "\", ");
			out.println("    \"WRITE_TIME\": \""     + (String)resArr.get(i+4) + "\" ");
			
			out.print("} ");
		}
		out.print("] ");
		out.print("} ");
	}
%>	