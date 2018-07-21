package org.wecancodeit.artistsandalbums;

import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long>{

	Artist findByArtistName(String artistName);

}
