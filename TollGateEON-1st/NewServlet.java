/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import okhttp3.*;
/**
 *
 * @author Jace Christian
 */ 
public class NewServlet extends HttpServlet {
    OkHttpClient client = new OkHttpClient();
    Response res;
    Request req;
    
    String account_no;
    String currency;
    String account_name;
    String statusAcc;
    String available_balance;
    String current_balance ;
     String accNum;
     String transinfo;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
        req = new Request.Builder()
                .url("https://api.us.apiconnect.ibmcloud.com/ubpapi-dev/sb/api/RESTs/getAccount?account_no=100791778958")
                .get()
                .addHeader("x-ibm-client-id", "0b826325-782c-4a47-884e-6093beb45e99")
                .addHeader("x-ibm-client-secret", "yC1fW0uB8nW8xY3bB3bM5mW0oL3eI3kK7vK5aY4lT8rR8yI2oI")
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build();

        res = client.newCall(req).execute();
        

        }
        catch(Exception e){
            e.printStackTrace();
        }
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println(res.body().string());
            out.println("</body>");
            out.println("</html>");
            
        }
    }
    
    
     public void getInfoBal(String info)
    {
        transinfo=info;
        info = info.replace("[","").replace("]","").replace("{","").replace("}","").replace("\"","");
        String[] collection_info = info.split(",");

        for(int ctr = 0; ctr < collection_info.length; ctr++)
        {
            String[] temp = collection_info[ctr].split(":");
            collection_info[ctr] = temp[1];
        }

        account_no = collection_info[0];
        currency = collection_info[1];
        account_name = collection_info[2];
        statusAcc = collection_info[3];
        available_balance = collection_info[4];
        current_balance = collection_info[5];
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
