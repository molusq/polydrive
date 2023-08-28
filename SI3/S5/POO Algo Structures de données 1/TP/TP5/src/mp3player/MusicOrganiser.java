package mp3player;

import java.io.File;
import java.util.ArrayList;

public class MusicOrganiser extends ArrayList<Track> {
    private final MusicPlayer mp;

    protected MusicOrganiser() {
        super();
        mp = new MusicPlayer();
    }


    @Override
    public String toString() {
        if(this.isEmpty()){
            return "No music in the list";
        }
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(Track track : this){
            sb.append(track).append(" (").append(i).append(")").append("\n");
            i++;
        }
        return sb.toString();
    }

    protected void addFile(String fileName){
        File file = new File(fileName);
        if(file.exists() && file.isFile() && file.getName().endsWith(".mp3")){
            this.add(new Track(file.getName().split("-")[0], file.getName().split("-")[1].split(".mp3")[0], file.getAbsolutePath()));
        }
    }

    protected void removeFile(String fileName){
        File file = new File(fileName);
        if(file.exists() && file.isFile() && file.getName().endsWith(".mp3")){
            this.remove(new Track(file.getName().split("-")[0], file.getName().split("-")[1].split(".mp3")[0], file.getAbsolutePath()));
        }
    }

    protected void addFilesFromDirectory(String directoryName) {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile() && file.getName().endsWith(".mp3")) {
                    add(new Track(file.getName().split("-")[0], file.getName().split("-")[1].split(".mp3")[0], file.getAbsolutePath()));
                    System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    addFilesFromDirectory(file.getAbsolutePath());
                }
            }
        }
    }

    protected void removeFilesFromDirectory(String directoryName){
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile() && file.getName().endsWith(".mp3")) {
                    remove(new Track(file.getName().split("-")[0], file.getName().split("-")[1].split(".mp3")[0], file.getAbsolutePath()));
                } else if (file.isDirectory()) {
                    removeFilesFromDirectory(file.getAbsolutePath());
                }
            }
        }
    }

    protected void playSample(int i){
        try {
            mp.playSample(get(i - 1).getChemin());
        } catch (Exception e) {
            System.err.println("The file is not available : " + e);
        }
}

    protected void playWholeFile(int i){
        try {
            mp.startPlaying(get(i - 1).getChemin());
        } catch (Exception e) {
            System.err.println("The file is not available : " + e);
        }
    }

    protected void stop(){
        mp.stop();
    }
}
