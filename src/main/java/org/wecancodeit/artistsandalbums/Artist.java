package org.wecancodeit.artistsandalbums;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Artist {

	@Id
	@GeneratedValue
	private Long id;
	
	private String artistName;
	private String recordLabel;
	@OneToMany(mappedBy="artist")
	private Collection<Album> albums;
	@OneToMany(mappedBy="artist")
	private Collection<Song> songs;
	
	public Artist() {}
	
	public Artist(String artistName, String recordLabel) {
		this.artistName = artistName;
		this.recordLabel = recordLabel;
	}

	public Long getId() {
		return id;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getRecordLabel() {
		return recordLabel;
	}

	public Collection<Song> getSongs() {
		return songs;
	}

	public Collection<Album> getAlbums() {
		return albums;
	}

	@Override
	public String toString() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public void setRecordLabel(String recordLabel) {
		this.recordLabel = recordLabel;
	}

	public void setAlbums(Collection<Album> albums) {
		this.albums = albums;
	}

	public void setSongs(Collection<Song> songs) {
		this.songs = songs;
	}
	
	
	
}
