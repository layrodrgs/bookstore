package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;

import javax.faces.bean.ManagedBean;


@ManagedBean
public class LivroBean {
    private Livro livro = new Livro();

    public Livro getLivro() {
        return livro;
    }

    public void gravar(){
        System.out.println("Gravando Livro: "+this.livro.getTitulo());

        if(livro.getAutores().isEmpty()){
            throw new RuntimeException("Livro deve ter pelo menos um autor.");
        }

        new DAO<Livro>(Livro.class).adicionar(this.livro);
    }
}
