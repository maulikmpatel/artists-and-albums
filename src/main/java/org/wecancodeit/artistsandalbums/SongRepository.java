package org.wecancodeit.artistsandalbums;

import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long>{
	
	Song findBySongName(String songName);
}
