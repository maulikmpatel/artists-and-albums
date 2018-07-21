package org.wecancodeit.artistsandalbums;

import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album,Long> {

	Album findByAlbumName(String albumName);

}
