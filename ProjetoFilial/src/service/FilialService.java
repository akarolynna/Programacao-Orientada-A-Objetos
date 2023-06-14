package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Filial;

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

	public List<Filial> listarFiliaisPorNome() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Filial> cquery = cb.createQuery(Filial.class);
		Root<Filial> rootFilial = cquery.from(Filial.class);
		cquery.select(rootFilial);
		cquery.orderBy(cb.asc(rootFilial.get("nome")));

		List<Filial> resultado = getEntityManager().createQuery(cquery).getResultList();

		return resultado;

	}

}
