package br.com.rafaelsilva91.helpdesk.repositories;

import br.com.rafaelsilva91.helpdesk.domain.Chamado;
import br.com.rafaelsilva91.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
