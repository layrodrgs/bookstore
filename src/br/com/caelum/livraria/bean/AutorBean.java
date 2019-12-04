package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AutorBean {

    private Autor autor = new Autor();

    public Autor getAutor(){
        return autor;
    }

    public void gravar(){
        System.out.println("Gravando autor "+this.autor.getNome());

        new DAO<Autor>(Autor.class).adicionar(this.autor);
    }
}
