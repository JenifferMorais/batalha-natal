import Element.Casa;

import java.util.Scanner;

public class Jogador {
    Scanner scanner = new Scanner(System.in);

    private String nome;
    private int pontuacao;
    private int totalPontuacao;
    private Tabuleiro tabuleiro;
    private int cordX, cordY;
    private int N;
    private int sub;
    private int torp;
    private int por;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
        this.totalPontuacao = 0;
    }
   
    public int getCordX() {
        return cordX;
    }

    public int getCordY() {
        return cordY;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public int getTPontuacao() {
        return totalPontuacao;
    }
    public String getNome() {
        return nome;
    }

    public Casa[][] getTabuleiro() {
        return tabuleiro.tabuleiro;
    }
    
    public void pontuacao(int pontuacao) {
        this.pontuacao += pontuacao;
        this.totalPontuacao += pontuacao;
    }

    public void novaPartida() {
        pontuacao = 0;
    }

    public int receberAtaque(int cordX, int cordY) {
        return tabuleiro.verificaPos(cordX, cordY);
    }

    public void tipo(int sub, int torp, int por) {
        if (sub > 0) {
        	System.out.println();
            System.out.print("1- Submarino; ");

        }
        if (torp > 0) {
        	System.out.println();
            System.out.print("2- Torpedeiro; ");

        }
        if (por > 0) {
            System.out.print("3- Porta Aviões; ");

        }

        System.out.println();

    }

    public void ajustarTabuleiro(int tabV, int tabH, int sub, int torp, int por) {
        this.sub = sub;
        this.torp = torp;
        this.por = por;

        boolean tipo, possivel = true;

        tabuleiro = new Tabuleiro(tabV, tabH);
        tabuleiro.mostrarTabuleiro();
        while (possivel) {

            tipo(sub, torp, por);
            System.out.println();
            System.out.println("Qual embarcação deseja posicionar:");
            int p = scanner.nextInt();
            
            switch (p) {
                case 1:
                    if (sub > 0) {
                        menu();
                        if (tabuleiro.verificaDis(this.cordX, this.cordY, posicionar(this.N), p)) {

                            System.out.println("É possivel colocar aqui");
                            tabuleiro.criarEmbarcacao(this.cordX, this.cordY, posicionar(this.N), p);
                            sub--;

                        } else {
                            System.out.println("Não é possivel colocar aqui");
                        }
                    } else {
                        System.out.println("Código indisponível!");
                    }

                    break;
                case 2:
                    if (torp > 0) {
                        menu();
                        if (tabuleiro.verificaDis(this.cordX, this.cordY, posicionar(this.N), p)) {
                            tabuleiro.criarEmbarcacao(this.cordX, this.cordY, posicionar(this.N), p);
                            System.out.println("É possivel colocar aqui");
                            torp--;

                        } else {
                            System.out.println("Não é possivel colocar aqui");
                        }
                    } else {
                        System.out.println("Código indisponível!");
                    }

                    break;
                case 3:
                    if (por > 0) {
                        menu();
                        if (tabuleiro.verificaDis(this.cordX, this.cordY, posicionar(this.N), p)) {
                            tabuleiro.criarEmbarcacao(this.cordX, this.cordY, posicionar(this.N), p);
                            System.out.println("É possivel colocar aqui");
                            por--;

                        } else {
                            System.out.println("Não é possivel colocar aqui");
                        }
                    } else {
                        System.out.println("Código indisponível!");

                    }
                    break;
            }

            if (sub == 0 && por == 0 && torp == 0) {
                possivel = false;
            }

        }
        tabuleiro.completarTab();
        tabuleiro.mostrarTabuleiro();
    }

    public boolean posicionar(int N) {

        // verticalmente verdadeiro
        // horizontalmente falso
        if (N == 1) {
            return true;
        } else {
            return false;
        }

    }

    
    public void menu() {

        System.out.println("Deseja posicionar verticalmente (digite 1) ou horizontamente (digite 2)?");
        this.N = scanner.nextInt();

        menupos();

    }

    public void menupos() {
        System.out.println("Digite a coordenada inicial vertical: ");
        this.cordX = scanner.nextInt();
        this.cordX--;

        System.out.println("Digite a coordenada inicial horizontal: ");
        this.cordY = scanner.nextInt();
        this.cordY--;
    }

 
}