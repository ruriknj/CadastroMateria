package CRUD;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import dao.MateriaDAO;
import dao.MateriaDAOImp;
import entidade.Atividade;
import entidade.Estudante;
import entidade.Materia;
import util.JpaUtil;

public class TestCRUD {

	private static MateriaDAO materiaDAO = new MateriaDAOImp();
	private static EntityManager ent = JpaUtil.getEntityManager();

	public static void main(String[] args) {

		TestSalvar();
		// TestlistarMateria();
		// TestRemover(13);
	}

	public static void TestSalvar() {

		// Relação: OnetoMany

		Atividade atividade = new Atividade(null, "Matematica7", "estudo de vetores7");
		Materia materia = new Materia(null, "Matematica7", "fisica7 classica7");
		atividade.setMateria(materia);
		materia.getAtividades().add(atividade);
		// ============================================================================
		// Relação: ManytoOne

		Estudante estudante = new Estudante(null, "nick7", "nick@com", "1234567");
		estudante.getMaterias().add(materia);
		materia.setEstudante(estudante);

		System.out.println("Cadastro de Materia: " + materia);

		materiaDAO.salvar(materia);

	}

	public static void TestlistarMateria() {

		List<Materia> materias = new ArrayList<Materia>();

		materias = materiaDAO.listarMateria();

		System.out.println("Lista Materia: " + materias);
	}

	public static void TestRemover(int id) {

		Materia materia = ent.find(Materia.class, id);

		materiaDAO.remover(materia.getId());
	}
}
