package com.komsoft.shopmvc.controller;

import com.komsoft.shopmvc.exception.DataBaseException;
import com.komsoft.shopmvc.form.Header;
import com.komsoft.shopmvc.model.Product;
import com.komsoft.shopmvc.repository.PostgreSQLJDBC;
import com.komsoft.shopmvc.repository.ProductRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ProductController extends HttpServlet {
    ProductRepository productRepository;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
//        if ()
        try {
            List<Product> products = productRepository.getAllProduct(category);
//            productRepository.
            request.setAttribute("products", products);
            String url = Header.PAGE_ROOT + "products.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        productRepository = new ProductRepository(new PostgreSQLJDBC());
    }


}
