package be.uantwerpen.clubiot.Model;

public class Stats
{
    private String  mostLiked; // TODO make this an object : {id - name - etc}
    private String  mostDisliked;
    private String bestVoter;
    private String welcome;

    public Stats()
    {
        this.mostLiked = "Yes-R en PartySquad - Op die Beat";
        this.mostDisliked = "Takashi69 - fefe";
        this.bestVoter = "Steven";
        this.welcome = "Made for IGNIS";
    }

    public Stats (String mostLiked, String mostDisliked, String bestVoter, String welcome)
    {
        this.mostLiked = mostLiked;
        this.mostDisliked = mostDisliked;
        this.bestVoter = bestVoter;
        this.welcome = welcome;
    }

    public String getMostLiked() {
        return mostLiked;
    } // TODO also make them return objects here

    public String getMostDisliked() {
        return mostDisliked;
    }

    public String getBestVoter() {
        return bestVoter;
    }

    public void setMostLiked(String mostLiked) {
        this.mostLiked = mostLiked;
    }

    public void setMostDisliked(String mostDisliked) {
        this.mostDisliked = mostDisliked;
    }

    public void setBestVoter(String bestVoter) {
        this.bestVoter = bestVoter;
    }
}
