package principal;

import model.Pessoa;

public class Principal {
	public static void main(String[] args) {
		Pessoa p1 = new Pessoa(1, "Agnaldo Pereira", "agnaldo@gmail.com");
		
		System.out.println(p1.toString());
		
	}
}
