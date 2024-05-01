package br.com.rafaelsilva91.helpdesk.services;


import br.com.rafaelsilva91.helpdesk.domain.Chamado;
import br.com.rafaelsilva91.helpdesk.domain.Cliente;
import br.com.rafaelsilva91.helpdesk.domain.Tecnico;
import br.com.rafaelsilva91.helpdesk.domain.dtos.ChamadoDTO;
import br.com.rafaelsilva91.helpdesk.repositories.ChamadoRepository;
import br.com.rafaelsilva91.helpdesk.services.exceptions.ObjectNotFountExceptions;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService implements Serializable {

    public static final long seriaVersionUID = 1L;

    private ChamadoRepository repository;

    private TecnicoService tecnicoService;
    private ClienteService clienteService;

    public ChamadoService(ChamadoRepository repository
            ,TecnicoService tecnicoService
            , ClienteService clienteService) {
        this.repository = repository;
        this.tecnicoService = tecnicoService;
        this.clienteService = clienteService;
    }

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFountExceptions("Chamado não encontrado! Id: "+id));
    }

    public List<Chamado> findAll(){
        List<Chamado> list = repository.findAll();
        return list;
    }

    public Chamado create(ChamadoDTO objDTO) {
        objDTO.setId(null);

        Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
        Cliente cliente = clienteService.findById(objDTO.getCliente());

        Chamado chamado = new Chamado(objDTO);
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);

        return repository.save(chamado);
    }

    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId(id);
        Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
        Cliente cliente = clienteService.findById(objDTO.getCliente());
        Chamado oldChamado = findById(id);
        oldChamado = new Chamado(objDTO);
        oldChamado.setCliente(cliente);
        oldChamado.setTecnico(tecnico);
        return repository.save(oldChamado);
    }

}