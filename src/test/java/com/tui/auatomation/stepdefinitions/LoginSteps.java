package com.tui.auatomation.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    @Given("the app is launched")
    public void the_app_is_launched() {
        System.out.println("App is launched");
    }
    @Given("user is in {string} screen")
    public void user_is_in_screen(String string, io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        System.out.println("testing");
    }
    @When("user clicks SignIn button")
    public void user_clicks_sign_in_button() {
        System.out.println("testing");;
    }
    @Then("user sees Products screen")
    public void user_sees_products_screen() {
        System.out.println("testing");
    }
}
