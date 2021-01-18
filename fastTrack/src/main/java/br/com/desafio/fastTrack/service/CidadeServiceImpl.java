package br.com.desafio.fastTrack.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.reflect.TypeToken;

import br.com.desafio.fastTrack.controller.dto.CidadesDto;
import br.com.desafio.fastTrack.controller.dto.ClienteDto;
import br.com.desafio.fastTrack.exceptions.ResourceNotFoundException;
import br.com.desafio.fastTrack.exceptions.ResourceUnprocessableEntityException;
import br.com.desafio.fastTrack.model.CidadesEntity;
import br.com.desafio.fastTrack.model.ClienteEntity;
import br.com.desafio.fastTrack.repository.CidadesRepository;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CidadesRepository cidadesRepository;

	@Override
	@Transactional
	public CidadesDto cadastrar(CidadesDto cidadesDto) {

		Optional<List<CidadesEntity>> response = cidadesRepository
				.findCidadesEntityByNomeAndEstado(cidadesDto.getNome(), cidadesDto.getEstado());

		if (response.isPresent()) {

			throw new ResourceUnprocessableEntityException("Cidade já possui cadastro");

		}

		CidadesEntity converterCidadeDtoToCidade = converterCidadeDtoToCidade(cidadesDto);

		CidadesEntity cidadesEntity = cidadesRepository.save(converterCidadeDtoToCidade);

		return converterCidadesToCidadeDto(cidadesEntity);

	}

	@Override
	@Transactional(readOnly = true)
	public List<CidadesDto> listar() {
		List<CidadesEntity> list = cidadesRepository.findAll();
		if (!list.isEmpty()) {
			return modelMapper.map(list, new TypeToken<List<CidadesDto>>() {
			}.getType());
		}

		throw new ResourceNotFoundException(
				"Nenhuma cidade encontrada, é necessário " + "realizar no mínimo um cadastro de cidade");

	}

	@Override
	@Transactional(readOnly = true)
	public List<CidadesDto> buscarNome(String nome) {
		List<CidadesEntity> cidade = cidadesRepository.findCidadesEntityByNomeContaining(nome);

		if (!cidade.isEmpty()) {

			return modelMapper.map(cidade, new TypeToken<List<CidadesDto>>() {
			}.getType());

		}
		throw new ResourceNotFoundException("cidade " + nome + " não encontrada!");

	}

	@Override
	@Transactional(readOnly = true)
	public List<CidadesDto> buscarUf(String estado) {
		List<CidadesEntity> ufCidade = cidadesRepository.findCidadesEntityByEstadoContaining(estado);
		if (!ufCidade.isEmpty()) {
			return modelMapper.map(ufCidade, new TypeToken<List<CidadesDto>>() {
			}.getType());
		}
		throw new ResourceNotFoundException("estado " + estado + " não encontrada");
	}

	private CidadesEntity converterCidadeDtoToCidade(final CidadesDto cidadesDto) {

		return modelMapper.map(cidadesDto, CidadesEntity.class);

	}

	private CidadesDto converterCidadesToCidadeDto(final CidadesEntity cidadesEntity) {

		return modelMapper.map(cidadesEntity, CidadesDto.class);

	}

}