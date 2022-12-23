<%@ include file="header.jsp" %>
  <h2>Pay page</h2>
<div style='text-align: center;'>${payMessage}</div>
<div style='text-align: center;'>
  <p>Click this button sets Cart attribute "paid" to TRUE</p>
  <form action="${pageContext.request.contextPath}/pay/success" method="post" >
    <input type="submit" value='Paid successfully' />
  </form>
</div>
<%@ include file="footer.jsp" %>
