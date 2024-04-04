package examples;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;


public class LottoTest {
    public static void main(String[] args) {

        String filePath = "src\\file\\Lotto.csv";
        int startRow = 2;
        int startColumn = 3;
        int endColumn = 8;
        int endRow = 500;

        if (filePath.endsWith(".csv")){
            readCSV(filePath,startRow,startColumn,endColumn,endRow);
        }
        else if (filePath.endsWith(".xlsx")){
        }
        else{System.out.println("unknown format...");}

    }
    private static void readCSV(String filePath,
                                int startRow, int startColumn, int endColumn, int endRow){
        Map<Integer, Integer> valueCounts = new HashMap<>(); // hashMap are good if there is really-big count of information and he can search fast

        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(filePath)))){
            List<String[]> lines = reader.readAll();

            for (int i = startRow-1; i <Math.min(endRow, lines.size()); i++){ //Math.min choosing/checking that we didn't chosed wrong number that can be more then,-"lines count"
                String[] line  = lines.get(i);                                // and if we choose endRow bigger then the file have he will correct it and the method will not drop...

                for(int j = startColumn-1; j < Math.min(endColumn, line.length);j++)
                {
                    if(!line[j].isEmpty()){
                        int value = Integer.parseInt(line[j]);
                        valueCounts.put(value, valueCounts.getOrDefault(value,0)+1);// .put - we know... getOrDefault checks if the list have the key we search. if not he will put default value "0" with the key,
                    }                                                                          //  then +1 cuz we found it in the method... and if we have the key allready then just value+1...
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        printValueMap(valueCounts);// the method below

    }

    private
    static void printValueMap(Map<Integer, Integer> valueCounts){
        System.out.println("occurrence of values: ");// (occurrence of) = Возникновение+-
        valueCounts.entrySet() //получает набор пар ключ-значение из map
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entity -> System.out.println
                        (entity.getKey()+" : "+entity.getValue()+ " times"));

    }
    /*
    Этот метод printValueMap принимает карту valueCounts,
     содержащую целочисленные значения и их количество вхождений.
      Метод выводит на экран пары "значение: количество вхождений" из карты valueCounts,
       отсортированные по убыванию количества вхождений.

    valueCounts.entrySet().stream() -
    создает поток из набора пар ключ-значение из карты valueCounts.

    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) -
    сортирует элементы потока по значениям (количеству вхождений) в обратном порядке.

    .forEach(entity -> System.out.println(entity.getKey()+" : "+entity.getValue()+ " times")); -
     для каждой пары ключ-значение в отсортированном потоке выполняется операция вывода на экран строки в формате "ключ : значение times", где times - это количество вхождений значения.

     */
}
