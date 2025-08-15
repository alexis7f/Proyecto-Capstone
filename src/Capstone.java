public class Capstone {
    private int with;
    private int height;
    private int generations;
    private int delay;
    private String initialMapState;
    private int movement;

    Capstone(int with, int height, int generations, int delay, String initialMapState, int movement) {
        setHeight(height);
        setWith(with);
        setGenerations(generations);
        setDelay(delay);
        setInitialMapState(initialMapState);
        setMovement(movement);

    }


    public int getDelay() {
        return delay;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) { // filas
            for (int j = 0; j < matrix[i].length; j++) { // columnas
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setDelay(int delay) {
        final int[] ALLOWED_DELAY_VALUES = {0, 250, 500, 1000, 5000};
        boolean isDelayAllowed = false;
        for (int i = 0; i < ALLOWED_DELAY_VALUES.length; i++) {
            if (delay == ALLOWED_DELAY_VALUES[i]) {
                isDelayAllowed = true;
                break;
            }
        }
        if (isDelayAllowed) {
            this.delay = delay;
        } else {
            System.out.println("El valor del tiempo no esta permitido!");
        }
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        if (generations >= 0 && generations < 1000) {
            this.generations = generations;
        } else {
            System.out.println("El valor de las generaciones no esta permitido!");
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        final int[] ALLOWED_HEIGHT_VALUES = {5, 10, 15, 20, 40};
        boolean isHeightAllowed = false;
        for (int i = 0; i < ALLOWED_HEIGHT_VALUES.length; i++) {
            if (height == ALLOWED_HEIGHT_VALUES[i]) {
                isHeightAllowed = true;
                break;
            }
        }
        if (isHeightAllowed) {
            this.height = height;
        } else {
            System.out.println("El valor del alto no esta permitido!");
        }

    }

    public int getWith() {
        return with;
    }

    public void setWith(int with) {
        final int[] ALLOWED_WITH_VALUES = {5, 10, 15, 20, 40, 80};
        boolean isWithAllowed = false;
        for (int i = 0; i < ALLOWED_WITH_VALUES.length; i++) {
            if (height == ALLOWED_WITH_VALUES[i]) {
                isWithAllowed = true;
                break;
            }
        }
        if (isWithAllowed) {
            this.with = with;
        } else {
            System.out.println("El valor del ancho no esta permitido!");
        }
    }

    public String getInitialMapState() {
        return initialMapState;
    }

    public void setInitialMapState(String initialMapState) {
        if (initialMapState == "rnd") {
            //tarea ; ver como generar esto aleatoriamente , se puede usar math ramdom
            this.initialMapState = initialMapState;
        } else {
            int rowCounter = 0;
            int[][] completeInitialMap = new int[getHeight()][getWith()];
//h=3 w=2
            //h=filas
            //w=columnas
            // [[],[]]
            // [[],[]]
            // [[],[]]

            for (int i = 0; i < initialMapState.length(); i++) {
                if (initialMapState.charAt(i) == '#') {
                    rowCounter++;
                }
            }
            String[] initialMapRow = initialMapState.split("#", rowCounter);
            for (int i = 0; i < initialMapRow.length; i++) {
                System.out.println("Fila " + (i + 1) + ": '" + initialMapRow[i] + "'");
                if (initialMapRow[i].length() != this.with) {
                    if (initialMapRow[i].length() > this.with) {
                        System.out.println("Error en el mapa inicial hay una fila que sobrepasa los limitas del ancho");
                        System.out.println("La fila: " + initialMapRow[i] + " pasa el limite de la grilla que es de : " + this.with);
                        System.out.close();
                    } else {
                        int completeRowLength = this.with - initialMapRow[i].length();

                        if (completeRowLength > 0) {
                            String rowComplete = initialMapRow[i];
                            if (initialMapRow[i].equals("#")) {
                                rowComplete = "";
                                for (int j = 0; j < this.with; j++) {

                                    rowComplete = rowComplete + "0";
                                }
                            } else {
                                for (int j = 0; j < completeRowLength; j++) {

                                    rowComplete = rowComplete + "0";
                                }

                                for (int h = 0; h < this.with; h++) {

                                    completeInitialMap[i][h] =Integer.parseInt(String.valueOf(rowComplete.charAt(h)));
                                }
                            }


                        } else {
                            for (int h = 0; h < this.with; h++) {
                                completeInitialMap[i][h] = Integer.parseInt(String.valueOf(initialMapRow[i].charAt(h)));
                            }
                        }

                    }

                }

            }
            printMatrix(completeInitialMap);

        }

    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
}
