package br.com.maiscadastros.jsf.viewbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.maiscadastros.controller.LojaController;
import br.com.maiscadastros.dto.LojaDto;
import br.com.maiscadastros.model.Loja;

@ViewScoped
@ManagedBean(name="LojaVB")
public class LojaJavaBean 
{
    // Atributos - Valores dos componentes visuais
	private Integer id;
	private String nome;
	private Long telefone;
	private String email;
	private Long cnpj;
	private String endereco;
    private boolean edicao;
    private String  tela;
    private List<Loja> listaLoja;
    
    @PostConstruct
    public void init()
    {
        Loja tLoja = (Loja) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("LOJA");
        if (tLoja != null)
        {
            id = tLoja.getId();
            email = tLoja.getEmail();
            nome = tLoja.getNome();
            telefone = tLoja.getTelefone();
            cnpj = tLoja.getCnpj();
            endereco = tLoja.getEndereco();
            edicao = true;
        }
    }
    
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

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
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

	public List<Loja> getListaLoja() {
		return listaLoja;
	}

	public void setListaLoja(List<Loja> listaLoja) {
		this.listaLoja = listaLoja;
	}

	// Métodos da Controller
    public String limpar()
    {
        id = null;
        email = null;
        nome = null;
        telefone = null;
        cnpj = null;
        endereco = null;
        edicao = false;

        return tela;
    }
	
	// Métodos da Controller
	public String cadastrar()
	{
	    System.out.println("LojaVB - Cadastrar : " + this);

	    Loja tLoja = new Loja();
	    tLoja.setEmail(email);
	    tLoja.setNome(nome);
	    tLoja.setTelefone(telefone);
	    tLoja.setCnpj(cnpj);
	    tLoja.setEndereco(endereco);

	    LojaController tController = new LojaController();

	    LojaDto tDto = tController.cadastrarLoja(tLoja);
	    if (tDto.isOk())
	    {
	        // Ok, incluído
	        id = tDto.getLoja().getId();

	        // Colocando a mensagem do sistema
	        FacesContext.getCurrentInstance().addMessage(null,
	                        new FacesMessage(FacesMessage.SEVERITY_INFO, tDto.getMensagem(), tDto.getMensagem()));
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
	        System.out.println("LojaVB - Alterar : " + this);

	        Loja tLoja = new Loja();
	        tLoja.setId(id);
	        tLoja.setEmail(email);
	        tLoja.setNome(nome);
	        tLoja.setTelefone(telefone);
	        tLoja.setCnpj(cnpj);
	        tLoja.setEndereco(endereco);

	        LojaController tController = new LojaController();

	        LojaDto tDto = tController.atualizarLoja(tLoja);
	        if (tDto.isOk())
	        {
	            // Ok, alterado
	            id = tDto.getLoja().getId();

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
	        System.out.println("LojaVB - Consultar : " + this);

	        LojaController tController = new LojaController();

	        LojaDto tDto = tController.recuperarLoja(id);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            Loja tLoja = tDto.getLoja();
	            id = tLoja.getId();
	            email = tLoja.getEmail();
	            nome = tLoja.getNome();
	            telefone = tLoja.getTelefone();
	            cnpj = tLoja.getCnpj();
	            endereco = tLoja.getEndereco();

	            // indicando que a pesquisa deu certo
	            edicao = true;
	            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("LOJA", tLoja);
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
	        System.out.println("LojaVB - Excluir : " + this);

	        LojaController tController = new LojaController();

	        LojaDto tDto = tController.removeLoja(id);
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
	        System.out.println("LojaVB - Pesquisar : " + this);

	        LojaController tController = new LojaController();

	        LojaDto tDto = tController.pesquisarLojasPorNome(nome);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            listaLoja = tDto.getLista();
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
	    tBuilder.append(nome);
	    tBuilder.append(", ");
	    tBuilder.append(telefone);
	    tBuilder.append(", ");
	    tBuilder.append(cnpj);
	    tBuilder.append(", ");
	    tBuilder.append(endereco);
	    tBuilder.append("]");
	    return tBuilder.toString();
	}
	}