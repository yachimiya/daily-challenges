package dev.poporo.sec11;

import com.google.common.util.concurrent.Uninterruptibles;
import dev.poporo.common.AbstractChannelTest;
import dev.poporo.common.GrpcServer;
import dev.poporo.models.sec11.BalanceCheckRequest;
import dev.poporo.models.sec11.BankServiceGrpc;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    It is a class to demo the keep alive PING & GO AWAY
 */
public class Lec06KeepAliveDemoTest extends AbstractChannelTest {

    private static final Logger log = LoggerFactory.getLogger(Lec06KeepAliveDemoTest.class);
    private final GrpcServer grpcServer = GrpcServer.create(new DeadlineBankService());
    private BankServiceGrpc.BankServiceBlockingStub bankBlockingStub;

    @BeforeAll
    public void setup() {
        this.grpcServer.start();
        this.bankBlockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    /*
        Configure the server with keep alive
     */
    @Test
    public void keepAliveDemo() {
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(1)
                .build();
        var response = this.bankBlockingStub.getAccountBalance(request);
        log.info("{}", response);

        // just blocking the thread for 30 seconds
        Uninterruptibles.sleepUninterruptibly(30,  TimeUnit.SECONDS);
    }

    @AfterAll
    public void stop() {
        this.grpcServer.stop();
    }
}
