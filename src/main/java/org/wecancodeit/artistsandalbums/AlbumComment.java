package org.wecancodeit.artistsandalbums;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class AlbumComment {


		@ManyToOne
		private Album album;

		@Id
		@GeneratedValue
		private Long id;
		private String albumUser;
		@Lob
		private String albumComment;

		public AlbumComment() {
		}

		public AlbumComment(String albumUser, String albumComment, Album Album) {
			this.albumComment = albumComment;
			this.albumUser = albumUser;
	
		}

		public Album getAlbum() {
			return album;
		}

		public String getAlbumUser() {
			return albumUser;
		}

		public Long getId() {
			return id;
		}

		public String getAlbumComment() {
			return albumComment;
		}

		@Override
		public String toString() {
			return albumComment;
		}


	
}
