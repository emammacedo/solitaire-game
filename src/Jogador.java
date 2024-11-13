	import java.io.Serializable;

	/* Exerc�cio de avalia��o 2
	 * 
	 * Ana Catarina Pais Pereira - 2019244449
	 * Ema Margarida da Silva Macedo - 2019233271
	 * 
	 Jogo da Paci�ncia/Solit�rio */

	//Esta classe implementa a classe Jogador// 

	public class Jogador implements Serializable
	{
		// criar atributos da classe:
		private String email;
		private String password;
		private String nome;
		private double avgmoves; //n� m�dio movimentos
		private int n_jogos; //n� de jogos ganhos

		public Jogador() // construtor vazio
		{
		}

		public Jogador(String nome, String email, String password)// construtor que recebe os atributos
		{
			// inicializar atributos do objeto
			this.nome = nome;
			this.email = email;
			this.password = password;
			this.avgmoves = 0;
			this.n_jogos = 0;
		}

		// M�todos:
		public String getEmail()
		{
			return email;
		}

		public int getN_jogos()
		{
			return n_jogos;
		}

		public void setN_jogos(int n_jogos)
		{
			this.n_jogos = n_jogos;
		}

		public void setEmail(String email)
		{
			this.email = email;
		}

		public String getPassword()
		{
			return password;
		}

		public void setPassword(String password)
		{
			this.password = password;
		}

		public String getNome()
		{
			return nome;
		}

		public void setNome(String nome)
		{
			this.nome = nome;
		}

		public double getAvgmoves()
		{
			return avgmoves;
		}

		public void setAvgmoves(double avgmoves)
		{
			this.avgmoves = avgmoves;
		}

		public String toString() // representa��o, provavelmente n�o � preciso, s� para testar
		{
			String str = "";
			str += "Nome: " + this.nome + " \nEmail: " + this.email + " \nPassword: " + this.password + "\nN� jogos ganhos: " + this.n_jogos + " \nM�dia: " + this.avgmoves;
			return str;
		}

		// Para testar
		public static void main(String[] args)
		{
			Jogador joao = new Jogador("Joao", "joao@gmail.com", "joao123");
			System.out.println(joao);
		}

	}


