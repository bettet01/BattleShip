package com.adonahue.battleship.ui;

import java.time.LocalDate;

/**
 * UserIO
 */
public interface UserIO {

    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);
    
    void insert(String text);
    
    String readStringNoSpace();

    LocalDate getRobotDate();
    
    void printNoSpace(String prompt);

    LocalDate readDate(String prompt);
}