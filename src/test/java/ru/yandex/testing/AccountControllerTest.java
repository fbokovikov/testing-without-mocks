package ru.yandex.testing;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;
import ru.yandex.testing.util.HttpRequestUtil;
import ru.yandex.testing.util.JsonTestUtil;

/**
 * @author fbokovikov
 */
class AccountControllerTest extends FunctionalTest {

    private static final long ACCOUNT_ID = 100L;

    @Test
    @DatabaseSetup("classpath:account.xml")
    @ExpectedDatabase("classpath:depositAccount.xml")
    void deposit() {
        String response = deposit(400.0);

        String expected = "" +
                "{\"id\":100,\"amount\":900.0}";
        JsonTestUtil.assertEquals(
                expected,
                response
        );
    }

    @Test
    @DatabaseSetup("classpath:transferSetup.xml")
    @ExpectedDatabase("classpath:transferExpected.xml")
    void transfer() {
        String requestBody = "" +
                "{  \n" +
                "   \"fromId\":100,\n" +
                "   \"toId\":200,\n" +
                "   \"amount\":100.0\n" +
                "}";
        transfer(requestBody);
    }

    private String depositUrl(double amount) {
        return UriComponentsBuilder.fromUriString(baseUrl())
                .path("/accounts/{accountId}/deposit")
                .queryParam("amount", amount)
                .buildAndExpand(ACCOUNT_ID)
                .toUriString();
    }

    private String transferUrl() {
        return UriComponentsBuilder.fromUriString(baseUrl())
                .path("/accounts/transfer")
                .toUriString();
    }

    private String deposit(double amount) {
        return HttpRequestUtil.post(depositUrl(amount));
    }

    private String transfer(String requestBody) {
        return HttpRequestUtil.post(transferUrl(), requestBody);
    }

}
