package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Filial;

@Stateless
public class FilialService extends GenericService<Filial> {
//Não preciso colocar o atualizar, excluir filial a parte do funcionario;
	// Aí lá no funcionário eu terei que informar sim!
	// Construtor desta classe
	public FilialService() {
		super(Filial.class);
	}

	public void criarFilial(Filial filial) {

		// Tiramos o nosso if, pois o nosso endereço nunca será nulo
		getEntityManager().persist(filial.getEndereco());
		getEntityManager().persist(filial);
	}

	public void atualizarFilial(Filial filial) {
		getEntityManager().merge(filial.getEndereco());
		getEntityManager().merge(filial);
	}

	public void excluirFilial(Filial filial) {

		// Isso pode estar dando erro pq filial tem chave estrangeira em endereço. Então
		// ele não permite excluir por isso!
		getEntityManager().remove(getEntityManager().merge(filial));
		getEntityManager().remove(getEntityManager().merge(filial.getEndereco()));

	}

	public List<Filial> listarFiliaisPorNome() {
		// O final ele está ali na frente, pois ele não permite alterar o atributo. É
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
