package POOI;

import java.util.ArrayList;
import java.util.List;

public class Filme {
    private static int contadorId = 0;
    private int id;
    private String nome;
    private String dataLancamento; //Formato DD/MM/AAAA
    private double orcamento;
    private double nota;
    private String descricao;
    private Diretor diretor;
    private List<Artista> artistas = new ArrayList<Artista>();

    public Filme(String nome, String dataLancamento, double orcamento, double nota, String descricao) {
        this.id = ++contadorId;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.orcamento = orcamento;
        this.nota = nota;
        this.descricao = descricao;
    }

    // Métodos para adicionar e remover artistas
    public void adicionarArtista(Artista artista) {
        artistas.add(artista);
    }

    public boolean removerArtista(Artista artista) {
        return artistas.remove(artista);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public double getNota(){
        return nota;
    }
    
    public void setNota(double nota){
        this.nota = nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }
    
}
