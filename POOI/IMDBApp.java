package POOI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IMDBApp {
    private static Scanner scanner = new Scanner(System.in);
    private static final List<Filme> filmes = new ArrayList<Filme>();
    private static final List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private static Menu menu = new Menu();

    public static void main(String[] args) {
        lerDiretores();
        lerArtistas();
        lerFilmes();        
        menu.menuBoasVindas();
        boolean sair = false;
        while (!sair) {
            menu.exibirMenu();
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarFilme();
                    break;
                case 2:
                    if (filmes.size() != 0) {
                        editarFilme();
                    } else {
                        System.out.println(("Não há filmes cadastrados!"));
                    }
                    break;
                case 3:
                    cadastrarArtista();
                    break;
                case 4:
                    cadastrarDiretor();
                    break;
                case 5:
                    if (filmes.size() != 0) {
                        menu.exibirFilmesCadastrados(filmes);
                     exibirDetalhesDoFilme();
                    } else {
                        System.out.println(("Não há filmes cadastrados!"));
                    }                    
                    break;
                case 6:
                    menu.exibirArtistasCadastrados(pessoas);
                    break;
                case 7:
                    menu.exibirDiretoresCadastrados(pessoas);
                    break;
                case 8:
                    procurarFilmePorNome(filmes);
                    break;
                case 9:
                    gravarFilmes();
                    gravarArtistas();
                    gravarDiretores();
                    sair = true;
                    scanner.close();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static List<Filme> getFilmes() {
        return filmes;
    }

    public static List<Pessoa> getPessoas() {
        return pessoas;
    }

    private static void cadastrarFilme() {
        System.out.println("Digite o nome do filme:");
        String nome = scanner.nextLine();
        Filme filme = verificaFilme(nome);
        boolean sair = false;

        if (filme != null) {

            while (!sair) {

                System.out.println("Um filme com esse nome já foi cadastrado.");
                System.out.println(
                        "O que deseja fazer:\n 1 - Cadastrar um novo filme com o mesmo nome \n 2 - Editar o filme existente");
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        sair = true;
                        break;
                    case 2:
                        editarFilmeCadastrado(filme);
                        System.out.println("Filme editado com sucesso!");
                        return;
                    default:
                        System.out.println("Opção Inválida!");
                        break;
                }
            }

        }
        boolean cadastro = cadastrarNovoFilme(nome);
        if (cadastro == true) {
            System.out.println("Filme cadastrado com sucesso!");
        } else {
            System.out.println("Erro no cadastro!");
        }
    }

    private static boolean cadastrarNovoFilme(String nome) {
        boolean sair = false;
        String dataLancamento = null;
        while (!sair) {
            System.out.println("Digite a data de lançamento do filme (DD/MM/AAAA):");
            dataLancamento = scanner.nextLine();
            if (ValidarData.validarData(dataLancamento)) {
                sair = true;
            } else {
                System.out.println("Data inválida. Tente novamente!");
            }
        }

        System.out.println("Digite o orçamento do filme:");
        double orcamento = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline

        System.out.println("Digite a nota do filme:");
        Double nota = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline

        System.out.println("Digite a descrição do filme:");
        String descricao = scanner.nextLine();

        // Criação do filme
        Filme novoFilme = new Filme(nome, dataLancamento, orcamento, nota, descricao);

        System.out.println("Deseja vincular um diretor ao filme? (S/N)");
        String resposta = scanner.nextLine().trim().toUpperCase();

        if (resposta.equals("S")) {
            Diretor diretor = selecionarOuCadastrarDiretor();
            novoFilme.setDiretor(diretor);
        }

        sair = false;
        while (!sair) {
            System.out.println("Deseja vincular um artista ao filme? (S/N)");
            resposta = scanner.nextLine().trim().toUpperCase();

            if (resposta.equals("N")) {
                sair = true;
            } else {
                Artista artista = selecionarOuCadastrarArtista();
                if (novoFilme.verificaArtistaExistente(artista)) {
                    System.out.println("Artista já cadastrado para esse filme!");
                } else {
                    novoFilme.adicionarArtista(artista);
                }
            }

        }

        IMDBApp.getFilmes().add(novoFilme);

        return true;
    }

    private static Filme verificaFilme(String nome) {
        for (Filme filme : getFilmes()) {
            if (filme.getNome().equalsIgnoreCase(nome)) {
                return filme;
            }
        }
        return null;
    }

    private static void editarFilmeCadastrado(Filme filme) {
        while (true) {
            menu.editarFilmeCadastrado(filme);
            System.out.println("O que deseja editar?");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir newline
            switch (escolha) {
                case 1:
                    System.out.println("Digite o novo nome:");
                    filme.setNome(scanner.nextLine());
                    break;
                case 2:
                    boolean sair = false;
                    String dataLancamento = null;
                    while (!sair) {
                        System.out.println("Digite a nova data de lançamento (formato DD/MM/AAAA):");
                        dataLancamento = scanner.nextLine();
                        if (ValidarData.validarData(dataLancamento)) {
                            sair = true;
                        } else {
                            System.out.println("Data inválida. Tente novamente!");
                        }
                    }
                    filme.setDataLancamento(dataLancamento);
                    break;
                case 3:
                    System.out.println("Digite o novo orçamento:");
                    filme.setOrcamento(scanner.nextDouble());
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Digite a nova descrição:");
                    filme.setDescricao(scanner.nextLine());
                    break;
                case 5:
                    System.out.println("Digite a nova nova:");
                    filme.setNota(scanner.nextDouble());
                    scanner.nextLine();
                    break;
                case 6:
                    Diretor diretor = selecionarOuCadastrarDiretor();
                    filme.setDiretor(diretor);
                    break;
                case 7:
                    filme.exibirArtistasCadastrados();
                    editarArtistasFilme(filme);
                    break;
                case 0:
                    return; // Sair do método de edição
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

    }

    private static void editarFilme() {
        menu.exibirFilmesCadastrados(filmes);
        System.out.println("Escolha o filme que deseja editar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Filme filme = escolherFilmePorId(id);
        editarFilmeCadastrado(filme);
    }

    private static Filme escolherFilmePorId(int id) {
        for (Filme filme : filmes) {
            if (filme.getId() == id) {
                return filme;
            }
        }
        return null;

    }

    private static void exibirDetalhesDoFilme() {
        System.out.print("Escolha uma opção para detalhes: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Filme filme = escolherFilmePorId(id);
        menu.exibirDetalhesDoFilme(filme);
    }

    private static void procurarFilmePorNome(List<Filme> filmes) {
        System.out.println("Digite o nome (ou parte) do filme a ser procurado:");
        String busca = scanner.nextLine();
        menu.procurarFilmePorNome(filmes, busca);
    }

    private static void cadastrarArtista() {
        System.out.print("Nome do Artista: ");
        String nome = scanner.nextLine();
        boolean sair = false;
        String dataNascimento = null;
        while (!sair) {
            System.out.print("Data de Nascimento (DD/MM/AAAA): ");
            dataNascimento = scanner.nextLine();
            if (ValidarData.validarData(dataNascimento)) {
                sair = true;
            } else {
                System.out.println("Data inválida. Tente novamente!");
            }
            if (verificaArtista(nome, dataNascimento)) {
                System.out.println("Artista já cadastrado.");
            } else {
                Artista novoArtista = new Artista(nome, dataNascimento);
                pessoas.add(novoArtista);
                System.out.println("Artista cadastrado com sucesso!");
            }
        }
    }

    private static boolean verificaArtista(String nome, String dataNascimento) {
        return pessoas.stream()
                .anyMatch(pessoa -> pessoa instanceof Artista && pessoa.getNome().equalsIgnoreCase(nome) &&
                        pessoa.getDataNascimento().equals(dataNascimento));
    }

    private static Artista selecionarOuCadastrarArtista() {
        Artista artista = null;
        int id;

        // Exibe lista de artistas
        if (existemArtistas(pessoas)) {
            menu.exibirArtistasCadastrados(pessoas);
            System.out.println("Escolha o artista pelo ID ou digite 0 para cadastrar um novo:");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            if (escolha == 0) {
                cadastrarArtista();
                id = getIdUltimoArtistaCadastrado(pessoas);
                artista = encontrarArtistaPorId(id);
            } else {
                artista = encontrarArtistaPorId(escolha);
            }
        } else {
            System.out.println("Não existem artistas cadastrados.");
            System.out.println("Cadastrar novo artista");
            cadastrarArtista();
            id = getIdUltimoArtistaCadastrado(pessoas);
            artista = encontrarArtistaPorId(id);
        }

        return artista; // Caso não encontre o diretor ou opção inválida
    }

    public static Artista encontrarArtistaPorId(int id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Artista && pessoa.getId() == id) {
                return (Artista) pessoa;
            }
        }
        return null;
    }

    public static boolean existemArtistas(List<Pessoa> pessoas) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Artista) {
                return true;
            }
        }
        return false;
    }

    public static int getIdUltimoArtistaCadastrado(List<Pessoa> pessoas) {
        for (int i = pessoas.size() - 1; i >= 0; i--) {
            Pessoa pessoa = pessoas.get(i);
            if (pessoa instanceof Artista) {
                return pessoa.getId();
            }
        }
        return -1;
    }

    private static void editarArtistasFilme(Filme filme) {

        System.out.println("Digite o ID do artista para remover, ou 0 para adicionar um adicionar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id == 0) {
            Artista artista = selecionarOuCadastrarArtista();
            if (filme.verificaArtistaExistente(artista)) {
                System.out.println("Artista já cadastrado para esse filme!");
            } else {
                filme.adicionarArtista(artista);
            }
        } else {
            filme.getArtistas().remove(encontrarArtistaPorId(id));
        }

    }

    private static void cadastrarDiretor() {
        System.out.print("Nome do Diretor: ");
        String nome = scanner.nextLine();

        if (verificaDiretor(nome)) {
            System.out.println("Um diretor com esse nome já foi cadastrado.");
        } else {
            boolean sair = false;
            String dataNascimento = null;
            while (!sair) {
                System.out.print("Data de Nascimento (DD/MM/AAAA): ");
                dataNascimento = scanner.nextLine();
                if (ValidarData.validarData(dataNascimento)) {
                    sair = true;
                } else {
                    System.out.println("Data inválida. Tente novamente!");
                }
            }

            Diretor novoDiretor = new Diretor(nome, dataNascimento);
            pessoas.add(novoDiretor);
            System.out.println("Diretor cadastrado com sucesso!");
        }
    }

    private static boolean verificaDiretor(String nome) {
        return pessoas.stream()
                .anyMatch(pessoa -> pessoa instanceof Diretor && pessoa.getNome().equalsIgnoreCase(nome));
    }

    private static Diretor selecionarOuCadastrarDiretor() {
        Diretor diretor = null;
        int id;

        // Exibe lista de diretores
        if (existemDiretores(pessoas)) {
            menu.exibirDiretoresCadastrados(pessoas);
            System.out.println("Escolha o diretor pelo ID ou digite 0 para cadastrar um novo:");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            if (escolha == 0) {
                cadastrarDiretor();
                id = getIdUltimoDiretorCadastrado(pessoas);
                diretor = encontrarDiretorPorId(id);
            } else {
                diretor = encontrarDiretorPorId(escolha);
            }
        } else {
            System.out.println("Não existem diretores cadastrados.");
            System.out.println("Cadastrar novo diretor");
            cadastrarDiretor();
            id = getIdUltimoDiretorCadastrado(pessoas);
            diretor = encontrarDiretorPorId(id);
        }

        return diretor;
    }

    public static Diretor encontrarDiretorPorId(int id) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Diretor && pessoa.getId() == id) {
                return (Diretor) pessoa;
            }
        }
        return null;
    }

    public static boolean existemDiretores(List<Pessoa> pessoas) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa instanceof Diretor) {
                return true;
            }
        }
        return false;
    }

    public static int getIdUltimoDiretorCadastrado(List<Pessoa> pessoas) {
        for (int i = pessoas.size() - 1; i >= 0; i--) { // Começa do último elemento da lista
            Pessoa pessoa = pessoas.get(i);
            if (pessoa instanceof Diretor) { // Verifica se o objeto é uma instância de Diretor
                return pessoa.getId(); // Retorna o ID do diretor encontrado
            }
        }
        return -1;
    }

    public static void gravarFilmes() {
        try (PrintWriter pw = new PrintWriter(new File("filmes.csv"))) {
            pw.println("ID,Nome,DataLancamento,Orcamento,Nota,Descriacao,DiretorID,ArtistasIDs");
            for (Filme filme : filmes) {
                int diretorId = -1;
                if (filme.getDiretor() != null) {
                    diretorId = filme.getDiretor().getId();
                }
                String artistasIds = filme.getArtistas().stream()
                        .map(artista -> String.valueOf(artista.getId()))
                        .collect(Collectors.joining(";"));
                
                pw.println(filme.getId() + "," + filme.getNome() + "," + filme.getDataLancamento() + "," +
                        filme.getOrcamento() + "," +
                        filme.getNota() + "," +
                        filme.getDescricao() + "," + diretorId + "," + artistasIds);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao salvar os filmes.");
        }
    }

    public static void gravarArtistas() {
        try (PrintWriter pw = new PrintWriter(new File("artistas.csv"))) {
            pw.println("ID,Nome,DataNascimento");
            for (Pessoa pessoa : pessoas) {
                if (pessoa instanceof Artista) {
                    Artista artista = (Artista) pessoa;
                    pw.println(artista.getId() + "," + artista.getNome() + "," + artista.getDataNascimento());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao salvar os diretores.");
        }
    }

    public static void gravarDiretores() {
        try (PrintWriter pw = new PrintWriter(new File("diretores.csv"))) {
            pw.println("ID,Nome,DataNascimento");
            for (Pessoa pessoa : pessoas) {
                if (pessoa instanceof Diretor) {
                    Diretor diretor = (Diretor) pessoa;
                    pw.println(diretor.getId() + "," + diretor.getNome() + "," + diretor.getDataNascimento());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao salvar os diretores.");
        }
    }

    public static void lerFilmes() {
        try (Scanner scanner = new Scanner(new File("filmes.csv"))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String nome = data[1];
                String dataLancamento = data[2];
                double orcamento = Double.parseDouble(data[3]);
                double nota = Double.parseDouble(data[4]);
                String descricao = data[5];
                int diretorId = Integer.parseInt(data[6]);
                Diretor diretor = null;
                if (diretorId != -1) {
                    diretor = (Diretor) pessoas.stream().filter(p -> p instanceof Diretor && p.getId() == diretorId)
                        .findFirst().orElse(null);
                }
                List<Artista> artistas = new ArrayList<>();
                if (data.length > 7) {
                    String[] artistasIds = data[7].split(";");
                    for (String artistaId : artistasIds) {
                        Artista artista = (Artista) pessoas.stream()
                                .filter(p -> p instanceof Artista && p.getId() == Integer.parseInt(artistaId))
                                .findFirst().orElse(null);
                        if (artista != null) {
                            artistas.add(artista);
                        }
                    }
                }
                Filme filme = new Filme(nome, dataLancamento, orcamento, nota, descricao);
                filme.setId(id);
                System.out.println(diretor);
                filme.setDiretor(diretor);
                filme.setArtistas(artistas);
                filmes.add(filme);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de filmes não encontrado.");
        }
    }

    public static void lerArtistas() {
        try (Scanner scanner = new Scanner(new File("artistas.csv"))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine(); 
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String nome = data[1];
                String dataNascimento = data[2];
                Artista artista = new Artista(nome, dataNascimento);
                artista.setId(id);
                pessoas.add(artista);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de artistas não encontrado.");
        }
    }

    public static void lerDiretores() {
        try (Scanner scanner = new Scanner(new File("diretores.csv"))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String nome = data[1];
                String dataNascimento = data[2];
                Diretor diretor = new Diretor(nome, dataNascimento);
                diretor.setId(id);
                pessoas.add(diretor);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de diretores não encontrado.");
        }
    }
}