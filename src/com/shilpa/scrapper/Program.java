package com.shilpa.scrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {

    public static void main(String[] args) throws FileNotFoundException,IOException,MalformedURLException {
            
       URL url = new URL("http://photobucket.com/images/harry%20potter");
        URLConnection conn = url.openConnection();
        StringBuilder builder;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line = "";
            builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            reader.close();
            System.out.println(builder.toString());
        }

        String regex;
       
        regex= "<meta property=\"(.*?)\" content=\"(.*?).jpg\" />";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(builder.toString());
        while (matcher.find()) {
            System.out.println(matcher.group());
            String imgLink = matcher.group(2);
            URL img;
            img = new URL(imgLink);
            URLConnection connec = img.openConnection();
                    try (InputStream istream = connec.getInputStream()) {
                        String[] tokens = imgLink.split("/");
                        String path = "C:\\Users\\Shilpa\\Desktop\\javaAssign\\harry potter\\";
                        try (FileOutputStream ostream = new FileOutputStream(path + tokens[tokens.length - 1])) {
                            byte[] data = new byte[1024];
                            int i = 0;
                            while ((i = istream.read(data)) != -1) {
                                ostream.write(data, 0, i);
                            }
                            ostream.close();
                        }
                    }
                
            
        }

    }

}
