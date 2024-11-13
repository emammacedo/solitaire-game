import java.util.ArrayList;
import java.util.Scanner;


public class Pilha
{
	Scanner sc = new Scanner(System.in);
	// atributos
	private int total;
	private int invis;
	private int vis;

	// listas
	private ArrayList<Carta> cInvis;
	private ArrayList<Carta> cVis;

	public Pilha() // construtor vazio
	{
	}

	public Pilha(ArrayList<Carta> c) // Cria��o da pilha
	{
		if (c.size() > 0)
		{
			this.total = c.size();
			this.vis = 1; // apenas uma carta deve estar vis�vel
			this.invis = this.total - this.vis;

			this.cVis = new ArrayList<Carta>();
			this.cInvis = new ArrayList<Carta>();

			this.cVis.add(c.get(this.total - 1)); // a �ltima carta da pilha � a vis�vel
			c.remove(this.total - 1);
			this.cInvis = c;// cartas invis�veis correspondem ao resto da pilha
		}
		else // pilhas c/ 0 cartas --> inicio jogo - pilhas armazenamento
		{
			this.total = 0;
			this.vis = 0;
			this.invis = this.total - this.vis;

			this.cVis = new ArrayList<Carta>();
			this.cInvis = new ArrayList<Carta>();
		}
	}

	// __________________________________________________________________
	/// M�todos:

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getInvis()
	{
		return invis;
	}

	public void setInvis(int invis)
	{
		this.invis = invis;
	}

	public int getVis()
	{
		return vis;
	}

	public void setVis(int vis)
	{
		this.vis = vis;
	}

	public ArrayList<Carta> getcInvis()
	{
		return cInvis;
	}

	public void setcInvis(ArrayList<Carta> cInvis)
	{
		this.cInvis = cInvis;
	}

	public ArrayList<Carta> getcVis()
	{
		return cVis;
	}

	public void setcVis(ArrayList<Carta> cVis)
	{
		this.cVis = cVis;
	}

	// __________________________________________________________________
	/// Outros m�todos:

	// Devolve carta vis�vel da pilha
	public Carta getCarta(int i)
	{
		return this.cVis.get(i);
	}

	// Atualiza��o dos atributos
	public void atual_atrib()
	{
		this.vis = this.cVis.size();
		this.invis = this.cInvis.size();
		this.total = this.vis + this.invis;
	}

	// Torna todas as cartas da pilha vis�veis
	public void todasVis()
	{
		for (int i = 0; i < this.total - 1; i++)
		{
			this.cVis.add(this.cInvis.get(0));
			this.cInvis.remove(this.cInvis.get(0));
		}
		// carta determinada como v�sivel na constru��o da pilha (this.cVis.get(0)) � colocada no final da lista para
		// manter a ordem inicialmente definida
		this.cVis.add(this.cVis.get(0));
		this.cVis.remove(this.cVis.get(0));
		this.atual_atrib();
	}

	// Mostrar nova carta v�sivel
	public void mostrar_carta()
	{
		// n�o existem cartas vis�veis para mostrar + a pilha ainda tem cartas para mostrar:
		if (this.cInvis.size() > 0 && this.cVis.size() == 0)
		{
			this.cVis.add(cInvis.get(this.cInvis.size() - 1)); // �ltima carta da cInvis � adicionada � cVis
			this.cInvis.remove(cInvis.get(this.cInvis.size() - 1));
		}

	}

	// _____________Implementa��o - Regras do Jogo_________________________

	// ______________________________________________________________
	/// Transfer�ncia entre pilhas de TRANSI��O
	public boolean transf_T(Pilha OutraPilha, int n_cartas)
	{
		boolean transf = false;

		// n_cartas = n� de cartas que jogador deseja mover
		if (n_cartas <= this.getVis())
		{
			if (OutraPilha.cVis.size() > 0)
			{
				int c1 = this.cVis.get(this.cVis.size() - n_cartas).getNum(); // 1� vis�vel selecionada pelo jogador
				int c2 = OutraPilha.cVis.get(OutraPilha.cVis.size() - 1).getNum();

				if (c1 == c2 - 1) // n� carta da Pilha = n� carta da OutraPilha - 1
				{
					if (this.cVis.get(this.cVis.size() - n_cartas).getCor() != OutraPilha.cVis
							.get(OutraPilha.cVis.size() - 1).getCor()) // cores diferentes
					{
						transf = true;

					}
				}
			}

			else // OutraPilha vazia --> s� pode receber um K (rei)
			{
				if (this.cVis.get(this.cVis.size() - n_cartas).getNum() == 13) // 1� vis�vel selecionada pelo jogador =
																				// K
				{
					transf = true;
				}
			}
		}

		if (transf == true) // se houver transfer�ncia de cartas
		{
			int tamanho = this.cVis.size();
			for (int i = 1; i <= n_cartas; i++)
			{
				/*
				 * adi��o da 1� carta selecionada � OutraPilha e remo��o da mesma da Pilha; a carta de this.cVis
				 * seguinte passa a ocupar o �ndice antes ocupado pela carta removida --> indice constante
				 */
				OutraPilha.cVis.add(this.cVis.get(tamanho - n_cartas));
				this.cVis.remove(this.cVis.get(tamanho - n_cartas));
			}

			this.mostrar_carta();
			this.atual_atrib();
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada imposs�vel");

		return transf;
	}

	// ______________________________________________________________
	/// Transfer�ncia pilhas TRANSI��O / N�O USADAS--> ARMAZENAMENTO

	// Pilhas de armazenamento / n�o usadas --> todas as cartas s�o consideradas vis�veis
	public boolean transf_IgualNaipe(Pilha OutraPilha)
	{
		// Pilha --> Transi��o/ N�o Usadas
		// OutraPilha --> Armazenamento
		// Cartas movidas uma a uma; organizadas por ordem crescente

		boolean transf = false;

		if (this.getVis() > 0) // existem cartas para mover
		{
			if (OutraPilha.total == 0) // Pilha Armazenamento vazia
			{
				if (this.cVis.get(this.cVis.size() - 1).getNum() == 1) // S� o �s pode ser movido
					transf = true;
			}
			else // Pilha Armazenamento c/ cartas
			{
				// Carta � a seguinte e superior � que est� na pilha Armazenamento
				if (this.cVis.get(this.cVis.size() - 1)
						.getNum() == OutraPilha.cVis.get(OutraPilha.cVis.size() - 1).getNum() + 1)
				{
					// Tem igual naipe
					if (this.cVis.get(this.cVis.size() - 1).getNaipe() == OutraPilha.cVis
							.get(OutraPilha.cVis.size() - 1).getNaipe())
						transf = true;
				}
			}
		}

		if (transf == true) // se houver transfer�ncia de cartas
		{
			//Adi��o � pilha Armazenamento e elimina��o da Pilha Transi��o/N usada
			OutraPilha.cVis.add(this.cVis.get(this.cVis.size() - 1)); 
			this.cVis.remove(this.cVis.get(this.cVis.size() - 1)); 
			
			this.mostrar_carta(); //para Pilha Transi��o
			this.atual_atrib(); 
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada imposs�vel");

		return transf;
	}

	// __________________________________________________________________
	/// Transfer�ncia entre pilhas N�O USADAS (DISP + MONTE)
	// Pilhas n�o usadas --> todas as cartas s�o consideradas vis�veis
	
	// Monte --> Disp
	public boolean passar_Cartas(Pilha OutraPilha)
	{
		// Pilha --> Monte 
		// OutraPilha --> Disp - Vazia no in�cio 
		
		boolean transf = false;

		if (this.cVis.size() > 0) // Existem cartas em Monte para mostrar
		{
			transf = true;
			OutraPilha.cVis.add(this.cVis.get(this.cVis.size() - 1)); // adi��o da carta de cima do Monte ao Disp
			this.cVis.remove(this.cVis.get(this.cVis.size() - 1));// elimina��o
			this.atual_atrib();
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada Imposs�vel");

		return transf;
	}

	// Disp --> Monte
	public boolean rebobinar(Pilha OutraPilha)
	{
		// Pilha --> Monte
		// Outra Pilha --> Disp - Vazia no in�cio

		boolean transf = false;
		if (this.cVis.size() == 0) // Monte vazio
		{
			for (int i = 0; i < OutraPilha.total; i++) // Enquanto Disp tiver cartas
			{
				transf = true;
				this.cVis.add(OutraPilha.cVis.get(OutraPilha.cVis.size() - 1)); // adi��o da carta de cima de Disp ao Monte
				OutraPilha.cVis.remove(OutraPilha.cVis.get(OutraPilha.cVis.size() - 1)); // elimina��o
			}
			this.atual_atrib();
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada Imposs�vel");

		return transf;
	}

	// __________________________________________________________________
	/// Impress�o da pilha
	public String toString()
	{
		String pilha_rep = "";
		for (int j = 0; j < this.invis; j++) // print das cartas invis�veis
		{
			pilha_rep = "-------\n";
			pilha_rep += "|       |\n";
			pilha_rep += "|       |\n";
			pilha_rep += "-------";
		}
		for (int j = 0; j < this.vis; j++) // print das cartas vis�veis, utilizando o m�todo toString da classe Carta
		{
			pilha_rep += this.cVis.get(j).toString();
		}
		return pilha_rep;
	}
}
