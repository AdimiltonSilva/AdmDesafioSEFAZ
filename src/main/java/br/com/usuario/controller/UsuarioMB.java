package br.com.usuario.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.usuario.dao.DAO;
import br.com.usuario.model.Usuario;


@ManagedBean
@ViewScoped
public class UsuarioMB {
	
	private Usuario usuario = new Usuario();
	private DAO<Usuario> daoGenerico = new DAO<Usuario>();
	private List<Usuario> lista;
	
	public UsuarioMB() {}
	
	@PostConstruct
	public void initUsuarioMB() {
		carregarLista();
	}

	private void carregarLista() {
		lista = daoGenerico.buscarTodos("SELECT u FROM Usuario u");
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public void salvar() {
		daoGenerico.salvar(usuario);
		usuario = new Usuario();
		carregarLista();
	}
	
	public void buscar(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void remover(Usuario usuario) {
		daoGenerico.remover(Usuario.class, usuario.getId());
		carregarLista();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DAO<Usuario> getDaoGenerico() {
		return daoGenerico;
	}

	public void setDaoGenerico(DAO<Usuario> daoGenerico) {
		this.daoGenerico = daoGenerico;
	}
	
}
