package Model;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(id="email") public WebElement username;
	@FindBy(id="pass") public WebElement password;
	@FindBy(id="u_0_b") public WebElement signInBtn;
}
