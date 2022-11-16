import java.util.Date;

public class Garcom {
    private int codigoGarcom;
    private String nome;
    private Date dataNascimento;
    private String email;
    private String telefone;
    private String cpf;
    private String sexo;
    private String salariofixo;

    public Garcom(int codigoGarcom, String nome, Date dataNascimento, String email, String telefone, String cpf, String sexo, String salariofixo) {
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSalariofixo() {
        return salariofixo;
    }

    public void setSalariofixo(String salariofixo) {
        this.salariofixo = salariofixo;
    }
}
