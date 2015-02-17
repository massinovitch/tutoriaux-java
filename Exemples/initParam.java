package com.sdz.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Index extends HttpServlet {

	public void doGet(	HttpServletRequest request, 
						HttpServletResponse response)
						throws IOException, ServletException{
		//Bon, l�, c'est simple tout de m�me...
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//La servlet a une m�thode de r�cup�ration des noms de ces param�tres
		Enumeration e = getInitParameterNames();
		
		//On r�cup�re un objet de configuration de servlets
		ServletConfig conf = getServletConfig();
		
		//Et on parcourt le tout
		while(e.hasMoreElements()){
			String name = e.nextElement().toString();
			//On appelle la m�thode getInitParameter(String name) 
			//afin de r�cup�rer la valeur 
			out.println("<p><strong>" + name + " = " + conf.getInitParameter(name) + "</strong></p>");
		}
		//Vous pouviez aussi y acc�der comme ceci
		//out.println(getServletConfig().getInitParameter("titrePage"));
		//out.println(getServletConfig().getInitParameter("sousTitre"));
		
	}	
}

et dans le web.xml :

<web-app>

	<servlet>
		<servlet-class>com.sdz.control.Index</servlet-class>
		<servlet-name>StartPage</servlet-name>
		
		<init-param>
			<param-name>titrePage</param-name>
			<param-value>Nom de la page</param-value>
		</init-param>
		
		<init-param>
			<param-name>sousTitre</param-name>
			<param-value>Sous titre de la page</param-value>
		</init-param>
		
	</servlet>
	
	<servlet-mapping>
		<servlet-name>StartPage</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

</web-app>
