package be.uantwerpen.clubiot.Model;

public class SongResult
{
    private long id;
    private String title;
    private String artist;
    private int year;
    private int upvotes;
    private int downvotes;

    public SongResult()
    {
        this.id = -1;
        this.title = "Unknown Title";
        this.artist = "Unknown Artist";
        this.year = 2018;
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public SongResult(long songId, int upvotes, int downvotes)
    {
        this.id = songId;
        this.title = "Unknown Title";
        this.artist = "Unknown Artist";
        this.year = 2018;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public SongResult (long songId, String title, String artist, int year, int upvotes, int downvotes)
    {
        this.id = songId;
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    public long getSongId()
    {
        return this.id;
    }

    public String getTitle()
    {
        return this.title;
    }

    public String getArtist()
    {
        return this.artist;
    }

    public int getYear()
    {
        return this.year;
    }

    public int getUpvotes()
    {
        return this.upvotes;
    }

    public int getDownvotes()
    {
        return this.downvotes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
