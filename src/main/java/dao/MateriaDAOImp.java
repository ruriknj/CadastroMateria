package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import entidade.Materia;
import util.JpaUtil;

public class MateriaDAOImp implements MateriaDAO {

	@Override
	public void salvar(Materia materia) {

		EntityManager ent = JpaUtil.getEntityManager();
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.persist(materia);

		tx.commit();
		ent.close();

	}


}
