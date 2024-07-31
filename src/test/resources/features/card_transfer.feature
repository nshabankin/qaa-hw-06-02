Feature: Card Transfer

  Scenario: Transfer money from card 2 to card 1
    Given the user is on the login page
    When the user logs in with username "vasya" and password "qwerty123"
    And the user verifies with code "12345"
    And the user transfers 5000 rubles from the card with number "5559 0000 0000 0002" to his first card from the dashboard
    Then the balance of his first card from the dashboard should be 15000 rubles
