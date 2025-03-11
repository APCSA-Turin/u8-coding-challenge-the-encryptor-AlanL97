package com.example.project;
import java.util.ArrayList;
import java.util.Arrays;

public class Encryptor {
    
    public static int determineColumns(int messageLen, int rows){
        if (messageLen%rows == 0 && messageLen > 0) {
            return messageLen/rows;
        } else {
            return messageLen/rows+1;
        }
    }
    
    public static String[][] generateEncryptArray(String message, int rows) {
        int col = determineColumns(message.length(), rows);
        int count = 0;
        String[][] strArr = new String[rows][col];
        for (int i = 0; i < strArr.length; i++) {
            for (int j = 0; j < strArr[0].length; j++) {
                if (count >= message.length()) {
                    strArr[i][j] = "=";
                } else {
                    strArr[i][j] = message.substring(count, count+1);
                }
                count++;
            }
        }
        return strArr;
    }

    public static String encryptMessage(String message, int rows){
        String[][] strArr = generateEncryptArray(message, rows);
        String encryptedMessage = "";
        for (int i = strArr[0].length-1; i >= 0; i--) {
            for (int j = 0; j < strArr.length; j++) {
                encryptedMessage += strArr[j][i];
            }
        }
        return encryptedMessage;
    }

    public static String decryptMessage(String encryptedMessage, int rows) {
        int count = encryptedMessage.length()-1;
        int col = determineColumns(encryptedMessage.length(), rows);
        String[][] strArr = new String[rows][col];
        String message = "";
        if (rows*col != encryptedMessage.length()) {
            return message;
        }
        for (int i = 0; i < strArr[0].length; i++) {
            for (int j = strArr.length-1; j >= 0; j--) {
                strArr[j][i] = encryptedMessage.substring(count, count+1);
                count--;
            }
        }
        for (String[] row : strArr) {
            for (String str : row) {
                if (str.equals("=")) {
                    break;
                }
                message += str;
            }
        }
        return message;
    }
}