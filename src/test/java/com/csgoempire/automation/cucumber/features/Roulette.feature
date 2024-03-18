Feature: CSGOEmpire Roulette Page

	Scenario: Verify that bet amount text field is empty until an action is performed on it
	Given I am on the Roulette Page
	Then Bet amount text field contains placeholder text "Enter bet amount..."
	When I click CLEAR button
	Then Bet amount equals to "0.00"


	Scenario Outline: Verify that bet amount text field is correctly updated after bet modifications
	Given I am on the Roulette Page
	When I enter <starting value> as a bet
	And I click on "+ 0.01" bet button
	Then Bet amount equals to <after 0.01>
	When I click on "+ 0.1" bet button
	Then Bet amount equals to <after 0.1>
	When I click on "+ 1" bet button
	Then Bet amount equals to <after 1>
	When I click on "+ 10" bet button
	Then Bet amount equals to <after 10>
	When I click on "+ 100" bet button
	Then Bet amount equals to <after 100>
		When I click on "1/ 2" bet button
	Then Bet amount equals to <after 1/2>
		When I click on "X 2" bet button
	Then Bet amount equals to <after x2>
	
	Examples:
	| starting value | after 0.01 | after 0.1 | after 1   | after 10  | after 100 | after 1/2 | after x2 |
	| "-150.50"      | "-150.49"  | "-150.39" | "-149.39" | "-139.39" | "-39.39"  | "-19.70"  | "-39.39" |
	| "33.33"        | "33.34"    | "33.44"   | "34.44"   | "44.44"   | "144.44"  | "72.22"   | "144.44" |
	
	
	Scenario Outline: Verify that bet amount text field is correctly updated in negative scenarios
	Given I am on the Roulette Page
	When I enter <starting value> as a bet
	Then Bet amount equals to <expected value>
	
	Examples:
	| starting value | expected value |
	| "d" | "NaN" |
	| "1+1" | "1.00" |
	
	
	Scenario: Verify that the text of Race Rules is according to the documentation
	Given I am on the Roulette Page
	When I click on the Race Rules button
	Then Race Rules window opens with all the text being according to the documentation