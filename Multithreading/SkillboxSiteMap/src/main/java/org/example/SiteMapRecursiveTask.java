package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SiteMapRecursiveTask extends RecursiveTask<SortedSet<String>> {

    private String url;
    private volatile SortedSet<String> urlList = new TreeSet<>();
    private volatile HashSet<String> links = new HashSet<>();

    public SiteMapRecursiveTask(String url) {
        this.url = url;
    }

    @Override
    protected SortedSet<String> compute() {

        Document document = null;
        try {
            Thread.sleep(150);
            document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36")
                    .referrer("https://www.google.com")
                    .followRedirects(true)
                    .get();
        } catch (InterruptedException | IOException e) {
            if (e.getMessage().contains("Status=403")) {
                urlList.remove(url);
            }
        }

        HashSet<SiteMapRecursiveTask> siteList = new HashSet<>();
        Elements elements = document.select("a[href]");

        String regex = "^/[a-z0-9-/]+[^#]";
        Pattern pattern = Pattern.compile(regex);

        for (Element element : elements) {

            String link = element.attr("href");
            Matcher matcher = pattern.matcher(link);
            if (!matcher.find()) {
                continue;
            }

            if (link.contains("/#")){
                continue;
            }

            if (!link.contains("https://") && !links.contains(link)) {
                link = url + link;
                urlList.add(link);
            }

            if (urlList.contains(link)) {
                continue;
            }

            SiteMapRecursiveTask task = new SiteMapRecursiveTask(link);
            task.fork();
            siteList.add(task);
        }

        for (SiteMapRecursiveTask task : siteList) {
            urlList.add(task.join().toString());
        }
        return urlList;
    }
}
