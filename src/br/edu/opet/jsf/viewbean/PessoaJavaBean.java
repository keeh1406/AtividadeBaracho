package br.edu.opet.jsf.viewbean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.edu.opet.dto.PessoaDto;
import br.edu.opet.controller.PessoaController;
import br.edu.opet.model.Pessoa;

@ViewScoped
@ManagedBean(name="PessoaVB")
public class PessoaJavaBean 
{
    // Atributos - Valores dos componentes visuais
private Integer id;
private String email;
private String senha;
private String nome;
private Date dataNascimento;
private Long telefone;
private Long cpf;
private String endereco;
private String status;
private boolean edicao;
private String  tela;
private List<Pessoa> listaPessoa;

@PostConstruct
public void init()
{
    Pessoa tPessoa = (Pessoa) FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestMap().get("PESSOA");
    if (tPessoa != null)
    {
        id = tPessoa.getId();
        email = tPessoa.getEmail();
        senha = tPessoa.getSenha();
        nome = tPessoa.getNome();
        dataNascimento = java.sql.Date.valueOf(tPessoa.getDataNascimento());
        telefone = tPessoa.getTelefone();
        cpf = tPessoa.getCpf();
        endereco = tPessoa.getEndereco();
        status = tPessoa.getStatus();
        edicao = true;
    }
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
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

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
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

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
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

public List<Pessoa> getListaPessoa() {
	return listaPessoa;
}

public void setListaPessoa(List<Pessoa> listaPessoa) {
	this.listaPessoa = listaPessoa;
}

// Métodos da Controller
public String limpar()
{
    id = null;
    email = null;
    senha = null;
    nome = null;
    dataNascimento = null;
    telefone = null;
    cpf = null;
    endereco = null;
    status = null;
    edicao = false;

    return tela;
}

// Métodos da Controller
public String cadastrar()
{
    System.out.println("PessoaVB - Cadastrar : " + this);

    Pessoa tPessoa = new Pessoa();
    tPessoa.setEmail(email);
    tPessoa.setSenha(senha);
    tPessoa.setNome(nome);
    LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
    tPessoa.setDataNascimento(tDataNascimento);
    tPessoa.setTelefone(telefone);
    tPessoa.setCpf(cpf);
    tPessoa.setEndereco(endereco);
    tPessoa.setStatus(status);

    PessoaController tController = new PessoaController();

    PessoaDto tDto = tController.cadastrarPessoa(tPessoa);
    if (tDto.isOk())
    {
        // Ok, incluído
        id = tDto.getPessoa().getId();

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PESSOA", tPessoa);
    }
    else
    {
        // Erro de inclusão

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
    }
    return null;
}

public String alterar()
{
    System.out.println("PessoaVB - Alterar : " + this);

    Pessoa tPessoa = new Pessoa();
    tPessoa.setId(id);
    tPessoa.setEmail(email);
    tPessoa.setSenha(senha);
    tPessoa.setNome(nome);
    LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
    tPessoa.setDataNascimento(tDataNascimento);
    tPessoa.setTelefone(telefone);
    tPessoa.setCpf(cpf);
    tPessoa.setEndereco(endereco);
    tPessoa.setStatus(status);

    PessoaController tController = new PessoaController();

    PessoaDto tDto = tController.atualizarPessoa(tPessoa);
    if (tDto.isOk())
    {
        // Ok, alterado
        id = tDto.getPessoa().getId();

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
    }
    else
    {
        // Erro de alteração

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
    }
    return null;
}

public String consultar()
{
    System.out.println("PessoaVB - Consultar : " + this);

    PessoaController tController = new PessoaController();

    PessoaDto tDto = tController.recuperarPessoa(id);
    if (tDto.isOk())
    {
        // Ok, recuperado
        Pessoa tPessoa = tDto.getPessoa();
        id = tPessoa.getId();
        email = tPessoa.getEmail();
        senha = tPessoa.getSenha();
        nome = tPessoa.getNome();
        dataNascimento = java.sql.Date.valueOf(tPessoa.getDataNascimento());
        telefone = tPessoa.getTelefone();
        cpf = tPessoa.getCpf();
        endereco = tPessoa.getEndereco();
        status = tPessoa.getStatus();

        // indicando que a pesquisa deu certo
        edicao = true;
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PESSOA", tPessoa);
    }
    else
    {
        // Erro de consulta
        edicao = false;

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
    }

    return tela;
}

public String excluir()
{
    System.out.println("PessoaVB - Excluir : " + this);

    PessoaController tController = new PessoaController();

    PessoaDto tDto = tController.removePessoa(id);
    if (tDto.isOk())
    {
        // Ok, exluido
        limpar();

        // indicando que a pesquisa deu certo
        edicao = false;

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));

    }
    else
    {
        // Erro de consulta
        edicao = false;

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
    }

    return null;
}

public String pesquisar()
{
    System.out.println("PessoaVB - Pesquisar : " + this);

    PessoaController tController = new PessoaController();

    PessoaDto tDto = tController.pesquisarPessoasPorNome(nome);
    if (tDto.isOk())
    {
        // Ok, recuperado
        listaPessoa = tDto.getLista();
    }
    else
    {
        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, tDto.getMensagem(), tDto.getMensagem()));
    }

    return null;
}

// Métodos Gerais
@Override
public String toString()
{
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
    tBuilder.append(dataNascimento);
    tBuilder.append(", ");
    tBuilder.append(telefone);
    tBuilder.append(", ");
    tBuilder.append(cpf);
    tBuilder.append(", ");
    tBuilder.append(endereco);
    tBuilder.append(", ");
    tBuilder.append(status);
    tBuilder.append("]");
    return tBuilder.toString();
}
}