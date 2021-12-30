Feature: Product Order Confirmation

  @Smoke
  Scenario Outline: This script is to verify that create and complete product order
    Given I launch the application <url>
    And I login to the application using <userName> and <password>
    And I clear the basket if present
    When I Select and add a products to basket
    Then I enter checkout user <FirstName> and <LastName> details and <Postcode> and submit
    And I navigate to Order Confirmation page from checkout review page
    And I logout from the application
   
    Examples:
      |url       |userName      |password  |FirstName|LastName     |Postcode|
      |StoreFrontUrl |standard_user |secret_sauce |Arunkumar|Rajamanickam |E125AD  |