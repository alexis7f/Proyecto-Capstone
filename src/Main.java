//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        final char[] VALIDATED_KEYS = {'w', 'h', 'g', 's', 'm', 'n'};
        int with = -1, height = -1, generations=-1 , delay = -1, movement = -1;
        String initialMapState = "";

        for (int i = 0; i < args.length; i++) {
            boolean isParameterValid = false;
            for (int j = 0; j < args[i].length(); j++) {
                char argChart = args[i].toLowerCase().charAt(j);
                //Esto es para validar que no me pasen los parametros mal formateados
                // y tambien para evitar este caso w==10
                if (j == 1 && argChart != '=' && args[i].toLowerCase().charAt(j + 1) == '=') {
                    System.out.println("Los argumentos estan mal escritos :");
                    System.out.println("Los parametros deberian de tener este formato x=cccc");
                    System.out.println("Se paso : " + args[i]);
                    break;
                } else if (j == 0) {
                    for (int k = 0; k < VALIDATED_KEYS.length; k++) {
                        if (argChart == VALIDATED_KEYS[k]) {
                            isParameterValid = true;
                        }
                    }
                }
            }
            if (isParameterValid) {
                String[] parameterParts = args[i].split("=");
                switch (parameterParts[0].toLowerCase()) {
                    case "w":
                        with = Integer.parseInt(parameterParts[1]);
                        break;
                    case "h":
                        height = Integer.parseInt(parameterParts[1]);
                        break;
                    case "g":
                        generations = Integer.parseInt(parameterParts[1]);
                        break;
                    case "s":
                        delay = Integer.parseInt(parameterParts[1]);
                        break;
                    case "m":
                        initialMapState = parameterParts[1];
                        break;
                    case "n":
                        movement = Integer.parseInt(parameterParts[1]);
                        break;
                }
            } else {
                System.out.println("Los parametros estan mal pasados !");
            }
        }
         new Capstone(with ,height , generations , delay , initialMapState , movement);

    }
}