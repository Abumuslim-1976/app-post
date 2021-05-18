package uz.pdp.apppost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apppost.entity.Role;
import uz.pdp.apppost.payload.ApiResponse;
import uz.pdp.apppost.payload.RoleDTO;
import uz.pdp.apppost.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {


    @Autowired
    RoleRepository roleRepository;


    public ApiResponse addRole(RoleDTO roleDTO) {
        if (roleRepository.existsByName(roleDTO.getName()))
            return new ApiResponse("Bunday role bor", true);

        roleRepository.save(new Role(
                roleDTO.getName(),
                roleDTO.getPermissions()
        ));
        return new ApiResponse("Role saqlandi", true);
    }


    public ApiResponse editRole(Long id, RoleDTO roleDTO) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (!roleOptional.isPresent())
            return new ApiResponse("Bunaqa ID lik role yo`q", false);

        Role role = roleOptional.get();
        role.setName(roleDTO.getName());
        role.setPermissions(roleDTO.getPermissions());
        roleRepository.save(role);
        return new ApiResponse("Role edit qilindi", true);
    }


    public ApiResponse deleteRole(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            throw new NullPointerException("Role topilmadi");
        }
        return new ApiResponse("Role o`chirildi", true);
    }


    public ApiResponse getRoles() {
        List<Role> roleList = roleRepository.findAll();
        return new ApiResponse("Role lar listi", true, roleList);
    }
}
