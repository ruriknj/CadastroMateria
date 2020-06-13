package entidade;

import java.util.ArrayList;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	private String descricao;

	@OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Atividade> atividades = new ArrayList<>();

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_estudante")
	private Estudante estudante;

	public Materia() {
	}

	public Materia(Integer id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
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
		return "Materia: [id=" + id + ", nome=" + nome +  ", descricao="
				+ descricao +  ", " + atividades + ", " + estudante ;
	}

	
}
