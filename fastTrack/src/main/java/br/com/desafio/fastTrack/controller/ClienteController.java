package br.com.desafio.fastTrack.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.fastTrack.controller.dto.ClienteDto;
import br.com.desafio.fastTrack.repository.ClienteRepository;
import br.com.desafio.fastTrack.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ClienteService clienteService;

	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteDto clienteDto) {

		return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.cadastrar(clienteDto));

	}

	@GetMapping("/buscar-id/{id}")
	public ResponseEntity<ClienteDto> buscarComId(@PathVariable Long id) {

		return ResponseEntity.ok(this.clienteService.buscarId(id));
	}

	@GetMapping("/buscar-nome/{nome}")
	public ResponseEntity<ClienteDto> buscarComNome(@PathVariable String nome) {

		return ResponseEntity.ok(this.clienteService.buscarNome(nome));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {

		clienteService.remover(id);
		return ResponseEntity.noContent().build();

	}

	@PatchMapping("/atualizar-nome/{id}")
	public ResponseEntity<ClienteDto> atualizaNome(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(this.clienteService.atualizar(clienteDto, id));

	}
}