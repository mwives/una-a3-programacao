import java.util.Date;

public class Garcom {
    private int codigogarcom;
    private String nome;
    private Date dataNascimento;
    private String email;
    private String telefone;

    private String cpf;
    private String sexo;
    private String salriofixo;


    public Garcom(String nome, Date dataNascimento, String email, String telefone, String cpf, String sexo, String salriofixo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.sexo = sexo;
        this.salriofixo = salriofixo;
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

    public String getSalriofixo() {
        return salriofixo;
    }

    public void setSalriofixo(String salriofixo) {
        this.salriofixo = salriofixo;
    }
}
