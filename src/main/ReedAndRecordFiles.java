import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReedAndRecordFiles {
    private List<Player> players = new ArrayList<>();

    public List<Player> reedFile(String path) {

        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            for (String string : lines) {
                String[] items = string.split(" - ");
                players.add(new Player(items[0], Integer.parseInt(items[1])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    public void recordFile(String path) {
        try {
            PrintWriter writer = new PrintWriter(path);
            for (Player player : players) {
                writer.write(player.getName() + " - " + player.getScore() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


