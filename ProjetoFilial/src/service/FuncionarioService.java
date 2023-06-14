package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import modelo.Filial;
import modelo.Funcionario;


@Stateless
public class FuncionarioService extends GenericService<Funcionario> {

	public FuncionarioService() {
		super(Funcionario.class);
		
	}
	public void CriarFuncionario(Funcionario funcionario) {
		getEntityManager().persist(funcionario.getEndereco());
		getEntityManager().persist(funcionario);
		
	}
	
	public void EditarDados(Funcionario funcionario) {
		getEntityManager().merge(funcionario.getEndereco());
		getEntityManager().merge(funcionario);
	}
	
	public void excluirFuncionario(Funcionario funcionario) {
		getEntityManager().remove(getEntityManager().merge(funcionario));
		getEntityManager().remove(getEntityManager().merge(funcionario.getEndereco()));
	
	}

	public List<Funcionario> listarFuncionarioPeloNome() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Funcionario> cquery = cb.createQuery(Funcionario.class);
		Root<Funcionario> rootFuncionario = cquery.from(Funcionario.class);
		cquery.select(rootFuncionario);
		cquery.orderBy(cb.asc(rootFuncionario.get("nome")));

		List<Funcionario> resultado = getEntityManager().createQuery(cquery).getResultList();

		return resultado;

	}
	
	public List<Funcionario> pesquisarFuncionarioLike(String nome) {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Funcionario> cquery = cb.createQuery(Funcionario.class);
	    Root<Funcionario> rootFuncionario = cquery.from(Funcionario.class);

	    final Expression<String> expNome = rootFuncionario.get("nome");
	    cquery.select(rootFuncionario);

	    cquery.where(cb.like(cb.lower(expNome), "%" + nome.toLowerCase() + "%")); 
	    cquery.orderBy(cb.asc(expNome));

	    List<Funcionario> resultado = getEntityManager().createQuery(cquery).getResultList();

	    return resultado;
	}

	public List<Funcionario> listarFuncionariosPorFilial(Filial filial) {
	    EntityManager em = getEntityManager();
	    TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE f.filial = :filial", Funcionario.class);
	    query.setParameter("filial", filial);
	    return query.getResultList();
	}
	public List<Funcionario> listarTodosFuncionarios() {
	    EntityManager em = getEntityManager();
	    TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
	    return query.getResultList();
	}

	public List<Funcionario> listarFuncionariosPorFaixaSalarial(double salarioMinimo, double salarioMaximo) {
	    List<Funcionario> funcionarios = listarTodosFuncionarios(); 
	    
	    List<Funcionario> funcionariosFiltrados = new ArrayList<Funcionario>(); 

	    for (Funcionario funcionario : funcionarios) {
	        double salario = funcionario.getSalario(); 
	        
	        if (salario >= salarioMinimo && salario <= salarioMaximo) {
	            funcionariosFiltrados.add(funcionario); 
	        }
	    }
	    
	    return funcionariosFiltrados;
	}





}
