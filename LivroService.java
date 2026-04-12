import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private List<Livro> acervo = new ArrayList<>();

    public void cadastrar(Livro novoLivro) throws Exception {

        if (novoLivro == null)
            throw new Exception("Objeto nulo");

        validarLivro(novoLivro);

        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(novoLivro.getTitulo())
                    && livro.getAutor().equalsIgnoreCase(novoLivro.getAutor())
                    && livro.getAnoPublicacao() == novoLivro.getAnoPublicacao()) {
                throw new Exception("Livro já cadastrado");
            }
        }

        acervo.add(novoLivro);
    }

    public List<Livro> listar() {
        return acervo;
    }

    public void remover(int index) throws Exception {
        if (index < 0 || index >= acervo.size())
            throw new Exception("Índice inválido");

        acervo.remove(index);
    }

    public void editar(int index, Livro livroAtualizado) throws Exception {
        if (index < 0 || index >= acervo.size())
            throw new Exception("Índice inválido");

        validarLivro(livroAtualizado);

        acervo.set(index, livroAtualizado);
    }

    private void validarLivro(Livro livro) throws Exception {

        if (livro.getTitulo() == null || livro.getTitulo().isEmpty())
            throw new Exception("Título inválido");

        livro.setTitulo(livro.getTitulo().trim().toUpperCase());

        if (livro.getAutor() == null || livro.getAutor().isEmpty())
            throw new Exception("Autor inválido");

        livro.setAutor(livro.getAutor().trim().toUpperCase());

        if (livro.getAnoPublicacao() < 1900
                || livro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano inválido");

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas inválido");
    }

    public List<Livro> pesquisarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();
        titulo = titulo.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getTitulo().contains(titulo))
                resultado.add(livro);
        }

        return resultado;
    }

    public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();
        autor = autor.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getAutor().contains(autor))
                resultado.add(livro);
        }

        return resultado;
    }

    public List<Livro> pesquisarPorAno(int ano) {
        List<Livro> resultado = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano)
                resultado.add(livro);
        }

        return resultado;
    }
}