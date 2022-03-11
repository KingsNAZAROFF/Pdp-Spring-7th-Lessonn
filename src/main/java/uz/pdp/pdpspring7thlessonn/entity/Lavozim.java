package uz.pdp.pdpspring7thlessonn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.pdpspring7thlessonn.entity.enums.Permissions;
import uz.pdp.pdpspring7thlessonn.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Lavozim extends AbsEntity {

    @Column(nullable = false,unique = true)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permissions> huquqList;

    @Column(length = 600)
    private String description;

    public Lavozim(String name, List<Permissions> huquqList) {
        this.name = name;
        this.huquqList = huquqList;
    }
}
