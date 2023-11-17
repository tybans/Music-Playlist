class Song {
    String title;
    String artist;
    String album;
    int duration;

    public Song(String title, String artist, String album, int duration) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }
}

class Playlist {
    Node head;

    class Node {
        Song song;
        Node next;

        Node(Song song) {
            this.song = song;
            this.next = null;
        }
    }

    public void addSong(String title, String artist, String album, int duration) {
        Song newSong = new Song(title, artist, album, duration);
        Node newNode = new Node(newSong);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    
    public void removeSongByTitle(String title) {
        if (head == null) {
            return;
        }

        if (head.song.title.equals(title)) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.song.title.equals(title)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    
    public void moveSongToPosition(String title, int newPosition) {
        Song songToMove = null;
        Node prevSongNode = null;
        Node current = head;
        int currentPosition = 0;

        while (current != null && currentPosition != newPosition) {
            if (current.song.title.equals(title)) {
                songToMove = current.song;
                if (prevSongNode != null) {
                    prevSongNode.next = current.next;
                } else {
                    head = current.next;
                }
                break;
            }
            prevSongNode = current;
            current = current.next;
            currentPosition++;
        }

        if (songToMove != null) {
            Node newNode = new Node(songToMove);
            newNode.next = current;
            if (prevSongNode != null) {
                prevSongNode.next = newNode;
            } else {
                head = newNode;
            }
        }
    }

    
    public void shufflePlaylist() {
        int length = getLength();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * length);
            moveSongToPosition(getSongAtIndex(i).title, randomIndex);
        }
    }

    
    public String calculateTotalDuration() {
        int totalDuration = 0;
        Node current = head;
        while (current != null) {
            totalDuration += current.song.duration;
            current = current.next;
        }

        int minutes = totalDuration / 60;
        int seconds = totalDuration % 60;
        return minutes + " minutes and " + seconds + " seconds";
    }

    
    private Song getSongAtIndex(int index) {
        Node current = head;
        int currentPosition = 0;
        while (current != null && currentPosition != index) {
            current = current.next;
            currentPosition++;
        }
        return current != null ? current.song : null;
    }

    
    private int getLength() {
        int length = 0;
        Node current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }
}

public class Music {
    public static void main(String[] args) {
        Playlist myPlaylist = new Playlist();

        // Adding songs to the playlist
        myPlaylist.addSong("Song 1", "Artist 1", "Album 1", 180);
        myPlaylist.addSong("Song 2", "Artist 2", "Album 2", 240);
        myPlaylist.addSong("Song 3", "Artist 3", "Album 3", 200);

        // Displaying original playlist
        System.out.println("Original Playlist:");
        displayPlaylist(myPlaylist);

        // Removing a song by title
        myPlaylist.removeSongByTitle("Song 2");
        System.out.println("Playlist after removing 'Song 2':");
        displayPlaylist(myPlaylist);

        // Moving a song to a new position
        myPlaylist.moveSongToPosition("Song 1", 1);
        System.out.println("Playlist after moving 'Song 1' to position 1:");
        displayPlaylist(myPlaylist);

        // Shuffling the playlist
        myPlaylist.shufflePlaylist();
        System.out.println("Shuffled Playlist:");
        displayPlaylist(myPlaylist);

        // Calculating total duration of the playlist
        System.out.println("Total Duration: " + myPlaylist.calculateTotalDuration());
    }

    public static void displayPlaylist(Playlist playlist) {
        Playlist.Node current = playlist.head;
        while (current != null) {
            System.out.println("Title: " + current.song.title + ", Artist: " + current.song.artist
                    + ", Album: " + current.song.album + ", Duration: " + current.song.duration);
            current = current.next;
        }
        System.out.println();
    }
}
