package com.twcch.rentalhelper;

import java.util.Scanner;

public class App {

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

        String infoA = "A物件 - " + areaA + "坪 - " + typeA + " - 每月" + priceA + "元 - 屋主: " + ownerA + " - 地址: " + addressA;
        String infoB = "B物件 - " + areaB + "坪 - " + typeB + " - 每月" + priceB + "元 - 屋主: " + ownerB + " - 地址: " + addressB;
        String infoC = "C物件 - " + areaC + "坪 - " + typeC + " - 每月" + priceC + "元 - 屋主: " + ownerC + " - 地址: " + addressC;

        Scanner scanner = new Scanner(System.in);

        // while (true) 進行命名
        mainLoop:
        while (true) {
            System.out.println("歡迎使用Rent Helper，請選擇功能:");
            System.out.println("1. 陳列出所有租屋房屋");
            System.out.println("2. 房屋出租查詢");
            System.out.println("3. 離開");

            if (scanner.hasNextInt()) { // 判斷 user 的輸入值，須為 integer
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println(infoA);
                        System.out.println(infoB);
                        System.out.println(infoC);
                        break;
                    case 2:
                        System.out.println("請輸入物件名稱 (A or B or C)");
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
                                System.out.println("很抱歉，物件不存在!");
                                break;
                        }
                        break;
                    case 3:
                        break mainLoop; // 中斷 mainLoop
                    default:
                        break;
                }
            } else {
                /*
                 * 移動參考點使用
                 * 當 user 輸入的值，非 integer 而跳過 nextInt 時，會導致 scanner 的參考點未移動，而進入無限迴圈
                 */
                String badOption = scanner.next();
            }
        }

    }

}
