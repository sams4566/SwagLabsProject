Feature: Being able to navigate to pages on Swag Labs

  Scenario: going to the cart page from the login page
    Given I am on the login page
    When I click on the cart link
    Then I receive the correct link

