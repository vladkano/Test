import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters {

    private WebDriver driver;

    public Filters(WebDriver driver) {
        this.driver = driver;
    }


    By filterButton = By.xpath("//span[text()='Фильтры']");
    By allEarringsButton = By.xpath("//span[text()='Все серьги']");
    By allRingsButton = By.xpath("//span[text()='Все кольца']");
    By allNecklacesButton = By.xpath("//span[text()='Все колье']");
    By allBraceletsButton = By.xpath("//span[text()='Все браслеты']");
    By priceButton = By.xpath("//div[text()='Цена']");
    By resetButton = By.xpath("//div[@class='filters__clear']");
    By earringsButton = By.xpath("//div[text()='Серьги']");
    By ringsButton = By.xpath("//div[text()='Кольца']");
    By necklacesButton = By.xpath("//div[text()='Колье']");
    By braceletsButton = By.xpath("//div[text()='Браслеты']");


    By countHeader = By.xpath("//div[@class='filters__total-count']");


    public Filters clickToFilterButton() {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", driver.findElement(filterButton));
        return this;
    }

    public Filters clickToAllRingsButton() {
        driver.findElement(allRingsButton).click();
        return this;
    }

    public Filters clickToAllEarringsButton() {
        driver.findElement(allEarringsButton).click();
        return this;
    }

    public Filters clickToAllNecklacesButton() {
        driver.findElement(allNecklacesButton).click();
        return this;
    }

    public Filters clickToAllBraceletsButton() {
        driver.findElement(allBraceletsButton).click();
        return this;
    }

    public Filters clickToPriceButton() {
        driver.findElement(priceButton).click();
        return this;
    }

    public Filters clickToResetButton() {
        driver.findElement(resetButton).click();
        return this;
    }

    public Filters clickToEarringsButton() {
        driver.findElement(earringsButton).click();
        return this;
    }

    public Filters clickToRingsButton() {
        driver.findElement(ringsButton).click();
        return this;
    }

    public Filters clickToNecklacesButton() {
        driver.findElement(necklacesButton).click();
        return this;
    }

    public Filters clickToBraceletsButton() {
        driver.findElement(braceletsButton).click();
        return this;
    }

    public String getCountHeader() {
        return driver.findElement(countHeader).getAttribute("textContent");
    }

    //SQL
    public static String findFirstItem() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT name from item_sku " +
                "JOIN storage_stock ON storage_stock.sku_id = item_sku.id " +
                "where balance - reserve >0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(list);
        worker.getSession().disconnect();
        return list.get(1);
    }

    public static String findAnotherItem() {
        DBWorker worker = new DBWorker();
        String name;
        List<String> list = new ArrayList<>();
        String query = "SELECT name from item_sku " +
                "JOIN storage_stock ON storage_stock.sku_id = item_sku.id " +
                "where balance - reserve >0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
//                System.out.println(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        System.out.println(list);
        worker.getSession().disconnect();
        return list.get(2);
    }

    public Integer getBalance() {
        DBWorker worker = new DBWorker();
        String name;
        Integer balance, reserve, itog;
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        String query = "SELECT name, balance, reserve  from item_sku " +
                "JOIN storage_stock ON storage_stock.sku_id = item_sku.id " +
                "where balance - reserve >0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                balance = resultSet.getInt("balance");
                reserve = resultSet.getInt("reserve");
                itog = balance- reserve;
//                list.add(name);
                hashMap.put(name, itog);
//                System.out.println(name);
//                System.out.println(itog);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String firstItem = this.findFirstItem();
        Integer i = hashMap.get(firstItem);
//        System.out.println(hashMap);
        worker.getSession().disconnect();
        return i;
    }

    //Тесты запросов к базе SQL
    public static void main(String[] args) {
        DBWorker worker = new DBWorker();
        String name;
        Integer balance, reserve, itog;

        List<String> list = new ArrayList<>();
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        String query = "SELECT name, balance, reserve  from item_sku " +
                "JOIN storage_stock ON storage_stock.sku_id = item_sku.id " +
                "where balance - reserve >0";
        try {
            Statement statement = worker.getCon().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                name = resultSet.getString("name");
                balance = resultSet.getInt("balance");
                reserve = resultSet.getInt("reserve");

                itog = balance- reserve;
//                list.add(name);

                hashMap.put(name, itog);
//                System.out.println(name);
//                System.out.println(itog);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String firstItem = Basket.findFirstItem();
        Integer i = hashMap.get(firstItem);
        System.out.println(hashMap);
        System.out.println(i);
        worker.getSession().disconnect();
    }
}