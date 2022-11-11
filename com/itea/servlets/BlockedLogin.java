package com.itea.servlets;

import com.itea.utils.Encoding;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.time.Duration;
import java.time.LocalDateTime;

public class BlockedLogin extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -6198900590472649299L;
    final int TIME_OUT = 10;
    final int MAX_TRY = 3;
    final String FORM_LOGIN = "<html><center>\n" +
            "   <form action='' method='post'>\n" +
            "   <table border='0'>\n" +
            "      <tr>\n" +
            "         <td width='50%' align='right'>Login: </td>\n" +
            "         <td width='50%'><input type='text' name='login' /></td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "         <td align='right'>Password: </td>\n" +
            "         <td><input type='text' name='password' /></td>\n" +
            "      </tr>\n" +
            "      <tr>\n" +
            "         <td align='center' colspan='2'><input type='submit' value='Send' /></td>\n" +
            "      </tr>\n" +
            "   </table>\n" +
            "   </form></center></html>";
    private StringBuilder message = new StringBuilder();
    private int count = 0;
    private LocalDateTime endTime = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostgreSQLJDBC conn = new PostgreSQLJDBC();
        boolean isShowForm = true;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (endTime != null) {
            long waitSecond = TIME_OUT - Duration.between(endTime, LocalDateTime.now()).getSeconds();
            message = new StringBuilder("<center>Wait ").append(waitSecond).append(" second(s) to try again</center>");
            isShowForm = waitSecond <= 0;
            if (isShowForm) {
                count = -1;
                endTime = null;
            }
        }
        if (count < MAX_TRY) {
            isShowForm = true;
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login != null) {
                conn.establishConnection();
                String fullName = conn.getFullNameByLoginAndPassword(login, Encoding.md5EncryptionWithSalt(password));
                conn.closeConnection();
//                if ("admin".equals(login) && "123".equals(request.getParameter("password"))) {
                if (fullName != null) {
                    message = new StringBuilder("<h2 align='center'><font color='#0000FF'>Access granted</font></h2>")
                            .append("Hello, ").append(fullName);
                    isShowForm = false;
                } else {
                    count++;
                    if (count < MAX_TRY) {
                        message = new StringBuilder();
                        if (count > 0) {
                            message.append("<center><h2><font color='#0000FF'>Access denied</font></h2><br>You have ").append(MAX_TRY - count).append(" attempt(s)<center>");
                        }
                    } else {
                        endTime = LocalDateTime.now();
                        message = new StringBuilder("<center>You are blocked for " + TIME_OUT + " seconds!</center>");
                        isShowForm = false;
                    }
                }
            }
        }
        if (isShowForm) {
            doGet(request, response);
        }
        out.write(message.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder outString;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        outString  = new StringBuilder(Menu.MENU);
        outString.append(FORM_LOGIN);
        out.write(outString.toString());
    }

}
