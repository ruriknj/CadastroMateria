package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import dao.MateriaDAO;
import dao.MateriaDAOImp;
import entidade.Estudante;
import entidade.Materia;
import util.JpaUtil;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {

	private String txtEmail;
	private String txtSenha;

	private Estudante estudante;
	private List<Estudante> listaEstudantes;
	private MateriaDAO materiaDAO;
	private Materia materia;

	public LoginBean() {
		this.estudante = new Estudante();
		this.listaEstudantes = new ArrayList<Estudante>();
		this.materiaDAO = new MateriaDAOImp();
		this.materia = new Materia();

	}

	public String entrar() throws Exception {
		System.out.println("---------------------");

		for (Estudante u : listaEstudantes) {
			System.out.println(u.getEmail() + " - " + u.getSenha());
		}
		System.out.println("---------------------");

		boolean achou = false;

		this.listaEstudantes = this.materiaDAO.listarEstudante();

		System.out.println("Estudante: " + this.listaEstudantes);

		for (Estudante estudanteLista : this.listaEstudantes) {
			if (estudanteLista.getEmail().equals(this.txtEmail) && estudanteLista.getSenha().equals(this.txtSenha)) {
				@SuppressWarnings("unused")
				Estudante estudante = estudanteLista;
				achou = true;

			}
		}

		if (achou) {

			FacesContext.getCurrentInstance().getExternalContext().redirect("manterMateria.xhtml");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Estudante invalido!!!"));
			return "";
		}
		return "";
	}

	public void criarEstudante() throws Exception {

		FacesContext.getCurrentInstance().getExternalContext().redirect("manterEstudante.xhtml");

	}

	public String listarMateria(String txtEmail) throws IOException {

		JpaUtil.getEntityManager();

		System.out.println("Funcionario selecionado: " + this.txtEmail);
		// this.funcionario.setId(idFuncionarioSelecionado);
		// this.funcionario.getId();
		String materiaRecuperada = this.materiaDAO.pesquisarEstudante(txtEmail);
		System.out.println("Materia achada " + materiaRecuperada);
		if (materiaRecuperada != null) {
			estudante.setEmail(materiaRecuperada);

			estudante.setEmail(materiaRecuperada);
			this.materia.setEstudante(estudante);
			FacesContext.getCurrentInstance().getExternalContext().redirect("manterMateria.xhtml");

		} else {
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atenção", "Não foi achado!!!");

		}
		return materiaRecuperada;

	}

	public String getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	public String getTxtSenha() {
		return txtSenha;
	}

	public void setTxtSenha(String txtSenha) {
		this.txtSenha = txtSenha;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estdante) {
		this.estudante = estdante;
	}

	public List<Estudante> getListaEstudantes() {
		return listaEstudantes;
	}

	public void setListaEstudantes(List<Estudante> listaEstudantes) {
		this.listaEstudantes = listaEstudantes;
	}

	public MateriaDAO getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(MateriaDAO materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

}
