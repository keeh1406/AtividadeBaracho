package br.com.maiscadastros.jsf.viewbean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.maiscadastros.controller.FuncionarioController;
import br.com.maiscadastros.controller.LojaController;
import br.com.maiscadastros.dto.FuncionarioDto;
import br.com.maiscadastros.dto.LojaDto;
import br.com.maiscadastros.model.Funcionario;
import br.com.maiscadastros.model.Loja;

@ViewScoped
@ManagedBean(name = "FuncionarioVB")
public class FuncionarioJavaBean {
	// Atributos - Valores dos componentes visuais
	private Integer id;
	private String nome;
	private String nomeLoja;
	private Date dataNascimento;
	private Long telefone;
	private String email;
	private String senha;
	private Long cpf;
	private String endereco;
	private Boolean flGerente;
	private Integer idLoja;
	private boolean edicao;
	private String tela;
	private List<Funcionario> listaFuncionario;
	private List<Loja> listaLoja;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String pNomeLoja) {
		nomeLoja = pNomeLoja;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Boolean getFlGerente() {
		return flGerente;
	}

	public void setFlGerente(Boolean flGerente) {
		this.flGerente = flGerente;
	}

	public Integer getIdLoja() {
		return idLoja;
	}

	public void setIdLoja(Integer idLoja) {
		this.idLoja = idLoja;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}

	public List<Funcionario> getListaFuncionario() {
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	public List<Loja> getListaLoja() {
		return listaLoja;
	}

	public void setListaLoja(List<Loja> listaLoja) {
		this.listaLoja = listaLoja;
	}

	// Métodos da Controller
	public String limpar() {
		id = null;
		email = null;
		senha = null;
		nome = null;
		dataNascimento = null;
		telefone = null;
		cpf = null;
		endereco = null;
		flGerente = null;
		idLoja = null;
		edicao = false;

		return tela;
	}

	@PostConstruct
	public void init() {
		Funcionario tFuncionario2 = (Funcionario) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestMap().get("FUNCIONARIO");
if (tFuncionario2 != null)
{
    id = tFuncionario2.getId();
    email = tFuncionario2.getEmail();
    senha = tFuncionario2.getSenha();
    nome = tFuncionario2.getNome();
    dataNascimento = java.sql.Date.valueOf(tFuncionario2.getDataNascimento());
    telefone = tFuncionario2.getTelefone();
    cpf = tFuncionario2.getCpf();
    endereco = tFuncionario2.getEndereco();
    flGerente = tFuncionario2.getFlGerente();
    edicao = true;
}
		Funcionario tFuncionario = (Funcionario) FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.get("FUNCIONÁRIO");
		if (tFuncionario != null) {
			id = tFuncionario.getId();
			idLoja = tFuncionario.getIdLoja();

			LojaController tLojaController = new LojaController();

			LojaDto tLojaDto = tLojaController.recuperarLoja(idLoja);
			if (tLojaDto.isOk()) {
				Loja tLoja = tLojaDto.getLoja();
				nomeLoja = tLoja.getNome();
			} else {
				nomeLoja = null;
			}

		}
		LojaController tController = new LojaController();

		LojaDto tDto = tController.pesquisarLoja();
		if (tDto.isOk()) {
			// Ok, recuperado
			listaLoja = tDto.getLista();
		} else {
			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}
	}

	// Métodos da Controller
	public String cadastrar() {
		System.out.println("FuncionarioVB - Cadastrar : " + this);

		Funcionario tFuncionario = new Funcionario();
		tFuncionario.setEmail(email);
		tFuncionario.setSenha(senha);
		tFuncionario.setNome(nome);
		LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
		tFuncionario.setDataNascimento(tDataNascimento);
		tFuncionario.setTelefone(telefone);
		tFuncionario.setCpf(cpf);
		tFuncionario.setEndereco(endereco);
		tFuncionario.setFlGerente(flGerente);
		tFuncionario.setIdLoja(idLoja);

		FuncionarioController tController = new FuncionarioController();

		FuncionarioDto tDto = tController.cadastrarFuncionario(tFuncionario);
		if (tDto.isOk()) {
			// Ok, incluído
			id = tDto.getFuncionario().getId();

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
		} else {
			// Erro de inclusão

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}
		return null;
	}

	public String alterar() {
		System.out.println("FuncionarioVB - Alterar : " + this);

		Funcionario tFuncionario = new Funcionario();
		tFuncionario.setId(id);
		tFuncionario.setEmail(email);
		tFuncionario.setSenha(senha);
		tFuncionario.setNome(nome);
		LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
		tFuncionario.setDataNascimento(tDataNascimento);
		tFuncionario.setTelefone(telefone);
		tFuncionario.setCpf(cpf);

		FuncionarioController tController = new FuncionarioController();

		FuncionarioDto tDto = tController.atualizarFuncionario(tFuncionario);
		if (tDto.isOk()) {
			// Ok, alterado
			id = tDto.getFuncionario().getId();

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
		} else {
			// Erro de alteração

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}
		return null;
	}

	public String consultar() {
		System.out.println("FuncionarioVB - Consultar : " + this);

		FuncionarioController tController = new FuncionarioController();

		FuncionarioDto tDto = tController.recuperarFuncionario(id);
		if (tDto.isOk()) {
			// Ok, recuperado
			Funcionario tFuncionario = tDto.getFuncionario();
			id = tFuncionario.getId();
			email = tFuncionario.getEmail();
			senha = tFuncionario.getSenha();
			nome = tFuncionario.getNome();
			dataNascimento = java.sql.Date.valueOf(tFuncionario.getDataNascimento());
			telefone = tFuncionario.getTelefone();
			cpf = tFuncionario.getCpf();
			endereco = tFuncionario.getEndereco();
			flGerente = tFuncionario.getFlGerente();
			idLoja = tFuncionario.getIdLoja();

			// indicando que a pesquisa deu certo
			edicao = true;
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("FUNCIONARIO", tFuncionario);
		} else {
			// Erro de consulta
			edicao = false;

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

		return tela;
	}

	public String excluir() {
		System.out.println("FuncionarioVB - Excluir : " + this);

		FuncionarioController tController = new FuncionarioController();

		FuncionarioDto tDto = tController.removeFuncionario(id);
		if (tDto.isOk()) {
			// Ok, exluido
			limpar();

			// indicando que a pesquisa deu certo
			edicao = false;

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));

		} else {
			// Erro de consulta
			edicao = false;

			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

		return null;
	}

	public String pesquisar() {
		System.out.println("FuncionarioVB - Pesquisar : " + this);

		FuncionarioController tController = new FuncionarioController();

		FuncionarioDto tDto = tController.pesquisarFuncionariosPorNome(nome);
		if (tDto.isOk()) {
			// Ok, recuperado
			listaFuncionario = tDto.getLista();
		} else {
			// Colocando a mensagem do sistema
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
		}

		return null;
	}

	// Métodos Gerais
	@Override
	public String toString() {
		StringBuilder tBuilder = new StringBuilder();
		tBuilder.append(" [");
		tBuilder.append(id);
		tBuilder.append(", ");
		tBuilder.append(email);
		tBuilder.append(", ");
		tBuilder.append(senha);
		tBuilder.append(", ");
		tBuilder.append(nome);
		tBuilder.append(", ");
		tBuilder.append(nomeLoja);
		tBuilder.append(", ");
		tBuilder.append(dataNascimento);
		tBuilder.append(", ");
		tBuilder.append(telefone);
		tBuilder.append(", ");
		tBuilder.append(cpf);
		tBuilder.append(", ");
		tBuilder.append(endereco);
		tBuilder.append(", ");
		tBuilder.append(flGerente);
		tBuilder.append(", ");
		tBuilder.append(idLoja);
		tBuilder.append("]");
		return tBuilder.toString();
	}
}