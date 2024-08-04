
package music;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class SongNode {
    String name;
    String singer;
    int duration; // Duration in seconds
    int playCount; // Track play count
    String lyrics; // Store lyrics
    SongNode next;
    SongNode prev;

    public SongNode(String name, String singer, int duration, String lyrics) {
        this.name = name;
        this.singer = singer;
        this.duration = duration;
        this.playCount = 0; // Initialize play count to 0
        this.lyrics = lyrics; // Store lyrics
    }
}

class Playlist {
    private SongNode head;
    private SongNode tail;
    private int totalCount; // Track total number of songs in the playlist
    
     private String name;

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Playlist() {
        this.head = null;
        this.tail = null;
        this.totalCount = 0; // Initialize song count to 0
    }

    public void insertFirst(String name, String singer, int duration, String lyrics) {
        SongNode newNode = new SongNode(name, singer, duration, lyrics);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        totalCount++; // Increment song count
    }

    public void insertLast(String name, String singer, int duration, String lyrics) {
        SongNode newNode = new SongNode(name, singer, duration, lyrics);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        totalCount++; // Increment song count
    }

   public void displayNamesOnly() {
    System.out.println("-------------------------------");
    SongNode current = head;
    while (current != null) {
        System.out.printf("| %-25s |\n", current.name);
        current = current.next;
    }
    System.out.println("-------------------------------");
}
  public void displaySingersOnly() {
    SongNode current = head;
    while (current != null) {
        System.out.printf("| %-25s | %-20s |\n", current.name, current.singer);
        current = current.next;
    }
}
   public void displayDurationsOnly() {
    
    SongNode current = head;
    while (current != null) {
        System.out.printf("| %-25s | %-10d |\n", current.name, current.duration);
        current = current.next;
    }
   
}
    public SongNode getHead() {
        return head;
    }

    public int getTotalCount() {
        return totalCount; // Return the total number of songs
    }

    // Sorting logic for songs by name
    public Playlist sortByName() {
     if (head == null) return this;
    SongNode current, index;
    String tempName, tempSinger, tempLyrics;
    int tempDuration, tempPlayCount;

    for (current = head; current.next != null; current = current.next) {
        for (index = current.next; index != null; index = index.next) {
            if (current.name.compareToIgnoreCase(index.name) > 0) {
                // Swap the node data
                tempName = current.name;
                tempSinger = current.singer;
                tempDuration = current.duration;
                tempLyrics = current.lyrics;
                tempPlayCount = current.playCount;

                current.name = index.name;
                current.singer = index.singer;
                current.duration = index.duration;
                current.lyrics = index.lyrics;
                current.playCount = index.playCount;

                index.name = tempName;
                index.singer = tempSinger;
                index.duration = tempDuration;
                index.lyrics = tempLyrics;
                index.playCount = tempPlayCount;
            }
        }
    }
    return this;
    }
    // Sorting logic for songs by singer
    public Playlist sortBySinger() {
      if (head == null) return this;
    SongNode current, index;
    String tempName, tempSinger, tempLyrics;
    int tempDuration, tempPlayCount;

    for (current = head; current.next != null; current = current.next) {
        for (index = current.next; index != null; index = index.next) {
            if (current.singer.compareToIgnoreCase(index.singer) > 0) {
                // Swap the node data
                tempName = current.name;
                tempSinger = current.singer;
                tempDuration = current.duration;
                tempLyrics = current.lyrics;
                tempPlayCount = current.playCount;

                current.name = index.name;
                current.singer = index.singer;
                current.duration = index.duration;
                current.lyrics = index.lyrics;
                current.playCount = index.playCount;

                index.name = tempName;
                index.singer = tempSinger;
                index.duration = tempDuration;
                index.lyrics = tempLyrics;
                index.playCount = tempPlayCount;
            }
        }
    }
    
    return this;
}
    // Sorting logic for songs by duration
public Playlist sortByDuration() {
    if (head == null) return this;
    SongNode current, index;
    String tempName, tempSinger, tempLyrics;
    int tempDuration, tempPlayCount;

    for (current = head; current.next != null; current = current.next) {
        for (index = current.next; index != null; index = index.next) {
            if (current.duration > index.duration) {
                // Swap the node data
                tempName = current.name;
                tempSinger = current.singer;
                tempDuration = current.duration;
                tempLyrics = current.lyrics;
                tempPlayCount = current.playCount;

                current.name = index.name;
                current.singer = index.singer;
                current.duration = index.duration;
                current.lyrics = index.lyrics;
                current.playCount = index.playCount;

                index.name = tempName;
                index.singer = tempSinger;
                index.duration = tempDuration;
                index.lyrics = tempLyrics;
                index.playCount = tempPlayCount;
            }
        }
    }
    
    return this;
}

    
}

public class Music {
     Scanner scanner; // Declare the scanner object

       // Map to track play counts by date
    Map<String, Map<LocalDate, Integer>> playCountByDate = new HashMap<>();
    public Music() {
        scanner = new Scanner(System.in); // Initialize the scanner object
    }
    Playlist home = new Playlist(); // Home playlist
    Playlist[] playlists = new Playlist[10]; // Array of custom playlists
    int playlistCount = 0; // Count of custom playlists
    Map<String, Integer> playCount = new HashMap<>(); // To track the play count of songs

    // Sample map of song lyrics
    Map<String, String> songLyrics = new HashMap<>();

    void displayMenu() {
        System.out.println("******************************");
        System.out.println("       🎵 Music Player 🎵      ");
        System.out.println("*****************************");
        System.out.println("| 1. Add Song to Home       |");
        System.out.println("| 2. Display Home Playlist  |");
        System.out.println("| 3. Create New Playlist    |");
        System.out.println("| 4. Display Playlists      |");
        System.out.println("| 5. Play Song              |");
        System.out.println("| 6. Sort Songs by Name     |");
        System.out.println("| 7. Sort Songs by Singer   |");
        System.out.println("| 8. Sort Songs by Duration |");
        System.out.println("| 9. Insert Song at Beginning|");
        System.out.println("| 10. Insert Song at End    |");
        System.out.println("| 11. Display a Playlist    |");
        System.out.println("| 12. Recommend Songs       |");
        System.out.println("| 13. Display Lyrics        |");
        System.out.println("| 14. Search Song           |");
        System.out.println("| 15. Create Custom Playlist|");
        System.out.println("| 16. Crossfade Play        |");
        System.out.println("| 17. Songs Total Count     |");
        System.out.println("| 18. Today's Biggest Hit     |");
        System.out.println("| 0. Exit                   |");
        System.out.println("-----------------------------");
    }

    // Method to recommend songs based on play count
    void recommendSongs() {
        System.out.println("-------------------------------");
        System.out.println("|       RECOMMENDED SONGS     |");
        System.out.println("-------------------------------");
        playCount.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> System.out.printf("| %-25s | %4d |\n", entry.getKey(), entry.getValue()));
        System.out.println("-------------------------------");
    }

    // Method to display lyrics of the currently playing song
    void displayLyrics(SongNode song) {
        System.out.println("-------------------------------");
        if (song.lyrics != null && !song.lyrics.isEmpty()) {
            System.out.println("| Lyrics for " + song.name + ":");
            System.out.println("-------------------------------");
            System.out.println(song.lyrics);
            System.out.println("-------------------------------");
        } else {
            System.out.println("| Lyrics not available for this song.");
            System.out.println("-------------------------------------");
        }
    }

    // Method to search for a song by name, singer, or part of the lyrics
    void searchSong(String query) {
        System.out.println("-------------------------------");
        SongNode current = home.getHead();
        boolean found = false;
        while (current != null) {
            if (current.name.equalsIgnoreCase(query) || current.singer.equalsIgnoreCase(query) ||
                (current.lyrics != null && current.lyrics.contains(query))) {
                System.out.println("| Found song: " + current.name + " by " + current.singer);
                System.out.println("-------------------------------");
                found = true;
                
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("| Song not found in the playlist.");
            System.out.println("-------------------------------");
        }
    }

    // Method to display the total number of songs in the home playlist
    void displayTotalSongsCount() {
        System.out.println("-------------------------------");
        System.out.println("| Total number of songs: " + home.getTotalCount());
        System.out.println("-------------------------------");
    }

    // Method to create a custom playlist with a specific total duration
    void createCustomPlaylistWithName() {
        System.out.println("-------------------------------");
        System.out.println("| Creating custom playlist... |");
        System.out.println("-------------------------------");
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        Playlist customPlaylist = new Playlist();
        System.out.println("-------------------------------");
        System.out.println("| " + playlistName + " playlist created.");
        System.out.println("-------------------------------");
        
        
        int i = 0;
        while (i < 2) {
        System.out.print("Enter song " + (i + 1) + " name: ");
        String songName = scanner.nextLine();
        System.out.print("Enter song " + (i + 1) + " singer: ");
        String singer = scanner.nextLine();
        System.out.print("Enter song " + (i + 1) + " duration (in seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter song " + (i + 1) + " lyrics: ");
        String lyrics = scanner.nextLine();
        customPlaylist.insertLast(songName, singer, duration, lyrics);
        i++; // Increment the counter
        
            // View the songs in the playlist
        System.out.println("-------------------------------");
        System.out.println("| Songs in " + playlistName + " playlist:");
        System.out.println("-------------------------------");
        customPlaylist.displayNamesOnly();

        playlists[playlistCount] = customPlaylist;
        playlistCount++;

}
    }
    void addSongToPlaylist() {
    System.out.print("Enter playlist number to add song to: ");
    int playlistNumber = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    if (playlistNumber >= 1 && playlistNumber <= playlistCount) {
        System.out.print("Enter song name: ");
        String name = scanner.nextLine();
        System.out.print("Enter singer name: ");
        String singer = scanner.nextLine();
        System.out.print("Enter duration (in seconds): ");
        int duration = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter lyrics: ");
        String lyrics = scanner.nextLine();
        playlists[playlistNumber - 1].insertLast(name, singer, duration, lyrics);
        System.out.println("-------------------------------");
        System.out.println("| Song added to playlist " + playlistNumber);
        System.out.println("-------------------------------");
    } else {
        System.out.println("-------------------------------");
        System.out.println("| Invalid playlist number.");
        System.out.println("-------------------------------");
    }
}

    // Method to crossfade play between two songs
    void crossfadePlay(SongNode current, SongNode next) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("| Crossfading from " + current.name + " to " + next.name + "...");
        System.out.println("-------------------------------------------------------------");
        // Simulated crossfade logic
    }

    // Method to add lyrics to a song
    void addLyricsToSong(SongNode song, String lyrics) {
        song.lyrics = lyrics;
        System.out.println("-------------------------------");
        System.out.println("| Lyrics added to " + song.name);
        System.out.println("-------------------------------");
    }
     // Method to update the play count for a song
    void updatePlayCount(String songName) {
         LocalDate today = LocalDate.now();
         playCountByDate.putIfAbsent(songName, new HashMap<>());
         Map<LocalDate, Integer> dailyCounts = playCountByDate.get(songName);
         dailyCounts.put(today, dailyCounts.getOrDefault(today, 0) + 1);
    
    // Update the playCount map
    playCount.put(songName, playCount.getOrDefault(songName, 0) + 1);
    }

    // Method to retrieve and display today's biggest hit
    void getTodaysBiggestHit() {
        String biggestHit = null;
        int maxPlays = 0;

         for (Map.Entry<String, Integer> entry : playCount.entrySet()) {
             String songName = entry.getKey();
             int totalPlays = entry.getValue();

             if (totalPlays > maxPlays) {
                maxPlays = totalPlays;
                biggestHit = songName;
             }
    }

         System.out.println("-------------------------------");
         if (biggestHit!= null) {
             System.out.println("| Today's Biggest Hit: " + biggestHit);
             System.out.println("| Play Count: " + maxPlays);
    }
    else {
             System.out.println("| No plays recorded today.");
    }
    System.out.println("-------------------------------");
    }


    // Main method
    public static void main(String[] args) {
        Music player = new Music();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            player.displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1: {
                    while (true) {
                    System.out.print("Enter song name (or type 'exit' to stop adding songs): ");
                    String name = scanner.nextLine();
                    if (name.equalsIgnoreCase("exit")) {
                        break;
                    }
                    System.out.print("Enter singer name: ");
                    String singer = scanner.nextLine();
                    System.out.print("Enter duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter lyrics: ");
                    String lyrics = scanner.nextLine();
                    player.home.insertLast(name, singer, duration, lyrics);
                    player.displayTotalSongsCount();
                }
                    break;

                }
                case 2: {
                    //player.home.display();
                    break;
                }
                case 3: {
                    player.createCustomPlaylistWithName();
                    break;
                }
                 case 4: {
                    System.out.println("-------------------------------");
                    System.out.println("| Available Playlists:");
                    System.out.println("-------------------------------");
                    for (int i = 0; i < player.playlistCount; i++) {
                         System.out.println("| " + (i + 1) + ". " + player.playlists[i].getName());
                         }
                        System.out.println("-------------------------------");
                        break;
                 }
                case 5: {
                    System.out.print("Enter song name to play: ");
                    String name = scanner.nextLine();
                    SongNode current = player.home.getHead();
                    while (current != null) {
                    if (current.name.equalsIgnoreCase(name)) {
                        System.out.println("-------------------------------");
                        System.out.println("| Playing song: " + current.name);
                        player.updatePlayCount(current.name); // Update play count
                        current.playCount++;
                        System.out.println("-------------------------------");
                        break;
            }
            current = current.next;
        }
        if (current == null) {
            System.out.println("-------------------------------");
            System.out.println("| Song not found.");
            System.out.println("-------------------------------");
        }
                    break;
                }
                case 6: {
                    player.home = player.home.sortByName();
                    System.out.println("-------------------------------");
                    System.out.printf("| %-25s |\n", "Song Name");
                    
                    System.out.println("-------------------------------");
                    player.home.displayNamesOnly();
                     break;
                }
               case 7: {
                     player.home = player.home.sortBySinger();
                     System.out.println("-------------------------------");
                     System.out.printf("| %-25s | %-20s |\n", "Song Name", "Singer");

                     System.out.println("-------------------------------");
                     player.home.displaySingersOnly(); // Display song names and singers
                     System.out.println("-------------------------------");
                     break;
}

                case 8: {
                     player.home = player.home.sortByDuration();
                     System.out.println("-------------------------------");
                     System.out.printf("| %-25s | %-10s |\n", "Song Name", "Duration");
                     System.out.println("-------------------------------");
                     player.home.displayDurationsOnly(); // Display song names and durations
                     System.out.println("-------------------------------");
                     break;
}
                case 9: {
                    System.out.print("Enter song name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter singer name: ");
                    String singer = scanner.nextLine();
                    System.out.print("Enter duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter lyrics: ");
                    String lyrics = scanner.nextLine();
                    player.home.insertFirst(name, singer, duration, lyrics);
                    System.out.println("------------------------------------------------");
                    System.out.println("Song added to the beginning of home playlist");
                    System.out.println("------------------------------------------------");

                    break;
                }
                case 10: {
                    System.out.print("Enter song name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter singer name: ");
                    String singer = scanner.nextLine();
                    System.out.print("Enter duration (in seconds): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter lyrics: ");
                    String lyrics = scanner.nextLine();
                    player.home.insertLast(name, singer, duration, lyrics);
                    System.out.println("-------------------------------------------");
                    System.out.println("Song added to the end of home playlist");
                    System.out.println("-------------------------------------------");

                    break;
                }
                case 11: {
                    System.out.print("Enter playlist number to display: ");
                    int num = scanner.nextInt();
                    if (num >= 0 && num < player.playlistCount) {
                       // player.playlists[num].display();
                        System.out.println("--------------------------------------------");
                        int playlistNumber = 0;
                        System.out.println("   *** Playlist " + playlistNumber + " ***");
                        System.out.println("--------------------------------------------");
                        //player.playlists[playlistNumber - 1].display();
                        System.out.println("-------------------------------");
                    } else {
                        System.out.println("-------------------------------");
                        System.out.println("| Invalid playlist number.");
                        System.out.println("-------------------------------");

                    }
                    break;
                }
                case 12: {
                    player.recommendSongs();
                    break;
                }
                case 13: {
                    System.out.print("Enter song name to display lyrics: ");
                    String name = scanner.nextLine();
                    SongNode current = player.home.getHead();
                    while (current != null) {
                        if (current.name.equalsIgnoreCase(name)) {
                            player.displayLyrics(current);
                            break;
                        }
                        current = current.next;
                    }
                    if (current == null) {
                       System.out.println("-------------------------------");
                       System.out.println("| Song not found.");
                       System.out.println("-------------------------------");

                    }
                    break;
                }
                case 14: {
                    System.out.print("Enter song name, singer, or lyrics to search: ");
                    String query = scanner.nextLine();
                    player.searchSong(query);
                    break;
                }
                case 15: {
                    player.addSongToPlaylist();
                    break;
                }
                case 16: {
                    System.out.print("Enter current song name: ");
                    String currentName = scanner.nextLine();
                    System.out.print("Enter next song name: ");
                    String nextName = scanner.nextLine();
                    SongNode current = player.home.getHead();
                    SongNode next = null;
                    while (current != null) {
                        if (current.name.equalsIgnoreCase(currentName)) {
                            next = current.next;
                            while (next != null) {
                                if (next.name.equalsIgnoreCase(nextName)) {
                                    player.crossfadePlay(current, next);
                                    break;
                                }
                                else
                                System.out.println("-------------------------------");
                                System.out.println("One or both songs not found");
                                System.out.println("-------------------------------");

                                next = next.next;
                            }
                            break;
                        }
                        current = current.next;
                    }
                    if (current == null || next == null) {
                        System.out.println("-------------------------------");
                        System.out.println("| Song not found.");
                        System.out.println("-------------------------------");

                    }
                    break;
                }
                case 17: {
                    player.displayTotalSongsCount();
                    break;
                }
                case 18:
                {
                     player.getTodaysBiggestHit();
                     break;
                }
                case 0: {
                    System.out.println("-------------------------------");
                    System.out.println("| Exiting the Music Player.");
                    System.out.println("-------------------------------");
                    scanner.close();
                    return;
                }
                default: {
                    System.out.println("-------------------------------");
                    System.out.println("| Invalid choice. Please try again.");
                    System.out.println("-------------------------------");
                }
            }
        }
    }
    
}
