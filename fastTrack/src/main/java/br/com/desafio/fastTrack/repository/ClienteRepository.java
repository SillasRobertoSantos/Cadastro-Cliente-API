package br.com.desafio.fastTrack.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desafio.fastTrack.model.CidadesEntity;
import br.com.desafio.fastTrack.model.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	List<ClienteEntity> findByNomeContaining(String nome);

	@Query("SELECT c FROM ClienteEntity c WHERE c.nome =:nome "
			+ "AND c.sexo = :sexo AND c.dataNascimento =:dataNascimento AND c.idade =:idade "
			+ "AND c.cidades =:cidades")
	Optional<ClienteEntity> finByCliente(String nome, String sexo, LocalDate dataNascimento, Integer idade,
			CidadesEntity cidades);

}
