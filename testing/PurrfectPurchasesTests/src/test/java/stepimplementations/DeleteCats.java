package stepimplementations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import runners.Runner;

import static runners.Runner.driver;

public class DeleteCats {
    @When("User clicks delete button for cat")
    public void user_clicks_delete_button_for_cat() throws InterruptedException {
        WebElement catDelete = driver.findElement(By.xpath("//tr[last()]/td[9]//img"));
        String catID = driver.findElement(By.xpath("//tr[last()]/td[1]")).getText();
        Runner.adminPage.setDeleteID(catID);
        catDelete.click();
        Thread.sleep(500);
    }
    @Then("User should see alert delete confirmation")
    public void user_should_see_alert_delete_confirmation() throws InterruptedException {
        String conf = "Do you wish to delete this cat?";
        Thread.sleep(500);
        boolean isPresent = driver.switchTo().alert().getText().equals(conf);
        if (isPresent) driver.switchTo().alert().accept();
        System.out.println(Runner.adminPage.getDeleteID());
        Thread.sleep(1000);
        Assert.assertEquals(isPresent, true, "Error: Cat not confirmed to be added!");
    }
    @Then("User should NOT see cat with <catID> in table")
    public void user_should_not_see_cat_with_cat_id_in_table() {
        boolean isPresent = driver.findElements(By.xpath("//tr/td[text()='" + Runner.adminPage.getDeleteID() + "']")).size() > 0;
    }
}
