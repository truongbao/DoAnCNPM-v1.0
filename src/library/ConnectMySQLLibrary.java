
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQLLibrary {
	private Connection connection;
	private String url;
	private String user;
	private String pass;
	
	public ConnectMySQLLibrary(){
		this.url = "jdbc:mysql://localhost:3307/nckhbachkhoa?useUnicode=true&characterEncoding=UTF-8";
		this.user  = "root";
		this.pass = "";
	}
	
    public Connection getConnectMySQL(){
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,pass);
		}	
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}	
    	
    	return connection;
    }
    public static void main(String[] args) {
		ConnectMySQLLibrary conn = new ConnectMySQLLibrary();
		System.out.println(conn.getConnectMySQL());
	}
	
}




/*package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectMySQLLibrary {
	private Connection connection;
	private String url;
	private String user;
	private String pass;
	private ReadPropertiesLibrary propertiesLibrary;
	private Properties properties=null;
	
	public ConnectMySQLLibrary(){
		propertiesLibrary = new ReadPropertiesLibrary();
		properties = propertiesLibrary.readProperties();
		
//		this.url = "jdbc:mysql://localhost:3306/shareit?useUnicode=true&characterEncoding=UTF-8";
//		this.user  = "root";
//		this.pass = "";
		this.url = properties.getProperty("url");
		this.user  = properties.getProperty("username");
		this.pass = properties.getProperty("password");
		
		
		
	}
	
//	public ConnectMySQLLibrary(){
//		this.url = "jdbc:mysql://node180998-hoaibao.jelastic.servint.net/shareit?useUnicode=true&characterEncoding=UTF-8";
//		this.user  = "root";
//		this.pass = "OPHxsa55998";
//	}
	
	
	
    public Connection getConnectMySQL(){
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url,user,pass);
		}	
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}	
    	
    	return connection;
    }
    public static void main(String[] args) {
		ConnectMySQLLibrary conn = new ConnectMySQLLibrary();
		System.out.println(conn.getConnectMySQL());
	}
	
}
*/























