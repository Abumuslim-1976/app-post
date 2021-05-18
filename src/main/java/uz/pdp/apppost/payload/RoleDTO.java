package uz.pdp.apppost.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.apppost.entity.Permission;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @NotNull(message = "Roleni nomini yozing")
    private String name;

    @NotNull(message = "Huquqlarni yozing")
    private List<Permission> permissions;

}
