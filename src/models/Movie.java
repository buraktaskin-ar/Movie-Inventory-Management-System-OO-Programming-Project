package models;

import constant.CsColors;

import java.util.ArrayList;

public class Movie {
    private int year;
    private String name;
    private String genre;
    private String director;
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    public Movie(int year, String name, String genre, String director, ArrayList<Actor> actors) {       // Movie sınıfı için yapıcı metot.
        this.year = year;
        this.name = name.trim();
        this.genre = genre.trim();
        this.director = director.trim();
        this.actors = actors;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void addActor(Actor actor) {     // Bir oyuncuyu filmin oyuncu listesine ekler.
        actors.add(actor);
    }

    public void removeActor(int index) {      // Belirli bir indexteki oyuncuyu filmin oyuncu listesinden kaldırır.
        actors.remove(index);
    }

    public ArrayList<Actor> getActors() {         // Filmin oyuncu listesini döndürür.
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {        // Filmin oyuncu listesini döndürür.
        this.actors = actors;
    }

    public String toFileString() {      // Dosya formatına uygun şekilde film bilgilerini döndürür.
        StringBuilder sb = new StringBuilder();
        sb.append(year).append(",").append(name).append(",").append(genre).append(",").append(director).append(",");

        if(actors.size() > 0) {
            sb.append("{");

            for (Actor actor : actors)
                sb.append("(").append(actor.getNameSurname()).append(",").append(actor.getGender()).append(",").append(actor.getCountry()).append(")");

            sb.append("}");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CsColors.CYAN_UNDERLINED + "Film Bilgileri\n" + CsColors.RESET);
        sb.append("Film Adı: ").append(name).append("\n");
        sb.append("Yapım Yılı: ").append(year).append("\n");
        sb.append("Film Türü: ").append(genre).append("\n");
        sb.append("Yönetmen: ").append(director).append("\n");
        sb.append("Oyuncular:\n");

        for (Actor actor : actors) {
            sb.append(actor.toString()).append("\n");
        }

        return sb.toString();
    }
}
