package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import model.Pessoa;

public class PessoaDAO {
	
	public boolean inserir(Pessoa pessoa) {
		try {
			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("Insert into usuario (nome, senha, email) values (?,?,?)");
			
			statement.setString(1, pessoa.getNome());
			statement.setString(2, pessoa.getSenha());
			statement.setString(3, pessoa.getEmail());
			
			statement.execute();
			
			conn.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Pessoa> buscar() {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		
		try {
			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("Select * from usuario");
			
			ResultSet retorno = statement.executeQuery();
			
			while (retorno.next()) {
				Pessoa p = new Pessoa();
				
				p.setId(retorno.getInt("id"));
				p.setEmail(retorno.getString("email"));
				p.setNome(retorno.getString("nome"));
			
				lista.add(p);
			}
			
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			return lista;
		}
	}
	
	public Pessoa buscarPorId(int id) {
		Pessoa p = new Pessoa();
		
		try {
			Connection conn = new ConexaoDAO().conectar();
			
			PreparedStatement statement = conn.prepareStatement("Select * from usuario where id = ?");
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				p.setId(resultSet.getInt("id"));
				p.setEmail(resultSet.getString("email"));
				p.setNome(resultSet.getString("nome"));
			}
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		} finally {
			return p;
		}
	}

}
