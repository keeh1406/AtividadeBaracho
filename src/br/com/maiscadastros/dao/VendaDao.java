package br.com.maiscadastros.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.maiscadastros.model.Venda;

public class VendaDao
{
	private Session session;

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	public Venda create(Venda venda) {

		this.session.save(venda);

		return null;
	}

	public Venda recovery(Integer id) {

		Venda venda = (Venda) this.session.get(Venda.class, id);

		return venda;
	}

	public Venda recoveryByCpf(long cpf) {

		Venda venda = (Venda) this.session.get(Venda.class, cpf);

		return venda;

	}

	public Venda update(Venda venda) {
		
		this.session.update(venda);
		
		return venda;
	}

	public void delete(Venda venda) {
		
		this.session.delete(venda);
	}

	@SuppressWarnings("unchecked")
	public List<Venda> search() {

		List<Venda> lista = new ArrayList<>();

		lista = this.session.createCriteria(Venda.class).list();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Venda> searchByNome(String nome) {

		List<Venda> lista = null;

		Criteria criteria = this.session.createCriteria(Venda.class).add(Restrictions.like("nome", nome));

		lista = (List<Venda>) criteria.list();

		return lista;

	}
}
