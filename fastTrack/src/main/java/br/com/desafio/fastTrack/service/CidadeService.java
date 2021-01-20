package br.com.desafio.fastTrack.service;

import java.util.List;

import br.com.desafio.fastTrack.controller.dto.CidadesDto;

public interface CidadeService {

	CidadesDto cadastrar(CidadesDto cidadesDto);

	List<CidadesDto> buscarNome(String nome);

	List<CidadesDto> buscarUf(String estado);
	
	List<CidadesDto> listarCidades();
}
