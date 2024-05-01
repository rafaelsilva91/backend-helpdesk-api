package br.com.rafaelsilva91.helpdesk.resources;

import br.com.rafaelsilva91.helpdesk.domain.Tecnico;
import br.com.rafaelsilva91.helpdesk.domain.dtos.TecnicoDTO;
import br.com.rafaelsilva91.helpdesk.repositories.TecnicoRepository;
import br.com.rafaelsilva91.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
@CrossOrigin(origins = "*")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> tecnicos = this.service.findAll();
        List<TecnicoDTO> list = tecnicos.stream().map(value -> new TecnicoDTO(value)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tecnico = this.service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new TecnicoDTO(tecnico));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
        Tecnico newObj = this.service.create(objDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id,
                                             @Valid @RequestBody TecnicoDTO objDTO){

        Tecnico obj =this.service.update(id, objDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new TecnicoDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        this.service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
