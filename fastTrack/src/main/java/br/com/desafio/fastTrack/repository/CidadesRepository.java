package br.com.desafio.fastTrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desafio.fastTrack.model.CidadesEntity;

@Repository
public interface CidadesRepository extends JpaRepository<CidadesEntity, Long> {

	List<CidadesEntity> findCidadesEntityByNome(String nome);

	List<CidadesEntity> findCidadesEntityByEstado(String estado);

	@Query("SELECT c FROM CidadesEntity c WHERE c.nome =:nome " + "AND c.estado = :estado")
	Optional<List<CidadesEntity>> findCidadesEntityByNomeAndByEstado(String nome, String estado);

}
