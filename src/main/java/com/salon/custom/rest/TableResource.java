package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.staff.StaffResponse;
import com.salon.custom.dto.table.TableRequest;
import com.salon.custom.dto.table.TableResponse;
import com.salon.custom.service.TableService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class TableResource extends BaseResource<TableService> {

    @GetMapping("api/getTable")
    public ResponseEntity<TableResponse> getListTable(@RequestParam(name = "page", defaultValue = "1") int page,
                                                      @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return ResponseEntity.ok().body(service.getListTable(pageable));
    }

    @PostMapping("api/createTable")
    public ResponseEntity<TableResponse> createTable(@RequestBody TableRequest request) {
        return ResponseEntity.ok().body(service.createTable(request));
    }

    @PutMapping("api/updateTable")
    public ResponseEntity<TableResponse> updateTable(@RequestBody TableRequest request) {
        return ResponseEntity.ok().body(service.updateTable(request));
    }

    @DeleteMapping("api/deleteTable")
    public ResponseEntity<TableResponse> deleteTable(@RequestParam(name = "id") Long id) {
        return ResponseEntity.ok().body(service.deleteTable(id));
    }

}
