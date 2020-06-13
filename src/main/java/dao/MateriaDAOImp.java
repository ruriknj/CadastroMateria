package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import entidade.Atividade;
import entidade.Estudante;
import entidade.Materia;
import util.JpaUtil;

public class MateriaDAOImp implements MateriaDAO {

	@Override
	public void salvar(Materia materia) {

		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();

		tx.begin();

		ent.merge(materia);

		tx.commit();
		ent.close();

	}

	public List<Materia> listarMateria() {

		EntityManager ent = JpaUtil.getEntityManager();

		Query query = ent.createQuery("from Materia m");
		@SuppressWarnings("unchecked")
		List<Materia> materias = query.getResultList();

		return materias;
	}

	public void remover(int id) {

		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();

		Materia materia = ent.find(Materia.class, id);

		tx.begin();

		ent.remove(materia);

		tx.commit();
		ent.close();

	}

	public List<Estudante> listarEstudante() {

		EntityManager ent = JpaUtil.getEntityManager();

		Query query = ent.createQuery("from Estudante u where email != null");
		@SuppressWarnings("unchecked")
		List<Estudante> estudantes = query.getResultList();

		return estudantes;

	}

	@Override
	public Materia pesquisar(int id) {

		EntityManager ent = JpaUtil.getEntityManager();

		Materia materia = ent.find(Materia.class, id);

		return materia;
	}

	public List<Materia> listarTodos() {

		EntityManager ent = JpaUtil.getEntityManager();

		Query query = ent.createQuery("from Materia f");
		@SuppressWarnings("unchecked")
		List<Materia> materias = query.getResultList();

		System.out.println("===== Entrou Consulta: ====");
		System.out.println(materias);

		return materias;
	}

	@Override
	public void removerAtividade(int id) {

		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();

		tx.begin();

		Atividade achou = ent.find(Atividade.class, id);
		System.out.println("Remover atividade final: " + achou);

		ent.remove(achou);

		tx.commit();
		ent.close();

	}

}
