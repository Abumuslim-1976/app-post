package uz.pdp.apppost.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.apppost.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends AbstractEntity {

    @Column(unique = true, nullable = false) // ADMIN , USER
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissions;

}
