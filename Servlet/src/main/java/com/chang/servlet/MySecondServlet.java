package com.chang.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MySecondServlet", urlPatterns = {"/MySecondServlet"})
public class MySecondServlet extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Write some content
            out.println("<html>");
            out.println("<head>");
            out.println("<title>MySecondServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Servlet MySecondServlet at " + request.getContextPath() + "</h2>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
	}
	
	@Override 
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		//do something
	}
	
	@Override
    public String getServletInfo() {
        return "MySecondServlet";
    }
	
}
