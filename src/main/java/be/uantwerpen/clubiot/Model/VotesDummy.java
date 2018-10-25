package be.uantwerpen.clubiot.Model;

public class VotesDummy
{
    private String mostLiked; // TODO make this an object : {id - name}
    private String mostDisliked;
    private String bestVoter;
    private String welcome;

    public VotesDummy()
    {
        this.mostLiked = "Yes-R en PartySquad - Op die Beat";
        this.mostDisliked = "Takashi69 - fefe";
        this.bestVoter = "Steven";
        this.welcome = "Made for IGNIS";
    }

    public String getMostLiked() {
        return mostLiked;
    }

    public String getMostDisliked() {
        return mostDisliked;
    }

    public String getBestVoter() {
        return bestVoter;
    }

}
