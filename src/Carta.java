
//Esta classe implementa a classe Carta//

public class Carta
{
	private int num;
	private String rep; // como string por causa da carta nº10
	private String naipe;
	private String cor;

	public Carta() // construtor vazio 
	{
	}

	public Carta(int num, String naipe)
	{
		// inicializar atributos do objeto
		this.num = num; // entre 1 e 13
		this.naipe = naipe; // C = copas, E = espadas, P = paus, O = ouros

		if (this.num == 1)
			this.rep = "A"; // Ás
		if (this.num == 2)
			this.rep = "2"; // Carta nº 2
		if (this.num == 3)
			this.rep = "3"; // Carta nº 3
		if (this.num == 4)
			this.rep = "4"; // Carta nº 4
		if (this.num == 5)
			this.rep = "5"; // Carta nº 5
		if (this.num == 6)
			this.rep = "6"; // Carta nº 6
		if (this.num == 7)
			this.rep = "7"; // Carta nº 7
		if (this.num == 8)
			this.rep = "8"; // Carta nº 8
		if (this.num == 9)
			this.rep = "9"; // Carta nº 9
		if (this.num == 10)
			this.rep = "10"; // Carta nº 10
		if (this.num == 11)
			this.rep = "J"; // Valete
		if (this.num == 12)
			this.rep = "Q"; // Dama
		if (this.num == 13)
			this.rep = "K"; // Rei

		if (naipe.equals("E") || naipe.equals("P")) //Espadas ou Paus
			this.cor = "preto";
		if (naipe.equals("C") || naipe.equals("O")) //Copas ou Ouros
			this.cor = "vermelho";
	}

	// Métodos:

	public int getNum()
	{
		return num;
	}

	public void setNum(int num)
	{
		this.num = num;
	}

	public String getRep()
	{
		return rep;
	}

	public void setRep(String rep)
	{
		this.rep = rep;
	}

	public String getNaipe()
	{
		return naipe;
	}

	public void setNaipe(String naipe)
	{
		this.naipe = naipe;
	}

	public String getCor()
	{
		return cor;
	}

	public void setCor(String cor)
	{
		this.cor = cor;
	}

/*	// Representação:
	public String toString()
	{
		if (this.rep.length() > 1) // caso da carta nº 10
		{
			String carta_rep = "-------\n";
			carta_rep += "|  " + this.rep + " |\n";
			carta_rep += "|  " + this.naipe + "  |\n";
			carta_rep += "-------\n";
			return carta_rep;
		}

		else
		{
			String carta_rep = "-------\n";
			carta_rep += "|  " + this.rep + "  |\n";
			carta_rep += "|  " + this.naipe + "  |\n";
			carta_rep += "-------\n";
			return carta_rep;
		}

	}

	// Para testar
	public static void main(String[] args)
	{
		Carta c = new Carta(10, "C");
		System.out.println(c);
	}*/

}
