package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entites.Song;
import com.example.demo.services.SongService;

@Controller
public class SongController {

	@Autowired
	SongService service;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		
		boolean songStatus=service.songExists(song.getName());
		if(songStatus ==false) {
		service.addSong(song);
		System.out.println("Song added sucessfully");
		}
		else {
			System.out.println("Song already exist");
		}
		return "adminhome";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songsList=service.fetchAllSongs();
		model.addAttribute("songs",songsList);
		//System.out.println(songsList);
		return "displaySongs";
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premiumUser=false;
		if(premiumUser == true) {
			List<Song> songsList=service.fetchAllSongs();
			model.addAttribute("songs",songsList);
			return "displaySongs";
		}
		else {
			return "makePayment";
		}
	}
}