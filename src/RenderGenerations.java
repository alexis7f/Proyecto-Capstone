public class RenderGenerations {
    RenderGenerations(String[][] initialMapState, int generations, int delay, int movement) {
        String[][] map = initialMapState;
        System.out.println();
       for(int i = 0 ; i < map[0].length ; i++){
           System.out.print("➖\u200B");
       }
       System.out.println();
        if (generations == 0) {
            int i = 1;
            while (true) {
                System.out.println("Render " + i);

                try {
                    if (i == 1) {
                        printMatrixWithCoordinates(map);
                    } else if (typeOfGeneration(i)) {
                        String[][] newMap = pairGeneration(map, movement, i);
                        printMatrixWithCoordinates(newMap);
                        map = newMap;
                    } else {
                        String[][] newMap = inPairGeneration(map, i);
                        printMatrixWithCoordinates(newMap);
                        map = newMap;
                    }
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++; // aumenta el contador infinito
            }
        } else {
            for (int i = 1; i <= generations; i++) {
                System.out.println("Generación n° " + i);
                try {
                    if (i == 1) {
                        printMatrix(map);
                    } else if (typeOfGeneration(i)) {
                        String[][] newMap = pairGeneration(map, movement , i);
                        printMatrix(newMap);
                        map = newMap;
                    } else if (!typeOfGeneration(i)) {
                        String[][] newMap = inPairGeneration(map, i);
                        printMatrix(newMap);
                        map = newMap;
                    }
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0 ; i < map[0].length ; i++){
            System.out.print("➖\u200B");
        }


    }

    public String[][] pairGeneration(String[][] map, int movement , int generations ) {
        String[][] newMap = new String[map.length][map[0].length];
        // Copiar el mapa original
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                String value = map[i][j];
                //Se procesan las transformaciones de celdas vacías
                //(los animales no se reproducen
                //en estas generaciones)
                if (value.equals("0")) {
                    newMap = emptyBoxHandler(newMap, map, generations, i, j , false );
                }
                if (value.equals("1")) {
                    newMap = treeBoxHandler(newMap, map, i, j);
                }
                //Se evalúan las condiciones de supervivencia.
                if (value.equals("2")) {
                    newMap = animalBoxHandler(newMap, map, i, j);
                    // Los animales se mueven en la dirección especificada por el parámetro 'n'.
                    newMap = animalMovementHandler(newMap, map, movement);
                }
            }
        }

        return newMap;
    }

    public String[][] inPairGeneration(String[][] map,  int currentGeneration) {

        String[][] newMap = new String[map.length][map[0].length];
        // Copiar el mapa original
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                String value = map[i][j];
                //Se procesan las transformaciones de celdas vacías
                //reproduciomos los animales
                if (value.equals("0")) {
                    newMap = emptyBoxHandler(newMap, map, currentGeneration, i, j , true );
                }
                //Se evalúan las condiciones de supervivencia y reproducción.
                if (value.equals("1")) {
                    newMap = treeBoxHandler(newMap, map, i, j);
                }
                if (value.equals("2")) {
                    newMap = animalBoxHandler(newMap, map, i, j);
                    //Los animales NO se mueven.
                }
            }
        }

        return newMap;
    }

    public boolean typeOfGeneration(int generation) {
        return generation % 2 == 0;
    }

    public static void printMatrix(String[][] matrix) {
        //https://emojitool.com/nature
        final String[] REPRESENTATION_IN_ICONS = {"⬜", "\uD83C\uDF33", "\uD83D\uDC11", "\uD83D\uDCA7"};
        for (int i = 0; i < matrix.length; i++) { // filas
            for (int j = 0; j < matrix[i].length; j++) { // columnas
                switch (matrix[i][j]) {
                    case "0":
                        System.out.print(REPRESENTATION_IN_ICONS[0] + " ");
                        break;
                    case "1":
                        System.out.print(REPRESENTATION_IN_ICONS[1] + " ");
                        break;
                    case "2":
                        System.out.print(REPRESENTATION_IN_ICONS[2] + " ");
                        break;
                    case "3":
                        System.out.print(REPRESENTATION_IN_ICONS[3] + " ");
                        break;
                }

            }
            System.out.println();
        }
    }

    public static void printMatrixWithCoordinates(String[][] matrix) {
        // Íconos
        final String[] REPRESENTATION_IN_ICONS = {"⬜", "\uD83C\uDF33", "\uD83D\uDC11", "\uD83D\uDCA7"};

        for (int i = 0; i < matrix.length; i++) { // filas
            for (int j = 0; j < matrix[i].length; j++) { // columnas
                String icon = "";
                switch (matrix[i][j]) {
                    case "0":
                        icon = REPRESENTATION_IN_ICONS[0];
                        break;
                    case "1":
                        icon = REPRESENTATION_IN_ICONS[1];
                        break;
                    case "2":
                        icon = REPRESENTATION_IN_ICONS[2];
                        break;
                    case "3":
                        icon = REPRESENTATION_IN_ICONS[3];
                        break;
                }
                // Mostrar icono + coordenadas
                System.out.print(icon + "(" + i + "," + j + ") ");
            }
            System.out.println();
        }
    }

    public boolean isImmediateNeighborhood(int x, int y, int currentX, int currentY) {
        // Diferencia en filas y columnas
        int dx = currentX - x;
        if (dx < 0) dx = -dx;

        int dy = currentY - y;
        if (dy < 0) dy = -dy;

        // Es vecino inmediato si está a 1 o 0 de distancia en ambas direcciones,
        // pero no es la misma celda
        return (dx <= 1 && dy <= 1) && !(dx == 0 && dy == 0);
    }

    public boolean isExternalNeighborhood(int x, int y, int currentX, int currentY) {
        int dx = currentX - x;
        if (dx < 0) dx = -dx;

        int dy = currentY - y;
        if (dy < 0) dy = -dy;

        // vecinos externos: están en un radio de 2 pero no en el inmediato
        return (dx <= 2 && dy <= 2) && (dx > 1 || dy > 1);
    }

    public String[][] emptyBoxHandler(String[][] newMap, String[][] map, int generations, int i, int j ,boolean animalReproduction) {
        int treesInternal = 0;
        int treesExternal = 0;
        int animalInternal = 0;
        int waterInternal = 0;
        int waterExternal = 0;
        boolean isTopWaterInternal = false;
        for (int k = 0; k < map.length; k++) {
            for (int t = 0; t < map[k].length; t++) {
                if (isImmediateNeighborhood(k, t, i, j) && map[k][t].equals("1")) {
                    treesInternal++;
                }
                if (isExternalNeighborhood(k, t, i, j) && map[k][t].equals("1")) {
                    treesExternal++;
                }
                if (isImmediateNeighborhood(k, t, i, j) && map[k][t].equals("2")) {
                    animalInternal++;
                }
                if (isImmediateNeighborhood(k, t, i, j) && map[k][t].equals("3")) {
                    waterInternal++;
                    if (k == i - 1) {
                        isTopWaterInternal = true;
                    }
                }
                if (isExternalNeighborhood(k, t, i, j) && map[k][t].equals("3")) {
                    waterExternal++;
                }
            }
        }
        // Nace un árbol si hay 2 o más árboles en su vecindario inmediato.
        if (treesInternal >= 2) {
            newMap[i][j] = "1";
        }
        //Nace un animal si hay exactamente 2 animales en el anillo interno y al menos un
        // árbol y agua en cualquiera de los dos vecindarios. (Solo en generaciones impares)
        int waterTotal = waterExternal + waterInternal;
        int treeTotal = treesInternal + treesExternal;
        if (animalInternal == 2 && (waterTotal > 0) && (treeTotal > 0) && animalReproduction) {
                newMap[i][j] = "2";
        }
        //Nace agua si hay agua en alguna de las casillas de la fila de arriba del
        // vecindario inmediato y la generación es múltiplo de 3.
        if (isTopWaterInternal && generations % 3 == 0) {
            newMap[i][j] = "3";
        }

        return newMap;
    }

    public String[][] treeBoxHandler(String[][] newMap, String[][] map, int i, int j) {
        int waterInternal = 0;
        int waterExternal = 0;
        for (int k = 0; k < map.length; k++) {
            for (int t = 0; t < map[k].length; t++) {
                if (isImmediateNeighborhood(k, t, i, j) && map[k][t].equals("3")) {
                    waterInternal++;
                }
                if (isExternalNeighborhood(k, t, i, j) && map[k][t].equals("3")) {
                    waterExternal++;
                }
            }
        }

        // Sobreviven si hay al menos un cuerpo de agua en su vecindario inmediato o
        // externo.
        int neighborInfluence = waterInternal + waterExternal;
        if ((neighborInfluence == 0)) {
            newMap[i][j] = "0";
        }

        return newMap;
    }

    public String[][] animalBoxHandler(String[][] newMap, String[][] map, int i, int j) {
        int treesInternal = 0;
        int treesExternal = 0;
        int waterInternal = 0;
        int waterExternal = 0;
        for (int k = 0; k < map.length; k++) {
            for (int t = 0; t < map[k].length; t++) {
                if (isImmediateNeighborhood(k, t, i, j) && map[k][t].equals("1")) {
                    treesInternal++;
                }
                if (isExternalNeighborhood(k, t, i, j) && map[k][t].equals("1")) {
                    treesExternal++;
                }
                if (isImmediateNeighborhood(k, t, i, j) && map[k][t].equals("3")) {
                    waterInternal++;
                }
                if (isExternalNeighborhood(k, t, i, j) && map[k][t].equals("3")) {
                    waterExternal++;
                }
            }
        }

        // Sobreviven si hay al menos un árbol y al menos un cuerpo de agua en su
        // vecindario inmediato o externo.
        int waterTotal = waterExternal + waterInternal;
        int treeTotal = treesInternal + treesExternal;
        if (waterTotal == 0 || treeTotal == 0) {
            newMap[i][j] = "0";
        }


        return newMap;
    }

    public String[][] animalMovementHandler(String[][] newMap, String[][] map, int movement) {
        int movementY = 0; // filas
        int movementX = 0; // columnas

        switch (movement) {
            case 1:
                movementX = 1;
                break;   // derecha : mover columna
            case 2:
                movementY = 1;
                break;   // abajo : mover fila
            case 3:
                movementX = -1;
                break;  // izquierda
            case 4:
                movementY = -1;
                break;  // arriba
            default:
                break;
        }

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col].equals("2")) { // animal
                    int newRow = row + movementY;
                    int newCol = col + movementX;

                    // validar límites
                    if (newRow < 0 || newRow >= map.length || newCol < 0 || newCol >= map[0].length) {
                        continue;
                    }

                    String nextCell = map[newRow][newCol];

                    if (nextCell.equals("0") || nextCell.equals("1")) {
                        newMap[row][col] = "0";    // celda anterior vacía
                        newMap[newRow][newCol] = "2"; // mover animal
                    }
                }
            }
        }

        return newMap;
    }


}
