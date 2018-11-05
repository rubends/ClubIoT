package be.uantwerpen.clubiot.Model;

public class Stats
{
    private SongResult  mostLiked; // TODO make this an object : {id - name - etc}
    private SongResult  mostDisliked;
    private Voter bestVoter;
    private String welcome;

    public Stats()
    {
        this.mostLiked = new SongResult();
        this.mostDisliked = new SongResult();
        this.bestVoter = new Voter("Thomas", 9);
        this.welcome = "Welcome Ignis";
    }

    public Stats (SongResult mostLiked, SongResult mostDisliked, Voter bestVoter, String welcome)
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

    public Voter getBestVoter() {
        return bestVoter;
    }

    public void setMostLiked(SongResult mostLiked) {
        this.mostLiked = mostLiked;
    }

    public void setMostDisliked(SongResult mostDisliked) {
        this.mostDisliked = mostDisliked;
    }

    public void setBestVoter(Voter bestVoter) {
        this.bestVoter = bestVoter;
    }
}
