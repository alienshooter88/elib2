package com.exmple.elib;

import com.exmple.elib.entity.Work;
import com.exmple.elib.repos.WorkRepo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class GreetingController {

    @Autowired
    private WorkRepo workRepo;

    public String lineWorks = "";
    public String compare="2";
    public String text = "";


    public FirefoxDriver driver;
    public void getData(){
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        System.out.println("DRIVER OK");

        driver.get("http://intra.elibrary.ru/projects/articulus/Login.aspx");
        try {
            Thread.sleep(500);
        }
        catch (Exception e){

        }

        WebElement user = driver.findElementById("Articulus_Login_UserName");
        user.sendKeys("xioc");
        try {
            Thread.sleep(500);
        }
        catch (Exception e){

        }
        WebElement password = driver.findElementById("Articulus_Login_Password");
        password.sendKeys("elibraryoksana");
        try {
            Thread.sleep(500);
        }
        catch (Exception e){

        }
        WebElement login = driver.findElementByName("Articulus_Login$LoginButton");
        login.click();
        driver.get("http://intra.elibrary.ru/projects/articulus/IdentityAuthors.aspx?pid=397718");

        List<WebElement> elements = driver.findElements(By.cssSelector("tbody > tr:nth-child(n+269)"));
        int num=0;
        int row=0;
        int temp=2;
        for(WebElement e:elements){
            WebElement rowspan = e.findElement(By.cssSelector("td"));
            if (rowspan.getAttribute("rowspan")!=null){
                row = Integer.parseInt(rowspan.getAttribute("rowspan"));
                WebElement number = e.findElement(By.cssSelector("td a"));
                num = Integer.parseInt(number.getText());
                System.out.print(num+".1"+" ");
                WebElement element = e.findElement(By.cssSelector("td:nth-child(3) p"));
                System.out.print(element.getText());
                WebElement button = e.findElement(By.cssSelector("td:last-child"));
                button.click();
                try {
                    Thread.sleep(500);
                }
                catch (Exception r){

                }
                getWorks();

                String[] worksTemp = lineWorks.split(";");
                int worktrue = 0;
                for (int i=0; i<worksTemp.length; i++){
                    if (worksTemp[i].toLowerCase().contains(element.getText().toLowerCase())){
                        worktrue = i+1;
                        compare="1";
                    }
                }

                Work work = new Work(num+".1",element.getText(),lineWorks,worktrue,compare);
                workRepo.save(work);
                temp = 2;
                compare="2";
            }
            else {
                if(temp<=row){
                    System.out.print(num+"."+temp+" ");
                    WebElement element = e.findElement(By.cssSelector("td:nth-child(2) p"));
                    System.out.print(element.getText());
                    WebElement button = e.findElement(By.cssSelector("td:last-child"));
                    button.click();
                    try {
                        Thread.sleep(500);
                    }
                    catch (Exception r){

                    }
                    getWorks();
                    String[] worksTemp = lineWorks.split(";");
                    int worktrue = 0;
                    for (int i=0; i<worksTemp.length; i++){
                        if (worksTemp[i].toLowerCase().contains(element.getText().toLowerCase())){
                            worktrue = i+1;
                            compare="1";
                        }
                    }

                    Work work = new Work(num+"."+temp,element.getText(),lineWorks,worktrue,compare);
                    workRepo.save(work);
                    temp++;
                    compare="2";
                }

            }

        }

    }

    public void getWorks(){
        WebElement notworks = driver.findElement(By.cssSelector("#popup_result ul span"));
        lineWorks = "";
        if (notworks.getText().equals("автор не найден")){
            lineWorks = lineWorks+notworks.getText()+";";
            compare = "3";
            System.out.println(" "+notworks.getText());
            try {
                Thread.sleep(200);
            }
            catch (Exception e){

            }
        }
        else{
            List<WebElement> works = driver.findElements(By.cssSelector("#popup_result ul > li"));
            System.out.print(works.size());
            for (WebElement w: works){
                WebElement span = w.findElement(By.cssSelector("span"));

                System.out.println(" "+span.getText());
                lineWorks = lineWorks+span.getText()+";<br>";
            }
            System.out.println();
        }
        WebElement popupcancel = driver.findElement(By.cssSelector("#popup_cancel"));
        popupcancel.click();

        try {
            Thread.sleep(200);
        }
        catch (Exception e){

        }
    }


    public void setCompare(){
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver/geckodriver.exe");
        driver = new FirefoxDriver();
        System.out.println("DRIVER OK");

        driver.get("http://intra.elibrary.ru/projects/articulus/Login.aspx");
        try {
            Thread.sleep(500);
        }
        catch (Exception e){

        }

        WebElement user = driver.findElementById("Articulus_Login_UserName");
        user.sendKeys("xioc");
        try {
            Thread.sleep(500);
        }
        catch (Exception e){

        }
        WebElement password = driver.findElementById("Articulus_Login_Password");
        password.sendKeys("elibraryoksana");
        try {
            Thread.sleep(500);
        }
        catch (Exception e){

        }
        WebElement login = driver.findElementByName("Articulus_Login$LoginButton");
        login.click();
        driver.get("http://intra.elibrary.ru/projects/articulus/IdentityAuthors.aspx?pid=397718");

        String[] list = text.split(",");
        Iterable<Work> iterable = workRepo.findAll();

        for (String s: list){
            for (Work w: iterable){
                if (Long.parseLong(s)==w.getId()){
                    int temp2 = 268+Integer.parseInt(s);
                    WebElement findElement = driver.findElementByCssSelector("tbody > tr:nth-child("+temp2+")");
                    WebElement ident = findElement.findElement(By.cssSelector("td:last-child"));
                    ident.click();
                    try {
                        Thread.sleep(500);
                    }
                    catch (Exception e){

                    }
                    WebElement work = driver.findElement(By.cssSelector("#popup_result ul li:nth-child("+w.getWork()+")"));
                    work.click();
                    try {
                        Thread.sleep(500);
                    }
                    catch (Exception e){

                    }
                    WebElement save = driver.findElement(By.id("popup_ok"));
                    save.click();
                    try {
                        Thread.sleep(500);
                    }
                    catch (Exception e){

                    }
                }
            }
        }




    }






    public GreetingController() {
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        Iterable<Work> iterable = workRepo.findAll();
        model.put("works",iterable);
        return "elib";
    }

    @PostMapping("getdata")
    public String getdata(Map<String, Object> model){
        getData();
        System.out.println("ОК");
        return "elib";
    }

    @PostMapping("run")
    public String run(@RequestParam String textarea, Map<String, Object> model){
        System.out.println(textarea);
        text=textarea;
        Iterable<Work> iterable = workRepo.findAll();
        model.put("works",iterable);
        setCompare();
        return "elib";
    }


}


