package com.example.parsetender.parser.sites;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TenderPro extends Thread {
    String good;
    String url = "https://www.tender.pro/api/tenders/list";

    public TenderPro(String good) {
        this.good = good;

    }

    @Override
    public void run() {
        WebDriver driver = new ChromeDriver();

        //Tender.pro search
        driver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement inputElement = driver.findElement(By.id("good_name_id"));

// Добавить текст в элемент

        inputElement.sendKeys(good);
        inputElement.sendKeys(Keys.ENTER);

        boolean flag = true;

        while (flag) {

            try {
                WebElement tableElement = driver.findElement(By.className("table table-bordered table-striped table-hover table-stat search-page__table"));
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


                WebElement button = driver.findElement(By.className("pagination__link pagination__link_next"));
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
