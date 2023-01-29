# Leet Scraper

A simple command line tool to recursively crawl a given website.
It's simple in the sense that it has only been tested for `http://books.toscrape.com/index.html`
(with this save-file implementation the `index.html` part is actually important),
although it will be able to crawl through essentially any website the local version will in general not function
properly.

The collected files will be saved to the user home directory, for example on windows `C:\Users\eahlb\books.toscrape.com`

The main idea behind the design in to make the running and data collecting general enough to make it possible
to implement any strategy, driver, to collect the data.
For now there exist the different drivers implemented (see below),
for the ambitious reader a reactive driver would be interesting to compare with the parallel driver.

## Build

Build jar-file with maven, or run from your favorite IDE.

Note: included with the test-suite is a benchmark-test for each available driver type, which will take quite some time
to run.

## Run

Two parameters are required to run the crawler, default values are shown below

1. Driver type (default PARALLEL)
2. URL to scrape (default `http://books.toscrape.com/index.html`)

Example:

```
> java -jar .\LeetScraper-1.0-SNAPSHOT-jar-with-dependencies.jar PARALLEL http://books.toscrape.com/index.html
```

### Available driver types

* SIMPLE
* RECURSIVE
* PARALLEL