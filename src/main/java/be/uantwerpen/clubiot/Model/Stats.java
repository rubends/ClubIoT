package be.uantwerpen.clubiot.Model;

public class Stats
{
    private SongResult  mostLiked; // TODO make this an object : {id - name - etc}
    private SongResult  mostDisliked;
    private String bestVoter;
    private String welcome;

    public Stats()
    {
        this.mostLiked = new SongResult();
        this.mostDisliked = new SongResult();
        this.bestVoter = "Default user";
        this.welcome = "Welcome Ignis";
    }

    public Stats (SongResult mostLiked, SongResult mostDisliked, String bestVoter, String welcome)
    {
        this.mostLiked = mostLiked;
        this.mostDisliked = mostDisliked;
        this.bestVoter = bestVoter;
        this.welcome = welcome;
    }

    public SongResult getMostLiked() {
        return mostLiked;
    } // TODO also make them return objects here

    public SongResult getMostDisliked() {
        return mostDisliked;
    }

    public String getBestVoter() {
        return bestVoter;
    }

    public void setMostLiked(SongResult mostLiked) {
        this.mostLiked = mostLiked;
    }

    public void setMostDisliked(SongResult mostDisliked) {
        this.mostDisliked = mostDisliked;
    }

    public void setBestVoter(String bestVoter) {
        this.bestVoter = bestVoter;
    }
}
