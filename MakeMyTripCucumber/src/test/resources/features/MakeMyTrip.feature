Feature: Flight Search on MakeMyTrip

  Scenario: Search for a round-trip flight from HYD to MAA
    Given I launch Chrome browser and open MakeMyTrip website
    And I click on Flights and select ROUND TRIP
    And I enter FROM location as "Hyderabad" and TO location as "Chennai"
    And I select DEPARTURE and RETURN dates
    When I click on the Search button
    Then I should see the Search Results page
