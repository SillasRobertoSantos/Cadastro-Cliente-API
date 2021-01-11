package br.com.desafio.fastTrack.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;

import br.com.desafio.fastTrack.controller.dto.CidadesDto;
import br.com.desafio.fastTrack.exceptions.ResourceNotFoundException;
import br.com.desafio.fastTrack.model.CidadesEntity;
import br.com.desafio.fastTrack.repository.CidadesRepository;

@Service
public class CidadeServiceImpl implements CidadeService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CidadesRepository cidadesRepository;

	@Override
	public CidadesDto cadastrar(CidadesDto cidadesDto) {

		CidadesEntity response = converterCidadeDtoToCidade(cidadesDto);

		CidadesEntity cidadesEntity = cidadesRepository.save(response);

		return converterCidadesToCidadeDto(cidadesEntity);
	}

	@Override
	public List<CidadesDto> buscarNome(String nome) {
		List<CidadesEntity> cidade = cidadesRepository.findCidadesEntityByNome(nome);

		if (!cidade.isEmpty()) {

			return modelMapper.map(cidade, new TypeToken<List<CidadesDto>>() {
			}.getType());

		}
		throw new ResourceNotFoundException("cidade " + nome + " não encontrada!");

	}

	@Override
	public List<CidadesDto> buscarUf(String estado) {
		List<CidadesEntity> ufCidade = cidadesRepository.findCidadesEntityByEstado(estado);
		if (!ufCidade.isEmpty()) {
			return modelMapper.map(ufCidade, new TypeToken<List<CidadesDto>>() {
			}.getType());
		}
		throw new ResourceNotFoundException("estado " + estado + "não encontrada");
	}

	private CidadesEntity converterCidadeDtoToCidade(final CidadesDto cidadesDto) {

		return modelMapper.map(cidadesDto, CidadesEntity.class);

	}

	private CidadesDto converterCidadesToCidadeDto(final CidadesEntity cidadeEntity) {

		return modelMapper.map(cidadeEntity, CidadesDto.class);

	}
}