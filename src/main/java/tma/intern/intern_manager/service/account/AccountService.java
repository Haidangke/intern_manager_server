package tma.intern.intern_manager.service.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import tma.intern.intern_manager.dto.account.AccountDetailDto;
import tma.intern.intern_manager.dto.account.AccountRequestDto;

import java.util.UUID;

public interface AccountService {
    AccountDetailDto addAccount(AccountRequestDto request);

    AccountDetailDto updateAccount(UUID id, AccountRequestDto request);

    String deleteAccount(UUID id);

    Page<AccountDetailDto> getListAccount(PageRequest pr);
}
