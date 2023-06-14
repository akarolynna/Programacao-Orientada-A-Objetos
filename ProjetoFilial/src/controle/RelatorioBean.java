package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
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

	private Long idFilial;

	private List<SelectItem> filiais = new ArrayList<SelectItem>();
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

	private double salarioMinimo;
	private double salarioMaximo;

	@PostConstruct
	public void iniciar() {
		idFilial = null;
		carregarFiliais();
	}

	public void carregarFiliais() {
		filiais.clear();
		List<Filial> listaFiliais = filialService.listarFiliaisPorNome();

		for (Filial filial : listaFiliais) {
			filiais.add(new SelectItem(filial.getId(), filial.getNome()));
		}
	}

	public void gerarRelatorio() {
		if (idFilial != null) {
			if (idFilial.equals(-1L)) {
				funcionarios = funcionarioService.listarTodosFuncionarios();

				if (funcionarios.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage("msg1",
							new FacesMessage("Não há registro de funcionários!"));
				}
			} else {
				Filial filial = filialService.obtemPorId(idFilial);
				if (filial != null) {
					funcionarios = funcionarioService.listarFuncionariosPorFilial(filial);

					if (funcionarios.isEmpty()) {
						FacesContext.getCurrentInstance().addMessage("msg1",
								new FacesMessage("A filial selecionada não possui registros de funcionarios."));
					}
				}
			}
		} else {
			funcionarios = funcionarioService.listarTodosFuncionarios();
		}

		funcionarios = funcionarioService.listarFuncionariosPorFaixaSalarial(salarioMinimo, salarioMaximo);
	}

	// Getters e setters

	public Long getIdFilial() {
		return idFilial;
	}

	public void setIdFilial(Long idFilial) {
		this.idFilial = idFilial;
	}

	public List<SelectItem> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<SelectItem> filiais) {
		this.filiais = filiais;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

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

	public double getSalarioMinimo() {
		return salarioMinimo;
	}

	public void setSalarioMinimo(double salarioMinimo) {
		this.salarioMinimo = salarioMinimo;
	}

	public double getSalarioMaximo() {
		return salarioMaximo;
	}

	public void setSalarioMaximo(double salarioMaximo) {
		this.salarioMaximo = salarioMaximo;
	}

}
