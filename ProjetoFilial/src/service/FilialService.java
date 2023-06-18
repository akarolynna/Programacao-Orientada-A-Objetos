package service;

import java.util.List;
import javax.persistence.criteria.Root;
import modelo.Filial;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


@Stateless
public class FilialService extends GenericService<Filial> {

	public FilialService() {
		super(Filial.class);
	}

	public void criarFilial(Filial filial) {
		getEntityManager().persist(filial.getEndereco());
		getEntityManager().persist(filial);
	}

	public void atualizarFilial(Filial filial) {
		getEntityManager().merge(filial.getEndereco());
		getEntityManager().merge(filial);
	}

	public void excluirFilial(Filial filial) {
		getEntityManager().remove(getEntityManager().merge(filial));
		getEntityManager().remove(getEntityManager().merge(filial.getEndereco()));

	}
	public Filial obtemFilialPorId(Long id) {
        return getEntityManager().find(Filial.class, id);
    }

	public List<Filial> listarFiliaisPorNome() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Filial> cquery = cb.createQuery(Filial.class);
		Root<Filial> rootFilial = cquery.from(Filial.class);
		cquery.select(rootFilial);
		cquery.orderBy(cb.asc(rootFilial.get("nome")));

		List<Filial> resultado = getEntityManager().createQuery(cquery).getResultList();

		return resultado;

	}
	/*public int obterNumeroFuncionariosPorFilial(Filial filial) {
	    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
	    CriteriaQuery<Long> query = cb.createQuery(Long.class);
	    Root<Filial> root = query.from(Filial.class);
	    
	    // Faz o join com a entidade Funcionario
	    Join<Filial, Funcionario> funcionariosJoin = root.join("funcionarios", JoinType.LEFT);
	    
	    // Aplica as restrições para a filial desejada
	    query.select(cb.count(funcionariosJoin));
	    query.where(cb.equal(root, filial));

	    TypedQuery<Long> typedQuery =getEntityManager().createQuery(query);
	    return typedQuery.getSingleResult().intValue();
	}
*/
}
