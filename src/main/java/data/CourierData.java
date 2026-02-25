package data;

import com.github.javafaker.Faker;

public class CourierData {
    private static final String BASE_URI= "https://qa-scooter.praktikum-services.ru/";
    static Faker user = new Faker();
    public static final String LOGIN = user.name().lastName();
    public static final String PASSWORD = user.regexify("[0-9]{4}");
    public static final String FIRSTNAME = user.name().firstName();
    public static final String CREATE_PATH = "/api/v1/courier";
    public static final String ERROR_LOGIN_ALREADY_USED = "Этот логин уже используется";
    public static final String ERROR_INSUFFICIENT_DATA = "Недостаточно данных для создания учетной записи";
    public static final String SIGN_IN = "/api/v1/courier/login";
    public static final String ERROR_INCORRECT_DATA = "Недостаточно данных для входа";
    public static final String ERROR_ACCOUNT_NOT_FOUND = "Учетная запись не найдена";
    public static final String DELETE_PATH = "/api/v1/courier/:id";
}
