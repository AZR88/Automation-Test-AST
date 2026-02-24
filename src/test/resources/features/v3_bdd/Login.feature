Feature: Login Functionality (BDD Style)

  Scenario: User performs login with valid credentials
    Given user is on demoblaze homepage
    When user clicks login menu
    And user enters username "azriel_test" and password "password123"
    And user clicks login button
    Then login should be successful