package br.com.maiscadastros.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.maiscadastros.model.Produto;

public class ProdutoDao
{
	private Session session;

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	public Produto create(Produto produto) {

		this.session.save(produto);

		return null;
	}

	public Produto recovery(Integer id) {

		Produto produto = (Produto) this.session.get(Produto.class, id);

		return produto;
	}

	public Produto recoveryByNome(String nome) {

		Produto produto = (Produto) this.session.get(Produto.class, nome);

		return produto;

	}

	public Produto update(Produto produto) {
		
		this.session.update(produto);
		
		return produto;
	}

	public void delete(Produto produto) {
		
		this.session.delete(produto);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> search() {

		List<Produto> lista = new ArrayList<>();

		lista = this.session.createCriteria(Produto.class).list();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> searchByNome(String nome) {

		List<Produto> lista = null;

		Criteria criteria = this.session.createCriteria(Produto.class).add(Restrictions.like("nome", nome));

		lista = (List<Produto>) criteria.list();

		return lista;

	}
}
