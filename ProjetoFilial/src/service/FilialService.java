package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Filial;

@Stateless
public class FilialService extends GenericService<Filial> {
//N�o preciso colocar o atualizar, excluir filial a parte do funcionario;
	// A� l� no funcion�rio eu terei que informar sim!
	// Construtor desta classe
	public FilialService() {
		super(Filial.class);
	}

	public void criarFilial(Filial filial) {

		// Tiramos o nosso if, pois o nosso endere�o nunca ser� nulo
		getEntityManager().persist(filial.getEndereco());
		getEntityManager().persist(filial);
	}

	public void atualizarFilial(Filial filial) {
		getEntityManager().merge(filial.getEndereco());
		getEntityManager().merge(filial);
	}

	public void excluirFilial(Filial filial) {

		// Isso pode estar dando erro pq filial tem chave estrangeira em endere�o. Ent�o
		// ele n�o permite excluir por isso!
		getEntityManager().remove(getEntityManager().merge(filial));
		getEntityManager().remove(getEntityManager().merge(filial.getEndereco()));

	}

	public List<Filial> listarFiliaisPorNome() {
		// O final ele est� ali na frente, pois ele n�o permite alterar o atributo. �
		// como se fosse o final
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Filial> cquery = cb.createQuery(Filial.class);
		Root<Filial> rootFilial = cquery.from(Filial.class);
		cquery.select(rootFilial);
		cquery.orderBy(cb.asc(rootFilial.get("nome")));

		List<Filial> resultado = getEntityManager().createQuery(cquery).getResultList();

		return resultado;

	}

}
