package uz.pdp.apppost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apppost.aop.CheckPermission;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.RoleDTO;
import uz.pdp.apppost.service.RoleService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/role")
public class RoleController {


    @Autowired
    RoleService roleService;


    @PreAuthorize(value = "hasAnyAuthority('ADD_ROLE')")
    @PostMapping
    public HttpEntity<?> createRole(@Valid @RequestBody RoleDTO roleDTO) {
        ApiResponse apiResponse = roleService.addRole(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    //    @CheckPermission(value = "EDIT_ROLE")
    @PreAuthorize(value = "hasAnyAuthority('EDIT_ROLE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id, @Valid @RequestBody RoleDTO roleDTO) {
        ApiResponse apiResponse = roleService.editRole(id, roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('DELETE_ROLE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Long id) {
        ApiResponse apiResponse = roleService.deleteRole(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('GET_ROLES')")
    @GetMapping
    public HttpEntity<?> viewRoles() {
        ApiResponse apiResponse = roleService.getRoles();
        return ResponseEntity.ok(apiResponse);
    }


}
