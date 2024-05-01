package br.com.rafaelsilva91.helpdesk.services;

import br.com.rafaelsilva91.helpdesk.domain.Pessoa;
import br.com.rafaelsilva91.helpdesk.domain.Tecnico;
import br.com.rafaelsilva91.helpdesk.domain.dtos.TecnicoDTO;
import br.com.rafaelsilva91.helpdesk.repositories.PessoaRepository;
import br.com.rafaelsilva91.helpdesk.repositories.TecnicoRepository;
import br.com.rafaelsilva91.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.rafaelsilva91.helpdesk.services.exceptions.ObjectNotFountExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = this.repository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFountExceptions("objeto não Encontrado! ID:" + id));
    }

    public List<Tecnico> findAll() {
        return this.repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return this.repository.save(newObj);

    }

    public Tecnico update(Integer id, TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = this.findById(id);
        validaPorCpfEEmail(objDTO);

        oldObj = new Tecnico(objDTO);
        return this.repository.save(oldObj);

    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = this.pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no Sistema");
        }

        obj = this.pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no Sistema");
        }
    }

    public void deleteById(Integer id) {
        Tecnico tecnico = this.findById(id);
        if (tecnico.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("O Técnico possui ordens de Servico e não pode ser deletado!");
        }
        this.repository.deleteById(id);
    }
}
