package service;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
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
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Funcionario> cquery = cb.createQuery(Funcionario.class);
	    Root<Funcionario> rootFuncionario = cquery.from(Funcionario.class);

	    cquery.select(rootFuncionario);
	    cquery.where(cb.equal(rootFuncionario.get("filial"), filial));

	    TypedQuery<Funcionario> query = getEntityManager().createQuery(cquery);
	    List<Funcionario> funcionarios = query.getResultList();

	    return funcionarios;
	
	}
	
	public List<Funcionario> listarTodosFuncionarios() {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Funcionario> cquery = cb.createQuery(Funcionario.class);
	    Root<Funcionario> rootFuncionario = cquery.from(Funcionario.class);

	    cquery.select(rootFuncionario);

	    TypedQuery<Funcionario> query = getEntityManager().createQuery(cquery);
	    List<Funcionario> funcionarios = query.getResultList();

	    return funcionarios;
	}

	public List<Funcionario> listarFuncionarioPorFaixaSalarial(double salarioMinimo, double salarioMaximo) {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Funcionario> query = cb.createQuery(Funcionario.class);
	    Root<Funcionario> rootFuncionario = query.from(Funcionario.class);

	    query.select(rootFuncionario);

	    List<Predicate> predicates = new ArrayList<Predicate>();

	    Path<Double> salarioPath = rootFuncionario.get("salario");
	    predicates.add(cb.between(salarioPath, cb.literal(salarioMinimo), cb.literal(salarioMaximo)));

	    query.where(predicates.toArray(new Predicate[0]));
	    query.orderBy(cb.desc(salarioPath));

	    return getEntityManager().createQuery(query).getResultList();
	}



	public List<Funcionario> listarFuncionarioPorFaixaSalarialEFilial(Long idFilial, double salarioMinimo, double salarioMaximo) {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Funcionario> query = cb.createQuery(Funcionario.class);
	    Root<Funcionario> rootFuncionario = query.from(Funcionario.class);

	    query.select(rootFuncionario);

	    List<Predicate> predicates = new ArrayList<Predicate>();

	    // Verifica se todas as filiais foram selecionadas
	    if (idFilial != null && idFilial != -1L) {
	        Join<Funcionario, Filial> joinFilial = rootFuncionario.join("filial");
	        predicates.add(cb.equal(joinFilial.get("id"), idFilial));
	    }

	    Path<Double> salarioPath = rootFuncionario.get("salario");
	    predicates.add(cb.between(salarioPath, cb.literal(salarioMinimo), cb.literal(salarioMaximo)));

	    query.where(predicates.toArray(new Predicate[0]));
	    query.orderBy(cb.desc(salarioPath));

	    return getEntityManager().createQuery(query).getResultList();
	}
	
	public List<Funcionario> listarFuncionariosOrdenadoPorSalario() {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Funcionario> cquery = cb.createQuery(Funcionario.class);
	    Root<Funcionario> rootFuncionario = cquery.from(Funcionario.class);

	    cquery.select(rootFuncionario);
	    cquery.orderBy(cb.desc(rootFuncionario.get("salario")));

	    TypedQuery<Funcionario> query = getEntityManager().createQuery(cquery);
	    List<Funcionario> funcionarios = query.getResultList();

	    return funcionarios;
	}
	
	public List<Funcionario> listarFuncionariosPorFilialOrdenadoPorSalario(Filial filial) {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Funcionario> cquery = cb.createQuery(Funcionario.class);
	    Root<Funcionario> rootFuncionario = cquery.from(Funcionario.class);

	    cquery.select(rootFuncionario);
	    cquery.where(cb.equal(rootFuncionario.get("filial"), filial));
	    cquery.orderBy(cb.desc(rootFuncionario.get("salario")));

	    TypedQuery<Funcionario> query = getEntityManager().createQuery(cquery);
	    List<Funcionario> funcionarios = query.getResultList();

	    return funcionarios;
	}

}



