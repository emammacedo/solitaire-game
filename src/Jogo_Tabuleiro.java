import java.util.ArrayList;

/* Exerc�cio de avalia��o 2
 * 
 * Ana Catarina Pais Pereira - 2019244449
 * Ema Margarida da Silva Macedo - 2019233271
 * 
 Jogo da Paci�ncia/Solit�rio */

//Esta classe implementa o Tabuleiro de Jogo//

public class Jogo_Tabuleiro
{
	// atributos da classe
	private Baralho baralho;
	private Pilha[] pilhasTransicao;
	private Pilha[] pilhasArmazenamento;

	public Jogo_Tabuleiro() // construtor
	{
		this.baralho = new Baralho(); // cria ArrayList c/ 52 cartas
		this.pilhasTransicao = new Pilha[7]; // cada elemento do vetor � uma pilha de transi��o, por ordem
		this.pilhasArmazenamento = new Pilha[7]; // cada elemento do vetor � uma pilha de armazenamento, por ordem
	}

	public Pilha getPilhaArm(int i)
	{
		return this.pilhasArmazenamento[i];
	}

	public Pilha getPilhaTra(int i)
	{
		return this.pilhasTransicao[i];
	}

	public void IniciarJogo()
	{
		this.baralho.Baralhar(); // baralhar as cartas

		for (int i = 0; i < 7; i++) // separar as cartas do baralho em 7 pilhas <-> as pilhas de Transi��o
		{
			this.pilhasTransicao[i] = new Pilha(baralho.getPilhas(i + 1)); // cada pilha fica com i+1 cartas: 1�
																			// pilha(0) tem 1 carta, 2� pilha (1) tem 2
																			// cartas...
		}

		// assume-se naoUsadas como de Armazenamento
		// naoUsada � esquerda (Monte)
		this.pilhasArmazenamento[0] = new Pilha(baralho.getPilhas(24)); // Total - cartasPilhaTransicao = cartasNUsadas;
																		// 52-28=24
		// naoUsada � direita (Disp.)
		this.pilhasArmazenamento[1] = new Pilha(new ArrayList<Carta>());
		// Armazenamento est�o vazias
		this.pilhasArmazenamento[2] = new Pilha(new ArrayList<Carta>());
		this.pilhasArmazenamento[3] = new Pilha(new ArrayList<Carta>());
		this.pilhasArmazenamento[4] = new Pilha(new ArrayList<Carta>());
		this.pilhasArmazenamento[5] = new Pilha(new ArrayList<Carta>());
		this.pilhasArmazenamento[6] = new Pilha(new ArrayList<Carta>());
		// at� 6, porque s�o 7 pilhas na parte de cima, sendo que a pilha entre Disp e Arm-1 (2) nunca vai ser
		// representada nem ter� cartas
	}

	public String toString() // Representa��o do tabuleiro: |5 espa�os| + 2 espa�os para a proxima pilha
	{
		String str = " Monte    Disp.             Arm-1    Arm-2    Arm-3    Arm-4\n";

		int maxCartas = 0; // em cada pilha

		// PILHAS DE CARTAS NAO USADAS + PILHAS DE ARMAZENAMENTO
		for (int j = 0; j < 4; j++) // percorre as linhas das pilhas
		{
			for (int i = 0; i < 7; i++)
			{
				if (this.pilhasArmazenamento[i].getTotal() > 0) // inicialmente, s� a 0 tem cartas -> � a �nica
																// representada
				{
					if (j == 0 || j == 3) // limites da carta
					{
						str += "-------  ";
					}
					else if (j == 1) // n�/letra da carta
					{
						// a �ltima carta da pilha � a que fica � mostra
						if (this.pilhasArmazenamento[i].getCarta(this.pilhasArmazenamento[i].getVis() - 1)
								.getNum() == 10) // caso do n� 10
							str += "|  " + this.pilhasArmazenamento[i]
									.getCarta(this.pilhasArmazenamento[i].getVis() - 1).getRep() + " |  ";
						else
							str += "|  " + this.pilhasArmazenamento[i]
									.getCarta(this.pilhasArmazenamento[i].getVis() - 1).getRep() + "  |  ";
					}
					else if (j == 2) // naipe da carta
					{
						str += "|  " + this.pilhasArmazenamento[i].getCarta(this.pilhasArmazenamento[i].getVis() - 1)
								.getNaipe() + "  |  ";
					}
				}

				else
				{
					str += "         "; // deixa espa�o entre cartas
				}

			}
			str += "\n"; // passa para a pr�xima linha da carta da pilha
		}

		str += "\n\n";
		str += " Pil-1    Pil-2    Pil-3    Pil-4    Pil-5    Pil-6    Pil-7\n";

		// PILHAS DE TRANSI��O
		for (int i = 0; i < 7; i++)
		{
			if (this.pilhasTransicao[i].getTotal() > maxCartas) // se houver cartas na pilha para representar
			{
				maxCartas = this.pilhasTransicao[i].getTotal(); // n� de cartas em cada pilha
			}
		}

		for (int c = 0; c < maxCartas; c++) // percorre cada carta da pilha
		{
			for (int j = 0; j < 4; j++)
			{
				for (int i = 0; i < 7; i++)
				{
					if (c < this.pilhasTransicao[i].getTotal())
					{
						if (j == 0 || j == 3) // parte de baixo da carta
						{
							str += "-------  ";
						}
						else if (j == 1) // n�/letra da carta
						{
							if (c < this.pilhasTransicao[i].getInvis())
							{
								str += "|     |  ";
							}
							else
							{
								if (this.pilhasTransicao[i].getCarta(c - this.pilhasTransicao[i].getInvis())
										.getNum() == 10) // se as carta visiveis forem um 10
								{
									str += "|  " + this.pilhasTransicao[i]
											.getCarta(c - this.pilhasTransicao[i].getInvis()).getRep() + " |  ";
								}
								else // se n�o forem um 10
								{
									str += "|  " + this.pilhasTransicao[i]
											.getCarta(c - this.pilhasTransicao[i].getInvis()).getRep() + "  |  "; 
								}
							}
						}

						else if (j == 2) // naipe da carta
						{
							if (c < this.pilhasTransicao[i].getInvis())
							{
								str += "|     |  ";
							}
							else
							{
								str += "|  " + this.pilhasTransicao[i].getCarta(c - this.pilhasTransicao[i].getInvis())
										.getNaipe() + "  |  ";
							}
						}
					}
					else
					{
						str += "         "; // adiciona o espa�o entre cartas
					}
				}
				str += "\n"; // passa para a proxima carta (a de baixo)
			}
		}
		return str;
	}

	// Para testar
	public static void main(String[] args)
	{
		Jogo_Tabuleiro jogo = new Jogo_Tabuleiro();
		jogo.IniciarJogo();
		System.out.println(jogo);
	}
}
