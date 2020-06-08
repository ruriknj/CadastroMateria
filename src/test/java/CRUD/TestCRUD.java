package CRUD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.MateriaDAO;
import dao.MateriaDAOImp;
import entidade.Atividade;
import entidade.Estudante;
import entidade.Materia;

public class TestCRUD {

	public static void main(String[] args) {
		
		TestSalvar();
		

	}

	public static void TestSalvar() {
		
		MateriaDAO materiaDAO;
		materiaDAO = new MateriaDAOImp();
		
		//Relação: OnetoMany
		
		Atividade atividade = new Atividade(null, "Matematica1" , "estudo de vetores");
		Materia materia = new Materia(null, "Matematica", "nick@com", "123", "fisica classica");
		atividade.setMateria(materia);
		materia.getAtividades().addAll(Arrays.asList(atividade));
	//============================================================================	
		//Relação: ManytoOne
		
		Estudante estudante = new Estudante(null, "nick", "nick@com", "123");
		estudante.getMaterias().addAll(Arrays.asList(materia));
		materia.setEstudante(estudante);
		
	
		System.out.println("Cadastro de Materia: " + materia );
		
		materiaDAO.salvar(materia);
		
	
	}
}
