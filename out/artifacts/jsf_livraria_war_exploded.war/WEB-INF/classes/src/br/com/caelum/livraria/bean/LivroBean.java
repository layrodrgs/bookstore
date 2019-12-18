package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;


@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Livro livro = new Livro();
    private Integer autorId;
    private Integer livroId;

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Integer getLivroId() {
        return livroId;
    }

    public void setLivroId(Integer livroId) {
        this.livroId = livroId;
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
            FacesContext.getCurrentInstance().addMessage("autor",
                    new FacesMessage("Livro deve ter pelo menos um Autor"));
            return;
        }

        if (this.livro.getId() == null) {
            new DAO<Livro>(Livro.class).adicionar(this.livro);
        } else {
            new DAO<Livro>(Livro.class).atualiza(this.livro);
        }
        this.livro = new Livro();
    }

    public void carregar(Livro livro) {
        System.out.println("Carregando Livro" +livro.getTitulo());
        this.livro = livro;
    }

    public void remover(Livro livro) {
        System.out.println("Removendo Livro" +livro.getTitulo());
        new DAO<Livro>(Livro.class).remove(livro);
    }

    public void removeAutorDoLivro(Autor autor){
        this.livro.removeAutor(autor);
    }

    public String formAutor() {
        System.out.println("Chamando formulário do autor");
        return "autor?faces-redirect=true";
    }

    public void comecaComDigitoUm(FacesContext fc, UIComponent component,
                                  Object value) throws Exception {

        String valor = value.toString();
        if (!valor.startsWith("1")) {
            throw new Exception("ISBN deveria começar com 1");
        }

    }

    public void carregarLivroPelaId() {
        this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
    }
}
