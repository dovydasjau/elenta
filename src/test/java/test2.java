import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class test2 {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

//    @Test
//    public void test1(){
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.skelbiu.lt");
//    }


    @Test
    public void test2() {
        driver.get("https://elenta.lt/registracija");

        WebElement cookieButton = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]"));
        cookieButton.click(); // click the cookie button

//        for (int i = 0; i < 1000; i++) {
        String randomName = generateRandomName();//random name
        String randomEmail = generateRandomEmail();//random email

        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[1]/td[2]/input")).sendKeys(randomName); //setting the random name string to the "vartotojo vardas" text box
        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[4]/td[2]/input")).sendKeys(randomEmail); //setting the random email string to the "el. pasto adresas" text box

        String randomPassword = generateRandomPassword();//random password

        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[7]/td[2]/input")).sendKeys(randomPassword);//setting the random password string to the "slaptazodis" text box
        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[8]/td[2]/input")).sendKeys(randomPassword);//repeating the step above to the repeated password text box
        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[11]/td[2]/input")).click(); //clicking submit

        WebElement resultText = driver.findElement(By.xpath("/html/body/div[1]/div[2]/h1/b"));
        Assert.assertEquals(resultText.getText(), "Jūs sėkmingai prisiregistravote!");

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/a[1]")).click(); //clicking create listing

        Random rand = new Random();
        int randomNumber = rand.nextInt(7) + 1; // generating a random number to click a random listing group (1-7)
        String xpath = "//ul[@id='select-top-category-list']/li[" + randomNumber + "]";

        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();

        List<WebElement> listElements = driver.findElements(By.xpath("//ul[@id='select-sub-category-list']/li"));

        int count = listElements.size();

        // Generate a random number within the count
        Random rnd = new Random();
        int rndNumber = rnd.nextInt(count);

        // Build XPath for the random <li> element
        String xPath = "//ul[@id='select-sub-category-list']/li[" + (rndNumber + 1) + "]";
        // Find the element and click
        WebElement Element = driver.findElement(By.xpath(xPath));
        Element.click();

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[1]/input")).sendKeys(randomPassword); //listing name
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[2]/div[1]/span/textarea")).sendKeys("oh jeepers " + randomPassword); // listing description

        Random randm = new Random();
        int randomNum = randm.nextInt(1000) + 1;
        String randomPhoneNumber = randomPhoneNumber();

        if (randomNumber != 3) {
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[3]/input")).sendKeys(Integer.toString(randomNum)); // listing price
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[4]/div/input")).sendKeys("Kaunas"); // listing city
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // wait 3 seconds for the popup to show up
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[5]/input")).sendKeys(randomPhoneNumber); // listing phone number
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[6]/input")).sendKeys(randomEmail); // listing email
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[7]/input[2]")).click(); // clicking next button
        } else {
            try {
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[3]/div/input")).sendKeys("Kaunas"); // listing city
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // wait 3 seconds for the popup to show up
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[4]/input")).sendKeys(randomPhoneNumber); // listing phone number
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[5]/input")).sendKeys(randomEmail); // listing email
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[6]/input[2]")).click(); // clicking next button
            } catch (Exception e) {
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[3]/input")).sendKeys(Integer.toString(randomNum)); // listing price
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // wait 3 seconds for the popup to show up
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[5]/input")).sendKeys(randomPhoneNumber); // listing phone number
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[6]/input")).sendKeys(randomEmail); // listing email
                driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[7]/input[2]")).click(); // clicking next button
            }

        }

//        driver.findElement(By.xpath("/html/body/div[4]")).click(); //clicking on the city

        WebElement fileInput = driver.findElement(By.xpath("/html/body/div[1]/form[1]/div[1]/label/input[1]")); //selecting "Ikelkite nuotraukas"

        String filePath = "C:/Users/David/Desktop/pics/dkycTe4.png"; // Changing to the path of the image file
        fileInput.sendKeys(filePath);

        driver.findElement(By.xpath("/html/body/div[1]/div[5]/input[2]")).click(); // clicking next button
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/input[2]")).click(); // clicking the next button

        driver.findElement(By.xpath("/html/body/div[1]/ul/li/div/h3/a")).click(); // opening the listing
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        resultText = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/a"));
        Assert.assertEquals(resultText.getText(), randomPhoneNumber);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        resultText = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[3]"));
        Assert.assertEquals(resultText.getText(), "Oh jeepers " + randomPassword);

        System.out.println(driver.getCurrentUrl()); // printing the listings link

//                }


//        driver.get("https://elenta.lt/prisijungti?returnurl=https%3A%2F%2Felenta.lt%2F"); //opening the login page
//
//        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[1]/td[2]/input")).sendKeys(randomName); //setting the random name string to the username text box in the login page
//        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[3]/td[2]/input")).sendKeys(randomPassword); //setting the random password string to the password text box in the login page
//
//        driver.findElement(By.xpath("/html/body/div[1]/form/fieldset/table/tbody/tr[4]/td[2]/input")).click(); //clicking on the "prisijungti" button

//        driver.get("https://elenta.lt");
//        driver.findElement(By.xpath("//*[@id=\"searchKeyword\"]")).sendKeys("traktorius");// operacija nekuriant kintamojo.
//        driver.findElement(By.xpath("//*[@id=\"searchButton\"]")).click();
    }


//    @Test
//    public void test3(){//jei atidaro teisingai pakūrėme projektą
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.elenta.lt/prisijungti?returnurl=https%3A%2F%2Felenta.lt%2F");
//
//        driver.findElement(By.id("")).click();
//        driver.findElement(By.id("searchKeyword")).sendKeys("traktorius");// operacija nekuriant kintamojo.
//        // //*[@id="searchKeyword"]  paltginti dalinio xpath struktūrą su id. iš xpath matosi, kad elementas turi savo id.
//        driver.findElement(By.id("searchButton")).click();
//
//    }


    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "example.com"};
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};

        Random random = new Random();

        StringBuilder email = new StringBuilder();

        // Generate username part
        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            email.append(randomChar);
        }

        // Adding '@' symbol
        email.append("@");

        // Selecting random domain
        String randomDomain = domains[random.nextInt(domains.length)];
        email.append(randomDomain);

        return email.toString();
    }

    public static String generateRandomName() {
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};
        Random random = new Random();

        StringBuilder username = new StringBuilder();

        int usernameLength = random.nextInt(10) + 5; // Random length between 5 to 14 characters
        for (int i = 0; i < usernameLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            username.append(randomChar);
        }
        return username.toString();
    }

    public static String generateRandomPassword() {
        String[] characters = {"abcdefghijklmnopqrstuvwxyz", "0123456789"};
        Random random = new Random();

        StringBuilder password = new StringBuilder();

        int passwordLength = random.nextInt(9) + 6; // Random length between 6 to 14 characters
        for (int i = 0; i < passwordLength; i++) {
            String characterSet = characters[random.nextInt(2)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            password.append(randomChar);
        }
        return password.toString();

    }

    public static String randomPhoneNumber() {
        String[] characters = {"0123456789"};
        Random random = new Random();

        StringBuilder phoneNumber = new StringBuilder("+370");

        int phoneLength = 11;
        for (int i = 3; i < phoneLength; i++) {
            String characterSet = characters[random.nextInt(1)]; // Selecting either alphabets or numbers
            char randomChar = characterSet.charAt(random.nextInt(characterSet.length()));
            phoneNumber.append(randomChar);
        }

        return phoneNumber.toString();
    }
}
