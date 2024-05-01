package br.com.rafaelsilva91.helpdesk.resources;


import br.com.rafaelsilva91.helpdesk.domain.Chamado;
import br.com.rafaelsilva91.helpdesk.domain.dtos.ChamadoDTO;
import br.com.rafaelsilva91.helpdesk.services.ChamadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
@CrossOrigin(origins = "*")
public class ChamadoResource {

    private ChamadoService service;

    public ChamadoResource(ChamadoService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = this.service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list = service.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO request){
        Chamado obj = service.create(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO request){
        Chamado chamado = service.update(id, request);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Integer id){
//        service.delete(id);
//        return ResponseEntity.noContent().build();
//    }



}