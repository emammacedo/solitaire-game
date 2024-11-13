import java.util.Scanner;

public class Jogo
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		boolean novo_login = true; // para permitir login de outro jogador

		do //faz login/registo do jogador
		{

			boolean jogar_novamente = true;

			Jogador jogat = new Registo().getJogador(); 

			do 	//Começa o jogo:
			{
				Jogo_Tabuleiro Tab = new Jogo_Tabuleiro(); // criação do tabuleiro
				Tab.IniciarJogo(); // iniciar o jogo --> criação das pilhas
				System.out.println(Tab); // impressão

				// Torna todas as cartas do Monte visíveis (única das PilhaArm do tabuleiro c/ cartas)
				Tab.getPilhaArm(0).todasVis();

				// elementos auxiliares
				boolean jogo_cont = true;
				boolean jog_pos = false;

				int n_mov = 0;

				do
				{
					System.out.println("Número de movimentos do jogo atual = " + n_mov);
					System.out.println("P->Passar Cartas | R->Rebobinar | M->Mover | T->Terminar Jogo");
					char jogada = sc.next().charAt(0); // retorna o 1º caracter escrito
					sc.nextLine();

					if (jogada == 'M')
					{
						System.out.println("Insira origem (Disp | Arm- | Pil-)");
						String pOrigem = sc.nextLine();

						System.out.println("Insira destino (Arm- | Pil-)");
						String pFinal = sc.nextLine();

						if (pOrigem.charAt(0) == 'P' && pFinal.charAt(0) == 'P') // movimento entre pilhas de Transição
						{
							int origem = pOrigem.charAt(pOrigem.length() - 1) - '0'; // pilha origem
							int fim = pFinal.charAt(pFinal.length() - 1) - '0'; // pilha destino

							System.out.println("Quantas cartas deseja mover?");
							int nCARTA = sc.nextInt();
							sc.nextLine();

							jog_pos = Tab.getPilhaTra(origem - 1).transf_T(Tab.getPilhaTra(fim - 1), nCARTA); // método
																												// transferência

						}
						else if (pOrigem.charAt(0) == 'P' && pFinal.charAt(0) == 'A') // transição --> armazenamento
						{
							int origem = pOrigem.charAt(pOrigem.length() - 1) - '0';
							int fim = pFinal.charAt(pFinal.length() - 1) - '0';

							jog_pos = Tab.getPilhaTra(origem - 1).transf_IgualNaipe(Tab.getPilhaArm(fim + 2));
						}
						else if (pOrigem.charAt(0) == 'D' && pFinal.charAt(0) == 'A') // baralho --> armazenamento
						{
							int fim = pFinal.charAt(pFinal.length() - 1) - '0';

							jog_pos = Tab.getPilhaArm(1).transf_IgualNaipe(Tab.getPilhaArm(fim + 2));

						}
						else if (pOrigem.charAt(0) == 'A' && pFinal.charAt(0) == 'P') // armazenamento --> transição
						{
							int origem = pOrigem.charAt(pOrigem.length() - 1) - '0';
							int fim = pFinal.charAt(pFinal.length() - 1) - '0';

							jog_pos = Tab.getPilhaArm(origem + 2).transf_T(Tab.getPilhaTra(fim - 1), 1);
						}
						else if (pOrigem.charAt(0) == 'D' && pFinal.charAt(0) == 'P')
						{
							int fim = pFinal.charAt(pFinal.length() - 1) - '0';

							jog_pos = Tab.getPilhaArm(1).transf_T(Tab.getPilhaTra(fim - 1), 1);
						}
						else
						{
							// Por exemplo, retirar/acrescentar cartas ao Monte
							System.out.println("Jogada impossível");
						}
						System.out.println(Tab);
					}
					else if (jogada == 'P') // Passar Cartas
					{
						jog_pos = Tab.getPilhaArm(0).passar_Cartas(Tab.getPilhaArm(1));
						System.out.println(Tab);
					}
					else if (jogada == 'R') // Rebobinar
					{
						jog_pos = Tab.getPilhaArm(0).rebobinar(Tab.getPilhaArm(1));
						System.out.println(Tab);
					}
					else if (jogada == 'T') // Terminar Jogo
					{
						jogo_cont = false;
						System.out.println("Jogo terminado");
					}
					else //p. exemplo, jogador inserir caracateres não válidos
					{
						System.out.println("Jogada impossível");
					}

					// Atualização nº movimentos
					if (jog_pos == true)
						n_mov = n_mov + 1;

					// Jogo Ganho: se as de armazenamento estiverem cheias
					if (Tab.getPilhaArm(3).getTotal() + Tab.getPilhaArm(4).getTotal() + Tab.getPilhaArm(5).getTotal()
							+ Tab.getPilhaArm(6).getTotal() == 52)
					{
						jogo_cont = false;
						System.out.println("Parabéns!! " + jogat.getNome() + "! Ganhou!!!");
						
						//Cálculo da média + atualização de dados
						boolean b = new Registo().atualizar_reg(jogat, n_mov);
					}

				}
				while (jogo_cont == true);

				// Para jogar novamente:
				System.out.println("Quer jogar novamente? Sim ou Não?");
				String resposta = sc.nextLine();
				System.out.println("\n");
				if (resposta.charAt(0) == 's' || resposta.charAt(0) == 'S')
				{
					jogar_novamente = true;
				}
				else
				{
					System.out.println("Logout");
					System.out.println("--------------------------------------------------------------\n");
					jogar_novamente = false;
				}

			}
			while (jogar_novamente == true); //se escolher jogar novamente, volta a começar o jogo
		}
		while (novo_login == true); //se escolher sair do jogo, permite um novo login/registo

		sc.close();
	}
}
