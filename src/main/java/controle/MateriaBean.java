package controle;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import dao.MateriaDAO;
import dao.MateriaDAOImp;
import entidade.Atividade;
import entidade.Estudante;
import entidade.Materia;
import util.JpaUtil;

@ManagedBean(name = "MateriaBean")
@SessionScoped
public class MateriaBean {

	private Materia materia;
	private MateriaDAO materiaDAO;
	private Estudante estudante;
	private int idAtividadeSelecionado;
	private Atividade atividadeAdicionado;
	private int idMateriaSelecionada;
	private List<Materia> materias;
	private List<Atividade> atividades;
	private List<Estudante> estudantes;
	private String emailSelecionado;

	public MateriaBean() {

		this.materia = new Materia();
		this.estudante = new Estudante();
		this.atividadeAdicionado = new Atividade();
		this.materiaDAO = new MateriaDAOImp();
		this.materias = new ArrayList<Materia>();
		this.atividades = new ArrayList<Atividade>();
		this.estudante.setMaterias(new ArrayList<Materia>());
		this.materia.getAtividades().add(atividadeAdicionado);

	}

	public void adicionarAtividade() {

		Atividade novo = new Atividade();

		System.out.println("item digitado: " + this.atividadeAdicionado);

		novo.setMateria(this.materia);

		novo.setTitulo(this.atividadeAdicionado.getTitulo());
		novo.setObservacao(this.atividadeAdicionado.getObservacao());
		System.out.println("Item novo 1: " + novo);
		boolean achou = false;
		for (Atividade item : this.materia.getAtividades()) {
			if (item.getTitulo().equalsIgnoreCase(this.atividadeAdicionado.getTitulo())) {
				achou = true;
				System.out.println("Item novo 2: " + novo);
			}
		}

		if (achou) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Já existe na lista !!!"));
		} else {
			this.materia.getAtividades().add(novo);
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Sucesso !!!"));

	}

	public void adicionarMateria() {

		Materia novo = new Materia();

		if (!this.existeMateria(materia)) {

			System.out.println("item digitado: " + this.materia);

			novo.setEstudante(this.estudante);
			novo.setNome(this.materia.getNome());
			novo.setDescricao(this.materia.getDescricao());
			System.out.println("Item novo 1: " + novo);

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Materia já existe !!!"));
		}
		this.estudante.getMaterias().add(novo);
		this.materia.setEstudante(this.estudante);

		System.out.println("Item novo 2: " + this.materia);
	}

	private boolean existeMateria(Materia materia) {
		boolean retorno = false;

		for (Materia matLista : this.estudante.getMaterias()) {
			if (matLista.getNome().equals(materia.getNome())
					&& matLista.getDescricao().equals(materia.getDescricao())) {
				retorno = true;
			}
		}
		return retorno;
	}

	public void adicionar() {

		System.out.println("Materia Salva: " + this.materia);

		this.materiaDAO.salvar(this.materia);
		limpar();
	}

	public void removerMateria() {

		System.out.println("Remover materia: " + idMateriaSelecionada);

		if (idMateriaSelecionada != 0) {

			System.out.println("Materia a deletar: " + materia);
			this.materiaDAO.remover(idMateriaSelecionada);
			limpar();
		}
	}

	public void limpar() {

		materia = new Materia();
		this.estudante = new Estudante();
		atividadeAdicionado = new Atividade();
		this.materias = new ArrayList<Materia>();
		this.estudante.setMaterias(new ArrayList<Materia>());

	}

	public String editarMateria() {

		JpaUtil.getEntityManager();

		System.out.println("Funcionario selecionado: " + idMateriaSelecionada);
		// this.funcionario.setId(idFuncionarioSelecionado);
		// this.funcionario.getId();
		Materia materiaRecuperada = this.materiaDAO.pesquisar(this.idMateriaSelecionada);
		System.out.println("Materia achada " + materiaRecuperada);
		if (materiaRecuperada != null) {

			this.materia = materiaRecuperada;
			this.materia.setEstudante(estudante);

			return "manterMateria.xhtml";
		} else {
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção", "Não foi achado!!!");
			return "";
		}
	}

	public void listarTodos() {
		System.out.println("Estudante:" + emailSelecionado);
		this.materias = this.materiaDAO.listarTodos();

	}

	public void removerAtividade() {

		System.out.println("Atividade selecionada: " + idAtividadeSelecionado);

		if (idAtividadeSelecionado != 0) {

			this.materiaDAO.removerAtividade(idAtividadeSelecionado);
			limpar();
		} else {
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Informação", "Atividade não cadastrada");
			System.out.println("Materia nova: " + this.materia.getId());
		}
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public MateriaDAO getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(MateriaDAO materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public int getIdAtividadeSelecionado() {
		return idAtividadeSelecionado;
	}

	public void setIdAtividadeSelecionado(int idAtividadeSelecionado) {
		this.idAtividadeSelecionado = idAtividadeSelecionado;
	}

	public Atividade getAtividadeAdicionado() {
		return atividadeAdicionado;
	}

	public void setAtividadeAdicionado(Atividade atividadeAdicionado) {
		this.atividadeAdicionado = atividadeAdicionado;
	}

	public int getIdMateriaSelecionada() {
		return idMateriaSelecionada;
	}

	public void setIdMateriaSelecionada(int idMateriaSelecionada) {
		this.idMateriaSelecionada = idMateriaSelecionada;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public String getEmailSelecionado() {
		return emailSelecionado;
	}

	public void setEmailSelecionado(String emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}

	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(List<Estudante> estudantes) {
		this.estudantes = estudantes;
	}

}
