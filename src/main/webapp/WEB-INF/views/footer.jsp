<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
			  </div>
            
				<div id="sidebar">
					<table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    Вы авторизировались как ${authenticatedUser}<br />
                    В вашей корзине 0 товаров.
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
					<ul>
						<li><a href="${pageContext.request.contextPath}/products?category=1">Hard drive</a></li>
						<li><a href="${pageContext.request.contextPath}/products?category=2">RAM</a></li>
						<li><a href="${pageContext.request.contextPath}/products?category=3">Flash</a></li>
						<li><a href="${pageContext.request.contextPath}/registration">Регистрация</a></li>
						<li><a href="${pageContext.request.contextPath}/login">Вход</a></li>
						<li><a href="${pageContext.request.contextPath}/cart">Корзина</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">
	<p>Copyright (c) by Бондаренко Антон</p>
</div>
<!-- end #footer -->
</body>
</html>
