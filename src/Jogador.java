	import java.io.Serializable;

	/* Exercício de avaliação 2
	 * 
	 * Ana Catarina Pais Pereira - 2019244449
	 * Ema Margarida da Silva Macedo - 2019233271
	 * 
	 Jogo da Paciência/Solitário */

	//Esta classe implementa a classe Jogador// 

	public class Jogador implements Serializable
	{
		// criar atributos da classe:
		private String email;
		private String password;
		private String nome;
		private double avgmoves; //nº médio movimentos
		private int n_jogos; //nº de jogos ganhos

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

		// Métodos:
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

		public String toString() // representação, provavelmente não é preciso, só para testar
		{
			String str = "";
			str += "Nome: " + this.nome + " \nEmail: " + this.email + " \nPassword: " + this.password + "\nNº jogos ganhos: " + this.n_jogos + " \nMédia: " + this.avgmoves;
			return str;
		}

		// Para testar
		public static void main(String[] args)
		{
			Jogador joao = new Jogador("Joao", "joao@gmail.com", "joao123");
			System.out.println(joao);
		}

	}


