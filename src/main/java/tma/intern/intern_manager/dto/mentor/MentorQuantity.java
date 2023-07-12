package tma.intern.intern_manager.dto.mentor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorQuantity implements  IMentorQuantity{
    private UUID id;
    private String name;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private Date birthday;
    private Integer totalIntern;
    private Integer totalTeam;
}
