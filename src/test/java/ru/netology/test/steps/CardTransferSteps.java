package ru.netology.test.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.netology.domain.pages.DashboardPage;
import ru.netology.domain.pages.LoginPage;
import ru.netology.domain.pages.TransferPage;
import ru.netology.domain.pages.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTransferSteps {

    private LoginPage loginPage;
    private VerificationPage verificationPage;
    private DashboardPage dashboardPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        open("http://localhost:9999");
        loginPage = new LoginPage();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        verificationPage = loginPage.validLogin(username, password);
    }

    @When("the user verifies with code {string}")
    public void theUserVerifiesWithCode(String verificationCode) {
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @When("the user transfers {int} rubles from the card with number {string} to his first card from the dashboard")
    public void theUserTransfersRublesFromTheCardWithNumberToHisFirstCardFromTheDashboard(int amount, String cardNumber) {
        TransferPage transferPage = dashboardPage.selectDestinationCard(0);
        dashboardPage = transferPage.makeTransfer(cardNumber, amount);
    }

    @Then("the balance of his first card from the dashboard should be {int} rubles")
    public void theBalanceOfHisFirstCardFromTheDashboardShouldBeRubles(int expectedBalance) {
        int finalBalanceOriginCard = dashboardPage.getCardBalance(0);
        assertEquals(expectedBalance, finalBalanceOriginCard, "Balance of Card 1 should be " + expectedBalance + " rubles");
    }
}
