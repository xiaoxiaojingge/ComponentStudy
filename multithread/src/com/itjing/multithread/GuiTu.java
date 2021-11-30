package com.itjing.multithread;

/**
 * 模拟龟兔赛跑案例
 */
public class GuiTu {
    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}

class Race implements Runnable {
    //胜利者
    //定义为静态，保证胜利者只有一个
    private static String winner;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            boolean flag = gameOver(i);
            if (flag) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "--->跑了" + i + "步");
        }
    }

    //判断是否完成比赛
    public boolean gameOver(int steps) {
        //判断是否有胜利者
        if (null != winner) {//已经存在胜利者了
            return true;
        }
        {
            if (steps >= 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            }
        }
        return false;
    }
}
