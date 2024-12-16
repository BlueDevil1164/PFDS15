package mx.unam.dgtic.auth.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioInfoRoleDTO {
    private Long userId;
    private String userRoleName;
    private Integer userIdStatus;
    private Long userCreatedBy;
    private LocalDateTime userCreatedDate;
    private Long userModifiedBy;
    private LocalDateTime userModifiedDate;
}
