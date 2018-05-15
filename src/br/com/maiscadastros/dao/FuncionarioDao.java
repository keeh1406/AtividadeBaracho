package br.com.maiscadastros.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import br.com.maiscadastros.model.Funcionario;

public class FuncionarioDao {
	
private Session session;

/**
 * @param session
 *            the session to set
 */
public void setSession(Session session) {
	this.session = session;
}

public Funcionario create(Funcionario funcionario) {

	this.session.save(funcionario);

	return null;
}

public Funcionario recovery(Funcionario funcionario) {

	return 	 (Funcionario) this.session.get(Funcionario.class, funcionario.getId());
}

public Funcionario recoveryByCpf(long cpf) {

	Funcionario funcionario = (Funcionario) this.session.get(Funcionario.class, cpf);

	return funcionario;

}

public Funcionario update(Funcionario funcionario) {
	
	this.session.update(funcionario);
	
	return funcionario;
}

public void delete(Funcionario funcionario) {
	
	this.session.delete(funcionario);
}

@SuppressWarnings("unchecked")
public List<Funcionario> search() {

	List<Funcionario> lista = new ArrayList<>();

	lista = this.session.createCriteria(Funcionario.class).list();

	return lista;
}

@SuppressWarnings("unchecked")
public List<Funcionario> searchByNome(String nome) {

	List<Funcionario> lista = null;

	Criteria criteria = this.session.createCriteria(Funcionario.class).add(Restrictions.like("nome", nome));

	lista = (List<Funcionario>) criteria.list();

	return lista;

}
}
