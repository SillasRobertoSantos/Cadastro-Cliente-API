package br.com.desafio.fastTrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desafio.fastTrack.model.CidadesEntity;

@Repository
public interface CidadesRepository extends JpaRepository<CidadesEntity, Long> {

	List<CidadesEntity> findCidadesEntityByNomeContaining(String nome);

	List<CidadesEntity> findCidadesEntityByEstadoContaining(String estado);

	Optional<List<CidadesEntity>> findCidadesEntityByNomeAndEstado(String nome, String estado);

}
