package POOI;

public abstract class Pessoa {
    private static int contadorId = 0; // Contador para gerar um ID único
    private int id;
    private String nome;
    private String dataNascimento;



    public Pessoa(String nome, String dataNascimento) {
        this.id = ++contadorId; // Incrementa o contador e atribui o ID
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
