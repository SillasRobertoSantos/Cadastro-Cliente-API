package br.com.desafio.fastTrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.fastTrack.model.CidadesEntity;
@Repository
public interface CidadesRepository extends JpaRepository<CidadesEntity, Long> {

	List<CidadesEntity> findCidadesEntityByNome(String nome);

	List<CidadesEntity> findCidadesEntityByEstado(String estado);

}
