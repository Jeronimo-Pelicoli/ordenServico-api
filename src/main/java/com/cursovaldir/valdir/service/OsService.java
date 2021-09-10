package com.cursovaldir.valdir.service;

import com.cursovaldir.valdir.domain.Cliente;
import com.cursovaldir.valdir.domain.OS;
import com.cursovaldir.valdir.domain.Tecnico;
import com.cursovaldir.valdir.domain.enums.Prioridade;
import com.cursovaldir.valdir.domain.enums.Status;
import com.cursovaldir.valdir.dto.OSDTO;
import com.cursovaldir.valdir.repository.OSRepository;
import com.cursovaldir.valdir.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OSRepository osRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    public OS findById(Integer id) {
        Optional<OS> obj= osRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Ordem de Serviço não encontrado! Id: " + id + ", tipo: " + OS.class.getName()
        ));
    }

    public List<OS> findAll() { return osRepository.findAll(); }

    public OS create(@Valid OSDTO obj) {
        return fromDTO(obj);
    }

    public OS update(@Valid OSDTO obj) {
        findById(obj.getId());
        return fromDTO(obj);
    }

    public OS fromDTO(OSDTO obj) {
        OS newObj = new OS();
        newObj.setId(obj.getId());
        newObj.setObservation(obj.getObservation());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));

        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cli = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cli);

        if(newObj.getStatus().getCod().equals(2)) {
            newObj.setDataFechamento(LocalDateTime.now());
        }
        return osRepository.save(newObj);
    }
}
