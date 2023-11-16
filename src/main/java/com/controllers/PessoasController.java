package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		
		if(url.equals("/lista")) {
			doGetListaPessoas(request, response);
		} else if(url.equals("/cadastro")) {
			doPostPessoa(request, response);
		} else if(url.equals("/edit")) {
			doGetPessoa(request, response);
		} else if(url.equals("/update")) {
			doUpdatePessoa(request, response);
		} else if(url.equals("/delete")) {
			doDeletePessoa(request, response);
		}
	}
	
	protected void doDeletePessoa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pessoas.setId(Integer.parseInt(request.getParameter("id")));
		dao.deleteById(pessoas);
		response.sendRedirect("lista");
	}
	
	protected void doUpdatePessoa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pessoas.setId(Integer.parseInt(request.getParameter("id")));
		pessoas.setNome(request.getParameter("nome"));
		pessoas.setSobrenome(request.getParameter("sobrenome"));
		pessoas.setTelefone(request.getParameter("telefone"));
		pessoas.setEmail(request.getParameter("email"));
		pessoas.setLinkedIn(request.getParameter("linkedIn"));
		dao.updateById(pessoas);
		response.sendRedirect("lista");
	}
	
	protected void doPostPessoa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pessoas.setNome(request.getParameter("nome"));
		pessoas.setSobrenome(request.getParameter("sobrenome"));
		pessoas.setTelefone(request.getParameter("telefone"));
		pessoas.setEmail(request.getParameter("email"));
		pessoas.setLinkedIn(request.getParameter("linkedIn"));
		dao.save(pessoas);
		response.sendRedirect("lista");
	}
	
	protected void doGetPessoa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		pessoas.setId(id);
		dao.findById(pessoas);
		request.setAttribute("pessoa", pessoas);
		RequestDispatcher rd = request.getRequestDispatcher("AtualizarPessoa.jsp");
		rd.forward(request, response);
	}

	protected void doGetListaPessoas(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<JavaBeans> listaPessoas = dao.findAll();
		request.setAttribute("pessoas", listaPessoas);
		RequestDispatcher rd = request.getRequestDispatcher("listaDePessoas.jsp");
		rd.forward(request, response);
	}


}
