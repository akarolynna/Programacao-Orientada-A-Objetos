package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import modelo.Filial;
import modelo.Funcionario;
import service.FilialService;
import service.FuncionarioService;

@ViewScoped
@ManagedBean
public class RelatorioBean {

	@EJB
	private FilialService filialService;
	@EJB
	private FuncionarioService funcionarioService;
	private Long idFilial = 0L;

  private Filial filial;
	private List<Filial> filiais = new ArrayList<Filial>();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	private Double salarioMinimo = null;
	private Double salarioMaximo = null;

	@PostConstruct
	public void iniciar() {
		idFilial = null;
		carregarFiliais();
	}

	public void carregarFiliais() {
		filiais = filialService.listarFiliaisPorNome();
	}
	public void gerarRelatorio() {
	    if (idFilial == null || idFilial.equals(-1L)) {
	        if (salarioMinimo == 0.0 && salarioMaximo == 0.0) {
	            funcionarios = funcionarioService.listarFuncionariosOrdenadoPorSalario();
	        } else {
	            funcionarios = funcionarioService.listarFuncionarioPorFaixaSalarial(salarioMinimo, salarioMaximo);
	        }
	    } else {
	        Filial filial = filialService.obtemPorId(idFilial);
	        if (filial != null) {
	            if (salarioMinimo == 0.0 && salarioMaximo == 0.0) {
	                funcionarios = funcionarioService.listarFuncionariosPorFilialOrdenadoPorSalario(filial);
	            } else {
	                funcionarios = funcionarioService.listarFuncionarioPorFaixaSalarialEFilial(filial.getId(), salarioMinimo, salarioMaximo);
	            }
	        }
	    }

	    if (funcionarios.isEmpty()) {
	        FacesContext.getCurrentInstance().addMessage("msg1",
	                new FacesMessage("Nenhum Funcionário encontrado com essa faixa salarial!"));
	    }
	}


	// Getters e setters
	
	public FilialService getFilialService() {
		return filialService;
	}

	public void setFilialService(FilialService filialService) {
		this.filialService = filialService;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public Long getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Long idFilial) {
		this.idFilial = idFilial;
	}

	public Filial getFilial() {
		return filial;
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

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Double getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(Double salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public Double getSalarioMaximo() {
		return salarioMaximo;
	}

	public void setSalarioMaximo(Double salarioMaximo) {
		this.salarioMaximo = salarioMaximo;
	}
	
}
