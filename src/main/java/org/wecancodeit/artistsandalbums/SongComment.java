package org.wecancodeit.artistsandalbums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
@Entity
public class SongComment {
	
	@ManyToOne
	private Song song;

	@Id
	@GeneratedValue
	private Long id;
	private String userName;
	@Lob
	private String songComment;

	public SongComment() {
	}

	public SongComment(String userName, String songComment, Song song) {
		this.songComment = songComment;

	}

	public Long getId() {
		return id;
	}

	public String getSongComment() {
		return songComment;
	}

	public String getUserName() {
		return userName;
	}

	public Song getSong() {
		return song;
	}

	@Override
	public String toString() {
		return songComment;
	}

	
}
