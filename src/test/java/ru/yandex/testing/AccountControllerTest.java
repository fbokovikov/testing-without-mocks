package ru.yandex.testing;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;
import ru.yandex.testing.util.JsonTestUtil;

/**
 * @author fbokovikov
 */
@DatabaseSetup("classpath:account.xml")
class AccountControllerTest extends FunctionalTest {

    private static final long ACCOUNT_ID = 100L;

    @Test
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

    private String depositUrl(double amount) {
        return UriComponentsBuilder.fromUriString(baseUrl())
                .path("/accounts/{accountId}/deposit")
                .queryParam("amount", amount)
                .buildAndExpand(ACCOUNT_ID)
                .toUriString();
    }

    private String deposit(double amount) {
        return REST_TEMPLATE.postForObject(depositUrl(amount), null, String.class);
    }

}
