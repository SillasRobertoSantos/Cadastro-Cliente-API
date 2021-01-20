package br.com.desafio.fastTrack.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.reflect.TypeToken;

import br.com.desafio.fastTrack.controller.dto.ClienteDto;
import br.com.desafio.fastTrack.exceptions.ResourceNotFoundException;
import br.com.desafio.fastTrack.exceptions.ResourceUnprocessableEntityException;
import br.com.desafio.fastTrack.model.ClienteEntity;
import br.com.desafio.fastTrack.repository.ClienteRepository;
import lombok.Data;

@Data
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	@Transactional(readOnly = true)
	public ClienteDto buscarId(Long id) {
		Optional<ClienteEntity> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return converterClienteToClienteDto(cliente.get());
		}
		throw new ResourceNotFoundException("cliente não encontrado para o id " + id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteDto> buscarNome(String nome) {
		List<ClienteEntity> cliente = clienteRepository.findByNomeContaining(nome);
		if (!cliente.isEmpty()) {
			return modelMapper.map(cliente, new TypeToken<List<ClienteDto>>() {
			}.getType());

		}
		throw new ResourceNotFoundException("cliente " + nome + " não encontrado!");
	}

	@Override
	@Transactional
	public ClienteDto cadastrar(ClienteDto clienteDto) {

		Optional<ClienteEntity> response = clienteRepository.finByCliente(clienteDto.getNome(), clienteDto.getSexo(),
				clienteDto.getDataNascimento(), clienteDto.getIdade(), clienteDto.getCidades());

		if (response.isPresent()) {

			throw new ResourceUnprocessableEntityException("Cliente já possui cadastro");

		}

		ClienteEntity converterClienteDtoToCliente = converterClienteDtoToCliente(clienteDto);

		ClienteEntity cliente = clienteRepository.save(converterClienteDtoToCliente);

		return converterClienteToClienteDto(cliente);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);
		if (!clienteEntityOptional.isPresent()) {
			throw new ResourceNotFoundException("cliente não encontrado para o id " + id);
		}

		clienteRepository.deleteById(id);
	}

	@Override
	@Transactional
	public ClienteDto atualizar(String nome, Long id) {

		Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);

		if (clienteOptional.isPresent()) {

			clienteOptional.get().setNome(nome);
			ClienteEntity clienteEntity = clienteRepository.save(clienteOptional.get());

			return converterClienteToClienteDto(clienteEntity);
		}
		throw new ResourceNotFoundException("impossivel atualizar, cliente não encontrado para id " + id);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteDto> listarClientes() {
		List<ClienteEntity> list = clienteRepository.findAll(Sort.by(Direction.ASC, "nome"));
		if (!list.isEmpty()) {
			return modelMapper.map(list, new TypeToken<List<ClienteDto>>() {
			}.getType());
		}

		throw new ResourceNotFoundException(
				"Nenhum cliente encontrado, é necessário " + "no mínimo um cadastro de cliente");
	}

	private ClienteEntity converterClienteDtoToCliente(final ClienteDto clienteDto) {
		return modelMapper.map(clienteDto, ClienteEntity.class);

	}

	private ClienteDto converterClienteToClienteDto(final ClienteEntity clienteEntity) {

		return modelMapper.map(clienteEntity, ClienteDto.class);
	}

}