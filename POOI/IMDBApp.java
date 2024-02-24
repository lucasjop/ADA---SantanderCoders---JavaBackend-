package POOI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IMDBApp {
    private static Scanner scanner = new Scanner(System.in);
    private static final List<Filme> filmes = new ArrayList<Filme>();
    private static final List<Pessoa> pessoas = new ArrayList<Pessoa>();
    private static Menu menu = new Menu(scanner);

    public static void main(String[] args) {
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
                    cadastrarArtista();
                    break;
                case 3:
                    cadastrarDiretor();
                    break;
                case 4:
                    exibirFilmesCadastrados();
                    break;
                case 5:
                    exibirArtistasCadastrados();
                    break;
                case 6:
                    exibirDiretoresCadastrados();
                    break;
                case 7:
                    procurarFilme();
                    break;
                case 8:
                    // salvarContatosEmArquivo(nomeArquivo);
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
        System.out.println("Digite a data de lançamento do filme (DD/MM/AAAA):");
        String dataLancamento = scanner.nextLine();

        System.out.println("Digite o orçamento do filme:");
        double orcamento = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline

        System.out.println("Digite a nota do filme:");
        Double nota = scanner.nextDouble();
        scanner.nextLine(); // Consumir newline

        System.out.println("Digite a descrição do filme:");
        String descricao = scanner.nextLine();

        // Seleciona ou cadastra um novo diretor
        Diretor diretor = selecionarOuCadastrarDiretor();

        // Criação do filme
        Filme novoFilme = new Filme(nome, dataLancamento, orcamento, nota, descricao);
        novoFilme.setDiretor(diretor);

        boolean sair = false;

        while (!sair) {
            System.out.println("Deseja cadastrar artista? (S/N)");
            String resposta = scanner.nextLine().trim().toUpperCase();
    
            if (resposta.equals("N")) {
                sair = true;
            } else {
                Artista artista = selecionarOuCadastrarArtista();
                novoFilme.adicionarArtista(artista);
            }

        }
        

        IMDBApp.getFilmes().add(novoFilme);

        return true;
    }

    private static void exibirFilmesCadastrados() {
        menu.exibirFilmesCadastrados(filmes);
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
            int escolha = menu.editarFilmeCadastrado(filme);
            switch (escolha) {
                case 1:
                    System.out.println("Digite o novo nome:");
                    filme.setNome(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Digite a nova data de lançamento (formato DD/MM/AAAA):");
                    filme.setDataLancamento(scanner.nextLine());
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
                    Diretor diretor = selecionarOuCadastrarDiretor();
                    filme.setDiretor(diretor);
                    break;
                case 6:
                    // Similar ao diretor, mas para artistas
                    // System.out.println("Funcionalidade de editar artistas ainda não
                    // implementada.");
                    break;
                case 0:
                    return; // Sair do método de edição
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

    }

    private static void procurarFilme() {
        menu.procurarFilme(filmes);
    }

    private static void cadastrarArtista() {
        System.out.print("Nome do Artista: ");
        String nome = scanner.nextLine();

        // Verifica se o artista já existe
        if (verificaArtista(nome)) {
            System.out.println("Um artista com esse nome já foi cadastrado.");
        } else {
            System.out.print("Data de Nascimento (DD/MM/AAAA): ");
            String dataNascimento = scanner.nextLine();

            Artista novoArtista = new Artista(nome, dataNascimento);
            pessoas.add(novoArtista);
            System.out.println("Artista cadastrado com sucesso!");
        }
    }

    private static boolean verificaArtista(String nome) {
        return pessoas.stream()
                .anyMatch(pessoa -> pessoa instanceof Artista && pessoa.getNome().equalsIgnoreCase(nome));
    }

    private static void exibirArtistasCadastrados() {
        menu.exibirArtistasCadastrados(pessoas);
    }

    private static Artista selecionarOuCadastrarArtista() {
        Artista artista = null;
        int id;
 

        // Exibe lista de artistas
        if (existemArtistas(pessoas)){
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

    public static void cadastrarDiretor() {
        System.out.print("Nome do Diretor: ");
        String nome = scanner.nextLine();

        if (verificaDiretor(nome)) {
            System.out.println("Um diretor com esse nome já foi cadastrado.");
        } else {
            System.out.print("Data de Nascimento (DD/MM/AAAA): ");
            String dataNascimento = scanner.nextLine();

            Diretor novoDiretor = new Diretor(nome, dataNascimento);
            pessoas.add(novoDiretor);
            System.out.println("Diretor cadastrado com sucesso!");
        }
    }

    private static boolean verificaDiretor(String nome) {
        return pessoas.stream()
                .anyMatch(pessoa -> pessoa instanceof Diretor && pessoa.getNome().equalsIgnoreCase(nome));
    }

    private static void exibirDiretoresCadastrados() {
        menu.exibirDiretoresCadastrados(pessoas);
    }

    private static Diretor selecionarOuCadastrarDiretor() {
        Diretor diretor = null;
        int id;
        System.out.println("Deseja selecionar um diretor existente? (S/N)");
        String resposta = scanner.nextLine().trim().toUpperCase();

        if (resposta.equals("N")) {
            return null;
        }

        // Exibe lista de diretores
        if (existemDiretores(pessoas)){
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
    


}