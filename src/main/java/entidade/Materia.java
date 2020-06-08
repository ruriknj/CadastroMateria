package entidade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_materia")
	private Integer id;
	private String nome;
	private String email;
	private String senha;
	private String descricao;

	@OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
	private List<Atividade> atividades = new ArrayList<>();

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_estudante")
	private Estudante estudante;

	public Materia() {
	}

	public Materia(Integer id, String nome, String email, String senha, String descricao) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.descricao = descricao;
		//this.estudante = estudante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	@Override
	public String toString() {
		return "Materia [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", descricao="
				+ descricao + ", estudante: " + estudante + "]";
	}

	
}
