package be.uantwerpen.clubiot.Model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MUSIC")
@AttributeOverride(name="id", column=@Column(name="ID"))
public class Music extends AbstractPersistable<Integer> {
    @Column(name="TITLE")
    private String title;
    @Column(name="ARTIST")
    private String artist;
    @Column(name="YEAR")
    private int year;

    public Music(String title, String artist, int year){
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
