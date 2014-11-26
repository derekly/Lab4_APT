import java.util.ArrayList;


public class MockMp3Player implements Mp3Player {
	ArrayList songs = new ArrayList();
	int index;
	boolean isPlaying;
	boolean paused;
	String currentSong;
	long startTime;// = System.currentTimeMillis();
	double timeElapsed;
	MockMp3Player(){
		index = 0;
		currentSong = "";		
		
		isPlaying = false;
		startTime = 0;
		timeElapsed = 0.0;
		paused = false;
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		if(songs.size() == 0){
			return;
		}
		currentSong = (String) songs.get(index);
		isPlaying = true;
		paused = false;
		//using arbitrary value for the sake of this lab. System timers do not work as intended during JUnit
		startTime = 123456789;//System.currentTimeMillis();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
		if(isPlaying){
			long temp = 234567890;//System.currentTimeMillis();
			long delta = temp - startTime;
			double elapsed = delta/1000;
			timeElapsed += Math.ceil(elapsed);
		}
		isPlaying = false;
		paused = true;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		isPlaying = false;
		paused = false;
		timeElapsed = 0;

	}

	@Override
	public double currentPosition() {
		// TODO Auto-generated method stub
		if(isPlaying){
			long temp = 234567890;//System.currentTimeMillis();
			long delta = temp - startTime;
			double elapsed = delta/1000;
			timeElapsed += Math.ceil(elapsed);
		}
		return timeElapsed;
	}

	@Override
	public String currentSong() {
		// TODO Auto-generated method stub
		return currentSong;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		index = index+1;
		if(index >= songs.size()){
			index= songs.size() -1;
		}
		stop();
		play();

	}

	@Override
	public void prev() {
		// TODO Auto-generated method stub
		index = index -1;
		if(index < 0){
			index = 0;//songs.size()-1;
		}
		stop();
		play();
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return isPlaying;
	}

	@Override
	public void loadSongs(ArrayList names) {
		// TODO Auto-generated method stub
		this.songs = names;

	}

}
