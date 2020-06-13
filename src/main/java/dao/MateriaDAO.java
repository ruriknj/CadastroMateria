package dao;

import java.io.IOException;
import java.util.List;

import entidade.Atividade;
import entidade.Estudante;
import entidade.Materia;

public interface MateriaDAO {

	public void salvar(Materia materia);

	public List<Materia> listarMateria();

	public void remover(int id);

	public List<Estudante> listarEstudante();

	public Materia pesquisar(int id);

	public List<Materia> listarTodos();

	public void removerAtividade(int id);

}
