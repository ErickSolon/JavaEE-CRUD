package com.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.DAO;
import com.models.JavaBeans;

@WebServlet(urlPatterns = { "/PessoasController", "/lista", "/cadastro", "/update", "/edit", "/delete" })
public class PessoasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DAO dao = new DAO();
    private JavaBeans pessoas = new JavaBeans();

    public PessoasController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getServletPath();

        if (url.equals("/lista")) {
            doGetListaPessoas(request, response);
        } else if (url.equals("/cadastro")) {
            doPostPessoa(request, response);
        } else if (url.equals("/edit")) {
            doGetPessoa(request, response);
        } else if (url.equals("/update")) {
            doUpdatePessoa(request, response);
        } else if (url.equals("/delete")) {
            doDeletePessoa(request, response);
        }
    }

    protected void doDeletePessoa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            pessoas.setId(id);
            dao.deleteById(pessoas);
            response.sendRedirect("lista?page=1&size=10");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    protected void doUpdatePessoa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String linkedIn = request.getParameter("linkedIn");

            pessoas.setId(id);
            pessoas.setNome(nome);
            pessoas.setSobrenome(sobrenome);
            pessoas.setTelefone(telefone);
            pessoas.setEmail(email);
            pessoas.setLinkedIn(linkedIn);

            dao.updateById(pessoas);
            response.sendRedirect("lista?page=1&size=10");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros inválidos.");
        }
    }

    protected void doPostPessoa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            String sobrenome = request.getParameter("sobrenome");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            String linkedIn = request.getParameter("linkedIn");

            pessoas.setNome(nome);
            pessoas.setSobrenome(sobrenome);
            pessoas.setTelefone(telefone);
            pessoas.setEmail(email);
            pessoas.setLinkedIn(linkedIn);

            dao.save(pessoas);
            response.sendRedirect("lista?page=1&size=10");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros inválidos.");
        }
    }

    protected void doGetPessoa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            pessoas.setId(id);
            dao.findById(pessoas);
            request.setAttribute("pessoa", pessoas);
            RequestDispatcher rd = request.getRequestDispatcher("AtualizarPessoa.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
        }
    }

    protected void doGetListaPessoas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int pageNumber = Integer.parseInt(request.getParameter("page"));
            int pageSize = Integer.parseInt(request.getParameter("size"));

            if (pageNumber < 1 || pageSize < 1) {
                throw new NumberFormatException();
            }

            List<JavaBeans> listaPessoas = dao.findAll(pageNumber, pageSize);
            request.setAttribute("pessoas", listaPessoas);
            RequestDispatcher rd = request.getRequestDispatcher("listaDePessoas.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros de paginação inválidos.");
        }
    }

}
