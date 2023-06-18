package controle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Filial;
import modelo.Funcionario;
import service.FilialService;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class FilialBean {

	@EJB
	private FilialService filialService;
	
	@EJB
	private FuncionarioService funcionarioService;
	 
	public Boolean gravar = true;

	private Filial filial = new Filial();
	private List<Filial> filiais = new ArrayList<Filial>();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    private int numeroFuncionarios;
	private String tituloPagina;
	

	@PostConstruct
	public void inicializar() {
		motrarFiliais();
		tituloTelaInicial();
	}

	public void gravar() {
		String cnpjFormatado = formatarCNPJ(filial.getCnpj());
		if (cnpjFormatado.equals("CNPJ inválido")) {
			FacesContext.getCurrentInstance().addMessage("msg1",
					new FacesMessage("A operação não pode ser  realizada, pois o número de CNPJ é inválido!"));
			// Limpa os campos preenchidos pelo usuário
			filial = new Filial();
			return;
		}
		filialService.criarFilial(filial);
		filial = new Filial();
		motrarFiliais();
		gravar = true;
	}

	public void motrarFiliais() {
		filiais = filialService.listarFiliaisPorNome();
	}

	public void atualizarDadosFilial() {
		filialService.atualizarFilial(filial);
		filial = new Filial();
		motrarFiliais();
		gravar = true;
		tituloTelaInicial();
	}

	public void tituloTelaInicial() {
		tituloPagina = "Cadastro de Filial";
	}

	public void tituloTelaEditar() {
		tituloPagina = "Edição de Filial";
	}

	public String formatarCNPJ(String cnpj) {
		// Remove caracteres não numéricos do CNPJ
		String cnpjNumerico = cnpj.replaceAll("\\D", "");

		// Verifica se o CNPJ possui 14 dígitos
		if (cnpjNumerico.length() != 14) {
			return "CNPJ inválido";
		}

		// Formata o CNPJ com os separadores adequados
		String cnpjFormatado = cnpjNumerico.substring(0, 2) + "." + cnpjNumerico.substring(2, 5) + "."
				+ cnpjNumerico.substring(5, 8) + "/" + cnpjNumerico.substring(8, 12) + "-" + cnpjNumerico.substring(12);

		return cnpjFormatado;
	}

	public void selecionandoFilial(Filial f) {
		tituloTelaEditar();
		filial = f;
		gravar = false;
	}

	
	// Métodos Getters and setters
	public Filial getFilial() {
		return filial;
	}

	public FilialService getFilialService() {
		return filialService;
	}

	public void setFilialService(FilialService filialService) {
		this.filialService = filialService;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

	public Boolean getGravar() {
		return gravar;
	}

	public void setGravar(Boolean gravar) {
		this.gravar = gravar;
	}

	public int getNumeroFuncionarios() {
		return numeroFuncionarios;
	}

	public void setNumeroFuncionarios(int numeroFuncionarios) {
		this.numeroFuncionarios = numeroFuncionarios;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
