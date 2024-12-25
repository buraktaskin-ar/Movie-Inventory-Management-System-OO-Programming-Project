package comx;

import constant.CsColors;
import enums.Direction;
import models.Actor;
import models.Movie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieInventory {
    private static final String DATA_SOURCE_FOLDER = "data";
    private static final String DATA_FILENAME = "bilgiler.txt";
    private static MovieList movieList;
    private static Scanner scanner;

    public static void main(String[] args) {
        movieList = new MovieList();

        readData();

        scanner = new Scanner(System.in);
        int chosen = -1;

        do {
            System.out.println("1) Filmi envantere ekle");
            System.out.println("2) Filmi envanterden sil");
            System.out.println("3) Film bilgilerini görüntüle");
            System.out.println("4) Tüm kayıtları baştan sona yazdır");
            System.out.println("5) Tüm kayıtları sondan başa yazdır");
            System.out.println("6) 2000 yılından önceki filmleri yazdır");
            System.out.println(CsColors.RED + "7) Çıkış Yap" + CsColors.RESET);

            System.out.print(CsColors.YELLOW_BOLD_BRIGHT +  "Seçiminizi yapın: " + CsColors.RESET);

            try {
                chosen = scanner.nextInt();
            } catch (InputMismatchException e){
                printNumberException();
                scanner.next();
                continue;
            }

            switch (chosen) {
                case 1 -> { // Envantere film ekleme
                    int year;
                    System.out.print("Yapım yılı: ");
                    try {
                        year = scanner.nextInt();
                    } catch (InputMismatchException e){
                        printNumberException();
                        scanner.next();
                        continue;
                    }
                    scanner.nextLine();
                    System.out.print("Film adı: ");
                    String title = scanner.nextLine();
                    System.out.print("Film türü: ");
                    String genre = scanner.nextLine();
                    System.out.print("Yönetmen adı: ");
                    String director = scanner.nextLine();
                    ArrayList<Actor> actors = new ArrayList<>();
                    String actorName, actorGender, actorCountry;
                    do {
                        System.out.print("Oyuncu adı (Çıkmak için 'q' tuşuna basın): ");
                        actorName = scanner.nextLine();

                        if (!actorName.equals("q")) {
                            System.out.print("Oyuncu cinsiyeti: ");
                            actorGender = scanner.nextLine();
                            System.out.print("Oyuncunun Ülkesi: ");
                            actorCountry = scanner.nextLine();

                            Actor actor = new Actor(actorName, actorGender, actorCountry);
                            actors.add(actor);
                        }
                    } while (!actorName.equals("q"));

                    Movie newMovie = new Movie(year, title, genre, director, actors);
                    movieList.add(newMovie);
                    System.out.println(CsColors.GREEN_BRIGHT + "\nFilm envantere eklendi." + CsColors.RESET);
                }

                case 2 -> { // Envanterden film silme
                    System.out.print("Silmek istediğiniz film adını giriniz: ");
                    scanner.nextLine();
                    String query = scanner.nextLine();
                    movieList.remove(query);
                }

                case 3 -> { // Envanterde film ara
                    System.out.print("Bilgilerini görüntülemek istediğiniz film adını girin: ");
                    scanner.nextLine();
                    String query = scanner.nextLine();
                    movieList.find(query);
                }

                case 4 -> movieList.print(); // Tüm kayıtları baştan sona yazdır

                case 5 -> movieList.print(Direction.BACKWARD); // Tüm kayıtları sondan başa yazdır

                case 6 ->  movieList.whereYear(2000); // 2000 yılından önceki filmleri yazdır

                case 7 -> { // Çıkış
                    movieList.save(DATA_SOURCE_FOLDER + "/" + DATA_FILENAME);
                    System.out.println(CsColors.GREEN_BRIGHT + "Envanter kaydedildi. Çıkılıyor..." + CsColors.RESET);
                }
                default -> System.out.println(CsColors.RED_BRIGHT + "\nGeçersiz seçim!" + CsColors.RESET);
            }
            System.out.println();
        } while (chosen != 7);

        scanner.close();
    }

    private static void readData() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(DATA_SOURCE_FOLDER + "/" + DATA_FILENAME, Charset.forName("UTF-8")));
            String line = reader.readLine();

            while (line != null) {
                ArrayList<Actor> actorList = new ArrayList<>();

                String actorsPattern = "\\{.*?\\}";
                String actorPattern = "\\(.*?\\)";
                Pattern p = Pattern.compile(actorsPattern);
                Matcher m = p.matcher(line);

                if (m.find()) {
                    p = Pattern.compile(actorPattern);
                    Matcher m2 = p.matcher(m.group(0));
                    while (m2.find()) {
                        try {
                            String[] parseActor = m2.group(0)
                                    .replaceAll("\\)", "")
                                    .replaceAll("\\(", "")
                                    .split(",");
                            actorList.add(new Actor(parseActor[0], parseActor[1], parseActor[2]));
                        } catch (Exception ignored) {}
                    }
                }

                line = line.replaceAll(actorsPattern, "");

                String[] parsed = line.split(",");

                if(parsed.length > 0) {
                    try {
                        int date = Integer.parseInt(parsed[0].trim());
                        String name = parsed[1];
                        Movie movie = new Movie(date, name, parsed[2], parsed[3], actorList);

                        movieList.add(movie);
                    } catch (NumberFormatException ignored) {}
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
        }               // Hata durumunda burası çalışır
    }

    public static void printNumberException() {
        System.out.println(CsColors.RED_BRIGHT + "\nLütfen sayısal değerler giriniz!!!\n" + CsColors.RESET);
    }
}
