import java.util.ArrayList;

public class Garcom {
    private int codigoGarcom;
    private String nome;
    private String dataNascimento;
    private String email;
    private String telefone;
    private String cpf;
    private Genero sexo;
    private double salariofixo;
    private ArrayList<Mesa> mesas;

    public Garcom(int codigoGarcom, String nome, String dataNascimento, String email, String telefone, String cpf, Genero sexo, double salariofixo) {
        this.codigoGarcom = codigoGarcom;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sexo = sexo;
        this.salariofixo = salariofixo;
    }

    public int getCodigoGarcom() {
        return codigoGarcom;
    }

    public void setCodigoGarcom(int codigoGarcom) {
        this.codigoGarcom = codigoGarcom;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Genero getSexo() {
        return sexo;
    }

    public void setSexo(Genero sexo) {
        this.sexo = sexo;
    }

    public double getSalariofixo() {
        return salariofixo;
    }

    public void setSalariofixo(double salariofixo) {
        this.salariofixo = salariofixo;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void addMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    public void removeMesa(Mesa mesa) {
        this.mesas.remove(mesa);
    }
}
