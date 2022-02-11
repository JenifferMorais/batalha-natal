import Element.*;

public class Tabuleiro {

    Casa[][] tabuleiro;

    public Tabuleiro(int tabV, int tabH) {
        tabuleiro = new Casa[tabV][tabH];
    }

    public void mostrarTabuleiro() {
        //mostrar tabuleiro
        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                //mostrar o que tem ou a coordenada na matriz
                System.out.print((tabuleiro[i][j] != null ? tabuleiro[i][j].toString().charAt(0) : ((i + 1) + "," + (j + 1))) + " | ");
            }
            System.out.println();
        }
    }

    public int verificaPos(int cordX, int cordY) {
        //verificar pontuação do local, se for -1 a pontuação ja foi atacada
        int pontos = tabuleiro[cordX][cordY].getPontos();
        if (pontos == -1) {
            System.out.print("Essa posição ja foi atacada!");
            return 0;
        }

        return pontos;
    }

    public boolean verificaDis(int cordX, int cordY, boolean cord, int tipo) {
        // cord verdadeiro vertical
        int i = 0;
        int p = 0;
        boolean teste;
        int limite;
        if (cord) {
            i = cordX;
            limite = tabuleiro.length;
        } else {
            i = cordY;
            limite = tabuleiro[0].length;
        }

        switch (tipo) {
            case 1:
                p = 2;
                break;
            case 2:
                p = 3;
                break;
            case 3:
                p = 4;
                break;
        }

        while (i < (i + p)) {
            if (i >= limite)
                return false;
            if (cord) {
                teste = (tabuleiro[i][cordY] == null);
            } else {
                teste = (tabuleiro[cordX][i] == null);
            }
            if (teste) {
                i++;
                p--;
            } else {
                return false;
            }
        }
        return true;
    }

    public void criarEmbarcacao(int cordX, int cordY, boolean cord, int tipo) {
        int p = 0;
        int i;
        Casa pos;
        boolean teste;
        if (cord) {
            i = cordX;
        } else {
            i = cordY;
        }

        switch (tipo) {
            case 1:
                p = 2;
                break;
            case 2:
                p = 3;
                break;
            case 3:
                p = 4;
                break;
        }
        while (i < (i + p)) {
            switch (tipo) {
                case 1:
                    if (cord) {
                        // recebe a posição x, y e o objeto para colocar no tabuleiro.
                        posiciona(i, cordY, new Submarino());
                    } else {
                        posiciona(cordX, i, new Submarino());
                    }
                    break;
                case 2:
                    if (cord) {
                        posiciona(i, cordY, new Torpedeiro());
                    } else {
                        posiciona(cordX, i, new Torpedeiro());
                    }
                    break;
                case 3:
                    if (cord) {
                        posiciona(i, cordY, new PortaAvioes());
                    } else {
                        posiciona(cordX, i, new PortaAvioes());
                    }
                    break;
            }
            p--;
            i++;
        }
    }

    private void posiciona(int x, int y, Casa peca) {
        tabuleiro[x][y] = peca;
    }

    //o que estiver nulo vai ser completado com agua
    public void completarTab() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == null) {
                    posiciona(i, j, new Agua());
                }
            }
        }
    }
}
