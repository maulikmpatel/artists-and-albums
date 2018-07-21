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
		@Lob
		private String albumComment;

		public AlbumComment() {
		}

		public AlbumComment(String albumComment, Album Album) {
			this.albumComment = albumComment;
	
		}

		public Album getAlbum() {
			return album;
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
