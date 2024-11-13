import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/* Exerc�cio de avalia��o 2
 * 
 * Ana Catarina Pais Pereira - 2019244449
 * Ema Margarida da Silva Macedo - 2019233271
 * 
 Jogo da Paci�ncia/Solit�rio */

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

	// Login e identifica��o do jogador, atrav�s do email e password
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

		if (registos.size() > 0) // se existirem j� registos efetuados
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
					System.out.println("O seu saldo de movimentos por jogo �, em m�dia, " + movimentos);
					System.out.println(" "); // dar espa�o
				}
			}

			if (jogadoratual == null) // se n�o estiver registado:
			{
				System.out.println("Jogador inexistente. Insira o nome do Jogador: "); // pede o nome primeiro
				String nome = sc.nextLine();
				//cria o registo, tendo em conta os dados j� inseridos:
				jogadoratual = new Jogador(nome, email, password);
				System.out.println("Benvindo " + jogadoratual.getNome());
				System.out.println(" "); // dar espa�o
				registos.add(jogadoratual);
				writePlayers(registos);
			}
		}

		else //caso do primeiro jogador, em que n�o existem registos efetuados
		{
			System.out.println("Jogador inexistente. Insira o nome do Jogador: "); // pede o nome primeiro
			String nome = sc.nextLine();
			//cria o registo, tendo em conta os dados j� inseridos:
			jogadoratual = new Jogador(nome, email, password);
			System.out.println("Benvindo " + jogadoratual.getNome());
			System.out.println(" "); // dar espa�o
			registos.add(jogadoratual);
			writePlayers(registos);
		}
		return jogadoratual;
	}

	public boolean atualizar_reg(Jogador jog, int n_mov)
	{
		// M�dia de movimentos
		jog.setN_jogos(jog.getN_jogos() + 1); // incremento do n� jogos ganhos
		jog.setAvgmoves(((jog.getAvgmoves() * (jog.getN_jogos() - 1.0) + n_mov) / jog.getN_jogos())); 
		System.out.println("M�dia de movimentos = " + jog.getAvgmoves());

		//atualiza��o dos registos
		registos.add(jog);
		writePlayers(registos);

		return true;
	}
}
