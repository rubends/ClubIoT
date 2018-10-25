package be.uantwerpen.clubiot.Repository;

import be.uantwerpen.clubiot.Model.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends CrudRepository<Music, Integer> {
    List<Music> findAllByTitleContainingOrArtistContainingAllIgnoreCase(String text, String text2);
}
