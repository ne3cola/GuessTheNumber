import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReedAndRecordFiles reedAndRecordFiles = new ReedAndRecordFiles();
        String path = "src/resources/table.txt";
        List<Player> players = reedAndRecordFiles.reedFile(path);

        System.out.println("Welcome to game");

        label:
        while (true) {
            System.out.println("1. Play");
            System.out.println("2. Check records");
            System.out.println("3. Exit");

            String command = scanner.nextLine();

            switch (command) {
                case "2":
                    if (players.isEmpty()) {
                        System.out.println("Таблица пуста");
                    } else {
                        for (Player player : players) {
                            System.out.println(player.getName() + " - " + player.getScore());
                        }
                    }
                    break;
                case "1":
                    System.out.println("Enter you name");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println("Enter you number");
                    int number = new Random().nextInt(10);
                    int attempts = 1;
                    int score = 1000;
                    while (true) {
                        int userNumber = new Scanner(System.in).nextInt();
                        if (userNumber < number) {
                            System.out.println("Больше");
                            score -= 10;
                            attempts++;
                        } else if (userNumber > number) {
                            System.out.println("Меньше");
                            score -= 10;
                            attempts++;
                        } else {
                            System.out.println("Правильно");
                            if (attempts == 1) {
                                System.out.println("Это абсолютный рекорд вы угадали число с 1 попытки!!!!");
                            }
                            System.out.println("Количество попыток: " + attempts);
                            for (Player player : players) {
                                if (player.getName().equals(name) && player.getScore() < score) {
                                    players.remove(player);
                                }
                            }
                            players.add(new Player(name, score));
                            players.sort(Player::compareTo);
                            reedAndRecordFiles.recordFile(path);
                            break;
                        }
                    }
                    break;
                case "3":
                    System.out.println("GoodBye ^)");
                    break label;
            }
        }
    }
}

