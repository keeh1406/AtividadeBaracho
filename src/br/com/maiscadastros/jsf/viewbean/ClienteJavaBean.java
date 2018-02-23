package br.com.maiscadastros.jsf.viewbean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.maiscadastros.controller.ClienteController;
import br.com.maiscadastros.dto.ClienteDto;
import br.com.maiscadastros.model.Cliente;

@ViewScoped
@ManagedBean(name="ClienteVB")
public class ClienteJavaBean 
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
private boolean edicao;
private String  tela;
private List<Cliente> listaCliente;

@PostConstruct
public void init()
{
    Cliente tCliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestMap().get("CLIENTE");
    if (tCliente != null)
    {
        id = tCliente.getId();
        email = tCliente.getEmail();
        senha = tCliente.getSenha();
        nome = tCliente.getNome();
        dataNascimento = java.sql.Date.valueOf(tCliente.getDataNascimento());
        telefone = tCliente.getTelefone();
        cpf = tCliente.getCpf();
        endereco = tCliente.getEndereco();
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

public List<Cliente> getListaCliente() {
	return listaCliente;
}

public void setListaCliente(List<Cliente> listaCliente) {
	this.listaCliente = listaCliente;
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
    edicao = false;

    return tela;
}

// Métodos da Controller
public String cadastrar()
{
    System.out.println("ClienteVB - Cadastrar : " + this);

    Cliente tCliente = new Cliente();
    tCliente.setEmail(email);
    tCliente.setSenha(senha);
    tCliente.setNome(nome);
    LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
    tCliente.setDataNascimento(tDataNascimento);
    tCliente.setTelefone(telefone);
    tCliente.setCpf(cpf);
    tCliente.setEndereco(endereco);

    ClienteController tController = new ClienteController();

    ClienteDto tDto = tController.cadastrarCliente(tCliente);
    if (tDto.isOk())
    {
        // Ok, incluído
        id = tDto.getCliente().getId();

        // Colocando a mensagem do sistema
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CLIENTE", tCliente);
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
    System.out.println("ClienteVB - Alterar : " + this);

    Cliente tCliente = new Cliente();
    tCliente.setId(id);
    tCliente.setEmail(email);
    tCliente.setSenha(senha);
    tCliente.setNome(nome);
    LocalDate tDataNascimento = new java.sql.Date(dataNascimento.getTime()).toLocalDate();
    tCliente.setDataNascimento(tDataNascimento);
    tCliente.setTelefone(telefone);
    tCliente.setCpf(cpf);
    tCliente.setEndereco(endereco);

    ClienteController tController = new ClienteController();

    ClienteDto tDto = tController.atualizarCliente(tCliente);
    if (tDto.isOk())
    {
        // Ok, alterado
        id = tDto.getCliente().getId();

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
    System.out.println("ClienteVB - Consultar : " + this);

    ClienteController tController = new ClienteController();

    ClienteDto tDto = tController.recuperarCliente(id);
    if (tDto.isOk())
    {
        // Ok, recuperado
        Cliente tCliente = tDto.getCliente();
        id = tCliente.getId();
        email = tCliente.getEmail();
        senha = tCliente.getSenha();
        nome = tCliente.getNome();
        dataNascimento = java.sql.Date.valueOf(tCliente.getDataNascimento());
        telefone = tCliente.getTelefone();
        cpf = tCliente.getCpf();
        endereco = tCliente.getEndereco();

        // indicando que a pesquisa deu certo
        edicao = true;
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CLIENTE", tCliente);
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
    System.out.println("ClienteVB - Excluir : " + this);

    ClienteController tController = new ClienteController();

    ClienteDto tDto = tController.removeCliente(id);
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
    System.out.println("ClienteVB - Pesquisar : " + this);

    ClienteController tController = new ClienteController();

    ClienteDto tDto = tController.pesquisarClientesPorNome(nome);
    if (tDto.isOk())
    {
        // Ok, recuperado
        listaCliente = tDto.getLista();
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
    tBuilder.append("]");
    return tBuilder.toString();
}
}