/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbad;

import data.Product;
import data.ProductTable;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author milesforet
 */
@WebServlet(name = "ProductManagementServlet", urlPatterns = {"/productManagement"})
public class ProductManagementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        //remove the values from edit and confirmDelte
        session.removeAttribute("pp");

        switch (action) {
            case "displayProducts": {
                List products = ProductTable.selectProducts();
                session.setAttribute("products", products);
                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
                break;
            }
            case "confirmDelete": {
                //get code
                String code = request.getParameter("code");
                out.println("confirmDelete");
                out.println(code);
                //get product
                Product product = ProductTable.selectProduct(code);
                //display to the user
                session.setAttribute("pp", product);
                getServletContext().getRequestDispatcher("/confirmDelete.jsp").forward(request, response);
                break;
            }
            case "actuallyDelete": {

                String code = request.getParameter("code");
                //get product
                Product product = ProductTable.selectProduct(code);
                //delete product
                ProductTable.deleteProduct(product);
                //get list
                List products = ProductTable.selectProducts();
                //print list in products.jsp
                session.setAttribute("products", products);

                getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
                break;
            }
            case "updateProduct": {
                String code = request.getParameter("code");
                out.println("update Product");
                out.println(code);

                Product product = ProductTable.selectProduct(code);
                session.setAttribute("pp", product);
                getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
                break;
            }
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Checking action param
        String action = request.getParameter("action");
        //session set
        HttpSession session = request.getSession();
        //remove the values from edit and confirmDelte
        session.removeAttribute("pp");
        //make new product
        Product p = new Product();
        String url;
        if (action.equals("displayProducts")) {
            List products = ProductTable.selectProducts();
            session.setAttribute("products", products);
            getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
        }

        //get productCode
        String code = request.getParameter("code");
        //check if product exists
        boolean exist = ProductTable.exists(code);
        out.println(exist);
        //upadate the product
        if (exist == true) {
            String description = request.getParameter("description");
            //setting price
            if (request.getParameter("price") != null) {
                p.setPrice(Double.parseDouble(request.getParameter("price")));
            }
            //set stuff
            p.setCode(code);
            p.setDescription(description);
            //update the product
            ProductTable.updateProduct(p);
            List products = ProductTable.selectProducts();
            session.setAttribute("products", products);
            url = "/products.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        } else {
            //all else make new product
            //get strings
            String description = request.getParameter("description");
            //setting price
            if (request.getParameter("price") != null) {
                p.setPrice(Double.parseDouble(request.getParameter("price")));
            }

            p.setCode(code);
            p.setDescription(description);
            //insert product.
            ProductTable.insertProduct(p);
            //get products
            List products = ProductTable.selectProducts();
            session.setAttribute("products", products);
            url = "/products.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    private String actuallyDelete(HttpServletRequest request,
            HttpServletResponse response) {
        String code = request.getParameter("code");

        return "/products.jsp";
    }
}
