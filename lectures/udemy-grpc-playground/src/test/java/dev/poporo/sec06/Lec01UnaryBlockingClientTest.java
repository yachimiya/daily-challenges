package dev.poporo.sec06;

import com.google.protobuf.Empty;
import dev.poporo.models.sec06.BalanceCheckRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01UnaryBlockingClientTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(Lec01UnaryBlockingClientTest.class);

    @Test
    public void getBalanceTest() {
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(1)
                .build();

        var balance = this.bankBlockingStub.getAccountBalance(request);
        log.info("unary balance received: {}", balance);
        Assertions.assertEquals(100, balance.getBalance());
    }

    @Test
    public void getAllAccountsTest() {
        var accounts = this.bankBlockingStub.getAllAccounts(Empty.getDefaultInstance());
        log.info("all accounts size: {}", accounts.getAccountsCount());
        Assertions.assertEquals(10, accounts.getAccountsCount());
    }
}
