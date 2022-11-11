package com.itea.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

public class Registration extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -1210613704116541742L;

    final int FIELD_COUNT = 8;
    String[] errors = new String[] {"", "", "", "", "", "", "", ""};

    private void parseErrors(String errorString) {
        int iBegin, iEnd;
        if (errorString == null || errorString.isEmpty()) {
            for (int i = 0; i < FIELD_COUNT; i++) {
                errors[i] = "";
            }
        } else {
            iBegin = errorString.indexOf("<li>");
            for (int i = 0; i < FIELD_COUNT; i++) {
                iEnd = errorString.indexOf("</li>", iBegin);
                if (iEnd > 0) {
                    errors[i] = errorString.substring(iBegin + 4, iEnd);
                    iBegin = iEnd + 5;
                } else {
                    iBegin = errorString.length();
                    errors[i] = "";
                }
            }
        }
    }

    private String fillRegistrationForm(HttpServletRequest request) {
        StringBuilder form = new StringBuilder("<center><form action='' method='POST'><table border='0'>");
        form.append("<tr><td width='150'>Login</td><td width='200'><input type='email' name='login' value='")
                .append(request.getParameter("login") != null ? request.getParameter("login") : "")
                .append("' /></td><td width='250'>").append(errors[0])
                .append("</td></tr><tr><td>Password</td><td><input type='password' name='password' /></td><td>")
                .append(errors[1])
                .append("</td></tr><tr><td>Re-Password</td><td><input type='password' name='rePassword' /></td><td>")
                .append(errors[2])
                .append("</td></tr><tr><td>Name</td><td><input type='text' name='fullName' value='")
                .append(request.getParameter("fullName") != null ? request.getParameter("fullName") : "")
                .append("' /></td><td>").append(errors[3])
                .append("</td></tr><tr><td>Region</td><td><select name='region'><option value=''>Select your region</option>")
                .append("<option value='Kyiv' ").append("Kyiv".equals(request.getParameter("region")) ? "selected" : "").append(">Kyiv region</option>")
                .append("<option value='Lviv' ").append("Lviv".equals(request.getParameter("region")) ? "selected" : "").append(">Lviv region</option>")
                .append("<option value='Odesa' ").append("Odesa".equals(request.getParameter("region")) ? "selected" : "").append(">Odesa region</option>")
                .append("</select></td><td>").append(errors[4])
                .append("</td></tr><tr><td>Gender</td><td>")
                .append("F<input type='radio' name='gender' value='F' ").append("F".equalsIgnoreCase(request.getParameter("gender")) ? "checked" : "").append(" />")
                .append("M<input type='radio' name='gender' value='M' ").append("M".equalsIgnoreCase(request.getParameter("gender")) ? "checked" : "").append(" />")
                .append("</td><td>").append(errors[5])
                .append("</td></tr><tr><td>Comment</td><td><textarea name='comment' cols='21' rows='5'>")
                .append(request.getAttribute("comment") != null ? request.getAttribute("comment") : "")
//                .append(request.getParameter("comment") != null ? request.getParameter("comment") : "")
                .append("</textarea></td><td>").append(errors[6])
                .append("</td></tr><tr><td>Glory to Ukraine</td><td><input type='checkbox' name='agreement' ")
                .append("on".equalsIgnoreCase(request.getParameter("agreement")) ? "checked" : "")
                .append(" /></td><td>").append(errors[7])
                .append("</td></tr><tr><td></td><td align='center'><input type='submit' value='Send' /></td><td></td></tr>")
                .append("</table></form></center>");
        return form.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder outString;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        UserRegisteringData user = new UserRegisteringData(request);
        if (user.isCorrect()) {
            outString = new StringBuilder("<center>login = ").append(user.getLogin())
                    .append("<br>password = ").append(user.getPassword())
                    .append("<br>fullName = ").append(user.getFullName())
                    .append("<br>region = ").append(user.getRegion())
                    .append("<br>gender = ").append(user.getGender())
                    .append("<br>comment = ").append(user.getComment())
                    .append("<br>agreement = ").append(user.getAgreement())
                    .append("<br>")
                    .append("<h2 align='center' style='color:blue;'>Register completed</h2>");
            out.write(outString.toString());
//      Register completed
        } else {
            this.parseErrors(user.getErrors());
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder outString;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        outString  = new StringBuilder(Menu.MENU);
        outString.append(this.fillRegistrationForm(request));
        out.write(outString.toString());
    }

}
