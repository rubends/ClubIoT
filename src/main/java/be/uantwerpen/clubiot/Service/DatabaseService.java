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

    public Iterable<Music> findSongByText(String song){
        return this.musicRepository.findAllByTitleContainingOrArtistContainingAllIgnoreCase(song, song);
    }

    public Music findSongById(int id){
        return this.musicRepository.findOne(id);
    }
}
