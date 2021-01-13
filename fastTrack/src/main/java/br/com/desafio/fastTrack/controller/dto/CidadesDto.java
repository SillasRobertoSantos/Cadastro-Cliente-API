package br.com.desafio.fastTrack.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CidadesDto {

	private Long id;
	@NotEmpty
	@NotNull(message = "Campo Obrigatório")
	private String nome;
	@NotEmpty(message = "Estado não pode ser Vazio.")
	private String estado;
}
