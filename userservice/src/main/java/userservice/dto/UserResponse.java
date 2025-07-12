package userservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String username;
    private String email;
    private String fullName;
    private String phone;
    private String gender;
}
