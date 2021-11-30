package com.itjing.syn;

import java.util.concurrent.TimeUnit;

/**
 * 不安全的取钱
 * 两个人去银行取钱，账户
 */
public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100, "结婚基金");

        Drawing you = new Drawing(account, 50, "你");
        Drawing girlFriend = new Drawing(account, 100, "girlFriend");

        you.start();
        girlFriend.start();

    }
}

//账户
class Account {
    int money;//余额
    String name;//卡名

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread {
    Account account;//账户
    int drawingMoney;//取了多少钱
    int nowMoney;//现在手里有多少钱


    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    @Override
    public void run() {
//        synchronized 默认锁的是this
        synchronized (account) {
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "--->钱不够，取不了");
                return;
            }

            //模拟延时，可以放大问题的发生性
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //卡内余额 = 金额 - 你取的钱
            account.money = account.money - drawingMoney;
            //你手里的钱
            nowMoney = nowMoney + drawingMoney;
            System.out.println(account.name + "余额为:" + account.money);
//        System.out.println(this.getName());
            System.out.println(Thread.currentThread().getName() + "手里的钱：" + nowMoney);
        }

    }
}

