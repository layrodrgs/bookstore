package br.com.caelum.livraria.bean;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@ViewScoped
public class LoginBean {


    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public String efetuaLogin() {
        System.out.println("Fazendo login do usuário "
                + this.usuario.getEmail());


        boolean existe = new UsuarioDao().existe(this.usuario);
        if (existe) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap()
                    .put("usuarioLogado", this.usuario);

            return "index?faces-redirect=true";
        }

        return null;
    }
    public String deslogar(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        return "login?faces-redirect=true";

    }

}
