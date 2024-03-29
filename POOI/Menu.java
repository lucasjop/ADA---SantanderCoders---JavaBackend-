package POOI;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {
    

    public void menuBoasVindas(){
        System.out.println("#######################################################");
        System.out.println("##### Santander Coders - 2023.2 - Java - Back End #####");
        System.out.println("#####   Projeto  Módulo 02 - Catálago de Filmes   #####");
        System.out.println("#####            Lucas Cavalcanti Cruz            #####");
        System.out.println("#######################################################");
    }

    public void exibirMenu(){
        System.out.println("##############################");
        System.out.println("##### Catálogo de Filmes #####");
        System.out.println("##############################");
        System.out.println(">>>> Menu <<<<");
        System.out.println("1 - Cadastrar novo filme");
        System.out.println("2 - Editar filme cadastrado");
        System.out.println("3 - Cadastrar novo artista");
        System.out.println("4 - Cadastrar novo diretor");
        System.out.println("5 - Exibir lista de filmes cadastrados");
        System.out.println("6 - Exibir lista de artistas cadastrados");
        System.out.println("7 - Exibir lista de diretores cadastrados");
        System.out.println("8 - Procurar filme");
        System.out.println("9 - Sair");
    }

    public void exibirFilmesCadastrados(List<Filme> filmes){
        if (filmes.isEmpty()) {
            System.out.println("Não há filmes cadastrados.");
            return;
        }

        System.out.println("Filmes Cadastrados:");
        for (Filme filme : filmes) {
        System.out.println(filme.getId() + " - " + filme.getNome());
        }
    }

    public void exibirDetalhesDoFilme(Filme filme) {
        System.out.println("Nome: " + filme.getNome());
        System.out.println("Data de Lançamento: " + filme.getDataLancamento());
        System.out.println("Orçamento: $" + filme.getOrcamento());
        System.out.println("Nota: " + filme.getNota());
        System.out.println("Descrição: " + filme.getDescricao());
        if (filme.getDiretor() != null) {
            System.out.println("Diretor: " + filme.getDiretor().getNome() + " - " + filme.getDiretor().getDataNascimento());
        }
        if (filme.getArtistas() != null) {
            System.out.println("Elenco:");
            for (Artista artista : filme.getArtistas()) {
                System.out.println("\t" + artista.getNome() + " - " + artista.getDataNascimento());
            }
        }              
    }

    public void editarFilmeCadastrado(Filme filme){
        System.out.println("Editando o Filme: \"" + filme.getNome() + "\":\n" +
                "1 - Nome\n" +
                "2 - Data de lançamento\n" +
                "3 - Orçamento\n" +
                "4 - Descrição\n" +
                "5 - Nota\n" +
                "6 - Diretor\n" +
                "7 - Artistas\n" +
                "0 - Sair");    
    }

    public void exibirArtistasCadastrados(List<Pessoa> pessoas) {
        List<Artista> artistas = pessoas.stream()
                                    .filter(p -> p instanceof Artista)
                                    .map(p -> (Artista) p)
                                    .collect(Collectors.toList());

        if (artistas.isEmpty()) {
            System.out.println("Não há artistas cadastrados.");
            return;
        }

        System.out.println("Artistas Cadastrados:");
        artistas.forEach(artista -> System.out.println(artista.getId() + " - " + artista.getNome() + " - " + artista.getDataNascimento()));
    }

    public void exibirDiretoresCadastrados(List<Pessoa> pessoas) {
        List<Diretor> diretores = pessoas.stream()
                                        .filter(p -> p instanceof Diretor)
                                        .map(p -> (Diretor) p)
                                        .collect(Collectors.toList());

        if (diretores.isEmpty()) {
            System.out.println("Não há diretores cadastrados.");
            return;
        }

        System.out.println("Diretores Cadastrados:");
        diretores.forEach(diretor -> System.out.println(diretor.getId() + " - " + diretor.getNome() + " - " + diretor.getDataNascimento()));
    }

    public void procurarFilmePorNome(List<Filme> filmes, String busca) {
        busca = normalizarTexto(busca);

        boolean encontrado = false;
        for (Filme filme : filmes) {
            String nomeFilmeNormalizado = normalizarTexto(filme.getNome());
            if (nomeFilmeNormalizado.contains(busca)) {
                if (!encontrado) {
                    System.out.println("Filmes encontrados:");
                    encontrado = true;
                }
                System.out.println(filme.getNome());
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum filme encontrado com o termo: " + busca);
        }
    }

    private String normalizarTexto(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[^\\p{ASCII}]", ""); // Remove acentuação.
        return texto.toLowerCase();
    }


}
