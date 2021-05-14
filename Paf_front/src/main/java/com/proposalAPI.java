package com;
import com.proposal;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
* Servlet implementation class proposalAPI
*/
@WebServlet("/proposaltAPI")
public class proposalAPI extends HttpServlet {
private static final long serialVersionUID = 1L;

proposal proposalObj = new proposal();

/**
* @see HttpServlet#HttpServlet()
*/
public proposalAPI() {
super();
// TODO Auto-generated constructor stub
}



/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}



/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
*/
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String output = proposalObj.insertproposal(request.getParameter("title"),
request.getParameter("expBudget"),
request.getParameter("description"));
response.getWriter().write(output);
}



/**
* @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
*/
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Map paras = getParasMap(request);
String output = proposalObj.updateproposal(paras.get("hidpcodeSave").toString(),
paras.get("title").toString(),
paras.get("expBudget").toString(),
paras.get("description").toString());
response.getWriter().write(output);

}



/**
* @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
*/
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



Map paras = getParasMap(request);
String output = proposalObj.deleteproposal(paras.get("pcode").toString());
response.getWriter().write(output);

System.out.println("delete method '"+output+"'");




}


private static Map getParasMap(HttpServletRequest request)
{
Map<String, String> map = new HashMap<String, String>();
try
{
Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
String queryString = scanner.hasNext() ?
scanner.useDelimiter("\\A").next() : "";
scanner.close();
String[] params = queryString.split("&");
for (String param : params)
{
String[] p = param.split("=");
map.put(p[0], p[1]);
}
}
catch (Exception e)
{
}
return map;
}



}