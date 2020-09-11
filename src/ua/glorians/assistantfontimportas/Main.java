package ua.glorians.assistantfontimportas;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) {

        if (args != null) {
            String src = Arrays.toString(args);
            start(src);
        } else
            System.out.println("Данная директория не существует или файлы в ней отсутствуют");

    }

    public static void start(String src) {

        File[] arrayFiles = new File(src).listFiles();
        //Создаем файл, который имеет директорию

        if (arrayFiles != null) {
            for (File file : arrayFiles) {
                String filename = fileName(file.toString()); // Получаем имя файла
                String nameTrue = nameTrue(filename); // Обрабатываем имя, и получаем нужный результат
                File newFile = createNewFile(file.toString(), filename, nameTrue); //Создаем новый файл с новым названием
                rename(file, newFile); // Переменовуем файл с помощью нового файла
            }
        }
        else {
            System.out.println("no Files");
        }

    }


    private static String[] arrayDir(String src) {
        String[] arrayDir;
        String ch1 = "/";
        String ch2 = "\\\\";

        if (!src.contains(ch1))
            arrayDir = src.split(ch2);
        else
            arrayDir = src.split(ch2);
        return arrayDir;
    }

    private static String fileName(String src) {
        String[] array = arrayDir(src);
        String result = clearSymbol(array[array.length - 1]);
        System.out.println("RESULT " + result);
        return result;
    }

    private static String clearSymbol(String name) {
        String ch = "-";

        name.replaceAll(ch, "");

        return name;
    }

    private static String nameTrue(String name) {
        ArrayList<String> arrayPiece = new ArrayList<>();
        Pattern p = Pattern.compile("[A-Z]{1}[a-z]+");
        Matcher m = p.matcher(name);
        StringBuilder result = new StringBuilder();

        while (m.find()) {
            arrayPiece.add(m.group());
        }

        for (int i = 0; i < arrayPiece.size(); i++) {
            if (i + 1 == arrayPiece.size())
                result.append(arrayPiece.get(i));
            else
                result.append(arrayPiece.get(i)).append("_");
        }

        return result.toString().toLowerCase();
    }

    private static File createNewFile(String src, String name, String nameTrue) {
        src = src.replaceAll(name, nameTrue + ".ttf");
        File file = new File(src);
        return file;
    }

    private static void rename(File oldFile, File newFile) {
        oldFile.renameTo(newFile);
    }


}
