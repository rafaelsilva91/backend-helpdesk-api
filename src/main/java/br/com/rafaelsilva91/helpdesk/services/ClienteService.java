package br.com.rafaelsilva91.helpdesk.services;

import br.com.rafaelsilva91.helpdesk.domain.Cliente;
import br.com.rafaelsilva91.helpdesk.domain.Pessoa;
import br.com.rafaelsilva91.helpdesk.domain.dtos.ClienteDTO;
import br.com.rafaelsilva91.helpdesk.repositories.ClienteRepository;
import br.com.rafaelsilva91.helpdesk.repositories.PessoaRepository;
import br.com.rafaelsilva91.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.rafaelsilva91.helpdesk.services.exceptions.ObjectNotFountExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = this.repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFountExceptions("objeto não Encontrado! ID:" + id));
    }

    public List<Cliente> findAll() {
        return this.repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return this.repository.save(newObj);

    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = this.findById(id);
        validaPorCpfEEmail(objDTO);

        oldObj = new Cliente(objDTO);
        return this.repository.save(oldObj);

    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
        Cliente cliente = this.findById(id);
        if (cliente.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("O Cliente possui ordens de Servico e não pode ser deletado!");
        }
        this.repository.deleteById(id);
    }
}
