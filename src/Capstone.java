public class Capstone {
    private int width;
    private int height;
    private int generations;
    private int delay;
    private int movement;

    Capstone(int with, int height, int generations, int delay, String initialMapState, int movement) {
        boolean isHeightAllowed = setHeight(height);
        boolean isWithAllowed = setWidth(with);
        boolean isGenerationsAllowed = setGenerations(generations);
        boolean isDelayAllowed = setDelay(delay);
        setMovement(movement);

        if (isHeightAllowed && isWithAllowed && isGenerationsAllowed && isDelayAllowed) {
            String[][] completeInitialMap = setInitialMapState(initialMapState);
            new RenderGenerations(completeInitialMap , getGenerations(), getDelay() , movement);
        } else {
            System.out.println("Capstone se a cerrado");
            System.out.close();
        }

    }

    public int getDelay() {
        return delay;
    }

    public boolean setDelay(int delay) {
        final int[] ALLOWED_DELAY_VALUES = {0, 250, 500, 1000, 5000};
        boolean isDelayAllowed = false;
        for (int i = 0; i < ALLOWED_DELAY_VALUES.length; i++) {
            if (delay == ALLOWED_DELAY_VALUES[i]) {
                isDelayAllowed = true;
                break;
            }
        }
        if (isDelayAllowed) {
            if (delay == 0) {
                System.out.println("speed = Infinito");
            } else {
                this.delay = delay;
                System.out.println("speed = " + getDelay());
            }
        } else {
            System.out.println("speed = [Invalido]");
        }
        return isDelayAllowed;
    }

    public int getGenerations() {
        return generations;
    }

    public boolean setGenerations(int generations) {
        boolean isGenerationsAllowed = false;
        if (generations >= 0 && generations < 1000) {
            this.generations = generations;
            isGenerationsAllowed = true;
            System.out.println("generations = " + getGenerations());
        } else if(generations == -1) {
            System.out.println("generations = [No presente]");
        } else {
            System.out.println("generations = [Invalido]");
        }
        return isGenerationsAllowed;
    }

    public int getHeight() {
        return height;
    }

    public boolean setHeight(int height) {
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
            System.out.println("height = " + getHeight());
        } else {
            System.out.println("height = [Invalido]");
        }
        return isHeightAllowed;
    }

    public int getWidth() {
        return width;
    }

    public boolean setWidth(int width) {
        final int[] ALLOWED_WITH_VALUES = {5, 10, 15, 20, 40, 80};
        boolean isWithAllowed = false;
        for (int i = 0; i < ALLOWED_WITH_VALUES.length; i++) {
            if (width == ALLOWED_WITH_VALUES[i]) {
                isWithAllowed = true;
                break;
            }
        }
        if (isWithAllowed) {
            this.width = width;
            System.out.println("width = " + getWidth());
        } else {
            System.out.println("width = [Invalido]");
        }
        return isWithAllowed;
    }

    public String[][] setInitialMapState(String initialMapState) {
        String[][] completeInitialMap = new String[getHeight()][getWidth()];
        if (initialMapState.isEmpty()) {
            System.out.println("map = [No  Presente]");
        } else if (initialMapState.equals("rnd")) {
            for (int i = 0; i < getHeight(); i++) {
                for (int j = 0; j < getWidth(); j++) {
                    int num = (int) (Math.random() * 4); // 0, 1, 2 o 3
                    completeInitialMap[i][j] = num + "";
                }
            }
        } else {
            String[] initialMapRow = initialMapState.split("#", getWidth());

            for (int i = 0; i < initialMapRow.length; i++) {
                //Validaciones
                if (initialMapRow[i].length() > getWidth()) {
                    System.out.println("En el mapa inicial hay una fila que sobrepasa los limitas del ancho");
                    System.out.println("La fila: " + initialMapRow[i] + " pasa el limite de la grilla que es de : " + getWidth());
                    System.out.close();
                }

                if (initialMapRow.length > getHeight()) {
                    System.out.println("En el mapa inicial hay una columna que sobrepasa los limitas del alto");
                    System.out.println("Hay " + initialMapRow.length + " filas que sobrepasan el alto de la grilla que es de : " + getHeight());
                    System.out.close();
                }


                if (initialMapRow[i].isEmpty()) {
                    for (int j = 0; j < getWidth(); j++) {
                        initialMapRow[i] = initialMapRow[i] + "0";
                    }
                }
                if (initialMapRow[i].length() != getWidth()) {
                    int different = getWidth() - initialMapRow[i].length();
                    for (int j = 0; j < different; j++) {
                        initialMapRow[i] = initialMapRow[i] + "0";
                    }
                }
                for (int j = 0; j < getWidth(); j++) {
                    completeInitialMap[i][j] = initialMapRow[i].substring(j, j + 1);
                }

            }

        }
        return completeInitialMap;
    }

    public int getMovement() {
        return movement;
    }

    public boolean setMovement(int movement) {
        final int[] ALLOWED_MOVEMENT_VALUES = {1, 2, 3, 4};
        boolean isMovementAllowed = false;
        for (int i = 0; i < ALLOWED_MOVEMENT_VALUES.length; i++) {
            if (movement == ALLOWED_MOVEMENT_VALUES[i]) {
                isMovementAllowed = true;
                break;
            }
        }
        if (isMovementAllowed) {
            this.movement = movement;
            System.out.println("n = " + getMovement());
        } else if(movement == -1){
            this.movement = 1;
            System.out.println("n = " + getMovement());
        }
        else {
            System.out.println("n = [Invalido]");
        }
        return isMovementAllowed;
    }
}
