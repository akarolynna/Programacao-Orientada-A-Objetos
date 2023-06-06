package controle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Filial;
import service.FilialService;

@ViewScoped
@ManagedBean
public class FilialBean {

	@EJB
	private FilialService filialService;
	public Boolean gravar = true;

	private Filial filial = new Filial();
	private List<Filial> filiais = new ArrayList<Filial>();
	

	@PostConstruct
	public void inicializar() {
		motrarFiliais();
	}

	public void gravar() {
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
		filial = new Filial(); // filial está recebendo uma nova instancia
		motrarFiliais();
		gravar = true; //atualizamos a lista
	}
	
	//Esse carinha quando eu clicar nele ele levará as informações da filial que eu cliquei lá para serem mudadas
	public void selecionandoFilial(Filial f) { 
		filial = f;
		gravar = false;
	}
	/* ESSE REMOVER SERVIRÁ DE BASE PARA O MEU REMOVER EM FUNCIONARIO, POREM FILIAL NÃO TEM REMOVE
	public void excluirFilial(Filial filial) {
		filialService.excluirFilial(filial);
		motrarFiliais();
	}
	
	
	
	*/
	
	
	// Métodos Getters and setters
	public FilialService getFilialService() {
		return filialService;
	}

	public void setFilialService(FilialService filialService) {
		this.filialService = filialService;
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

	public Boolean getGravar() {
		return gravar;
	}

	public void setGravar(Boolean gravar) {
		this.gravar = gravar;
	}

}
