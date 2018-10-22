package be.uantwerpen.clubiot.Repository;

import be.uantwerpen.clubiot.Model.Music;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends CrudRepository<Music, Long> {
    Iterable<Music> findByTitleContainingOrArtistContaining(String text);
}
