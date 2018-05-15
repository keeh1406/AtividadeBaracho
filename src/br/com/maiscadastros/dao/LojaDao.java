package br.com.maiscadastros.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.maiscadastros.model.Loja;

public class LojaDao
{
	private Session session;

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	public Loja create(Loja loja) {

		this.session.save(loja);

		return null;
	}

	public Loja recovery(Loja loja) {

		return 	 (Loja) this.session.get(Loja.class, loja.getId());
	}

	public Loja recoveryByCnpj(long cnpj) {

		Loja loja = (Loja) this.session.get(Loja.class, cnpj);

		return loja;

	}

	public Loja update(Loja loja) {
		
		this.session.update(loja);
		
		return loja;
	}

	public void delete(Loja loja) {
		
		this.session.delete(loja);
	}

	@SuppressWarnings("unchecked")
	public List<Loja> search() {

		List<Loja> lista = new ArrayList<>();

		lista = this.session.createCriteria(Loja.class).list();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Loja> searchByNome(String nome) {

		List<Loja> lista = null;

		Criteria criteria = this.session.createCriteria(Loja.class).add(Restrictions.like("nome", nome));

		lista = (List<Loja>) criteria.list();

		return lista;

	}
}
