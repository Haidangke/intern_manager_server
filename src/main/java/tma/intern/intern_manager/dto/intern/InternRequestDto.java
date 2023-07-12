package tma.intern.intern_manager.dto.intern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternRequestDto {
    UUID id;
    String name;
    String email;
    String phone;
    String gender;
    String address;
    Date birthday;
    String description;
    String technology;
    String status;
    UUID mentor;
    UUID team;
    UUID account;
}
