package com.adonahue.battleship.ui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * UserIOImp
 */
public class UserIOImp implements UserIO {
    Scanner sc = new Scanner(System.in);
    static private Robot robot;
    String ERROR = "That was not a valid input, please try again.";

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.println(ERROR);
            }
        }
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        while (true) {
            try {
                System.out.println(prompt);
                double x = sc.nextDouble();
                if (x >= min && x <= max) {
                    return x;
                }
            } catch (Exception e) {
                System.out.println(ERROR);
                sc.nextLine();
            }
        }
    }

    @Override
    public float readFloat(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return sc.nextFloat();
            } catch (Exception e) {
                System.out.println(ERROR);
                sc.nextLine();
            }
        }
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        while (true) {
            try {
                System.out.println(prompt);
                float x = sc.nextFloat();
                if (x >= min && x <= max) {
                    return x;
                }
            } catch (Exception e) {
                System.out.println(ERROR);
                sc.nextLine();
            }
        }
    }

    @Override
    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int i = sc.nextInt();
                sc.nextLine();
                return i;
            } catch (Exception e) {
                System.out.println(ERROR);
                sc.nextLine();
            }
        }
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.println(prompt);
                int x = sc.nextInt();
                if (x >= min && x <= max) {
                    sc.nextLine();
                    return x;
                }
            } catch (Exception e) {
                System.out.println(ERROR);
                sc.nextLine();
            }
        }
    }

    @Override
    public long readLong(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return sc.nextLong();
            } catch (Exception e) {
                System.out.println(ERROR);
                sc.nextLine();
            }
        }
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        while (true) {
            try {
                System.out.println(prompt);
                long x = sc.nextLong();
                if (x >= min && x <= max) {
                    return x;
                }
            } catch (Exception e) {
                System.out.println(ERROR);
                sc.nextLine();
            }
        }
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String input = sc.nextLine();
        if (input.equals("")) {
            input = "N/A";
        }
        return input;
    }

    @Override
    public LocalDate readDate(String prompt) {
        System.out.println(prompt);
        LocalDate date;

        while (true) {
            try{
                date = LocalDate.parse(sc.nextLine());
                return date;
            } catch (Exception e) {
                System.out.println("That was not a valid date");
                System.out.println("Please enter a date in the format yyyy-MM-dd");
            }
        }        
    }

    @Override
    public void insert(String text) {
        if (robot == null) {
            try {
                robot = new Robot();
                robot.setAutoDelay(5);
                robot.setAutoWaitForIdle(true);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        char[] chars = text.toCharArray();
        for (char c : chars) {
            int code = KeyEvent.getExtendedKeyCodeForChar(c);
            // robot.keyRelease(16);
            if (Character.isUpperCase(c)) {
                robot.keyPress(16);
                robot.keyPress(code);
                robot.keyRelease(code);
                robot.keyRelease(16);
            } else {
                robot.keyPress(code);
                robot.keyRelease(code);
            }
        }
    }

    @Override
    public String readStringNoSpace() {
        return sc.nextLine();
    }

    @Override
    public void printNoSpace(String prompt) {
        System.out.print(prompt);
    }

    @Override
    public LocalDate getRobotDate() {
        LocalDate date = LocalDate.parse(sc.nextLine());
        return date;
    }
}