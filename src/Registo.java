import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/* Exercício de avaliação 2
 * 
 * Ana Catarina Pais Pereira - 2019244449
 * Ema Margarida da Silva Macedo - 2019233271
 * 
 Jogo da Paciência/Solitário */

//Esta classe implementa a classe Registo//

public class Registo implements Serializable
{

	public static ArrayList<Jogador> readPlayers() // Leitura
	{
		ArrayList<Jogador> jog = new ArrayList<Jogador>();
		try
		{
			FileInputStream fos = new FileInputStream("db.tmp");
			ObjectInputStream oos = new ObjectInputStream(fos);
			jog = (ArrayList) oos.readObject();
			oos.close();
			return jog;
		}
		catch (IOException | ClassNotFoundException ioe)
		{
			ioe.printStackTrace();
			return jog;
		}
	}

	public static boolean writePlayers(ArrayList<Jogador> jogs)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("db.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(jogs);
			oos.close();
			return true;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
			return false;
		}
	}

	ArrayList<Jogador> registos = new ArrayList<Jogador>();

	// Login e identificação do jogador, através do email e password
	public Jogador getJogador()
	{
		Jogador jogadoratual = null;
		Scanner sc = new Scanner(System.in);

		registos = readPlayers();
		// Efetuar login
		System.out.println("Email: ");
		String email = sc.nextLine();
		System.out.println("Password: ");
		String password = sc.nextLine();

		if (registos.size() > 0) // se existirem já registos efetuados
		{
			for (int i = 0; i < registos.size(); i++) // percorre os jogadores registados
			{
				// verifica o email e password
				if ((registos.get(i).getEmail().equals(email)) && (registos.get(i).getPassword().equals(password))) // se
																													// estiver
																													// registado:
				{
					jogadoratual = registos.get(i);
					double movimentos = registos.get(i).getAvgmoves();
					System.out.println("Benvindo de novo " + jogadoratual.getNome());
					System.out.println("O seu saldo de movimentos por jogo é, em média, " + movimentos);
					System.out.println(" "); // dar espaço
				}
			}

			if (jogadoratual == null) // se não estiver registado:
			{
				System.out.println("Jogador inexistente. Insira o nome do Jogador: "); // pede o nome primeiro
				String nome = sc.nextLine();
				//cria o registo, tendo em conta os dados já inseridos:
				jogadoratual = new Jogador(nome, email, password);
				System.out.println("Benvindo " + jogadoratual.getNome());
				System.out.println(" "); // dar espaço
				registos.add(jogadoratual);
				writePlayers(registos);
			}
		}

		else //caso do primeiro jogador, em que não existem registos efetuados
		{
			System.out.println("Jogador inexistente. Insira o nome do Jogador: "); // pede o nome primeiro
			String nome = sc.nextLine();
			//cria o registo, tendo em conta os dados já inseridos:
			jogadoratual = new Jogador(nome, email, password);
			System.out.println("Benvindo " + jogadoratual.getNome());
			System.out.println(" "); // dar espaço
			registos.add(jogadoratual);
			writePlayers(registos);
		}
		return jogadoratual;
	}

	public boolean atualizar_reg(Jogador jog, int n_mov)
	{
		// Média de movimentos
		jog.setN_jogos(jog.getN_jogos() + 1); // incremento do nº jogos ganhos
		jog.setAvgmoves(((jog.getAvgmoves() * (jog.getN_jogos() - 1.0) + n_mov) / jog.getN_jogos())); 
		System.out.println("Média de movimentos = " + jog.getAvgmoves());

		//atualização dos registos
		registos.add(jog);
		writePlayers(registos);

		return true;
	}
}
