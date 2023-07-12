package tma.intern.intern_manager.service.account;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tma.intern.intern_manager.dto.account.AccountDetailDto;
import tma.intern.intern_manager.dto.account.AccountRequestDto;
import tma.intern.intern_manager.entity.Account;
import tma.intern.intern_manager.entity.Intern;
import tma.intern.intern_manager.entity.Mentor;
import tma.intern.intern_manager.exception.ExistingException;
import tma.intern.intern_manager.exception.InvalidDataException;
import tma.intern.intern_manager.exception.NotFoundException;
import tma.intern.intern_manager.repository.AccountRepository;
import tma.intern.intern_manager.repository.InternRepository;
import tma.intern.intern_manager.repository.MentorRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private final MentorRepository mentorRepository;
    @Autowired
    private final InternRepository internRepository;
    @Autowired
    private final ModelMapper mapper;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, MentorRepository mentorRepository, InternRepository internRepository, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.mentorRepository = mentorRepository;
        this.internRepository = internRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<AccountDetailDto> getListAccount(PageRequest pr) {
        Page<Account> listAccount = accountRepository.findAll(pr);

        Page<AccountDetailDto> response = listAccount.map(account -> mapper.map(account, AccountDetailDto.class));
        return response;
    }

    public AccountDetailDto addAccount(AccountRequestDto request) {
        Optional<Account> existingAccount = accountRepository.findByUsername(request.getUsername());
        if(existingAccount.isPresent()) {
            throw new ExistingException("This username already exists");
        }

        Account create = mapper.map(request, Account.class);
        if(request.getMentor() != null) {
            Optional<Mentor> mentor = mentorRepository.findById(request.getMentor());
            mentor.ifPresent(create::setMentor);
        }
        if(request.getIntern() != null) {
            Optional<Intern> intern = internRepository.findById(request.getIntern());
            intern.ifPresent(create::setIntern);
        }
        String passwordHash = passwordEncoder.encode(request.getPassword());
        create.setPassword(passwordHash);
        Account newAccount = accountRepository.save(create);
        AccountDetailDto response = mapper.map(newAccount, AccountDetailDto.class);
        return response;
    }

    @Override
    public AccountDetailDto updateAccount(UUID id, AccountRequestDto request) {
        Account existingAccount = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account is not found"));
        String passwordHash = passwordEncoder.encode(request.getPassword());

        Optional<Mentor> mentor = mentorRepository.findById(request.getMentor());
        Optional<Intern> intern = internRepository.findById(request.getIntern());

        if(mentor.isPresent() && intern.isPresent()) {
            throw new InvalidDataException("Account cannot be assigned to both mentor and intern");
        }

        mentor.ifPresent(existingAccount::setMentor);
        intern.ifPresent(existingAccount::setIntern);

        existingAccount.setPassword(passwordHash);
        existingAccount.setUsername(request.getUsername());

        Account updatedAccount = accountRepository.save(existingAccount);
        return mapper.map(updatedAccount, AccountDetailDto.class);

    }

    @Override
    public String deleteAccount(UUID id)  {
        Account existingAccount = accountRepository.findById(id).orElseThrow(() -> new NotFoundException("Account is not found"));

        if(existingAccount.getIntern() != null) {
            throw new InvalidDataException("This account has been assigned to an intern");
        }
        if(existingAccount.getMentor() != null) {
            throw new InvalidDataException("This account has been assigned to an mentor");
        }

        accountRepository.deleteById(existingAccount.getId());
        return "Delete account successfully";
    }
}
