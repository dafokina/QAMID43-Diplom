package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.PerformException;

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
import ru.iteco.fmhandroid.ui.steps.AboutStep;
import ru.iteco.fmhandroid.ui.steps.AuthStep;
import ru.iteco.fmhandroid.ui.steps.ClaimsStep;
import ru.iteco.fmhandroid.ui.steps.GeneralStep;
import ru.iteco.fmhandroid.ui.steps.CreateClaimStep;
import ru.iteco.fmhandroid.ui.steps.LoveStep;
import ru.iteco.fmhandroid.ui.steps.MainStep;
import ru.iteco.fmhandroid.ui.steps.NewsStep;
import ru.iteco.fmhandroid.ui.steps.SplashStep;

@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    AuthStep authStep = new AuthStep();
    MainStep mainStep = new MainStep();
    ClaimsStep claimsStep = new ClaimsStep();
    NewsStep newsStep = new NewsStep();
    AboutStep aboutStep = new AboutStep();
    LoveStep loveStep = new LoveStep();
    CreateClaimStep createClaim = new CreateClaimStep();
    GeneralStep generalStep = new GeneralStep();
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
        }
    }

    @Test
    @DisplayName("Отображение разделов меню")
    @Description("В списке есть разделы Main, Claims, News, About")
    public void testCheckMenuScreenList() {
        mainStep.clickMenuButton();
        mainStep.checkMenuList();
    }

    @Test
    @DisplayName("Переход из бокового меню в разделы \"Claims\", \"Main\",  \"News\", " +
            "\"About\"")
    @Description("Переход на соответствующую вкладку из меню приложения")
    public void testCheckTransitionFromMenu() {
        mainStep.goToClaims();
        claimsStep.checkClaimsScreenElements();
        mainStep.goToNews();
        newsStep.checkNewsElements();
        mainStep.goToAbout();
        aboutStep.checkScreenElementsAbout();

    }

    @Test
    @DisplayName("Переход на вкладку с цитатами Love is all")
    @Description("Переход на вкладку Love is all из главного экрана приложения")
    public void testCheckTransitionToMissionScreen() {
        mainStep.clickMissionButton();
        loveStep.checkMissionElements();
    }

    @Test
    @DisplayName("Переход на вкладку News через All News и возврат на главный экран")
    @Description("На главном экране, нажав кнопку All News, происходит переход на вкладку News")
    public void testCheckAllNewsButton() {
        mainStep.clickAllNews();
        newsStep.checkNewsElements();
        mainStep.goToMain();
        mainStep.checkMainElements();
    }

    @Test
    @DisplayName("Развернуть и свернуть блок новостей (News)")
    @Description("Блок новостей при нажатии сворачивается, при повторном нажатии - разворачивается")
    public void testExpandAndCollapseNewsBlock() {
        mainStep.checkAllNews();
        mainStep.allNewsNotDisplay();
        mainStep.checkAllNews();
        mainStep.allNewsDisplay();
    }

    @Test
    @DisplayName("Переход на вкладку Claims через All Claims и возврат на главный экран")
    @Description("На главном экране, нажав кнопку All Claims, происходит переход на вкладку Claims")
    public void testCheckAllClaimsButton() {
        mainStep.clickAllClaims();
        claimsStep.checkClaimsScreenElements();
        mainStep.goToMain();
        mainStep.checkMainElements();
    }
    @Test
    @DisplayName("Развернуть и свернуть блок претензий (Claims)")
    @Description("Блок претензий при нажатии сворачиваются, при повторном нажатии - разворачивается")
    public void testExpandAndCollapseClaimsBlock() {
        mainStep.checkAllClaims();
        mainStep.allClaimsNotDisplay();
        mainStep.checkAllClaims();
        mainStep.allClaimsDisplay();
    }


    @Test
    @DisplayName("Развернуть отдельную новость")
    @Description("При нажатии на отдельную новость отображается ее описание")
    public void testExpandSeparateNewsItem() {
        mainStep.checkOneNews(0);
        mainStep.descriptionIsDisplay(0);
    }

    @Test
    @DisplayName("Развернуть/свернуть отдельную прeтенезию")
    @Description("При нажатии на претензию открывается окно с претензией и ее описанием")
    public void testExpandCollapseSeparateClaim() {
        mainStep.clickClaimOnMain(0);
        claimsStep.allClaimElementsLoad();
        claimsStep.checkClaimElements();
        claimsStep.goBackToPreviousScreen();
        mainStep.checkMainElements();
    }

}