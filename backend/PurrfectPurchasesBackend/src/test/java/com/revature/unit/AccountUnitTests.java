package com.revature.unit;

import com.revature.project2.dao.AccountDAO;
import com.revature.project2.service.AccountService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Provides Jupiter with additional functionalities coming with Mockito
public class AccountUnitTests {

    @Mock
    AccountDAO mockAD;

    @InjectMocks
    AccountService accountService;


}
