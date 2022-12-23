<%@ page isELIgnored="false"%>

<%@ include file="header.jsp" %>
    <h3 align='center' style='color:blue;'>Access granted. Hello, ${authenticatedUser.getFullName()}!</h3>
<%@ include file="footer.jsp" %>
