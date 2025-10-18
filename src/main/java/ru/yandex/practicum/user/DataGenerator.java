package ru.yandex.practicum.user;

import net.datafaker.Faker;

public class DataGenerator {
    static Faker faker = new Faker();

    public static User randomUser() {
        final String name = faker.name().firstName();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(6, 10);
        return new User(name, email, password);
    }

    public static User userInvalidPassword() {
        final String name = faker.name().firstName();
        final String email = faker.internet().emailAddress();
        final String password = faker.internet().password(1, 5);
        return new User(name, email, password);
    }
}
