Feature: Register Technician

  Scenario Outline: As a Technician i want to register in LineRepair.
    Given I want to register as technician
    And I enter my own information like username <username>,names <names>, lastNames <lastNames>, email <email>, password <password>, address <address>,cellPhoneNumber<cellPhoneNumber>
    Then I should be able to see my newly account

    Examples:
      | username         | names     | lastNames   | email              | password     | address       | cellPhoneNumber       |
      | "Carlos123"      | "Carlos"  | "Leon"      | "carlos@gmail.com" | "carlos123"  | "La Victoria" | "942121545"           |