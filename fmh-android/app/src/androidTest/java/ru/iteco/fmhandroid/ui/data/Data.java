package ru.iteco.fmhandroid.ui.data;

import static ru.iteco.fmhandroid.ui.data.Helper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.Helper.getCurrentTime;

import io.bloco.faker.Faker;
public class Data {
    static Faker faker = new Faker();

    public static final String validLogin = "login2";
    public static final String  validPass = "password2";
    public static final String invalidLogin = faker.name.firstName() + faker.number.between(1, 10);
    public static final String invalidPass = faker.internet.password();
    public static final String emptyLogin = "";
    public static final String emptyPass = "";
    public static final String executor = "Алексеев Алексей Алексеевич";

    public String quoteText = "Все сотрудники хосписа - это адвокаты пациента, его прав и потребностей. " +
            "Поиск путей решения различных задач - это и есть хосписный индивидуальный подход к паллиативной помощи.";

    public String dateOfPublic = getCurrentDate();
    public String timeOfPublic = getCurrentTime();
    public String dateNonNews = "09.09.1999";

    public String descriptOnCyr = "Другое описание новости";
    public String descriptSymb = "='+*&^%$#@~";
    public String titleCyr = "Заголовок новости";
    public String titleSymb = "'+*&^%$#@~";
    public String descriptionEmptyText = "";
    public String newTitleEdit = "Отредактированный заголовок";
    public String newDescriptionEdit = "Отредактированное описание";
    public String newTitle = "Новый заголовок";
    public String newDescription = "Новое описание";

    public String claimDateOfPublic = getCurrentDate();
    public String claimTimeOfPublic = getCurrentTime();

    public String claimDescriptLatin = "Description claim";
    public String claimDescriptCyr = "Претензия на ремонт";
    public String claimDescriptSymb = "'+*&^%$#@~";
    public String claimDescriptSpace = " ";
    public String claimTitleLatin = "Claim";
    public String claimTitle51 = "123456789012345678901234567890123456789012345678901";
    public String claimTitleCyr = "Претензия на установку";
    public String claimTitleSymbols = "=+_)(*&^%$#@!~";
    public String claimTitleSpace = " ";
    public String claimEditedTitle = "Новый заголовок";
    public String claimEditedDescription = "Новое описание";
    public String titleClaim = "Новость на экране";
    public String dateClaim = "15.08.2023";
    public String timeClaim = "10:10";

    public String commentCyr = "Комментарий" + faker.number.between(1, 100);
    public String editComment = "После редактировования";
}