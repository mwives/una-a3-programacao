public class Mesa {
    private int codigoMesa;
    private int numeroMesa;
    private SituacaoMesa situacao;
    private int capacidadeMaxima;
    private Garcom garcomResponsavel;

    public Mesa(int codigoMesa, int numeroMesa, int capacidadeMaxima, Garcom garcomResponsavel) {
        this.codigoMesa = codigoMesa;
        this.numeroMesa = numeroMesa;
        this.situacao = SituacaoMesa.LIVRE;
        this.capacidadeMaxima = capacidadeMaxima;
        this.garcomResponsavel = garcomResponsavel;
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

    public SituacaoMesa getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoMesa situacao) {
        this.situacao = situacao;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Garcom getGarcomResponsavel() {
        return garcomResponsavel;
    }

    public void setGarcomResponsavel(Garcom garcomResponsavel) {
        this.garcomResponsavel = garcomResponsavel;
    }
}
