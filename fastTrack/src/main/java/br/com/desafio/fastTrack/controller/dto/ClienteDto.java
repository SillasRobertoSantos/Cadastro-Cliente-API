package br.com.desafio.fastTrack.controller.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.desafio.fastTrack.model.CidadesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
	private Long id;

	@NotEmpty(message = "Nome não pode ser vazio")
	@NotNull(message = "Campo Obrigatório")
	private String nome;

	@NotEmpty(message = "Nome não pode ser vazio")
	@NotNull(message = "Campo Obrigatório")
	private String sexo;

	@DateTimeFormat
	private LocalDate dataNascimento;

	@NotNull(message = "Campo Obrigatório")
	private Integer idade;

	@NotNull(message = "Campo Obrigatório")
	private CidadesEntity cidades;

}
