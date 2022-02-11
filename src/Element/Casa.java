package Element;

public abstract class Casa {
    protected String nome;
    protected int pontos;
    protected boolean atingido;

    public int getPontos() {
        if (!atingido) {
            //ele chama o toString que esta configurado para mostrar o que foi atingido, o atributo nome est� sobreescrito
            System.out.println("voc� atingiu: " + toString());
            atingido = true;
            return pontos;
        }
        return -1;
    }

    public boolean isAtingido() {
        return atingido;
    }

    public boolean isValido() {
        //se n�o for uma instancia de agua entao � valido
        if (!(this instanceof Agua)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nome;
    }
}
