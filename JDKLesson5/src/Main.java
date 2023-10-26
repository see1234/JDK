import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Filosovi[] filosovi = new Filosovi[numPhilosophers];
        List<String> names = Arrays.asList("Дмитрий", "Алиса", "Макар", "Татьяна", "Мария", "Ольга", "Альберт");
        for (int i = 0; i < numPhilosophers; i++) {
            int rnd = new Random().nextInt(names.size());
            String name = names.get(rnd);
            filosovi[i] = new Filosovi(name, i, filosovi);
            new Thread(filosovi[i]).start();
        }
    }
}
class Filosovi extends Thread {
    private final String name;
    private final int id;
    private volatile int eatCount;
    private final Filosovi[] neighbors;

    public Filosovi(String name, int id, Filosovi[] neighbors) {
        this.id = id;
        this.name = name;
        this.neighbors = neighbors;
        this.eatCount = 0;
    }

    public void run() {
        while (eatCount < 3) {
            think();
            int leftNeighbor = (id + 1) % neighbors.length;
            int rightNeighbor = (id + neighbors.length - 1) % neighbors.length;
            if (id % 2 == 0) {
                synchronized (neighbors[leftNeighbor]) {
                    synchronized (neighbors[rightNeighbor]) {
                        eat();
                        eatCount++;
                    }
                }
            } else {
                synchronized (neighbors[rightNeighbor]) {
                    synchronized (neighbors[leftNeighbor]) {
                        eat();
                        eatCount++;
                    }
                }
            }
        }
    }

    private void think() {
        System.out.println(String.format("%s [%d]: размышляет", name, id));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        System.out.println(String.format("%s [%d]: кушает %d раз", name, id, eatCount));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

