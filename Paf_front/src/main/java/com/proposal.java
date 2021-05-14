package com;
import java.sql.*;
public class proposal {
//A common method to connect to the DB
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");



//Provide the correct details: DBServer/DBName, username, password
con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/proposal", "root", "");
}
catch (Exception e)
{e.printStackTrace();}
return con;
}
public String insertproposal( String title, String expBudget, String description)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for inserting."; }
// create a prepared statement
String query = " insert into proposal(`proposalCode`,`proposalTitle`,`proposalExpBudget`,`proposalDescription``)"
+ " values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
//preparedStmt.setInt(1, 0);

preparedStmt.setString(1, title);
preparedStmt.setDouble(2, Double.parseDouble(expBudget));
preparedStmt.setString(3, description);

// execute the statement

preparedStmt.execute();
con.close();
String newproposal = readproposal();
output = "{\"status\":\"success\", \"data\": \"" +
newproposal + "\"}" ;
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\": \"Error While Inserting the proposal.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String readproposal()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>Proposal Code</th><th>Proposal Title</th>" + "<th>Proposal ExpBudget</th>"
+ "<th>Proposal Description</th>"+  "<th>Update</th><th>Remove</th></tr>";

String query = "select * from proposal";

Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String proposalID = Integer.toString(rs.getInt("proposalID"));
String proposalCode = rs.getString("proposalCode");
String proposalTitle = rs.getString("proposalTitle");
String proposalExpBudget = Double.toString(rs.getDouble("proposalExpBudget"));
String proposalDescription = rs.getString("proposalDescription");

// Add into the html table;

output += "<tr><td>" + proposalCode + "</td>";
output += "<td>" + proposalTitle + "</td>";
output += "<td>" + proposalExpBudget + "</td>";
output += "<td>" + proposalDescription + "</td>";

// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'data-proposalid='" + proposalID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-proposalid='" + proposalID + "'></td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}

catch (Exception e)
{
output = "Error while reading the proposal.";
System.err.println(e.getMessage());
}
return output;
}




public String updateproposal(String pcode, String title, String expBudget, String description)




{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE proposal SET title=?,expBudget=?,description=?WHERE pcode=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, title);
preparedStmt.setDouble(2, Double.parseDouble(expBudget));
preparedStmt.setString(3, description);
preparedStmt.setInt(4, Integer.parseInt(pcode));
// execute the statement
preparedStmt.execute();
con.close();
String newproposal = readproposal();
output = "{\"status\":\"success\", \"data\": \"" + newproposal + "\"}" ;
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\": \"Error While Updating the Proposal.\"}";
System.err.println(e.getMessage());
e.printStackTrace();
}
return output;
}

public String deleteproposal(String pcode)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for deleting."; }
// create a prepared statement
String query = "delete from proposal where pcode=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(pcode));
// execute the statement
preparedStmt.execute();
con.close();
String newproposal = readproposal();
output = "{\"status\":\"success\", \"data\": \"" + newproposal + "\"}" ;
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\": \"Error While Deleting the proposal.\"}";
System.err.println(e.getMessage());
e.printStackTrace();

}
return output;
}
}