public class Mesa {
    private int codigoMesa;
    private int numeroMesa;
    private String situacao;
    private int capacidadeMaxima;

    public Mesa(int codigoMesa, int numeroMesa, String situacao, int capacidadeMaxima) {
        this.codigoMesa = codigoMesa;
        this.numeroMesa = numeroMesa;
        this.situacao = situacao;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getCodigoMesa() {
        return codigoMesa;
    }

    public void setCodigoMesa(int codigoMesa) {
        this.codigoMesa = codigoMesa;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }
}
