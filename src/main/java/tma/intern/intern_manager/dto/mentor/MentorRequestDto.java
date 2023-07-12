package tma.intern.intern_manager.dto.mentor;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class MentorRequestDto {
    private UUID id;

    private String name;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private Date birthday;
    private UUID account;

}
