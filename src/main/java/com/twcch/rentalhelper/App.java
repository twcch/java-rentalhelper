package com.twcch.rentalhelper;

import java.util.Scanner;

public class App {

    public static void printGuide() {

        System.out.println("歡迎使用Rent Helper，請選擇功能:");
        System.out.println("1. 陳列出所有租屋房屋");
        System.out.println("2. 房屋出租查詢");
        System.out.println("3. 租金試算");
        System.out.println("4. 離開");

    }

    public static void printHouseQuery() {

        System.out.println("請輸入物件名稱 (A or B or C): ");

    }

    public static void printHouseNotFound() {

        System.out.println("很抱歉，物件不存在!");

    }

    public static void printInvalidData() {

        System.out.println("資料無效!");

    }

    public static String getHouseString(String id, float area, String type, int price, String owner, String address) {

        return id + "物件 - " + area + "坪 - " + type + " - 每月" + price + "元 - 屋主: " + owner + " - 地址: " + address;

    }

    public static void main(String[] args) {

        // A 租屋物件資料
        float areaA = 10F;
        String typeA = "套房";
        int priceA = 12000;
        String ownerA = "王先生";
        String addressA = "台北市文山區木柵路一段xx號";

        // B 租屋物件資料
        float areaB = 8F;
        String typeB = "套房";
        int priceB = 8000;
        String ownerB = "陳先生";
        String addressB = "新北市中和區中和路yy號";

        // C 租屋物件資料
        float areaC = 4F;
        String typeC = "雅房";
        int priceC = 6000;
        String ownerC = "林先生";
        String addressC = "新北市新店區中正路aa巷zz號";

        String infoA = getHouseString("A", areaA, typeA, priceA, ownerA, addressA);
        String infoB = getHouseString("B", areaB, typeB, priceB, ownerB, addressB);
        String infoC = getHouseString("C", areaC, typeC, priceC, ownerC, addressC);

        Scanner scanner = new Scanner(System.in);

        // while (true) 進行命名
        mainLoop:
        while (true) {

            printGuide();

            if (scanner.hasNextInt()) { // 判斷 user 的輸入值，須為 integer
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println(infoA);
                        System.out.println(infoB);
                        System.out.println(infoC);
                        break;
                    case 2: { // 建立 block ，可以讓 case 2 與 case 3 的變數屬於不同 block

                        printHouseQuery();

                        String id = scanner.next();
                        switch (id) {
                            case "A":
                                System.out.println(infoA);
                                break;
                            case "B":
                                System.out.println(infoB);
                                break;
                            case "C":
                                System.out.println(infoC);
                                break;
                            default:
                                printHouseNotFound();
                                break;
                        }
                        break;
                    }
                    case 3: {
                        // 租金試算

                        printHouseQuery();

                        String id = scanner.next();
                        int monthlyPrice = 0;
                        switch (id) {
                            case "A":
                                monthlyPrice = priceA;
                                break;
                            case "B":
                                monthlyPrice = priceB;
                                break;
                            case "C":
                                monthlyPrice = priceC;
                                break;
                            default:
                                printHouseNotFound();
                                continue;
                        }

                        // 接收合約租期
                        System.out.println("請輸入合約租期(月): ");
                        int expectMonths = scanner.nextInt();
                        if (expectMonths < 0) {
                            printInvalidData();
                        } else {
                            // 接收壓金期數
                            System.out.println("請輸入押金期數(月): ");
                            int depositMonths = scanner.nextInt();
                            if (depositMonths < 0) {
                                printInvalidData();
                            } else {
                                // 接收實際租期
                                System.out.println("請輸入實際租期(月): ");
                                int actualMonths = scanner.nextInt();
                                if (actualMonths > expectMonths || actualMonths < 0) {
                                    printInvalidData();
                                } else {
                                    // 進行租金試算
                                    int total = monthlyPrice * actualMonths + ((actualMonths < depositMonths) ? (depositMonths * monthlyPrice) : 0);
                                    float average = (float) total / actualMonths;
                                    System.out.println("租金總額: " + total + " 元");
                                    System.out.println("月平均租金: " + average + " 元");
                                }
                            }
                        }

                        break;
                    }
                    case 4:
                        break mainLoop; // 中斷 mainLoop
                    default:
                        break;
                }
            } else {
                /*
                 * 移動參考點使用
                 * 當 user 輸入的值，非 integer 而跳過 nextInt 時，會導致 scanner 的參考點未移動，而進入無限迴圈
                 */
                scanner.next();
            }

        }

    }

}
