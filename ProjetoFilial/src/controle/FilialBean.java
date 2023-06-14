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

	private int numeroFuncionariosFilial;
	private String tituloPagina;
	

	@PostConstruct
	public void inicializar() {
		motrarFiliais();
		tituloTelaInicial();
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
		filial = new Filial(); 
		motrarFiliais();
		gravar = true; 
		tituloTelaInicial();
	}
	
	
	public void selecionandoFilial(Filial f) { 
		 tituloTelaEditar();
		filial = f;
		gravar = false;
		
	}
	public void tituloTelaInicial() {
		tituloPagina = "Cadastro de Filial";
	}
	public void tituloTelaEditar() {
		tituloPagina = "Edição de Filial";
	}

	// Mï¿½todos Getters and setters
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

	public int getNumeroFuncionariosFilial() {
		return numeroFuncionariosFilial;
	}

	public void setNumeroFuncionariosFilial(int numeroFuncionariosFilial) {
		this.numeroFuncionariosFilial = numeroFuncionariosFilial;
	}

	public String getTituloPagina() {
		return tituloPagina;
	}

	public void setTituloPagina(String tituloPagina) {
		this.tituloPagina = tituloPagina;
	}

}
