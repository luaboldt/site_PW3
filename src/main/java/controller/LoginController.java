package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ConexaoDAO;
import dao.PessoaDAO;
import model.Pessoa;


@WebServlet(urlPatterns = {"/login","/novousuario"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConexaoDAO conexaoDAO = new ConexaoDAO();
		conexaoDAO.conectar();

		String acao = request.getServletPath();
		System.out.println(acao);
		
		if (acao.equals("/novousuario")) {
			response.sendRedirect("novousuario.jsp");
		} else if (acao.equals("/login")) {
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("login.jsp");
		}
		
		/*String url = "login.jsp";
		RequestDispatcher view = request.getRequestDispatcher(url);
		view.forward(request, response);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getServletPath();
		System.out.println(acao);
		
		//1 - Receber os parâmetros		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
			
		if (acao.equals("/novousuario")) {

			List<String> erros = new ArrayList<String>();
			
			if (nome == null || nome.isEmpty()) {
				erros.add("O usuário precisa ser preenchido");
			}
			
			if (email == null || email.isEmpty()) {
				erros.add("O email precisa ser preenchido");
			}
			
			if (senha == null || senha.isEmpty()) {
				erros.add("A senha precisa ser preenchida");
			}
			
			// Redirecionar para a página interna ou de volta para o Login
			if (erros.isEmpty()) {
				
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(nome);
				pessoa.setEmail(email);
				pessoa.setSenha(senha);
				
				// Armazena
				PessoaDAO dao = new PessoaDAO();
				if (dao.inserir(pessoa)) {
					System.out.println("Usuário cadastrado com sucesso");
				} else {
					System.out.println("Erro ao cadastrar usuário");
				}
				
				
				// Se não há erros na lista, cadastro com sucesso
				request.getSession().setAttribute("nome", nome);
				response.sendRedirect("areainterna.jsp");
			}
			else {
				// Invalida a sessão
				request.getSession().invalidate();
				request.setAttribute("erros", erros); // Erros é um atributo que será acessado no html
				RequestDispatcher view = request.getRequestDispatcher("novousuario.jsp");
				
				view.forward(request, response);
			}
			
			
		} else if (acao.equals("/login")) {
					
			//1 - Receber os parâmetros		
			
			String lembrar = request.getParameter("lembrar");
			
			//2 - Validar o preenchimento
			List<String> erros = new ArrayList<String>();
			
			if (email == null || email.isEmpty()) {
				erros.add("O email precisa ser preenchido");
			}
			
			if (senha == null || senha.isEmpty()) {
				erros.add("A senha precisa ser preenchida");
			}
			
			//3 - Consultar o banco de dados
			if (email.equals("teste@teste.com") && senha.equals("123")) {
				//Logado
				nome = "Fulano";
			}
			else {
				erros.add("Usuário ou senha inválidos");
			}
			
			//4 - Redirecionar para a página interna ou de volta para o Login
			if (erros.isEmpty()) {
				// Se não há erros na lista, usuário ok
				request.getSession().setAttribute("nome", nome);
				response.sendRedirect("areainterna.jsp");
			}
			else {
				// Invalida a sessão
				request.getSession().invalidate();
				request.setAttribute("erros", erros); // Erros é um atributo que será acessado no html
				RequestDispatcher view = request.getRequestDispatcher("login.jsp");
				
				view.forward(request, response);
			}
		} else {
			// nenhuma ação permitida aqui
		}
	}

}
