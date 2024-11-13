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

	public Pilha(ArrayList<Carta> c) // Criação da pilha
	{
		if (c.size() > 0)
		{
			this.total = c.size();
			this.vis = 1; // apenas uma carta deve estar visível
			this.invis = this.total - this.vis;

			this.cVis = new ArrayList<Carta>();
			this.cInvis = new ArrayList<Carta>();

			this.cVis.add(c.get(this.total - 1)); // a última carta da pilha é a visível
			c.remove(this.total - 1);
			this.cInvis = c;// cartas invisíveis correspondem ao resto da pilha
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
	/// Métodos:

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
	/// Outros métodos:

	// Devolve carta visível da pilha
	public Carta getCarta(int i)
	{
		return this.cVis.get(i);
	}

	// Atualização dos atributos
	public void atual_atrib()
	{
		this.vis = this.cVis.size();
		this.invis = this.cInvis.size();
		this.total = this.vis + this.invis;
	}

	// Torna todas as cartas da pilha visíveis
	public void todasVis()
	{
		for (int i = 0; i < this.total - 1; i++)
		{
			this.cVis.add(this.cInvis.get(0));
			this.cInvis.remove(this.cInvis.get(0));
		}
		// carta determinada como vísivel na construção da pilha (this.cVis.get(0)) é colocada no final da lista para
		// manter a ordem inicialmente definida
		this.cVis.add(this.cVis.get(0));
		this.cVis.remove(this.cVis.get(0));
		this.atual_atrib();
	}

	// Mostrar nova carta vísivel
	public void mostrar_carta()
	{
		// não existem cartas visíveis para mostrar + a pilha ainda tem cartas para mostrar:
		if (this.cInvis.size() > 0 && this.cVis.size() == 0)
		{
			this.cVis.add(cInvis.get(this.cInvis.size() - 1)); // última carta da cInvis é adicionada à cVis
			this.cInvis.remove(cInvis.get(this.cInvis.size() - 1));
		}

	}

	// _____________Implementação - Regras do Jogo_________________________

	// ______________________________________________________________
	/// Transferência entre pilhas de TRANSIÇÃO
	public boolean transf_T(Pilha OutraPilha, int n_cartas)
	{
		boolean transf = false;

		// n_cartas = nº de cartas que jogador deseja mover
		if (n_cartas <= this.getVis())
		{
			if (OutraPilha.cVis.size() > 0)
			{
				int c1 = this.cVis.get(this.cVis.size() - n_cartas).getNum(); // 1º visível selecionada pelo jogador
				int c2 = OutraPilha.cVis.get(OutraPilha.cVis.size() - 1).getNum();

				if (c1 == c2 - 1) // nº carta da Pilha = nº carta da OutraPilha - 1
				{
					if (this.cVis.get(this.cVis.size() - n_cartas).getCor() != OutraPilha.cVis
							.get(OutraPilha.cVis.size() - 1).getCor()) // cores diferentes
					{
						transf = true;

					}
				}
			}

			else // OutraPilha vazia --> só pode receber um K (rei)
			{
				if (this.cVis.get(this.cVis.size() - n_cartas).getNum() == 13) // 1º visível selecionada pelo jogador =
																				// K
				{
					transf = true;
				}
			}
		}

		if (transf == true) // se houver transferência de cartas
		{
			int tamanho = this.cVis.size();
			for (int i = 1; i <= n_cartas; i++)
			{
				/*
				 * adição da 1ª carta selecionada à OutraPilha e remoção da mesma da Pilha; a carta de this.cVis
				 * seguinte passa a ocupar o índice antes ocupado pela carta removida --> indice constante
				 */
				OutraPilha.cVis.add(this.cVis.get(tamanho - n_cartas));
				this.cVis.remove(this.cVis.get(tamanho - n_cartas));
			}

			this.mostrar_carta();
			this.atual_atrib();
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada impossível");

		return transf;
	}

	// ______________________________________________________________
	/// Transferência pilhas TRANSIÇÃO / NÃO USADAS--> ARMAZENAMENTO

	// Pilhas de armazenamento / não usadas --> todas as cartas são consideradas visíveis
	public boolean transf_IgualNaipe(Pilha OutraPilha)
	{
		// Pilha --> Transição/ Não Usadas
		// OutraPilha --> Armazenamento
		// Cartas movidas uma a uma; organizadas por ordem crescente

		boolean transf = false;

		if (this.getVis() > 0) // existem cartas para mover
		{
			if (OutraPilha.total == 0) // Pilha Armazenamento vazia
			{
				if (this.cVis.get(this.cVis.size() - 1).getNum() == 1) // Só o Ás pode ser movido
					transf = true;
			}
			else // Pilha Armazenamento c/ cartas
			{
				// Carta é a seguinte e superior à que está na pilha Armazenamento
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

		if (transf == true) // se houver transferência de cartas
		{
			//Adição à pilha Armazenamento e eliminação da Pilha Transição/N usada
			OutraPilha.cVis.add(this.cVis.get(this.cVis.size() - 1)); 
			this.cVis.remove(this.cVis.get(this.cVis.size() - 1)); 
			
			this.mostrar_carta(); //para Pilha Transição
			this.atual_atrib(); 
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada impossível");

		return transf;
	}

	// __________________________________________________________________
	/// Transferência entre pilhas NÃO USADAS (DISP + MONTE)
	// Pilhas não usadas --> todas as cartas são consideradas visíveis
	
	// Monte --> Disp
	public boolean passar_Cartas(Pilha OutraPilha)
	{
		// Pilha --> Monte 
		// OutraPilha --> Disp - Vazia no início 
		
		boolean transf = false;

		if (this.cVis.size() > 0) // Existem cartas em Monte para mostrar
		{
			transf = true;
			OutraPilha.cVis.add(this.cVis.get(this.cVis.size() - 1)); // adição da carta de cima do Monte ao Disp
			this.cVis.remove(this.cVis.get(this.cVis.size() - 1));// eliminação
			this.atual_atrib();
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada Impossível");

		return transf;
	}

	// Disp --> Monte
	public boolean rebobinar(Pilha OutraPilha)
	{
		// Pilha --> Monte
		// Outra Pilha --> Disp - Vazia no início

		boolean transf = false;
		if (this.cVis.size() == 0) // Monte vazio
		{
			for (int i = 0; i < OutraPilha.total; i++) // Enquanto Disp tiver cartas
			{
				transf = true;
				this.cVis.add(OutraPilha.cVis.get(OutraPilha.cVis.size() - 1)); // adição da carta de cima de Disp ao Monte
				OutraPilha.cVis.remove(OutraPilha.cVis.get(OutraPilha.cVis.size() - 1)); // eliminação
			}
			this.atual_atrib();
			OutraPilha.atual_atrib();
		}
		else
			System.out.println("Jogada Impossível");

		return transf;
	}

	// __________________________________________________________________
	/// Impressão da pilha
	public String toString()
	{
		String pilha_rep = "";
		for (int j = 0; j < this.invis; j++) // print das cartas invisíveis
		{
			pilha_rep = "-------\n";
			pilha_rep += "|       |\n";
			pilha_rep += "|       |\n";
			pilha_rep += "-------";
		}
		for (int j = 0; j < this.vis; j++) // print das cartas visíveis, utilizando o método toString da classe Carta
		{
			pilha_rep += this.cVis.get(j).toString();
		}
		return pilha_rep;
	}
}
