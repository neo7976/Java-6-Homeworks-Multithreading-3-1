
public class Main {

    public static volatile int value = 0;
    public static final int N = 10;
    public static final int TIME = 1000;

    public static void main(String[] args) {


        Thread player = new Thread(() -> {
            for (int i = 0; i < N; i++) {
                System.out.printf("Я - %s и повернул тумблер! Он активный - %d!\n", Thread.currentThread().getName(), value = 1);
                try {
                    Thread.sleep(TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("%s завершил свою работу\n", Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        });
        player.setName("Игрок-1");
        player.start();
        Thread toy = new Thread(() -> {
            while (!player.isInterrupted()) {
                if (value != 0) {
                    try {
                        Thread.sleep(TIME / 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("Я - %s и повернул тумблер! Он выключен - %d!\n", Thread.currentThread().getName(), value = 0);
                }
            }
            System.out.printf("%s завершил свою работу\n", Thread.currentThread().getName());
            System.out.println("Состояние тумблера: " + value);
        });
        toy.setName("TOY");
        toy.start();
    }
}
