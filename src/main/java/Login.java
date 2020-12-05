import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Pattern;

public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        boolean alert = false;     
        String email = request.getParameter("email");
        char[] pass = request.getParameter("pass").toCharArray();
        
        // Sanitize email string
        StringBuilder sb = new StringBuilder(email.length());
        for (int i = 0; i < email.length(); ++i) {
            char ch = email.charAt(i);
            if (Character.isLetterOrDigit(ch) || ch == ' ' || ch == '\'' || ch == '@' || ch == '.' || ch == '_' || ch == '-') {
                sb.append(ch);
            }
        }
        email = sb.toString();
        
        // Normalize
        email = Normalizer.normalize(email, Form.NFKC);
        // Validate
        String spattern_email = "^[A-Za-z0-9+_.-]+@(.+)$";

        Pattern pattern_email = Pattern.compile(spattern_email);
        if(!pattern_email.matcher(email).matches()) alert = true;
        
        if(Validate.checkUser(email, pass) && !alert)
        {
            RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            rs.forward(request, response);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("index.html");
           rs.include(request, response);
        }
        clearArray(pass);
    }  

    private void clearArray(char[] pass) {
        for (int i = 0; i < pass.length; i++) {
            pass[i] = 0;
        }
    }
}