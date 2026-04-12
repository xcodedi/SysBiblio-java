import java.util.List;
import java.util.ArrayList;

LivroService service = new LivroService();

void main() {
    String menu = """
            ===== SysBiblio =====
            1 - Cadastrar Livro
            2 - Listar Livros
            3 - Pesquisar Livro
            4 - Remover Livro
            5 - Editar Livro
            0 - Sair
            """;

    int opcao;

    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite uma opção: ");

        try {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> pesquisar();
                case 4 -> remover();
                case 5 -> editar();
                case 0 -> IO.println("Até breve!");
                default -> IO.println("Opção inválida");
            }
        } catch (Exception e) {
            IO.println("ERRO: " + e.getMessage());
        }

        IO.readln("Pressione Enter...");
    } while (opcao != 0);
}

void cadastrar() throws Exception {
    String titulo = Input.scanString("Título: ");
    String autor = Input.scanString("Autor: ");
    int ano = Input.scanInt("Ano: ");
    int paginas = Input.scanInt("Páginas: ");

    Livro livro = new Livro(titulo, autor, ano, paginas);

    service.cadastrar(livro);

    IO.println("Livro cadastrado com sucesso!");
}

void listar() {
    List<Livro> livros = service.listar();
    imprimirLista(livros);
}

void remover() throws Exception {
    List<Livro> livros = service.listar();
    imprimirLista(livros);

    int index = Input.scanInt("Digite o número do livro: ") - 1;

    service.remover(index);

    IO.println("Livro removido!");
}

void editar() throws Exception {
    List<Livro> livros = service.listar();
    imprimirLista(livros);

    int index = Input.scanInt("Digite o número do livro: ") - 1;

    String titulo = Input.scanString("Novo título: ");
    String autor = Input.scanString("Novo autor: ");
    int ano = Input.scanInt("Novo ano: ");
    int paginas = Input.scanInt("Novas páginas: ");

    Livro atualizado = new Livro(titulo, autor, ano, paginas);

    service.editar(index, atualizado);

    IO.println("Livro atualizado!");
}

void pesquisar() {
    String menu = """
            1 - Por título
            2 - Por autor
            3 - Por ano
            """;

    IO.println(menu);

    int op = Input.scanInt("Escolha: ");

    List<Livro> livros = new ArrayList<>();

    switch (op) {
        case 1 -> {
            String titulo = Input.scanString("Digite o título: ");
            livros = service.pesquisarPorTitulo(titulo);
        }
        case 2 -> {
            String autor = Input.scanString("Digite o autor: ");
            livros = service.pesquisarPorAutor(autor);
        }
        case 3 -> {
            int ano = Input.scanInt("Digite o ano: ");
            livros = service.pesquisarPorAno(ano);
        }
        default -> IO.println("Opção inválida");
    }

    imprimirLista(livros);
}

void imprimirLista(List<Livro> livros) {
    if (livros.isEmpty()) {
        IO.println("Nenhum livro encontrado!");
        return;
    }

    int i = 1;
    for (Livro livro : livros) {
        IO.println(i++ + " - " + livro);
    }
}