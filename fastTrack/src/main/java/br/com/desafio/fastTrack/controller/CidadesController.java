package br.com.desafio.fastTrack.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.fastTrack.controller.dto.CidadesDto;
import br.com.desafio.fastTrack.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadesController {

	@Autowired
	private CidadeService cidadeService;

	@PostMapping("/cadastrar")
	public ResponseEntity<CidadesDto> cadastrar(@RequestBody @Valid CidadesDto cidadesDto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(this.cidadeService.cadastrar(cidadesDto));

	}

	@GetMapping
	public ResponseEntity<List<CidadesDto>> listar() {
		return ResponseEntity.ok(this.cidadeService.listar());
	}

	@GetMapping("/buscarNome/{nome}")
	public ResponseEntity<List<CidadesDto>> buscarNome(@PathVariable String nome) {

		return ResponseEntity.ok(this.cidadeService.buscarNome(nome));
	}

	@GetMapping("/buscarEstado/{estado}")
	public ResponseEntity<List<CidadesDto>> buscarUF(@PathVariable String estado) {

		return ResponseEntity.ok(this.cidadeService.buscarUf(estado));

	}

}