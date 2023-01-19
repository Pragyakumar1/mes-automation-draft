Feature: Create Synthesis Plan

  Scenario: Create synthesis plan for clonal Genes product
    Given user has create synthesis run request
    When user invoke create synthesis run api
    #Then verify status code

    Given user has created request for add started event
    When user invoke create add started event api
   # Then verify started event has successfully created

    Given user has created request for layer completion
    When user invoke create layer completion api

    Given user has created request for add completed event
    When user invoke for create add completed event

    When user invoke get ready plates for CHP Deprotection
    Then user should able to perform chp deprotection submission

    Given user invoke get plate number api
    When user has created request for generate extraction plate bar code
    Then user invoke generate extraction plate api
    And User has moved the plate from transportation to done lane