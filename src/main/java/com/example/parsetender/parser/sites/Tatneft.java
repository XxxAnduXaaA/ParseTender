package com.example.parsetender.parser.sites;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Tatneft extends Thread {

    String good;
    String url = "https://etp.tatneft.ru/pls/tzp/f?p=220:562:1997614442949::::P562_OPEN_MODE,GLB_NAV_ROOT_ID,GLB_NAV_ID:,12920020,12920020";

    public Tatneft(String good) {
        this.good = good;

    }

    @Override
    public void run() {
        WebDriver driver = new ChromeDriver();

        //etp.tatneft search
        driver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Найти элемент input по его ID
        WebElement inputElement = driver.findElement(By.id("P562_SEARCH_FIELD"));

// Добавить текст в элемент

        inputElement.sendKeys(good);
        inputElement.sendKeys(Keys.ENTER);


        boolean flag = true;


        while (flag) {

            try {
                WebElement tableElement = driver.findElement(By.id("25399118176800742"));
                List<WebElement> rows = tableElement.findElements(By.tagName("tr"));

                // Проходимся по каждой строке таблицы
                for (WebElement row : rows) {
                    // Находим все ячейки в текущей строке
                    List<WebElement> cells = row.findElements(By.tagName("td"));

                    // Проходимся по каждой ячейке текущей строки
                    for (WebElement cell : cells) {
                        // Получаем текст из ячейки
                        String cellText = cell.getText();
//                        System.out.println(cell.getAccessibleName() + "\t");
                        System.out.print(cellText + "\t");
                    }
                    System.out.println();
                }


                WebElement button = driver.findElement(By.cssSelector("button[title='>']"));
                flag = true;
                System.out.println("Button is available");
                button.click();
            } catch (Exception e) {
                System.out.println("Search is end ");
                flag = false;
            }


        }


    }

}
