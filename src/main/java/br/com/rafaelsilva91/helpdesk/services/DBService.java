package br.com.rafaelsilva91.helpdesk.services;

import br.com.rafaelsilva91.helpdesk.domain.Chamado;
import br.com.rafaelsilva91.helpdesk.domain.Cliente;
import br.com.rafaelsilva91.helpdesk.domain.Tecnico;
import br.com.rafaelsilva91.helpdesk.domain.enums.PerfilEnum;
import br.com.rafaelsilva91.helpdesk.domain.enums.PrioridadeEnum;
import br.com.rafaelsilva91.helpdesk.domain.enums.StatusEnum;
import br.com.rafaelsilva91.helpdesk.repositories.ChamadoRepository;
import br.com.rafaelsilva91.helpdesk.repositories.ClienteRepository;
import br.com.rafaelsilva91.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;
    public void instanciaDB(){
        Tecnico t1 = new Tecnico(null, "Joao Cesar", "00000000011", "joao@email.com", "123");
        t1.addPerfil(PerfilEnum.ADMIN);
        Tecnico t2 = new Tecnico(null, "Richard Stallman", "00000000022", "richard@email.com", "123");
        Tecnico t3 = new Tecnico(null, "Claude Elwood Shannon", "00000000033", "claude@email.com", "123");
        Tecnico t4 = new Tecnico(null, "Tim Bernner-Lee", "00000000044", "timbernners@email.com", "123");
        Tecnico t5 = new Tecnico(null, "Linus Torvalds", "00000000055", "linus@email.com", "123");

        Cliente cli1 = new Cliente(null, "Albert Einstein", "00000000066", "albert@email.com", "123");
        Cliente cli2 = new Cliente(null, "Marie Curie", "00000000077", "marie@email.com", "123");
        Cliente cli3 = new Cliente(null, "Charles Daewin", "00000000088", "charles@email.com", "123");
        Cliente cli4 = new Cliente(null, "Stephen Hawking", "00000000099", "stephen@email.com", "123");
        Cliente cli5 = new Cliente(null, "Max Planck", "00000000000", "max@email.com", "123");

        Chamado c1 = new Chamado(null, PrioridadeEnum.MEDIA, StatusEnum.ANDAMENTO, "Chamado 01", "Primeiro chamado", t1, cli1);
        Chamado c2 = new Chamado(null, PrioridadeEnum.ALTA, StatusEnum.ABERTO, "Chamado 02", "Segundo chamado", t1, cli2);
        Chamado c3 = new Chamado(null, PrioridadeEnum.BAIXA, StatusEnum.ENCERRADO, "Chamado 03", "Terceiro chamado", t2, cli3);
        Chamado c4 = new Chamado(null, PrioridadeEnum.ALTA, StatusEnum.ABERTO, "Chamado 04", "Quarto chamado", t3, cli3);
        Chamado c5 = new Chamado(null, PrioridadeEnum.MEDIA, StatusEnum.ANDAMENTO, "Chamado 05", "Quinto chamado", t2, cli1);
        Chamado c6 = new Chamado(null, PrioridadeEnum.BAIXA, StatusEnum.ENCERRADO, "Chamado 06", "Sexto chamado", t1, cli5);

        this.tecnicoRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));
        this.clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
        this.chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
    }

}
