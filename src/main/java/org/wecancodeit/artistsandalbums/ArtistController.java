package org.wecancodeit.artistsandalbums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArtistController {

	
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
	
	@RequestMapping("/")
	public String home() {
		return "redirect:artists";
	}
	@RequestMapping("/artists")
	public String getArtists(Model model) {
		model.addAttribute("artists", artistRepo.findAll());
		return "artists";
	}
	@RequestMapping("/artists/{artistName}")
	public String getArtistbyName(@PathVariable(name = "artistName")String artistName, Model model) {
		model.addAttribute("artist", artistRepo.findByArtistName(artistName));
		return "artist";
	}
	@RequestMapping("/artists/{artistName}/{albumName}")
	public String getAlbumByName(@PathVariable(name = "artistName") String artistName,
								@PathVariable(name="albumName") String albumName, Model model) {
		
		model.addAttribute("artist", artistRepo.findByArtistName(artistName));
		model.addAttribute("album", albumRepo.findByAlbumName(albumName));
		return "album";
	}
	@RequestMapping("/artists/{artistName}/{albumName}/{id}")
	public String getOneSong(@PathVariable(name="artistName")String artistName,
							@PathVariable(name = "albumName")String albumName,
							@PathVariable(name="id")Long id, Model model) {
		model.addAttribute("artist", artistRepo.findByArtistName(artistName));
		model.addAttribute("album", albumRepo.findByAlbumName(albumName));
		model.addAttribute("song", songRepo.findOne(id));
	return "song";
	}
	@RequestMapping("/albums")
	public String getAlbums(Model model) {
		model.addAttribute("albums", albumRepo.findAll());
		return "albums";
	}
	@RequestMapping("/songs")
	public String getSongs(Model model) {
		model.addAttribute("songs", songRepo.findAll());
		return "songs";
	}
	@RequestMapping("/songs/{id}")
	public String getOneSong(@PathVariable(name="id")Long id, Model model) {
		model.addAttribute("song", songRepo.findOne(id));
	return "song";
	}
	@RequestMapping("/albums/{albumName}")
	public String getOneAlbum(@PathVariable(name="albumName")String albumName, Model model) {
		model.addAttribute("album", albumRepo.findByAlbumName(albumName));
	return "albumid";
	}
	@RequestMapping("/albums/{albumName}/{id}")
	public String getAlbumSong(@PathVariable(name="albumName")String albumName,
								@PathVariable(name="id")Long id, Model model) {
		model.addAttribute("album", albumRepo.findByAlbumName(albumName));
		model.addAttribute("song", songRepo.findOne(id));
	return "song";
	}
}
