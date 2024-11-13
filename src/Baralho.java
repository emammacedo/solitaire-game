import java.util.ArrayList;
import java.util.Random;

/* Exercício de avaliação 2
 * 
 * Ana Catarina Pais Pereira - 2019244449
 * Ema Margarida da Silva Macedo - 2019233271
 * 
 Jogo da Paciência/Solitário */

//Esta classe implementa a classe Baralho//

public class Baralho
{	
	private ArrayList<Carta> cartas;
	
	public Baralho()
	{
		//Baralho de cartas contém 52 cartas, com 4 naipes e 13 representações (9 numeros + 4 figuras)
		String[] naipes = {"E","P","O","C"};
		
		//criar as cartas
		cartas = new ArrayList<Carta>(); 
		
		for (int i=1; i <= 13; i++) //representações
		{
			for (int j=0; j<4; j++) //naipes
				cartas.add(new Carta(i, naipes[j])); //cria 13 (i) cartas de cada naipe (j)
		}
	}
	
	public void Baralhar() //através de permutações 
	{
		Random random = new Random();
		ArrayList<Carta> cartasbaralho = new ArrayList<Carta>();
		
		for (int i = 0; i < 52; i++) //percorre as 52 cartas
		{
			int num = random.nextInt(cartas.size());
			cartasbaralho.add(this.cartas.get(num)); //adiciona 1 carta ao baralho
			this.cartas.remove(num);	//remove essa carta
		}
		for (int i = 0; i < cartasbaralho.size(); i++) 
			this.cartas.add(cartasbaralho.get(i)); //volta a adicionar às cartas	
	}

	public ArrayList<Carta> getPilhas(int n) // dividir todas as cartas pelas pilhas respetivas
	{
		ArrayList<Carta> aux = new ArrayList<Carta>(); //todas as cartas da pilha (Vis e Invis)
		
		for (int i = 0; i < n; i++)
			aux.add(this.cartas.get(i)); //adição das cartas à Pilha
			
		for (int j = 0; j < n; j++) 
			this.cartas.remove(0); //remover do baralho as cartas adicionadas

		return aux;
	}
	
	public int getSize() //tamanho do baralho
	{
		return this.cartas.size();
	}
	
	public String toString() //representação
	{
		String str = "";
		
		for (int i = 0; i < this.cartas.size(); i++)
		{
			str += this.cartas.get(i).toString();
		}
		return str;
	}
}

