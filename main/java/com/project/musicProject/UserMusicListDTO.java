package com.project.musicProject;

import java.util.List;

public class UserMusicListDTO {
	
	private int userId;
    private String listName;
    private List<MusicDTO> musicList;
    
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public List<MusicDTO> getMusicList() {
		return musicList;
	}
	public void setMusicList(List<MusicDTO> musicList) {
		this.musicList = musicList;
	}
    
}
