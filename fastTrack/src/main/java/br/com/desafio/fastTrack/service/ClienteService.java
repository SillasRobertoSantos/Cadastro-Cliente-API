package br.com.desafio.fastTrack.service;

import java.util.List;

import br.com.desafio.fastTrack.controller.dto.ClienteDto;

public interface ClienteService {

	ClienteDto buscarId(Long id);

	List<ClienteDto> buscarNome(String nome);

	ClienteDto cadastrar(ClienteDto clienteDto);

	void remover(Long id);

	ClienteDto atualizar(ClienteDto clienteDto, Long id);
	
	List<ClienteDto> listar();
}
