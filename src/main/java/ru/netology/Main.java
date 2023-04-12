package ru.netology;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static final BlockingQueue<String> textsQueueA = new ArrayBlockingQueue<>(100);
    public static final BlockingQueue<String> textsQueueB = new ArrayBlockingQueue<>(100);
    public static final BlockingQueue<String> textsQueueC = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {

        String[] texts = new String[10_000];

        Thread queue = new Thread(() -> {
            for (int i = 0; i < texts.length; i++) {
                texts[i] = generateText("abc", 100_000);
                try {
                    textsQueueA.put(texts[i]);
                    textsQueueB.put(texts[i]);
                    textsQueueC.put(texts[i]);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        queue.start();

        Thread queueA = new Thread(() -> {
            int counterA = 0;
            int counterMaxA = 0;
            // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
            // String textMaxA = null;

            for (int i = 0; i < texts.length; i++) {
                try {
                    String textA = textsQueueA.take();
                    for (int j = 0; j < textA.length(); j++) {
                        if (textA.charAt(j) == 'a') {
                            counterA++;
                        }
                    }
                    if (counterA > counterMaxA) {
                        // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
                        // textMaxA = textA;
                        counterMaxA = counterA;
                    }
                    counterA = 0;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Наибольшее количество букв а: " + counterMaxA);
            // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
            // System.out.println("Слово: " + textMaxB);
        });
        queueA.start();

        Thread queueB = new Thread(() -> {
            int counterB = 0;
            int counterMaxB = 0;
            // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
            // String textMaxB = null;

            for (int i = 0; i < texts.length; i++) {
                try {
                    String textB = textsQueueB.take();
                    for (int j = 0; j < textB.length(); j++) {
                        if (textB.charAt(j) == 'b') {
                            counterB++;
                        }
                    }
                    if (counterB > counterMaxB) {
                        // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
                        // textMaxB = textB;
                        counterMaxB = counterB;
                    }
                    counterB = 0;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Наибольшее количество букв b: " + counterMaxB);
            // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
            // System.out.println("Слово: " + textMaxB);
        });
        queueB.start();

        Thread queueC = new Thread(() -> {
            int counterC = 0;
            int counterMaxC = 0;
            // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
            // String textMaxC = null;

            for (int i = 0; i < texts.length; i++) {
                try {
                    String textC = textsQueueC.take();
                    for (int j = 0; j < textC.length(); j++) {
                        if (textC.charAt(j) == 'c') {
                            counterC++;
                        }
                    }
                    if (counterC > counterMaxC) {
                        // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
                        // textMaxC = textC;
                        counterMaxC = counterC;
                    }
                    counterC = 0;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Наибольшее количество букв c: " + counterMaxC);
            // Можно активировать для вывода на печать слова с максимальным количеством проверяемых символов
            // System.out.println("Слово: " + textMaxC);
        });
        queueC.start();

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}