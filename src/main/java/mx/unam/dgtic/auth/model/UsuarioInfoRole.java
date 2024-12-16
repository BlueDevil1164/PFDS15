package mx.unam.dgtic.auth.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sec_role")
public class UsuarioInfoRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", columnDefinition = "bigint", nullable = false, updatable = false)
    private Long userId;
    @Column(name = "user_role_name", columnDefinition = "varchar(30)", length = 30, nullable = false)
    private String userRoleName;
    @Column(name = "user_id_status", columnDefinition = "integer", nullable = false)
    private Integer userIdStatus;
    @Column(name = "user_created_by", columnDefinition = "bigint", updatable = false, nullable = false)
    private Long userCreatedBy;
    @CreationTimestamp
    @Column(name = "user_created_date", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP", updatable = false, nullable = false,
            insertable = false)
    private LocalDateTime userCreatedDate;
    @Column(name = "user_modified_by", columnDefinition = "bigint", nullable = false)
    private Long userModifiedBy;
    @UpdateTimestamp
    @Column(name = "user_modified_date", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false)
    private LocalDateTime userModifiedDate;

}
