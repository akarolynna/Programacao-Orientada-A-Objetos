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
public class FuncionarioBean {

	@EJB
	private FuncionarioService funcionarioService;

	@EJB
	private FilialService filialService;

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	private List<Filial> filiais = new ArrayList<Filial>();
	public Boolean gravar = true;
	private Long idFilial = 0L;
	private String texto;
	private String tituloPagina;
	


	public void carregarPaginaNormal() {
		tituloPagina = "Cadastro de Funcionário";
	}

	public void carregarPaginaEditar() {
		tituloPagina = "Edição de Funcionário";
	}

	public void pesquisar() {
		funcionarios = funcionarioService.pesquisarFuncionarioLike(texto);
	}

	@PostConstruct
	public void inicializar() {
		mostrarFuncionarios();
		filiais = filialService.listarFiliaisPorNome();
		carregarPaginaNormal();
	}

	public void mostrarFuncionarios() {
		funcionarios = funcionarioService.listarFuncionarioPeloNome();

	}

	public void selecionarFuncionario(Funcionario func) {
		carregarPaginaEditar();
		/*
		 * Colocamos aqui, pois queremos que a mensagem apareça assim que ele clica no
		 * botão editar e é este método que está sendo chamado lá no nosso botão editar
		 */
		FacesContext.getCurrentInstance().addMessage("msg2", new FacesMessage(
				"Não é possível alterar a filial em que você está cadastrado, por isso, o campo da filial está desabilitado!"));
		funcionario = func;
		gravar = false;
	}

	public void gravar() {
		String cpfFormatado = formatarCPF(funcionario.getCpf());
		  if (cpfFormatado.equals("CPF inválido")) {
			  FacesContext.getCurrentInstance().addMessage("msg3", new FacesMessage(
						"A operação não pode ser  realizada, pois o número de CPF é inválido!"));
			  funcionario = new Funcionario();
		        return;
		    }

		Filial filial = filialService.obtemPorId(idFilial);
		funcionario.setFilial(filial);

		funcionarioService.CriarFuncionario(funcionario);
		funcionario = new Funcionario();
		mostrarFuncionarios();
		gravar = true;
		idFilial = 0L;

	}

	public void editar() {

		funcionarioService.EditarDados(funcionario);
		funcionario = new Funcionario();
		mostrarFuncionarios();
		gravar = true;
		carregarPaginaNormal();

	}

	
	public void excluir(Funcionario funcionario) {
		funcionarioService.excluirFuncionario(funcionario);
		mostrarFuncionarios();
	}
	
	public String formatarCPF(String cpf)  {
	    // Remove caracteres não numéricos do CPF
	    String cpfNumerico = cpf.replaceAll("\\D", "");

	    // Verifica se o CPF possui 11 dígitos
	    if (cpfNumerico.length() != 11) {
	        return "CPF inválido";
	    }

	    // Formata o CPF com os separadores adequados
	    String cpfFormatado = cpfNumerico.substring(0, 3) + "." +
	                          cpfNumerico.substring(3, 6) + "." +
	                          cpfNumerico.substring(6, 9) + "-" +
	                          cpfNumerico.substring(9);

	    return cpfFormatado;
	}


	// Métodos getters and setters

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Boolean getGravar() {
		return gravar;
	}

	public void setGravar(Boolean gravar) {
		this.gravar = gravar;
	}

	public Long getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Long idFilial) {
		this.idFilial = idFilial;
	}

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

	public FilialService getFilialService() {
		return filialService;
	}

	public void setFilialService(FilialService filialService) {
		this.filialService = filialService;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

}
