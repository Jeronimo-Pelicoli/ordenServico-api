package com.cursovaldir.valdir.service;

import com.cursovaldir.valdir.domain.Cliente;
import com.cursovaldir.valdir.domain.OS;
import com.cursovaldir.valdir.domain.Tecnico;
import com.cursovaldir.valdir.domain.enums.Prioridade;
import com.cursovaldir.valdir.domain.enums.Status;
import com.cursovaldir.valdir.repository.ClienteRepository;
import com.cursovaldir.valdir.repository.OSRepository;
import com.cursovaldir.valdir.repository.TecnicoRepository;
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
    private OSRepository osRepository;

    public void instanciaDB() {

        Tecnico t1 = new Tecnico(null, "Valdir Cezar", "144.785.300-84", "(88)98888-8888");
        Tecnico t2 = new Tecnico(null, "Paulo Cezar", "493.861.300-09", "(88)98887-8888");
        Cliente c1 = new Cliente(null, "Betina Campos", "598.508.200-80", "(88)98889-8888");
        OS os1 = new OS(null, Prioridade.ALTA, "Teste createOD", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1, t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));

    }
}
