package com.salon.custom.service;

import com.salon.base.core.BaseService;
import com.salon.custom.dto.table.TableDTO;
import com.salon.custom.dto.table.TableRequest;
import com.salon.custom.dto.table.TableResponse;
import com.salon.custom.entities.TableEntity;
import com.salon.custom.repository.TableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TableService extends BaseService<TableEntity, TableRepository> {

    public TableResponse getListTable(Pageable pageable){
        Page<TableEntity> tableEntities = repository.findByDeletedFalse(pageable);
        List<TableDTO> tableDTOS = new ArrayList<>();
        tableEntities.forEach(tableEntity -> tableDTOS.add(toDTO(tableEntity)));
        return new TableResponse(tableDTOS, populatePageDto(tableEntities));
    }

    public TableResponse createTable(TableRequest request){
        TableEntity tableEntity = new TableEntity();
        toEntity(request, tableEntity);
        save(tableEntity);
        return new TableResponse(toDTO(tableEntity));
    }

    public TableResponse updateTable(TableRequest request){
        TableEntity tableEntity = repository.findByIdAndDeletedFalse(request.getId());
        if (tableEntity == null){
            return new TableResponse("This table not found", 4004);
        }
        TableEntity tableExist = repository.findByNameAndDeletedFalse(request.getName());
        if (tableExist != null){
            return new TableResponse("This table exist", 4004);
        }
        toEntity(request, tableEntity);
        update(tableEntity);
        return new TableResponse(toDTO(tableEntity));
    }

    public TableResponse deleteTable(Long id){
        TableEntity tableEntity = repository.findByIdAndDeletedFalse(id);
        if (tableEntity == null){
            return new TableResponse("This table not found", 4004);
        }
        tableEntity.setDeleted(true);
        update(tableEntity);
        return new TableResponse(toDTO(tableEntity));
    }

    private TableDTO toDTO(TableEntity tableEntity){
        TableDTO tableDTO = new TableDTO();
        tableDTO.setId(tableEntity.getId());
        tableDTO.setName(tableEntity.getName());
        tableDTO.setSeat(tableEntity.getSeat());
        tableDTO.setAvailable(tableEntity.isAvailable());
        return tableDTO;
    }

    private void toEntity(TableRequest request, TableEntity table){
        table.setName(request.getName());
        table.setSeat(request.getSeat());
        table.setAvailable(request.isAvailable());
    }

    public List<TableEntity> findListTable(){
        return repository.findByDeletedFalse();
    }

    public List<TableEntity> getByIds(Set<Long> ids) {
        return repository.findByIdInAndDeletedFalse(ids);
    }

    public void saveAll(List<TableEntity> tables){
        repository.saveAll(tables);
    }

    public List<TableEntity> getTablesAvailable(){
        return repository.findTablesAvailable();
    }

    public Set<Long> getTableIdsOfBooking(Long bookingId){
        return repository.findTableIdsOfBooking(bookingId);
    }

    public TableResponse getListTableAvailable(){
        List<TableEntity> tableEntities = repository.findTablesAvailable();
        List<TableDTO> tableDTOS = new ArrayList<>();
        tableEntities.forEach(tableEntity -> tableDTOS.add(toDTO(tableEntity)));
        return new TableResponse(tableDTOS);
    }

}
