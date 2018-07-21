package org.wecancodeit.artistsandalbums;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Song {

	@Id
	@GeneratedValue
	private Long id;
	private String songName;
	private String length;
	private String lyrics;
	private String rating;
	@ManyToOne
	private Album album;
	@ManyToOne
	private Artist artist;
	@OneToMany(mappedBy="song")
	private Collection<SongComment> songComments;
	
	public Song() {}
	
	public Song(String songName, String length, String lyrics, String rating, Album album,Artist artist, SongComment...songComments ) {
		this.songName = songName;
		this.length = length;
		this.lyrics = lyrics;
		this.rating = rating;
		this.album = album;
		this.artist = artist;
		this.songComments = Arrays.asList(songComments);
	}

	public Long getId() {
		return id;
	}

	public String getSongName() {
		return songName;
	}

	public String getLength() {
		return length;
	}

	public String getLyrics() {
		return lyrics;
	}

	public String getRating() {
		return rating;
	}

	public Album getAlbum() {
		return album;
	}

	public Artist getArtist() {
		return artist;
	}

	public Collection<SongComment> getSongComments() {
		return songComments;
	}
	public void addSongComment(SongComment songComment) {
		songComments.add(songComment);
		
	}
	@Override
	public String toString() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}


}
