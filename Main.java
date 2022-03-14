import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Random random = new Random();
    private static final Scanner sc = new Scanner(System.in);

    private static final String[] frutas = {
            "Abacate", "Banana", "Caju", "Damasco", "Embaúba", "Figo", "Goiaba", "Hilocéreo",
            "Imbu", "Jabuticaba", "Kiwi", "Laranja", "Maçã", "Nectarina", "Oiti", "Pera"
    };

    private static final byte[] grupoA = {8, 9, 10, 11, 12, 13, 14, 15};
    private static final byte[] grupoB = {4, 5,  6,  7, 12, 13, 14, 15};
    private static final byte[] grupoC = {2, 3,  6,  7, 10, 11, 14, 15};
    private static final byte[] grupoD = {1, 3,  5,  7,  9, 11, 13, 15};

    public static void main(String[] args) {

        while (true) {

            embaralha();

            mostrarGrupos();

            System.out.println();
            System.out.println("::::::: A D I V I N H A Ç Ã O :::::::");
            System.out.println();
            System.out.println("1. Escolha uma fruta da tela");
            System.out.println("2. Digite, separado por espaço, as letras dos grupos");
            System.out.println("em que aparecem a fruta que você escolheu.");
            System.out.println();
            System.out.println("Digite as letras (exemplo: A B) - Digite fim para encerrar.");
            System.out.print("? ");

            String digitado = sc.nextLine().toUpperCase();
            if ("FIM".equals(digitado)) break;

            String[] grupos = digitado.split("( ?[,;-] ?| +)");
            byte indexFruta = 0;

            for (String letra : grupos) {

                int n = "DCBA".indexOf(letra);

                if (n < 0) {
                    indexFruta = -1;
                    break;
                }

                indexFruta += 0b00000001 << n;     // desloca n bits à esquerda (signed left shift)

            }

            if (indexFruta < 0)
                System.err.println("\nInforme apenas as letras A, B, C e/ou D separadas por espaço.");
            else
                System.err.println("[ " + frutas[indexFruta] + " ]  <<< SUA ESCOLHA!");

            System.out.println("\nTecle ENTER para prosseguir...");
            sc.nextLine();

        }

    }

    private static void mostrarGrupos() {
        System.out.println("\n---//" + "-".repeat(70) + "\n");
        System.out.println(("   +" + "-".repeat(12) + "+").repeat(4));
        System.out.printf("   | %-10s |".repeat(4) + "%n", "Grupo A", "Grupo B", "Grupo C", "Grupo D");
        System.out.println(("   +" + "-".repeat(12) + "+").repeat(4));

        for (byte i = 0; i < 8; i++) {

            System.out.printf(
                    "   | %-10s |   | %-10s |   | %-10s |   | %-10s |%n",
                    frutas[grupoA[i]], frutas[grupoB[i]], frutas[grupoC[i]], frutas[grupoD[i]]
            );

        }

        System.out.println(("   +" + "-".repeat(12) + "+").repeat(4));
    }

    private static void embaralha() {
        int count = frutas.length;
        for (int i = count; i > 1; i--) {
            swap(i - 1, random.nextInt(i));
        }
    }

    private static void swap(int index, int newIndex) {
        String temp = frutas[index];
        frutas[index] = frutas[newIndex];
        frutas[newIndex] = temp;
    }

}
