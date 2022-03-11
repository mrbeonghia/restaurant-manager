package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.table.TableDTO;
import com.salon.custom.dto.table.TableResponse;
import com.salon.custom.entities.TableEntity;
import com.salon.custom.repository.TableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService extends BaseService<TableEntity, TableRepository> {

    public TableResponse getListTable(Pageable pageable){
        Page<TableEntity> tableEntities = repository.findByDeletedFalse(pageable);
        List<TableDTO> tableDTOS = new ArrayList<>();
        tableEntities.forEach(tableEntity -> tableDTOS.add(toDTO(tableEntity)));
        return new TableResponse(tableDTOS, populatePageDto(tableEntities));
    }

    public TableDTO toDTO(TableEntity tableEntity){
        TableDTO tableDTO = new TableDTO();
        tableDTO.setId(tableEntity.getId());
        tableDTO.setName(tableEntity.getName());
        tableDTO.setSeat(tableEntity.getSeat());
        tableDTO.setAvailable(tableEntity.isAvailable());
        return tableDTO;
    }

    public List<TableEntity> findListTable(){
        return repository.findByDeletedFalse();
    }

}
