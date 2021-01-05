package br.com.usuario.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAO<T> {
	private static EntityManager manager = ConnectionFactory.getEntityManager();
	
	public T buscarPorId(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}
	
	public void salvar(T t) {
		try {
			abrirConexaoSeNecessario();
			manager.getTransaction().begin();
			
			if (t.getClass() == null) {
				manager.persist(t);
			} else {
				manager.merge(t);
			}
			
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
		manager.close();
	}

	public void remover(Class<T>clazz, Long id) {
		T t = buscarPorId(clazz, id);
		
		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos(String jpql) {
		abrirConexaoSeNecessario();
		Query qry = manager.createQuery(jpql);
		return qry.getResultList();
	}

	private void abrirConexaoSeNecessario() {
		if (!manager.isOpen()) {
			manager = ConnectionFactory.getEntityManager();
		}
	}
}
