/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DTO.Anime;
import DAO.DAOAnime;
import Erro.Erros;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author informatica
 */
@WebServlet(name = "teste", urlPatterns = {"/teste"})
public class teste extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erros erro = new Erros("");
        String pagina;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            boolean AnimeExistente;
            /* TODO output your page here. You may use following sample code. */
           Anime anime = new Anime();
           DAOAnime salvarAnime = new DAOAnime();
           anime.setNome(request.getParameter("AddNome"));
           anime.setAutor(request.getParameter("AddAtor"));
           anime.setAno(request.getParameter("AddAno"));
           anime.setGenero(request.getParameter("AddGenero"));
           AnimeExistente = salvarAnime.adicionarAnime(anime);
            if (AnimeExistente) {
                erro.setErro("Este anime já existe na lista.Por favor adicione outro.");
		pagina = "Erro.jsp";
		request.setAttribute("erro", erro);
            }else{
                pagina = "Incluir.jsp";
            }
           RequestDispatcher rd = request.getRequestDispatcher(pagina);
           rd.forward(request, response);
                
           
        }
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
