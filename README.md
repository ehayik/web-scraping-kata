## Web Scrapping Kata

## Description

Web scraping is a technique used to extract large amounts of data from websites whereby the data is
extracted and saved to a local file in your computer or to a database in a tabular form.
Here is how a typical web scraping process works:

1. A web scraper sends a GET request to the URL that you have instructed it to scrape.
2. The server responds to the request by returning the HTML content of the webpage.
3. The web scraper then parses the HTML content of the page, filters out the data it needs,
   and saves this data into a format you want.

This project illustrates Web Scrapping challenges and concepts, showing how to:

- Set up a Selenium web scrapping java.
- Set up web scrapping service docker image using _Firefox_, _GeckoDriver_, and [Spring Boot layered Jar](https://www.baeldung.com/spring-boot-docker-images#layered-jars).
- Automate WebDriver management using [WebDriverManager](https://github.com/bonigarcia/webdrivermanager).
- Manage WebDriver instances in a multi-threaded environment.
- Implement WebDriver pooling manager using Apache Common pool.
- Implement Page Object Pattern.

## Getting Started

### Prerequisites

- Java JDK version 17.
- An IDE that supports Java and Spring MVC development, such as IntelliJ IDEA
- Maven
- Docker

> **Note:** 
> Chrome browser compatible with latest Selenium _ChromeDriver_ version is required, to run standalone.

### Installing

1. Clone the repository `git clone git@github.com:ehayik/web-scrapping-kata.git`
2. Open the project folder.
3. Run `mvn clean compile` to install the necessary dependencies from the POM file.

### Running standalone

1. Run `mvn spring-boot:run -Dspring-boot.run.profiles=standalone`

> **Note:** 
> The web scrapper will use the _Chrome_ browser installed in your machine, and latest _ChromeDriver_ version will be downloaded.

### Running container

1. Run `mvn clean package` to package application in jar file.
2. Run `docker build -t web-scrapping-kata:0.0.1 .`  to build your Docker image.
3. Run `docker run -d -p 8080:8080 --name web-scrapping-kata  web-scrapping-kata:0.0.1` to run it.

> **Note:** 
> The web scrapper will use the _Firefox_ browser installed in your container, and latest _Gecko Driver_ version will be downloaded.

### Usage

### GET scrapeme-shop/products

Returns products listed in Scrapeme Shop website.

Example request: 

```bash
curl -X GET http://localhost:8080/scrapeme-shop/products
```

Example response:

```json
[
   {
      "url": "https://scrapeme.live/shop/Bulbasaur/",
      "image": "https://scrapeme.live/wp-content/uploads/2018/08/001-350x350.png",
      "name": "Bulbasaur",
      "price": "£63.00"
   }
]
```
