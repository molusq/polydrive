package mp3player;

public class Track {
    private final String titre;
    private final String artiste;
    private final String chemin;

    public Track(String titre, String artiste, String chemin) {
        this.titre = titre;
        this.artiste = artiste;
        this.chemin = chemin;
    }

    public String getTitre() {
        return titre;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getChemin() {
        return chemin;
    }

    @Override
    public String toString() {
        return "titre='" + titre + "'\t" + ", artiste='" + artiste + '\'' ;

    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Track p){
            return p.getTitre().equals(this.getTitre()) && p.getArtiste().equals(this.getArtiste()) && p.getChemin().equals(this.getChemin());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.getTitre().hashCode() + this.getArtiste().hashCode() + this.getChemin().hashCode();
    }
}
