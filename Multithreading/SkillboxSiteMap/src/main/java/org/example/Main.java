package org.example;

import java.io.FileOutputStream;
import java.util.SortedSet;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static void main(String[] args) {
        String url = "https://tvoe.ru/";
        SortedSet<String> list;
        String path_to_file = "src/main/resources/sitemap.txt";

        try {
            list = new ForkJoinPool().invoke(new SiteMapRecursiveTask(url));
            FileOutputStream stream = new FileOutputStream(path_to_file);
            stream.write((url + "\n").getBytes());

            for (String link : list) {
                String tab = "\t";
                String urlFile = "";

                link = link.substring(url.length() + 1, link.length() - 1);
                String[] subLink = link.split("/");

                for (int i = 1; i <= subLink.length; i++) {
                    urlFile = urlFile.concat(tab.repeat(i));
                }
                urlFile = urlFile.concat(url + link + "/" + "\n");
                stream.write(urlFile.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}