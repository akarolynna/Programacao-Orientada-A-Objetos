package modelo;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Filial {

	@Id @GeneratedValue
	private Long id;
	private String nome;
	private String cnpj;
	
	@OneToOne
	private Endereco endereco;
	
	@OneToMany(mappedBy = "filial")
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	

	//� este carinha aqui que faz conseguirmos salvar o endere�o.� ele quem faz a m�gica toda;
	  public Filial() {
	        endereco = new Endereco(); 
	    }
	  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	


}
