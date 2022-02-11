package Element;

public abstract class Casa {
    protected String nome;
    protected int pontos;
    protected boolean atingido;

    public int getPontos() {
        if (!atingido) {
            //ele chama o toString que esta configurado para mostrar o que foi atingido, o atributo nome está sobreescrito
            System.out.println("você atingiu: " + toString());
            atingido = true;
            return pontos;
        }
        return -1;
    }

    public boolean isAtingido() {
        return atingido;
    }

    public boolean isValido() {
        //se não for uma instancia de agua entao é valido
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
