package br.com.desafio.fastTrack.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class ClienteEntity {

	public ClienteEntity(String nome, String sexo, LocalDate dataNascimento, Integer idade, CidadesEntity cidades) {

		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.idade = idade;
		this.cidades = cidades;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sexo;
	private LocalDate dataNascimento;
	private Integer idade;
	@ManyToOne 
	@JoinColumn (name ="cidades_id", insertable = true)
	private CidadesEntity cidades;

}
