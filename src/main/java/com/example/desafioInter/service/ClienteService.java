package com.example.desafioInter.service;

import com.example.desafioInter.entity.Cliente;
import com.example.desafioInter.repository.ClienteRepository;
import com.example.desafioInter.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente inserirNovoCliente(Cliente cliente) {
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);
    }

    public Cliente alterarCliente(Long id, Cliente cliente) {
        Cliente alteraCliente = buscarClientePorId(id);

        alteraCliente.setNome(cliente.getNome());
        alteraCliente.setEmail(cliente.getEmail());
        alteraCliente.setCpf(cliente.getCpf());

        return clienteRepository.save(alteraCliente);
    }

    public Cliente buscarClientePorId(Long id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " +id+ ", Tipo: " +Cliente.class.getName()));
    }

    public Cliente buscarClientePorNome(String nome){
        Optional<Cliente> obj = clienteRepository.findByNome(nome);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Nome: " +nome+ ", Tipo: " +Cliente.class.getName()));
    }

    public Cliente buscarClientePorCpf(String cpf){
        Optional<Cliente> obj = clienteRepository.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! CPF: " +cpf+ ", Tipo: " +Cliente.class.getName()));
    }

    public List<Cliente> buscarTodosOsClientes() {
        return clienteRepository.findAll();
    }

    public void excluirClientePorId(Long id) {
        buscarClientePorId(id);
        clienteRepository.deleteById(id);
    }
}
