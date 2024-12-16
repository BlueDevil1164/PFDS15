package mx.unam.dgtic.auth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sec_user")
public class UsuarioInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id", columnDefinition = "bigint", nullable = false, updatable = false)
    private Long usuId;
    @Column(name = "usu_first_name", columnDefinition = "varchar(20)", length = 20, nullable = false)
    private String usuFirstName;
    @Column(name = "usu_last_name", columnDefinition = "varchar(20)", length = 20, nullable = false)
    private String usuLastName;
    @Column(name = "usu_email", columnDefinition = "varchar(45)", length = 45, nullable = false, unique = true)
    private String usuEmail;
    @Column(name = "usu_passwd", columnDefinition = "varchar(64)", length = 64, nullable = false)
    private String usuPasswd;
    @Column(name = "usu_id_status", columnDefinition = "integer", nullable = false)
    private Integer usuIdStatus;
    @Column(name = "usu_created_by", columnDefinition = "bigint", updatable = false, nullable = false)
    private Long usuCreatedBy;
    @CreationTimestamp
    @Column(name = "usu_created_date", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP", updatable = false, nullable = false, insertable = false)
    private LocalDateTime usuCreatedDate;
    @Column(name = "cmp_modified_by", columnDefinition = "bigint", nullable = false)
    private Long usuModifiedBy;
    @UpdateTimestamp
    @Column(name = "cmp_modified_date", columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP", nullable = false, insertable = false)
    private LocalDateTime usuModifiedDate;
    //ROLES
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "usu_adm",
            name = "sec_usuario_role_relation",
            joinColumns = @JoinColumn(name = "urr_id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "urr_id_usuario_role")
    )
    @JsonManagedReference
    private Set<UsuarioInfoRole> usuInfoRoles = new HashSet<>();

    public String getFullName() {
        return this.usuFirstName + ' ' + this.usuLastName;
    }


}
