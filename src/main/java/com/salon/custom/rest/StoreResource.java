package com.salon.custom.rest;

import com.salon.base.core.BaseResource;
import com.salon.custom.dto.store.ChangePositionRequest;
import com.salon.custom.dto.store.StoreRequest;
import com.salon.custom.dto.store.StoreResponse;
import com.salon.custom.entities.UserAdmin;
import com.salon.custom.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StoreResource extends BaseResource<StoreService> {

    @Autowired
    private StoreService storeService;

    @GetMapping("v1/app/getAllStoreForApp")
    public ResponseEntity<StoreResponse> getAllStoreForApp(@RequestParam(name = "page", defaultValue = "1") int page,
                                                           @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        StoreResponse storeResponse = storeService.getAllStoreForApp(pageable);
        return ResponseEntity.ok().body(storeResponse);
    }

    @GetMapping("api/v1/app/getAllStoreBookForApp")
    public ResponseEntity<StoreResponse> getAllStoreBookForApp() {
        StoreResponse storeResponse = storeService.getAllStoreBookForApp();
        return ResponseEntity.ok().body(storeResponse);
    }

    @PutMapping("api/v1/app/actionBookingStore")
    public ResponseEntity<StoreResponse> actionBookingStore(@RequestParam(name = "storeId") Long storeId,
                                                            @RequestParam(name = "action") String action) {
        UserAdmin userAdmin = getCurrentUser().getUserAdmin();
        StoreResponse storeResponse = storeService.actionBookingStore(userAdmin, storeId, action);
        return ResponseEntity.ok().body(storeResponse);
    }

    @GetMapping("api/v1/admin/getAllStoreForAdmin")
    public ResponseEntity<StoreResponse> getAllStoreForAdmin(@RequestParam(name = "page", defaultValue = "1") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        StoreResponse storeResponse = storeService.getAllStoreForAdmin(pageable);
        return ResponseEntity.ok().body(storeResponse);
    }

    @GetMapping("api/v1/admin/getAllStoreFilterForAdmin")
    public ResponseEntity<StoreResponse> getAllStoreFilterForAdmin(@RequestParam(name = "page", defaultValue = "1") int page,
                                                                   @RequestParam(name = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page - 1, size);
        StoreResponse storeResponse = storeService.getAllStoreFilterForAdmin(pageable);
        return ResponseEntity.ok().body(storeResponse);
    }

    @PutMapping("api/v1/admin/changePosition")
    public ResponseEntity<StoreResponse> changePosition(@RequestBody ChangePositionRequest changePositionRequest) {
        StoreResponse storeResponse = storeService.changePosition(changePositionRequest);
        return ResponseEntity.ok().body(storeResponse);
    }


    @PostMapping("api/v1/admin/store")
    public ResponseEntity<StoreResponse> createStore(@RequestBody StoreRequest storeRequest) {
        StoreResponse storeResponse = storeService.createStore(storeRequest);
        return ResponseEntity.ok().body(storeResponse);
    }

    @PutMapping("api/v1/admin/store")
    public ResponseEntity<StoreResponse> updateStore(@RequestBody StoreRequest storeRequest) {
        StoreResponse storeResponse = storeService.updateStore(storeRequest);
        return ResponseEntity.ok().body(storeResponse);
    }

    @DeleteMapping("api/v1/admin/store/{id}")
    public ResponseEntity<StoreResponse> deleteStore(@PathVariable Long id) {
        StoreResponse storeResponse = storeService.deleteStore(id);
        return ResponseEntity.ok().body(storeResponse);
    }

    @GetMapping("api/v2/app/store/{id}")
    public ResponseEntity<StoreResponse> findStoreById(@PathVariable Long id) {
        StoreResponse storeResponse = storeService.findStoreId(id);
        return ResponseEntity.ok().body(storeResponse);
    }

    @GetMapping("api/v2/admin/findStoreByOwner")
    public ResponseEntity<StoreResponse> findStoreByOwner() {
        StoreResponse storeResponse = storeService.findStoreByOwner();
        return ResponseEntity.ok().body(storeResponse);
    }

    @PutMapping("api/v2/admin/updateQrCodeStore")
    public ResponseEntity<StoreResponse> updateQrCodeStore() {
        StoreResponse storeResponse = storeService.updateQrCodeStore();
        return ResponseEntity.ok().body(storeResponse);
    }

    @PutMapping("api/v2/admin/updatePointOfStore")
    public ResponseEntity<StoreResponse> updatePointOfStore() {
        StoreResponse storeResponse = storeService.updatePointDefaultOfStore();
        return ResponseEntity.ok().body(storeResponse);
    }


}

