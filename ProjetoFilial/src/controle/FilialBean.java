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
		filial = new Filial(); // filial est� recebendo uma nova instancia
		motrarFiliais();
		gravar = true; //atualizamos a lista
	}
	
	//Esse carinha quando eu clicar nele ele levar� as informa��es da filial que eu cliquei l� para serem mudadas
	public void selecionandoFilial(Filial f) { 
		filial = f;
		gravar = false;
	}
	/* ESSE REMOVER SERVIR� DE BASE PARA O MEU REMOVER EM FUNCIONARIO, POREM FILIAL N�O TEM REMOVE
	public void excluirFilial(Filial filial) {
		filialService.excluirFilial(filial);
		motrarFiliais();
	}
	
	
	
	*/
	
	
	// M�todos Getters and setters
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
