package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;


@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {
    private Livro livro = new Livro();
    private Integer autorId;

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Livro getLivro() {
        return livro;
    }

    public List<Livro> getLivros() {
        return new DAO<Livro>(Livro.class).listaTodos();
    }

    public List<Autor> getAutores() {
        return new DAO<Autor>(Autor.class).listaTodos();
    }

    public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutores();
    }

    public void adicionarAutor() {

        Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
        System.out.println("Usando autor " + autor.getNome());
        this.livro.adicionaAutor(autor);
    }
    public void gravarAutor() {
        Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
        this.livro.adicionaAutor(autor);
    }


    public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());

        if (livro.getAutores().isEmpty()) {
            throw new RuntimeException("Livro deve ter pelo menos um Autor.");
        }

        new DAO<Livro>(Livro.class).adicionar(this.livro);
        this.livro = new Livro();
    }
}
