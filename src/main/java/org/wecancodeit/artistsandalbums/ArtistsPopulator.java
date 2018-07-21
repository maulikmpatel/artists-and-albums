package org.wecancodeit.artistsandalbums;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

public class ArtistsPopulator implements CommandLineRunner{

	
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
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Artist busta = artistRepo.save(new Artist("Busta Rhymes", "Epic"));
		Artist tupac = artistRepo.save(new Artist("Tupac", "Death Row Records"));
		
		Album genesis = albumRepo.save(new Album("Genesis", "November 27, 2001","Rap Music", "https://upload.wikimedia.org/wikipedia/en/c/c7/Bustarhymes_genesis.jpg", busta));
		Album eyez = albumRepo.save(new Album("All Eyez On Me", "February 13, 1996", "Rap Music", "https://upload.wikimedia.org/wikipedia/en/thumb/1/16/Alleyezonme.jpg/220px-Alleyezonme.jpg",tupac));
	
		Song heartz = songRepo.save(new Song("Heartz of Men","4:43","stuff", "great", eyez, tupac));
		Song dou = songRepo.save(new Song("How Do U Want It","4:48","more stuff", "great", eyez, tupac));
		Song holla = songRepo.save(new Song("Holla","4:34","extra stuff","good", genesis,busta));
		Song hurt = songRepo.save(new Song("Make it Hurt", "3:23","lotta stuff", "good", genesis,busta));
	
	}

}
