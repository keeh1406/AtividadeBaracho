package br.com.maiscadastros.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.maiscadastros.model.Cliente;

public class ClienteDao {

	private Session session;

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	public Cliente create(Cliente cliente) {

		this.session.save(cliente);

		return null;
	}

	public Cliente recovery(Integer id) {

		Cliente cliente = (Cliente) this.session.get(Cliente.class, id);

		return cliente;
	}

	public Cliente recoveryByCpf(long cpf) {

		Cliente cliente = (Cliente) this.session.get(Cliente.class, cpf);

		return cliente;

	}

	public Cliente update(Cliente cliente) {
		
		this.session.update(cliente);
		
		return cliente;
	}

	public void delete(Cliente cliente) {
		
		this.session.delete(cliente);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> search() {

		List<Cliente> lista = new ArrayList<>();

		lista = this.session.createCriteria(Cliente.class).list();

		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> searchByNome(String nome) {

		List<Cliente> lista = null;

		Criteria criteria = this.session.createCriteria(Cliente.class).add(Restrictions.like("nome", nome));

		lista = (List<Cliente>) criteria.list();

		return lista;

	}
}
