package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void start() {
        app = App.getApp();
        app.start();
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void stop() {
        app.stop();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("users");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @Test
    void testCreateValidUser() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Andrew")
                .field("lastName", "Henderson")
                .field("email", "henderson.a@gmail.com")
                .field("password", "qwerty1984")
                .asString();

        assertThat(response.getStatus()).isEqualTo(302);

        User user = new QUser()
                .firstName.equalTo("Andrew")
                .lastName.equalTo("Henderson")
                .email.equalTo("henderson.a@gmail.com")
                .password.equalTo("qwerty1984")
                .findOne();

        assertThat(user).isNotNull();
    }

    @Test
    void testCreateInvalidUser() {
        HttpResponse<String> response = Unirest
                .post(baseUrl + "/users")
                .field("firstName", "Andrew")
                .field("lastName", "Henderson")
                .field("email", "henderson.agmail.com")
                .field("password", "23")
                .asString();

        assertThat(response.getStatus()).isEqualTo(422);
        assertThat(response.getBody())
                .contains("Andrew", "Henderson", "henderson.agmail.com", "23")
                .contains("Должно быть валидным email", "Пароль должен содержать не менее 4 символов");

        User user = new QUser()
                .firstName.equalTo("Andrew")
                .lastName.equalTo("Henderson")
                .email.equalTo("henderson.agmail.com")
                .password.equalTo("23")
                .findOne();

        assertThat(user).isNull();
    }
    // END
}
