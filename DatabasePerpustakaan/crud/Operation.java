package crud;

import java.io.*;



public class Operation {

    public static void showListBook() throws IOException {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("database.txt");
            br = new BufferedReader(fr);

            String s, book = "";
            while((s = br.readLine()) != null) {
                book += s + "\n";
            }
            String[] books = book.split("\n");

            System.out.println();
            System.out.println("+----+----------------------+----------------------+----------------------------------------------------+");
            System.out.println("| NO | Author               | Publisher            | Title                                              |");
            System.out.println("+----+----------------------+----------------------+----------------------------------------------------+");
            for (int i = 0; i < books.length; i++) {
                String[] temp = books[i].split(";");
                System.out.printf("| %2d | %-20s | %-20s | %-50s |\n", i + 1, temp[0], temp[1], temp[2]);
            }
            System.out.println("+----+----------------------+----------------------+----------------------------------------------------+");
 
        } catch (Exception e) {
            System.err.println("\nDatabase file not found.");
        } finally {
            if (br != null) br.close();
            if (fr != null) fr.close();
        }
    }

    public static void searchBook() throws IOException {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("database.txt");
            br = new BufferedReader(fr);
        } catch (IOException e) {
            System.err.println("\nDatabase file not found.");
        } finally {
            if (br != null) br.close();
            if (fr != null) fr.close();
        }

        System.out.print("Input keyword => ");
        String key = Utility.sc.nextLine();
        String[] keywords = key.split(" ");

        checkBookInDatabase(keywords, false);
    }

    public static boolean checkBookInDatabase(String[] keywords, boolean isAdd) throws IOException {
        FileReader fr = new FileReader("database.txt");
        BufferedReader br = new BufferedReader(fr);

        String s, result = "";
        int i = 0;
        result += isAdd ? "\nThe book you wanna add is already in the database :\n" : "\nResult :\n";
        result += "+----+----------------------+----------------------+----------------------------------------------------+\n";
        result += "| NO | Author               | Publisher            | Title                                              |\n";
        result += "+----+----------------------+----------------------+----------------------------------------------------+\n";
        while ((s = br.readLine()) != null) {
            String[] temp = s.split(";");
            
            boolean isExist = true;            
            for (String str : keywords) {
                boolean getWord = false;
                for (String dataBook : temp) {
                    if (isAdd) {
                        if (dataBook.equalsIgnoreCase(str)) getWord = true;
                    } else {
                        String[] wordsData = dataBook.split(" ");
                        for (String word : wordsData) {
                            if (word.equalsIgnoreCase(str)) {
                                getWord = true;
                                break;
                            }
                        }
                    }
                    if (getWord) break;
                }
                isExist = isExist && getWord;
            }
            if (isExist) 
                result += String.format("| %2d | %-20s | %-20s | %-50s |\n", ++i, temp[0], temp[1], temp[2]);
            
        }
        br.close();
        result += "+----+----------------------+----------------------+----------------------------------------------------+";

        if (i == 0)
            if (!isAdd) {
                System.out.println("Book not found.");
                return false;
            } else return false;
        else {
            System.out.println(result);
            return true;
        }
    } 

    public static void addBook() throws IOException{
        FileWriter fileOutput = new FileWriter("database.txt", true);
        BufferedWriter bufferOutput = new BufferedWriter(fileOutput);

        System.out.print("Input author's name => ");
        String author = Utility.sc.nextLine();
        System.out.print("Input publisher => ");
        String publisher = Utility.sc.nextLine();
        System.out.print("Input book's title => ");
        String title = Utility.sc.nextLine();

        String[] keywords = {author, publisher, title};

        boolean isExist = checkBookInDatabase(keywords, true);
        if (!isExist){
            System.out.println("\nBook's data");
            System.out.println("------------------------------------------");
            System.out.println("Author     : " + author);
            System.out.println("Publisher  : " + publisher);
            System.out.println("Title      : " + title);
            System.out.println();

            boolean isAdd = Utility.getYesOrNo("Are you sure wanna add this book?");
            if (isAdd) {
                bufferOutput.write(author + ";" + publisher + ";" + title + ";");
                bufferOutput.newLine();
                bufferOutput.flush();
                showListBook();
                System.out.println(title + " has been added to the database.");
            }
        }
        bufferOutput.close();
        fileOutput.close();
    }

    public static void deleteBook() throws IOException {
        

        //show list book to delete
        showListBook();
        System.out.print("Input number book you want to delete = > ");
        int deleteBook = Utility.sc.nextInt();
        Utility.sc.nextLine();

        //reader to read original database
        File database = new File("database.txt");
        FileReader file_input = new FileReader(database);
        BufferedReader buffered_input = new BufferedReader(file_input);

        //writer to write data to temp Databse
        File tempDatabase = new File("tempDatabase.txt");
        FileWriter file_output = new FileWriter(tempDatabase);
        BufferedWriter buffered_output = new BufferedWriter(file_output);

        String s;
        int indexBook = 0;
        boolean deleteBookisFound = false;
        while ((s = buffered_input.readLine()) != null) {
            indexBook++;
            boolean isDelete = false;

            if (deleteBook == indexBook) {
                deleteBookisFound = true;
                String[] temp = s.split(";");

                System.out.println("\nBook's data you wanna delete :");
                System.out.println("------------------------------");
                System.out.println("Author    : " + temp[0]);
                System.out.println("Publisher : " + temp[1]);
                System.out.println("Title     : " + temp[2]);

                isDelete = Utility.getYesOrNo("Are you sure want to delete this book?");
            }

            if (isDelete) {
                System.out.println("Book has been deleted.");
            } else {
                buffered_output.write(s);
                buffered_output.newLine();
            }
        }
        if (!deleteBookisFound) System.out.println("Book not found.");
        buffered_output.flush();

        buffered_output.close();
        file_output.close();

        buffered_input.close();
        file_input.close();

        database.delete();
        tempDatabase.renameTo(database);

    }

    public static void updateData() throws IOException {
        File database = new File("database.txt");
        FileReader fileInput = new FileReader(database);
        BufferedReader bufferedInput = new BufferedReader(fileInput);

        File tempDatabase = new File("tempDatabase.txt");
        FileWriter fileOutput = new FileWriter(tempDatabase);
        BufferedWriter bufferedOutput = new BufferedWriter(fileOutput);

        showListBook();
        System.out.print("Input book number you want to edit => ");
        int editNumber = Utility.sc.nextInt();
        Utility.sc.nextLine();

        String data;
        int index = 0;
        while ((data = bufferedInput.readLine()) != null) {
            index++;

            if (editNumber == index) {
                String[] temp = data.split(";");
                System.out.println("\nBook's data you want to edit :");
                System.out.println("--------------------------------");
                System.out.println("Author    : " + temp[0]);
                System.out.println("Publisher : " + temp[1]);
                System.out.println("Title     : " + temp[2]);

                boolean isEdit = Utility.getYesOrNo("Do you want to edit this book?");
                if (isEdit) {
                    System.out.println();
                    String[] dataBook = {"Author", "Publisher", "Title"};
                    String[] editedDataBook = new String[3];
                    for (int i = 0; i < dataBook.length; i++) {
                        boolean editData = Utility.getYesOrNo("Do you want to change the " + dataBook[i] + "?");
                        if (editData) {
                            System.out.print("Input new " + dataBook[i] + " => ");
                            editedDataBook[i] = Utility.sc.nextLine();
                        } 
                        else editedDataBook[i] = temp[i];
                    }

                    System.out.println("\nEdit results");
                    System.out.println("-------------");
                    for (int i = 0; i < editedDataBook.length; i++) {
                        System.out.println(
                                        String.format("%-15s : %s --> %s", dataBook[i], temp[i], editedDataBook[i]));
                    }
                    boolean isUpdate = Utility.getYesOrNo("Are sure want to update this book?");
                    if (isUpdate)
                        bufferedOutput.write(editedDataBook[0] + ";" + editedDataBook[1] + ";" + editedDataBook[2] + ";");
                    else 
                        bufferedOutput.write(temp[0] + ";" + temp[1] + ";" + temp[2] + ";");
                } else 
                    bufferedOutput.write(temp[0] + ";" + temp[1] + ";" + temp[2] + ";"); 
            } else {
                bufferedOutput.write(data);
            }
            bufferedOutput.newLine();
        }
        bufferedOutput.flush();

        bufferedOutput.close();
        fileOutput.close();
        bufferedInput.close();
        fileInput.close();

        database.delete();
        tempDatabase.renameTo(database);
    }

}   
