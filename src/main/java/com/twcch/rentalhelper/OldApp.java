package com.twcch.rentalhelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OldApp {

    public static void printGuide() {
        System.out.println("歡迎使用Rent Helper，請選擇功能:");
        System.out.println("1. 陳列出所有租屋房屋");
        System.out.println("2. 房屋出租查詢");
        System.out.println("3. 租金試算");
        System.out.println("4. 離開");
    }

    public static void printHouseQuery() {
        System.out.println("請輸入物件索引: ");
    }

    public static void printHouseNotFound() {
        System.out.println("很抱歉，物件不存在!");
    }

    public static void printInvalidData() {
        System.out.println("資料無效!");
    }

    public static String getHouseString(int id, House house) {
        return "索引" + id + "物件 - " + house.getString();
    }

    public static ArrayList<House> readHouses(String path) throws FileNotFoundException {

        Scanner fileScanner = new Scanner(new File(path)); // 可能發生 exception ， exception 產生的原因會是外界傳入 path 錯誤導致，故該 exception 應由外界處理

        ArrayList<House> result = new ArrayList<>();

        int lineNo = 0;
        while (fileScanner.hasNextLine()) {
            lineNo++;
            String line = fileScanner.nextLine();

            if (lineNo == 1) { // 避免取到表頭
                continue;
            }

            String[] values = line.split(",");

            float area = Float.parseFloat(values[0]);
            String type = values[1];
            int price = Integer.parseInt(values[2]);
            String owner = values[3];
            String address = values[4];

            House house = new House(area, type, price, owner, address);
            result.add(house);
        }

        fileScanner.close(); // close scanner
        return result;

    }

    public static void main(String[] args) {

        String defaultPath = "D:\\Develop\\Source Code\\java-code\\java-rentalhelper\\src\\main\\resources\\houses.csv";
        List<House> houses = null;

        Scanner scanner = new Scanner(System.in);

        try {
            houses = readHouses(defaultPath);
        } catch (FileNotFoundException e) {
            System.out.println("檔案不存在預設路徑: " + defaultPath);
            System.out.println("請重新輸入csv檔路徑");
            /*
             * 不用 scanner.next 因為機制會取到空白為斷點，如果 path 中含有空白會導致路徑不完全
             * scanner.nextLine 不會有前述的問題
             */
            String inputPath = scanner.nextLine();
            try {
                houses = readHouses(inputPath);
            } catch (FileNotFoundException e2) {
                System.out.println("檔案不存在於路徑: " + inputPath);
                return;
            }
        }

        Collections.sort(houses, new HouseComparator());

        // while (true) 進行命名
        mainLoop:
        while (true) {

            printGuide();

            if (scanner.hasNextInt()) { // 判斷 user 的輸入值，須為 integer
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        for (int i = 0; i < houses.size(); i++) {
                            System.out.println(getHouseString(i, houses.get(i)));
                        }

                        break;
                    case 2: { // 建立 block ，可以讓 case 2 與 case 3 的變數屬於不同 block

                        printHouseQuery();

                        int index = scanner.nextInt();

                        if (!(index >= 0 && index < houses.size())) {
                            printHouseNotFound();
                            continue;
                        }

                        System.out.println(getHouseString(index, houses.get(index)));

                        break;
                    }
                    case 3: {
                        // 租金試算
                        printHouseQuery();

                        int index = scanner.nextInt();

                        if (!(index >= 0 && index < houses.size())) {
                            printHouseNotFound();
                            continue;
                        }

                        int monthlyPrice = houses.get(index).getPrice();

                        // 接收合約租期
                        System.out.println("請輸入合約租期(月): ");
                        int expectMonths = scanner.nextInt();

                        if (expectMonths < 0) {
                            printInvalidData();
                            continue;
                        }

                        // 接收壓金期數
                        System.out.println("請輸入押金期數(月): ");
                        int depositMonths = scanner.nextInt();

                        if (depositMonths < 0) {
                            printInvalidData();
                            continue;
                        }

                        // 接收實際租期
                        System.out.println("請輸入實際租期(月): ");
                        int actualMonths = scanner.nextInt();

                        if (actualMonths > expectMonths || actualMonths < 0) {
                            printInvalidData();
                            continue;
                        }

                        // 進行租金試算
                        int total = monthlyPrice * actualMonths
                                + ((actualMonths < depositMonths) ? (depositMonths * monthlyPrice) : 0);
                        float average = (float) total / actualMonths;
                        System.out.println("租金總額: " + total + " 元");
                        System.out.println("月平均租金: " + average + " 元");

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
