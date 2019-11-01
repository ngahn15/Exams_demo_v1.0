Feature: Testcase login
  I want to use this template for my feature file

  Background: 
    Given browse to user login page

  Scenario Outline: User Login Test
    When enter Email as "<email>" and  password as "<password>"
    And click Login button
    Then my login is "<expectedResult>" and check "<messagesError>" or "<title>"

    Examples: 
      | Testcase | email                 | password | expectedResult | messagesError               | title                          |
      |        1 | hoaps@vnpt.vn         |   123456 | successful     |                             | Bảng điều khiển cá nhân - Odoo |
      |        2 |                       |   123456 | failed         | Please fill out this field. |                                |
      |        3 | testuser1             |          | failed         | Please fill out this field. |                                |
      |        4 | ngocnga.hnn@gmail.com |   123456 | failed         | Wrong login/password        |                                |
