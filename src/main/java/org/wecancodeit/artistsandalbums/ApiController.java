package org.wecancodeit.artistsandalbums;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	ArtistRepository artistRepo;
	@Autowired
	AlbumRepository albumRepo;
	@Autowired
	SongRepository songRepo;
	@Autowired
	AlbumCommentRepository albumCommentRepo;
	@Autowired
	SongCommentRepository songCommentRepo;
	
	@RequestMapping("/artists")
	public Collection<Artist> getArtists(){
		return (Collection<Artist>) artistRepo.findAll();
	}
	@RequestMapping("/albums")
	public Collection<Album> getAlbums(){
		return (Collection<Album>) albumRepo.findAll();
	}
	@RequestMapping("/song")
	public Collection<Song> getSongs(){
		return (Collection<Song>) songRepo.findAll();
	}
	@RequestMapping("/artists/{artistName}")
	public Collection<Album> getArtistAlbums(@PathVariable(name = "artistName") String artistName) {
		return artistRepo.findByArtistName(artistName).getAlbums();
	}
	@RequestMapping("/artists/{artistName}/{albumName}")
	public Collection<Song>getArtistAlbumSongs(@PathVariable(name="artistName")String artistName,
												@PathVariable(name="albumName")String albumName){
		return albumRepo.findByAlbumName(albumName).getSongs();
	}
	@RequestMapping(value= "/artists", method = RequestMethod.POST)
	public Collection<Artist> addArtist(@RequestParam(value = "artistName") String artistName,
										@RequestParam(value = "recordLabel", required=false) String recordLabel){	
		if (artistRepo.findByArtistName(artistName) == null) {		
				artistRepo.save(new Artist(artistName, recordLabel));
			}
		return (Collection<Artist>) artistRepo.findAll();	
		}
	@RequestMapping(value= "/artists/{artistName}", method = RequestMethod.POST)
	public Collection<Album> addAlbum(
			@PathVariable(name="artistName")String artistName,
			@RequestParam(value = "albumName", required=false) String albumName,
			@RequestParam(value = "releaseDate", required=false) String releaseDate,
			@RequestParam(value = "genre", required=false) String genre,
			@RequestParam(value = "coverImg", required=false) String coverImg,
			@RequestParam(value = "recordLabel", required=false) String recordLabel) {	
		if (albumRepo.findByAlbumName(albumName) == null && artistRepo.findByArtistName(artistName) == null) {		
			Artist newArtist = artistRepo.save(new Artist(artistName, recordLabel));
			albumRepo.save(new Album(albumName, releaseDate, genre, coverImg, newArtist));
			}
		return (Collection<Album>) albumRepo.findAll();	
	}
	@RequestMapping(value = "/album/add-albumcomment", method = RequestMethod.POST)
	public Collection<AlbumComment> addAlbumComment(@RequestParam(value = "albumUser") String albumUser,
			@RequestParam(value = "albumComment") String albumComment,
			@RequestParam(value = "albumName") String albumName){
		albumCommentRepo.save(new AlbumComment(albumUser, albumComment, albumRepo.findByAlbumName(albumName)));
		Collection<AlbumComment> newAlbumComment = albumRepo.findByAlbumName(albumName).getAlbumComments();
		return newAlbumComment;
	}
	@RequestMapping(value= "/album/add-album", method = RequestMethod.POST)
	public Collection<Song> addSong(@RequestParam(value="artistName")String artistName,
			@RequestParam(value="albumName")String albumName,
			@RequestParam(value = "releaseDate", required=false) String releaseDate,
			@RequestParam(value = "genre", required=false) String genre,
			@RequestParam(value = "coverImg", required=false) String coverImg,
			@RequestParam(value = "recordLabel", required=false) String recordLabel,
			@RequestParam(value="songName", required=false)String songName,
			@RequestParam(value="length", required=false)String length,
			@RequestParam(value="lyrics", required=false)String lyrics,
			@RequestParam(value="rating", required=false)String rating)
	{	
		if (albumRepo.findByAlbumName(albumName) == null && artistRepo.findByArtistName(artistName) == null && songRepo.findBySongName(songName) == null) {		
			Artist newArtist = artistRepo.save(new Artist(artistName, recordLabel));
			Album newAlbum = albumRepo.save(new Album(albumName, releaseDate, genre, coverImg, newArtist));
			songRepo.save(new Song(songName, length, lyrics, rating, newAlbum));
		}
		return (Collection<Song>) songRepo.findAll();	
	}
	@RequestMapping(value = "/artists/{artistName}", method = RequestMethod.GET)
	public Artist returnArtist(@PathVariable(name = "artistName") String artistName) {
		return artistRepo.findByArtistName(artistName);
	}
	@RequestMapping(value = "/artists/{artistName}/{albumName}", method = RequestMethod.GET)
	public Album returnAlbum(@PathVariable(name = "artistName") String artistName,
							@PathVariable(name="albumName")String albumName) {
		return albumRepo.findByAlbumName(albumName);
	}
	@RequestMapping(value = "/artists/{artistName}/{albumName}/{id}", method = RequestMethod.GET)
	public Song returnSong(@PathVariable(name = "artistName") String artistName,
							@PathVariable(name="albumName")String albumName,
							@PathVariable(name = "id")Long id) {
		return songRepo.findOne(id);
	}
	
	@RequestMapping(value = "/artists/{artistName}/{albumName}", method = RequestMethod.DELETE)
	public Collection<Song> deleteSong(@PathVariable(name = "artistName") String artistName, 
							@RequestParam(value = "albumName") String albumName) {
		albumRepo.delete(albumRepo.findByAlbumName(albumName));
		
		return albumRepo.findByAlbumName(albumName).getSongs();
	}
	

	@RequestMapping(value = "/artist/{artistName}", method = RequestMethod.DELETE)
	public Collection<Album> deleteAlbum(@PathVariable(name="artistName") String artistName,
							@RequestParam(value ="albumName") String albumName) {
		for(Song song : albumRepo.findByAlbumName(albumName).getSongs()) {
			songRepo.delete(song);
		}
		albumRepo.delete(albumRepo.findByAlbumName(albumName));
		return (Collection<Album>) albumRepo.findAll();
	}
	@RequestMapping(value = "/artist", method = RequestMethod.DELETE)
	public Collection<Artist> deleteArtist(@RequestParam(value ="artistName") String artistName,
										@RequestParam(value="albumName")String albumName) {
		for(Album album : artistRepo.findByArtistName(artistName).getAlbums()) {
			albumRepo.delete(album);
				for(Song song : albumRepo.findByAlbumName(albumName).getSongs()) {
					songRepo.delete(song);
					}
				}
			artistRepo.delete(artistRepo.findByArtistName(artistName));
		return (Collection<Artist>) artistRepo.findAll();
	}
	@RequestMapping(value = "/artists", method = RequestMethod.PUT)
	public Collection<Artist> editArtist(@RequestParam(value = "artistName") String artistName,
			@RequestParam(value = "recordLabel") String recordLabel){
		Artist artistToEdit = artistRepo.findByArtistName(artistName);
		if (artistToEdit != null) {
			artistToEdit.setArtistName(artistName);
			artistToEdit.setRecordLabel(recordLabel);
			artistToEdit = artistRepo.save(artistToEdit);
		}
		return (Collection<Artist>) artistRepo.findAll();
	}
	@RequestMapping(value="/artists/{artistName}", method = RequestMethod.PUT)
	public Collection<Album> editAlbum(@PathVariable(name="artistName")String artistName,
									@RequestParam(value="releaseDate")String releaseDate,
									@RequestParam(value="genre")String genre,
									@RequestParam(value="coverImg")String coverImg,
									@RequestParam(value="albumName")String albumName){
		Album albumToEdit = albumRepo.findByAlbumName(albumName);
		if(albumToEdit != null) {
			albumToEdit.setAlbumName(albumName);
			albumToEdit.setReleaseDate(releaseDate);
			albumToEdit.setGenre(genre);
			albumToEdit.setCoverImg(coverImg);
			
		}
		return (Collection<Album>) albumRepo.findAll();
	}
	@RequestMapping(value="/artists/{artistName}/{albumName}", method = RequestMethod.PUT)
	public Collection<Song> editSong(@PathVariable(name="artistName")String artistName,
									@PathVariable(name="albumName")String albumName,
									@RequestParam(value="songName")String songName,
									@RequestParam(value="length")String length,
									@RequestParam(value="lyrics")String lyrics,
									@RequestParam(value="rating")String rating){
		Song songToEdit = songRepo.findBySongName(songName);
		if(songToEdit != null) {
			songToEdit.setSongName(songName);
			songToEdit.setLength(length);
			songToEdit.setLyrics(lyrics);
			songToEdit.setRating(rating);
			
		}
		return (Collection<Song>) songRepo.findAll();
	}
}
