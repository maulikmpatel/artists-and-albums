package org.wecancodeit.artistsandalbums;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {

	@Id
	@GeneratedValue
	private Long id;
	private String albumName;
	private String releaseDate;
	private String genre;
	private String coverImg;
	@OneToMany(mappedBy="album")
	private Collection<Song> songs; 
	@ManyToOne
	private Artist artist;
	@OneToMany(mappedBy="album")
	private Collection<AlbumComment> albumComments;
	
	
	public Album() {}
	
	public Album(String albumName, String releaseDate, String genre, String coverImg, Artist artist, AlbumComment...albumComments) {
		this.albumName = albumName;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.coverImg = coverImg;
		this.artist = artist;
		this.albumComments = Arrays.asList(albumComments);
	}

	public Long getId() {
		return id;
	}

	public String getAlbumName() {
		return albumName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getGenre() {
		return genre;
	}

	public String getCoverImg() {
		return coverImg;
	}

	public Collection<Song> getSongs() {
		return songs;
	}

	public Artist getArtist() {
		return artist;
	}

	public Collection<AlbumComment> getAlbumComments() {
		return albumComments;
	}
	public void addAlbumComment(AlbumComment albumComment) {
		albumComments.add(albumComment);
	}
	@Override
	public String toString() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	
}
