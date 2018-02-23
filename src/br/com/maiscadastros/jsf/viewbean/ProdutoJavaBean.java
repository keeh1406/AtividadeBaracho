package br.com.maiscadastros.jsf.viewbean;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.maiscadastros.controller.LojaController;
import br.com.maiscadastros.controller.ProdutoController;
import br.com.maiscadastros.dto.LojaDto;
import br.com.maiscadastros.dto.ProdutoDto;
import br.com.maiscadastros.model.Loja;
import br.com.maiscadastros.model.Produto;

@ViewScoped
@ManagedBean(name="ProdutoVB")
public class ProdutoJavaBean 
{
    // Atributos - Valores dos componentes visuais
	private Integer id;
	private String nome;
	private String nomeLoja;
	private String descricao;
	private Date dataValidade;
	private String marca;
	private Integer idLoja;
    private boolean edicao;
    private String  tela;
    private List<Produto> listaProduto;
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
	
	public String getNomeLoja()
    {
        return nomeLoja;
    }

    public void setNomeLoja(String pNomeLoja)
    {
        nomeLoja = pNomeLoja;
    }

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
	public List<Loja> getListaLoja() {
		return listaLoja;
	}

	public void setListaLoja(List<Loja> listaLoja) {
		this.listaLoja = listaLoja;
	}
	
	@PostConstruct
    public void init()
    {
		Produto tProduto2 = (Produto) FacesContext.getCurrentInstance().getExternalContext()
                .getRequestMap().get("PRODUTO");
if (tProduto2 != null)
{
    id = tProduto2.getId();
    nome = tProduto2.getNome();
    descricao = tProduto2.getDescricao();
    dataValidade = java.sql.Date.valueOf(tProduto2.getDataValidade());
    marca = tProduto2.getMarca();
    edicao = true;
}
        Produto tProduto = (Produto) FacesContext.getCurrentInstance().getExternalContext()
                        .getRequestMap().get("PRODUTO");
        if (tProduto != null)
        {
            id = tProduto.getId();
            idLoja = tProduto.getIdLoja();

            LojaController tLojaController = new LojaController();

            LojaDto tLojaDto = tLojaController.recuperarLoja(idLoja);
            if (tLojaDto.isOk())
            {
                Loja tLoja = tLojaDto.getLoja();
                nomeLoja = tLoja.getNome();
            }
            else
            {
                nomeLoja=null;
            }
        }
        
        LojaController tController = new LojaController();

        LojaDto tDto = tController.pesquisarLoja();
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
    }

	// Métodos da Controller
    public String limpar()
    {
        id = null;
        nome = null;
        descricao = null;
        dataValidade = null;
        marca = null;
        idLoja = null;
        edicao = false;

        return tela;
    }
	
	// Métodos da Controller
	public String cadastrar()
	{
	    System.out.println("ProdutoVB - Cadastrar : " + this);

	    Produto tProduto = new Produto();
	    tProduto.setNome(nome);
	    tProduto.setDescricao(descricao);
	    LocalDate tDataValidade = new java.sql.Date(dataValidade.getTime()).toLocalDate();
	    tProduto.setDataValidade(tDataValidade);
	    tProduto.setMarca(marca);
	    tProduto.setIdLoja(idLoja);

	    ProdutoController tController = new ProdutoController();

	    ProdutoDto tDto = tController.cadastrarProduto(tProduto);
	    if (tDto.isOk())
	    {
	        // Ok, incluído
	        id = tDto.getProduto().getId();

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
	        System.out.println("ProdutoVB - Alterar : " + this);

	        Produto tProduto = new Produto();
	        tProduto.setId(id);
	        tProduto.setNome(nome);
	        tProduto.setDescricao(descricao);
	        LocalDate tDataValidade = new java.sql.Date(dataValidade.getTime()).toLocalDate();
	        tProduto.setDataValidade(tDataValidade);
	        tProduto.setMarca(marca);
	        tProduto.setIdLoja(idLoja);

	        ProdutoController tController = new ProdutoController();

	        ProdutoDto tDto = tController.atualizarProduto(tProduto);
	        if (tDto.isOk())
	        {
	            // Ok, alterado
	            id = tDto.getProduto().getId();

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
	        System.out.println("ProdutoVB - Consultar : " + this);

	        ProdutoController tController = new ProdutoController();

	        ProdutoDto tDto = tController.recuperarProduto(id);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            Produto tProduto = tDto.getProduto();
	            id = tProduto.getId();
	            nome = tProduto.getNome();
	            descricao = tProduto.getDescricao();
	            dataValidade = java.sql.Date.valueOf(tProduto.getDataValidade());
	            marca = tProduto.getMarca();
	            idLoja = tProduto.getIdLoja();

	            // indicando que a pesquisa deu certo
	            edicao = true;
	            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PRODUTO", tProduto);
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
	        System.out.println("ProdutoVB - Excluir : " + this);

	        ProdutoController tController = new ProdutoController();

	        ProdutoDto tDto = tController.removeProduto(id);
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
	        System.out.println("ProdutoVB - Pesquisar : " + this);

	        ProdutoController tController = new ProdutoController();

	        ProdutoDto tDto = tController.pesquisarProdutosPorNome(nome);
	        if (tDto.isOk())
	        {
	            // Ok, recuperado
	            listaProduto = tDto.getLista();
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
	    tBuilder.append(nome);
	    tBuilder.append(", ");
	    tBuilder.append(nomeLoja);
        tBuilder.append(", ");
	    tBuilder.append(descricao);
	    tBuilder.append(", ");
	    tBuilder.append(dataValidade);
	    tBuilder.append(", ");
	    tBuilder.append(marca);
	    tBuilder.append(", ");
	    tBuilder.append(idLoja);
	    tBuilder.append("]");
	    return tBuilder.toString();
	}
	}
