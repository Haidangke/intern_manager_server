package tma.intern.intern_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tma.intern.intern_manager.dto.account.AccountDetailDto;
import tma.intern.intern_manager.dto.account.AccountRequestDto;
import tma.intern.intern_manager.service.account.AccountService;

import java.util.UUID;

@RestController
@PreAuthorize(
        "hasRole('ROLE_ADMIN')"
)
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    @Autowired
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    ResponseEntity<Page<AccountDetailDto>> getList(
            @RequestParam(name = "page" , defaultValue = "0") int page,
            @RequestParam(name = "size" , defaultValue = "10") int size) {
        PageRequest pr = PageRequest.of(page, size);
        Page<AccountDetailDto> response = accountService.getListAccount(pr);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<AccountDetailDto> add(@RequestBody AccountRequestDto request) {
        AccountDetailDto response = accountService.addAccount(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    ResponseEntity<AccountDetailDto> update(@PathVariable(name = "id") UUID id, @RequestBody AccountRequestDto request) {
        AccountDetailDto response = accountService.updateAccount(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable(name = "id") UUID id) {
        String response = accountService.deleteAccount(id);
        return ResponseEntity.ok(response);
    }
}
