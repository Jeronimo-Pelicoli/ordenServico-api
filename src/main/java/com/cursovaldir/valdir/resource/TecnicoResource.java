package com.cursovaldir.valdir.resource;

import com.cursovaldir.valdir.domain.Tecnico;
import com.cursovaldir.valdir.dto.TecnicoDTO;
import com.cursovaldir.valdir.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        TecnicoDTO objDTO = new TecnicoDTO(tecnicoService.findById(id));
        return ResponseEntity.ok().body(objDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {

        List<TecnicoDTO> listDTO = tecnicoService.findAll()
                .stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
//        List<Tecnico> list = tecnicoService.findAll();
//        List<TecnicoDTO> listDTO = new ArrayList<>();
//        for(Tecnico obj : list) {
//            listDTO.add(new TecnicoDTO(obj));
//        }
//        list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO) {
        Tecnico newObj = tecnicoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO) {
        TecnicoDTO newObj = new TecnicoDTO(tecnicoService.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
