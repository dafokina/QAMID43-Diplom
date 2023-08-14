package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.data.Data.executor;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static org.junit.Assert.assertEquals;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.Helper;
import ru.iteco.fmhandroid.ui.data.Data;
import ru.iteco.fmhandroid.ui.screenElement.ClaimsElement;
import ru.iteco.fmhandroid.ui.screenElement.MainElement;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.ClaimsStep;
import ru.iteco.fmhandroid.ui.steps.CommentStep;
import ru.iteco.fmhandroid.ui.steps.GeneralStep;
import ru.iteco.fmhandroid.ui.steps.CreateClaimStep;
import ru.iteco.fmhandroid.ui.steps.EditClaimStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.SplashStep;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ClaimsTest {

    AuthStep authStep = new AuthStep();
    ClaimsStep claimsStep = new ClaimsStep();
    MainElement mainElement = new MainElement();
    ClaimsElement claimsElement = new ClaimsElement();
    CreateClaimStep createClaimStep = new CreateClaimStep();
    MainStep mainStep = new MainStep();
    Data data = new Data();
    GeneralStep generalStep = new GeneralStep();
    CommentStep commentStep = new CommentStep();
    EditClaimStep editClaimsSteps = new EditClaimStep();
    SplashStep splashStep = new SplashStep();


    @Rule
    public androidx.test.rule.ActivityTestRule<AppActivity> ActivityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void logoutCheck() {
        splashStep.appDownload();
        try {
            mainStep.mainScreenLoad();
        } catch (Exception e) {
            authStep.authWithValidLoginAndPass(Helper.authInfo());
            authStep.clickSignInButton();
        } finally {
            mainStep.mainScreenLoad();
            mainStep.clickAllClaims();
        }
    }

    @Test
    @DisplayName("Отображение информации в блоке \"Claims\"")
    @Description("Корректность отображения всех элементов Claims")
    public void testCheckClaimElements() {
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.checkClaimElements();
    }

    @Test
    @DisplayName("Развернуть и свернуть претензию")
    @Description("При нажатии на претензию отображается ее содержание")
    public void testOpenAndCloseClaim() {
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.checkClaimElements();
        claimsStep.goBackToPreviousScreen();
        claimsStep.checkClaimsScreenElements();
    }

    @Test
    @DisplayName("Открытие окна с фильтром претензии")
    @Description("При нажатии на кнопку фильтра открывается окно с фильтром претензии")
    public void testOpenWindowWithClaimFilter() {
        claimsStep.openFilter();
        claimsStep.checkFilterElements();
    }

    @Test
    @DisplayName("Сортировка/фильтрация претензии по критерию \"Open\" во вкладке \"Claims\" " +
            "приложения (позитивный)")
    @Description("При фильтрации по статусу Open отображаются только претензии со статусом Open")
    public void testSortClaimsByOpen() {
        claimsStep.openFilter();
        claimsStep.checkInProgress();
        generalStep.clickOkButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.checkClaimStatus("Open");
    }

    @Test
    @DisplayName("Отфильтровать претензии со статусом In Progress")
    @Description("При фильтрации по статусу In progress отображаются только претензии со статусом " +
            "In progress")
    public void testSortClaimsByInProgress() {
        claimsStep.openFilter();
        claimsStep.checkOpen();
        generalStep.clickOkButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.checkClaimStatus("In progress");
    }

    @Test
    @DisplayName("Сортировка и фильтрация претензий по критерию \"Executed\" " +
            "во вкладке \"Claims\" приложения (позитивный)")
    @Description("При фильтрации по статусу Executed отображаются только претензии со статусом Executed")
    public void testSortClaimsByExecuted() {
        claimsStep.openFilter();
        claimsStep.checkOpen();
        claimsStep.checkInProgress();
        claimsStep.checkExecuted();
        generalStep.clickOkButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.checkClaimStatus("Executed");
    }

    @Test
    @DisplayName("Сортировка и фильтрация претензий по критерию \"Cancelled \" " +
            "во вкладке \"Claims\" приложения (позитивный)")
    @Description("При фильтрации по статусу Cancelled отображаются только претензии со статусом Cancelled")
    public void testSortClaimsByCancelled() {
        claimsStep.openFilter();
        claimsStep.checkOpen();
        claimsStep.checkInProgress();
        claimsStep.checkCancelled();
        generalStep.clickOkButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.checkClaimStatus("Canceled");
    }

    @Test
    @DisplayName("Не выбрать ни один фильтр во вкладке \"Claims\" приложения (негативный)")
    @Description("Претензии без статуса не отображается, alert: There is nothing here yet")
    public void testNotCheckFiltersClaims() {
        claimsStep.openFilter();
        claimsStep.checkOpen();
        claimsStep.checkInProgress();
        generalStep.clickOkButton();
        claimsStep.emptyScreen();
        generalStep.checkClaimButterfly();
        generalStep.checkNothingToShow();
    }

    @Test
    @DisplayName("Создание претензии из главного экрана приложения через кнопку +")
    @Description("Тест падает, потому что претензию невозможно найти, на экране отображаются претензии с ранней датой, " +
            "например 1700 года. Хотя новые претензии должны быть наверху")
    public void testCreateClaimFromMainScreen() {
        mainStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimElements();
        createClaimStep.fillInTitleField(data.titleClaim);
        createClaimStep.fillInExecutorField(executor);
        createClaimStep.fillInDateField(data.dateClaim);
        createClaimStep.fillInTimeField(data.timeClaim);
        createClaimStep.fillItDescriptionField(data.claimDescriptCyr);
        generalStep.clickSaveButton();
        mainStep.mainScreenLoad();
        mainElement.claimsText.perform(swipeUp());
        mainStep.claimOnMainload();
        mainStep.clickClaimOnMain(0);
        claimsStep.allClaimElementsLoad();
        assertEquals(data.titleClaim, claimsStep.getClaimTitle());
        assertEquals(data.claimDescriptCyr, claimsStep.getClaimDescription());
        assertEquals(data.dateClaim, claimsStep.getClaimDate());
        assertEquals(data.timeClaim, claimsStep.getClaimTime());
    }
    @Test
    @DisplayName("Создание претензии с полем, которое состоит из 51 символа")
    @Description("Сохраняются только 50 символов. Тест падает, потому что, чтобы он проходил надо поставить самую раннюю дату" +
            "которая есть у претензий в приложении")
    public void testCheckTitleClaimLength() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();;
        createClaimStep.fillInTitleField(data.claimTitle51);
        createClaimStep.fillInDateField(data.dateClaim);
        createClaimStep.fillInTimeField(data.timeClaim);
        createClaimStep.fillItDescriptionField(data.claimDescriptLatin);
        generalStep.clickSaveButton();
        claimsStep.claimsListLoad();
        claimsElement.claimList.perform(swipeDown());
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.getClaimTitle();
        assertEquals("123456789012345678901234567890123456789012345678901", claimsStep.getClaimTitle());
    }

    @Test
    @DisplayName("Создание претензии пробелами в названии и описании")
    @Description("Претензия не создается, предупреждение о необходимости заполнить пустые поля")
    public void testCreateClaimWithSpaces() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();
        createClaimStep.checkCreateClaimElements();
        createClaimStep.fillInTitleField(data.claimTitleSpace);
        createClaimStep.fillInExecutorField(executor);
        createClaimStep.fillInDateField(data.claimDateOfPublic);
        createClaimStep.fillInTimeField(data.claimTimeOfPublic);
        createClaimStep.fillItDescriptionField(data.claimDescriptSpace);
        generalStep.clickSaveButton();
        generalStep.checkFillEmptyFieldsMessage();
    }

    @Test
    @DisplayName("Создание претензии с пустыми полями")
    @Description("Не заполняется, всплывает предупреждение о необходимости заполнить пустые поля")
    public void testCreateClaimEmptyFields() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();
        createClaimStep.checkCreateClaimElements();
        generalStep.clickSaveButton();
        generalStep.checkFillEmptyFieldsMessage();
    }


    @Test
    @DisplayName("Создание претензии со спецсимволами в полях названии и описании")
    @Description("Тест падает. Форма дает возможность ввода спецсимволов в поля")
    public void testCreateClaimsWithSymbols() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();
        createClaimStep.checkCreateClaimElements();
        createClaimStep.fillInTitleField(data.claimTitleSymbols);
        createClaimStep.fillInExecutorField(executor);
        createClaimStep.fillInDateField(data.claimDateOfPublic);
        createClaimStep.fillInTimeField(data.claimTimeOfPublic);
        createClaimStep.fillItDescriptionField(data.claimDescriptSymb);
        generalStep.clickSaveButton();
        generalStep.checkInvalidData("Wrong format data", true);
    }

    @Test
    @DisplayName("Создать претензию с пустой датой и временем")
    @Description("Предупреждение о необходимости заполнить пустые поля")
    public void testCreateClaimWithEmptyDateAndTime() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();
        createClaimStep.checkCreateClaimElements();
        createClaimStep.fillInTitleField(data.claimTitleLatin);
        createClaimStep.fillItDescriptionField(data.claimDescriptLatin);
        generalStep.clickSaveButton();
        generalStep.checkFillEmptyFieldsMessage();
    }

    @Test
    @DisplayName("Редактирование комментария во вкладке \"Claims\" ")
    @Description("Измененный комменатрий отображается")
    public void testEditCommentClaim() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();
        createClaimStep.checkCreateClaimElements();
        createClaimStep.fillInTitleField(data.claimTitleCyr);
        createClaimStep.fillInExecutorField(executor);
        createClaimStep.fillInDateField(data.dateClaim);
        createClaimStep.fillInTimeField(data.timeClaim);
        createClaimStep.fillItDescriptionField(data.claimDescriptCyr);
        generalStep.clickSaveButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.clickAddCommentButton();
        commentStep.checkCommentScreenElements();
        commentStep.addComment(data.commentCyr);
        generalStep.clickSaveButton();
        claimsStep.clickCommentEditButton(0);
        commentStep.addComment(data.editComment);
        generalStep.clickSaveButton();
        claimsStep.allClaimElementsLoad();
        claimsStep.checkComment(data.editComment);
    }

    @Test
    @DisplayName("Отменить комментирование к претензии")
    @Description("Комментарий не сохраняется")
    public void testCancelCommentingClaim() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();
        createClaimStep.checkCreateClaimElements();
        createClaimStep.fillInTitleField(data.claimTitleCyr);
        createClaimStep.fillInExecutorField(executor);
        createClaimStep.fillInDateField(data.dateClaim);
        createClaimStep.fillInTimeField(data.timeClaim);
        createClaimStep.fillItDescriptionField(data.claimDescriptCyr);
        generalStep.clickSaveButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.clickAddCommentButton();
        commentStep.checkCommentScreenElements();
        commentStep.addComment(data.commentCyr);
        generalStep.clickSaveButton();
        claimsStep.allClaimElementsLoad();
        String initialCommentContent = claimsStep.getClaimComment(0);
        claimsStep.clickCommentEditButton(0);
        commentStep.addComment(data.editComment);
        generalStep.clickCancelButton();
        claimsStep.allClaimElementsLoad();
        String commentAfterCancelledEditing = claimsStep.getClaimComment(0);
        assertEquals(initialCommentContent, commentAfterCancelledEditing);
    }

    @Test
    @DisplayName("Отменить создание комментария к претензии")
    @Description("Комментарий не добавляется")
    public void testCancelCommentAdd() {
        claimsStep.clickNewClaimButton();
        createClaimStep.checkCreateClaimScreenLoad();
        createClaimStep.checkCreateClaimElements();
        createClaimStep.fillInTitleField(data.claimTitleLatin);
        createClaimStep.fillInExecutorField(executor);
        createClaimStep.fillInDateField(data.dateClaim);
        createClaimStep.fillInTimeField(data.timeClaim);
        createClaimStep.fillItDescriptionField(data.claimDescriptLatin);
        generalStep.clickSaveButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.clickAddCommentButton();
        commentStep.checkCommentScreenElements();
        generalStep.clickCancelButton();
        claimsStep.allClaimElementsLoad();
        claimsStep.statusIsDisplayed();
    }

    @Test
    @DisplayName("Редактировать претензию")
    @Description("Претензия отображается с новыми данными")
    public void testEditClaim() {
        claimsStep.openFilter();
        claimsStep.checkInProgress();
        generalStep.clickOkButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.clickEditClaimButton();
        editClaimsSteps.changeClaimTitle(data.claimEditedTitle);
        editClaimsSteps.changeClaimDescription(data.claimEditedDescription);
        generalStep.clickSaveButton();
        assertEquals(data.claimEditedTitle, claimsStep.getClaimTitle());
        assertEquals(data.claimEditedDescription, claimsStep.getClaimDescription());
    }

    @Test
    @DisplayName("Отменить редактирование претензии")
    @Description("При отмене редактирования без подтверждения редактирование можно продолжить," +
            " при подтверждении отмены - претензия не редактируется")
    public void testCancelClaimEdit() {
        claimsStep.openFilter();
        claimsStep.checkInProgress();
        generalStep.clickOkButton();
        claimsStep.claimsListLoad();
        claimsStep.expandClaim(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.clickEditClaimButton();
        generalStep.clickCancelButton();
        generalStep.checkCancelToast();
        generalStep.clickCancelInDialog();
        editClaimsSteps.checkEditClaimElements();
        generalStep.clickCancelButton();
        generalStep.checkCancelToast();
        generalStep.clickOkButton();
        claimsStep.statusIsDisplayed();
    }
}