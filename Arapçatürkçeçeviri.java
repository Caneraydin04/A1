
package çeviri;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;


public class Arapçatürkçeçeviri {
   
    // Arapça harfleri Türkçe harflere çeviren sözlük
    private static final HashMap<Character, String> arapcaToTurkce = new HashMap<>();
    // Türkçe harfleri Arapça harflere çeviren sözlük
    private static final HashMap<Character, String> turkceToArapca = new HashMap<>();

    static {
        // Arapça > Türkçe harf çevirileri
        arapcaToTurkce.put('ا', "a");
        arapcaToTurkce.put('ب', "b");
        arapcaToTurkce.put('ت', "t");
        arapcaToTurkce.put('ث', "s");
        arapcaToTurkce.put('ج', "c");
        arapcaToTurkce.put('ح', "h");
        arapcaToTurkce.put('خ', "h");
        arapcaToTurkce.put('د', "d");
        arapcaToTurkce.put('ذ', "z");
        arapcaToTurkce.put('ر', "r");
        arapcaToTurkce.put('ز', "z");
        arapcaToTurkce.put('س', "s");
        arapcaToTurkce.put('ش', "ş");
        arapcaToTurkce.put('ص', "s");
        arapcaToTurkce.put('ض', "d");
        arapcaToTurkce.put('ط', "t");
        arapcaToTurkce.put('ظ', "z");
        arapcaToTurkce.put('ع', "a");
        arapcaToTurkce.put('غ', "g");
        arapcaToTurkce.put('ف', "f");
        arapcaToTurkce.put('ق', "k");
        arapcaToTurkce.put('ك', "k");
        arapcaToTurkce.put('ل', "l");
        arapcaToTurkce.put('م', "m");
        arapcaToTurkce.put('ن', "n");
        arapcaToTurkce.put('ه', "h");
        arapcaToTurkce.put('و', "v");
        arapcaToTurkce.put('ي', "y");

        // Türkçe > Arapça harf çevirileri
        turkceToArapca.put('a', "ا");
        turkceToArapca.put('b', "ب");
        turkceToArapca.put('c', "ج");
        turkceToArapca.put('d', "د");
        turkceToArapca.put('f', "ف");
        turkceToArapca.put('g', "غ");
        turkceToArapca.put('h', "ه");
        turkceToArapca.put('k', "ك");
        turkceToArapca.put('l', "ل");
        turkceToArapca.put('m', "م");
        turkceToArapca.put('n', "ن");
        turkceToArapca.put('r', "ر");
        turkceToArapca.put('s', "س");
        turkceToArapca.put('ş', "ش");
        turkceToArapca.put('t', "ت");
        turkceToArapca.put('v', "و");
        turkceToArapca.put('y', "ي");
        turkceToArapca.put('z', "ز");
    }

    public static void main(String[] args) {
        try {
            // Konsol çıktısını UTF-8 olarak ayarladım
            //bende ÇIKTI UTF-8 olarak ayarlanmadığı için PrintStream kulandım
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("UTF-8 desteklenmiyor!");
            return;
        }

        Scanner scanner = new Scanner(System.in, "UTF-8");
        boolean devam = true;

        System.out.println("Çeviri Uygulaması");
        System.out.println("Çıkmak için 'x' yazın.");

        while (devam) {
            System.out.println("\n1. Arapça > Türkçe");
            System.out.println("2. Türkçe > Arapça");
            System.out.print("Seçiminiz (1 veya 2): ");
            int secim = scanner.nextInt();
            scanner.nextLine(); 

            if (secim == 1) {
                System.out.print("Arapça metin girin: ");
                String arapcaMetin = scanner.nextLine();
                if (arapcaMetin.equalsIgnoreCase("x")) {
                    devam = false;
                } else {
                    String turkceCeviri = arapcaToTurkceCevir(arapcaMetin);
                    System.out.println("Türkçe çeviri: " + turkceCeviri);
                }
            } else if (secim == 2) {
                System.out.print("Türkçe metin girin: ");
                String turkceMetin = scanner.nextLine();
                if (turkceMetin.equalsIgnoreCase("x")) {
                    devam = false;
                } else {
                    String arapcaCeviri = turkceToArapcaCevir(turkceMetin);
                    System.out.println("Arapça çeviri: " + arapcaCeviri);
                }
            } else {
                System.out.println("Geçersiz seçim! Lütfen 1 veya 2 girin.");
            }
        }

        System.out.println("Program sonlandırıldı.");
        scanner.close();
    }

    // Arapça metni Türkçe'ye çevir
    private static String arapcaToTurkceCevir(String metin) {
        StringBuilder sonuc = new StringBuilder();
        for (char harf : metin.toCharArray()) {
            sonuc.append(arapcaToTurkce.getOrDefault(harf, String.valueOf(harf)));
        }
        return sonuc.toString();
    }

    // Türkçe metni Arapça'ya çevir
    private static String turkceToArapcaCevir(String metin) {
        StringBuilder sonuc = new StringBuilder();
        for (char harf : metin.toCharArray()) {
            sonuc.append(turkceToArapca.getOrDefault(harf, String.valueOf(harf)));
        }
        return sonuc.toString();
    }
}
    



