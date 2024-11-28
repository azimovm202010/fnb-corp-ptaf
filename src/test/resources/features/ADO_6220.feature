@ADO_6220 @release_1_060
Feature: ADO_6220

  Background: Navigate to Teller URL
    Given we navigate to Argo_Teller_qaEnvUrl url
#    Then we select on frame login locator location_flt value "L1"
#    And we enter value on frame login locator cashbox_flt value "77"
#    Then we click on frame login locator sign_on_btn
    When we select on main frame login locator location_flt value "L1"
    Then we enter value on main frame login locator cashbox_flt value "77"
    When we click on main frame login locator sign_on_btn
    Then we click on warning-box frame login locator ok_btn
    And we click on warning-box frame login locator yes_btn

  Scenario Outline: ADO 2821 - 902 Canadian Checks not showing on EJ's
#    Then we wait for some time
#    Then we wait for some time
##    Then we click on frame login locator sign_on_btn
#    Given get title of page
#    Then we click on frame landing locator search_btn
#    Then we wait for some time
    Then we wait for some time
    Then we click on second frame landing locator body
#    Then we wait for some time
    When we click on second frame landing locator search_btn
    Then we select on second frame landing locator search_bank_flt value "0009"
    And we select on second frame landing locator select_search_by_flt value "A"
    Then we click on second frame landing locator account_number_flt
#    And we enter value on second frame landing locator account_number_flt value "200221"
#    When we click on second frame searchPage locator search_btn
#    Then we click on second frame searchPage locator select_btn
#    Then we click on second frame landing locator body
#    And we click on second frame landing locator deposit_btn
#    And we wait for some time
#    And we enter value on second frame landing locator account_number_flt value "<AccountOne>"
#    And we wait for some time
#    When we enter value on second frame deposit locator total_dep_amount_flt value "<TotalDeposit>"
#    Then we click on second frame landing locator body
#    When we click on second frame deposit locator ok_btn
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator account_number_flt value "<AccountTwo>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator amount_flt value "<TotalDeposit>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator serial_number_flt value "<SerialNumber>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator tran_flt value "<Tran>"
#    Then we click on pop-up frame deposit locator body
#    Then we enter value on pop-up frame deposit locator routing_flt value "<Routing>"
#    Then we click on pop-up frame deposit locator body
#    And we capture screenshot on main frame deposit locator body name "defects/ADO_6220/before_deposit1"
#    And we click on pop-up frame deposit locator ok_btn
#    And we wait for some time
#    And we click on second frame landing locator deposit_btn
#    And we wait for some time
#    And we enter value on second frame landing locator account_number_flt value "<AccountOne>"
#    And we wait for some time
#    When we enter value on second frame deposit locator total_dep_amount_flt value "<TotalDeposit>"
#    Then we click on second frame landing locator body
#    When we click on main frame deposit locator image_detail_tab
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator account_number_flt value "<AccountTwo>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator amount_flt value "<TotalDeposit>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator serial_number_flt value "<SerialNumber>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator tran_flt value "<Tran>"
#    Then we click on pop-up frame deposit locator body
#    Then we enter value on pop-up frame deposit locator routing_flt value "<Routing>"
#    Then we click on pop-up frame deposit locator body
#    And we capture screenshot on main frame deposit locator body name "defects/ADO_6220/before_deposit2"
#    And we click on pop-up frame deposit locator ok_btn
#    And we wait for some time
#    And we check on second frame landing locator no_recipe_checkbox
#    Then we capture screenshot on main frame deposit locator body name "defects/ADO_6220/after_deposit1"
#    When we click on second frame landing locator end_session_btn
#    And we click on second frame landing locator deposit_btn
#    And we wait for some time
#    And we enter value on second frame landing locator account_number_flt value "<AccountOne>"
#    And we wait for some time
#    When we enter value on second frame deposit locator total_dep_amount_flt value "<TotalDeposit>"
#    Then we click on second frame landing locator body
#    When we click on second frame deposit locator ok_btn
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator account_number_flt value "<AccountTwo>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator amount_flt value "<TotalDeposit>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator serial_number_flt value "<SerialNumber>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator tran_flt value "<Tran>"
#    Then we click on pop-up frame deposit locator body
#    Then we enter value on pop-up frame deposit locator routing_flt value "<Routing>"
#    Then we click on pop-up frame deposit locator body
#    And we capture screenshot on main frame deposit locator body name "defects/ADO_6220/before_deposit3"
#    And we click on pop-up frame deposit locator ok_btn
#    And we wait for some time
#    And we click on second frame landing locator deposit_btn
#    And we wait for some time
#    And we enter value on second frame landing locator account_number_flt value "<AccountOne>"
#    And we wait for some time
#    When we enter value on second frame deposit locator total_dep_amount_flt value "<TotalDeposit>"
#    Then we click on second frame landing locator body
#    When we click on main frame deposit locator image_detail_tab
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator account_number_flt value "<AccountTwo>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator amount_flt value "<TotalDeposit>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator serial_number_flt value "<SerialNumber>"
#    Then we click on pop-up frame deposit locator body
#    And we enter value on pop-up frame deposit locator tran_flt value "<Tran>"
#    Then we click on pop-up frame deposit locator body
#    Then we enter value on pop-up frame deposit locator routing_flt value "<Routing>"
#    Then we click on pop-up frame deposit locator body
#    And we capture screenshot on main frame deposit locator body name "defects/ADO_6220/before_deposit4"
#    And we click on pop-up frame deposit locator ok_btn
#    And we wait for some time
#    And we check on second frame landing locator no_recipe_checkbox
#    Then we capture screenshot on main frame deposit locator body name "defects/ADO_6220/after_deposit2"
#    When we click on second frame landing locator end_session_btn
#    And we click on second frame landing locator body
#    Then we click on warning-box frame landing locator sign_out_stop_btn
#    Then we click on warning-box frame landing locator yes_btn
#    Then we click on warning-box frame landing locator yes_btn

    Examples:
      | AccountOne | TotalDeposit | AccountTwo | Tran | Routing   | SerialNumber |
      | 200221     | 100          | 55884419   | 45   | 56792-241 | 00019147     |