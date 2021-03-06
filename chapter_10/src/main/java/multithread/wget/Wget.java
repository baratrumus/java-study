package multithread.wget;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Ilya Ivannikov (voldores@mail.ru)
 * @version $Id$
 * @since 0.1

/***
 Нужно написать консольную программу аналог Wget.
 Программа должна скачивать файл из сети с ограничением по скорости скачки.
 например Wget url 200
 Wget (ссылка) (скорость в килобайтах в секунту)
 для того. чтобы ограничить скорость скачки нужно проверять сколько загрузилось за 1 секунду.
 если объем больше. то нужно выставлять паузу.
 Для скачивания файлу используйте HttpConnection.java.
 Тесты писать на этот код не нужно.
 */

public class Wget implements Runnable {
    int speedLimit;
    URL url;
    private final static String LN = System.lineSeparator();
    String separator = File.separator;

    public Wget(int speedLimit, URL url) {
        this.speedLimit = speedLimit;
        this.url = url;
    }

    @Override
    public void run() {
        String fileName = "";
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String request = conn.getRequestMethod();
            int code = conn.getResponseCode();
            String response = conn.getResponseMessage();
            Map<String, List<String>> headers  = conn.getHeaderFields();
            System.out.println(LN);
            System.out.println("url: " + url.toString());
            System.out.println("request method: " + request);
            System.out.println("response code: " + code);
            System.out.println("response message: " + response);
            /*System.out.println("response headers: ");
            Set<String> headerKeys = headers.keySet();
            for (String k : headerKeys) {
                System.out.println(k + " : " + headers.get(k));
            }*/
            if (code != HttpURLConnection.HTTP_OK) {
                System.out.println("Wrong server's response code: " + code);
                return;
            }
            String disposition = conn.getHeaderField("Content-Disposition");
            int index;
            if (disposition != null) {
                index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 9);
                }
            } else {
                fileName = url.getFile();
                index = fileName.lastIndexOf("/");
                fileName = fileName.substring(index + 1);
            }
            System.out.println("Имя файла : " + fileName);

            String dirString = System.getProperty("java.io.tmpdir")  + "WgetFiles" + separator;
            File dir = new File(dirString);
            if (!dir.exists()) {
                dir.mkdir();
            }

            try (BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
                FileOutputStream fos = new FileOutputStream(dirString + fileName)) {
                int bytesCount;
                long timeLost;
                int bytesCountAdd = 0;
                int allBytes = 0;
                long oneSecondCount = System.currentTimeMillis();
                byte[] data = new byte[1024];
                while ((bytesCount = in.read(data, 0, 1024)) != -1) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                    bytesCountAdd += bytesCount;
                    allBytes += bytesCount;
                    timeLost = System.currentTimeMillis() - oneSecondCount;
                    if ((bytesCountAdd) > speedLimit) {
                        if (timeLost < 1000L) {
                            try {
                                Thread.sleep(1000L - timeLost);
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        oneSecondCount = System.currentTimeMillis();
                        bytesCountAdd = 0;
                        System.out.printf("%d Kb recieved \r", allBytes / 1000);
                    }
                    fos.write(data, 0, bytesCount);
                }
                System.out.printf("%d Kb recieved", allBytes / 1000);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                conn.disconnect();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
