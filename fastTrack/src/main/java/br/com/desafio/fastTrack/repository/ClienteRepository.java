package br.com.desafio.fastTrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.fastTrack.model.ClienteEntity;
@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	ClienteEntity findByNome(String nome);

}
