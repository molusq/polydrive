package mp3player;

import java.util.Scanner;

public class MusicOrganiserUI {

    public static void main(String[] args) {
        MusicOrganiser ms = new MusicOrganiser();
        System.out.println(ms);
        Scanner sc = new Scanner(System.in);
        int command;
        int command2;
        String fileName;
        boolean x = true;

        System.out.println("MusicOrganiser Initialised and ready to go!");


        while (x) {
            System.out.println("(1) Show all musics");
            System.out.println("(2) Add musics from directory");
            System.out.println("(3) Add music");
            System.out.println("(4) Remove music from directory");
            System.out.println("(5) Remove music");
            System.out.println("(6) Play sample");
            System.out.println("(7) Play music");
            System.out.println("(8) Stop music");
            System.out.println("(9) Exit");

            command = sc.nextInt();
            sc.nextLine();
            switch (command) {
                case 1 -> {
                    System.out.println(ms);
                    sc.nextLine();
                }
                case 2 -> {
                    System.out.print("Enter the directory name :");
                    String directoryName = sc.nextLine();
                    ms.addFilesFromDirectory(directoryName);
                    System.out.println("Files added");
                }
                case 3 -> {
                    System.out.print("Enter the file name :");
                    fileName = sc.nextLine();
                    ms.addFile(fileName);
                    System.out.println("File added");
                }
                case 4 -> {
                    System.out.print("Enter the directory name :");
                    String directoryName = sc.nextLine();
                    ms.removeFilesFromDirectory(directoryName);
                    System.out.println("Files removed");
                }
                case 5 -> {
                    System.out.print("Enter the file name :");
                    fileName = sc.nextLine();
                    ms.removeFile(fileName);
                    System.out.println("File removed");
                }
                case 6 -> {
                    System.out.println(ms);
                    System.out.print("Which music :");
                    command2 = sc.nextInt();
                    if(command2-1 > 0 && command2-1 <= ms.size()){
                        ms.playSample(command2);
                        System.out.println("Nom playing a sample of : " + ms.get(command2));
                    }else{
                        System.out.println("Invalid number");
                    }

                }
                case 7 -> {
                    System.out.println(ms);
                    System.out.print("Wich music :");
                    command2 = sc.nextInt();
                    if(command2-1 > 0 && command2-1 <= ms.size()) {
                        ms.playWholeFile(command2);
                        System.out.println("Nom playing : " + ms.get(command2));
                    }else{
                        System.out.println("Invalid number");
                    }
                }
                case 8 -> {
                    ms.stop();
                    System.out.println("Music stopped");
                }
                case 9 -> {
                    x = false;
                    System.out.println("Bye bye");
                }
                default -> System.out.println("Invalid command");
            }
        }
        sc.close();


    }

}
