package br.com.desafio.fastTrack.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public ClienteDto buscarId(Long id) {
		Optional<ClienteEntity> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return converterClienteToClienteDto(cliente.get());
		}
		throw new ResourceNotFoundException("cliente não encontrado para o id " + id);
	}

	@Override
	public ClienteDto buscarNome(String nome) {
		Optional<ClienteEntity> cliente = clienteRepository.findByNome(nome);
		if (cliente != null) {
			return converterClienteToClienteDto(cliente.get());
		}
		throw new ResourceNotFoundException("cliente " + nome + " não encontrado!");
	}

	@Override
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
	public void remover(Long id) {
		Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);
		if (!clienteEntityOptional.isPresent()) {
			throw new ResourceNotFoundException("cliente não encontrado para o id " + id);
		}

		clienteRepository.deleteById(id);
	}

	@Override
	public ClienteDto atualizar(ClienteDto clienteDto, Long id) {

		Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);

		if (clienteOptional.isPresent()) {

			clienteOptional.get().setNome(clienteDto.getNome());
			ClienteEntity clienteEntity = clienteRepository.save(clienteOptional.get());

			return converterClienteToClienteDto(clienteEntity);
		}
		throw new ResourceNotFoundException("impossivel atualizar, cliente não encontrado par id" + id);

	}

	private ClienteEntity converterClienteDtoToCliente(final ClienteDto clienteDto) {
		return modelMapper.map(clienteDto, ClienteEntity.class);

	}

	private ClienteDto converterClienteToClienteDto(final ClienteEntity cliente) {

		return modelMapper.map(cliente, ClienteDto.class);
	}

}