package javaBasic;

public class Topic_05_Condition {
    static void main(String[] args) {
        String fullName = "Nguyen Van Linh";
        //if
        if (fullName.equals("Nguyen Van Linh")) {
            System.out.println("Tôi là học sinh được chọn");
        }
        //if-else
        String browserName = "Chromium";
        if (browserName.equals("Chromium")) {
            System.out.println("Init Chrome or Edge browser");
        } else {
            System.out.println("Init Firefox or Edge browser");
        }

        //if-else - if-else
        float point = 7.6f;
        if (point >= 8.5){
            System.out.println("Điểm A");
        } else if (point < 8.5 && point >= 7.5){
            System.out.println("Điểm B");
        } else if (point < 7.5 && point >= 5.5){
            System.out.println("Điểm C");
        } else {
            System.out.println("Điểm D");
        }

        //switch - case
        String environmentName = "QA";
        String url = null;
        switch (environmentName){
            case "local":
                url = "https://loccalhost:9999/";
                break;
            case "Dev":
                url = "https://dev.rode.com/";
                break;
            case "QA":
                url = "https://qa.rode.com/";
                break;
            case "Prod":
                url = "https://rode.com/";
                break;
            default:
                throw new RuntimeException("Invironment name is not valid");
        }
        System.out.println(url);

    }

}
