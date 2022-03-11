package uz.pdp.pdpspring7thlessonn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.pdpspring7thlessonn.entity.template.AbsEntity;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment extends AbsEntity {

    @Column(nullable = false,columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}
