Feature: Create Users
  Scenario Outline: Create user then get newly created user detail
    Given API HOST "https://gorest.co.in"
    Then I input user email "<email>"
    Then I input user name "<name>"
    Then I input user gender "<gender>"
    Then I input user status "<status>"
    Then I hit create user
    Then response code should be "201"
    And response body contains key "id"
    When I hit get user detail with newly created user id
    Then response code should be "200"
    And response body "name" equals "<name>"
    And response body "gender" equals "<gender>"
    And response body "status" equals "<status>"
    Examples:
    |email|name|gender|status|
    |<random>@test.com|Test Name 1|male|active|
    |<random>@test.com|Test Name 2|female|active|