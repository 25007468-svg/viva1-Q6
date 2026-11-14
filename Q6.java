import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter robot commands: ");
        String commands = scanner.nextLine();

        // Validate the command string
        for (int i = 0; i < commands.length(); i++) {
            char c = commands.charAt(i);
            if ("FBLRfblr".indexOf(c) == -1) {
                System.out.println("Invalid Command String");
                return;
            }
        }

        int xP = 0, yP = 0, dirP = 0;
        int xQ = 0, yQ = 0, dirQ = 0;
        char[] dirs = {'N', 'E', 'S', 'W'};

        int meetings = 0;
        int originReturns = 0;
        int maxGap = 0;

        String simplified = "";

        for (int i = 0; i < commands.length(); i++) {
            char c = commands.charAt(i);

            if (Character.isUpperCase(c)) {
                if (c == 'F' || c == 'B') {
                    int step;
                    if (c == 'F') {
                        step = 1;
                    } else {
                        step = -1;
                    }

                    if (dirP == 0) {
                        yP += step;
                    } else if (dirP == 1) {
                        xP += step;
                    } else if (dirP == 2) {
                        yP -= step;
                    } else if (dirP == 3) {
                        xP -= step;
                    }

                    if (xP == xQ && yP == yQ) {
                        meetings++;
                    }
                    if (xP == 0 && yP == 0) {
                        originReturns++;
                    }
                    int gap = Math.abs(xP - xQ) + Math.abs(yP - yQ);
                    if (gap > maxGap) {
                        maxGap = gap;
                    }
                } else if (c == 'R') {
                    dirP = (dirP + 1) % 4;
                } else if (c == 'L') {
                    dirP = (dirP + 3) % 4;
                }
            } else {
                if (c == 'f' || c == 'b') {
                    int step;
                    if (c == 'f') {
                        step = 1;
                    } else {
                        step = -1;
                    }

                    if (dirQ == 0) {
                        yQ += step;
                    } else if (dirQ == 1) {
                        xQ += step;
                    } else if (dirQ == 2) {
                        yQ -= step;
                    } else if (dirQ == 3) {
                        xQ -= step;
                    }

                    if (xP == xQ && yP == yQ) {
                        meetings++;
                    }
                    if (xQ == 0 && yQ == 0) {
                        originReturns++;
                    }
                    int gap = Math.abs(xP - xQ) + Math.abs(yP - yQ);
                    if (gap > maxGap) {
                        maxGap = gap;
                    }
                } else if (c == 'r') {
                    dirQ = (dirQ + 1) % 4;
                } else if (c == 'l') {
                    dirQ = (dirQ + 3) % 4;
                }
            }

            int len = simplified.length();
            if (len > 0 && isOpposite(simplified.charAt(len - 1), c)) {
                String temp = "";
                for (int j = 0; j < len - 1; j++) {
                    temp = temp + simplified.charAt(j);
                }
                simplified = temp;
            } else {
                simplified = simplified + c;
                len = simplified.length();
                if (len >= 4 && isTurn(c)) {
                    if (simplified.charAt(len - 2) == c &&
                        simplified.charAt(len - 3) == c &&
                        simplified.charAt(len - 4) == c) {
                        String temp = "";
                        for (int j = 0; j < len - 4; j++) {
                            temp = temp + simplified.charAt(j);
                        }
                        simplified = temp;
                    }
                }
            }
        }

        String finalSimplified;
        if (simplified.length() == 0) {
            finalSimplified = "EMPTY";
        } else {
            finalSimplified = simplified;
        }

        System.out.println("Meetings: " + meetings);
        System.out.println("Origin Returns: " + originReturns);
        System.out.println("Maximum Gap: " + maxGap);
        System.out.println("Robot P Final: (" + xP + "," + yP + ") facing " + dirs[dirP]);
        System.out.println("Robot Q Final: (" + xQ + "," + yQ + ") facing " + dirs[dirQ]);
        System.out.println("Simplified Commands: " + finalSimplified);

        scanner.close();
    }

    private static boolean isOpposite(char a, char b) {
        return (a == 'F' && b == 'B') || (a == 'B' && b == 'F') ||
               (a == 'L' && b == 'R') || (a == 'R' && b == 'L') ||
               (a == 'f' && b == 'b') || (a == 'b' && b == 'f') ||
               (a == 'l' && b == 'r') || (a == 'r' && b == 'l');
    }

    private static boolean isTurn(char c) {
        return c == 'L' || c == 'R' || c == 'l' || c == 'r';
    }
}
