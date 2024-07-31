package ru.netology.domain.pages;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class VerificationPage {
    public DashboardPage validVerify(String verificationCode) {
        $("[data-test-id='code'] input").shouldBe(visible);
        System.out.println("Input field is visible.");
        sleep(1000);  // Explicit wait (to manually close the Google password change popup)
        $("[data-test-id='code'] input").setValue(verificationCode).shouldHave(value(verificationCode));
        // Add debug message
        System.out.println("Verification code entered.");
        $("[data-test-id='action-verify']").click();
        // Add debug message
        System.out.println("Verification button clicked.");
        return new DashboardPage();
    }
}
