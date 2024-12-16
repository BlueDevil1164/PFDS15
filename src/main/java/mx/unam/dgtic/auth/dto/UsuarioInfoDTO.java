package mx.unam.dgtic.auth.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioInfoDTO {
    private Long usuId;
    private String usuFirstName;
    private String usuLastName;
    private String usuEmail;
    private String usuPasswd;
    private Integer usuIdStatus;
    private Long usuCreatedBy;
    private LocalDateTime usuCreatedDate;
    private Long usuModifiedBy;
    private LocalDateTime usuModifiedDate;
    private Set<UsuarioInfoRoleDTO> usuInfoRoles = new HashSet<>();
}
