Feature: Register and Login
  Scenario: Register success then login success
    Given API HOST "https://reqres.in"
    When I input email "george.edwards@reqres.in"
    And I input password "HelloWorld@123"
    Then I hit register
    Then response code should be "200"
    And response body contains key "token"
    Then I input email "george.edwards@reqres.in"
    And I input password "HelloWorld@123"
    Then I hit login
    Then response code should be "200"
    And response body contains key "token"