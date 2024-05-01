package br.com.rafaelsilva91.helpdesk.repositories;

import br.com.rafaelsilva91.helpdesk.domain.Cliente;
import br.com.rafaelsilva91.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
