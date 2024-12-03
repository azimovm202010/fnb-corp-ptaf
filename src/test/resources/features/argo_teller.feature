@Argo_Teller
Feature: Argo_Teller

  Background: Navigate to Teller URL
    Given we navigate to Argo_Teller_qaEnvUrl url
    When we select on main frame login locator location_flt value "L1"
    Then we enter value on main frame login locator cashbox_flt value "77"
    When we click on main frame login locator sign_on_btn
    Then we click on warning-box frame login locator ok_btn
    And we click on warning-box frame login locator yes_btn

  Scenario: Argo_Teller General Ledger Enhancement Debit Selection
    Given get title of page
    Then we click on second frame landing locator body
    Then we wait for some time
    When we click on second frame landing locator general_ledger_btn
    Then we wait for some time
    Then we check on second frame general_ledger_page locator debit_radio_btn
    Then we click on warning-box frame general_ledger_page locator cancel_btn
    And we wait for some time
    And we select on second frame general_ledger_page locator debit_title_flt value "Cash Items"
    Then we enter value on second frame general_ledger_page locator debit_description_flt value "TEST DESCRIPTION"
    When we enter value on second frame general_ledger_page locator cash_amount_flt value "100"
    And we capture screenshot on second frame general_ledger_page locator body name "defects/ADO_6606/debit description field"
    Then we click on second frame general_ledger_page locator ok_btn
    And we wait for some time
    When we click on second frame sessionSummaryPage locator EJ_btn
    And we wait for some time
    Then we click on pop-up frame sessionSummaryPage locator imagDetail_flt
    And we click on pop-up frame sessionSummaryPage locator view_btn
    And we wait for some time
    Then we click on second pop-up frame sessionSummaryPage locator dr_selection
    And we wait for some time
    And we capture screenshot on second pop-up frame sessionSummaryPage locator virtual_check_flt name "defects/ADO_6606/debit_virtual_check"
    Then we click on second pop-up frame sessionSummaryPage locator close_btn
    Then we click on pop-up frame sessionSummaryPage locator close_btn
    Then we click on second frame sessionSummaryPage locator general_ledger_btn
    Then we check on second frame general_ledger_page locator credit_radio_btn
    Then we click on warning-box frame general_ledger_page locator cancel_btn
    And we wait for some time
    And we select on second frame general_ledger_page locator credit_title_flt value "Cash Items"
    Then we enter value on second frame general_ledger_page locator credit_description_flt value "TEST DESCRIPTION"
    And we enter value on second frame general_ledger_page locator total_cash_amount_flt value "100"
    And we enter value on second frame general_ledger_page locator session_funds_amount_flt value "100"
    And we capture screenshot on second frame general_ledger_page locator body name "defects/ADO_6606/credit description field"
    Then we click on second frame general_ledger_page locator ok_btn
    When we click on second frame sessionSummaryPage locator EJ_btn
    And we wait for some time
    Then we click on pop-up frame sessionSummaryPage locator imagDetail_flt
    And we click on pop-up frame sessionSummaryPage locator view_btn
    When we wait for some time
    And we capture screenshot on second pop-up frame sessionSummaryPage locator virtual_check_flt name "defects/ADO_6606/credit_virtual_check"
    Then we click on second pop-up frame sessionSummaryPage locator close_btn
    Then we click on pop-up frame sessionSummaryPage locator close_btn
    And we check on second frame landing locator no_recipe_checkbox
    Then we click on second frame sessionSummaryPage locator general_ledger_btn
    Then we wait for some time
    Then we check on second frame general_ledger_page locator GL_to_GL_radio_btn
    Then we click on warning-box frame general_ledger_page locator cancel_btn
    And we wait for some time
    And we select on second frame general_ledger_page locator credit_title_flt value "Cash Items"
    Then we enter value on second frame general_ledger_page locator credit_description_flt value "TEST DESCRIPTION"
    And we enter value on second frame general_ledger_page locator GL_credit_amount value "100"
    And we select on second frame general_ledger_page locator debit_title_flt value "Cash Items"
    Then we enter value on second frame general_ledger_page locator debit_description_flt value "TEST DESCRIPTION"
    When we enter value on second frame general_ledger_page locator GL_debit_amount value "100"
    And we capture screenshot on second frame general_ledger_page locator body name "defects/ADO_6606/GL-GL field"
    Then we click on second frame general_ledger_page locator ok_btn
    When we click on second frame sessionSummaryPage locator EJ_btn
    And we wait for some time
    Then we click on pop-up frame sessionSummaryPage locator imagDetail_flt
    And we click on pop-up frame sessionSummaryPage locator view_btn
    And we wait for some time
    And we capture screenshot on second pop-up frame sessionSummaryPage locator virtual_check_flt name "defects/ADO_6606/GL-GL virtual_check"
    Then we click on second pop-up frame sessionSummaryPage locator close_btn
    Then we click on pop-up frame sessionSummaryPage locator close_btn
    And we check on second frame landing locator no_recipe_checkbox
    When we click on second frame landing locator end_session_btn
    And we click on second frame landing locator body
    Then we click on warning-box frame landing locator sign_out_stop_btn
    Then we click on warning-box frame landing locator yes_btn
    Then we click on warning-box frame landing locator yes_btn


