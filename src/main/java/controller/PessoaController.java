package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PessoaDAO;
import model.Pessoa;

@WebServlet(urlPatterns = {"/PessoaController", "/listarusuarios", "/procurar"})
public class PessoaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PessoaController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String acao = request.getServletPath();
		
		System.out.println("Acao: " + acao);
		
		if (acao.equals("/listarusuarios")) {
			List<Pessoa> dados = new PessoaDAO().buscar();
			request.setAttribute("dados", dados);
			request.getRequestDispatcher("listar.jsp").forward(request, response);
		} else if (acao.equals("/procurar")) {
			String id = request.getParameter("id");
			Pessoa p = new PessoaDAO().buscarPorId(Integer.parseInt(id));
			System.out.println(p);
		}
		
		
		/*for(int i = 0; i < dados.size(); i++) {
			System.out.println(dados.get(i).getNome());
		}*/
		
		/*Pessoa p1 = new Pessoa(1, "Fulano", "fulano@gmail.com");
		Pessoa p2 = new Pessoa(2, "Ciclano", "ciclano@gmail.com");
		
		List<Pessoa> lista = new ArrayList<Pessoa>();
		lista.add(p1);
		lista.add(p2);
		
		PrintWriter saida = response.getWriter();
		
		for (int i = 0; i < lista.size(); i++) {
			saida.println(lista.get(i).toString());
		}*/
				
		//saida.println("Olá");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
