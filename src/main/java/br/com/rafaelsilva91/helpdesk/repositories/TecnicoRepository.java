package br.com.rafaelsilva91.helpdesk.repositories;

import br.com.rafaelsilva91.helpdesk.domain.Cliente;
import br.com.rafaelsilva91.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
