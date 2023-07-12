package tma.intern.intern_manager.dto.mentor;

import java.util.Date;
import java.util.UUID;

public interface IMentorQuantity {
     UUID getId();
     String getName();
     String getEmail();
     String getGender();
     String getPhone();
     String getAddress();
     Date getBirthday();
     Integer getTotalIntern();
     Integer getTotalTeam();
}
