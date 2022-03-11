package uz.pdp.pdpspring7thlessonn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pdpspring7thlessonn.aop.CheckPermission;
import uz.pdp.pdpspring7thlessonn.entity.Lavozim;
import uz.pdp.pdpspring7thlessonn.payload.ApiResponse;
import uz.pdp.pdpspring7thlessonn.payload.LavozimDto;
import uz.pdp.pdpspring7thlessonn.service.LavozimService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lavozim")
public class LavozimController {
    @Autowired
    LavozimService lavozimService;

    @PreAuthorize(value = "hasAuthority('ADD_LAVOZIM')")
    @PostMapping("/addLavozim")
    public ResponseEntity<?> addlavozim(@Valid @RequestBody LavozimDto lavozimDto){
        ApiResponse response = lavozimService.addLavozim(lavozimDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_LAVOZIM')")
//    @CheckPermission(huquq = "EDIT_LAVOZIM")
    @PutMapping("/addLavozim{id}")
    public ResponseEntity<?> editlavozim(@PathVariable Long id, @Valid  @RequestBody LavozimDto lavozimDto){
        ApiResponse response = lavozimService.editlavozim(id,lavozimDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
    @PreAuthorize(value = "hasAuthority('DELETE_LAVOZIM')")
    @DeleteMapping("/deleteLavozim/{id}")
    public ResponseEntity<?> deleteLavozim(Long id){
        lavozimService.deleteLavozim(id);
        return ResponseEntity.ok("Lavozim o'chirildi");
    }
    @PreAuthorize(value = "hasAuthority('VIEW_LAVOZIMLAR')")
    @GetMapping("/getAll")
    public  ResponseEntity<?> getAll(){
        List<Lavozim> aLlLavozims = lavozimService.getALlLavozims();
        return ResponseEntity.ok(aLlLavozims);
    }

}
