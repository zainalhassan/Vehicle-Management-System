import org.eclipse.jetty.server.Server; //Imports Jetty Server 
import org.eclipse.jetty.webapp.Configuration.ClassList; // Imports the Webapp Configuration
import org.eclipse.jetty.webapp.WebAppContext; // Imports the Webapp Context

/** The purpose of the jetty Controller is to set up and run the jetty server so that the vehicle database can be viewed online, and the crud methods could be tested online as well.
 *
 * @author Zain Al-Hassan
 *
 */
public class ServerController 
{
	
		public static void main(String[] args) throws Exception
		{
		  Server server = new Server(8005); //New Server Instance using port "8005"
		  WebAppContext ctx = new WebAppContext(); //New WebAppContext instance call ctx
		  ctx.setResourceBase("webapp");
		  ctx.setContextPath("/VehiclesDB");
		  
		  // Configuration 
		  ctx.setAttribute("org.eclispe.jetty.server.webapp.ContainerIncludeJarPattern",".*/[^/]*jstl.*\\.jar$");
		  ClassList classlist = ClassList.setServerDefault(server);
		  classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration","org.eclipse.jetty.annotations.AnnotationConfiguration");
		  			
		  //Mapping
		  ctx.addServlet("Servlets.ServletHome","/home"); // Home Page
		  ctx.addServlet("Servlets.ServletJSON","/JSON"); // JSON Page
		  ctx.addServlet("Servlets.ServletLogin","/login"); // login Page
		  ctx.addServlet("Servlets.ServletSignUp","/signup"); // sign up Page
		  ctx.addServlet("Servlets.ServletSignOut","/logout"); // sign out Page
		  
		  // API Route Mappings 
		  ctx.addServlet("Servlets.ServletApi", "/api");
		  
		  // Setting the Handler and Start the Server
		  server.setHandler(ctx);
		  server.start();
		  server.join();
		 
		}
	
}
