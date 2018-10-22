package be.uantwerpen.clubiot.Service;

import be.uantwerpen.clubiot.Model.Music;
import be.uantwerpen.clubiot.Repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    @Autowired
    private MusicRepository musicRepository;

    public Iterable<Music> findAll() {
        return this.musicRepository.findAll();
    }

    public Iterable<Music> findSong(String song){
        return this.musicRepository.findByTitleContainingOrArtistContaining(song);
    }
}
