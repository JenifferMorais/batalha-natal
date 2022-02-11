import Element.Casa;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jogador jogadores[] = new Jogador[2];
        String nome;
        boolean novojogo = false;
        int tabV, tabH, sub, torp, por, totalPontos;


        System.out.println("Bem vindo a Batalha Naval 1.0");
        System.out.println("Insira o nome dos Jogadores");
        for (int i = 0; i < 2; i++) {
            System.out.print("Insira o nome do Jogador " + (i + 1) + ": ");
            nome = scanner.next();
            jogadores[i] = new Jogador(nome);
        }

        do {
            jogadores[0].novaPartida();
            jogadores[1].novaPartida();
            System.out.println("Para esta partida, primeiramente, vamos configurar os tabuleiros");
            System.out.println("Digite o tamanho da tabela verticalmente:");
            tabV = scanner.nextInt();
            System.out.println("Digite o tamanho da tabela horizontalmente:");
            tabH = scanner.nextInt();
            System.out.println("Digite a quantidade de Submarinos:");
            sub = scanner.nextInt();
            System.out.println("Digite a quantidade de Torpedeiros:");
            torp = scanner.nextInt();
            System.out.println("Digite a quantidade de Porta-Avi�o:");
            por = scanner.nextInt();
            totalPontos = sub * 2 + torp * 3 + por * 4;
            System.out.println("Agora vamos configurar o tabuleiro para:" + jogadores[0].getNome());
            System.out.println();
            jogadores[0].ajustarTabuleiro(tabV, tabH, sub, torp, por);
            limpaTela();
            System.out.println();
            System.out.println("Agora vamos configurar o tabuleiro para:" + jogadores[1].getNome());
            System.out.println();
            jogadores[1].ajustarTabuleiro(tabV, tabH, sub, torp, por);
            limpaTela();


            int i = 0;
            int k = 0;
            //Para jogar de novo nenhum dos jogadores podem ter a pontua��o maxima atingida
            while (jogadores[0].getPontuacao() < totalPontos && jogadores[1].getPontuacao() < totalPontos) {
                //no printf voce tem um padrao e as variaveis que v�o substitui-la. %s string, para substituir por uma variavel
                //%n quebra de linha
                System.out.printf("Jogador %s Escolha uma posi��o para seu ataque: %n", jogadores[i].getNome());
                //ele chama o proprio do tabuleiro do jogo e do adversario
                System.out.println(getTabs(jogadores[i].getTabuleiro(), jogadores[(i + 1) % 2].getTabuleiro()));
                jogadores[i].menupos();

                //pontua��o da jogada
                int pontos = jogadores[(i + 1) % 2].receberAtaque(jogadores[i].getCordX(), jogadores[i].getCordY());
                // Tabuleiro p�s jogada
                System.out.println(getTabs(jogadores[i].getTabuleiro(), jogadores[(i + 1) % 2].getTabuleiro()));
                //se � diferente de zero, para marcar a pontua��o e mudar o jogador
                if (pontos != 0) {
                    //se o jogador tiver acertado e ganhado ponto ele continua jogando
                    jogadores[i].pontuacao(pontos);
                } else {
                    //do contrario muda o jogador
                    i = (i + 1) % 2;
                }

            }
            System.out.println("Partida encerrada!");
            if (jogadores[0].getPontuacao() > jogadores[1].getPontuacao()) {
                System.out.print(jogadores[0].getNome());}
            else {
                System.out.print(jogadores[1].getNome());
            System.out.println(" Venceu a partida!");
            //no printf voce tem um padrao e as variaveis que v�o substitui-la. %s string, para substituir por uma variavel
            // %d passa numeros inteiro
            // %n pula linha
            // %s substitui
            System.out.printf("Pontua��o parcial:%n %s tem %d%n%s tem %d%n", jogadores[0].getNome(), jogadores[0].getTPontuacao(), jogadores[1].getNome(), jogadores[1].getTPontuacao());
           
            //O m�todo toLowerCase() retorna a string chamada convertida para min�sculo.
            //contains � um teste para saber se cont�m a palavra que esta dentro dele, se for verdade novo jogo recebe o true, senao receber false;
            System.out.println("Quer jogar uma nova partida? sim /n�o");
            novojogo = scanner.next().toLowerCase().contains("sim") ? true : false;
            }
        } while (novojogo);
        if (jogadores[0].getTPontuacao() > jogadores[1].getTPontuacao())
            System.out.print(jogadores[0].getNome());
        else
            System.out.print(jogadores[1].getNome());
        System.out.println("Fez mais pontos");
        System.out.printf("Pontua��o Final:%n %s tem %d pontos %n%s tem %d pontos%n",jogadores[0].getNome(), jogadores[0].getTPontuacao(), jogadores[1].getNome(), jogadores[1].getTPontuacao());

    }

    public static String getTabs(Casa[][] tab1, Casa[][] tab2) {
        String linlJogador, linlOponente, linhas = "";

        for (int i = 0; i < tab1.length; i++) {
            linlJogador = "| ";
            linlOponente = "| ";
            for (int j = 0; j < tab1[0].length; j++) {
                // Jogador atual, se for valido e atingido marca o x no tabuleiro do que foi afundado, se nao imprime qual a embarca��o naquela posi��o ou agua.
                //charAt tira o caracter daquela posi��o
                // Usando Ternario, se... ? entao "..." : senao...;
                linlJogador += ((tab1[i][j].isValido() && tab1[i][j].isAtingido() ? "X" : tab1[i][j].toString().charAt(0)) + " | ");
                // Jogador oponente,
                linlOponente += ((tab2[i][j].isValido() && tab2[i][j].isAtingido() ? "1" : tab2[i][j].isAtingido() ? "X" : " ") + " | ");
            }
            linhas += linlJogador + "\t\t" + linlOponente + "\n"; //junta uma linha e outra
        }
        return linhas;
    }

    public static void limpaTela() {
        System.out.println();
        for (int i = 0; i <40; i++)
        	System.out.println();

    }
}
