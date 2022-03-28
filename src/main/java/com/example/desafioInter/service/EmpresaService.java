package com.example.desafioInter.service;

import com.example.desafioInter.entity.Empresa;
import com.example.desafioInter.repository.EmpresaRepository;
import com.example.desafioInter.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa inserirNovaEmpresa(Empresa empresa) {
        empresa.setIdEmpresa(null);
        return empresaRepository.save(empresa);
    }

    public Empresa alterarPrecoAcao(Long id, float precoAcao) {
        Empresa alteraPrecoAcao = buscarAcaoPorId(id);

        alteraPrecoAcao.setPreco(precoAcao);

        return empresaRepository.save(alteraPrecoAcao);
    }

    public Empresa alterarStatusEmpresa(Long id, boolean status) {
        Empresa alteraPrecoAcao = buscarAcaoPorId(id);

        alteraPrecoAcao.setStatus(status);

        return empresaRepository.save(alteraPrecoAcao);
    }

    public Empresa buscarAcaoPorId(Long id){
        Optional<Empresa> obj = empresaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Ação não foi encontrada!"));
    }

    public Empresa buscarAcaoPorNome(String nomeAcao){
        Optional<Empresa> obj = empresaRepository.findByNomeAcao(nomeAcao);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Ação " +nomeAcao+ "não encontrada!"));
    }

    public List<Empresa> buscarAcoesPorStatus(boolean status){
        return empresaRepository.findAllByStatus(status);
    }

    public List<Empresa> buscarTodasAsAcoes() {
        return empresaRepository.findAll();
    }

    public void excluirEmpresaPorNomeAcao(String nomeAcao) {
        buscarAcaoPorNome(nomeAcao);
        empresaRepository.deleteByNomeAcao(nomeAcao);
    }


}
